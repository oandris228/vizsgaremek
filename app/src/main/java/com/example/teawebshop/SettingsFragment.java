package com.example.teawebshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;
import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private TeaCartViewModel viewModel;
    private LinearLayout itemsContainer;
    private TextView tvTotalPrice;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        itemsContainer = view.findViewById(R.id.items_container);
        tvTotalPrice = view.findViewById(R.id.tv_total_price);


        TextView tvSelectedItems = view.findViewById(R.id.tv_selected_items);
        tvSelectedItems.setVisibility(View.GONE);

        viewModel = new ViewModelProvider(requireActivity()).get(TeaCartViewModel.class);

        setupObservers();

        return view;
    }

    private void setupObservers() {
        viewModel.getSelectedTeas().observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> teas) {
                updateCartItems(teas);
            }
        });

        viewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer total) {
                tvTotalPrice.setText(total > 0 ? "Összesen: " + total + " Ft" : "");
            }
        });
    }

    private void updateCartItems(ArrayList<String> teas) {
        itemsContainer.removeAllViews(); // Clear existing views

        if (teas == null || teas.isEmpty()) {
            TextView emptyText = new TextView(getContext());
            emptyText.setText("Nincsenek kiválasztott termékek");
            emptyText.setTextSize(16);
            emptyText.setTextColor(getResources().getColor(android.R.color.black));
            itemsContainer.addView(emptyText);
            return;
        }

        for (int i = 0; i < teas.size(); i++) {
            addCartItem(teas.get(i), i);
        }
    }

    private void addCartItem(String product, final int position) {
        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.cart_item, itemsContainer, false);

        TextView tvProductName = itemView.findViewById(R.id.tv_product_name);
        ImageButton btnRemove = itemView.findViewById(R.id.btn_remove);

        tvProductName.setText(product);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.removeItem(position);
                Toast.makeText(getContext(), "Termék eltávolítva", Toast.LENGTH_SHORT).show();
            }
        });

        itemsContainer.addView(itemView);
    }
}