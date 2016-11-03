package com.tt.tcustomview;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tt.tcustomview.fragment.BuildingFragment;
import com.tt.tcustomview.fragment.CustomScrollViewFragment;
import com.tt.tcustomview.fragment.GlideDemoFragment;
import com.tt.tcustomview.fragment.RandomNumberFragment;
import com.tt.tcustomview.fragment.RightSlideClosedFragment;
import com.tt.tcustomview.fragment.WelComeFragment;
import com.tt.tcustomview.utils.PageName;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        setFragment();
    }

    private void setFragment() {
        Intent intent = getIntent();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment;
        int page = intent.getIntExtra("page", PageName.WELCOME);
        switch(page) {
            case PageName.WELCOME:
                fragment = new WelComeFragment();
                break;
            case PageName.RANDOM_NUMBER:
                fragment = new RandomNumberFragment();
                break;
            case PageName.GLIDE_DEMO:
                fragment = new GlideDemoFragment();
                break;
            case PageName.CUSTOM_SCROLLVIEW_DEMO:
                fragment = new CustomScrollViewFragment();
                break;
            case PageName.RightSlideClosedView:
                fragment = new RightSlideClosedFragment();
                break;
            default:
                fragment = new BuildingFragment();
                break;
        }
        transaction.replace(R.id.activity_custom_view, fragment);
        transaction.commit();
    }

}
