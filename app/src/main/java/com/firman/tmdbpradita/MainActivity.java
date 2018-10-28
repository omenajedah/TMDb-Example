package com.firman.tmdbpradita;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager pager;
    private SectionPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        setSupportActionBar(findViewById(R.id.toolbar));
        tabLayout = findViewById(R.id.tabLayout);
        pager = findViewById(R.id.container);
        adapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
//        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        tabLayout.setupWithViewPager(pager);

        adapter.addItem(new PopularFragment(), "Popular");
        adapter.addItem(new NowPlayingFragment(), "NowPlaying");
    }
}
