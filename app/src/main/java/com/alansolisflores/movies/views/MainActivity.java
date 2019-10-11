package com.alansolisflores.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.alansolisflores.movies.adapters.SectionsPagerAdapter;
import com.alansolisflores.movies.R;
import com.alansolisflores.movies.presenters.MainPresenter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
        implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeView();
    }

    private void initializeView(){
        this.tabLayout = findViewById(R.id.tabLayout);
        this.viewPager = findViewById(R.id.sectionsViewPager);

        this.setProperties();
    }

    private void setProperties(){
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_trending));
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_podium));
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_event));


        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                this.tabLayout.getTabCount());

        this.viewPager.setAdapter(pagerAdapter);
        this.viewPager
                .addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout));

        this.tabLayout.addOnTabSelectedListener(this);
        getSupportActionBar().setElevation(0);
        this.setToolbarTitle(R.string.popular);

    }

    private void setToolbarTitle(int res){
        getSupportActionBar().setTitle(res);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                this.setToolbarTitle(R.string.popular);
                break;
            case 1:
                this.setToolbarTitle(R.string.top_rated);
                break;
            case 2:
                this.setToolbarTitle(R.string.upcoming);
                break;
            default:
                this.setToolbarTitle(R.string.app_name);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        //Intentionally blank
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //Intentionally blank
    }
}
