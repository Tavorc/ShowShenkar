package shenkar.ac.il.showshenkar.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import il.ac.shenkar.showshenkar.backend.departmentApi.DepartmentApi;
import il.ac.shenkar.showshenkar.backend.departmentApi.model.Department;
import il.ac.shenkar.showshenkar.backend.projectApi.ProjectApi;
import shenkar.ac.il.showshenkar.adapters.DepGridViewAdapter;
import shenkar.ac.il.showshenkar.model.DepImageItem;
import shenkar.ac.il.showshenkar.R;
import shenkar.ac.il.showshenkar.utils.Constants;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private DepGridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new DepGridViewAdapter(this, R.layout.dep_grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                DepImageItem item = (DepImageItem) parent.getItemAtPosition(position);

                //Create intent
                Intent intent = new Intent(MainActivity.this, DepartmentActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImageResource());

                //Start details activity
                startActivity(intent);
            }
        });
    }

    private String getStringResourceByName(String aString) {
        String packageName = getPackageName();
        int resId = getResources().getIdentifier(aString, "string", packageName);
        return getString(resId);
    }

    // Prepare some dummy data for gridview
    private ArrayList<DepImageItem> getData() {
        /***********real getData logic for gridView**************************************************************

            final DepartmentApi departmentApi = new DepartmentApi.Builder(
            AndroidHttp.newCompatibleTransport(),
            new JacksonFactory(),
            new HttpRequestInitializer() {
            @Override
                public void initialize(HttpRequest request) throws IOException {

                }
            }).setRootUrl(Constants.ROOT_URL).build();
            new AsyncTask<Void,Void,Void>(){
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                        List<Department> departments = departmentApi.getDepartments().execute().getItems();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    //show complition in UI
                    //fill grid view with data
                }
            }.execute();


         ******************************************************************************************************/

        final ArrayList<DepImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            imageItems.add(new DepImageItem(imgs.getResourceId(i, -1), getStringResourceByName("dep_" + (i + 1))));
        }
        return imageItems;
    }
}
