package com.sarasapp.sarasapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh on 14/4/16.
 */
public class DiscussionsFragment extends Fragment {
    public static final String ARG_PLANET_NUMBER = "planet_number";
    public ViewPager viewPager1;
    private TabLayout tabLayout1;


    public DiscussionsFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_discussions, container, false);
        getActivity().setTitle("Discussions");

        viewPager1 = (ViewPager) rootView.findViewById(R.id.viewpager1);
        viewPager1.setOffscreenPageLimit(2);
        setupViewPager(viewPager1);

        tabLayout1 = (TabLayout) rootView.findViewById(R.id.tabs1);
        tabLayout1.setupWithViewPager(viewPager1);

        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new DiscussionsJoinedFragment(), "Joined");
        adapter.addFragment(new DiscussionsAllFragment(), "All");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}