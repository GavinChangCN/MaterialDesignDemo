package com.gavin.materialdesigndemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gavin.materialdesigndemo.Fragment.SuperAwesomeCardFragment;

/**
 * Author: Gavin
 * E-mail: gavin.zhang@healthbok.com
 * Date:  2015/10/10 0010
 */
public class FriendsPagerAdapter extends FragmentPagerAdapter {

    public static final String[] TITLES = {
            "全部", "密友", "亲友", "同学", "同事", "合作伙伴", "老师", "黑名单"
    };

    public FriendsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return SuperAwesomeCardFragment.newInstance(position);
    }
}
