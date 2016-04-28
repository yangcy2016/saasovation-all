package com.saasovation.identityaccess.domain.model.user;

public class Name {
    private String fristName;
    private String lastName;

    public Name(String fristName, String lastName) {
        this.fristName = fristName;
        this.lastName = lastName;
    }

    public String getFristName() {
        return fristName;
    }

    public String getLastName() {
        return lastName;
    }
}
