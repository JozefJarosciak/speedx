package io.rong.imkit.widget.provider;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.message.utils.BitmapUtil;
import java.io.File;
import java.io.IOException;

public class TakingPicturesActivity extends Activity implements OnClickListener {
    private static final int REQUEST_CAMERA = 2;
    private static final String TAG = "TakingPicturesActivity";
    private ImageView mImage;
    private Uri mSavedPicUri;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C4974R.layout.rc_ac_camera);
        Button button = (Button) findViewById(C4974R.id.rc_back);
        Button button2 = (Button) findViewById(C4974R.id.rc_send);
        this.mImage = (ImageView) findViewById(C4974R.id.rc_img);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        RLog.m19419d(TAG, "onCreate savedInstanceState : " + bundle);
        if (bundle == null) {
            startCamera();
            return;
        }
        String string = bundle.getString("photo_uri");
        if (string != null) {
            this.mSavedPicUri = Uri.parse(string);
            try {
                this.mImage.setImageBitmap(BitmapUtil.getResizedBitmap(this, this.mSavedPicUri, 960, 960));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onClick(View view) {
        if (!new File(this.mSavedPicUri.getPath()).exists()) {
            finish();
        }
        if (view.getId() == C4974R.id.rc_send) {
            if (this.mSavedPicUri != null) {
                Intent intent = new Intent();
                intent.setData(this.mSavedPicUri);
                setResult(-1, intent);
            }
            finish();
        } else if (view.getId() == C4974R.id.rc_back) {
            finish();
        }
    }

    private void startCamera() {
        Intent intent = new Intent();
        intent.setAction("android.media.action.IMAGE_CAPTURE");
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        this.mSavedPicUri = Uri.fromFile(new File(externalStoragePublicDirectory, System.currentTimeMillis() + ".jpg"));
        RLog.m19419d(TAG, "startCamera output pic uri =" + this.mSavedPicUri);
        intent.putExtra("output", this.mSavedPicUri);
        intent.addCategory("android.intent.category.DEFAULT");
        try {
            startActivityForResult(intent, 2);
        } catch (SecurityException e) {
            Log.e(TAG, "REQUEST_CAMERA SecurityException!!!");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        RLog.m19419d(TAG, "onActivityResult resultCode = " + i2 + ", intent=" + intent);
        if (i2 != -1) {
            finish();
            return;
        }
        switch (i) {
            case 2:
                if (i2 == 0) {
                    finish();
                    Log.e(TAG, "RESULT_CANCELED");
                }
                if (this.mSavedPicUri != null && i2 == -1) {
                    try {
                        this.mImage.setImageBitmap(BitmapUtil.getResizedBitmap(this, this.mSavedPicUri, 960, 960));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                super.onActivityResult(i, i2, intent);
                return;
            default:
                return;
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        Log.e(TAG, "onRestoreInstanceState");
        this.mSavedPicUri = Uri.parse(bundle.getString("photo_uri"));
        super.onRestoreInstanceState(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Log.e(TAG, "onSaveInstanceState");
        bundle.putString("photo_uri", this.mSavedPicUri.toString());
        super.onSaveInstanceState(bundle);
    }
}
