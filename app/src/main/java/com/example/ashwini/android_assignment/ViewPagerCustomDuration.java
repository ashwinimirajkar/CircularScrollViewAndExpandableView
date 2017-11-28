package com.example.ashwini.android_assignment;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by ashwini on 26/11/17.
 */

public class ViewPagerCustomDuration extends ViewPager {
    private FixedSpeedScroller _scroller = null;

    public ViewPagerCustomDuration(Context context) {
        super(context);
        init();
    }

    public ViewPagerCustomDuration(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        try {
            Class<?> mViewpager = ViewPager.class;
            Field mScroller = mViewpager.getDeclaredField("_scroller");
            mScroller.setAccessible(true);
            _scroller = new FixedSpeedScroller(getContext(),
                    new DecelerateInterpolator());
            mScroller.set(this, _scroller);
        } catch (Exception ignored) {
        }
    }

    private class FixedSpeedScroller extends Scroller {

        private int mDuration = 200;

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }
}