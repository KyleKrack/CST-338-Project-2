package com.daclink.gymlog_v_sp22.DB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.daclink.gymlog_v_sp22.GymLog;
import com.daclink.gymlog_v_sp22.User;

@Database(entities = {GymLog.class, User.class}, version = 3)
@TypeConverters(DateTypeConverter.class)

public abstract class AppDataBase extends RoomDatabase {

    public static final String DATABASE_NAME = "GymLog.gb";
    public static final String GYMLOG_TABLE = "gymLog_table";

    public static final String USER_TABLE = "USER_TABLE";

    private static volatile AppDataBase instance;

    private static final Object Lock = new Object();

    public abstract GymLogDAO GymLogDAO();


    public static AppDataBase getInstance(Context context){
        if(instance == null){
            synchronized (Lock){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class,
                            DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }

}
