package com.benmohammad.mvp.data.network.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogResponse {

    @Expose
    @SerializedName("status_code")
    private String statusCode;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private List<Blog> data;

    public String getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Blog> getData() {
        return data;
    }

    public void setData(List<Blog> data) {
        this.data = data;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof  BlogResponse)) return false;
         BlogResponse that = (BlogResponse) obj;

         if(!(statusCode.equals(that.statusCode))) return false;
         if(!message.equals(that.message)) return false;

         return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        int result = statusCode.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + data.hashCode();

        return result;
    }

    public static class Blog {
        @Expose
        @SerializedName("blog_url")
        private String blogUrl;

        @Expose
        @SerializedName("img_url")
        private String coverImgUrl;

        @Expose
        @SerializedName("title")
        private String title;

        @Expose
        @SerializedName("description")
        private String description;

        @Expose
        @SerializedName("author")
        private String author;

        @Expose
        @SerializedName("published_at")
        private String date;

        public String getBlogUrl() {
            return blogUrl;
        }

        public void setBlogUrl(String blogUrl) {
            this.blogUrl = blogUrl;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if(this == obj)return true;
            if(!(obj instanceof Blog)) return false;

            Blog blog = (Blog) obj;

            if(!blog.equals(blog.blogUrl)) return false;
            if(!coverImgUrl.equals(blog.coverImgUrl)) return false;
            if(!title.equals(blog.title))return false;
            if(!description.equals(blog.description)) return false;
            if(!author.equals(blog.author)) return false;

            return date.equals(blog.date);

        }

        @Override
        public int hashCode() {
            int result = blogUrl.hashCode();
            result = 31 * result + coverImgUrl.hashCode();
            result = 31 * result +title.hashCode();
            result = 31 * result + description.hashCode();
            result = 31 * result + author.hashCode();
            result = 31 * result + date.hashCode();
            return result;
        }
    }


}