package br.com.acoaapp.main.myaccount;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.acoaapp.R;

public class MyAccountFragment extends Fragment {

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_my_account, container, false);
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
    }

    private void loadData() {
    }
}
