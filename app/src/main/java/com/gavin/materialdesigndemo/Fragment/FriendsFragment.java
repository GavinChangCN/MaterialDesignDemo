package com.gavin.materialdesigndemo.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.gavin.materialdesigndemo.R;
import com.gavin.materialdesigndemo.adapter.FriendsPagerAdapter;
import com.gavin.materialdesigndemo.widget.PagerSlidingTabStrip;

public class FriendsFragment extends Fragment {

    private PagerSlidingTabStrip mPagerSlidingTabStrip;
    private ViewPager mViewPager;

    public FriendsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends, container, false);
        mPagerSlidingTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabs);
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(new FriendsPagerAdapter(getActivity().getSupportFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mViewPager);
        mPagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
//                colorChange(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        initTabsValue();

        return rootView;
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     *
     */
    private void initTabsValue() {
        // 底部游标颜色
        mPagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
        // tab的分割线颜色
        mPagerSlidingTabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        mPagerSlidingTabStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        // tab底线高度
        mPagerSlidingTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1, getResources().getDisplayMetrics()));
        // 游标高度
        mPagerSlidingTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                5, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        mPagerSlidingTabStrip.setSelectedTextColor(getResources().getColor(R.color.colorAccent));
        // 正常文字颜色
        mPagerSlidingTabStrip.setTextColor(getResources().getColor(R.color.textColorPrimary));
    }

//    /**
//     * 界面颜色的更改
//     */
//    @SuppressLint("NewApi")
//    private void colorChange(int position) {
//        // 用来提取颜色的Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//                SuperAwesomeCardFragment.getBackgroundBitmapPosition(position));
//        // Palette的部分
//        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//            /**
//             * 提取完之后的回调方法
//             */
//            @Override
//            public void onGenerated(Palette palette) {
//                Palette.Swatch vibrant = palette.getVibrantSwatch();
//				/* 界面颜色UI统一性处理,看起来更Material一些 */
//                mPagerSlidingTabStrip.setBackgroundColor(vibrant.getRgb());
//                mPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
//                // 其中状态栏、游标、底部导航栏的颜色需要加深一下，也可以不加，具体情况在代码之后说明
//                mPagerSlidingTabStrip.setIndicatorColor(colorBurn(vibrant.getRgb()));
//
//                mToolbar.setBackgroundColor(vibrant.getRgb());
//                if (android.os.Build.VERSION.SDK_INT >= 21) {
//                    Window window = getWindow();
//                    // 很明显，这两货是新API才有的。
//                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
//                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
//                }
//            }
//        });
//    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues
     *            RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *            Android中我们一般使用它的16进制，
     *            例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *            red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *            所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch (item.getItemId()) {
        // case R.id.action_settings:
        // Toast.makeText(MainActivity.this, "action_settings", 0).show();
        // break;
        // case R.id.action_share:
        // Toast.makeText(MainActivity.this, "action_share", 0).show();
        // break;
        // default:
        // break;
        // }
        return super.onOptionsItemSelected(item);
    }
}