package app.com.slidingscreens.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.com.slidingscreens.Fragment.ScreenFourFragment;
import app.com.slidingscreens.Fragment.ScreenOneFragment;
import app.com.slidingscreens.Fragment.ScreenThreeFragment;
import app.com.slidingscreens.Fragment.ScreenTwoFragment;
import app.com.slidingscreens.R;

public class SlideActivity extends AppCompatActivity {

    private LinearLayout dotsLayout;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int[] layouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        layouts = new int[] {
                R.layout.fragment_screen_one, R.layout.fragment_screen_two,
                R.layout.fragment_screen_three, R.layout.fragment_screen_four};

        addBottomDots(0);

        fragmentList.add(Fragment.instantiate(this, ScreenOneFragment.class.getName()));
        fragmentList.add(Fragment.instantiate(this, ScreenTwoFragment.class.getName()));
        fragmentList.add(Fragment.instantiate(this, ScreenThreeFragment.class.getName()));
        fragmentList.add(Fragment.instantiate(this, ScreenFourFragment.class.getName()));

        viewPager.setAdapter(new SamplePagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                addBottomDots(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addBottomDots(int currentPage) {
        TextView[] dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private class SamplePagerAdapter extends FragmentPagerAdapter {

        List<Fragment> fragmentList;

        SamplePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ScreenOneFragment();
                case 1:
                    return new ScreenTwoFragment();
                case 2:
                    return new ScreenThreeFragment();
                default:
                    return new ScreenFourFragment();
            }
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}