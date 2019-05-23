package com.fanikiosoftware.mynews.Controllers.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.fanikiosoftware.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueryActivity extends AppCompatActivity {

    @BindView(R.id.etSearch) EditText etSearch;
    @BindView(R.id.etDateStart) EditText  etDateStart;
    @BindView(R.id.etDateEnd) EditText etDateEnd;
    @BindView(R.id.check1) AppCompatCheckBox check1;
    @BindView(R.id.check2) AppCompatCheckBox check2;
    @BindView(R.id.check3) AppCompatCheckBox check3;
    @BindView(R.id.check4) AppCompatCheckBox check4;
    @BindView(R.id.check5) AppCompatCheckBox check5;
    @BindView(R.id.check6) AppCompatCheckBox check6;
    int tag;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        getActivityTitle();
        setTitle(title);
    }

    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        //create tag identifier for each menu item
        tag = 0;
        switch (item.getItemId()) {
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                tag = 10;
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                tag = 20;
                break;
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                tag = 30;
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                tag = 40;
                break;
            default:
                break;
        }
        //add intent extra int to identify which button click called the activity
        intent.putExtra("tag", tag);
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }

    public String getActivityTitle() {
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", 0);
        if (tag == 30) {
            title = "Notifications";
        } else {
            title = "Search";
        }
        return title;
    }
}
