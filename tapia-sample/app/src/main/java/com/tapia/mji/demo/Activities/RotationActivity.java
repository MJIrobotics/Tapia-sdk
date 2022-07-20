package com.tapia.mji.demo.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Utils.TapiaRobot;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

public class RotationActivity extends TapiaActivity {
    public static int DEFAULT_POSITION = 15;

    ImageButton home;
    View upButton;
    View downButton;
    View rightButton;
    View leftButton;
    View frontButton;
    View locationButton;
    TextView locationValue;
    boolean isOnSoundLocation = false;
    TapiaRobotManager robotManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        home = findViewById(R.id.homeButton);
        home.setOnClickListener(v -> finish());

        upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(v -> onUpButtonClicked());

        downButton = findViewById(R.id.downButon);
        downButton.setOnClickListener(v -> onDownButtonClicked());

        rightButton = findViewById(R.id.rightButton);
        rightButton.setOnClickListener(v -> onRightButtonClicked());

        leftButton = findViewById(R.id.leftButton);
        leftButton.setOnClickListener(v -> onLeftButtonClicked());

        frontButton = findViewById(R.id.frontButton);
        frontButton.setOnClickListener(v -> onFrontButtonClicked());
        locationButton = findViewById(R.id.iv_location);
        locationButton.setOnClickListener(v -> onLocationButtonClicked());

        locationValue = findViewById(R.id.tv_location);
        robotManager = TapiaRobot.getManager(getApplicationContext());
        checkCurrentLocation(null);
    }

    private void checkCurrentLocation(CheckLocationCallback callback) {
        robotManager.setOnPositionChangeListener((i, orientation) -> {
            robotManager.setOnPositionChangeListener(null);
            if (callback != null) {
                callback.onChecked();
            }
        });
        robotManager.requestUpdateLocationPosition();
    }

    interface CheckLocationCallback {
        void onChecked();
    }

    private void onUpButtonClicked() {
        robotManager.rotate(TapiaRobotManager.Direction.UP, 10, null);
    }

    private void onDownButtonClicked() {
        robotManager.rotate(TapiaRobotManager.Direction.DOWN, 10, null);
    }

    private void onRightButtonClicked() {
        robotManager.rotate(TapiaRobotManager.Direction.RIGHT, 50, null);
    }

    private void onLeftButtonClicked() {
        robotManager.rotate(TapiaRobotManager.Direction.LEFT, 50, null);
    }

    private void onFrontButtonClicked() {
        checkCurrentLocation(() -> {
            robotManager.rotate(TapiaRobotManager.Direction.LEFT, robotManager.getCurrentLRPosition(), () -> {
                int udPosition = robotManager.getCurrentUDPosition();
                if (udPosition > DEFAULT_POSITION) {
                    robotManager.rotate(TapiaRobotManager.Direction.DOWN, robotManager.getCurrentUDPosition() - DEFAULT_POSITION, null);
                } else {
                    robotManager.rotate(TapiaRobotManager.Direction.UP, DEFAULT_POSITION - robotManager.getCurrentUDPosition(), null);
                }

            });
        });
    }

    private void onLocationButtonClicked() {
        if (!isOnSoundLocation) {
            isOnSoundLocation = true;

            locationButton.setBackground(getDrawable(R.drawable.bg_circle_red));

            robotManager.setOnSoundLocationListener(degree -> {

                // degree from default position.
                int targetPosition = (degree + 360) % 360;
                locationValue.setText(targetPosition + "°");

                // Prevent detecting sounds of rotating Tapia.
                robotManager.stopSoundLocation();
                robotManager.rotate(TapiaRobotManager.Direction.LEFT, targetPosition, () -> {
                    new Handler().postDelayed(() -> {
                        if (isOnSoundLocation) {
                            robotManager.startSoundLocation();
                        }
                    }, 500);
                });
            });
            robotManager.startSoundLocation();
        } else {
            isOnSoundLocation = false;
            locationButton.setBackground(getDrawable(R.drawable.bg_circle_gray));
            robotManager.stopSoundLocation();
        }
    }
}
