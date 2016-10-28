package sure.com.mymaterialdesigndemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import sure.com.mymaterialdesigndemo.ui.DemoFragment;

/**
 * 描述：
 * 创建人：ShuoLi
 * 创建时间：2016/10/27 15:54
 */
public class ViewPagAdapter extends FragmentPagerAdapter {


    public ViewPagAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new DemoFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "title";
    }
}
