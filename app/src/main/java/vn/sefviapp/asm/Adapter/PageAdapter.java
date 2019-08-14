package vn.sefviapp.asm.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.sefviapp.asm.Fragments.KhoanChiFragment;
import vn.sefviapp.asm.Fragments.KhoanThuFragment;
import vn.sefviapp.asm.Fragments.LoaiThuFragment;

public class PageAdapter extends FragmentPagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KhoanThuFragment();
            case 1:
                return new LoaiThuFragment();
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
                return "Khoản Thu";
            case 1:
                return "Loại Thu";
            default:
                return null;
        }
    }
}