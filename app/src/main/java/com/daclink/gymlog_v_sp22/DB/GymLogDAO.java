package com.daclink.gymlog_v_sp22.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.daclink.gymlog_v_sp22.GymLog;
import com.daclink.gymlog_v_sp22.User;
import  com.daclink.gymlog_v_sp22.Post;
import com.daclink.gymlog_v_sp22.Message;

import java.util.List;

@Dao
public interface GymLogDAO {

   @Insert
   void insert(GymLog... gymLogs);

   @Update
    void update(GymLog... gymLogs);

   @Delete
    void delete(GymLog gymLog);

    @Query("SELECT * FROM " + AppDataBase.GYMLOG_TABLE + " ORDER BY mDate desc")
    List<GymLog> getAllGymLogs();
    @Query("SELECT * FROM " + AppDataBase.GYMLOG_TABLE +" WHERE mLogID = :logId")
    List<GymLog> getGymLogsById(int logId);


    @Query("SELECT * FROM " + AppDataBase.GYMLOG_TABLE +" WHERE mUserId = :userId ORDER BY mDate desc")
    List<GymLog> getGymLogsByUserId(int userId);

    @Insert
    void insert (User... users);

    @Update
    void update( User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE )
    List<User> getAllUsers();

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserName = :username ")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDataBase.USER_TABLE + " WHERE mUserId = :userId ")
    User getUserByUserId(int userId);



    @Insert
    void insert (Post... posts);

    @Update
    void update (Post... posts);

    @Delete
    void delete (Post post);

    @Query("SELECT * FROM " + AppDataBase.POST_TABLE + " ORDER BY mDatePostMade desc")
    List<Post> getAllPosts();
    @Query("SELECT * FROM " + AppDataBase.POST_TABLE +" WHERE mPostUserID = :logId")
    List<Post> getPostsByID(int logId);




 @Insert
 void insert (Message... messages);

 @Update
 void update (Message... messages);

 @Delete
 void delete (Message message);

 @Query("SELECT * FROM " + AppDataBase.MESSAGE_TABLE + " WHERE mReceiver = :receiver ")
 List<Message> getAllMessagesBySentToUser(int receiver);
 @Query("SELECT * FROM " + AppDataBase.MESSAGE_TABLE +" WHERE mMessageID = :logId")
 List<Message> getMessagesByID(int logId);





}
