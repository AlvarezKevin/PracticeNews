package example.alvarezkevin.udacitynews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 4/3/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentsTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentsTitle.get(position);
    }

    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitle.add(fragmentTitle);
    }
}
