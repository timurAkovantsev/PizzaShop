package com.example.fragments;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SelectFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public SelectFragment() {
        // Required empty public constructor
    }

    public static SelectFragment newInstance(String param1, String param2) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_select, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void onStart() {
        super.onStart();
        TextView textName = (TextView) getActivity().findViewById(R.id.pizzaNameFragment);
        textName.setText(MainActivity.nameArray[MainActivity.num]);

        ImageView imageView = (ImageView) getActivity().findViewById(R.id.pizzaImageFragment);
        Resources resources = getResources();
        Drawable drawable = resources.getDrawable(MainActivity.imageArray[MainActivity.num]);
        imageView.setImageDrawable(drawable);

        TextView textDescription = (TextView) getActivity().findViewById(R.id.pizzaDescriptionFragment);
        textDescription.setText(MainActivity.descriptionArray[MainActivity.num]);

        RadioGroup radioGroup = (RadioGroup) getActivity().findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton40:
                        MainActivity.type = 1;
                        break;
                    case R.id.radioButton30:
                        MainActivity.type = 2;
                        break;
                    case R.id.radioButton1:
                        MainActivity.type = 3;
                        break;
                }

            }
        });

        Button button = (Button)getActivity().findViewById(R.id.buttonAddInBasket);
        String string = new String("В корзину");
        button.setText(string);

        RadioButton radioButton1 = (RadioButton) getActivity().findViewById(R.id.radioButton1);
        RadioButton radioButton30 = (RadioButton) getActivity().findViewById(R.id.radioButton30);
        RadioButton radioButton40 = (RadioButton) getActivity().findViewById(R.id.radioButton40);
        radioButton1.setText("1 кусок - " + MainActivity.minPriceArray[MainActivity.num] + " ₽");
        radioButton30.setText("30 см - " + MainActivity.medPriceArray[MainActivity.num] + " ₽");
        radioButton40.setText("40 см - " + MainActivity.maxPriceArray[MainActivity.num] + " ₽");
    }
}


// switch (MainActivity.type) {
//         case 1:
//         string = "В корзину, " + MainActivity.maxPriceArray[MainActivity.num] + " ";
//         break;
//         case 2:
//         string = "В корзину, " + MainActivity.medPriceArray[MainActivity.num] + " ₽";
//         break;
//         case 3:
//         string = "В корзину, " +  + " ₽";
//         break;
//default:
//        string = "В корзину";
//        }