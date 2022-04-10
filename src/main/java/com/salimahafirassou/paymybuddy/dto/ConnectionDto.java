package com.salimahafirassou.paymybuddy.dto;

import java.io.Serializable;

public class ConnectionDto implements Serializable {

    private String buddy_email;

    public String getBuddy_email() {
        return buddy_email;
    }

    public void setBuddy_email(String buddy_email) {
        this.buddy_email = buddy_email;
    }
    
}
