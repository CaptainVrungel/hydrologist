package ru.hydrologist.calculations;


import ru.hydrologist.exceptions.SavingDataException;
import ru.hydrologist.users.User;

public interface Calculatelable {
    String getCalculationMethodName();
    User getUser();
    void saveCalculation() throws SavingDataException;
}
