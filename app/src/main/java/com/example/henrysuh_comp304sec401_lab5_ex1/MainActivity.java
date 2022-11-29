package com.example.henrysuh_comp304sec401_lab5_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.henrysuh_comp304sec401_lab5_ex1.service.DatabaseService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final DatabaseService databaseService = new DatabaseService();
    ListView listView;
    List<String> cuisineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cuisineList = databaseService.getCategories(this);

        listView = findViewById(R.id.listCuisine);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                cuisineList
        ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, position + " " + id, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ChooseRestaurant.class);
                intent.putExtra("cuisine", cuisineList.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}