package com.example.fragments;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.fragments.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private ActivityMainBinding binding;

    public static Pizza[] pizzaList = new Pizza[9];
    public static SelectFragment fragment = new SelectFragment();

    public static ArrayList<PizzaItem> cart = new ArrayList();
    public int identifier = 0;
    public static int type = 0;
    public static int num = 0;

    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_menu,
//        R.id.navigation_basket, R.id.navigation_orders).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(binding.navView, navController);

        dbHelper = new DBHelper(this);

    }

    public void onDeleteFragment(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(fragment);
        fragmentTransaction.commit();
    }

    public void addToBasket(View view) {
        if (type == 0) {
            Toast toast1 = Toast.makeText(MainActivity.this, "Выберите размер пиццы", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            Toast toast = Toast.makeText(MainActivity.this, "Добавлено в корзину", Toast.LENGTH_SHORT);
            toast.show();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            identifier++;
            switch (type) {
                case 1:
                    cart.add(new PizzaItem(nameArray[num], maxPriceArray[num], identifier, type));
                    break;
                case 2:
                    cart.add(new PizzaItem(nameArray[num], medPriceArray[num], identifier, type));
                    break;
                case 3:
                    cart.add(new PizzaItem(nameArray[num], minPriceArray[num], identifier, type));
                    break;
            }
        }
    }

    static String[] nameArray = {"Пеперони", "Гавайская", "Европа", "Маргарита", "Баварская",
            "Капричоза", "Венеция", "Ассорти", "Четыре сыра", "Грибной жюльен", "Альтоно", "Карбонара",
            "Песто", "Аррива"};
    static int[] minPriceArray = {55, 75, 85, 75, 75, 75, 85, 85, 85, 75, 75, 59, 59, 55};
    static int[] medPriceArray = {425, 595, 695, 595, 595, 595, 695, 695, 695, 595, 595, 469, 469, 419};
    static int[] maxPriceArray = {655, 875, 945, 875, 875, 875, 945, 945, 945, 875, 875, 799, 795, 749};
    static int[] imageArray = {R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3, R.drawable.pizza4, R.drawable.pizza5,
            R.drawable.pizza6, R.drawable.pizza7, R.drawable.pizza8, R.drawable.pizza9, R.drawable.pizza10, R.drawable.pizza11,
            R.drawable.pizza12, R.drawable.pizza7, R.drawable.pizza9};
    static String[] descriptionArray = {"Состав: пепперони, соус томатный, сыр Моцарелла",
            "Состав: филе куриное, помидоры, ананас консервированный, перец болгарский, соус Маджорио, сыр Моцарелла",
            "Состав: соус томатный, балык, ветчина, сервелат, филе куриное, перец болгарский, соус Маджорио, сыр Моцарелла",
            "Состав: соус томатный, шампиньоны, помидоры, соус Маджорио, сыр Моцарелла",
            "Состав: соус Горчичный, ветчина, помидоры, огурцы маринованные, сыр Моцарелла, соус Маджорио, колбаски Баварские",
            "Состав: ветчина, шампиньоны, маслины, маринованные огурцы, сыр Моцарелла, соус томатный, соус Маджорио, орегано",
            "Состав: соус томатный, шампиньоны, филе куриное, соус Маджорио, сыр Моцарелла, зелень",
            "Состав: соус томатный, сервелат, шампиньоны, помидоры, соус Маджорио, сыр Моцарелла, зелень",
            "Состав: сыр Чеддер, Моцарелла, Дор Блю, Пармезан" ,
            "Состав: шампиньоны, соус Маджорио, сыр Моцарелла, филадельфия",
            "Состав: ветчина, сервелат, сыр Моцарелла, соус Томатный, соус Маджорио, укроп" ,
            "Состав: бекон, сыры чеддер и пармезан, моцарелла, томаты, красный лук, чеснок, соус альфредо, итальянские травы",
            "Состав: цыпленок, соус песто, кубики брынзы, томаты, моцарелла, соус альфредо",
            "Состав: Цыпленок, острая чоризо, соус бургер, сладкий перец, красный лук, томаты, моцарелла, соус ранч, чеснок"};

    public static Pizza[] makePizza() {
        Pizza[] array = new Pizza[14];

        for (int i = 0; i < array.length; i++) {
            Pizza pizza;
            if (minPriceArray[i] % 10 == 1 && minPriceArray[i] != 11)
                pizza = new Pizza(nameArray[i], "от " + minPriceArray[i] + " рубля", imageArray[i]);
            else
                pizza = new Pizza(nameArray[i], "от " + minPriceArray[i] + " рублей", imageArray[i]);

            array[i] = pizza;
        }
        return array;
    }
}