package il.ac.shenkar.endofyearshenkar.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.List;

import il.ac.shenkar.endofyearshenkar.R;
import il.ac.shenkar.endofyearshenkar.model.DBHelper;
import il.ac.shenkar.showshenkar.backend.projectApi.ProjectApi;
import il.ac.shenkar.showshenkar.backend.projectApi.model.Project;

public class ShenkarActivity extends AppCompatActivity {

    public String Qrlocation;
    Long rContent;
    ProjectApi projectApi;
    Project project;
    List <String> studentsName;
    String projectName;

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
        Intent i = new Intent(this, MapActivity.class);
        i.putExtra("objectId", "general");
        i.putExtra("objectType", "general");
        startActivity(i);
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
                try {
                    // Parse QR Code
                    String oActivity = result.getContents().split(";")[0];
                    final Long oID = Long.valueOf(result.getContents().split(";")[1]);
                    // Select Activity
                    Intent intent;
                    if(oActivity.equals("MapActivity")){
                        String oType = result.getContents().split(";")[2];
                        intent = new Intent(this, MapActivity.class);
                        intent.putExtra("objectType", oType);
                        intent.putExtra("objectId", oID);
                        startActivity(intent);
                    }else if(oActivity.equals("ProjectActivity")) {
                        intent = new Intent(this, ProjectActivity.class);
                        intent.putExtra("objectId", oID);
                        startActivity(intent);
                    }

                }catch (Exception exc){

                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            Qrlocation = result.getContents();
        }

    }
}
