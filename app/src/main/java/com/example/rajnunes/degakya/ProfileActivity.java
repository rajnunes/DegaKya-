package com.example.rajnunes.degakya;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class ProfileActivity extends AppCompatActivity {

    public String profilePic;
    private ImageView imageView;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = (ImageView) findViewById(R.id.profilePic);

        Intent intent = getIntent();
//        profilePic = intent.getExtras().getString("profilePic");
       /* try {
            url = new URL(profilePic);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {

        }*/
//       imageView.setImageBitmap(loadImageBitmap(getApplicationContext(),"profilePic.jpeg"));
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public Bitmap loadImageBitmap(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 3, Something went wrong!");
            e.printStackTrace();
        }
        return bitmap;
    }
}
