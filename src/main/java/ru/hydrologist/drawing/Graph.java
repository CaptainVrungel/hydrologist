package ru.hydrologist.drawing;

import ru.hydrologist.utils.DataConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


//Общий рефакторинг. Сейчас без стакана не разберёшься. Много дублирования кода
//Легенда графика
//Название графика с переносом строки
public class Graph implements Graphable{
    protected DrawableDataSet dataSet;

    private int imageWidth = 2260;          //величина картинки по x
    private int imageHeight = 1600;         //величина картинки по y
    private int indentXPersent = 4;         //отступ от края картинки по X в процентах
    private int indentYPersent = 4;         //отступ от края картинки по Y в процентах
    private int indentXPersentLeft = 6;     //отступ от левого края картинки по Х в процентах
    private int indentYPersentBottom = 4;   //отступ снизу от края картинки по Y в процентах

    private int axisTextSizePersent = 120;       //Размер текста, подписывающего цифры шкалах в %
    private int axisLabelTextSizePersent = 150;  //Размер текста, подписывающего названия шкал на графике в %
    private int pointsTextSizePersent = 100;     //Размер текста, подписывающего цифры на графике в %
    private int dashSizePersent = 50;            //Размер рисок на шкалах, в процентных пунктах (чтобы получить проценты, нужно разделить на 100)
    private int pointSizePersent = 50;           //Размер точек в %

    private String axisXLabel = "P,%";       //Подпись оси X
    private String axisYLabel = "Q,м3/с";    //Подпись оси Y
    private String graphLabel = "";          //Название графика

    private Color backgroundColor = Color.white;    //Цвет фона для картинки
    private Color axisColor = Color.black;          //Цвет шкал
    private Color additionalAxisColor = Color.gray; //Цвет дополнительных линий

    protected BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);                                    //Само изображение
    protected double yTransformFactor;                                //коэффициент преобразования масштаба по У
    protected double xTransformFactor;                                //коэффициент преобразования масштаба по оси Х

    private String SAVEPATH = "C:\\temp\\img\\";            //Директория для сохранения сгенерированных графиков
    private String SAVEFORMAT = "PNG";                          //Формат, в котором будут сохраняться сгенерированные графики

    public Graph(){

    }

    public Graph(DrawableDataSet dataSet){
        this.dataSet = dataSet;

        this.xTransformFactor = ((double) getAxisXLength())/getAxisXDiapason(); //Коэффициент, преобразующий расстояние по оси х графика в расстояние на нашей кривой на рисунке
        this.yTransformFactor = ((double) getYAxisLength())/getAxisYDiapason(); //считаем коэффициент преобразования масштаба по y
    }

    public EnsuranceCurve createEnsuranceCurve(DrawableDataSet dataSet){
        return new EnsuranceCurve(dataSet);
    }

    public RelationGraph createRelationGraph(DrawableDataSet dataSet){
        return new RelationGraph(dataSet);
    }

    public void createGraph(){
        Graphics graphics = getGraphicsObject(backgroundColor);

        drawBackground();
        drawAxes();
        drawAxesArrows();
        drawAxesLabels();
        drawGraphLabel();
        drawGridX();
        drawGridY();
        drawGridXLabels();
        drawGridYLabels();
        drawData();
        inscriptData();

        graphics.drawImage(image,0, 0, imageWidth, imageHeight,null);
    }

    protected void drawBackground(){
        Graphics2D background =  getGraphicsObject(backgroundColor);
        background.fillRect(0,0, imageWidth, imageHeight);
    }

    //рисуем шкалы
    protected void drawAxes(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor);
        axisGraphics.draw(new Line2D.Double(getAxisXCoordinate(), getAxisYCoordinate(), getAxisXCoordinate() + getAxisXLength(), getAxisYCoordinate() )); //рисуем ось х
        axisGraphics.draw(new Line2D.Double(getAxisXCoordinate(), getAxisYCoordinate(), getAxisXCoordinate(), getAxisYCoordinate() - getYAxisLength())); //рисуем ось y
    }

    //рисуем стрелки на шкалах
    protected void drawAxesArrows(){
        Graphics2D arrowGraphics = getGraphicsObject(axisColor);

        Polygon yArrow = new Polygon();
        yArrow.addPoint(getAxisXCoordinate()-getDashSize()/2, getAxisYCoordinate()-getYAxisLength());
        yArrow.addPoint(getAxisXCoordinate()+getDashSize()/2, getAxisYCoordinate()-getYAxisLength());
        yArrow.addPoint(getAxisXCoordinate(), getAxisYCoordinate()-getYAxisLength()-getDashSize()*2);

        Polygon xArrow = new Polygon();
        xArrow.addPoint(getAxisXCoordinate() + getAxisXLength(), getAxisYCoordinate()-getDashSize()/2);
        xArrow.addPoint(getAxisXCoordinate() + getAxisXLength(), getAxisYCoordinate()+getDashSize()/2);
        xArrow.addPoint(getAxisXCoordinate() + getAxisXLength() + getDashSize()*2, getAxisYCoordinate());

        arrowGraphics.fillPolygon(yArrow);
        arrowGraphics.fillPolygon(xArrow);
    }

    //подписываем шкалы
    protected void drawAxesLabels(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor, getAxisLabelTextSize());
        axisGraphics.drawString(axisYLabel,  getAxisXCoordinate()-getAxisTextSize(), getAxisYCoordinate() - getYAxisLength() - getAxisTextSize()*2);    //Подпись оси х
        axisGraphics.drawString(axisXLabel,  getAxisXCoordinate() + getAxisXLength() + getAxisTextSize(), getAxisYCoordinate() - getAxisTextSize()/2);    //Подпись оси у
    }

    //Подписываем график
    protected void drawGraphLabel(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor, getAxisLabelTextSize());
        axisGraphics.drawString(graphLabel,  getAxisXCoordinate(), getAxisYCoordinate() + getAxisTextSize()*4);    //Подпись графика
    }

    //Схема рисования осей х и у похожа. Может можно выделить какой-от общий код
    //рисуем координатную сетку на оси Х
    protected void drawGridX(){
        Graphics2D axisGraphics = getGraphicsObject(getAxisColor(), getAxisLabelTextSize());
        Graphics2D additionalAxisGraphics = getGraphicsObject(additionalAxisColor, "additional");

        for(double i = 0; i <= (double) getAxisXLength(); i += getDashStepXTransformed()){
            //Рисуем риски
            axisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + i, getAxisYCoordinate(), getAxisXCoordinate() + i, getAxisYCoordinate()+getDashSize()/2));
            //Рисуем линии сетки
            axisGraphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{6f, 6f}, 0f));
            axisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + i, getAxisYCoordinate(), getAxisXCoordinate() + i, getAxisYCoordinate()-getYAxisLength()));
        }
         //Промежуточные риски на шкале x
        for(double i = 0; i< getAxisXLength(); i += getAxisXDashStepAdditional()*xTransformFactor){
            additionalAxisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + i, getAxisYCoordinate(), (double)getAxisXCoordinate() + i, (double)getAxisYCoordinate()-getYAxisLength()));
        }
    }

    //рисуем координатную сетку на оси Y
    protected void drawGridY(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor, getAxisLabelTextSize());
        Graphics2D additionalAxisGraphics = getGraphicsObject(additionalAxisColor, "additional");
        /*Деления на шкале y*/
        for(double i=0; i <= (double) getYAxisLength(); i += getDashStepYTransformed()){ //Тут потенциально могут быть проблемы. Плюс не понятно, откуда берётся шаг, с которым мы пойдём
            //Рисуем риски у значений
            axisGraphics.draw(new Line2D.Double((double)getAxisXCoordinate() - ((double) getDashSize())/2, (double)getAxisYCoordinate()-i, (double)getAxisXCoordinate(), (double)getAxisYCoordinate()-i));
            //Рисуем линии напротив значений (пунктиром)
            axisGraphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{6f, 6f}, 0f));
            axisGraphics.draw(new Line2D.Double(getAxisXCoordinate(), (double)getAxisYCoordinate()-i, (double)getAxisXCoordinate() + (double) getAxisXLength(), (double)getAxisYCoordinate()-i));
        }

    	/*Промежуточные риски на шкале y*/
        for(double i = 0; i<getYAxisLength(); i += getAxisYDashStepAdditional()*yTransformFactor){
            additionalAxisGraphics.draw(new Line2D.Double(getAxisXCoordinate(), (double)getAxisYCoordinate()-i, (double)getAxisXCoordinate() + (double) getAxisXLength(), (double)getAxisYCoordinate()-i));
        }
    }

    //подписываем координатную сетку на X
    protected void drawGridXLabels(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor, getAxisLabelTextSize());
        int count = 0;
        for(double i=getAxisXMinimumValue(); i <= getAxisXMaximumValue(); i += getAxisXDashStep()){
            //Подписываем значения на оси x
            axisGraphics.drawString(Double.toString(DataConverter.DoubleRound(i, 3)), (int) ((double)getAxisXCoordinate()+getAxisXDashStep()*count*xTransformFactor), (int) ((double)getAxisYCoordinate()+((double)getAxisTextSize())*1.3));
            count++;
        }
    }

    //подписываем координатную сетку на Y
    protected void drawGridYLabels(){
        Graphics2D axisGraphics = getGraphicsObject(axisColor, getAxisLabelTextSize());
        int count = 0;
        for(double i=getAxisYMinimumValue(); i <= getAxisYMaximumValue(); i += getAxisYDashStep()){
            //Подписываем значения на оси Y
            int valueTextLength = Double.toString(DataConverter.DoubleRound(i, 3)).length();
            axisGraphics.drawString(Double.toString(DataConverter.DoubleRound(i, 3)), (int) (getAxisXCoordinate()-getAxisTextSize()*valueTextLength/1.5), (int) ((double)getAxisYCoordinate()-getAxisYDashStep()*count*yTransformFactor));
            count++;
        }
    }

    //наносим данные на график
    protected void drawData(){
        for(DrawableData data: dataSet.getDrawingDataSet()){
            Graphics2D pointsGraphics = getGraphicsObject(data.getPointsColor());
            for(GraphPoint point : data.getPoints()){
                if(data.fillPoints()){
                    pointsGraphics.fill(new Ellipse2D.Double(getTransformedXCoordinate(point) - getPointSize()/2, getTransformedYCoordinate(point) - getPointSize()/2, getPointSize(), getPointSize()));
                }else{
                    pointsGraphics.draw(new Ellipse2D.Double(getTransformedXCoordinate(point) - getPointSize()/2, getTransformedYCoordinate(point) - getPointSize()/2, getPointSize(), getPointSize()));
                }
            }
        }
    }

    //Подписываем данные на графике
    protected void inscriptData(){
        for(DrawableData data: dataSet.getDrawingDataSet()){
            Graphics2D pointsGraphics = getGraphicsObject(data.getPointsColor(), getPointsTextSize());
            if(data.DrawInscription()){
                for(GraphPoint point : data.getPoints()){
                    pointsGraphics.drawString(DataConverter.smartHydrologicalRoundToString(point.getYCoordinate()).toString(), (int) getTransformedXCoordinate(point), (int) (getTransformedYCoordinate(point)-getPointSize()/1.5));
                }
            }
        }
    }

    public void saveGraph(String fileName){
        try {
            ImageIO.write(image, SAVEFORMAT, new File(SAVEPATH + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Double getAxisXDashStep(){
        int maxXDashNumber = imageWidth/100 - 1;
        int minXDashNumber = 8;
        int maxValueLength = Integer.toString((int) dataSet.getXPointsMaximum()).length();

        return getAxisDashStep(dataSet.getXPointsMaximum() - dataSet.getXPointsMinimum(), maxXDashNumber, minXDashNumber, maxValueLength);
    }

    private Double getAxisXDashStepAdditional(){
        return getAxisXDashStep()/10;
    }

    private Double getAxisYDashStep(){
        int maxYDashNumber = imageHeight/100;
        int minYDashNumber = 6;
        int maxValueLength = Integer.toString((int) (double) dataSet.getYPointsMaximum()).length();

        return getAxisDashStep(dataSet.getYPointsMaximum() - dataSet.getYPointsMinimum(), maxYDashNumber, minYDashNumber, maxValueLength);
    }

    private Double getAxisYDashStepAdditional(){
        return getAxisYDashStep()/10;
    }

    private Double getAxisDashStep(Double diapason, int maxDashNumber, int minDashNumber, int maxValueLength){
        Double[] axisValues = {1.0, 2.0, 5.0, 10.0, 20.0, 50.0};
        ArrayList<Double> result = new ArrayList<Double>();

        for(Double d: axisValues){
            Double coefficient = d*Math.pow(10, maxValueLength-2);

            if(diapason/coefficient <= maxDashNumber && diapason/coefficient >= minDashNumber){
                result.add(coefficient);
            }
        }
        return Collections.max(result);
    }

    protected Double getAxisXMinimumValue(){
        return getAxisMinimumValue(dataSet.getXPointsMinimum(), getAxisXDashStep());
    }

    protected Double getAxisYMinimumValue(){
        return getAxisMinimumValue(dataSet.getYPointsMinimum(), getAxisYDashStep());
    }

    //Получаем минимальное значение, с которого начнётся шкала на оси
    private Double getAxisMinimumValue(Double min, Double step){
        if(min <= step){
            return 0.0;
        }else{
            return step * ((int) (min/step));
        }
    }

    public Double getAxisXMaximumValue(){
        return getAxisMaximumValue(dataSet.getXPointsMaximum(), getAxisXDashStep());
    }

    public Double getAxisYMaximumValue(){
        return getAxisMaximumValue(dataSet.getYPointsMaximum(), getAxisYDashStep());
    }

    //Получаем максимальное значение, на котором закончится шкала на оси
    protected Double getAxisMaximumValue(Double max, Double step){
        if(max <= step){
            return step;
        }
        int stepNumber = (int) Math.ceil(max/step);
        if( max == step * stepNumber){
            return step * (stepNumber+1);
        }else{
            return step * (stepNumber);
        }
    }

    public Double getAxisXDiapason(){
        return getAxisXMaximumValue() - getAxisXMinimumValue();
    }

    public Double getAxisYDiapason(){
        return getAxisYMaximumValue() - getAxisYMinimumValue();
    }

    public int getAxisXLength(){
        return imageWidth-(getIndentX() + getIndentXLeft() + axisXLabel.length() * getAxisLabelTextSize());
    }

    //Возвращаем отступ от края картинки по X
    public int getIndentX(){
        return (int) indentXPersent*imageWidth/100;
    }

    //Возвращаем отступ от левого края картинки по X
    public int getIndentXLeft(){
        return (int) indentXPersentLeft*imageWidth/100;
    }

    //Длина оси координат Y графика
    public int getYAxisLength(){
        return  imageHeight-(getIndentY()*2 + getIndentYBottom())- getAxisLabelTextSize();
    }

    //Возвращаем отступ от края картинки по Y
    public int getIndentY(){
        return (int) indentYPersent*imageWidth/100;
    }

    //Возвращаем отступ снизу от края картинки по Y
    public int getIndentYBottom(){
        return (int) indentYPersentBottom*imageWidth/100;
    }

    //Размер текста, подписывающего названия шкал на графике
    public int getAxisLabelTextSize(){
        return (int) axisLabelTextSizePersent*imageWidth/10000;
    }

    //Координата Х начала координат графика
    public int getAxisXCoordinate(){
        return getIndentXLeft();
    }

    //Координата Y начала координат графика
    public int getAxisYCoordinate(){
        return imageHeight-getIndentYBottom()-getIndentY();
    }

    //Размер текста, подписывающего цифры шкалах
    public int getAxisTextSize(){
        return (int) axisTextSizePersent*imageWidth/10000;
    }

    //Возвращаем размер рисок на шкалах
    public int getDashSize(){
        return  dashSizePersent*imageWidth/10000;
    }

    //Возвращаем размер точек
    public int getPointSize(){
        return (int) pointSizePersent*imageWidth/10000;
    }

    //Размер текста, подписывающего цифры на графике
    public int getPointsTextSize(){
        return (int) pointsTextSizePersent*imageWidth/10000;
    }

    protected double getDashStepYTransformed(){
        return getAxisYDashStep()*yTransformFactor;
    }

    protected double getDashStepXTransformed(){
        return xTransformFactor * getAxisXDashStep();
    }

    protected Graphics2D getGraphicsObject(Color color, int textSize){
        return getGraphicsObject(color, textSize, "usual");
    }

    protected Graphics2D getGraphicsObject(Color color){
        return getGraphicsObject(color, 0, "usual");
    }

    protected Graphics2D getGraphicsObject(Color color, String type){
        return getGraphicsObject(color, 0, type);
    }

    protected Graphics2D getGraphicsObject(Color color, int textSize, String type){
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(color);
        graphics.setFont(new Font("Serif", Font.PLAIN, textSize));

        if(type == "additional"){
            graphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{1f, 5f}, 0f));
        }

        return graphics;
    }

    protected Color getAxisColor() {
        return axisColor;
    }

    protected Color getAdditionalAxisColor() {
        return additionalAxisColor;
    }

    protected int getTransformedXCoordinate(GraphPoint point){
        return getAxisXCoordinate() + (int) (Math.round(point.getXCoordinate()*xTransformFactor) - getAxisXMinimumValue()*xTransformFactor);
    }

    protected int getTransformedYCoordinate(GraphPoint point){
        return getAxisYCoordinate() - (int) (Math.round(point.getYCoordinate()*yTransformFactor) - getAxisYMinimumValue()*yTransformFactor);
    }

    public BufferedImage getImage() {
        return image;
    }
}
