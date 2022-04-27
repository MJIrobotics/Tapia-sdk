package com.tapia.mji.demo.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tapia.mji.demo.Activities.PhotoShowActivity;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Exceptions.LanguageNotSupportedException;
import com.tapia.mji.tapialib.Utils.CameraHelper;
import com.tapia.mji.tapialib.Utils.TapiaNetwork;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by Sami on 11-Jul-16.
 */
public class PhotoFragment extends DialogFragment {

    ImageView Limage;
    ImageView Simage;
    ImageButton back;
    ImageButton home;
    ImageButton qrcode;
    ImageButton delete;
    TextView line;

    ProgressBar loadingBar;
    boolean isClick = false;
    PhotoShowActivity photoShowActivity;
    RequestParams params = new RequestParams();

    String photoName = null;
    boolean isBusy = false;
    File imgFile = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        photoShowActivity = (PhotoShowActivity) getActivity();
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Material);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TapiaActivity.configWindow(getActivity());
        Bundle args = getArguments();
        final String photoPath = args.getString("photoPath");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_photo, null);
        loadingBar = view.findViewById(R.id.progressBar);
        loadingBar.setVisibility(View.GONE);
        Limage = view.findViewById(R.id.photo);
        Simage = view.findViewById(R.id.imageView);
        back = view.findViewById(R.id.imageButton5);
        home = view.findViewById(R.id.imageButton6);
        qrcode = view.findViewById(R.id.buttonQRcode);
        delete = view.findViewById(R.id.buttonDelete);
        line = view.findViewById(R.id.textView);


        AlertDialog myDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();
        myDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);


        back.setOnClickListener(v -> getDialog().dismiss());

        qrcode.setOnClickListener(v -> {
            if (!isBusy) {
                params.put("filename", photoName);
                encodeImagetoString();
                loadingBar.setVisibility(View.VISIBLE);
            }
        });


        home.setOnClickListener(v -> getDialog().dismiss());
        delete.setOnClickListener(v -> {
            v.setEnabled(false);
            try {
                photoShowActivity.ttsProvider.say(photoShowActivity.getString(R.string.photo_ask_delete0));
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


    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        TapiaNetwork.getJson("http://mji.main.jp/Robot/deleteImage.php?name=" + photoName.replace(".jpg", "").replace(".jpeg", ""), null);
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


    public void encodeImagetoString() {
        new AsyncTask<Void, Void, String>() {

            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(Void... params) {
                BitmapFactory.Options options = null;
                options = new BitmapFactory.Options();
                options.inSampleSize = 3;
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                // Must compress the Image to reduce image size to make upload easy
                bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
                byte[] byte_arr = stream.toByteArray();
                // Encode Image to String

                return Base64.encodeToString(byte_arr, 0);
            }

            @Override
            protected void onPostExecute(String encodedString) {

                // Put converted Image string into Async Http Post param
                params.put("image", encodedString);
                // Trigger Image upload
                makeHTTPCall();
            }
        }.execute(null, null, null);
    }

    public void makeHTTPCall() {
        AsyncHttpClient client = new AsyncHttpClient();
        // Don't forget to change the IP address to your LAN address. Port no as well.
        client.post("http://mji.main.jp/Robot/upload_image.php", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                loadingBar.setVisibility(View.INVISIBLE);
                try {
                    String str = new String(responseBody, StandardCharsets.UTF_8);
                    Limage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    loadingBar.setVisibility(View.GONE);
                    new DownloadImageTask(Limage).execute(str);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                loadingBar.setVisibility(View.INVISIBLE);
                switch (statusCode) {
                    case 404:
                        break;
                    case 500:
                        break;
                    default:
                        break;
                }
            }


        });
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
