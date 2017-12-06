package ru.hydrologist.calculations;


import ru.hydrologist.dao.PostgreDAO;
import ru.hydrologist.exceptions.InterpolatorException;
import ru.hydrologist.exceptions.SavingDataException;
import ru.hydrologist.ranges.EmpiricRangable;
import ru.hydrologist.ranges.ProbabilityDistribution;
import ru.hydrologist.users.User;

public class Calculation implements Calculatelable{
    protected String CALCULATIONMETHODNAME;
    protected int ID;
    protected User user;

    public Calculation(){
    }

    public Calculation(int ID, String CALCULATIONMETHODNAME, User user){
        this(CALCULATIONMETHODNAME, user);
        this.ID = ID;
    }

    public Calculation(String CALCULATIONMETHODNAME, User user){
        this.CALCULATIONMETHODNAME = CALCULATIONMETHODNAME;
        this.user = user;
    }

    public Calculation(User user){
        this.user = user;
    }

    public SpringFloodMaximumWaterFlowCalculationSP331012003 createSpringFloodMaximumWaterFlowCalculationSP331012003(EmpiricRangable empiricRange) throws InterpolatorException {
        return new SpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange);
    }

    public SpringFloodMaximumWaterFlowCalculationSP331012003 createSpringFloodMaximumWaterFlowCalculationSP331012003(EmpiricRangable empiricRange, ProbabilityDistribution distribution) throws InterpolatorException {
        return new SpringFloodMaximumWaterFlowCalculationSP331012003(empiricRange, distribution);
    }

    public void saveCalculation() throws SavingDataException {
        new PostgreDAO().saveCalculation(this);
    }

    public String getCalculationMethodName() {
        return CALCULATIONMETHODNAME;
    }

    public User getUser() {
        return user;
    }
}
