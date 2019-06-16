package com.fanikiosoftware.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.fanikiosoftware.mynews.R;

import java.nio.FloatBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *query activity for searching api (article search)
 */
public class QueryActivity extends AppCompatActivity {

    @BindView(R.id.tvEndDateSpinner)
    TextView tvEndDateSpinner;
    @BindView(R.id.tvStartDateSpinner)
    TextView tvStartDateSpinner;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.etDateStartLabel)
    EditText etDateStart;
    @BindView(R.id.etDateEndLabel)
    EditText etDateEnd;
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
    public String query = "";
    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        tvStartDateSpinner.setText(sdf.format(myCalendar.getTime()));
    }

    private void setUpListeners() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuery();
            }
        });

        tvEndDateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        tvStartDateSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getApplicationContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void getQuery() {

        query = etSearch.getText().toString();
        if (query.equals("")) {
            Toast.makeText(getApplicationContext(), R.string.search_term_required, Toast.LENGTH_SHORT).show();
        } else if (!query.isEmpty()) {
            System.out.println("Search term: " + query);
            //confirm that at least ONE checkbox is checked or give feedback to user
            if (!check1.isChecked() && !check2.isChecked() && !check3.isChecked() &&
                    !check4.isChecked() && !check5.isChecked() && !check6.isChecked()) {
                Toast.makeText(getApplicationContext(), R.string.checkbox_required, Toast.LENGTH_SHORT).show();
            }
        }
    }


    //create menu options
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.miNotifications:
                intent = new Intent(getBaseContext(), QueryActivity.class);
                t = "Notifications";
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

    public String getActivityTitle() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        return title;
    }

    //remove notifications switch if on Search activity else remove Submit btn if on Notifications
    private void setSwitch(String s) {
        if (s.equals("Search")) {
            notificationSwitch.setVisibility(View.GONE);
        } else if (s.equals("Notifications")) {
            btnSubmit.setVisibility(View.GONE);
        }
    }
}
