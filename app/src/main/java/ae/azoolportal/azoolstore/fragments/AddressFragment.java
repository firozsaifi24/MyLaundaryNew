package ae.azoolportal.azoolstore.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;
import ae.azoolportal.azoolstore.adapter.AddressAdapter;
import ae.azoolportal.azoolstore.adapter.ClothesWashAndFoldAdapter;
import ae.azoolportal.azoolstore.interfaces.OnAddressDeleteCallback;
import ae.azoolportal.azoolstore.interfaces.OnAddressEditCallback;
import ae.azoolportal.azoolstore.interfaces.OnAddressSelectionCallback;
import ae.azoolportal.azoolstore.model.Address;
import ae.azoolportal.azoolstore.model.Clothes;


public class AddressFragment extends Fragment implements View.OnClickListener, OnAddressEditCallback, OnAddressDeleteCallback, OnAddressSelectionCallback {

    ImageView menu_btn;
    DrawerLayout mDrawer;

    RelativeLayout add_address_layout;

    ArrayList<Address> addressList;
    AddressAdapter addressAdapter;
    RecyclerView recyclerView_address;
    int selectedAdapterPosition;
    ArrayList<Address> selectedAddress;


    Button btn_next;


    //Received data from previous fragment
    private ArrayList<Clothes> washandfold_list = new ArrayList<>();
    private ArrayList<Clothes> washandiron_list = new ArrayList<>();
    private ArrayList<Clothes> iron_list = new ArrayList<>();
    private ArrayList<Clothes> premiumlaundry_list = new ArrayList<>();
    private ArrayList<Clothes> drycleaning_list = new ArrayList<>();
    long totalPrice;

    String pickupDate;
    String pickupTime;
    String deliveryDate;
    String deliveryTime;
    String note;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddressFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddressFragment newInstance(String param1, String param2) {
        AddressFragment fragment = new AddressFragment();
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

            pickupDate= getArguments().getString("PickupDate");
            pickupTime= getArguments().getString("PickupTime");
            deliveryDate= getArguments().getString("DeliveryDate");
            deliveryTime= getArguments().getString("DeliveryTime");
            note= getArguments().getString("Note");

            Log.d("Pickup Date", pickupDate);
            Log.d("Pickup Time", pickupTime);
            Log.d("Delivery Date", deliveryDate);
            Log.d("Delivery Time", deliveryTime);
            Log.d("Note", note);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_address, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        add_address_layout= view.findViewById(R.id.add_address_layout);
        btn_next= view.findViewById(R.id.btn_next);
        recyclerView_address= view.findViewById(R.id.recycler_address);

        //Blocked swipe navigation drawer
        mDrawer = (DrawerLayout) this.getActivity().findViewById(R.id.drawer_layout);
        mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);


        menu_btn.setOnClickListener(this);
        add_address_layout.setOnClickListener(this);
        btn_next.setOnClickListener(this);

        addressList= new ArrayList<>();
        selectedAdapterPosition= -1;
        selectedAddress= new ArrayList<>();

        for(int i=0; i<=5; i++)
        {
            Address add= new Address();
            add.setAddressType("Home "+i);
            add.setBuldingNameAndNumber("Flat no. 403, Sai Apartment "+ i);
            add.setStreet("Dangat Nagar, Shivane "+ i);
            add.setLandmark("Landmark "+i);
            add.setCity("City "+ i);
            add.setState("State "+i);
            add.setCountry("India "+i);
            add.setZipCode("000111 "+i);

            addressList.add(add);
        }

        Log.d("Address List", String.valueOf(addressList.size()));


        GridLayoutManager adsGridLayoutManager= new GridLayoutManager(getActivity(),1);
        recyclerView_address.setLayoutManager(adsGridLayoutManager);
        recyclerView_address.setScrollContainer(false);
        recyclerView_address.setNestedScrollingEnabled(false);

        addressAdapter= new AddressAdapter(getActivity(), addressList, this, this, this);
        recyclerView_address.setAdapter(addressAdapter);

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

        if(v==menu_btn)
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

        if(v==add_address_layout)
        {
            if(getActivity()!=null)
            {
                try
                {
                    MainActivity mainActivity= (MainActivity)getActivity();
                    mainActivity.addAddress();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(v==btn_next)
        {
            if(getActivity()!=null)
            {

                if(selectedAddress.size()!=0)
                {
                    try
                    {
                        MainActivity mainActivity= (MainActivity)getActivity();
                        mainActivity.reviewOrder(washandfold_list, washandiron_list, iron_list, premiumlaundry_list, drycleaning_list, totalPrice, pickupDate, pickupTime, deliveryDate, deliveryTime, note, selectedAddress);

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Toast.makeText(getActivity(), "Please select address", Toast.LENGTH_LONG).show();
                }
            }
        }


    }

    @Override
    public void onAddressDeleteSuccess(String addressId, int adapterId) {
        //Log.d("Address id", addressId);
        Log.d("Adapter id", String.valueOf(adapterId));
        Toast.makeText(getActivity(), "Address deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddressEditSuccess(String addressId, int adapterId) {
        //Log.d("Address id", addressId);
        Log.d("Adapter id", String.valueOf(adapterId));
        Toast.makeText(getActivity(), "Address edited", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddressSelectionSuccess(String addressId, int adapterId) {
        //Log.d("Address id", addressId);
        Log.d("Adapter id", String.valueOf(adapterId));

        if(selectedAdapterPosition!=-1)
        {
            ImageView selectedAddressDot= recyclerView_address.findViewHolderForAdapterPosition(selectedAdapterPosition).itemView.findViewById(R.id.img_selection_dot);
            selectedAddressDot.setBackgroundResource(R.drawable.border_dot);
            selectedAddressDot.setImageResource(0);
        }

            selectedAdapterPosition= adapterId;
            ImageView selectedAddressDot= recyclerView_address.findViewHolderForAdapterPosition(adapterId).itemView.findViewById(R.id.img_selection_dot);
            selectedAddressDot.setBackgroundResource(R.drawable.border_dot_red);
            selectedAddressDot.setImageResource(R.drawable.ic_dot_black_24dp);

            selectedAddress.clear();
            Address add= new Address();
            add.setAddressType(addressList.get(adapterId).getAddressType());
            add.setBuldingNameAndNumber(addressList.get(adapterId).getBuldingNameAndNumber());
            add.setStreet(addressList.get(adapterId).getStreet());
            add.setLandmark(addressList.get(adapterId).getLandmark());
            add.setCity(addressList.get(adapterId).getCity());
            add.setState(addressList.get(adapterId).getState());
            add.setCountry(addressList.get(adapterId).getCountry());
            add.setZipCode(addressList.get(adapterId).getZipCode());

            selectedAddress.add(add);

            Log.d("Selected Address", String.valueOf(selectedAddress.size()));

            Toast.makeText(getActivity(), "Address selected", Toast.LENGTH_LONG).show();
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
