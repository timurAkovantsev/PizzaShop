package com.example.fragments.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.fragments.MainActivity;
import com.example.fragments.PizzaAdapter;
import com.example.fragments.R;
import com.example.fragments.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment {

    private FragmentMenuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel homeViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onStart() {
        super.onStart();
        PizzaAdapter adapter = new PizzaAdapter(getActivity().getApplicationContext(), MainActivity.makePizza());
        ListView lv =(ListView)getActivity().findViewById(R.id.listView);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                if(MainActivity.fragment != null) {
                    fragmentTransaction.remove(MainActivity.fragment);
                }
                fragmentTransaction.replace(R.id.select_fragment, MainActivity.fragment);
                fragmentTransaction.commit();
                MainActivity.num = position;
            }
        });
    }
}