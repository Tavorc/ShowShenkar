package il.ac.shenkar.showshenkar.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import il.ac.shenkar.showshenkar.R;

public class ShenkarActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_camera)
        {
            // TODO: implement camera QR logic
            Toast.makeText(this, R.string.camera_btn, Toast.LENGTH_LONG).show();
        }

        return true;
    }
}
