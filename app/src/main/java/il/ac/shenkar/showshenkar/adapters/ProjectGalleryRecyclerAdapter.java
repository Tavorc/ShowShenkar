package il.ac.shenkar.showshenkar.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.backend.contentApi.model.Media;
import il.ac.shenkar.showshenkar.utils.DownloadImageTask;

public class ProjectGalleryRecyclerAdapter extends RecyclerView.Adapter<ProjectGalleryRecyclerAdapter.CustomViewHolder> {
    private final ImageView mMainImage;
    private List<Media> mImages;
    private Context mContext;

    public ProjectGalleryRecyclerAdapter(Context context, ImageView mainImage, List<Media> mImages) {
        this.mImages = mImages;
        this.mContext = context;
        this.mMainImage = mainImage;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.project_tumb, null);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Media image = mImages.get(i);

        new DownloadImageTask(customViewHolder.imgTumb).execute(image.getUrl());
    }

    @Override
    public int getItemCount() {
        return (null != mImages ? mImages.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView imgTumb;

        public CustomViewHolder(View view) {
            super(view);
            this.imgTumb = (ImageView) view.findViewById(R.id.imgTumb);
            imgTumb.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ImageView image = (ImageView) v;
            mMainImage.setImageBitmap(((BitmapDrawable) image.getDrawable()).getBitmap());
        }
    }

    public void refresh(final List<Media> medias) {
        boolean isNew = mImages.isEmpty();
        mImages.clear();
        for (Media media : medias) {
            if (media.getType() == "image") {
                mImages.add(media);
            }
        }

        if (isNew && !mImages.isEmpty())
        {
            new DownloadImageTask(mMainImage).execute(mImages.get(0).getUrl());
        }

        notifyDataSetChanged();

    }
}