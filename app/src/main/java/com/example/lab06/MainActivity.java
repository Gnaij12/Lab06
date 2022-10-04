package com.example.lab06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ViewPager2 mViewPager2;
    RecyclerView.Adapter mFragStateAdaptor;
    int NUM_ITEMS = 6;
    int[] images;
    Context context;
    MediaPlayer mediaPlayer ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assign instance of ViewPager2
        mViewPager2 = findViewById(R.id.container);
        context= getApplicationContext();
        mediaPlayer = MediaPlayer.create(context, R.raw.scroll);
        mViewPager2.setPageTransformer(new DepthPageTransformer());
        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                mediaPlayer.start();
            }
        });
        //create ViewPager2 adaptor
        mFragStateAdaptor = new FragmentStateAdapter(this);
        //set adapter for ViewPager
        mViewPager2.setAdapter(mFragStateAdaptor);
    }

    private class FragmentStateAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {

        public FragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(mViewPager2,position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}