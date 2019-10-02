package org.appspot.apprtc;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onBackPressed() {
        Random random=new Random();
        int room= random.nextInt(6);
        startActivity(new Intent(ProfileActivity.this,ConnectActivity.class).putExtra("room","2345"+room ));
        ProfileActivity.this.finish();
        super.onBackPressed();
    }
}
