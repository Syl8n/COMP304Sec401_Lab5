package com.example.henrysuh_comp304sec401_lab5_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.henrysuh_comp304sec401_lab5_ex1.dto.RestaurantDto;
import com.example.henrysuh_comp304sec401_lab5_ex1.service.DatabaseService;

import java.util.List;

public class ChooseRestaurant extends AppCompatActivity {

    private final DatabaseService databaseService = new DatabaseService();
    List<RestaurantDto> restaurantDtoList;
    TextView textCuisine;
    ListView listView;
    String cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_restaurant);

        Intent intent = getIntent();

        cuisine = intent.getStringExtra("cuisine");
        if(cuisine == null || "".equals(cuisine)) {
            SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
            if(sharedPreferences != null) {
                cuisine = sharedPreferences.getString("selectedCuisine", "");
            }
        } else {
            SharedPreferences sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("selectedCuisine", cuisine);
            myEdit.apply();
        }

        textCuisine = findViewById(R.id.textCuisine);
        textCuisine.setText(cuisine + " :");

        restaurantDtoList = databaseService.getRestaurants(this, cuisine);
        listView = findViewById(R.id.listRestaurant);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                restaurantDtoList
        ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ChooseRestaurant.this, restaurantDtoList.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChooseRestaurant.this, ShowMap.class);
                intent.putExtra("restaurantId", restaurantDtoList.get(position).getId());
                startActivity(intent);
            }
        });
    }
}