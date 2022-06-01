package com.example.fragments.ui.orders;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragments.DBHelper;
import com.example.fragments.MainActivity;
import com.example.fragments.OrderItem;
import com.example.fragments.ui.adapters.OrdersAdapter;
import com.example.fragments.R;
import com.example.fragments.databinding.FragmentOrdersBinding;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {

    private FragmentOrdersBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        OrdersViewModel notificationsViewModel =
                new ViewModelProvider(this).get(OrdersViewModel.class);

        binding = FragmentOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();

        SQLiteDatabase database = MainActivity.dbHelper.getWritableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_ORDERS, null, null, null, null, null, null);

        OrderItem[] orderItems;
        ArrayList<OrderItem> arrayList = new ArrayList<OrderItem>();

        if(cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int namesIndex = cursor.getColumnIndex(DBHelper.KEY_NAMES);
            int priceIndex = cursor.getColumnIndex(DBHelper.KEY_PRICE);
            do {
                arrayList.add( new OrderItem(cursor.getString(namesIndex), cursor.getInt(priceIndex), cursor.getInt(idIndex)));
            } while (cursor.moveToNext());

            orderItems = new OrderItem[arrayList.size()];
            for(int i = 0; i < orderItems.length; i++) {
                orderItems[i] = arrayList.get(i);
            }

        } else {
            orderItems = new OrderItem[0];
        }

        cursor.close();
        MainActivity.dbHelper.close();

        OrdersAdapter ordersAdapter = new OrdersAdapter(getActivity().getApplicationContext(), orderItems);
        ListView listView = (ListView) getActivity().findViewById(R.id.orders);
        listView.setAdapter(ordersAdapter);
    }
}