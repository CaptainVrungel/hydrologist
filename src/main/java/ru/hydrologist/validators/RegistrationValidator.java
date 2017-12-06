package ru.hydrologist.validators;

import ru.hydrologist.dao.CalculationDAO;
import ru.hydrologist.dao.PostgreDAO;
import ru.hydrologist.exceptions.UserNotFoundException;
import ru.hydrologist.users.User;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

@FacesValidator("ru.hydrologist.validators.RegistrationValidator")
public class RegistrationValidator implements Validator {
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        ResourceBundle bundle = ResourceBundle.getBundle("languageSupport.messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());

        try{
            String newValue = value.toString();

            CalculationDAO dao = new PostgreDAO();

            try {
                User user = dao.getUserByEmail(newValue);
                throw new IllegalArgumentException(bundle.getString("duplicate_email_error"));
            } catch (UserNotFoundException e) {

            }

        }catch (IllegalArgumentException e){
            FacesMessage message = new FacesMessage(e.getMessage());
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}
