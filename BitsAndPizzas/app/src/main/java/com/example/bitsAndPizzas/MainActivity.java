package com.example.bitsAndPizzas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), getLifecycle());
        ViewPager2 pager = (ViewPager2) findViewById(R.id.pager);
        //pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(sectionPagerAdapter);
        //pager.setPageTransformer(new MarginPageTransformer(1500));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        new TabLayoutMediator(tabLayout, pager,
                ((tab, position) -> {
                    switch (position){
                        case 0:
                            tab.setText(getResources().getText(R.string.home_tab));
                            break;
                        case 1:
                            tab.setText(getResources().getText(R.string.pizza_tab));
                            break;
                        case 2:
                            tab.setText(getResources().getText(R.string.pasta_tab));
                            break;
                        case 3:
                            tab.setText(getResources().getText(R.string.store_tab));
                            break;
                    }
                })
        ).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu to add it to the app bar
        getMenuInflater().inflate(R.menu.menu, menu);

        //add share menu item
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Want to join me for pizza?");
        return super.onCreateOptionsMenu(menu);
    }

    private void setShareActionIntent(String s) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, s);
        shareActionProvider.setShareIntent(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private class SectionPagerAdapter extends FragmentStateAdapter{

        public SectionPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new TopFragment();
                case 1:
                    return new PizzaFragment();
                case 2:
                    return new PastaFragment();
                case 3:
                    return new StoresFragment();
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}