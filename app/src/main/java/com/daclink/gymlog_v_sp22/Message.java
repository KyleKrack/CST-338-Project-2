package com.daclink.gymlog_v_sp22;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

@Entity(tableName = AppDataBase.MESSAGE_TABLE)
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int mMessageID;

    private int mSender;
    private int mReciever;
    private String mMessageText;


    public Message(int messageID, int sender, int reciever, String messageText) {
        mMessageID = messageID;
        mSender = sender;
        mReciever = reciever;
        mMessageText = messageText;
    }


    @Override
    public String toString() {
        return "Message{" +
                "mMessageID=" + mMessageID +
                ", mSender=" + mSender +
                ", mReciever=" + mReciever +
                ", mMessageText='" + mMessageText + '\'' +
                '}';
    }

    public int getMessageID() {
        return mMessageID;
    }

    public void setMessageID(int messageID) {
        mMessageID = messageID;
    }

    public int getSender() {
        return mSender;
    }

    public void setSender(int sender) {
        mSender = sender;
    }

    public int getReciever() {
        return mReciever;
    }

    public void setReciever(int reciever) {
        mReciever = reciever;
    }

    public String getMessageText() {
        return mMessageText;
    }

    public void setMessageText(String messageText) {
        mMessageText = messageText;
    }
}
