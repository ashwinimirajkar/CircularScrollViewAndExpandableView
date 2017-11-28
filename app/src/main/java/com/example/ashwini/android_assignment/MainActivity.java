package com.example.ashwini.android_assignment;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private ViewPager _viewPager;
    private Handler _autoScrollHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initGridView();
        initViewPager();
        initAutoScroll();
    }

    private void initGridView(){
        GridView mGridview = (GridView) findViewById(R.id.gridview);
        mGridview.setAdapter(new ImageAdapter(this));
    }

    private void initViewPager(){
        _viewPager = (ViewPager) findViewById(R.id._viewPager);

        CardFragmentPagerAdapter mPagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager());

        _viewPager.setAdapter(mPagerAdapter);

        // reset the slider when the ViewPager is scrolled manually to prevent the quick slide after it is scrolled.
        _viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    initAutoScroll();
                } else {
                    if (_autoScrollHandler != null) {
                        _autoScrollHandler.removeCallbacksAndMessages(null);
                        _autoScrollHandler = null;
                    }
                }
                return false;
            }
        });
    }

    private void initAutoScroll() {
        if (_autoScrollHandler == null) _autoScrollHandler = new Handler();
        _autoScrollHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int mCurPos = _viewPager.getCurrentItem();
                mCurPos++;

                if(mCurPos >= CardFragment.mAds.length){
                    mCurPos = 0;
                }
                _viewPager.setCurrentItem(mCurPos, true);
                _autoScrollHandler.postDelayed(this, 3000); // 3 seconds
            }
        }, 3000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (_autoScrollHandler != null) _autoScrollHandler.removeCallbacksAndMessages(null);
    }
}
