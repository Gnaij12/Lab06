package com.example.lab06;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {
    ViewPager2 mViewPager2;
    int position;
    ImageView kirby;
    int[] images = new int[]{R.drawable.kirby1, R.drawable.kirby2,
            R.drawable.kirby3, R.drawable.kirby4,R.drawable.kirby5,R.drawable.kirby6};
    public static Fragment newInstance(ViewPager2 mViewPager2, int position) {
        MainFragment fragment = new MainFragment();
        fragment.mViewPager2 = mViewPager2;
        fragment.position=position;

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TabLayout tabLayout = getActivity().findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout,mViewPager2,
                (tab,position) -> tab.setText("I am " + (position + 1))
        ).attach();
        //access button
        Button mButton = view.findViewById(R.id.pressMe);
        //set text to identify fragment position
        mButton.setText("Press " + position);
        kirby = view.findViewById(R.id.background);
        kirby.setScaleType(ImageView.ScaleType.FIT_XY);
        kirby.setImageResource(images[position]);

    }
}
