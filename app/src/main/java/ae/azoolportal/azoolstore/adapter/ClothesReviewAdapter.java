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

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.model.Clothes;

public class ClothesReviewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Clothes> adsArrayList;

    public ClothesReviewAdapter(Context mContext, ArrayList<Clothes> adsArrayList) {
        this.mContext = mContext;
        this.adsArrayList = adsArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clothes_review, parent,false);
        return new AdsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Clothes clothes = adsArrayList.get(position);
        AdsHolder holder = (AdsHolder) viewHolder;

        holder.txt_title.setText(clothes.getTitle());
        holder.txt_price.setText(String.valueOf(clothes.getPrice()));
        holder.txt_quantity.setText(String.valueOf(clothes.getQuantity()));
        holder.txt_amount.setText(String.valueOf(clothes.getPriceWithQuantity()));

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

    private class AdsHolder extends RecyclerView.ViewHolder
    {

        ImageView img_cloth_icon;
        TextView txt_title, txt_price, txt_quantity, txt_amount;
        TextView txt_name_english;
        LinearLayout txt_visit_shop;
        LinearLayout itemLayout;

        public AdsHolder(View view) {
            super(view);

            img_cloth_icon=view.findViewById(R.id.img_cloth_icon);
            txt_title=view.findViewById(R.id.txt_title);
            txt_price= view.findViewById(R.id.txt_price);
            txt_quantity=view.findViewById(R.id.txt_quantity);
            txt_amount=view.findViewById(R.id.txt_amount);
            itemLayout=view.findViewById(R.id.item_clothes);

            //itemLayout.setOnClickListener(this);

        }

    }

}
