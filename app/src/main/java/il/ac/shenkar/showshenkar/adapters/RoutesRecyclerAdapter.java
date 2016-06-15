package il.ac.shenkar.showshenkar.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.activities.DepartmentActivity;
import il.ac.shenkar.showshenkar.model.Route;

public class RoutesRecyclerAdapter extends RecyclerView.Adapter<RoutesRecyclerAdapter.CustomViewHolder> {
    private List<Route> routes;
    private Context mContext;

    public RoutesRecyclerAdapter(Context context, List<Route> routes) {
        this.routes = routes;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.route_row, null);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Route route = routes.get(i);

        customViewHolder.txtRouteName.setText(route.getName());
    }

    @Override
    public int getItemCount() {
        return (null != routes ? routes.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView txtRouteName;

        public CustomViewHolder(View view) {
            super(view);
            this.txtRouteName = (TextView) view.findViewById(R.id.route_name);
            txtRouteName.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Create intent
            Intent intent = new Intent(mContext, DepartmentActivity.class);
            intent.putExtra("title", txtRouteName.getText().toString());

            // TODO: place the right image per route
            intent.putExtra("image", R.drawable.image_1);

            //Start details activity
            mContext.startActivity(intent);
        }
    }
}