package vn.sefviapp.asm.Fragments;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.sefviapp.asm.Adapter.ThongKEAdapter;

import vn.sefviapp.asm.Fragments.ThongKe.HomNayFragment;
import vn.sefviapp.asm.R;


public class ThongKeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_thong_ke, null);
        TabLayout tabLayout = v.findViewById(R.id.main_tabThongKe);
        final ViewPager viewPager = v.findViewById(R.id.main_viewpagerThongKe);
        ThongKEAdapter thongKEAdapter = new ThongKEAdapter(getChildFragmentManager());
        viewPager.setAdapter(thongKEAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        return v;
    }

}
