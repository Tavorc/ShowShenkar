package il.ac.shenkar.showshenkar.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.backend.departmentApi.DepartmentApi;
import il.ac.shenkar.showshenkar.backend.departmentApi.model.Department;
import il.ac.shenkar.showshenkar.utils.BitmapDownloader;
import il.ac.shenkar.showshenkar.utils.Constants;

public class DepGridViewAdapter extends ArrayAdapter<Department> {

    private Context context;
    private int layoutResourceId;
    private List<Department> data;

    public DepGridViewAdapter(Context context, int layoutResourceId, List<Department> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final Department item = data.get(position);
        holder.imageTitle.setText(item.getName());

        new AsyncTask<Void, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(Void... params) {
                return BitmapDownloader.getBitmapFromURL(item.getImageUrl());
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                //show complition in UI
                //fill grid view with data
                if (bitmap != null) {
                    holder.image.setImageBitmap(bitmap);
                }
            }
        }.execute();

        return row;
    }

    public void refresh() {
        final DepartmentApi departmentApi = new DepartmentApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) throws IOException {

                    }
                }).setRootUrl(Constants.ROOT_URL).build();

        new AsyncTask<Void, Void, List<Department>>() {
            @Override
            protected List<Department> doInBackground(Void... params) {
                List<Department> departments = null;
                try {
                    departments = departmentApi.getDepartments().execute().getItems();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return departments;
            }

            @Override
            protected void onPostExecute(List<Department> departments) {
                //show complition in UI
                //fill grid view with data
                if (departments != null) {
                    data.clear();
                    data.addAll(departments);
                    notifyDataSetChanged();
                }
            }
        }.execute();
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}