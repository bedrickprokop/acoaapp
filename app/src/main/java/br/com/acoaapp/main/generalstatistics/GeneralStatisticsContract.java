package br.com.acoaapp.main.generalstatistics;

import br.com.acoaapp.data.entity.GeneralStatisticsEntity;

public interface GeneralStatisticsContract {

    interface View {
        void setProgressIndicator(boolean isActive);

        void showStatistics(GeneralStatisticsEntity generalStatisticsEntity);
    }

    interface UserActionListener {
        void loadStatistics(boolean doRefresh);
    }
}
