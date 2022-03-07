package com.salimahafirassou.paymybuddy.dto;

public class TransactionToBuddyDTO {

    private Long idCredited; 
    private Long idDebited; 
    private Float Solt;
    public Long getIdCredited() {
        return idCredited;
    }
    public void setIdCredited(Long idCredited) {
        this.idCredited = idCredited;
    }
    public Long getIdDebited() {
        return idDebited;
    }
    public void setIdDebited(Long idDebited) {
        this.idDebited = idDebited;
    }
    public Float getSolt() {
        return Solt;
    }
    public void setSolt(Float solt) {
        Solt = solt;
    }
    
    
    
}
