package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMakePostBinding;

import java.util.List;

public class MakePostActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.PREFERENCES_KEY";
    ActivityMakePostBinding binding;
    private EditText mPostDescription;
    private EditText mPostLink;

    private  Button mPostButton;

    private  Button mCancelButton;
    private GymLogDAO mGymLogDAO;

    private List<GymLog> mGymLogList;

    private int mUserId = -1;
    private SharedPreferences mPreferences = null;
    private User mUser;

    //private static int mUserID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
        getDatabase();
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        mUser = mGymLogDAO.getUserByUserId(mUserId);

        //mPostDescription = findViewById(R.id.postContents);
        //mPostLink = findViewById(R.id.postLink);
        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Post newPost = new Post (mUserId, mPostDescription.getText().toString(), mPostLink.getText().toString());
                //mGymLogDAO.insert(newPost);
                Intent intent;
                if(mUser.isAdmin()){
                    intent = AdminDashActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                }else{
                    intent = UserDashActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                }
                startActivity(intent);
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(mUser.isAdmin()){
                    intent = AdminDashActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                }else{
                    intent = UserDashActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                }
                startActivity(intent);
            }
        });




    }


    private void getDatabase() {
        mGymLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().GymLogDAO();

    }

    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, MakePostActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

}