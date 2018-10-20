package br.com.acoaapp.data.entity;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeneralStatisticsEntity implements Serializable {

    //média do consumo por hora ao dia
    private Double averageConsumptionPerHour = 0d;
    //soma do consumo diário
    private Double totalConsumptionPerDay = 0d;
    //média do consumo diário ao mes
    private Double averageConsumptionPerDay = 0d;
    //soma do consumo mensal
    private Double totalConsumptionPerMonth = 0d;
    //média do consumo mensal do ano
    private Double averageConsumptionPerMonth = 0d;
    //soma do consumo anual
    private Double totalConsumptionPerYear = 0d;
    //quantidade total de usuários da mesma conta
    private Integer totalAccountUsers = 0;

    private List<DateFlowEntity> dailyConsumptionList = new ArrayList<>();
    private List<DateFlowEntity> monthlyConsumptionList = new ArrayList<>();

    public Double getAverageConsumptionPerHour() {
        return averageConsumptionPerHour;
    }

    public void setAverageConsumptionPerHour(Double averageConsumptionPerHour) {
        this.averageConsumptionPerHour = averageConsumptionPerHour;
    }

    public Double getTotalConsumptionPerDay() {
        return totalConsumptionPerDay;
    }

    public void setTotalConsumptionPerDay(Double totalConsumptionPerDay) {
        this.totalConsumptionPerDay = totalConsumptionPerDay;
    }

    public Double getAverageConsumptionPerDay() {
        return averageConsumptionPerDay;
    }

    public void setAverageConsumptionPerDay(Double averageConsumptionPerDay) {
        this.averageConsumptionPerDay = averageConsumptionPerDay;
    }

    public Double getTotalConsumptionPerMonth() {
        return totalConsumptionPerMonth;
    }

    public void setTotalConsumptionPerMonth(Double totalConsumptionPerMonth) {
        this.totalConsumptionPerMonth = totalConsumptionPerMonth;
    }

    public Double getAverageConsumptionPerMonth() {
        return averageConsumptionPerMonth;
    }

    public void setAverageConsumptionPerMonth(Double averageConsumptionPerMonth) {
        this.averageConsumptionPerMonth = averageConsumptionPerMonth;
    }

    public Double getTotalConsumptionPerYear() {
        return totalConsumptionPerYear;
    }

    public void setTotalConsumptionPerYear(Double totalConsumptionPerYear) {
        this.totalConsumptionPerYear = totalConsumptionPerYear;
    }

    public List<DateFlowEntity> getDailyConsumptionList() {
        return dailyConsumptionList;
    }

    public void setDailyConsumptionList(List<DateFlowEntity> dailyConsumptionList) {
        this.dailyConsumptionList = dailyConsumptionList;
    }

    public List<DateFlowEntity> getMonthlyConsumptionList() {
        return monthlyConsumptionList;
    }

    public void setMonthlyConsumptionList(List<DateFlowEntity> monthlyConsumptionList) {
        this.monthlyConsumptionList = monthlyConsumptionList;
    }

    public Integer getTotalAccountUsers() {
        return totalAccountUsers;
    }

    public void setTotalAccountUsers(Integer totalAccountUsers) {
        this.totalAccountUsers = totalAccountUsers;
    }
}