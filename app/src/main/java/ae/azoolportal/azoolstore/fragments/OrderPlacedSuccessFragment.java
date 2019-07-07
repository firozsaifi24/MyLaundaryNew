package ae.azoolportal.azoolstore.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.activity.MainActivity;


public class OrderPlacedSuccessFragment extends Fragment implements View.OnClickListener {

    ImageView menu_btn;

    Button btn_home, btn_track;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public OrderPlacedSuccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderPlacedSuccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderPlacedSuccessFragment newInstance(String param1, String param2) {
        OrderPlacedSuccessFragment fragment = new OrderPlacedSuccessFragment();
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
        View view= inflater.inflate(R.layout.fragment_order_placed_success, container, false);
        menu_btn= view.findViewById(R.id.menu_btn);
        btn_home= view.findViewById(R.id.btn_home);
        btn_track= view.findViewById(R.id.btn_track);

        menu_btn.setOnClickListener(this);
        btn_home.setOnClickListener(this);
        btn_track.setOnClickListener(this);


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
                    mainActivity.home();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(v== btn_home)
        {
            if(getActivity()!=null)
            {
                try
                {
                    MainActivity mainActivity= (MainActivity)getActivity();
                    mainActivity.home();

                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        if(v== btn_track)
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
