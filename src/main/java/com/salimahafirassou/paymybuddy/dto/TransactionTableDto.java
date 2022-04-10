package com.salimahafirassou.paymybuddy.dto;

public class TransactionTableDto {

    private String buddy_name;
    private String description;
    private Float amount;
    
    public String getBuddy_name() {
        return buddy_name;
    }
    public void setBuddy_name(String buddy_name) {
        this.buddy_name = buddy_name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
