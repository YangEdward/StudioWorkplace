package com.bestride.fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bestride.waiterwork.R;
import com.github.johnpersano.supertoasts.SuperToast;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

/**
 * Created by Administrator on 2015/1/9.
 */
@EFragment
abstract class BaseFragment extends Fragment{

    protected boolean detach = false;
    protected View emptyView;

    @Override
    public void onStart() {
        super.onStart();
        detach = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        detach = false;
    }
    protected abstract void getAgainListener();

    @UiThread
    void showInformation(String messageInfo,boolean isError) {
        if(!detach){
            return;
        }
        SuperToast superToast = new SuperToast(getActivity());
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

    @UiThread void setEmptyView(int resId,AbsListView floorStatus,boolean isLoading){
        if(getActivity() == null){
            return;
        }
        ViewGroup group = (ViewGroup) floorStatus.getParent();
        if(emptyView != null){
            group.removeView(emptyView);
        }
        emptyView = LayoutInflater.from(getActivity()).
                inflate(resId, null);
        if(resId == R.layout.has_no_data){
            TextView content = (TextView) emptyView.findViewById(R.id.content);
            content.setText(R.string.no_report);
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, 1);
        lp.setMargins(300, 200, 300, 200);
        emptyView.setPadding(300, 200, 300, 200);
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
}
