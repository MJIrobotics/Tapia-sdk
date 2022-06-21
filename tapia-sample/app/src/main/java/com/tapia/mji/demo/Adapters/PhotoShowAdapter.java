package com.tapia.mji.demo.Adapters;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tapia.mji.demo.Activities.PhotoShowActivity;
import com.tapia.mji.demo.Fragments.PhotoFragment;
import com.tapia.mji.demo.R;

import java.io.File;
import java.util.List;

/**
 * Created by Sami on 11-Jul-16.
 */
public class PhotoShowAdapter extends BaseAdapter {
    List<PhotoShowActivity.PhotoListItem> photoList;
    Activity activity;
    private LayoutInflater inflater;

    public PhotoShowAdapter(Activity activity, List<PhotoShowActivity.PhotoListItem> photoList) {
        this.photoList = photoList;
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
    }


    @Override
    public int getCount() {
        return photoList.size();
    }

    @Override
    public PhotoShowActivity.PhotoListItem getItem(int position) {
        return photoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_photo, parent, false);
            holder.content_ll = convertView.findViewById(R.id.photoLayout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final PhotoShowActivity.PhotoListItem myItem = photoList.get(position);

        if (holder.content_ll != null) {
            if (holder.content_ll.getChildCount() > 0)
                holder.content_ll.removeAllViews();
        }

        for (int i = 0; i < myItem.pictureNames.length; i++) {
            if (myItem.pictureNames[i] != null) {
                final int index = i;
                File imgFile = new File(myItem.pictureNames[i]);

                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    myItem.picturesView[i].setImageBitmap(myBitmap);
                }
                final int targetIndex = i;
                myItem.picturesView[i].setLayoutParams(new LinearLayout.LayoutParams(0, 250, 1f));
                myItem.picturesView[i].setBackgroundResource(R.drawable.button_image);
                myItem.picturesView[i].setScaleType(ImageView.ScaleType.FIT_XY);
                myItem.picturesView[i].setPadding(5, 5, 5, 5);
                myItem.picturesView[i].setOnClickListener(v -> {
                    FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
                    Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
                    if (prev != null) {
                        ft.remove(prev);
                    }
                    ft.addToBackStack(null);
                    // Create and show the dialog.
                    DialogFragment newFragment = new PhotoFragment();
                    Bundle args = new Bundle();
                    args.putString("photoPath", myItem.pictureNames[index]);
                    newFragment.setArguments(args);
                    newFragment.setShowsDialog(true);
                    ((PhotoFragment) newFragment).setListener(() -> {
                        photoList.remove(targetIndex);
                        refresh(photoList);
                    });
                    newFragment.show(ft, "dialog");
                });
                holder.content_ll.addView(myItem.picturesView[i]);
            } else {
                ImageView filler = new ImageView(activity);
                filler.setLayoutParams(new LinearLayout.LayoutParams(0, 250, 1f));
                holder.content_ll.addView(filler);
            }
        }
        convertView.setTag(R.id.photoItem, myItem);
        return convertView;
    }

    private class ViewHolder {
        private LinearLayout content_ll;
    }

    public void refresh(final List<PhotoShowActivity.PhotoListItem> photoItemList) {
        Handler refresh = new Handler(Looper.getMainLooper());
        refresh.post(() -> {
            photoList = photoItemList;
            notifyDataSetChanged();
        });
    }
}
