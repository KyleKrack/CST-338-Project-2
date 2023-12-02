package com.daclink.gymlog_v_sp22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daclink.gymlog_v_sp22.DB.GymLogDAO;
import com.daclink.gymlog_v_sp22.databinding.ActivityMainBinding;

import java.util.List;

public class AdminDashActivity extends AppCompatActivity {

    private static final String USER_ID_KEY = "com.daclink.gymlog_v_sp22.userIdKey";
    private static final String PREFERENCES_KEY = "com.daclink.gymlog_v_sp22.PREFERENCES_KEY";
    ActivityMainBinding binding;
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


    }


    public void wireUpDisplay(){

    }
    private void refreshDisplay(){
    }
    private void getDatabase(){
    }
    private void clearUserFromIntent(){
    }
    private void clearUserFromPreferences(){}
    private void checkForUser(){}
    private void getPreferences(){}
    private void logoutUser(){}
    private void refresh(){}





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
                Toast.makeText(this, "Logout user Selected", Toast.LENGTH_SHORT).show();
                //logoutUser();
                return true;

            case R.id.viewPosts:
                Toast.makeText(this, "View post selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.makePost:
                Toast.makeText(this, "Make post selected", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.viewMessages:
                Toast.makeText(this, "View messages selected", Toast.LENGTH_SHORT).show();
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








    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, AdminDashActivity.class);
        //intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }


}