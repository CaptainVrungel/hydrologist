package ru.hydrologist.calculations;

import ru.hydrologist.dao.PostgreDAO;
import ru.hydrologist.exceptions.DataNotFoundException;
import ru.hydrologist.exceptions.InterpolatorException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean
@ApplicationScoped
public class CalculationList {
    public ArrayList<CalculationMethod> calculationList = new ArrayList<CalculationMethod>();

    private ArrayList<CalculationMethod> getCalculations() throws InterpolatorException {
        try {
            calculationList = new PostgreDAO().getExistingCalculationsList();
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
        return calculationList;
    }

    public ArrayList<CalculationMethod> getCalculationList() throws InterpolatorException {
        if(!calculationList.isEmpty()){
            return calculationList;
        }else{
            return getCalculations();
        }
    }
}
