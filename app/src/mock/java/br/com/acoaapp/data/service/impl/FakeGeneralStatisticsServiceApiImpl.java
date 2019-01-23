package br.com.acoaapp.data.service.impl;

import android.os.Handler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.acoaapp.data.entity.DateFlowEntity;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;
import br.com.acoaapp.data.service.GeneralStatisticsServiceApi;
import br.com.acoaapp.utils.DateTimeUtils;

public class FakeGeneralStatisticsServiceApiImpl implements GeneralStatisticsServiceApi {

    @Override
    public void loadStatsConsumption(final GeneralStatisticsCallback<GeneralStatisticsEntity> callback) {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralStatisticsEntity generalStatisticsEntity = new GeneralStatisticsEntity();
                generalStatisticsEntity.setTotalAccountUsers(3);
                generalStatisticsEntity.setAverageConsumptionPerHour(0.01);
                generalStatisticsEntity.setAverageConsumptionPerDay(450.00);
                generalStatisticsEntity.setAverageConsumptionPerMonth(13500.00);
                generalStatisticsEntity.setTotalConsumptionPerDay(422.00);
                generalStatisticsEntity.setTotalConsumptionPerMonth(14000.00);
                generalStatisticsEntity.setTotalConsumptionPerYear(162000.00);
                generalStatisticsEntity.setDailyConsumptionList(getDateFlowEntityList(true));
                callback.onLoaded(generalStatisticsEntity);
            }
        }, 2000);
    }

    private List<DateFlowEntity> getDateFlowEntityList(Boolean reverseOrder) {
        int totalDays = 365;
        List<DateFlowEntity> dateFlowEntityList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 1);

        for (int i = 1; i < totalDays; i++) {
            String day;
            if (calendar.get(Calendar.DAY_OF_MONTH) > 9) {
                day = "" + calendar.get(Calendar.DAY_OF_MONTH);
            } else {
                day = "0" + calendar.get(Calendar.DAY_OF_MONTH);
            }

            String month;
            if (calendar.get(Calendar.MONTH) + 1 > 9) {
                month = "" + (calendar.get(Calendar.MONTH) + 1);
            } else {
                month = "0" + (calendar.get(Calendar.MONTH) + 1);
            }
            String year = "" + calendar.get(Calendar.YEAR);
            String strDate = day + "/" + month + "/" + year;

            Date date;
            try {
                date = DateTimeUtils.toDate(strDate, "dd/MM/yyyy");
            } catch (ParseException e) {
                date = null;
            }

            Double generatedValue;
            if (!reverseOrder) {
                if (i % 20 == 0) generatedValue = Math.random() * 10000;
                else generatedValue = i / 35.05;
            } else {
                if (i % 20 == 0) generatedValue = i / 35.05;
                else generatedValue = Math.random() * 10000;
            }
            if (generatedValue > 10000 || generatedValue < 0) generatedValue = Math.random() * 50;

            DateFlowEntity dateFlowEntity = new DateFlowEntity();
            dateFlowEntity.setDate(date);
            dateFlowEntity.setFlowRate(generatedValue);
            dateFlowEntityList.add(dateFlowEntity);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateFlowEntityList;
    }

    @Override
    public void loadConsumptionPerDay(String date, final GeneralStatisticsCallback<GeneralStatisticsEntity> callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralStatisticsEntity generalStatisticsEntity = new GeneralStatisticsEntity();
                callback.onLoaded(generalStatisticsEntity);
            }
        }, 2000);
    }

    @Override
    public void loadConsumptionPerMonth(String date, final GeneralStatisticsCallback<GeneralStatisticsEntity> callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralStatisticsEntity generalStatisticsEntity = new GeneralStatisticsEntity();
                callback.onLoaded(generalStatisticsEntity);
            }
        }, 2000);
    }

    @Override
    public void loadConsumptionPerMonthInYear(Integer year, final GeneralStatisticsCallback<GeneralStatisticsEntity> callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralStatisticsEntity generalStatisticsEntity = new GeneralStatisticsEntity();
                callback.onLoaded(generalStatisticsEntity);
            }
        }, 2000);
    }

    @Override
    public void loadConsumptionPerYear(Integer year, final GeneralStatisticsCallback<GeneralStatisticsEntity> callback) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                GeneralStatisticsEntity generalStatisticsEntity = new GeneralStatisticsEntity();
                callback.onLoaded(generalStatisticsEntity);
            }
        }, 2000);
    }
}
