package ae.azoolportal.azoolstore.fragments;

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

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;
import ae.azoolportal.azoolstore.adapter.ClothesDryCleanAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesIronAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesPremiumLaundryAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesReviewAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesWashAndFoldAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesWashAndIronAdapter;
import ae.azoolportal.azoolstore.model.Address;
import ae.azoolportal.azoolstore.model.Clothes;

public class ReviewOrderFragment extends Fragment implements View.OnClickListener {


    ImageView menu_btn;
    Button btn_confirm;
    TextView txt_totalAmount;
    TextView txt_pickup_date, txt_pickup_time, txt_delivery_date, txt_delivery_time;

    //textview pickup address
    TextView p_building, p_street, p_city, p_state, p_country, p_landmark, p_zipcode;
    //textview delivery address
    TextView d_building, d_street, d_city, d_state, d_country, d_landmark, d_zipcode;

    //Received selected list for review order
    private ArrayList<Clothes> washandfold_list = new ArrayList<>();
    private ArrayList<Clothes> washandiron_list = new ArrayList<>();
    private ArrayList<Clothes> iron_list = new ArrayList<>();
    private ArrayList<Clothes> premiumlaundry_list = new ArrayList<>();
    private ArrayList<Clothes> drycleaning_list = new ArrayList<>();
    long totalPrice;

    //Received data from previous fragment
    String pickupDate;
    String pickupTime;
    String deliveryDate;
    String deliveryTime;
    String note;
    private ArrayList<Address> selectedAddress = new ArrayList<>();

    //recyclerview
    RecyclerView recyclerView_washandfold;
    RecyclerView recyclerView_iron;
    RecyclerView recyclerView_washandiron;
    RecyclerView recyclerView_premium_laundry;
    RecyclerView recyclerView_dryclean;

    private ClothesReviewAdapter clothesReviewAdapter;

    LinearLayout washandfold_layout, iron_layout, washandiron_layout, premiumlaundry_layout, dryclean_layout;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ReviewOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewOrderFragment newInstance(String param1, String param2) {
        ReviewOrderFragment fragment = new ReviewOrderFragment();
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
            washandfold_list= getArguments().getParcelableArrayList("WashAndFoldList");
            washandiron_list= getArguments().getParcelableArrayList("WashAndIronList");
            iron_list= getArguments().getParcelableArrayList("IronList");
            premiumlaundry_list= getArguments().getParcelableArrayList("PremiumLaundryList");
            drycleaning_list= getArguments().getParcelableArrayList("DryCleaningList");
            totalPrice= getArguments().getLong("TotalAmount");

            //date time and address data
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
            /*Bundle extras = getIntent().getExtras();
            ArrayList<ObjectName> arraylist  = extras.getParcelableArrayList("arraylist");*/

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_review_order, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        txt_totalAmount= view.findViewById(R.id.txt_total_amount);
        txt_pickup_date= view.findViewById(R.id.txt_pickup_date);
        txt_pickup_time= view.findViewById(R.id.txt_pickup_time);
        txt_delivery_date= view.findViewById(R.id.txt_delivery_date);
        txt_delivery_time= view.findViewById(R.id.txt_delivery_time);
        btn_confirm= view.findViewById(R.id.btn_confirm);

        p_building= view.findViewById(R.id.txt_building_name_number_pickup);
        p_street= view.findViewById(R.id.txt_street_pickup);
        p_city= view.findViewById(R.id.txt_city_pickup);
        p_state= view.findViewById(R.id.txt_state_pickup);
        p_country= view.findViewById(R.id.txt_country_pickup);
        p_landmark= view.findViewById(R.id.txt_landmark_pickup);
        p_zipcode= view.findViewById(R.id.txt_zipcode_pickup);
        //textview delivery address
        d_building= view.findViewById(R.id.txt_building_name_number_delivery);
        d_street= view.findViewById(R.id.txt_street_delivery);
        d_city= view.findViewById(R.id.txt_city_delivery);
        d_state= view.findViewById(R.id.txt_state_delivery);
        d_country= view.findViewById(R.id.txt_country_delivery);
        d_landmark= view.findViewById(R.id.txt_landmark_delivery);
        d_zipcode= view.findViewById(R.id.txt_zipcode_delivery);


        washandfold_layout= view.findViewById(R.id.washandfold_layout);
        washandiron_layout= view.findViewById(R.id.washandiron_layout);
        iron_layout= view.findViewById(R.id.iron_layout);
        premiumlaundry_layout= view.findViewById(R.id.premiumlaundry_layout);
        dryclean_layout= view.findViewById(R.id.dryclean_layout);

        recyclerView_washandfold=view.findViewById(R.id.recycler_home_washandfold);
        recyclerView_washandiron=view.findViewById(R.id.recycler_home_washandiron);
        recyclerView_iron=view.findViewById(R.id.recycler_home_iron);
        recyclerView_premium_laundry=view.findViewById(R.id.recycler_home_premium_laundary);
        recyclerView_dryclean=view.findViewById(R.id.recycler_home_dryclean);

        Log.d("myArray1", washandfold_list.toString());
        Log.d("myArray2", washandiron_list.toString());
        Log.d("myArray3", iron_list.toString());
        Log.d("myArray4", premiumlaundry_list.toString());
        Log.d("myArray5", drycleaning_list.toString());
        Log.d("Total Amount", String.valueOf(totalPrice));
        Log.d("myArray1 Size", String.valueOf(washandfold_list.size()));
        Log.d("myArray2 Size", String.valueOf(washandiron_list.size()));
        Log.d("myArray3 Size", String.valueOf(iron_list.size()));
        Log.d("myArray4 Size", String.valueOf(premiumlaundry_list.size()));
        Log.d("myArray5 Size", String.valueOf(drycleaning_list.size()));

        menu_btn.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);

        if(washandfold_list.size()>0)
        {
            //recycler for washandfold
            GridLayoutManager adsGridLayoutManager= new GridLayoutManager(getActivity(),1);
            recyclerView_washandfold.setLayoutManager(adsGridLayoutManager);
            recyclerView_washandfold.setScrollContainer(false);
            recyclerView_washandfold.setNestedScrollingEnabled(false);

            clothesReviewAdapter= new ClothesReviewAdapter(getActivity(), washandfold_list);
            recyclerView_washandfold.setAdapter(clothesReviewAdapter);

            washandfold_layout.setVisibility(View.VISIBLE);
            //recyclerView_washandfold.setVisibility(View.VISIBLE);
            //Toast.makeText(getActivity(),"Showing Home Ads", Toast.LENGTH_LONG).show();
        }

        if(washandiron_list.size()>0)
        {
            //recycler for washandiron
            GridLayoutManager adsGridLayoutManager3= new GridLayoutManager(getActivity(),1);
            recyclerView_washandiron.setLayoutManager(adsGridLayoutManager3);
            recyclerView_washandiron.setScrollContainer(false);
            recyclerView_washandiron.setNestedScrollingEnabled(false);

            clothesReviewAdapter= new ClothesReviewAdapter(getActivity(), washandiron_list);
            recyclerView_washandiron.setAdapter(clothesReviewAdapter);

            washandiron_layout.setVisibility(View.VISIBLE);
            //recyclerView_washandiron.setVisibility(View.VISIBLE);
        }

        if(iron_list.size()>0)
        {
            //recycler for iron
            GridLayoutManager adsGridLayoutManager2= new GridLayoutManager(getActivity(),1);
            recyclerView_iron.setLayoutManager(adsGridLayoutManager2);
            recyclerView_iron.setScrollContainer(false);
            recyclerView_iron.setNestedScrollingEnabled(false);

            clothesReviewAdapter= new ClothesReviewAdapter(getActivity(), iron_list);
            recyclerView_iron.setAdapter(clothesReviewAdapter);

            iron_layout.setVisibility(View.VISIBLE);
            //recyclerView_iron.setVisibility(View.VISIBLE);
        }

        if(premiumlaundry_list.size()>0)
        {
            //recycler for premium laundry
            GridLayoutManager adsGridLayoutManager4= new GridLayoutManager(getActivity(),1);
            recyclerView_premium_laundry.setLayoutManager(adsGridLayoutManager4);
            recyclerView_premium_laundry.setScrollContainer(false);
            recyclerView_premium_laundry.setNestedScrollingEnabled(false);

            clothesReviewAdapter= new ClothesReviewAdapter(getActivity(), premiumlaundry_list);
            recyclerView_premium_laundry.setAdapter(clothesReviewAdapter);

            premiumlaundry_layout.setVisibility(View.VISIBLE);
            //recyclerView_premium_laundry.setVisibility(View.GONE);
        }

        if(drycleaning_list.size()>0)
        {

            //recycler for dry clean
            GridLayoutManager adsGridLayoutManager5= new GridLayoutManager(getActivity(),1);
            recyclerView_dryclean.setLayoutManager(adsGridLayoutManager5);
            recyclerView_dryclean.setScrollContainer(false);
            recyclerView_dryclean.setNestedScrollingEnabled(false);

            clothesReviewAdapter= new ClothesReviewAdapter(getActivity(), drycleaning_list);
            recyclerView_dryclean.setAdapter(clothesReviewAdapter);

            dryclean_layout.setVisibility(View.VISIBLE);
            //recyclerView_dryclean.setVisibility(View.VISIBLE);
        }

        txt_totalAmount.setText(String.valueOf(totalPrice));

        txt_pickup_date.setText(pickupDate);
        txt_pickup_time.setText(pickupTime);
        txt_delivery_date.setText(deliveryDate);
        txt_delivery_time.setText(deliveryTime);

        if(selectedAddress!=null)
        {
            String addressType= selectedAddress.get(0).getAddressType();
            String buildingName= selectedAddress.get(0).getBuldingNameAndNumber();
            String street= selectedAddress.get(0).getStreet();
            String landmark= selectedAddress.get(0).getLandmark();
            String city= selectedAddress.get(0).getCity();
            String state= selectedAddress.get(0).getState();
            String country= selectedAddress.get(0).getCountry();
            String zipCode= selectedAddress.get(0).getZipCode();


            p_building.setText(buildingName);
            p_street.setText(street);
            p_city.setText(city);
            p_state.setText(state);
            p_country.setText(country);
            p_landmark.setText(landmark);
            p_zipcode.setText(zipCode);
            //textview delivery address
            d_building.setText(buildingName);
            d_street.setText(street);
            d_city.setText(city);
            d_state.setText(state);
            d_country.setText(country);
            d_landmark.setText(landmark);
            d_zipcode.setText(zipCode);


        }

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

        if(v==btn_confirm)
        {
            if(getActivity()!=null)
            {
                try
                {
                    MainActivity mainActivity= (MainActivity)getActivity();
                    mainActivity.orderPlacedSuccess();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
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
