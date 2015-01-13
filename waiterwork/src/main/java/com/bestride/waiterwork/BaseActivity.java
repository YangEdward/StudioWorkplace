package com.bestride.waiterwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity
abstract class BaseActivity extends Activity{

    protected View emptyView;
    protected abstract void getAgainListener();

    @UiThread void setEmptyView(int resId,AbsListView floorStatus,boolean isLoading){
        ViewGroup group = (ViewGroup) floorStatus.getParent();
        if(emptyView != null){
            group.removeView(emptyView);
        }
        emptyView = LayoutInflater.from(this).
                inflate(resId, null);
        if(resId == R.layout.has_no_data){
            TextView content = (TextView) emptyView.findViewById(R.id.content);
            content.setText(R.string.no_report);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        lp.setMargins(300, 200, 300, 200);
        group.addView(emptyView,lp);
        floorStatus.setEmptyView(emptyView);
        if(!isLoading){
            emptyView.findViewById(R.id.getAgain).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getAgainListener();
                }
            });
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
        findViewById(R.id.login).setEnabled(true);
    }
}
