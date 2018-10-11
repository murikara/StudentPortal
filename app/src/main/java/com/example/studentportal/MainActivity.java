package com.example.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PortalAdapter.PortalClickListener {

    @BindView(R.id.addListButton)
    FloatingActionButton addListButton;

    @BindView(R.id.portalRecyclerView)
    RecyclerView portalRecyclerView;

    private PortalAdapter portalAdapter;
    private ArrayList<Portal> portalArrayList;
    //Constants used when calling the update activity
    public static final String EXTRA_PORTAL = "Portal";
    public static final String URL = "URL";
    public static final int REQUESTCODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind all views with the ButterKnife library which uses the annotations
        ButterKnife.bind(this);

        portalArrayList = new ArrayList<>();
        portalAdapter = new PortalAdapter(portalArrayList, this);
        portalRecyclerView.setAdapter(portalAdapter);
        // set 3 columns in the recyclerview layout
        portalRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    /**
     * Deze methode vangt de data van het tweede scherm op en verwerkt dit
     * @param requestCode de code waarop wordt gecontroleerd
     * @param resultCode als het goed is gegaan
     * @param data de data die terug wordt gestuurd
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == Activity.RESULT_OK) {
                Portal newPortal = data.getParcelableExtra(MainActivity.EXTRA_PORTAL);
                Log.e(newPortal.getTitel(), newPortal.getUrl());
                // New timestamp: timestamp of update
                portalArrayList.add(newPortal);
                portalAdapter.notifyDataSetChanged();
                Log.e(""+portalAdapter.getItemCount(), ""+ portalArrayList.size());
            }
        }
    }

    /**
     * Dit is de onclicklistener voor de add button, gemaakt met de library ButterKnife
     */
    @OnClick(R.id.addListButton)
    public void addList(){
        startActivityForResult(new Intent(this, AddPortalActivity.class), REQUESTCODE);
    }

    /**
     * Deze methode start de webview op basis van geklikte portal
     * @param i het object in de lijst waarop wordt geklikt
     */
    @Override
    public void portalOnClick(int i) {
        Log.e("MAINPORTAL", String.valueOf(i));
        Intent intent = new Intent (this, WebViewActivity.class);
        intent.putExtra(MainActivity.URL, portalArrayList.get(i).getUrl());
        startActivity(intent);
    }
}
