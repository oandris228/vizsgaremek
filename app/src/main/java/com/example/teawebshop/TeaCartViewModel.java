package com.example.teawebshop;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

public class TeaCartViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<String>> selectedTeas = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Integer> totalPrice = new MutableLiveData<>(0);

    public void addTea(String teaName, int price) {
        ArrayList<String> currentTeas = selectedTeas.getValue();
        if (currentTeas != null) {
            currentTeas.add(teaName + " - " + price + " Ft");
            selectedTeas.setValue(currentTeas);
            totalPrice.setValue(totalPrice.getValue() + price);
        }
    }

    public MutableLiveData<ArrayList<String>> getSelectedTeas() {
        return selectedTeas;
    }

    public MutableLiveData<Integer> getTotalPrice() {
        return totalPrice;
    }

    public void removeItem(int position) {
        if (selectedTeas.getValue() != null && position >= 0 && position < selectedTeas.getValue().size()) {
            ArrayList<String> newList = new ArrayList<>(selectedTeas.getValue());
            newList.remove(position);
            selectedTeas.setValue(newList);
            calculateTotal();
        }
    }

    private void calculateTotal() {
        int total = 0;
        if (selectedTeas.getValue() != null) {
            for (String item : selectedTeas.getValue()) {

                try {
                    String priceStr = item.split("-")[1].replace("Ft", "").trim();
                    total += Integer.parseInt(priceStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        totalPrice.setValue(total);
    }

}