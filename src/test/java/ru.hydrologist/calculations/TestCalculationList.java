package ru.hydrologist.calculations;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.postgresql.ds.PGPoolingDataSource;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.hydrologist.dao.TestPostgreDAO;
import ru.hydrologist.exceptions.InterpolatorException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;

public class TestCalculationList {
    private final int timeout = 6000;
    public Logger log = LogManager.getLogger(TestCalculationList.class);                  //Объект для логирования

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
    public void testGetCalculationList() throws InterpolatorException {
        CalculationList calculationList = new CalculationList();

        ArrayList<CalculationMethod> result = calculationList.getCalculationList();

        if(
                !result.get(0).getCalculationMethodName().equals("Расчёт максимальных расходов воды весеннего половодья")
        ){
            for(CalculationMethod res: result){
                System.out.println(res.getCalculationMethodName());
            }
            System.out.println(result.get(0).getCalculationMethodName());
            System.out.println(result.get(1).getCalculationMethodName());
            Assert.fail();
        }
    }
}


