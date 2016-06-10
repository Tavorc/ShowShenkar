package il.ac.shenkar.showshenkar.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.showshenkar.showshenkar.R;

import java.util.ArrayList;

import il.ac.shenkar.showshenkar.model.DepImageItem;

public class DepGridViewAdapter extends ArrayAdapter<DepImageItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<DepImageItem> data = new ArrayList<DepImageItem>();

    public DepGridViewAdapter(Context context, int layoutResourceId, ArrayList<DepImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

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


        DepImageItem item = data.get(position);
        holder.imageTitle.setText(item.getTitle());
        Bitmap bitmap = BitmapFactory.decodeResource(row.getResources(), item.getImageResource());
        holder.image.setImageBitmap(bitmap);
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}