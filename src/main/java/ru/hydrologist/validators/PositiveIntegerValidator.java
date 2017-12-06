package ru.hydrologist.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator("ru.hydrologist.validators.PositiveIntegerValidator")
public class PositiveIntegerValidator implements Validator {
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("languageSupport.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            String newValue = value.toString();
            Integer i = Integer.parseInt(newValue);

            if(i<0){
                throw new IllegalArgumentException(bundle.getString("negative_nember_error"));
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
