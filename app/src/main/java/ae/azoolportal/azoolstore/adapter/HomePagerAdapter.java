package ae.azoolportal.azoolstore.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import ae.azoolportal.azoolstore.fragments.HomePagerFragment;
import ae.azoolportal.azoolstore.interfaces.OnPagerCallback;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<String> bandImages;
    public static boolean isClick = false;
    private OnPagerCallback onPagerCallback = null;

    public HomePagerAdapter(FragmentManager fm, ArrayList<String> bandImages) {
        super(fm);
        this.bandImages = bandImages;
    }

    public HomePagerAdapter(FragmentManager fm, ArrayList<String> bandImages, boolean isClick, OnPagerCallback onPagerCallback) {
        super(fm);
        this.bandImages = bandImages;
        this.isClick = isClick;
        this.onPagerCallback = onPagerCallback;
    }

    @Override
    public Fragment getItem(int position) {
        return new HomePagerFragment().newInstance(bandImages.get(position),String.valueOf(position),onPagerCallback);
    }

    @Override
    public int getCount() {
        return bandImages.size();
    }

}
