package com.bestride.waiterwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.bestride.fragment.MyWorkFragment_;
import com.bestride.fragment.ReportFragment_;
import com.bestride.helper.FinalValue;
import com.bestride.pageindicator.IconPagerAdapter;
import com.bestride.pageindicator.TabPageIndicator;
import com.bestride.view.BadgeView;
import com.github.johnpersano.supertoasts.SuperToast;
import com.ygledward.ion.Ion;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NoTitle;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@NoTitle
@EActivity(R.layout.main)
public class MainActivity extends FragmentActivity{

    @ViewById ViewPager pager;
    @ViewById static TabPageIndicator indicator;
    private static final String[] CONTENT = new String[] {
            "我的工作","报吧",
    };
    private static final int[] ICONS = new int[] {
            R.drawable.my_work,R.drawable.report
    };

    @AfterViews
    void initViews(){

        FragmentPagerAdapter adapter = new WorkAdapter(getSupportFragmentManager());
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setBackgroundColor(getResources().getColor(R.color.tab_title_color));
        indicator.setViewPager(pager);
        setInformationTip(new BadgeView(this),"10");
    }

    public static void setInformationTip(BadgeView badge,String value){
        badge.setTargetView(indicator.getTabView(0));
        badge.setText(value);
    }

    class WorkAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        public WorkAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            if(position == 0){
                fragment = new MyWorkFragment_();
            }else{
                fragment = new ReportFragment_();
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length];
        }

        @Override public int getIconResId(int index) {
            return ICONS[index];
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > FinalValue.EXIT_TIME) {
            showInformation(getString(R.string.please_back_again), false);
            exitTime = System.currentTimeMillis();
        } else {
            Ion.getDefault(this).getCookieMiddleware().clear();
            super.onBackPressed();
        }
    }

    @UiThread
    void showInformation(String messageInfo,boolean isError) {
        SuperToast superToast = new SuperToast(this);
        superToast.setAnimations(SuperToast.Animations.FLYIN);
        superToast.setDuration(SuperToast.Duration.SHORT);
        if(isError){
            superToast.setBackground(SuperToast.Background.RED);
        }else{
            superToast.setBackground(SuperToast.Background.BLUE);
        }
        superToast.setTextSize(SuperToast.TextSize.LARGE);
        superToast.setText(messageInfo);
        superToast.show();
    }
}
