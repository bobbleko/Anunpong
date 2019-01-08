package anunpongi.th.ac.rmutl.whateat;

import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //    Explicit
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Create Toolber
        createToolber();

//        Add Fragment
        addFragment();

//        Home Controller
        homeController();

//        AboutMe Controller
        aboutMeController();

//        Info Controller
        infoController();

//        logout Controller
        logoutController();

//            Exit Controller
        exitController();

//
    } //Main Method

    private void logoutController() {
        TextView textView = findViewById(R.id.txtlogout);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentMain , new InfoFragment()).commit();
                drawerLayout.closeDrawers();
            }
        });
    }


    private void exitController() {
        TextView textView = findViewById(R.id.txtExit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void infoController() {
        TextView textView = findViewById(R.id.txtInfo);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentMain, new InfoFragment()).commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void aboutMeController() {
        TextView textView = findViewById(R.id.txtmanucalorie);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentMain, new AboutMeFragment()).commit();
                drawerLayout.closeDrawers();

            }
        });
    }

    private void homeController() {
        TextView textView = findViewById(R.id.txtHome);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.contentFragmentMain, new MainFragment()).commit();
                drawerLayout.closeDrawers();
            }
        });
    }

    private void addFragment() {
        if (checkSQLite()) {
//            Have Datebase
            getSupportFragmentManager().beginTransaction().add(R.id.contentFragmentMain, new MainFragment()).commit();
        } else {
//            No Database
            getSupportFragmentManager().beginTransaction().add(R.id.contentFragmentMain, new FormFragment()).commit();
        }
    }

    private boolean checkSQLite() {

        boolean result = false;

        try {


            MyManage myManage = new MyManage(MainActivity.this);
            SQLiteDatabase sqLiteDatabase = MainActivity.this.openOrCreateDatabase(MyOpenHelper.database_name, MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABER",null);
            cursor.moveToFirst();
            if (cursor.getCount() == 0) {
                result = false;
            } else {
                result = true;
            }
            cursor.close();

            Log.d("27decV1", "result = " + result);

            return result;
        } catch (Exception e) {

            Log.d("27decV1", "e = " + e.toString());
            e.printStackTrace();
            return false;
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();

    }

    private void createToolber() {
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_toolber));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_action_hambager);

        drawerLayout = findViewById(R.id.deawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


    }

}   //Main Class
