package com.example.hp.mymachinetest3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by hp on 19-07-2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;
    private OnItemClickListener onItemClickListener;
    public MyRecyclerViewAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_raw, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int i) {
        final FeedItem feedItem = feedItemList.get(i);
        String img_url = "http://iroidtech.com/wecare/uploads/news_events/";

        //Download image using picasso library
        if (!TextUtils.isEmpty(feedItem.getThumbnail())) {
            Picasso.with(mContext)

                    /*.load(feedItem.getThumbnail())*/
                    .load(img_url+feedItem.getThumbnail())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.imageView);
        }

        //Setting text view title
        holder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        holder.imv.setText(Html.fromHtml(feedItem.getArray()));


      /*  View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(feedItem);
            }
        };
        holder.imageView.setOnClickListener(listener);
        holder.textView.setOnClickListener(listener);*/
      holder.setOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(View item, int position) {
              Intent ii=new Intent(mContext,SecondActivity.class);
              ii.putExtra("image",feedItem.getThumbnail().toString());
              ii.putExtra("text1",feedItem.getTitle().toString());
              ii.putExtra("text2",feedItem.getArray().toString());
              mContext.startActivity(ii);

          }
      });

    }


    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView imv;
        private OnItemClickListener onItemClickListener;

        public CustomViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.imv = (TextView) view.findViewById(R.id.tv);

        }

        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }


}
