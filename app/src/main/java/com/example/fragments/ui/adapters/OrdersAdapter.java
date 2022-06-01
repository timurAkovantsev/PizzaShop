package com.example.fragments.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.fragments.OrderItem;
import com.example.fragments.R;

public class OrdersAdapter extends ArrayAdapter<OrderItem> {

    public OrdersAdapter(Context context, OrderItem[] arr) {
        super(context, R.layout.order_adapter, arr);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final OrderItem orderItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_adapter, null);
        }

        ((TextView) convertView.findViewById(R.id.numberOrder)).setText(String.valueOf("Номер заказа: " + orderItem.id));
        ((TextView) convertView.findViewById(R.id.namesPizzaInOrders)).setText(String.valueOf("Ваш заказ: " + orderItem.names));
        ((TextView) convertView.findViewById(R.id.pricePizzaInOrders)).setText(String.valueOf("Сумма заказа: " + orderItem.price + " ₽"));

        return convertView;
    }

}
