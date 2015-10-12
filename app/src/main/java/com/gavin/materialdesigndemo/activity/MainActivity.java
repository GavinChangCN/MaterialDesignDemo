package com.gavin.materialdesigndemo.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gavin.materialdesigndemo.Fragment.FragmentDrawer;
import com.gavin.materialdesigndemo.Fragment.FriendsFragment;
import com.gavin.materialdesigndemo.Fragment.HomeFragment;
import com.gavin.materialdesigndemo.Fragment.MessagesFragment;
import com.gavin.materialdesigndemo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static final int HOMEPAGE = 0; // 首页Fragment位置
    private static final int FRIENDSPAGE = 1; // 好友页Fragment位置
    private static final int MESSAGESPAGE = 2; // 消息页Fragment位置


    private AppBarLayout mAppBarLayout;
//    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private FragmentDrawer mFragmentDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.toolbarLayout);
//        mCollapsingToolbarLayout = (CollapsingToolbarLayout) mAppBarLayout.findViewById(R.id.collapsing_toolbar);
//        mToolbarImage = (ImageView) mAppBarLayout.findViewById(R.id.toolbar_image);
        mToolbar = (Toolbar) mAppBarLayout.findViewById(R.id.toolbar);

        int sysVersion = Build.VERSION.SDK_INT; // 用户设备的SDK版本s
        // 版本在5.0以下时通过第三方插件实现状态栏颜色修改
        if (sysVersion < 20) {
            // 设置状态栏颜色
            // 创建状态栏的管理实例
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活（顶部）状态栏设置
            tintManager.setStatusBarTintEnabled(true);
            // 激活（底部）导航栏设置
            tintManager.setNavigationBarTintEnabled(false);
            // 设置一个颜色给系统栏
            tintManager.setTintColor(getResources().getColor(R.color.colorPrimary));

            mAppBarLayout.setFitsSystemWindows(true);
        } else {
            mAppBarLayout.setFitsSystemWindows(false);
        }


        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFragmentDrawer =
                (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        mFragmentDrawer.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        mFragmentDrawer.setDrawerListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // display the first navigation drawer view on app launch
        displayView(HOMEPAGE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // 点击导航抽屉菜单项的选择
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;

        String title = getString(R.string.app_name);

        switch (position) {
            case HOMEPAGE:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case FRIENDSPAGE:
                fragment = new FriendsFragment();
                title = getString(R.string.title_friends);
                break;
            case MESSAGESPAGE:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_toolbar, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
//            mCollapsingToolbarLayout.setTitle(title);
            getSupportActionBar().setTitle(title);
        }
    }
}
