package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<FoodViewHolder>{
private Context context;
private List<FoodData> foodData;

    public MyAdapter(Context context, List<FoodData> foodData) {
        this.context = context;
        this.foodData = foodData;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);

        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodViewHolder holder, int position) {
        FoodViewHolder.imageView.setImageResource(foodData.get(position).getItemImage());
        FoodViewHolder.title.setText(foodData.get(position).getItemName());
        FoodViewHolder.desc.setText(foodData.get(position).getItemDesc());
        FoodViewHolder.price.setText(foodData.get(position).getItemPrice());
        FoodViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,MainActivity2.class);
                intent.putExtra("Image",foodData.get(holder.getAdapterPosition()).getItemImage());
                intent.putExtra("Desc",foodData.get(holder.getAdapterPosition()).getItemDesc());
                intent.putExtra("Price",foodData.get(holder.getAdapterPosition()).getItemPrice());
                intent.putExtra("Title",foodData.get(holder.getAdapterPosition()).getItemName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return foodData.size();
    }
}
class  FoodViewHolder extends RecyclerView.ViewHolder{
    static ImageView imageView;
static TextView title,desc,price;
static CardView cardView;
    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.img);
        title=itemView.findViewById(R.id.tvTitle);
        desc=itemView.findViewById(R.id.tvDesc);
        price=itemView.findViewById(R.id.tvprice);
        cardView=itemView.findViewById(R.id.myCardView);

    }
}