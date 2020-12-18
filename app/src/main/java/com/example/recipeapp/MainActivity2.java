package com.example.recipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
TextView textView,textView2,textView3;
ImageView imageView,imageView2,imageView3;
Button b;DBHelper db;
EditText e1,e2;  int image,price;String name,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new DBHelper(this);
     b=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        textView2=(TextView)findViewById(R.id.textView4);
        textView3=(TextView)findViewById(R.id.textView2);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView2=(ImageView)findViewById(R.id.add);
        imageView3=(ImageView)findViewById(R.id.sub);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
                imageView.startAnimation(animation);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                imageView.startAnimation(animation);
            }
        });
        e1=(EditText)findViewById(R.id.editTextTextPersonName3);
        e2=(EditText)findViewById(R.id.editTextTextPersonName4);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            image =bundle.getInt("Image",0);
             price=bundle.getInt("Price");
             name=bundle.getString("Title");
             desc=bundle.getString("Desc");
        }
        textView.setText(desc);
        textView3.setText(name);
        textView2.setText(String.format("%d",price));
        imageView.setImageResource(image);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean inserted= db.insertOrder(e1.getText().toString(),e2.getText().toString(),price,image,name,desc);
               if (inserted)
                   Toast.makeText(MainActivity2.this,"Order Successfully Placed",Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(MainActivity2.this,"ERROR!! Order  is not Successfully Placed",Toast.LENGTH_SHORT).show();

            }
        });

    }
}