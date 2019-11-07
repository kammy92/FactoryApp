package com.karman.factoryapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.karman.factoryapp.R;
import com.karman.factoryapp.learning.activity.LearningActivity;
import com.karman.factoryapp.fragment.CustomerFragment;
import com.karman.factoryapp.fragment.HomeFragment;
import com.karman.factoryapp.fragment.ProductsFragment;
import com.karman.factoryapp.utils.Constants;

public class MainActivity2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private View navHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);

        // Loading profile image
        ImageView profileImage = navHeader.findViewById(R.id.profileImage);
        Glide.with(this).load(Constants.PROFILE_URL)
                .apply(RequestOptions.circleCropTransform())
                .thumbnail(0.5f)
                .into(profileImage);
        //Loading backgrounf image
        ImageView navBackground = navHeader.findViewById(R.id.img_header_bg);
        Glide.with(this).load(Constants.BACKGROUND_URL)
                .thumbnail(0.5f)
                .into(navBackground);

        //Select Home by default
        navigationView.setCheckedItem(R.id.nav_home);
        Fragment fragment = new CustomerFragment();
        displaySelectedFragment(fragment);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_customer) {
            fragment = new CustomerFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_products) {
            fragment = new ProductsFragment();
            displaySelectedFragment(fragment);

        } else if (id == R.id.nav_share) {
            //Display Share Via dialogue
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType(Constants.SHARE_TEXT_TYPE);
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Constants.SHARE_TITLE);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Constants.SHARE_MESSAGE);
            startActivity(Intent.createChooser(sharingIntent, Constants.SHARE_VIA));

        } else if (id == R.id.nav_visit_us) {
            //Open URL on click of Visit Us
            Intent intent = new Intent(MainActivity2.this, LearningActivity.class);
            startActivity(intent);
//            Intent urlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.SITE_URL));
//            startActivity(urlIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Loads the specified fragment to the frame
     *
     * @param fragment
     */
    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}