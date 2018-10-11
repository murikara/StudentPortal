package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPortalActivity extends AppCompatActivity {

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.titelEditText)
    EditText mTitelEditText;

    @BindView(R.id.addPortalButton)
    Button mAddPortalButton;

    String urlText;
    String titelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);
        ButterKnife.bind(this);

        //terug knop
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    /**
     * Deze methode voegt een portal toe door middel van het doorsturen van de ingevulde
     * data naar de main activity
     * @param view een view
     */
    @OnClick(R.id.addPortalButton)
    public void addPortal(View view) {
        Portal portal;
        urlText = mUrlEditText.getText().toString();
        titelText = mTitelEditText.getText().toString();
        if (!TextUtils.isEmpty(urlText) && !TextUtils.isEmpty(titelText)) {
            portal = new Portal(urlText, titelText);
            //Prepare the return parameter and return
            Intent resultIntent = new Intent();
            resultIntent.putExtra(MainActivity.EXTRA_PORTAL, portal);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        } else {
            Snackbar.make(view, "Enter some data first bro", Snackbar.LENGTH_LONG);
        }
    }
}
