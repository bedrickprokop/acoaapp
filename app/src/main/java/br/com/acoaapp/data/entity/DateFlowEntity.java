package br.com.acoaapp.data.entity;

import java.io.Serializable;
import java.util.Date;

public class DateFlowEntity implements Serializable{

    private Date date;
    private Double flowRate;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(Double flowRate) {
        this.flowRate = flowRate;
    }
}
