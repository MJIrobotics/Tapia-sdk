package com.tapia.mji.demo.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.tapia.mji.demo.Adapters.PhotoShowAdapter;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.CameraHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Sami on 11-Jul-16.
 */
public class PhotoShowActivity extends TapiaActivity {

    static final int PHOTO_BY_LINE = 3;

    PhotoShowAdapter photoAdapter;
    ListView photoList;
    int setcted = 0;
    String fileName;
    File dest;
    String dirPath;
    String dirPathThumbnail;
    ImageButton local;
    ImageButton sdcard;
    ImageButton home;

    public List<PhotoListItem> curPhotoList;
    public String selectedPhoto;

    public class PhotoListItem {
        public String[] pictureNames;
        public ImageView[] picturesView;

        PhotoListItem(String[] photosName) {
            pictureNames = new String[PHOTO_BY_LINE];
            picturesView = new ImageView[PHOTO_BY_LINE];
            int index = 0;
            for (String pName : photosName) {
                pictureNames[index] = pName;
                picturesView[index] = new ImageView(activity);
                index++;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showphoto);
        local = findViewById(R.id.localButton);
        sdcard = findViewById(R.id.sdcardButton);
        home = findViewById(R.id.homeButton);
        photoList = findViewById(android.R.id.list);
//        dirPath = CameraHelper.PICTURE_FOLDER;
        dirPathThumbnail = CameraHelper.SMALL_PICTURE_FOLDER;
        loadPictures(dirPathThumbnail);
        photoAdapter = new PhotoShowAdapter(activity, curPhotoList);
        photoList.setAdapter(photoAdapter);

        TapiaApp.setCustomViewBackground(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0).getBackground());

        local.setSelected(true);
        sdcard.setSelected(false);

        sttProvider = TapiaApp.currentLanguage.getOnlineSTTProvider();
        ttsProvider = TapiaApp.currentLanguage.getTTSProvider();
        offlineNLUProvider = TapiaApp.currentLanguage.getOfflineNLUProvider();

        home.setOnClickListener(v -> finish());
        sdcard.setOnClickListener(v -> {
            sdcard.setSelected(true);
            local.setSelected(false);
            dirPathThumbnail = "/storage/sdcard1/";//set to the sdcard path
            loadPictures(dirPathThumbnail);
            photoAdapter.refresh(curPhotoList);
        });
        local.setOnClickListener(v -> {
            local.setSelected(true);
            sdcard.setSelected(false);
            dirPathThumbnail = CameraHelper.SMALL_PICTURE_FOLDER;
            loadPictures(dirPathThumbnail);
            photoAdapter.refresh(curPhotoList);
        });

    }


    void loadPictures(String path) {
        File picFile = new File(path);
        curPhotoList = new ArrayList<>();
        String[] itemPictureList = new String[PHOTO_BY_LINE];
        int itemPicIndex = 0;
        if (picFile.exists() && picFile.isDirectory() && picFile.list() != null) {
            for (String pName : picFile.list()) {
                if (pName.contains(".jpeg") || pName.contains(".jpg") || pName.contains(".png")) {
                    itemPictureList[itemPicIndex] = path + pName;
                    itemPicIndex++;
                    if (itemPicIndex == PHOTO_BY_LINE) {
                        curPhotoList.add(new PhotoListItem(itemPictureList));
                        itemPicIndex = 0;
                    }
                }
            }

            if (itemPicIndex > 0) {
                for (int i = itemPicIndex; i < PHOTO_BY_LINE; i++) {
                    itemPictureList[i] = null;
                }
                curPhotoList.add(new PhotoListItem(itemPictureList));
            }
        }
    }

    public void refreshList() {
        loadPictures(dirPathThumbnail);
        photoAdapter.refresh(curPhotoList);
    }

    public Drawable findImageView(String photoName) {
        for (PhotoListItem photoItem : curPhotoList) {
            for (int i = 0; i < PHOTO_BY_LINE; i++) {
                if (photoItem.pictureNames[i].equals(photoName))
                    return photoItem.picturesView[i].getDrawable();
            }
        }
        return null;
    }


}
