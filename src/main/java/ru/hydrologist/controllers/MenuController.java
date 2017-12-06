package ru.hydrologist.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MenuController {

    public String calculations(){
        return "calculations";
    }

    public String contacts(){
        return "contacts";
    }

    public String about(){
        return "about";
    }

    public String documents() { return "documents"; }

}
