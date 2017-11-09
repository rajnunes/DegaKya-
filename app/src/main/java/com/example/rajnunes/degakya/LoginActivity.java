package com.example.rajnunes.degakya;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Button LoginButton;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private String email, personName, personPhotoUrl;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sp = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
        int chk = sp.getInt("Login", 0);
        if (chk == 201) {
            Intent intent = new Intent(this, MainFeedActivity.class);
            startActivity(intent);
            this.finish();
        }

        LoginButton = (Button) findViewById(R.id.loginButton);
        LoginButton.setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestProfile().requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();


//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                signIn();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    public void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            //TODO Get data components

            GoogleSignInAccount acct = result.getSignInAccount();
            personName = acct.getDisplayName();
            personPhotoUrl = acct.getPhotoUrl().toString();
            Log.d("URL", personPhotoUrl);
            email = acct.getEmail();

            SharedPreferences sp = getSharedPreferences("Prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putInt("Login", 201);
            ed.putString("profilePicUrl", personPhotoUrl);
            ed.apply();
            Intent intent = new Intent(this, MainFeedActivity.class);
//            intent.putExtra("profilePic", personPhotoUrl);

            /*try {
                url = new URL(personPhotoUrl);
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream) url.getContent());
//                saveFile(getApplicationContext(), bitmap, "profilePic");
            } catch (Exception e) {

            }*/

            startActivity(intent);
            this.finish();
        }
    }

    public static void saveFile(Context context, Bitmap b, String picName) {
        FileOutputStream fos;

        String TAG = "File";
        try {
            fos = context.openFileOutput(picName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            Log.d(TAG, "file not found");
            e.printStackTrace();
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        private String TAG = "DownloadImage";
        private Bitmap downloadImageBitmap(String sUrl) {
            Bitmap bitmap = null;
            try {
                InputStream inputStream = new URL(sUrl).openStream();   // Download Image from URL
                bitmap = BitmapFactory.decodeStream(inputStream);       // Decode Bitmap
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, "Exception 1, Something went wrong!");
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return downloadImageBitmap(params[0]);
        }

        protected void onPostExecute(Bitmap result) {
            saveImage(getApplicationContext(), result, "my_image.png");
        }
    }
    public void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.PNG, 100, foStream);
            foStream.close();
        } catch (Exception e) {
            Log.d("saveImage", "Exception 2, Something went wrong!");
            e.printStackTrace();
        }
    }
}
