package com.blogspot.androidgaidamak.globalmindassociatedtask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends Activity {
    public static final String IS_LOGGED_IN = "is_logged_in";
    // State is lost after recreation of activity
    // Not very secure
    private boolean mIsLoggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityFragment fragment = new MainActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_LOGGED_IN, false);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.fragmentHolder, fragment).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            if (mIsLoggedIn) {
                item.setTitle("Log in");
                Toast.makeText(this, "Items ARE NOT clickable", Toast.LENGTH_SHORT).show();
            } else {
                item.setTitle("Log out");
                Toast.makeText(this, "Items ARE clickable", Toast.LENGTH_SHORT).show();
            }
            mIsLoggedIn = !mIsLoggedIn;
            MainActivityFragment fragment = new MainActivityFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean(IS_LOGGED_IN, mIsLoggedIn);
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.fragmentHolder, fragment).commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
