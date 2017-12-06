package ru.hydrologist.jaas;

import ru.hydrologist.dao.PostgreDAO;
import ru.hydrologist.exceptions.DataNotFoundException;
import ru.hydrologist.exceptions.UserNotFoundException;
import ru.hydrologist.security.CustomHash; 
import ru.hydrologist.users.User;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginModuleCustom implements javax.security.auth.spi.LoginModule{

    private CallbackHandler handler;
    private Subject subject;
    private UserPrincipal userPrincipal;
    private RolePrincipal rolePrincipal;
    private String login;
    private List<String> userGroups;

    public void initialize(Subject subject,
                           CallbackHandler callbackHandler,
                           Map<String, ?> sharedState,
                           Map<String, ?> options) {

        System.out.println("Login Module Initializes");

        handler = callbackHandler;
        this.subject = subject;
    }

    public boolean login() throws LoginException {

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("login");
        callbacks[1] = new PasswordCallback("password", true);

        try {
            handler.handle(callbacks);
            String name = ((NameCallback) callbacks[0]).getName();
            String password = String.valueOf(((PasswordCallback) callbacks[1])
                    .getPassword());

            //Проавтотестить бы это (авторизацию пользователя и весь этот класс)
            PostgreDAO dao = new PostgreDAO();
            User user = dao.getUserByEmail(name);

            if (    name != null &&
                    name.equals(user.getEmail()) &&
                    password != null &&
                    password.equals(user.getPassword())) {

                login = name;
                //Получаем список ролей, доступных для пользователя
                userGroups = dao.getUserRolesByUser(user);

                return true;
            }

            // If credentials are NOT OK we throw a LoginException
            throw new LoginException("Authentication failed");

        } catch (IOException e) {
            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            throw new LoginException(e.getMessage());
        } catch (UserNotFoundException e) {
            throw new LoginException(e.getMessage());
        } catch (DataNotFoundException e) {
            throw new LoginException(e.getMessage());
        }

    }

    public boolean commit() throws LoginException {
        userPrincipal = new UserPrincipal(login);
        subject.getPrincipals().add(userPrincipal);

        if (userGroups != null && userGroups.size() > 0) {
            for (String groupName : userGroups) {
                rolePrincipal = new RolePrincipal(groupName);
                subject.getPrincipals().add(rolePrincipal);
            }
        }

        return true;
    }

    public boolean abort() throws LoginException {
        return false;
    }

    public boolean logout() throws LoginException {
        subject.getPrincipals().remove(userPrincipal);
        subject.getPrincipals().remove(rolePrincipal);
        return true;
    }


}
