package com.salimahafirassou.paymybuddy.dto;

import java.io.Serializable;
import java.util.List;

import com.salimahafirassou.paymybuddy.domain.UserEntity;

public class HomeDto implements Serializable {

    private List<TransactionTableDto> transactions;
    private List<UserEntity> connections;

    public List<TransactionTableDto> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionTableDto> transactions) {
        this.transactions = transactions;
    }
    public List<UserEntity> getConnections() {
        return connections;
    }
    public void setConnections(List<UserEntity> connections) {
        this.connections = connections;
    }

    
    
}
