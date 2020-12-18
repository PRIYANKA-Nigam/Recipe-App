package com.example.recipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
    TextView textView;
    ImageView imageView1,imageView2;
    List<FoodData> myFoodList;
    FoodData mFoodData;DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1=(ImageView)findViewById(R.id.add);
        imageView2=(ImageView)findViewById(R.id.sub);

        textView=(TextView)findViewById(R.id.textView7);
        db=new DBHelper(this);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db.getOrders();
                if(c.getCount()==0){
                    showData("Error","Nothing is stored");
                    return;
                } StringBuffer stringBuffer=new StringBuffer();
                while (c.moveToNext()){
                    stringBuffer.append("Username: " +c.getString(0)+"\n");
                    stringBuffer.append("Mob No: " +c.getString(1)+"\n\n");

                }
                showData("Registered Name & Number",stringBuffer.toString());
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        myFoodList =new ArrayList<>();
        mFoodData= new FoodData("One Dish Chicken & Rice bake","This is a half fried dish cooked in a low flame" +
                "and garnished with special spicies and other supplements.This dish is grilled under high pressure." +
                "For additional taste we have added soya sauce. To enrich the quality of the item extra minerals " +
                "are added.This makes the dish crunchy,spicy, and provide extra health benefit","Rs.900",R.drawable.image_search_1563197555359);
        myFoodList.add(mFoodData);
        mFoodData= new FoodData("Pine Apple","This is a half fried dish cooked in a low flame" +
                "and garnished with special spicies and other supplements.This dish is grilled under high pressure." +
                "For additional taste we have added soya sauce. To enrich the quality of the item extra minerals " +
                "are added.This makes the dish crunchy,spicy, and provide extra health benefit","Rs.800",R.drawable.image_search_1563197569590);
        myFoodList.add(mFoodData);
        mFoodData=new FoodData("Chicken Snacks"," ","Rs.450",R.drawable.chicken_snacks); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Fries with Hamburger"," ","Rs.450",R.drawable.fries_with_hamburger); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Fritters"," ","Rs.450",R.drawable.fritters); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Fruit Custard"," ","Rs.450",R.drawable.fruit_custard); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Pie"," ","Rs.450",R.drawable.pie); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Pluffy Chapati"," ","Rs.450",R.drawable.pluffy_chapati); myFoodList.add(mFoodData);
        mFoodData=new FoodData("Salad"," ","Rs.450",R.drawable.salad); myFoodList.add(mFoodData);
        MyAdapter myAdapter=new MyAdapter(MainActivity.this,myFoodList);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id==R.id.see) {
            Toast.makeText(getApplicationContext(),"You Clicked Share",Toast.LENGTH_SHORT).show();
        }
        return true;
        }
    public  void showData(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}