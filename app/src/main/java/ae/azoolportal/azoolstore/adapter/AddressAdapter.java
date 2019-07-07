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
import android.widget.Toast;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.interfaces.OnAdditionCallback;
import ae.azoolportal.azoolstore.interfaces.OnAddressDeleteCallback;
import ae.azoolportal.azoolstore.interfaces.OnAddressEditCallback;
import ae.azoolportal.azoolstore.interfaces.OnAddressSelectionCallback;
import ae.azoolportal.azoolstore.interfaces.OnSubtractCallback;
import ae.azoolportal.azoolstore.model.Address;
import ae.azoolportal.azoolstore.model.Clothes;

public class AddressAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Address> adsArrayList;
    public OnAddressEditCallback onAddressEditCallback;
    public OnAddressDeleteCallback onAddressDeleteCallback;
    public OnAddressSelectionCallback onAddressSelectionCallback;

    public AddressAdapter(Context mContext, ArrayList<Address> adsArrayList, OnAddressEditCallback onAddressEditCallback, OnAddressDeleteCallback onAddressDeleteCallback, OnAddressSelectionCallback onAddressSelectionCallback) {
        this.mContext = mContext;
        this.adsArrayList = adsArrayList;
        this.onAddressEditCallback = onAddressEditCallback;
        this.onAddressDeleteCallback = onAddressDeleteCallback;
        this.onAddressSelectionCallback = onAddressSelectionCallback;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_address, parent,false);
        return new AdsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Address add = adsArrayList.get(position);
        AdsHolder holder = (AdsHolder) viewHolder;

        holder.txt_address_type.setText(add.getAddressType());
        holder.txt_buildingname_number.setText(add.getBuldingNameAndNumber());
        holder.txt_street.setText(add.getStreet());
        holder.txt_city.setText(add.getCity());
        holder.txt_state.setText(add.getState());
        holder.txt_country.setText(add.getCountry());
        holder.txt_zipicode.setText(add.getZipCode());
        holder.txt_landmark.setText(add.getLandmark());

        /*if (ads.getArabicName() != null && !ads.getArabicName().isEmpty()) {
            holder.txt_name_arabic.setText(ads.getArabicName());
        }*/

/*        if (mContext != null){
            //Glide.with(mContext).load(ads.getImage()).into(holder.img_ads);
            //Picasso.get().load(clothes.getImage()).into(holder.img_cloth_icon);
            //Picasso.get().load(R.drawable.robo).into(holder.img_cloth_icon);
            holder.img_cloth_icon.setImageResource(R.drawable.robo);
        }*/
    }

    @Override
    public int getItemCount() {
        return adsArrayList.size();
    }

    private class AdsHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        ImageView img_selection_dot, img_edit, img_delete;
        TextView txt_address_type, txt_buildingname_number, txt_street, txt_landmark, txt_city, txt_state, txt_country, txt_zipicode;
        LinearLayout itemLayout;

        public AdsHolder(View view) {
            super(view);

            img_selection_dot=view.findViewById(R.id.img_selection_dot);
            img_edit=view.findViewById(R.id.img_edit_address);
            img_delete=view.findViewById(R.id.img_delete_address);
            txt_address_type= view.findViewById(R.id.txt_address_type);
            txt_buildingname_number= view.findViewById(R.id.txt_building_name_number);
            txt_street= view.findViewById(R.id.txt_street);
            txt_landmark= view.findViewById(R.id.txt_landmark);
            txt_city= view.findViewById(R.id.txt_city);
            txt_state= view.findViewById(R.id.txt_state);
            txt_country= view.findViewById(R.id.txt_country);
            txt_zipicode= view.findViewById(R.id.txt_zipcode);
            itemLayout=view.findViewById(R.id.main_content_layout);

            img_edit.setOnClickListener(this);
            img_delete.setOnClickListener(this);
            itemLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(v==img_edit)
            {
                onAddressEditCallback.onAddressEditSuccess(adsArrayList.get(getAdapterPosition()).getId(), getAdapterPosition());
            }

            if(v==img_delete)
            {
                onAddressDeleteCallback.onAddressDeleteSuccess(adsArrayList.get(getAdapterPosition()).getId(), getAdapterPosition());
            }

            if(v==itemLayout)
            {
                onAddressSelectionCallback.onAddressSelectionSuccess(adsArrayList.get(getAdapterPosition()).getId(), getAdapterPosition());
            }

        }
    }

}
