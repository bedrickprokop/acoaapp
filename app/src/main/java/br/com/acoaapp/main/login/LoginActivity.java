package br.com.acoaapp.main.login;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

import br.com.acoaapp.R;
import br.com.acoaapp.ShakeDetector;
import br.com.acoaapp.main.NavDrawerActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;

    //TODO sensor
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private int screenWidth, screenHeight;
    private List<LottieAnimationView> lottieAnimationViewList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);

        //TODO sensor
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(/*int count*/) {
                handleShakeEvent(/*count*/);
            }
        });

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btLogin = findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidFields()) {
                    //TODO chamar serviço de autenticação
                    Intent intent = new Intent(LoginActivity.this, NavDrawerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO sensor
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        //TODO sensor
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    //TODO sensor
    private void handleShakeEvent(/*int count*/) {
        createLottieElements();
        //startAnimation(0, 50);

        LottieAnimationView lottieAnimationView1 = lottieAnimationViewList.get(0);
        lottieAnimationView1.setVisibility(View.VISIBLE);
        lottieAnimationView1.playAnimation();

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LottieAnimationView lottieAnimationView2 = lottieAnimationViewList.get(1);
                lottieAnimationView2.setVisibility(View.VISIBLE);
                lottieAnimationView2.playAnimation();
            }
        }, 50);*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LottieAnimationView lottieAnimationView3 = lottieAnimationViewList.get(1);
                lottieAnimationView3.setVisibility(View.VISIBLE);
                lottieAnimationView3.playAnimation();
            }
        }, 500);
    }

    //TODO sensor
    private void createLottieElements() {
        if (lottieAnimationViewList == null) {
            lottieAnimationViewList = new ArrayList<>();
            String jsonName = "carnaval.json";

            LottieAnimationView anim1 = new LottieAnimationView(this);
            anim1.setId(1);
            anim1.setAnimation(jsonName);
            anim1.loop(false);
            anim1.setX(-(screenWidth / 4));
            anim1.setY(-(screenHeight / 4));
            anim1.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            anim1.addAnimatorListener(new CustomAnimatorListener(anim1));

            LottieAnimationView anim2 = new LottieAnimationView(this);
            anim2.setId(2);
            anim2.setAnimation(jsonName);
            anim2.loop(false);
            anim2.setX(screenWidth / 2);
            anim2.setY(-screenHeight / 2);
            anim2.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            anim2.addAnimatorListener(new CustomAnimatorListener(anim2));

            LottieAnimationView anim3 = new LottieAnimationView(this);
            anim3.setId(3);
            anim3.setAnimation(jsonName);
            anim3.loop(false);
            anim3.setX(screenWidth / 4);
            anim3.setY(screenHeight / 3);
            anim3.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            anim3.addAnimatorListener(new CustomAnimatorListener(anim3));

            lottieAnimationViewList.add(anim1);
            //lottieAnimationViewList.add(anim2);
            lottieAnimationViewList.add(anim3);

            ViewGroup rootElement = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content))
                    .getChildAt(0);
            rootElement.addView(anim1);
            //rootElement.addView(anim2);
            rootElement.addView(anim3);
        }
    }

    //TODO sensor
    private void startAnimation(final int indexAnimation, long delay) {
        final LottieAnimationView lottieAnimationView = lottieAnimationViewList.get(indexAnimation);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                int incrementedIndex = indexAnimation + 1;
                if (incrementedIndex < lottieAnimationViewList.size()) {
                    startAnimation(incrementedIndex, 80);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                lottieAnimationView.setVisibility(View.GONE);
                lottieAnimationView.setLayerType(View.LAYER_TYPE_NONE, null);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        if (indexAnimation == 0) {
            lottieAnimationView.playAnimation();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    lottieAnimationView.playAnimation();
                }
            }, delay);
        }
    }

    private boolean isValidFields() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.requestFocus();
            etEmail.setError(getString(R.string.act_login_ettext_requiredemail));
            return false;
        }
        if (password.isEmpty()) {
            etPassword.requestFocus();
            etPassword.setError(getString(R.string.act_login_ettext_requiredpassword));
            return false;
        }
        return true;
    }

    class CustomAnimatorListener implements Animator.AnimatorListener {

        private LottieAnimationView lottieAnimationView;

        public CustomAnimatorListener(LottieAnimationView lottieAnimationView) {
            this.lottieAnimationView = lottieAnimationView;
        }

        @Override
        public void onAnimationStart(Animator animation) {
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            lottieAnimationView.setVisibility(View.GONE);
            lottieAnimationView.setLayerType(View.LAYER_TYPE_NONE, null);
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }
}