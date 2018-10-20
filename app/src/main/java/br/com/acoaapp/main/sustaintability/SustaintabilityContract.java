package br.com.acoaapp.main.sustaintability;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;

public interface SustaintabilityContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showHistory(GeneralStatisticsEntity generalStatisticsEntity);
    }

    interface UserActionListener {
        void loadHistory(boolean doRefresh);
    }
}
