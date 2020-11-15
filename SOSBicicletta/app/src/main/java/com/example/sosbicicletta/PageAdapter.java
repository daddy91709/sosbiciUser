package com.example.sosbicicletta;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mFargmentList;
    ArrayList<String>mFragmentTitleList;

    PageAdapter(FragmentManager fmng){
        super(fmng);
        mFargmentList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        return mFargmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFargmentList.size();
    }


    public void addFragment(Fragment fragment,String title)
    {
        mFargmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    //======== this method is precaution for androidX ======
    //======== while tabs text not displaying android 9+ =====
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
