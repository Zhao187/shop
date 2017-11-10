package com.zhao.shopapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhao.shopapp.R;
import com.zhao.shopapp.fragment.base.BaseFragment;
import com.zhao.shopapp.util.UIUtils;

import java.util.List;

/**
 * 热门Adapter
 */

public class HotFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragmentList;

    public HotFragmentAdapter(FragmentManager fragmentManager, List<BaseFragment> fragmentList) {
        super(fragmentManager);
        this.fragmentList=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return UIUtils.getStringArr(R.array.remen_tab)[position];
    }
}
