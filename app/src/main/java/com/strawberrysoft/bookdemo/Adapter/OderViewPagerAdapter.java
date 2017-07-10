package com.strawberrysoft.bookdemo.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.strawberrysoft.bookdemo.Fragment.Oder1Fragment;
import com.strawberrysoft.bookdemo.Fragment.Oder2Fragment;
import com.strawberrysoft.bookdemo.Fragment.Oder3Fragment;
import com.strawberrysoft.bookdemo.Fragment.Oder4Fragment;

/**
 * Created by Administrator on 2016/8/21.
 */
public class OderViewPagerAdapter extends FragmentPagerAdapter {

    private  Context context;
    private Fragment[] fragments = {new Oder1Fragment(), new Oder2Fragment(), new Oder3Fragment(), new Oder4Fragment()};

    public OderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }
}
