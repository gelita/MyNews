package com.fanikiosoftware.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.Toast;

import com.fanikiosoftware.mynews.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *query activity for searching api (article search)
 */
public class QueryActivity extends AppCompatActivity {

    @BindView(R.id.dpStart)
    DatePicker dpStart;
    @BindView(R.id.dpEnd)
    DatePicker dpEnd;
    @BindView(R.id.etSearch)
    TextInputEditText etSearch;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.check1)
    AppCompatCheckBox check1;
    @BindView(R.id.check2)
    AppCompatCheckBox check2;
    @BindView(R.id.check3)
    AppCompatCheckBox check3;
    @BindView(R.id.check4)
    AppCompatCheckBox check4;
    @BindView(R.id.check5)
    AppCompatCheckBox check5;
    @BindView(R.id.check6)
    AppCompatCheckBox check6;
    @BindView(R.id.notificationSwitch)
    Switch notificationSwitch;
    String title;
    private DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar;
    public static final String TAG = "QueryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "  :: QueryActivity onCreate called");
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
        getActivityTitle();
        setSwitch(title);
        setTitle(title);
        setUpListeners();
        getDate();
    }

    private void getDate() {
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
//        dpStart.setText(sdf.format(myCalendar.getTime()));
    }

    private void setUpListeners() {
        Log.d(TAG, "  :: QueryActivity setting up listeners");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QueryResultsActivity.class);
                intent.putStringArrayListExtra("userQueryList", getQuery());
                System.out.println(TAG + " userquery = " + getQuery());
                startActivity(intent);
            }
        });

        dpStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        dpEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getApplicationContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private ArrayList<String> getQuery() {
        //if search is empty then display error to user
        ArrayList<String> userQueryList = new ArrayList<>();
        userQueryList.add(etSearch.getText().toString()); //this adds an element to the list.
        if (userQueryList.isEmpty()) {
            Toast.makeText
                    (getApplicationContext(), R.string.search_term_required, Toast.LENGTH_LONG).show();
            return null;
        } else {
            if (check1.isChecked()) {
                userQueryList.add(check1.getText().toString());
            }
            if (check2.isChecked()) {
                userQueryList.add(check2.getText().toString());
            }
            if (check3.isChecked()) {
                userQueryList.add(check3.getText().toString());
            }
            if (check4.isChecked()) {
                userQueryList.add(check4.getText().toString());
            }
            if (check5.isChecked()) {
                userQueryList.add(check5.getText().toString());
            }
            if (check6.isChecked()) {
                userQueryList.add(check6.getText().toString());
            }
            if (userQueryList.size() < 1) {
                //if no category selected - notify user to choose at least one
                Toast.makeText(this, "Please select at least one category.", Toast.LENGTH_SHORT).show();
                return null;
            } else {
                Log.d(TAG, "  :: QueryActivity userQueryList: " + userQueryList);
                return userQueryList;
            }
        }
    }

    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent();
        String t = "";
        switch (item.getItemId()) {
            case R.id.miBackPress:
                intent = new Intent(getBaseContext(), MainActivity.class);
                break;
            case R.id.miAbout:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                t = "About";
                break;
            case R.id.miHelp:
                intent = new Intent(getBaseContext(), InfoActivity.class);
                t = "Help";
                break;
            case R.id.miSearch:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                t = "Search";
                break;
            default:
                break;
        }
        //add intent extra int to identify which button click called the activity
        intent.putExtra("title", t);
        startActivity(intent);
        //allow processing of menu item to carry on - return false per documentation
        return false;
    }

    public void getActivityTitle() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
    }

    //remove notifications switch if on Search activity else remove Submit btn if on Notifications
    private void setSwitch(String s) {
        if (s.equals("Search")) {
            notificationSwitch.setVisibility(View.GONE);
        } else if (s.equals("Notifications")) {
            dpStart.setVisibility(View.GONE);
            dpEnd.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
        }
    }
}