package com.example.broilr;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Stack;


public class MainActivity extends Activity {

    Button mLikeButton;
    Button mDislikeButton;
    FragmentManager fm = getFragmentManager();
    FoodProfileFragment fragment = (FoodProfileFragment) fm.findFragmentById(R.id.foodProfileContainer);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (fragment == null) {
            fragment = new FoodProfileFragment();
            fm.beginTransaction()
                    .add(R.id.foodProfileContainer, fragment)
                    .commit();
        }

        mLikeButton = (Button) findViewById(R.id.like_button);
        mDislikeButton = (Button) findViewById(R.id.dislike_button);

        mDislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment.foodProfileStackSize() > 0) {
                    Toast dislikeToast = Toast.makeText(MainActivity.this, R.string.dislike_toast, Toast.LENGTH_SHORT);
                    Utils.showCustomToast(dislikeToast);
                }
                fragment.loadNewProfile();
            }
        });

        mLikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment.foodProfileStackSize() > 0) {
                    Toast likeToast = Toast.makeText(MainActivity.this, R.string.like_toast, Toast.LENGTH_SHORT);
                    Utils.showCustomToast(likeToast);
                }
                fragment.loadNewProfile();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem matchesItem = menu.findItem(R.id.matches_button);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.matches_button) {
            // Launch view matches activity from here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void showCustomToast(Toast toast) {
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 500);
        LinearLayout toastLayout = (LinearLayout) toast.getView();
        TextView toastTV = (TextView) toastLayout.getChildAt(0);
        toastTV.setTextSize(30);
        toast.show();
    }
}
