package com.example.deliveryfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.deliveryfood.Adapter.CategoryAdapter;
import com.example.deliveryfood.Adapter.RecommendedAdapter;
import com.example.deliveryfood.Domain.CategoryDomain;
import com.example.deliveryfood.Domain.FoodDomain;
import com.example.deliveryfood.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
  private RecyclerView.Adapter adapter , adapter2;
  private RecyclerView recyclerViewCategorieListe , recyclerViewPopularListe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerViewCategorie();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
            }
        });

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,CartActivity.class);
                startActivity(i);
            }
        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularListe=findViewById(R.id.view2);
        recyclerViewPopularListe.setLayoutManager(LinearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pepperoni pizza","pizza1",
                "slices pepperoni , mozzarella cheese , " +
                        "fresh oregano, ground black pepper ," +
                        "pizza sauce",13.0 , 5, 20 , 1000));
        foodlist.add(new FoodDomain("Cheese Burger","burger",
                "beef ,Gouda Cheese ," +
                        "Special sauce , Lettuce ," +
                        "tomato",15.20 , 4, 18 , 1500));
        foodlist.add(new FoodDomain("Vegetable pizza","pizza3",
                "olive oil , vegetable oil , " +
                        "pitted Kalamata, cherry tomatoes ," +
                        "fresh oregano ,basil ",11.0 , 3, 16 , 800));

        adapter2 = new RecommendedAdapter(foodlist);
        recyclerViewPopularListe.setAdapter(adapter2);

    }

    private void recyclerViewCategorie() {
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategorieListe=findViewById(R.id.view1);
        recyclerViewCategorieListe.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryListe = new ArrayList<>();
        categoryListe.add(new CategoryDomain("Pizza", "cat_1"));
        categoryListe.add(new CategoryDomain("Burger", "cat_2"));
        categoryListe.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryListe.add(new CategoryDomain("Drink", "cat_4"));
        categoryListe.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new  CategoryAdapter(categoryListe);
        recyclerViewCategorieListe.setAdapter(adapter);
    }
}