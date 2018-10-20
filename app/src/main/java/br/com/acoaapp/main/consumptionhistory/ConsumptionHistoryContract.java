package br.com.acoaapp.main.consumptionhistory;

import br.com.acoaapp.data.entity.ConsumptionHistoryEntity;

public interface ConsumptionHistoryContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showHistory(ConsumptionHistoryEntity consumptionHistoryEntity);
    }

    interface UserActionListener {
        void loadHistory(boolean doRefresh);
    }
}
