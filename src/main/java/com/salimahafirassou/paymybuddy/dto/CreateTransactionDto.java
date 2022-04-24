package com.salimahafirassou.paymybuddy.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateTransactionDto implements Serializable {

    @NotNull
    @NotEmpty(message = "please choose a connection")
    private String credited_email;

    @NotNull
    @NotEmpty(message = "the amount is requireds")
    private Double amount;
    
    private String description;

    public String getCredited_email() {
        return credited_email;
    }
    public void setCredited_email(String credited_email) {
        this.credited_email = credited_email;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
