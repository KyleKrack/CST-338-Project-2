package com.daclink.gymlog_v_sp22;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;

@Entity(tableName = AppDataBase.MESSAGE_TABLE)
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int mMessageID;

    private int mSender;
    private int mReceiver;
    private String mMessageText;


    public Message(int sender, int receiver, String messageText) {
        //mMessageID = messageID;
        mSender = sender;
        mReceiver = receiver;
        mMessageText = messageText;
    }


    @Override
    public String toString() {
        return "<3 <3 <3 <3 <3 <3 <3" +"\n" +
                "user " + mSender + ": " +"\n" +
                mMessageText +"\n" +
                "<3 <3 <3 <3 <3 <3 <3"+ "\n";
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

    public int getReceiver() {
        return mReceiver;
    }

    public void setReceiver(int receiver) {
        mReceiver = receiver;
    }

    public String getMessageText() {
        return mMessageText;
    }

    public void setMessageText(String messageText) {
        mMessageText = messageText;
    }
}
