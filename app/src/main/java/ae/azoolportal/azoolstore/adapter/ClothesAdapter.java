package ae.azoolportal.azoolstore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.interfaces.OnAdditionCallback;
import ae.azoolportal.azoolstore.interfaces.OnSubtractCallback;
import ae.azoolportal.azoolstore.model.Clothes;

public class ClothesAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Clothes> adsArrayList;
    public OnSubtractCallback onSubtractCallback;
    public OnAdditionCallback onAdditionCallback;

    public ClothesAdapter(Context mContext, ArrayList<Clothes> adsArrayList, OnSubtractCallback onSubtractCallback, OnAdditionCallback onAdditionCallback) {
        this.mContext = mContext;
        this.adsArrayList = adsArrayList;
        this.onSubtractCallback = onSubtractCallback;
        this.onAdditionCallback = onAdditionCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clothes, parent,false);
        return new AdsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Clothes clothes = adsArrayList.get(position);
        AdsHolder holder = (AdsHolder) viewHolder;

        holder.txt_title.setText(clothes.getTitle());
        holder.txt_price.setText(String.valueOf(clothes.getPrice()));
        holder.txt_quantity.setText(String.valueOf(clothes.getQuantity()));

        /*if (ads.getArabicName() != null && !ads.getArabicName().isEmpty()) {
            holder.txt_name_arabic.setText(ads.getArabicName());
        }*/

        if (mContext != null){
            //Glide.with(mContext).load(ads.getImage()).into(holder.img_ads);
            //Picasso.get().load(clothes.getImage()).into(holder.img_cloth_icon);
            //Picasso.get().load(R.drawable.robo).into(holder.img_cloth_icon);
            holder.img_cloth_icon.setImageResource(R.drawable.robo);
        }
    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

    private class AdsHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView img_cloth_icon, img_subtract, img_addition;
        TextView txt_title, txt_price, txt_quantity;
        TextView txt_name_english;
        LinearLayout txt_visit_shop;
        LinearLayout itemLayout;

        public AdsHolder(View view) {
            super(view);

            img_cloth_icon=view.findViewById(R.id.img_cloth_icon);
            img_subtract=view.findViewById(R.id.img_subtract_icon);
            img_addition=view.findViewById(R.id.img_addition_icon);
            txt_title=view.findViewById(R.id.txt_title);
            txt_price= view.findViewById(R.id.txt_price);
            txt_quantity=view.findViewById(R.id.txt_quantity);
            itemLayout=view.findViewById(R.id.item_clothes);

            img_subtract.setOnClickListener(this);
            img_addition.setOnClickListener(this);
            //itemLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(v==img_subtract)
            {
                onSubtractCallback.onSubtractSuccess("1", getAdapterPosition());
            }

            if(v==img_addition)
            {
                onAdditionCallback.onAdditionSuccess("1", getAdapterPosition());
            }

        }
    }

}
