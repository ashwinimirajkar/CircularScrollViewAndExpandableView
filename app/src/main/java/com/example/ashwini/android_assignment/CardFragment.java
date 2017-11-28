package com.example.ashwini.android_assignment;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class CardFragment extends Fragment {

    private static int _currentAd = 0;

    public static Fragment getInstance(int position) {
        CardFragment mCardFragment = new CardFragment();
        return mCardFragment;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.item_viewpager, container, false);

        ImageView mImage = (ImageView) mView.findViewById(R.id.image);

        mImage.setImageResource(mAds[_currentAd]);

        _currentAd = _currentAd + 1;

        if(_currentAd == mAds.length) {
            _currentAd = 0;
        }

        return mView;
    }

    public static Integer[] mAds = {
            R.drawable.ads,
            R.drawable.ads1,
            R.drawable.ads2,
            R.drawable.ads3,
            R.drawable.ads4,
            R.drawable.ads5,
            R.drawable.ads6
    };
}
