package ru.hydrologist.guiElements;

import org.primefaces.model.StreamedContent;
import ru.hydrologist.calculations.Calculation;
import ru.hydrologist.calculations.SpringFloodMaximumWaterFlowCalculationSP331012003;
import ru.hydrologist.drawing.Graph;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.probabilityDistribution.KrickyMenkelDistribution;
import ru.hydrologist.probabilityDistribution.PirsonDistribution;
import ru.hydrologist.probabilityDistribution.ThreeParametrGammaDistribution;
import ru.hydrologist.ranges.AnalystRangable;
import ru.hydrologist.ranges.EmpiricRangable;
import ru.hydrologist.ranges.EmpiricRange;
import ru.hydrologist.ranges.ProbabilityDistribution;
import ru.hydrologist.utils.DataConverter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.validator.ValidatorException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;
import java.util.List;

//РЕФАКТОРИТЬ МОЩНО
//Использован фактори метод для создания расчёта, но в итоге, всё равно привязался напрямую к конкретному расчёту
//Может стоит сделать управляемыми бинами прямо EmpiricRange и т.д.?
//Для подготовки данных для страницы пришлось сделать целый новый объект SingleObservation, может его можно вынести в бизнес-логику?
@ManagedBean
@ViewScoped
public class formBean implements Serializable{

    private int showBlock;
    private int showSubBlock;

    private List<EmpiricObservation> empiricObservations;  //Поля ввода на форме
    private String objectType;
    private String objectName;
    private String observationPostName;
    private String observationPostCode;
    private String additionalInformation;

    private ProbabilityDistribution selectedDistribution;
    private List<ProbabilityDistribution> distributionTypes = new ArrayList<ProbabilityDistribution>();
    private Double projectProbability;
    private Double projectValueForProbability;

    private Double rangeAverage;
    private List<Double> rangedRange; 	    //отсортированный ряд наблюдений
    private List<Double> probabilities;     //вероятности
    private Double r; 						//коэффициент автокорреляции
    private Double cv;						//коэффициент вариации
    private Double cs;						//коэффцицинет ассиметрии
    private Double cscv;					//соотношение Cs/Cv

    private List<SingleObservation> observations = new ArrayList<SingleObservation>();
    private List<SingleObservation> analystRangeValues = new ArrayList<SingleObservation>();

    private Map<Double, Double> analystRange;   //аналитический ряд наблюдений
    private String distributionName;            //Название распределения вероятностей

    private SpringFloodMaximumWaterFlowCalculationSP331012003 calculation;
    private Graph ensuranceCurve;

    BufferedImage image;
    private StreamedContent primeImage;

    private String curveLink;

    private String empiricColor;
    private String analystColor;
    private Boolean drawInscription = true;
    private Boolean drawConnectionLine = false;
    private Boolean fillPoints = true;
    private Boolean showAnalystCurve = true;

    private Map<String, Color> colors = new HashMap<String, Color>();

    @PostConstruct
    public void init() {
        empiricObservations = new ArrayList<EmpiricObservation>();
        add();

        this.distributionTypes.add(new ThreeParametrGammaDistribution());
        this.distributionTypes.add(new PirsonDistribution());
        this.distributionTypes.add(new KrickyMenkelDistribution());

        selectedDistribution = new ThreeParametrGammaDistribution();
        projectProbability = 1.0;
        showBlock = 1;

        colors.put("Черный", Color.black);
        colors.put("Красный", Color.red);
        colors.put("Зелёный", Color.green);
        colors.put("Синий", Color.blue);
        colors.put("Серый", Color.lightGray);

        empiricColor = "Черный";
        analystColor = "Красный";

        showAnalystCurve = true;
    }

    public void add(){
        empiricObservations.add(new EmpiricObservation());
    }

    public void distributeData(){

        String firstValue = empiricObservations.get(0).getValue();
        String[] parts = firstValue.split(" ");
        int length = parts.length;

        if(length > 1){
            if(length > empiricObservations.size()){
                int difference = length - empiricObservations.size();
                for(int i=0; i<difference; i++){
                    empiricObservations.add(new EmpiricObservation());
                }
            }

            for(int i=0; i<length; i++){
                empiricObservations.get(i).setValue(parts[i]);
            }
        }

        EmpiricObservation lastInput = empiricObservations.get(empiricObservations.size()-1);
        if(lastInput.getValue() != null && lastInput.getValue() != "" && lastInput.getValue() != " "){
            add();
        }

    }

    public void remove(EmpiricObservation obs){
        empiricObservations.remove(obs);
    }

    public void fireCalculation(){
        ArrayList<Double> rowEmpiricRange = new ArrayList<Double>();
        EmpiricRangable empiricRange;
        AnalystRangable analystRange;
        Calculation calculationFactory = new Calculation();

        for(EmpiricObservation empiricObservation : empiricObservations){
            if(empiricObservation.getValue() != null && empiricObservation.getValue() != ""){
                rowEmpiricRange.add(Double.valueOf(empiricObservation.getValue()));
            }
        }

        empiricRange = new EmpiricRange(rowEmpiricRange);
        try {
            calculation = calculationFactory.createSpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange, selectedDistribution);
            this.rangedRange = empiricRange.getRangedRange();
            this.probabilities = empiricRange.getProbabilities();
            this.rangeAverage = empiricRange.getRangeAverage();
            this.r = DataConverter.smartHydrologicalRound( empiricRange.getR());
            this.cs = DataConverter.smartHydrologicalRound( empiricRange.getCs());
            this.cscv = DataConverter.smartHydrologicalRound( empiricRange.getCsCv() );
            this.cv = DataConverter.smartHydrologicalRound(empiricRange.getCv());

            observations.clear();

            for(int i=0; i<empiricRange.getRangedRange().size(); i++){
                observations.add(new SingleObservation(this.rangedRange.get(i), DataConverter.smartHydrologicalRound( this.getProbabilities().get(i)) * 100));
            }
            analystRange = calculation.getAnalystRange();

            analystRangeValues.clear();

            for (Map.Entry<Double, Double> entry : analystRange.getAnalystRange().entrySet()) {
                this.analystRangeValues.add( new SingleObservation( DataConverter.smartHydrologicalRound(  entry.getValue() ), entry.getKey()) );
                this.analystRangeValues.sort(

                        new Comparator<SingleObservation>() {
                            public int compare(SingleObservation o1, SingleObservation o2) {
                                return o2.compareTo(o1);
                            }
                        }

                );
            }

            this.analystRange = analystRange.getAnalystRange();
            this.distributionName = analystRange.getDistributionName();

        } catch (InterpolatorException e) {
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public void saveEmpiricGraph() throws InterpolatorException {
        getCurveLink();
    }

    public Double getProjectValueForProbability(){
        this.projectValueForProbability = null;

        if(analystRange != null){
            this.projectValueForProbability = DataConverter.smartHydrologicalRound( selectedDistribution.getDistributionForProbability(projectProbability, analystRange));
        }

        return projectValueForProbability;
    }


    public List<Double> getRangedRange() {
        return rangedRange;
    }

    public List<Double> getProbabilities() {
        return probabilities;
    }

    public Double getR() {
        return r;
    }

    public Double getCv() {
        return cv;
    }

    public Double getCs() {
        return cs;
    }

    public Double getCscv() {
        return cscv;
    }

    public Map<Double, Double> getAnalystRange() {
        return analystRange;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public void setCv(Double cv) {
        this.cv = cv;
    }

    public void setCs(Double cs) {
        this.cs = cs;
    }

    public void setCscv(Double cscv) {
        this.cscv = cscv;
    }

    public List<SingleObservation> getObservations() {
        return observations;
    }

    public void setObservations(List<SingleObservation> observations) {
        this.observations = observations;
    }

    public Graph getEnsuranceCurve() {
        return ensuranceCurve;
    }

    public void setEnsuranceCurve(Graph ensuranceCurve) {
        this.ensuranceCurve = ensuranceCurve;
    }

    public BufferedImage getImage() {
        return image;
    }

    public StreamedContent getPrimeImage() {
        return primeImage;
    }

    public List<SingleObservation> getAnalystRangeValues() {
        return analystRangeValues;
    }

    public String getCurveLink() throws InterpolatorException {

        if(calculation == null){
            curveLink = "no link";
        }else{
            String fileName = calculation.getEnsuranceCurveFileName(
                    colors.get(empiricColor),
                    colors.get(analystColor),
                    drawInscription, drawConnectionLine, fillPoints, showAnalystCurve
            );
            this.curveLink = "http://localhost:8080/getCurve?imageId="+fileName;
        }
        return curveLink;
    }

    public List<EmpiricObservation> getEmpiricObservations() {
        return empiricObservations;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObservationPostName() {
        return observationPostName;
    }

    public void setObservationPostName(String observationPostName) {
        this.observationPostName = observationPostName;
    }

    public String getObservationPostCode() {
        return observationPostCode;
    }

    public void setObservationPostCode(String observationPostCode) {
        this.observationPostCode = observationPostCode;
    }

    public List<ProbabilityDistribution> getDistributionTypes() {
        return distributionTypes;
    }

    public ProbabilityDistribution getSelectedDistribution() {
        return selectedDistribution;
    }

    public void setSelectedDistribution(ProbabilityDistribution selectedDistribution) {
        this.selectedDistribution = selectedDistribution;
    }

    public Double getProjectProbability() {
        return projectProbability;
    }

    public void setProjectProbability(Double projectProbability) {
        this.projectProbability = projectProbability;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public int getShowBlock() {
        return showBlock;
    }

    public void setShowBlock(int showBlock) {
        this.showBlock = showBlock;
    }

    public Double getRangeAverage() {
        return rangeAverage;
    }

    public int getShowSubBlock() {
        return showSubBlock;
    }

    public void setShowSubBlock(int showSubBlock) {
        this.showSubBlock = showSubBlock;
    }

    public String getEmpiricColor() {
        return empiricColor;
    }

    public void setEmpiricColor(String empiricColor) {
        this.empiricColor = empiricColor;
    }

    public String getAnalystColor() {
        return analystColor;
    }

    public void setAnalystColor(String analystColor) {
        this.analystColor = analystColor;
    }

    public Boolean getDrawInscription() {
        return drawInscription;
    }

    public void setDrawInscription(Boolean drawInscription) {
        this.drawInscription = drawInscription;
    }

    public Boolean getDrawConnectionLine() {
        return drawConnectionLine;
    }

    public void setDrawConnectionLine(Boolean drawConnectionLine) {
        this.drawConnectionLine = drawConnectionLine;
    }

    public Boolean getFillPoints() {
        return fillPoints;
    }

    public void setFillPoints(Boolean fillPoints) {
        System.out.println("setFillPoints");
        this.fillPoints = fillPoints;
    }

    public Map<String, Color> getColors() {
        return colors;
    }

    public Boolean getShowAnalystCurve() {
        return showAnalystCurve;
    }

    public void setShowAnalystCurve(Boolean showAnalystCurve) {
        System.out.println("showAnalystCurve");
        this.showAnalystCurve = showAnalystCurve;
    }
}
