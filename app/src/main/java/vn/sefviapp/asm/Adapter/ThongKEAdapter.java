package vn.sefviapp.asm.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.sefviapp.asm.Fragments.ThongKe.HomNayFragment;
import vn.sefviapp.asm.Fragments.ThongKe.NamFragment;
import vn.sefviapp.asm.Fragments.ThongKe.ThangFragment;

public class ThongKEAdapter extends FragmentPagerAdapter {
    public ThongKEAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomNayFragment();
            case 1:
                return new ThangFragment();
            case 2:
                return new NamFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Hôm nay";
            case 1:
                return "Tháng";
            case 2:
                return "Năm";
            default:
                return null;
        }
    }
}
