package br.com.acoaapp.main.sustaintability;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.acoaapp.Injection;
import br.com.acoaapp.R;
import br.com.acoaapp.data.entity.GeneralStatisticsEntity;

public class SustaintabilityFragment extends Fragment implements SustaintabilityContract.View {

    private SustaintabilityContract.UserActionListener mActionListener;
    private GeneralStatisticsEntity mGeneralStatisticsEntity;

    public static SustaintabilityFragment newInstance() {
        return new SustaintabilityFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_sustaintability, container, false);
        mActionListener = new SustaintabilityPresenter(Injection.provideStatisticsServiceApi(), this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupActionBar();
        setupContentView(view);
        loadData();
    }

    private void setupActionBar() {
        //TODO change action bar names
    }

    private void setupContentView(View view) {
        //bcHistory = view.findViewById(R.id.bc_history);
    }

    private void loadData() {
        mActionListener.loadHistory(true);
    }

    @Override
    public void setProgressIndicator(boolean isActive) {

    }

    @Override
    public void showHistory(GeneralStatisticsEntity generalStatisticsEntity) {

    }
}
