package ru.hydrologist.locales;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class LocaleChanger implements Serializable{
    //private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
    }

    public LocaleChanger(){

    }

    public void changeLocale(String localeCode){

        if(localeCode.equals("ru_RU")){
            locale = new Locale("ru_RU", "RU");
        }else if(localeCode.equals("en")){
            locale = new Locale("en", "US");
        } else{
            locale = new Locale("ru_RU", "RU");
        }

        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

    }

    public Locale getLocale(){
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
