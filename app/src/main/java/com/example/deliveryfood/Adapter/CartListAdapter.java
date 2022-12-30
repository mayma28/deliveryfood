package com.example.deliveryfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deliveryfood.Domain.FoodDomain;
import com.example.deliveryfood.Helper.ManagementCart;
import com.example.deliveryfood.Interface.ChangeNumberItemListener;
import com.example.deliveryfood.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<FoodDomain> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemListener changeNumberItemListener ;

    public CartListAdapter(ArrayList<FoodDomain> listFoodSelected,
                           Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listFoodSelected = listFoodSelected;
        managementCart= new ManagementCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(listFoodSelected.get(position).getTitle());
        holder.feeEachItem.setText("$"+listFoodSelected.get(position).getFee());
        holder.totalEachItem.setText("$"+ Math.round((listFoodSelected.get(position).getNumberInCart()*listFoodSelected.get(position).getFee())));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        int drawableResourceId= holder.itemView.getContext().getResources()
                .getIdentifier(listFoodSelected.get(position).getPic(),"drawable",
                        holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));

        holder.minusItem.setOnClickListener(view -> managementCart.minusNumberFood(listFoodSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));
    }

    @Override
    public int getItemCount( ) {return listFoodSelected.size();}


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, feeEachItem;
        ImageView pic, plusItem , minusItem;
        TextView totalEachItem,num;

        public ViewHolder(@NonNull View itemView ){
            super(itemView);
            title = itemView.findViewById(R.id.title);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem= itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
