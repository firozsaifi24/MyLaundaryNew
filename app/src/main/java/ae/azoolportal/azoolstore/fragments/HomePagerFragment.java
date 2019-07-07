package ae.azoolportal.azoolstore.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ae.azoolportal.azoolstore.R;
import ae.azoolportal.azoolstore.interfaces.OnPagerCallback;

public class HomePagerFragment extends Fragment {

    ImageView img_pager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "status";
    private static final String ARG_PARAM3 = "position";
    private static final String ARG_PARAM4 = "callback";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String url;
    private String position;
    public static OnPagerCallback onPagerCallback;

    private OnFragmentInteractionListener mListener;

    public HomePagerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomePagerFragment newInstance(String url, String position, OnPagerCallback callback) {
        Log.e("calling hpager fragmenu",url);
        Log.e("calling hpager fragmenp",position);

        HomePagerFragment fragment = new HomePagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        args.putString(ARG_PARAM3, position);
        fragment.setArguments(args);
        onPagerCallback = callback;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_PARAM1);
            position = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_pager, container, false);
        img_pager= view.findViewById(R.id.img_pager_home);

        Log.e("Home pager fragment","in homepagerfragment");

        img_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPagerCallback!=null)
                    onPagerCallback.onBannerClick(position);
            }
        });

        if (url != null){
            Log.e("URL testing",url);

            //Picasso.with(getActivity()).load(url).into(img_pager);
            //String a="http://hdcarwallpapers.com/thumbs/2018/2018_aston_martin_db11_v8_volante_4k-t2.jpg";
            //String b="http://images.pexels.com/photos/707046/pexels-photo-707046.jpeg";
            //String c="https://azool.ae/multimedia/banner/rh_69771_1524894929.jpg";
            //Picasso.with(getActivity()).load(url).into(img_pager);
            Picasso.get().load(url).fit().into(img_pager);
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
