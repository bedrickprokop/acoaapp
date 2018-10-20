package br.com.acoaapp.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.acoaapp.R;

public class NavDrawerFragment extends Fragment {

    private Callbacks callbacks;

    public static NavDrawerFragment newInstance() {
        return new NavDrawerFragment();
    }

    interface Callbacks {
        void onNavItemClicked(int navItemId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        callbacks = (Callbacks) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_nav_drawer, container, false);

        TextView item1 = view.findViewById(R.id.item1);
        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onNavItemClicked(1);
            }
        });

        TextView item2 = view.findViewById(R.id.item2);
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onNavItemClicked(2);
            }
        });

        TextView item3 = view.findViewById(R.id.item3);
        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onNavItemClicked(3);
            }
        });

        TextView item4 = view.findViewById(R.id.item4);
        item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onNavItemClicked(4);
            }
        });

        TextView item5 = view.findViewById(R.id.item5);
        item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onNavItemClicked(5);
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }
}