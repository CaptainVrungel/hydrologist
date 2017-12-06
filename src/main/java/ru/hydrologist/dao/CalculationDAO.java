package ru.hydrologist.dao;

import ru.hydrologist.calculations.Calculatelable;
import ru.hydrologist.calculations.CalculationMethod;
import ru.hydrologist.exceptions.DataNotFoundException;
import ru.hydrologist.exceptions.DuplicateUsersException;
import ru.hydrologist.exceptions.SavingDataException;
import ru.hydrologist.exceptions.UserNotFoundException;
import ru.hydrologist.users.User;

import java.util.ArrayList;
import java.util.List;

public interface CalculationDAO {

    int saveCalculation(Calculatelable calculation) throws SavingDataException;
    List<Calculatelable> getCalculationListByUser(User user);
    Calculatelable getCalculationByID(int id) throws DataNotFoundException;
    User getUserByEmail(String email) throws UserNotFoundException;
    User getUserByID(int id) throws UserNotFoundException;
    ArrayList<CalculationMethod> getExistingCalculationsList() throws DataNotFoundException;
    Boolean registerUser(String email, String password, String confirmPassword) throws DuplicateUsersException;
    void changeUserPassword(User user, String password);


}
