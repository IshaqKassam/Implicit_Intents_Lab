package com.kassam.implicit_intents_lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;
    private EditText mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);
        mPhoneNumber = findViewById(R.id.phone_number);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        try {
            startActivity(intent);
        } catch (Exception e) {
            // Display some error message
            Log.d("ImplicitIntents", "Cant Handle This!");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        // Parse the location and create the intent.
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent, and start that activity.
        try {
            startActivity(intent);
        } catch (Exception e) {
            // Display some error message
            Log.d("ImplicitIntents", "Cant Handle This!");
        }
    }

    public void shareText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    public void callNumber(View view) {
        Uri u = Uri.parse("tel:"+ mPhoneNumber.getText().toString());
        Intent i = new Intent(Intent.ACTION_DIAL, u);

        try{
            startActivity(i);
        } catch (SecurityException s){
            Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show();
        }


    }
        
        
}
