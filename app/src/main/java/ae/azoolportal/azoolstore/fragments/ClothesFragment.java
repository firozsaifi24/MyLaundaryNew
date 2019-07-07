package ae.azoolportal.azoolstore.fragments;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;
import ae.azoolportal.azoolstore.adapter.ClothesDryCleanAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesIronAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesPremiumLaundryAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesWashAndFoldAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesWashAndIronAdapter;
import ae.azoolportal.azoolstore.interfaces.OnAdditionCallback;
import ae.azoolportal.azoolstore.interfaces.OnSubtractCallback;
import ae.azoolportal.azoolstore.model.Address;
import ae.azoolportal.azoolstore.model.Clothes;
import ae.azoolportal.azoolstore.utilities.PublicValues;

public class ClothesFragment extends Fragment implements View.OnClickListener, OnSubtractCallback, OnAdditionCallback {

    //Testing

    ImageView menu_btn;

    Button btn_select_service;

    LinearLayout washandfold_layout, iron_layout, washandiron_layout, premiumlaundry_layout, dryclean_layout;
    TextView txt_washandfold, txt_iron, txt_washandiron, txt_premiumlaundry, txt_dryclean;


    //recyclerview
    RecyclerView recyclerView_washandfold;
    RecyclerView recyclerView_iron;
    RecyclerView recyclerView_washandiron;
    RecyclerView recyclerView_premium_laundry;
    RecyclerView recyclerView_dryclean;
    //arraylist
    private ArrayList<Clothes> adsList;
    //adapters
    private ClothesWashAndFoldAdapter clothesWashAndFoldAdapter;
    private ClothesIronAdapter clothesIronAdapter;
    private ClothesWashAndIronAdapter clothesWashAndIronAdapter;
    private ClothesPremiumLaundryAdapter clothesPremiumLaundryAdapter;
    private ClothesDryCleanAdapter clothesDryCleanAdapter;

    TextView txt_total_price;
    private long total_price=0;

    int activeService;
    boolean isActiveWashAndFold= false;
    boolean isActiveWashAndIron= false;
    boolean isActiveIron= false;
    boolean isActivePremiumLaundry= false;
    boolean isActiveDryClean= false;



    //selected list for review order
    private ArrayList<Clothes> washandfold_list = new ArrayList<>();
    private ArrayList<Clothes> washandiron_list = new ArrayList<>();
    private ArrayList<Clothes> iron_list = new ArrayList<>();
    private ArrayList<Clothes> premiumlaundry_list = new ArrayList<>();
    private ArrayList<Clothes> drycleaning_list = new ArrayList<>();


    //Received data from previous fragment
    String pickupDate;
    String pickupTime;
    String deliveryDate;
    String deliveryTime;
    String note;
    private ArrayList<Address> selectedAddress = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ClothesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ClothesFragment newInstance(String param1, String param2) {
        ClothesFragment fragment = new ClothesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

            pickupDate= getArguments().getString("PickupDate");
            pickupTime= getArguments().getString("PickupTime");
            deliveryDate= getArguments().getString("DeliveryDate");
            deliveryTime= getArguments().getString("DeliveryTime");
            note= getArguments().getString("Note");
            selectedAddress= getArguments().getParcelableArrayList("Address");

            Log.d("Pickup Date", pickupDate);
            Log.d("Pickup Time", pickupTime);
            Log.d("Delivery Date", deliveryDate);
            Log.d("Delivery Time", deliveryTime);
            Log.d("Note", note);
            Log.d("Address", String.valueOf(selectedAddress.size()));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_clothes, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        btn_select_service= view.findViewById(R.id.btn_select_service);
        washandfold_layout= view.findViewById(R.id.washandfold_layout);
        iron_layout= view.findViewById(R.id.iron_layout);
        washandiron_layout= view.findViewById(R.id.washandiron_layout);
        premiumlaundry_layout= view.findViewById(R.id.premiumlaundry_layout);
        dryclean_layout= view.findViewById(R.id.dryclean_layout);
        txt_washandfold= view.findViewById(R.id.txt_washandfold);
        txt_iron= view.findViewById(R.id.txt_iron);
        txt_washandiron= view.findViewById(R.id.txt_washandiron);
        txt_premiumlaundry= view.findViewById(R.id.txt_premiumlaundry);
        txt_dryclean= view.findViewById(R.id.txt_dryclean);
        txt_total_price= view.findViewById(R.id.txt_total_price);

        isActiveWashAndFold= false;
        isActiveWashAndIron= false;
        isActiveIron= false;
        isActivePremiumLaundry= false;
        isActiveDryClean= false;

        total_price=0;
        adsList = new ArrayList<>();
        /*if(adsList!=null)
        {
            adsList.clear();
        }*/
        for(int i=0; i<=5; i++)
        {
            Clothes clothes= new Clothes();
            clothes.setId("1"+i);
            clothes.setTitle("Shirt "+i);
            clothes.setPrice(10+i);
            clothes.setQuantity(0);
            clothes.setImage("");

            adsList.add(clothes);
        }

        Log.d("AdsList", String.valueOf(adsList.size()));

        //selected list for review order
        washandfold_list = new ArrayList<>();
        washandiron_list = new ArrayList<>();
        iron_list = new ArrayList<>();
        premiumlaundry_list = new ArrayList<>();
        drycleaning_list = new ArrayList<>();

        //recycler for washandfold
        recyclerView_washandfold=view.findViewById(R.id.recycler_home_washandfold);

        GridLayoutManager adsGridLayoutManager= new GridLayoutManager(getActivity(),1);
        recyclerView_washandfold.setLayoutManager(adsGridLayoutManager);
        recyclerView_washandfold.setScrollContainer(false);
        recyclerView_washandfold.setNestedScrollingEnabled(false);

        clothesWashAndFoldAdapter= new ClothesWashAndFoldAdapter(getActivity(), adsList, this, this);
        recyclerView_washandfold.setAdapter(clothesWashAndFoldAdapter);
        //Toast.makeText(getActivity(),"Showing Home Ads", Toast.LENGTH_LONG).show();


        //recycler for washandiron
        recyclerView_washandiron=view.findViewById(R.id.recycler_home_washandiron);
        GridLayoutManager adsGridLayoutManager3= new GridLayoutManager(getActivity(),1);
        recyclerView_washandiron.setLayoutManager(adsGridLayoutManager3);
        recyclerView_washandiron.setScrollContainer(false);
        recyclerView_washandiron.setNestedScrollingEnabled(false);

        clothesWashAndIronAdapter= new ClothesWashAndIronAdapter(getActivity(), adsList, this, this);
        recyclerView_washandiron.setAdapter(clothesWashAndIronAdapter);


        //recycler for iron
        recyclerView_iron=view.findViewById(R.id.recycler_home_iron);
        GridLayoutManager adsGridLayoutManager2= new GridLayoutManager(getActivity(),1);
        recyclerView_iron.setLayoutManager(adsGridLayoutManager2);
        recyclerView_iron.setScrollContainer(false);
        recyclerView_iron.setNestedScrollingEnabled(false);

        clothesIronAdapter= new ClothesIronAdapter(getActivity(), adsList, this, this);
        recyclerView_iron.setAdapter(clothesIronAdapter);


        //recycler for premium laundry
        recyclerView_premium_laundry=view.findViewById(R.id.recycler_home_premium_laundary);
        GridLayoutManager adsGridLayoutManager4= new GridLayoutManager(getActivity(),1);
        recyclerView_premium_laundry.setLayoutManager(adsGridLayoutManager4);
        recyclerView_premium_laundry.setScrollContainer(false);
        recyclerView_premium_laundry.setNestedScrollingEnabled(false);

        clothesPremiumLaundryAdapter= new ClothesPremiumLaundryAdapter(getActivity(), adsList, this, this);
        recyclerView_premium_laundry.setAdapter(clothesPremiumLaundryAdapter);

        //recycler for dry clean
        recyclerView_dryclean=view.findViewById(R.id.recycler_home_dryclean);
        GridLayoutManager adsGridLayoutManager5= new GridLayoutManager(getActivity(),1);
        recyclerView_dryclean.setLayoutManager(adsGridLayoutManager5);
        recyclerView_dryclean.setScrollContainer(false);
        recyclerView_dryclean.setNestedScrollingEnabled(false);

        clothesDryCleanAdapter= new ClothesDryCleanAdapter(getActivity(), adsList, this, this);
        recyclerView_dryclean.setAdapter(clothesDryCleanAdapter);


        menu_btn.setOnClickListener(this);
        btn_select_service.setOnClickListener(this);
        washandfold_layout.setOnClickListener(this);
        iron_layout.setOnClickListener(this);
        washandiron_layout.setOnClickListener(this);
        premiumlaundry_layout.setOnClickListener(this);
        dryclean_layout.setOnClickListener(this);


        //testing visiblity starts here

        Log.d("OnCreateView", "OnCreateView reloaded");
        int a= recyclerView_washandfold.getVisibility();
        int b= recyclerView_washandiron.getVisibility();
        int c= recyclerView_iron.getVisibility();
        int d= recyclerView_premium_laundry.getVisibility();
        int e= recyclerView_dryclean.getVisibility();

        Log.d("a", String.valueOf(a));
        Log.d("b", String.valueOf(b));
        Log.d("c", String.valueOf(c));
        Log.d("d", String.valueOf(d));
        Log.d("e", String.valueOf(e));

        //testing visibilty ends here



        menu_btn.setOnClickListener(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v== menu_btn)
        {
            if(getActivity()!=null)
            {
                try
                {
                    MainActivity mainActivity= (MainActivity)getActivity();
                    mainActivity.onBackPressed();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(v==washandfold_layout)
        {
            isActiveWashAndFold=true;
            washandfold_layout.setBackgroundResource(R.drawable.border_select);
            txt_washandfold.setTextColor(getResources().getColor(R.color.white));

            iron_layout.setBackgroundResource(R.drawable.border_unselect);
            washandiron_layout.setBackgroundResource(R.drawable.border_unselect);
            premiumlaundry_layout.setBackgroundResource(R.drawable.border_unselect);
            dryclean_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_iron.setTextColor(getResources().getColor(R.color.black));
            txt_washandiron.setTextColor(getResources().getColor(R.color.black));
            txt_premiumlaundry.setTextColor(getResources().getColor(R.color.black));
            txt_dryclean.setTextColor(getResources().getColor(R.color.black));

            recyclerView_washandfold.clearAnimation();
            recyclerView_washandiron.clearAnimation();
            recyclerView_iron.clearAnimation();
            recyclerView_premium_laundry.clearAnimation();
            recyclerView_dryclean.clearAnimation();

            recyclerView_washandfold.setVisibility(View.VISIBLE);
            if(activeService!=1)
            {
                ObjectAnimator.ofFloat(recyclerView_washandfold, View.ALPHA, 0.0f, 1.0f).setDuration(1000).start();
                activeService=1;
            }


            recyclerView_iron.setVisibility(View.GONE);
            recyclerView_washandiron.setVisibility(View.GONE);
            recyclerView_premium_laundry.setVisibility(View.GONE);
            recyclerView_dryclean.setVisibility(View.GONE);
            //recyclerView_washandfold.animate().alpha(1.0f).setDuration(2000);


            //testing visiblity starts here

            Log.d("Service clicked", "Inside service 1");
            int a= recyclerView_washandfold.getVisibility();
            int b= recyclerView_washandiron.getVisibility();
            int c= recyclerView_iron.getVisibility();
            int d= recyclerView_premium_laundry.getVisibility();
            int e= recyclerView_dryclean.getVisibility();

            Log.d("a", String.valueOf(a));
            Log.d("b", String.valueOf(b));
            Log.d("c", String.valueOf(c));
            Log.d("d", String.valueOf(d));
            Log.d("e", String.valueOf(e));

            //testing visibilty ends here


        }

        if(v==washandiron_layout)
        {
            isActiveWashAndIron=true;
            washandiron_layout.setBackgroundResource(R.drawable.border_select);
            txt_washandiron.setTextColor(getResources().getColor(R.color.white));

            washandfold_layout.setBackgroundResource(R.drawable.border_unselect);
            iron_layout.setBackgroundResource(R.drawable.border_unselect);
            premiumlaundry_layout.setBackgroundResource(R.drawable.border_unselect);
            dryclean_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_washandfold.setTextColor(getResources().getColor(R.color.black));
            txt_iron.setTextColor(getResources().getColor(R.color.black));
            txt_premiumlaundry.setTextColor(getResources().getColor(R.color.black));
            txt_dryclean.setTextColor(getResources().getColor(R.color.black));

            recyclerView_washandiron.setVisibility(View.VISIBLE);
            if(activeService!=2)
            {
                ObjectAnimator.ofFloat(recyclerView_washandiron, View.ALPHA, 0.0f, 1.0f).setDuration(1000).start();
                activeService=2;
            }

            recyclerView_washandfold.setVisibility(View.GONE);
            recyclerView_iron.setVisibility(View.GONE);
            recyclerView_premium_laundry.setVisibility(View.GONE);
            recyclerView_dryclean.setVisibility(View.GONE);

        }

        if(v==iron_layout)
        {
            isActiveIron=true;
            iron_layout.setBackgroundResource(R.drawable.border_select);
            txt_iron.setTextColor(getResources().getColor(R.color.white));

            washandfold_layout.setBackgroundResource(R.drawable.border_unselect);
            washandiron_layout.setBackgroundResource(R.drawable.border_unselect);
            premiumlaundry_layout.setBackgroundResource(R.drawable.border_unselect);
            dryclean_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_washandfold.setTextColor(getResources().getColor(R.color.black));
            txt_washandiron.setTextColor(getResources().getColor(R.color.black));
            txt_premiumlaundry.setTextColor(getResources().getColor(R.color.black));
            txt_dryclean.setTextColor(getResources().getColor(R.color.black));

            recyclerView_iron.setVisibility(View.VISIBLE);
            if(activeService!=3)
            {
                ObjectAnimator.ofFloat(recyclerView_iron, View.ALPHA, 0.0f, 1.0f).setDuration(1000).start();
                activeService=3;
            }

            recyclerView_washandfold.setVisibility(View.GONE);
            recyclerView_washandiron.setVisibility(View.GONE);
            recyclerView_premium_laundry.setVisibility(View.GONE);
            recyclerView_dryclean.setVisibility(View.GONE);

        }

        if(v==premiumlaundry_layout)
        {
            isActivePremiumLaundry=true;
            premiumlaundry_layout.setBackgroundResource(R.drawable.border_select);
            txt_premiumlaundry.setTextColor(getResources().getColor(R.color.white));

            washandiron_layout.setBackgroundResource(R.drawable.border_unselect);
            iron_layout.setBackgroundResource(R.drawable.border_unselect);
            washandfold_layout.setBackgroundResource(R.drawable.border_unselect);
            dryclean_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_iron.setTextColor(getResources().getColor(R.color.black));
            txt_washandiron.setTextColor(getResources().getColor(R.color.black));
            txt_washandfold.setTextColor(getResources().getColor(R.color.black));
            txt_dryclean.setTextColor(getResources().getColor(R.color.black));

            recyclerView_premium_laundry.setVisibility(View.VISIBLE);
            if(activeService!=4)
            {
                ObjectAnimator.ofFloat(recyclerView_premium_laundry, View.ALPHA, 0.0f, 1.0f).setDuration(1000).start();
                activeService=4;
            }

            recyclerView_washandfold.setVisibility(View.GONE);
            recyclerView_iron.setVisibility(View.GONE);
            recyclerView_washandiron.setVisibility(View.GONE);
            recyclerView_dryclean.setVisibility(View.GONE);

        }

        if(v==dryclean_layout)
        {
            isActiveDryClean=true;
            dryclean_layout.setBackgroundResource(R.drawable.border_select);
            txt_dryclean.setTextColor(getResources().getColor(R.color.white));

            washandiron_layout.setBackgroundResource(R.drawable.border_unselect);
            premiumlaundry_layout.setBackgroundResource(R.drawable.border_unselect);
            washandfold_layout.setBackgroundResource(R.drawable.border_unselect);
            iron_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_washandiron.setTextColor(getResources().getColor(R.color.black));
            txt_premiumlaundry.setTextColor(getResources().getColor(R.color.black));
            txt_washandfold.setTextColor(getResources().getColor(R.color.black));
            txt_iron.setTextColor(getResources().getColor(R.color.black));

            recyclerView_dryclean.setVisibility(View.VISIBLE);
            if(activeService!=5)
            {
                ObjectAnimator.ofFloat(recyclerView_dryclean, View.ALPHA, 0.0f, 1.0f).setDuration(1000).start();
                activeService=5;
            }

            recyclerView_washandfold.setVisibility(View.GONE);
            recyclerView_iron.setVisibility(View.GONE);
            recyclerView_washandiron.setVisibility(View.GONE);
            recyclerView_premium_laundry.setVisibility(View.GONE);

        }

        if(v==btn_select_service)
        {
            if(getActivity()!=null)
            {

                try
                {

                    //wash and fold list
                    int size= adsList.size();
                    Log.d("Total value", String.valueOf(size));

                    if(isActiveWashAndFold || isActiveWashAndIron || isActiveIron || isActivePremiumLaundry || isActiveDryClean)
                    {

                        if(isActiveWashAndFold)
                        {
                            for(int a=0; a<size; a++)
                            {
                                TextView i= recyclerView_washandfold.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_quantity);
                                int qty= Integer.parseInt(i.getText().toString());

                                TextView j= recyclerView_washandfold.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_price);
                                long rate= Integer.parseInt(j.getText().toString());

                                TextView k= recyclerView_washandfold.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_title);
                                String title= k.getText().toString();

                                Log.d("Quantity value", String.valueOf(qty));
                                Log.d("Rate value", String.valueOf(rate));
                                Log.d("Title value", String.valueOf(title));
                                Log.d("Adapter value", String.valueOf(a));

                                if(qty>0)
                                {
                                    Clothes cloth= new Clothes();

                                    cloth.setTitle(title);
                                    cloth.setQuantity(qty);
                                    cloth.setPrice(rate);
                                    cloth.setPriceWithQuantity(qty*rate);

                                    washandfold_list.add(cloth);

                                    long total= qty*rate;
                                    Log.d("Total price per item", String.valueOf(total));

                                }

                            }
                        }

                        if(isActiveWashAndIron)
                        {
                            //wash and iron list
                            //int size= adsList.size();
                            //Log.d("Total value", String.valueOf(size));

                            for(int a=0; a<size; a++)
                            {
                                TextView i= recyclerView_washandiron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_quantity);
                                int qty= Integer.parseInt(i.getText().toString());

                                TextView j= recyclerView_washandiron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_price);
                                long rate= Integer.parseInt(j.getText().toString());

                                TextView k= recyclerView_washandiron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_title);
                                String title= k.getText().toString();

                                Log.d("Quantity value", String.valueOf(qty));
                                Log.d("Rate value", String.valueOf(rate));
                                Log.d("Title value", String.valueOf(title));
                                Log.d("Adapter value", String.valueOf(a));

                                if(qty>0)
                                {
                                    Clothes cloth= new Clothes();
                                    cloth.setTitle(title);
                                    cloth.setQuantity(qty);
                                    cloth.setPrice(rate);
                                    cloth.setPriceWithQuantity(qty*rate);

                                    washandiron_list.add(cloth);

                                    long total= qty*rate;
                                    Log.d("Total price per item", String.valueOf(total));

                                }

                            }

                        }

                        if(isActiveIron)
                        {
                            //iron list
                            //int size= adsList.size();
                            //Log.d("Total value", String.valueOf(size));

                            for(int a=0; a<size; a++)
                            {
                                TextView i= recyclerView_iron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_quantity);
                                int qty= Integer.parseInt(i.getText().toString());

                                TextView j= recyclerView_iron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_price);
                                long rate= Integer.parseInt(j.getText().toString());

                                TextView k= recyclerView_iron.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_title);
                                String title= k.getText().toString();

                                Log.d("Quantity value", String.valueOf(qty));
                                Log.d("Rate value", String.valueOf(rate));
                                Log.d("Title value", String.valueOf(title));
                                Log.d("Adapter value", String.valueOf(a));

                                if(qty>0)
                                {
                                    Clothes cloth= new Clothes();
                                    cloth.setTitle(title);
                                    cloth.setQuantity(qty);
                                    cloth.setPrice(rate);
                                    cloth.setPriceWithQuantity(qty*rate);

                                    iron_list.add(cloth);

                                    long total= qty*rate;
                                    Log.d("Total price per item", String.valueOf(total));

                                }

                            }
                        }

                        if(isActivePremiumLaundry)
                        {
                            //premium laundry list
                            //int size= adsList.size();
                            //Log.d("Total value", String.valueOf(size));

                            for(int a=0; a<size; a++)
                            {
                                TextView i= recyclerView_premium_laundry.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_quantity);
                                int qty= Integer.parseInt(i.getText().toString());

                                TextView j= recyclerView_premium_laundry.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_price);
                                long rate= Integer.parseInt(j.getText().toString());

                                TextView k= recyclerView_premium_laundry.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_title);
                                String title= k.getText().toString();

                                Log.d("Quantity value", String.valueOf(qty));
                                Log.d("Rate value", String.valueOf(rate));
                                Log.d("Title value", String.valueOf(title));
                                Log.d("Adapter value", String.valueOf(a));

                                if(qty>0)
                                {
                                    Clothes cloth= new Clothes();
                                    cloth.setTitle(title);
                                    cloth.setQuantity(qty);
                                    cloth.setPrice(rate);
                                    cloth.setPriceWithQuantity(qty*rate);

                                    premiumlaundry_list.add(cloth);

                                    long total= qty*rate;
                                    Log.d("Total price per item", String.valueOf(total));

                                }

                            }
                        }

                        if(isActiveDryClean)
                        {
                            //dry cleaning list
                            //int size= adsList.size();
                            //Log.d("Total value", String.valueOf(size));

                            for(int a=0; a<size; a++)
                            {
                                TextView i= recyclerView_dryclean.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_quantity);
                                int qty= Integer.parseInt(i.getText().toString());

                                TextView j= recyclerView_dryclean.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_price);
                                long rate= Integer.parseInt(j.getText().toString());

                                TextView k= recyclerView_dryclean.findViewHolderForAdapterPosition(a).itemView.findViewById(R.id.txt_title);
                                String title= k.getText().toString();

                                Log.d("Quantity value", String.valueOf(qty));
                                Log.d("Rate value", String.valueOf(rate));
                                Log.d("Title value", String.valueOf(title));
                                Log.d("Adapter value", String.valueOf(a));

                                if(qty>0)
                                {
                                    Clothes cloth= new Clothes();
                                    cloth.setTitle(title);
                                    cloth.setQuantity(qty);
                                    cloth.setPrice(rate);
                                    cloth.setPriceWithQuantity(qty*rate);

                                    drycleaning_list.add(cloth);

                                    long total= qty*rate;
                                    Log.d("Total price per item", String.valueOf(total));

                                }

                            }
                        }

                        Log.d("Washfold size", String.valueOf(washandfold_list.size()));
                        Log.d("Washiron size", String.valueOf(washandiron_list.size()));
                        Log.d("iron size", String.valueOf(iron_list.size()));
                        Log.d("premium laundry size", String.valueOf(premiumlaundry_list.size()));
                        Log.d("dry clean size", String.valueOf(drycleaning_list.size()));

                        if(washandfold_list.size()>0 || washandiron_list.size()>0 || iron_list.size()>0 || premiumlaundry_list.size()>0 || drycleaning_list.size()>0)
                        {
                            MainActivity mainActivity= (MainActivity)getActivity();
                            mainActivity.reviewOrder(washandfold_list, washandiron_list, iron_list, premiumlaundry_list, drycleaning_list, total_price, pickupDate, pickupTime, deliveryDate, deliveryTime, note, selectedAddress);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Please select clothes", Toast.LENGTH_LONG).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Please select clothes", Toast.LENGTH_LONG).show();
                    }

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onAdditionSuccess(final String serviceType, final int adapterId) {
        Log.d("Service Type: ", serviceType);

        if(serviceType.equals("1"))
        {
            TextView i= recyclerView_washandfold.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_washandfold.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>=0)
            {
                qty=qty+1;
                total_price+=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
            }

        }
        else
        if(serviceType.equals("2"))
        {
            TextView i= recyclerView_washandiron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_washandiron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>=0)
            {
                qty=qty+1;
                total_price+=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
            }

        }
        else
        if(serviceType.equals("3"))
        {
            TextView i= recyclerView_iron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_iron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>=0)
            {
                qty=qty+1;
                total_price+=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
            }


        }
        else
        if(serviceType.equals("4"))
        {
            TextView i= recyclerView_premium_laundry.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_premium_laundry.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>=0)
            {
                qty=qty+1;
                total_price+=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
            }

        }
        else
        if(serviceType.equals("5"))
        {
            TextView i= recyclerView_dryclean.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_dryclean.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>=0)
            {
                qty=qty+1;
                total_price+=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Added", Toast.LENGTH_LONG).show();
            }

        }
        else
        {
            Toast.makeText(getActivity(), "No Service Type available!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onSubtractSuccess(final String serviceType, final int adapterId) {
        Log.d("Service Type: ", serviceType);

        if(serviceType.equals("1"))
        {

            TextView i= recyclerView_washandfold.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_washandfold.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>0)
            {
                qty=qty-1;
                total_price-=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Subtracted", Toast.LENGTH_LONG).show();

            }

        }
        else
        if(serviceType.equals("2"))
        {
            TextView i= recyclerView_washandiron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_washandiron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>0)
            {
                qty=qty-1;
                total_price-=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Subtracted", Toast.LENGTH_LONG).show();

            }
        }
        else
        if(serviceType.equals("3"))
        {
            TextView i= recyclerView_iron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_iron.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>0)
            {
                qty=qty-1;
                total_price-=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Subtracted", Toast.LENGTH_LONG).show();

            }

        }
        else
        if(serviceType.equals("4"))
        {
            TextView i= recyclerView_premium_laundry.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_premium_laundry.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>0)
            {
                qty=qty-1;
                total_price-=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Subtracted", Toast.LENGTH_LONG).show();

            }
        }
        else
        if(serviceType.equals("5"))
        {
            TextView i= recyclerView_dryclean.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_quantity);
            int qty= Integer.parseInt(i.getText().toString());
            TextView j= recyclerView_dryclean.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.txt_price);
            long rate= Integer.parseInt(j.getText().toString());
            Log.d("Quantity value", String.valueOf(qty));
            Log.d("Rate value", String.valueOf(rate));
            Log.d("Adapter value", String.valueOf(adapterId));
            if(qty>0)
            {
                qty=qty-1;
                total_price-=rate;
                Log.d("New qty", String.valueOf(qty));
                Log.d("New total price", String.valueOf(total_price));
                i.setText(String.valueOf(qty));
                txt_total_price.setText(String.valueOf(total_price));

                Toast.makeText(getActivity(), "Subtracted", Toast.LENGTH_LONG).show();

            }
        }
        else
        {
            Toast.makeText(getActivity(), "No Service Type available!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
