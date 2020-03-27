package com.benmohammad.mvp.ui.feed.blogs;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.benmohammad.mvp.R;
import com.benmohammad.mvp.data.network.model.BlogResponse;
import com.benmohammad.mvp.ui.base.BaseViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BlogAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;
    public static final int VIEW_TYPE_NORMAL = 1;

    private Callback callback;

    private List<BlogResponse.Blog> blogList;

    public BlogAdapter(List<BlogResponse.Blog> blogList) {
        this.blogList = blogList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
            return new ViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_view, parent, false));
            case VIEW_TYPE_EMPTY:
            default:
                return new EmptyViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_view, parent, false));
        }
    }

    public void addItems(List<BlogResponse.Blog> blogList) {
        this.blogList.addAll(blogList);
        notifyDataSetChanged();

    }


    @Override
    public int getItemViewType(int position) {
        if(blogList != null && blogList.size() > 0) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        if(blogList != null && blogList.size() > 0) {
            return blogList.size();
        } else {
            return 1;
        }
    }

    public interface Callback {
        void onBlogEmptyViewRetryClick();
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.cover_image_view)
        ImageView coverImageView;

        @BindView(R.id.title_text_view)
        TextView titleTextView;

        @BindView(R.id.author_text_view)
        TextView authorTextView;

        @BindView(R.id.date_text_view)
        TextView dateTextView;

        @BindView(R.id.content_text_view)
        TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {

            coverImageView.setImageDrawable(null);
            titleTextView.setText("");
            contentTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final BlogResponse.Blog blog = blogList.get(position);

            if(blog.getCoverImgUrl() != null) {
                Glide.with(itemView.getContext()).asBitmap().load(blog.getCoverImgUrl()).into(coverImageView);
            }

            if(blog.getTitle() != null) {
                titleTextView.setText(blog.getTitle());
            }

            if(blog.getAuthor() != null) {
                authorTextView.setText(blog.getAuthor());
            }

            if(blog.getDate() != null) {
                dateTextView.setText(blog.getDate());
            }

            if(blog.getDescription() != null) {
                contentTextView.setText(blog.getDescription());
            }

            itemView.setOnClickListener(v  -> {
                if(blog.getBlogUrl() != null) {
                    try  {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.addCategory(Intent.CATEGORY_BROWSABLE);
                        intent.setData(Uri.parse(blog.getBlogUrl()));
                        itemView.getContext().startActivity(intent);
                    } catch(Exception e) {
                        Log.e("BlogAdapter", "url error: " + blog.getBlogUrl());
                    }
                }
            });
        }
    }


    public class EmptyViewHolder extends BaseViewHolder {
        @BindView(R.id.btn_retry)
        Button retryButton;

        @BindView(R.id.tv_message)
        TextView messageTextView;

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void clear() {

        }

        @OnClick(R.id.btn_retry)
        void onRetryClick() {
            if(callback != null) {
                callback.onBlogEmptyViewRetryClick();
            }
        }
    }

}
