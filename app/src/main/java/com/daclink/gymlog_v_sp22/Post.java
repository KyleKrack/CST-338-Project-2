package com.daclink.gymlog_v_sp22;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.POST_TABLE)
public class Post {

    @PrimaryKey (autoGenerate = true)
    private int mPostUserID;
    private int mPostType;
    private String mPostDescription;

    private Date mDatePostMade;

    private String mPostUrgency;

    private String mPostUserLink;


    @Override
    public String toString() {
        return "Post{" +
                "mPostUserID=" + mPostUserID +
                ", mPostType=" + mPostType +
                ", mPostDescription='" + mPostDescription + '\'' +
                ", mDatePostMade=" + mDatePostMade +
                ", mPostUrgency='" + mPostUrgency + '\'' +
                ", mPostUserLink='" + mPostUserLink + '\'' +
                '}';
    }

    public Post(int postUserID, int postType, String postDescription, Date datePostMade, String postUrgency, String postUserLink) {
        mPostUserID = postUserID;
        mPostType = postType;
        mPostDescription = postDescription;
        mDatePostMade = datePostMade;
        mPostUrgency = postUrgency;
        mPostUserLink = postUserLink;
    }


    public int getPostUserID() {
        return mPostUserID;
    }

    public void setPostUserID(int postUserID) {
        mPostUserID = postUserID;
    }

    public int getPostType() {
        return mPostType;
    }

    public void setPostType(int postType) {
        mPostType = postType;
    }

    public String getPostDescription() {
        return mPostDescription;
    }

    public void setPostDescription(String postDescription) {
        mPostDescription = postDescription;
    }

    public Date getDatePostMade() {
        return mDatePostMade;
    }

    public void setDatePostMade(Date datePostMade) {
        mDatePostMade = datePostMade;
    }

    public String getPostUrgency() {
        return mPostUrgency;
    }

    public void setPostUrgency(String postUrgency) {
        mPostUrgency = postUrgency;
    }

    public String getPostUserLink() {
        return mPostUserLink;
    }

    public void setPostUserLink(String postUserLink) {
        mPostUserLink = postUserLink;
    }
}
