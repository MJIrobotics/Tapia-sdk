package com.tapia.mji.demo.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tapia.mji.demo.Activities.PhotoShowActivity;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Exceptions.LanguageNotSupportedException;
import com.tapia.mji.tapialib.Utils.CameraHelper;

import java.io.File;

/**
 * Created by Sami on 11-Jul-16.
 */
public class PhotoFragment extends DialogFragment {

    ImageView Limage;
    ImageButton back;
    ImageButton delete;
    TextView line;
    PhotoShowActivity photoShowActivity;

    String photoName = null;
    File imgFile = null;


    PhotoDialogListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoShowActivity = (PhotoShowActivity) getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Material);
    }

    String photoPath = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TapiaActivity.configWindow(getActivity());
        Bundle args = getArguments();
        photoPath = args.getString("photoPath");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, null);

        Limage = view.findViewById(R.id.photo);
        back = view.findViewById(R.id.imageButton5);
        delete = view.findViewById(R.id.buttonDelete);
        line = view.findViewById(R.id.textView);


        AlertDialog myDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        myDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        back.setOnClickListener(v -> getDialog().dismiss());
        delete.setOnClickListener(v -> {
            v.setEnabled(false);
            try {
                photoShowActivity.ttsProvider.setOnSpeechCompleteListener(() -> {
                    photoShowActivity.ttsProvider.setOnSpeechCompleteListener(null);
                    final AlertDialog dialog = new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.photo_ask_delete0)
                            .setPositiveButton(R.string.delete, (dialog1, which) -> {
                                dialog1.dismiss();
                                myDialog.dismiss();
                                deleteImage();
                            })
                            .setNegativeButton(R.string.cancel, (dialog1, which) -> dialog1.dismiss()).create();
                    dialog.show();
                });
                photoShowActivity.ttsProvider
                        .say(photoShowActivity.getString(R.string.photo_ask_delete0));
            } catch (LanguageNotSupportedException e) {
                e.printStackTrace();
            }
        });
        Limage.setImageDrawable(photoShowActivity.findImageView(photoPath));

        if (photoPath != null) {
            String targetPic = photoPath.replace(CameraHelper.SMALL_PICTURE_FOLDER, CameraHelper.PICTURE_FOLDER);
            imgFile = new File(targetPic);
            photoName = imgFile.getName();
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                Limage.setImageBitmap(myBitmap);
            }
        }

        return myDialog;
    }

    void deleteImage() {
        new File(photoPath).delete();
        if (listener != null) {
            listener.onDeleted();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().getDecorView().setSystemUiVisibility(
                getActivity().getWindow().getDecorView().getSystemUiVisibility());
        getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        lp.width = 1150;
        lp.height = 700;
        getDialog().getWindow().setAttributes(lp);
    }

    public void setListener(PhotoDialogListener listener) {
        this.listener = listener;
    }

    public interface PhotoDialogListener {
        void onDeleted();
    }
}
