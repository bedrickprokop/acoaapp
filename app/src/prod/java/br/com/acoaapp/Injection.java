package br.com.acoaapp;

import br.com.acoaapp.impl.CollaboratorServiceApiImp;
import br.com.acoaapp.impl.ConsumptionHistoryServiceApiImpl;
import br.com.acoaapp.impl.StatisticsServiceApiImp;

public class Injection {

    public static CollaboratorServiceApiImp provideCollaboratorServiceApi() {
        return new CollaboratorServiceApiImp();
    }

    public static StatisticsServiceApiImp provideStatisticsServiceApi() {
        return new StatisticsServiceApiImp();
    }

    public static ConsumptionHistoryServiceApiImpl provideConsumptionHistoryServiceApi(){
        return new ConsumptionHistoryServiceApiImpl();
    }
}
