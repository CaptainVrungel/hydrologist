package ru.hydrologist.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator("ru.hydrologist.validators.PasswordValidator")
public class PasswordValidator  implements Validator {

    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("languageSupport.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            String newValue = value.toString();

            if(newValue.length() < 1){
                throw new IllegalArgumentException(bundle.getString("password_length_error"));
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
