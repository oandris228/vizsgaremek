package com.example.teawebshop;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TeaCartViewModel viewModel;
    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(TeaCartViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Gombok inicializálása
        Button btnGreenTea = view.findViewById(R.id.btn_green_tea);
        Button btnBlackTea = view.findViewById(R.id.btn_black_tea);
        Button btnHerbalTea = view.findViewById(R.id.btn_herbal_tea);
        Button btnFruitTea = view.findViewById(R.id.btn_fruit_tea);
        Button btnWhiteTea = view.findViewById(R.id.btn_white_tea);


        btnGreenTea.setOnClickListener(v -> viewModel.addTea("Zöld tea", 890));
        btnBlackTea.setOnClickListener(v -> viewModel.addTea("Fekete tea", 790));
        btnHerbalTea.setOnClickListener(v -> viewModel.addTea("Gyógytea", 990));
        btnFruitTea.setOnClickListener(v -> viewModel.addTea("Gyümölcstea", 1090));
        btnWhiteTea.setOnClickListener(v -> viewModel.addTea("Fehér tea", 1290));

        return view;
    }
}