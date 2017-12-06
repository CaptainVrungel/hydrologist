package ru.hydrologist.guiElements;

import ru.hydrologist.ranges.ProbabilityDistribution;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=ProbabilityDistribution.class)
public class DistributionConverter implements Converter {

        public String getAsString(FacesContext context, UIComponent component, Object value) {
            return (value instanceof ProbabilityDistribution) ? ((ProbabilityDistribution) value).getDistributionName() : null;
        }

        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if (value == null) {
                return null;
            }

            formBean bean = context.getApplication().evaluateExpressionGet(context, "#{formBean}", formBean.class);

            for (ProbabilityDistribution distribution : bean.getDistributionTypes()) {
                if (distribution.getDistributionName().equals(value)) {
                    return distribution;
                }
            }

            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Country", value)));
        }

}
