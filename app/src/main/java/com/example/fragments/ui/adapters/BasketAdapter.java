package com.example.fragments.ui.adapters;

import android.content.Context;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.fragments.PizzaItem;
import com.example.fragments.R;

import java.util.ArrayList;

public class BasketAdapter extends ArrayAdapter<PizzaItem> {

    public BasketAdapter(Context context, PizzaItem[] arr) {
        super(context, R.layout.basket_adapter, arr);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final PizzaItem pizzaItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basket_adapter, null);
        }

        Drawable drawable = AppCompatResources.getDrawable(getContext(), R.drawable.trash);
//        ((ImageView) convertView.findViewById(R.id.imageView)).setImageDrawable(drawable);
        ((TextView) convertView.findViewById(R.id.namePizzaInBasket)).setText(String.valueOf(pizzaItem.name));
        ((TextView) convertView.findViewById(R.id.priceInBasket)).setText(String.valueOf(pizzaItem.price + " ₽"));
        switch (pizzaItem.type) {
            case 1:
                ((TextView) convertView.findViewById(R.id.typeInBasket)).setText(String.valueOf("Размер: 40 см"));
                break;
            case 2:
                ((TextView) convertView.findViewById(R.id.typeInBasket)).setText(String.valueOf("Размер: 30 см"));
                break;
            case 3:
                ((TextView) convertView.findViewById(R.id.typeInBasket)).setText(String.valueOf("Размер: 1 кусочек"));
                break;
        }

        return convertView;
    }

}
