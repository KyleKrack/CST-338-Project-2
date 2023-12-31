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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.gymlog_v_sp22.DB.AppDataBase;
import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityAdminDashBinding;
//import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.Date;
import java.util.List;

public class AdminDashActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.PREFERENCES_KEY";
    ActivityAdminDashBinding binding;
    private TextView mPostDisplay;
    private TextView mMessageDisplay;
    private GymLogDAO mGymLogDAO;
    private List<Post> mPostList;
    private List<Message> mMessageList;
    private int mUserId = -1;
    private SharedPreferences mPreferences = null;
    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dash);

        getDatabase();
        //mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        checkForUser();
        addUserToPreference(mUserId);
        loginUser(mUserId);

        defaultPosts();

        //mUserId = getIntent().getIntExtra(USER_ID_KEY, -1);
        //Post defaultPost = new Post(mUserId, "Need money for food",  "@kyle");
        //mGymLogDAO.insert(defaultPost);
        //addUserToPreference(mUserId);
        //loginUser(mUserId);

        binding = ActivityAdminDashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mPostDisplay = binding.postDisplay;
        mMessageDisplay = binding.messagesDisplay;
        mPostDisplay.setMovementMethod(new ScrollingMovementMethod());
        mMessageDisplay.setMovementMethod(new ScrollingMovementMethod());

        refreshDisplay();
    }

    private void defaultPosts() {
        //mPostList.clear();
        //mMessageList.clear();
        List<Post> mPostList = mGymLogDAO.getAllPosts();
        List<Message> mMessageList = mGymLogDAO.getAllMessagesBySentToUser(mUserId);
        if(mPostList.size() <= 0 ){
            Post defaultPost = new Post(1, "Need money for food",  "@kylelynn");
            Post defaultPost2 = new Post(2, "rent is due soon!",  "@kyle");
            Post defaultPost3 = new Post(4, "These med prices are killing me!!",  "@admin2");
            Post defaultPost4 = new Post(3, "car broke down :<",  "@defaultuser1");

            mGymLogDAO.insert(defaultPost,defaultPost2, defaultPost3, defaultPost4);
        }


        if(mMessageList.size() <= 0 ){

            Message defaultMessage = new Message(2,1,"I can help with food! - kyle");
            Message defaultMessage2 = new Message(1,2,"I can help with rent! - kylelynn");
            Message defaultMessage3 = new Message(3,4,"I can help you admin! - testuser1");
            Message defaultMessage4 = new Message(4,3,"I can help you user! - admin2");
            mGymLogDAO.insert(defaultMessage, defaultMessage2,defaultMessage3,defaultMessage4);

        }

    }


    public void wireUpDisplay(){

    }
    private void refreshDisplay(){
        //mPostList.clear();
        //mMessageList.clear();

        mPostList = mGymLogDAO.getAllPosts();
        mMessageList = mGymLogDAO.getAllMessagesBySentToUser(mUserId);

        if(!mPostList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Post log : mPostList){
                sb.append(log.toString());
            }
            mPostDisplay.setText(sb.toString());
        }else{
            mPostDisplay.setText(R.string.no_logs_message);
        }


        mMessageList = mGymLogDAO.getAllMessagesBySentToUser(mUserId);
        //mMessageList = mGymLogDAO.get

        if(!mMessageList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(Message log : mMessageList){
                sb.append(log.toString());
            }
            mMessageDisplay.setText(sb.toString());
        }else{
            mMessageDisplay.setText("Somebody will contact you soon!"+ mUserId);
        }


    }
    private void getDatabase() {
        mGymLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build().GymLogDAO();

    }

    private void loginUser(int userId) {
        mUser = mGymLogDAO.getUserByUserId(userId);
        invalidateOptionsMenu();
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





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutUser:
                Toast.makeText(this, "Logout user Selected", Toast.LENGTH_SHORT).show();
                logoutUser();
                return true;



            case R.id.makePost:
                Toast.makeText(this, "Make post selected", Toast.LENGTH_SHORT).show();
                Intent intent = MakePostActivity.intentFactory(getApplicationContext(), mUser.getUserId());
                startActivity(intent);
                return true;



            case R.id.sendMessage:
                Toast.makeText(this, "Send message selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.deletePost:
                Toast.makeText(this, "Delete a post selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.aboutPage:
                Toast.makeText(this, "About page selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.subitem1:
                Toast.makeText(this, "What is mutual aid selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this, "How you can help selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(mUser != null){
            MenuItem item = menu.findItem(R.id.logoutUser);
            item.setTitle("logout " + mUser.getUserName());
        }
        return super.onPrepareOptionsMenu(menu);

    }




    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, AdminDashActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }


}