package com.example.fragments.ui.orders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrdersViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public OrdersViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}