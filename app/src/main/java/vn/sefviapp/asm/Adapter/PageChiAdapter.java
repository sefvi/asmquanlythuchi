package vn.sefviapp.asm.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import vn.sefviapp.asm.Fragments.KhoanChiFragment;
import vn.sefviapp.asm.Fragments.LoaiChiFragment;
import vn.sefviapp.asm.Fragments.LoaiThuFragment;

public class PageChiAdapter extends FragmentPagerAdapter {
    public PageChiAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KhoanChiFragment();
            case 1:
                return new LoaiChiFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Khoản Chi";
            case 1:
                return "Loại Chi";
            default:
                return null;
        }
    }
}
