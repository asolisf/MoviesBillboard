package com.alansolisflores.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.alansolisflores.movies.adapters.SectionsPagerAdapter;
import com.alansolisflores.movies.R;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    private void setProperties(){
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_flame));
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_star));
        this.tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_event));


        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                this.tabLayout.getTabCount());

        this.viewPager.setAdapter(pagerAdapter);
        this.viewPager
                .addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout));

        this.tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        this.viewPager.setCurrentItem(tab.getPosition());
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
