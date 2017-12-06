package ru.hydrologist;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name="HelloBean")
@SessionScoped
public class HelloBean implements Serializable {

    public HelloBean(){

    }

    public void check(){

    }

    private static final long serialVersionUID = 1L;

    private String name = "Vasya";

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}


