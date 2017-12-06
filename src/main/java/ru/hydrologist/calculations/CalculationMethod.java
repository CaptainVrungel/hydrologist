package ru.hydrologist.calculations;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class CalculationMethod {
    private String calculationMethodName;
    private String calculationMethodNormativeDocument;
    private int ID;

    public CalculationMethod(){

    }

    public CalculationMethod(String calculationMethodName, String calculationMethodNormativeDocument, int ID) {
        this.calculationMethodName = calculationMethodName;
        this.calculationMethodNormativeDocument = calculationMethodNormativeDocument;
        this.ID = ID;
    }

    public String getCalculationMethodName() {
        return calculationMethodName;
    }

    public String getCalculationMethodNormativeDocument() {
        return calculationMethodNormativeDocument;
    }

    public int getID() {
        return ID;
    }
}
