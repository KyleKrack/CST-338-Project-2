package com.daclink.gymlog_v_sp22;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

import java.util.Date;

@Entity(tableName = AppDataBase.GYMLOG_TABLE)
public class GymLog {


    @PrimaryKey(autoGenerate = true)
    private int mLogID;

    private String mExercise;
    private double mWeight;
    private int mReps;

    private Date mDate;

    private int mUserId;

    public GymLog(String exercise, double weight, int reps, int userId) {
        mExercise = exercise;
        mWeight = weight;
        mReps = reps;

        mDate = new Date();
        mUserId = userId;
    }


    @Override
    public String toString() {
        return "Log # " + mLogID + "\n" +
                "User ID: " + mUserId + "\n" +
                "Exercise: " + mExercise + "\n" +
                "Weight: " + mWeight + "\n" +
                "Reps: " + mReps + "\n" +
                "Date: " + mDate + "\n" +
                "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n";
    }


    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getLogID() {
        return mLogID;
    }

    public void setLogID(int logID) {
        mLogID = logID;
    }

    public String getExercise() {
        return mExercise;
    }

    public void setExercise(String exercise) {
        mExercise = exercise;
    }

    public double getWeight() {
        return mWeight;
    }

    public void setWeight(double weight) {
        mWeight = weight;
    }

    public int getReps() {
        return mReps;
    }

    public void setReps(int reps) {
        mReps = reps;
    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
