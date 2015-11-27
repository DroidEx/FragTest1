package com.braviot.mytestfrag;


import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements FirstFragment.OnFragmentInteractionListener {

    private Button btn1;
    private Button btn2;
    private int page = 1;

    FirstFragment fr1;
    SecondFragment fr2;
    FragmentManager fm;
    android.app.FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        fr1 = FirstFragment.newInstance(page);
        fr2 = SecondFragment.newInstance(page);

        fm = getFragmentManager();
        addFragment(fr1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  changeFragment(fr2);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(fr1);
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment f) {

        ft = fm.beginTransaction();
        ft.replace(R.id.fragcontainer, f);
        ft.commitAllowingStateLoss();
    }

    private void addFragment(Fragment f){
        ft = fm.beginTransaction();
        ft.add(R.id.fragcontainer,f);
        ft.commitAllowingStateLoss();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Toast.makeText(this, (CharSequence) uri, Toast.LENGTH_SHORT).show();
    }
}
