package com.example.fragments.ui.basket;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragments.ui.adapters.BasketAdapter;
import com.example.fragments.DBHelper;
import com.example.fragments.MainActivity;
import com.example.fragments.PizzaItem;
import com.example.fragments.R;
import com.example.fragments.databinding.FragmentBasketBinding;

public class BasketFragment extends Fragment {

    private FragmentBasketBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBasketBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;

    }


    @Override
    public void onStart() {
        super.onStart();

        PizzaItem[] pizzas = new PizzaItem[MainActivity.cart.size()];
        for (int i = 0; i < pizzas.length; i++) {
            pizzas[i] = MainActivity.cart.get(i);
        }

        BasketAdapter basketAdapter = new BasketAdapter(getActivity().getApplicationContext(), pizzas);
        ListView listView = (ListView) getActivity().findViewById(R.id.basket);
        listView.setAdapter(basketAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i < MainActivity.cart.size()) {
                    MainActivity.cart.remove(i);
                }
            }
        });

        int sum = 0;

        for(int i = 0; i < MainActivity.cart.size(); i++) {
            PizzaItem pizzaItem = MainActivity.cart.get(i);
            sum += pizzaItem.price;
        }

        Button button = (Button) getActivity().findViewById(R.id.buttonCreateOrder);
        button.setText("Сделать заказ, " + sum + " ₽");
        int finalSum = sum;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainActivity.cart.size() == 0) {
                    //если корзина пуста выводим тост
                    Toast toast = Toast.makeText(getContext(), "Корзина пуста", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    //если в корзине что-то есть:
                    //выводим тост
                    Toast toast = Toast.makeText(getContext(), "Заказ сделан", Toast.LENGTH_SHORT);
                    toast.show();

                    //переносим названия в одну строку
                    String names = MainActivity.cart.get(0).name;
                    for(int i = 1; i < MainActivity.cart.size(); i++) {
                        names = names + ",  "+ MainActivity.cart.get(i).name;
                    }

                    //очищаем корзину
                    MainActivity.cart.clear();

                    SQLiteDatabase database = MainActivity.dbHelper.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();

                    //переносим все значения в базу
                    contentValues.put(DBHelper.KEY_NAMES, names);
                    contentValues.put(DBHelper.KEY_PRICE, finalSum);
                    database.insert(DBHelper.TABLE_ORDERS, null, contentValues);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}