package ru.hydrologist.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.ds.PGPoolingDataSource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.calculations.Calculatelable;
import ru.hydrologist.calculations.Calculation;
import ru.hydrologist.exceptions.DataNotFoundException;
import ru.hydrologist.exceptions.DuplicateUsersException;
import ru.hydrologist.exceptions.SavingDataException;
import ru.hydrologist.exceptions.UserNotFoundException;
import ru.hydrologist.users.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestPostgreDAO {

    private double accuracy = 0.000000001;                                        //Точность вычислений
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestPostgreDAO.class);                  //Объект для логирования

    @BeforeClass
    public static void setUpClass() throws Exception {
        Logger logger = LogManager.getLogger(TestPostgreDAO.class);
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            // Construct DataSource
            PGPoolingDataSource ds = new PGPoolingDataSource();
            ds.setUrl("jdbc:postgresql://localhost/postgres");
            ds.setUser("postgres");
            ds.setPassword("Asdfg9876");

            ic.bind("java:/comp/env/jdbc/postgres", ds);
        } catch (NamingException ex) {
            logger.error(ex.getMessage() + " - " + ex.getStackTrace());
        }

    }

    @Test(timeOut = timeout)
    public void testDBConnection(){
        PostgreDAO PostgreDAO = new PostgreDAO();
    }

    @Test(timeOut = timeout)
    public void testGetUserByEmail() throws UserNotFoundException {
        PostgreDAO dao = new PostgreDAO();
        String email = "testovich@hydrologist.ru";
        User user = dao.getUserByEmail(email);

        Assert.assertEquals(user.getEmail(), "testovich@hydrologist.ru");
        Assert.assertEquals(user.getFullName(), "Тестов Тест Тестович");
    }

    @Test(timeOut = timeout, expectedExceptions = UserNotFoundException.class)
    public void testGetUserByEmailException() throws UserNotFoundException {
        PostgreDAO dao = new PostgreDAO();
        String email = "aksfgladkshfgasklfgasdlkf";
        User user = dao.getUserByEmail(email);
    }

    @Test(timeOut = timeout)
    public void getUserByID() throws UserNotFoundException {
        PostgreDAO dao = new PostgreDAO();
        int id = 2;
        User user = dao.getUserByID(id);

        Assert.assertEquals(user.getEmail(), "testovich@hydrologist.ru");
        Assert.assertEquals(user.getFullName(), "Тестов Тест Тестович");
    }

    @Test(timeOut = timeout)
    public void testSaveCalculation() throws UserNotFoundException, DataNotFoundException, SavingDataException {
        String method = "Метод проб и ошибок";
        String email = "testovich@hydrologist.ru";
        PostgreDAO dao = new PostgreDAO();

        User user = dao.getUserByEmail(email);
        Calculation calculation = new Calculation(method, user);
        int calculationId = dao.saveCalculation(calculation);
        Calculatelable testCalculation = dao.getCalculationByID(calculationId);
        Assert.assertEquals(testCalculation.getCalculationMethodName(), method);
        Assert.assertEquals(testCalculation.getUser().getEmail(), email);
    }

    @Test(timeOut = timeout)
    public void testGetCalculationListByUser() throws Exception {
        String email = "testovich@hydrologist.ru";
        PostgreDAO dao = new PostgreDAO();

        User user = dao.getUserByEmail(email);
        List<Calculatelable> calculations = dao.getCalculationListByUser(user);

        if(calculations.size() == 0){
            Assert.fail();
        }
        for(Calculatelable c: calculations){
            Assert.assertEquals(email, c.getUser().getEmail());
        }
    }

    @Test(timeOut = timeout)
    public void testGetCalculationByID() throws DataNotFoundException {
        int id = 19;
        String result = "Определение предела терпения у заказчика";

        PostgreDAO dao = new PostgreDAO();
        Calculatelable calculation = dao.getCalculationByID(id);

        Assert.assertEquals(result, calculation.getCalculationMethodName());
    }

    @Test(timeOut = timeout, expectedExceptions = DataNotFoundException.class)
    public void testGetCalculationByIDException() throws DataNotFoundException {
        PostgreDAO dao = new PostgreDAO();

        int id = 0;

        Calculatelable calculation = dao.getCalculationByID(id);
    }

    @Test(timeOut = timeout)
    public void testGetUserRolesByUser() throws DataNotFoundException, UserNotFoundException {
        PostgreDAO dao = new PostgreDAO();

        ArrayList<String> ultimateTruth = new ArrayList<String>();
        ultimateTruth.add("user");
        ultimateTruth.add("admin");

        ArrayList<String> result = new ArrayList<String>();
        String email = "fedorovskiy1@yandex.ru";
        User user = dao.getUserByEmail(email);

        result = dao.getUserRolesByUser(user);

        if(ultimateTruth.size() != result.size()){
            Assert.fail();
        }

        for(int i=0; i<ultimateTruth.size(); i++){
            if( !result.contains(ultimateTruth.get(i)) ){
                Assert.fail();
            }
        }
    }

    @Test(timeOut = timeout, expectedExceptions = DuplicateUsersException.class)
    public void testRegistrateUserExistingUser() throws DuplicateUsersException, UserNotFoundException {
        String email = "fea@fea.ru";
        String password = "123qwe";
        String confirmPassword = "123qwe";

        CalculationDAO dao = new PostgreDAO();

        boolean result = dao.registerUser(email, password, confirmPassword);
    }

    @Test(timeOut = timeout)
    public void testRegistrateUserWrongConfirmPassword() throws DuplicateUsersException {
        String email = UUID.randomUUID().toString();
        String password = "123qwe";
        String confirmPassword = "123";

        CalculationDAO dao = new PostgreDAO();
        boolean result = dao.registerUser(email, password, confirmPassword);

        Assert.assertEquals(result, false);
    }

    @Test(timeOut = timeout)
    public void testRegistrateUser() throws UserNotFoundException {
        String email = "testEmail4@test.ru";
        String password = "123qwe";
        String confirmPassword = "123qwe";

        CalculationDAO dao = new PostgreDAO();
        try {
            boolean result = dao.registerUser(email, password, confirmPassword);
        } catch (DuplicateUsersException e) {
            e.printStackTrace();
        }

        String resultEmail = dao.getUserByEmail(email).getEmail();
        System.out.println(resultEmail);

        Assert.assertEquals(resultEmail, email);
    }

    @Test(timeOut = timeout)
    public void testChangeUserPassword() throws UserNotFoundException {
        String password = "123qwe";

        CalculationDAO dao = new PostgreDAO();

        String email = "fedorovskiy1@yandex.ru";
        User user = dao.getUserByEmail(email);

        dao.changeUserPassword(user, password);
    }

}