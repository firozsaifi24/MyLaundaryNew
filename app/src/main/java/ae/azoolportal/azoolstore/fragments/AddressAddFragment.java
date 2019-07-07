package ae.azoolportal.azoolstore.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class AddressAddFragment extends Fragment implements View.OnClickListener {

    ImageView menu_btn;
    Button btn_save_address;


    LinearLayout home_address_layout, office_address_layout, other_address_layout;
    TextView txt_home_address, txt_office_address, txt_other_address;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddressAddFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddressAddFragment newInstance(String param1, String param2) {
        AddressAddFragment fragment = new AddressAddFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_address_add, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        home_address_layout= view.findViewById(R.id.home_address_layout);
        office_address_layout= view.findViewById(R.id.office_address_layout);
        other_address_layout= view.findViewById(R.id.other_address_layout);
        txt_home_address= view.findViewById(R.id.txt_home_address);
        txt_office_address= view.findViewById(R.id.txt_office_address);
        txt_other_address= view.findViewById(R.id.txt_other_address);
        btn_save_address= view.findViewById(R.id.btn_save_address);

        menu_btn.setOnClickListener(this);
        home_address_layout.setOnClickListener(this);
        office_address_layout.setOnClickListener(this);
        other_address_layout.setOnClickListener(this);
        btn_save_address.setOnClickListener(this);

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

        if(v==home_address_layout)
        {
            home_address_layout.setBackgroundResource(R.drawable.border_select);
            txt_home_address.setTextColor(getResources().getColor(R.color.white));

            office_address_layout.setBackgroundResource(R.drawable.border_unselect);
            other_address_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_office_address.setTextColor(getResources().getColor(R.color.black));
            txt_other_address.setTextColor(getResources().getColor(R.color.black));
        }

        if(v==office_address_layout)
        {
            office_address_layout.setBackgroundResource(R.drawable.border_select);
            txt_office_address.setTextColor(getResources().getColor(R.color.white));

            home_address_layout.setBackgroundResource(R.drawable.border_unselect);
            other_address_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_home_address.setTextColor(getResources().getColor(R.color.black));
            txt_other_address.setTextColor(getResources().getColor(R.color.black));
        }

        if(v==other_address_layout)
        {
            other_address_layout.setBackgroundResource(R.drawable.border_select);
            txt_other_address.setTextColor(getResources().getColor(R.color.white));

            office_address_layout.setBackgroundResource(R.drawable.border_unselect);
            home_address_layout.setBackgroundResource(R.drawable.border_unselect);
            txt_office_address.setTextColor(getResources().getColor(R.color.black));
            txt_home_address.setTextColor(getResources().getColor(R.color.black));
        }

        if(v==btn_save_address)
        {
            if(getActivity()!=null)
            {
                //hide the open keyboard
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(INPUT_METHOD_SERVICE);
                //if keyboard is open then hide else skip
                if(getActivity().getCurrentFocus() !=null)
                {
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                }

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
