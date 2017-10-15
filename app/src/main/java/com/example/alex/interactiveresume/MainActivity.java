package com.example.alex.interactiveresume;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    RelativeLayout tileRoot;
    CustomRoot root;
    RelativeLayout extra, award, job, academia, pdf;
    ImageView more, headshot, headshot2;
    boolean scrollInAction = false;
    boolean scrolled = false;
    boolean moreClick = false;
    boolean passToRoot = false;
    float lastY;
    float moreInitialY, moreInitialX;
    LinearLayout bottom, middle, top;
    FrameLayout nameContainer;
    TextView an, interactive, resume;
    View moreIndicator;

    float headshotY, headshot2Y, moreY, bottomY, middleY, topY, nameContainerY, anY,
            interactiveY, resumeY, indicatorY, tileRootY, tileRootFinalY;
    boolean setInitialY = false;
    float completeY, completeY2;
    float completePercent = 950f / 1280f, completePercent2 = 234f / 1280f;

    int touchSlop;
    ViewConfiguration vc;

    public static int SCREEN_HEIGHT;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        completeY = SCREEN_HEIGHT * completePercent;
        completeY2 = SCREEN_HEIGHT * completePercent2;

        extra = (RelativeLayout) findViewById(R.id.extra_tile);
        award = (RelativeLayout) findViewById(R.id.award_tile);
        job = (RelativeLayout) findViewById(R.id.job_tile);
        academia = (RelativeLayout) findViewById(R.id.academia_tile);
        pdf = (RelativeLayout) findViewById(R.id.pdf);

        moreIndicator = findViewById(R.id.more_indicator);
        more = (ImageView) findViewById(R.id.more_arrow);
        root = (CustomRoot) findViewById(R.id.root);
        headshot = (ImageView) findViewById(R.id.headshot);
        headshot2 = (ImageView) findViewById(R.id.headshot2);
        bottom = (LinearLayout) findViewById(R.id.bottom);
        middle = (LinearLayout) findViewById(R.id.middle);
        top = (LinearLayout) findViewById(R.id.top);
        nameContainer = (FrameLayout) findViewById(R.id.name_container);
        an = (TextView) findViewById(R.id.an);
        interactive = (TextView) findViewById(R.id.interactive);
        resume = (TextView) findViewById(R.id.resume);
        tileRoot = (RelativeLayout) findViewById(R.id.tile_root);
        tileRoot.setY(SCREEN_HEIGHT);

        vc = ViewConfiguration.get(MainActivity.this);
        touchSlop = vc.getScaledTouchSlop();
        more.setOnTouchListener(new MoreTouchListener(MainActivity.this));

        root.setOnTouchListener(new RootTouchListener(MainActivity.this));

        extra.setOnTouchListener(new TileTouchListener(MainActivity.this));
        award.setOnTouchListener(new TileTouchListener(MainActivity.this));
        job.setOnTouchListener(new TileTouchListener(MainActivity.this));
        academia.setOnTouchListener(new TileTouchListener(MainActivity.this));
        pdf.setOnTouchListener(new TileTouchListener(MainActivity.this));
    }

    @Override
    public void onBackPressed() {
        if (scrolled) {
            scrolled = false;
            headshot.animate().y(headshotY).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
            headshot2.animate().y(headshot2Y).setDuration(250).setStartDelay(0).setInterpolator(new DecelerateInterpolator()).start();
            bottom.animate().y(bottomY).setDuration(325).setInterpolator(new DecelerateInterpolator()).start();
            middle.animate().y(middleY).setDuration(380).setInterpolator(new DecelerateInterpolator()).start();
            top.animate().y(topY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            nameContainer.animate().y(nameContainerY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            an.animate().y(anY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            interactive.animate().y(interactiveY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            resume.animate().y(resumeY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            more.animate().y(moreY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            more.animate().rotation(360f).setDuration(150).setInterpolator(new DecelerateInterpolator()).start();
            tileRoot.animate().y(tileRootY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
            moreIndicator.animate().y(indicatorY).setDuration(250).setInterpolator(new DecelerateInterpolator()).start();
        } else {
            super.onBackPressed();
        }
    }

    private void copyAssets() {
        AssetManager am = getAssets();
        InputStream in;
        OutputStream out;
        try {
            String outDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            File outFile = new File(outDir, "resume.pdf");
            in = am.open("pdfs/alex_costa_resume.pdf");
            out = new FileOutputStream(outFile);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    public static boolean verifyStoragePermissions(final MainActivity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PERMISSION_GRANTED) {
            AlertDialog.Builder alert = new AlertDialog.Builder(activity)
                    .setTitle("Storage Access Permission")
                    .setMessage("Storage access permissions are needed to copy the pdf to external storage. Please accept the following prompt to allow access.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                        }
                    });
            alert.show();
            return false;
        } else {
            activity.copyAssets();
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            if (grantResults[0] == PERMISSION_GRANTED && grantResults[1] == PERMISSION_GRANTED) {
                copyAssets();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/resume.pdf";
                File pdf = new File(path);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(pdf), "application/pdf");
                Intent chooser = Intent.createChooser(intent, "Open File");
                try {
                    startActivity(chooser);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(MainActivity.this, "No PDF reader applications installed", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
