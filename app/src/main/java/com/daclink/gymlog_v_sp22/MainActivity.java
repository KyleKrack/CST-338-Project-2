package com.daclink.gymlog_v_sp22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.PREFERENCES_KEY";
    ActivityMainBinding binding;
    private TextView mMainDisplay;
    private  EditText mExercise;
    private EditText mWeight;
    private   EditText mReps;

    private  Button mSubmit;
    private GymLogDAO mGymLogDAO;

  private  List<GymLog> mGymLogList;

    private int mUserId = -1;
    private SharedPreferences mPreferences = null;
    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();
        checkForUser();
        addUserToPreference(mUserId);
        loginUser(mUserId);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mMainDisplay = binding.mainGymLogDisplay;
        mExercise = binding.mainExerciseEditText;
        mWeight = binding.mainWeightEditText;
        mReps = binding.mainRepsEditText;
        mSubmit = binding.mainSubmitButton;

        mMainDisplay.setMovementMethod(new ScrollingMovementMethod());

        refreshDisplay();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitGymLog();
                refreshDisplay();
            }
        });
    } //end of onCreate

    private void loginUser(int userId) {
        mUser = mGymLogDAO.getUserByUserId(userId);
        invalidateOptionsMenu();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mUser != null){
            MenuItem item = menu.findItem(R.id.logoutUser);
            item.setTitle(mUser.getUserName());
        }
        return super.onPrepareOptionsMenu(menu);

    }

    private void getDatabase() {
        mGymLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().GymLogDAO();

    }

    private void clearUserFromIntent(){
        getIntent().putExtra(USER_ID_KEY,-1);
    }

    private void clearUserFromPref(){
        addUserToPreference(-1);
    }



    private void addUserToPreference(int userId) {
        if(mPreferences==null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
    }


    private void checkForUser() {

        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);



        //do we have a user in the preferences?
        if(mUserId != -1){
            return;
        }

        //SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);


        if( mPreferences == null) {
            getPrefs();
        }
            mUserId = mPreferences.getInt(USER_ID_KEY, -1);

        if(mUserId != -1){
            return;
        }



        //do we have any users at all?
        List<User> users = mGymLogDAO.getAllUsers();

        if(users.size() <= 0 ){
            User defaultUser = new User("kylelynn", "123", true);
            User altUser = new User("kyle", "123", false);
            User testUser = new User("testuser1", "123", false);
            User testAdmin = new User("admin2","123", true);

            mGymLogDAO.insert(defaultUser, altUser, testUser,testAdmin);
        }

        Intent intent = LoginActivity.intentFactory(this);
        startActivity(intent);


    }

    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
    }


    private void logoutUser(){

        Toast.makeText(this, "Logout User Selected", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage(R.string.logout);

        alertBuilder.setPositiveButton(getString(R.string.yes),
            new DialogInterface.OnClickListener() {
            @Override
                public void onClick(DialogInterface dialog, int which){
                clearUserFromIntent();
                clearUserFromPref();
                mUserId = -1;
                checkForUser();
            }
            });

        alertBuilder.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        //we don't need to do anything here
                    }
                });

        alertBuilder.create();
        alertBuilder.show();

    }







    private void submitGymLog(){
        String exercise = mExercise.getText().toString();
        double weight = Double.parseDouble(mWeight.getText().toString());
        int reps = Integer.parseInt(mReps.getText().toString());

        GymLog log = new GymLog(exercise, weight, reps, mUserId);

        //log.setUserId(mUser.getUserId());

        mGymLogDAO.insert(log);

        
    }

    private void refreshDisplay(){
        mGymLogList = mGymLogDAO.getGymLogsById(mUserId);
        if(!mGymLogList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(GymLog log : mGymLogList){
                sb.append(log.toString());
            }
            mMainDisplay.setText(sb.toString());
        }else{
            mMainDisplay.setText(R.string.no_logs_message);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutUser:
                //Toast.makeText(this, "Logout User Selected", Toast.LENGTH_SHORT).show();
                logoutUser();
                return true;
            case R.id.makePost:
                Toast.makeText(this, "Make Post selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aboutPage:
                Toast.makeText(this, "item 3 selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem1:
                Toast.makeText(this, "subitem 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this, "subitem 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }




}