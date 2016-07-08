package il.ac.shenkar.showshenkar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import il.ac.shenkar.showshenkar.R;

public class ShenkarActivity extends AppCompatActivity {

    public String Qrlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.shenkar_actionbar, null);
        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

        ImageButton mapBtn = (ImageButton) mCustomView.findViewById(R.id.toolbarmap);
        mapBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showMap();
            }
        });

        ImageButton qrBtn = (ImageButton) mCustomView.findViewById(R.id.toolbarqr);
        qrBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openScanner();  //call for the scanner func
            }
        });
    }

    private void showMap() {
        // TODO: show map
    }

    /*
    * calling for the scanner to start and using zxing package for different customize options
    * */
    public void openScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("waiting for code to scan");
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    /*
    * working the result of the scan*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            Qrlocation = result.getContents();
        }

        /*
            read the project id from the QR code then add it to my route using the following code
            MyRouteActivity.addProjectId(this, projectId);
         */
    }
}