package ru.hydrologist.jaas;

import java.security.Principal;

public class UserPrincipal implements Principal{

    private String name;

    public UserPrincipal(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
