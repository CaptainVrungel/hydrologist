package ru.hydrologist.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator("ru.hydrologist.validators.CalculationValueValidator")
public class CalculationValueValidator implements Validator {
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("languageSupport.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            String newValue = value.toString();
            newValue = newValue.replace(",", ".");
            String[] parts = newValue.split(" ");

            for(String s : parts){
                try{
                    Double.parseDouble(s);
                }catch (Exception e){
                    throw new IllegalArgumentException(bundle.getString("calculation_number_validation_error"));
                }
            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
