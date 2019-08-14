package vn.sefviapp.asm.Fragments;


import android.os.Bundle;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.sefviapp.asm.Adapter.PageAdapter;
import vn.sefviapp.asm.Adapter.PageChiAdapter;
import vn.sefviapp.asm.R;

public class ChiFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chi,null);
        final TabLayout tabLayout = v.findViewById(R.id.main_tabChi);
        final ViewPager viewPager = v.findViewById(R.id.main_viewpagerChi);
        PageChiAdapter pageAdapter = new PageChiAdapter(getChildFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        return v;
    }
}
