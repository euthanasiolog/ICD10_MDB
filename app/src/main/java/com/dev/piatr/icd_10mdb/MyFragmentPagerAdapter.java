package com.dev.piatr.icd_10mdb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.LinkedList;


/**
 * Created by piatr on 01.02.18.
 */

public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private final LinkedList<Fragment> fragmentList = new LinkedList<>();
    private final LinkedList<String> fragmentTitleList = new LinkedList<>();

    public void addFragment(Fragment fragment, String title){
        int count = fragmentList.size();
        switch (count){
            case 0:
                fragmentList.add(fragment);
                fragmentTitleList.add(title);
                break;
            case 1:
                fragmentList.addLast(fragment);
                fragmentTitleList.addLast(title);
                break;
            case 2:
                fragmentList.removeFirst();
                fragmentList.addLast(fragment);
                fragmentTitleList.removeFirst();
                fragmentTitleList.addLast(title);
                break;
        }

    }

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
