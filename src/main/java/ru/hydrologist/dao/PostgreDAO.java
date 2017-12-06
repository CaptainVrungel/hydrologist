package ru.hydrologist.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.hydrologist.calculations.Calculatelable;
import ru.hydrologist.calculations.Calculation;
import ru.hydrologist.calculations.CalculationMethod;
import ru.hydrologist.exceptions.DataNotFoundException;
import ru.hydrologist.exceptions.DuplicateUsersException;
import ru.hydrologist.exceptions.SavingDataException;
import ru.hydrologist.exceptions.UserNotFoundException;
import ru.hydrologist.security.CustomHash;
import ru.hydrologist.users.User;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreDAO implements CalculationDAO{
    private Logger log = LogManager.getLogger(PostgreDAO.class);
    private String dataBaseConnectionName = "java:/comp/env/jdbc/postgres";

    public int saveCalculation(Calculatelable calculation) throws SavingDataException {
        Connection connection = initConnection();
        int calculationId = -1;
        int count = 0;
        String sql = "INSERT INTO hydrologist_test.calculations (calculation_method_name, user_id) VALUES(?, ?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, calculation.getCalculationMethodName());
            statement.setInt(2, calculation.getUser().getID());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            while(rs.next()){
                count++;
                calculationId = rs.getInt("id");
            }

            statement.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.error(e.getStackTrace());
        }finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(calculationId == -1){
            throw new SavingDataException("Не удалось сохранить расчёт " + calculation.getCalculationMethodName());
        }
        if(count > 1){
            throw new SavingDataException("Не удалось сохранить расчёт " + calculation.getCalculationMethodName());
        }
        return calculationId;
    }

    public List<Calculatelable> getCalculationListByUser(User user) {
        List<Calculatelable> calculations = new ArrayList<Calculatelable>();
        Connection connection = initConnection();
        String sql = "SELECT calculation_method_name, user_id, id FROM hydrologist_test.calculations WHERE user_id = ?";
        ResultSet resultSet;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getID());
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                String calculationmethodname = resultSet.getString("calculation_method_name");
                String ID = Integer.toString(resultSet.getInt("id"));
                calculations.add(new Calculation(calculationmethodname, user));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        return calculations;
    }

    public User getUserByEmail(String email) throws UserNotFoundException {

        Connection connection = initConnection();
        String sql = "SELECT id, email, password, firstname, secondname, middlename FROM hydrologist_test.users WHERE email = ?";
        ResultSet resultSet;
        User user = null;
        int count = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            resultSet = statement.executeQuery();

             while(resultSet.next()){
                count++;
                int id = resultSet.getInt("id");
                String eMail = resultSet.getString("email");
                String firstName = resultSet.getString("firstname");
                String secondName = resultSet.getString("secondname");
                String middleName = resultSet.getString("middlename");
                String password = resultSet.getString("password");

                user = new User(id, firstName, secondName, middleName, eMail, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(count > 1){
            throw new UserNotFoundException("По email " + email + " найдено более одной записи о пользователе ");
        }

        if(user == null){
            throw new UserNotFoundException("Пользователь с email " + email + " не найден");
        }

        return user;
    }

    public Calculatelable getCalculationByID(int id) throws DataNotFoundException {
        Connection connection = initConnection();
        String sql = "SELECT calculation_method_name, user_id, id FROM hydrologist_test.calculations WHERE id = ?";
        ResultSet resultSet;
        Calculatelable calculation = null;
        int count = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                count++;
                String calculationmethodname = resultSet.getString("calculation_method_name");
                int ID = resultSet.getInt("id");
                int UserID = resultSet.getInt("user_id");
                User user = getUserByID(UserID);
                calculation = new Calculation(ID, calculationmethodname, user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            log.error(e.getStackTrace());
            throw new DataNotFoundException("По ID " + id + " не найдено ни одного связанного пользователя ");
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(count > 1){
            throw new DataNotFoundException("По ID " + id + " найдено более одного расчёта ");
        }
        if(calculation == null){
            throw new DataNotFoundException("Пользователь с id " + id + " не найден");
        }
        return calculation;
    }

    public User getUserByID(int id) throws UserNotFoundException {
        Connection connection = initConnection();
        String sql = "SELECT id, email, password, firstname, secondname, middlename FROM hydrologist_test.users WHERE id = ?";
        ResultSet resultSet;
        User user = null;
        int count = 0;

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                count++;
                int ID = resultSet.getInt("id");
                String eMail = resultSet.getString("email");
                String firstName = resultSet.getString("firstname");
                String secondName = resultSet.getString("secondname");
                String middleName = resultSet.getString("middlename");

                user = new User(ID, firstName, secondName, middleName, eMail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(count > 1){
            throw new UserNotFoundException("По id " + id + " найдено более одной записи о пользователе ");
        }
        if(user == null){
            throw new UserNotFoundException("Пользователь с id " + id + " не найден");
        }
        return user;
    }

    public ArrayList<CalculationMethod> getExistingCalculationsList() throws DataNotFoundException {

        Connection connection = initConnection();
        String sql = "SELECT id, calculation_name, normative_document_id FROM hydrologist_test.calculation_types";
        ResultSet resultSet;
        ArrayList<CalculationMethod> calculationList = new ArrayList<CalculationMethod>();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                int ID = resultSet.getInt("id");
                String calculationName = resultSet.getString("calculation_name");
                int normative_document_id = resultSet.getInt("normative_document_id");

                calculationList.add(new CalculationMethod(calculationName, Integer.toString(normative_document_id), ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(calculationList == null){
            throw new DataNotFoundException("Не найдено ни одного расчёта");
        }

        return calculationList;
    }


    public Boolean registerUser(String email, String password, String confirmPassword) throws DuplicateUsersException {

        if(email != null){

            Connection connection = initConnection();
            String sql = "insert into hydrologist_test.users(email, password) values (?,?)";
            String insertRoleSql = "insert into hydrologist_test.users_roles(user_id, role_id) values (?,?)";

            try {
                User existingUser = getUserByEmail(email);
                throw new DuplicateUsersException();
            } catch (UserNotFoundException e) {
                if(password.equals(confirmPassword) ){

                    try {
                        PreparedStatement statement = connection.prepareStatement(sql);
                        statement.setString(1, email);
                        statement.setString(2, CustomHash.getHash(password));

                        int i= statement.executeUpdate();

                        if (i!=0)  {

                            User newUser = getUserByEmail(email);
                            int userId = newUser.getID();

                            PreparedStatement roleStatement = connection.prepareStatement(insertRoleSql);

                            roleStatement.setInt(1,userId);
                            roleStatement.setInt(2, 1);

                            int j= roleStatement.executeUpdate();

                            if(j!=0){
                                return true;
                            }

                        }

                    }catch (Exception ex){
                        ex.printStackTrace();
                    }finally {
                        if(connection != null){
                            finalizeConnection(connection);
                        }
                    }

                }
            }
        }

        return false;
    }

    public ArrayList<String> getUserRolesByUser(User user) throws DataNotFoundException{
        Connection connection = initConnection();
        String sql = "SELECT role FROM hydrologist_test.users_roles INNER JOIN hydrologist_test.roles ON (hydrologist_test.users_roles.role_id = hydrologist_test.roles.id) WHERE user_id = ?";
        ResultSet resultSet;
        ArrayList<String> roles = new ArrayList<String>();
        int userId = user.getID();

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            while(resultSet.next()){
                roles.add( resultSet.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

        if(roles == null){
            throw new DataNotFoundException("Не найдено ни одной роли");
        }

        return roles;
    }

    public void changeUserPassword(User user, String password){
        Connection connection = initConnection();
        String sql = "UPDATE  hydrologist_test.users SET password=? WHERE email=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, CustomHash.getHash(password));
            statement.setString(2, user.getEmail());

            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                finalizeConnection(connection);
            }
        }

    }


    Connection initConnection(){
        InitialContext context;
        DataSource dataSource;
        Connection connection = null;

        try {
            context = new InitialContext();
            dataSource = (DataSource) context.lookup(dataBaseConnectionName);
            connection = dataSource.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private void finalizeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQueryCustom(String query, Connection connection){
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
