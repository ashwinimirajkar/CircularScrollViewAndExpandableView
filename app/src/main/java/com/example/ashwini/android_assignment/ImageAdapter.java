package com.example.ashwini.android_assignment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by ashwini on 24/11/17.
 */

public class ImageAdapter extends BaseAdapter {

    private static boolean _isMoreLessIconClicked = false;
    private Context _context;

    public ImageAdapter(Context c) {
        _context = c;
    }

    public static void moreLessIconClicked() {
        _isMoreLessIconClicked = true;
    }

    public static void resetMoreLessIconClick(){
        _isMoreLessIconClicked = false;
    }

    public static boolean isMoreLessIconClicked(){
        return _isMoreLessIconClicked;
    }

    private boolean isMoreIconVisible(){
        if(mThumbIds[2] == R.drawable.more_icon) {
            return true;
        }
        return false;
    }

    private void shrinkParent(View originalView, ViewGroup originalParent){
        ImageView mView = (ImageView) originalView;
        GridView mParent = (GridView) originalParent;

        int mHeight = mView.getHeight();
        mParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mHeight));
    }

    private void expandParent(View originalView, ViewGroup originalParent){

        ImageView mView = (ImageView) originalView;
        GridView mParent = (GridView) originalParent;

        int mHeight = mView.getHeight();
        int mTotalheight=0;
        int mChildItemCount = mParent.getCount();

        if(mChildItemCount % 3 == 0){
            mTotalheight = (mChildItemCount/3) * mHeight;

        }else{
            mTotalheight = ((mChildItemCount/3)+1) * mHeight;
        }

        mParent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, mTotalheight));

    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, final ViewGroup parent) {
        ImageView mImageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            mImageView = new ImageView(_context);
            mImageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            mImageView.setBackgroundColor(Color.parseColor("#ffffff"));
            mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mImageView.setPadding(8, 8, 8, 8);

            // Check for position can be done in Onclick method if we want to define events on multiple icons
            if(position == 2) {
                mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        moreLessIconClicked();
                        if(isMoreIconVisible()){
                            expandParent(view, parent);
                        } else {
                            shrinkParent(view, parent);
                        }
                    }
                });
            }

        } else {

            if(isMoreLessIconClicked()){

                if(mThumbIds[2] == R.drawable.more_icon) {
                    mThumbIds[2] = R.drawable.less_icon;
                } else {
                    mThumbIds[2] = R.drawable.more_icon;
                }
                resetMoreLessIconClick();
            }

            mImageView = (ImageView) convertView;
        }

        mImageView.setImageResource(mThumbIds[position]);
        return mImageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.bakery_and_sweets,
            R.drawable.fruits_and_vegetables,
            R.drawable.more_icon,
            R.drawable.electronics,
            R.drawable.pet_care,
            R.drawable.grocery,
            R.drawable.specials,
            R.drawable.flower,
            R.drawable.home_electricals,
            R.drawable.fruits_and_vegetables
    };

}
