package il.ac.shenkar.showshenkar.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.adapters.DepGridViewAdapter;
import il.ac.shenkar.showshenkar.model.DepImageItem;



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
