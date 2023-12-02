package com.daclink.gymlog_v_sp22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.List;

public class MakePostActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.PREFERENCES_KEY";
    ActivityMainBinding binding;
    private EditText mExercise;
    private EditText mWeight;
    private   EditText mReps;

    private  Button mPostButton;

    private  Button mCancelButton;
    private GymLogDAO mGymLogDAO;

    private List<GymLog> mGymLogList;

    private int mUserId = -1;
    private SharedPreferences mPreferences = null;
    private User mUser;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_post);
    }








}