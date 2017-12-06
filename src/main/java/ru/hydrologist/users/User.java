package ru.hydrologist.users;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.hydrologist.dao.CalculationDAO;
import ru.hydrologist.dao.PostgreDAO;
import ru.hydrologist.exceptions.DuplicateUsersException;
import ru.hydrologist.security.CustomHash;
import ru.hydrologist.security.Email;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@ManagedBean
@SessionScoped
public class User implements Serializable{

    private int ID;
    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
    private String password;
    private String confirmingPassword;
    private Organization organization;
    private Logger log = LogManager.getLogger(User.class);
    private String loginLogoutButonName;

    public User(){

    }

    public User(String email){
        this.email = email;
    }

    public User(int ID, String firstName, String secondName, String middleName, String email){
        this.email = email;
        this.ID = ID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public User(int ID, String firstName, String secondName, String middleName, String email, String password){
        this.email = email;
        this.ID = ID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.password = password;
    }

    public User(int ID, String firstName, String secondName, String middleName, String email, Organization organization){
        this.ID = ID;
        this.firstName = firstName;
        this.secondName = secondName;
        this.middleName = middleName;
        this.email = email;
        this.organization = organization;
    }

    public String getFirstName() {
        return firstName;
    }

    public Organization getOrganization() {
        return organization;
    }

    public String getFullName(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(secondName);
        stringBuilder.append(" ");
        stringBuilder.append(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(middleName);

        return stringBuilder.toString();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String login() {

        String hash = CustomHash.getHash(password);

        try {
            ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).login(email, password);
            return "calculations";
        } catch (ServletException e) {
            e.printStackTrace();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("#{messages.authorization_failed_error}");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("login_form", message);
        }
        return "calculations";
    }

    public String logout(){
        String result = "exit";

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try{
            request.logout();
        }catch (ServletException e){
            log.error(e);
        }

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        return result;
    }

    public String loginLogout(){
        String result;

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if( request.getUserPrincipal() == null ){
            result = "authorization";
        }else{
            result = logout();
        }

        return result;
    }

    public String getLoginLogoutButonName(){
        String result;

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        if( request.getUserPrincipal() == null ){
            result = "Войти";
        }else{
            result = "Выйти";
        }

        return result;
    }

    public String register(){

        try {
            CalculationDAO dao = new PostgreDAO();
            dao.registerUser(email, password, confirmingPassword);
        } catch (DuplicateUsersException e) {
            e.printStackTrace();

            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("#{messages.duplicate_email_error}");
            FacesMessage message1 = new FacesMessage("#{messages.duplicate_email_error}", "#{messages.duplicate_email_error}");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("registration_form:email", message1);
        } catch (Exception ex){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("#{messages.registration_error}");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("registration_form:email", message);
        }

        return "calculations";
    }

    public String restorePassword(){
        CalculationDAO dao = new PostgreDAO();

        String newPassword = Email.generateRandomString(8);

        if(this.email == null){
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("#{messages.registration_error}");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("restore_form:email", message);
        }

        dao.changeUserPassword(this, newPassword);

        String text = "Для Вашего аккаунта было запрошено изменение пароля. Ваш новый пароль: "
                + "\n" + "\n" + newPassword + "\n" + "\n"
                + "Если Вы не запрашивали изменения пароля, пожалуйста, сообщите об этом в службу поддержки Hydrologist."
                + "\n" + "\n"
                + "Это письмо сгенерировано автоматически. Пожалуйста, не отвечайте на него.";

        String subject = "Hydrologist. Восстановление пароля";

        if(text != null){
            Email.sendEmail(text, subject, email);
        }

        return "login";
    }

    public String getConfirmingPassword() {
        return confirmingPassword;
    }

    public void setConfirmingPassword(String confirmingPassword) {
        this.confirmingPassword = confirmingPassword;
    }



}
