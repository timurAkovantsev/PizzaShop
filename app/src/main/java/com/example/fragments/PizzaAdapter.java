package com.example.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PizzaAdapter extends ArrayAdapter<Pizza> {


    public PizzaAdapter(Context context, Pizza[] arr) {
        super(context, R.layout.adapter_item, arr);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Pizza pizza = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

        ((TextView) convertView.findViewById(R.id.name)).setText(String.valueOf(pizza.name));
        ((TextView) convertView.findViewById(R.id.price)).setText(String.valueOf(pizza.price));
        ((ImageView) convertView.findViewById(R.id.im_res)).setImageResource(pizza.im_res);

        return convertView;
    }
}
