package ru.hydrologist.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("ru.hydrologist.validators.LoginValidator")
public class LoginValidator implements Validator{
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("languageSupport.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            String newValue = value.toString();

            if(newValue.length() < 3){
                throw new IllegalArgumentException(bundle.getString("login_length_error"));
            }

            if(!checkEmailByRegExp(newValue)){
                throw new IllegalArgumentException(bundle.getString("invalid_email_error"));
            }

            if(getUsedNames().contains(newValue)){
                throw new IllegalArgumentException(bundle.getString("used_name"));
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    private ArrayList<String> getUsedNames(){
        ArrayList<String> usedNames = new ArrayList<String>();
        //Тут надо бы делать запрос в БД
        usedNames.add("username");
        usedNames.add("login");
        return usedNames;
    }

    public boolean checkEmailByRegExp(String email){
        Pattern p = Pattern.compile("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$");
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
