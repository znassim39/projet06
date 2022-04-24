package com.salimahafirassou.paymybuddy.dto;

public class TransactionTableDto {

    private String buddy_name;
    private String description;
    private Double amount;
    
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
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
