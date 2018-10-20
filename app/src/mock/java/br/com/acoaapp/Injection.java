package br.com.acoaapp;

import br.com.acoaapp.data.service.impl.FakeCollaboratorServiceApiImpl;
import br.com.acoaapp.data.service.impl.FakeConsumptionHistoryServiceApiImpl;
import br.com.acoaapp.data.service.impl.FakeGeneralStatisticsServiceApiImpl;

public class Injection {

    public static FakeCollaboratorServiceApiImpl provideCollaboratorServiceApi(){
        return new FakeCollaboratorServiceApiImpl();
    }

    public static FakeGeneralStatisticsServiceApiImpl provideStatisticsServiceApi(){
        return new FakeGeneralStatisticsServiceApiImpl();
    }

    public static FakeConsumptionHistoryServiceApiImpl provideConsumptionHistoryServiceApi(){
        return new FakeConsumptionHistoryServiceApiImpl();
    }
}
