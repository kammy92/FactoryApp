package com.karman.factoryapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.karman.factoryapp.R;
import com.karman.factoryapp.utils.*;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Bundle savedInstanceState;
    AppDetailsPref appDetailsPref;
    UserDetailsPref userDetailsPref;
    ProgressDialog progressDialog;
    CoordinatorLayout clMain;
    RelativeLayout rlBack;
    private AccountHeader headerResult = null;
    private Drawer result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
        initApplication();
        isLogin();
        initDrawer();
    }

    private void isLogin() {
        if (userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.USER_TOKEN).length() == 0) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity (intent);
            finish ();
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    private void initListener() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.openDrawer();
            }
        });
    }

    private void initView() {
        rlBack = (RelativeLayout) findViewById(R.id.rlBack);
        clMain = (CoordinatorLayout) findViewById(R.id.clMain);
    }

    private void initData() {
        Utils.setTypefaceToAllViews(this, clMain);
        appDetailsPref = AppDetailsPref.getInstance();
        userDetailsPref = UserDetailsPref.getInstance();

        progressDialog = new ProgressDialog(this);

/*

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (true) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }
//        Window window = getWindow ();
//        if (Build.VERSION.SDK_INT >= 21) {
//            window.clearFlags (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags (WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor (ContextCompat.getColor (this, R.color.text_color_white));
//        }
*/
    }

    private void initFirstFragment () {
        FragmentTransaction transaction = getSupportFragmentManager ().beginTransaction ();
   //     transaction.replace (R.id.frame_layout, OffersFragment.newInstance (false));
   //     transaction.commit ();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initDrawer() {
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withTypeface(SetTypeFace.getTypeface(MainActivity.this))
                .withPaddingBelowHeader(false)
                .withSelectionListEnabled(false)
                .withSelectionListEnabledForSingleProfile(false)
                .withProfileImagesVisible(true)
                .withDividerBelowHeader(true)
                .withTextColor(getResources().getColor(R.color.primary_text))
                .withOnlyMainProfileImageVisible(false)
                .withDividerBelowHeader(true)
                .withHeaderBackground(R.color.app_background)
                .withSavedInstance(savedInstanceState)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem();
        profileDrawerItem.withName("Karman Singh");//userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.USER_NAME));
        profileDrawerItem.withEmail("9811064922");//userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.USER_MOBILE));
        profileDrawerItem.withTypeface(SetTypeFace.getTypeface(MainActivity.this));

        if (userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.USER_IMAGE).length() > 0) {
            profileDrawerItem.withIcon(userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.USER_IMAGE));
        } else {
            profileDrawerItem.withIcon(R.drawable.ic_profile_male);
        }
        headerResult.addProfiles(profileDrawerItem);


        DrawerBuilder drawerBuilder = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withSavedInstance(savedInstanceState)
                .withActivity(this)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return false;
                    }
                });


        switch (1) {//appDetailsPref.getIntPref (MainActivity.this, UserDetailsPref.USER_TYPE)) {
            case 1:
                drawerBuilder.addDrawerItems(
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName("Home").withIcon(FontAwesome.Icon.faw_home).withIdentifier(1).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this)),
                        new PrimaryDrawerItem().withName("My Projects").withIcon(FontAwesome.Icon.faw_wordpress).withIdentifier(2).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this)),
                        new PrimaryDrawerItem().withName("My Employees").withIcon(FontAwesome.Icon.faw_wordpress).withIdentifier(9).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this)),
                        new PrimaryDrawerItem().withName("Feedback").withIcon(FontAwesome.Icon.faw_star).withIdentifier(5).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this)),
                        new PrimaryDrawerItem().withName("Change Password").withIcon(FontAwesome.Icon.faw_asterisk).withIdentifier(6).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this)),
                        new PrimaryDrawerItem().withName("Sign Out").withIcon(FontAwesome.Icon.faw_sign_out).withIdentifier(7).withSelectable(false).withTypeface(SetTypeFace.getTypeface(MainActivity.this))
                );
                break;
        }


        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.with(imageView.getContext()).clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder(ctx, tag);
            }
        });

/*
        DrawerImageLoader.init (new AbstractDrawerImageLoader () {
            @Override
            public void set (ImageView imageView, Uri uri, Drawable placeholder) {
                if (uri != null) {
                    Glide.with (imageView.getContext ()).load (uri).into (imageView);
                }
            }
            
            @Override
            public void cancel (ImageView imageView) {
            }
            
            @Override
            public Drawable placeholder (Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name ().equals (tag)) {
                    return DrawerUIUtils.getPlaceHolder (ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name ().equals (tag)) {
                    return new IconicsDrawable(ctx).iconText (" ").backgroundColorRes (com.mikepenz.materialdrawer.R.color.colorPrimary).sizeDp (56);
                } else if ("customUrlItem".equals (tag)) {
                    return new IconicsDrawable (ctx).iconText (" ").backgroundColorRes (R.color.md_white_1000);
                }
                
                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()
                
                return super.placeholder (ctx, tag);
            }
        });
        
        headerResult = new AccountHeaderBuilder()
                .withActivity (this)
                .withCompactStyle (false)
                .withTypeface (SetTypeFace.getTypeface (MainActivity.this))
                .withPaddingBelowHeader (false)
                .withSelectionListEnabled (false)
                .withSelectionListEnabledForSingleProfile (false)
                .withProfileImagesVisible (true)
                .withDividerBelowHeader (true)
                .withTextColor (getResources ().getColor (R.color.primary_text))
                .withOnlyMainProfileImageVisible (false)
                .withDividerBelowHeader (true)
                .withHeaderBackground (R.color.secondary_text)
                .withSavedInstance (savedInstanceState)
                .withOnAccountHeaderListener (new AccountHeader.OnAccountHeaderListener () {
                    @Override
                    public boolean onProfileChanged (View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build ();
    
        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem ();
        profileDrawerItem.withName (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_NAME));
        profileDrawerItem.withEmail (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_MOBILE));
    
    
        if (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_IMAGE).length () > 0) {
            profileDrawerItem.withIcon (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_IMAGE));
        } else {
                profileDrawerItem.withIcon (R.drawable.ic_profile_male);
        }
        headerResult.addProfiles (profileDrawerItem);
    
        //case 1: employee
        //case 2: admin
        //case 3: project manager
    
        DrawerBuilder drawerBuilder = new DrawerBuilder ()
                .withActivity (this)
                .withAccountHeader (headerResult)
                .withSavedInstance (savedInstanceState)
                .withOnDrawerItemClickListener (new Drawer.OnDrawerItemClickListener () {
                    @Override
                    public boolean onItemClick (View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier ()) {
                            case 2:
//                                Intent intent2 = new Intent(MainActivity.this, MyProjectActivity.class);
//                                startActivity (intent2);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 3:
                                break;
                            case 5:
//                                Intent intent7 = new Intent(MainActivity.this, FeedbackActivity.class);
//                                startActivity (intent7);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 6:
//                                Intent intent6 = new Intent(MainActivity.this, ChangePasswordActivity.class);
//                                startActivity (intent6);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 7:
                                showLogOutDialog ();
                                break;
                            case 8:
//                                Intent intent8 = new Intent(MainActivity.this, MyProfileActivity.class);
//                                startActivity (intent8);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 9:
//                                Intent intent9 = new Intent(MainActivity.this, MyEmployeesActivity.class);
//                                startActivity (intent9);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 10:
//                                Intent intent10 = new Intent(MainActivity.this, LeavePortalActivity.class);
//                                startActivity (intent10);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                        }
                        return
        DrawerImageLoader.init (new AbstractDrawerImageLoader () {
            @Override
            public void set (ImageView imageView, Uri uri, Drawable placeholder) {
                if (uri != null) {
                    Glide.with (imageView.getContext ()).load (uri).into (imageView);
                }
            }

            @Override
            public void cancel (ImageView imageView) {
            }

            @Override
            public Drawable placeholder (Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name ().equals (tag)) {
                    return DrawerUIUtils.getPlaceHolder (ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name ().equals (tag)) {
                    return new IconicsDrawable(ctx).iconText (" ").backgroundColorRes (com.mikepenz.materialdrawer.R.color.colorPrimary).sizeDp (56);
                } else if ("customUrlItem".equals (tag)) {
                    return new IconicsDrawable (ctx).iconText (" ").backgroundColorRes (R.color.md_white_1000);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder (ctx, tag);
            }
        });

        headerResult = new AccountHeaderBuilder()
                .withActivity (this)
                .withCompactStyle (false)
                .withTypeface (SetTypeFace.getTypeface (MainActivity.this))
                .withPaddingBelowHeader (false)
                .withSelectionListEnabled (false)
                .withSelectionListEnabledForSingleProfile (false)
                .withProfileImagesVisible (true)
                .withDividerBelowHeader (true)
                .withTextColor (getResources ().getColor (R.color.primary_text))
                .withOnlyMainProfileImageVisible (false)
                .withDividerBelowHeader (true)
                .withHeaderBackground (R.color.secondary_text)
                .withSavedInstance (savedInstanceState)
                .withOnAccountHeaderListener (new AccountHeader.OnAccountHeaderListener () {
                    @Override
                    public boolean onProfileChanged (View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build ();

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem ();
        profileDrawerItem.withName (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_NAME));
        profileDrawerItem.withEmail (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_MOBILE));


        if (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_IMAGE).length () > 0) {
            profileDrawerItem.withIcon (userDetailsPref.getStringPref (MainActivity.this, UserDetailsPref.USER_IMAGE));
        } else {
                profileDrawerItem.withIcon (R.drawable.ic_profile_male);
        }
        headerResult.addProfiles (profileDrawerItem);

        //case 1: employee
        //case 2: admin
        //case 3: project manager

        DrawerBuilder drawerBuilder = new DrawerBuilder ()
                .withActivity (this)
                .withAccountHeader (headerResult)
                .withSavedInstance (savedInstanceState)
                .withOnDrawerItemClickListener (new Drawer.OnDrawerItemClickListener () {
                    @Override
                    public boolean onItemClick (View view, int position, IDrawerItem drawerItem) {
                        switch ((int) drawerItem.getIdentifier ()) {
                            case 2:
//                                Intent intent2 = new Intent(MainActivity.this, MyProjectActivity.class);
//                                startActivity (intent2);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 3:
                                break;
                            case 5:
//                                Intent intent7 = new Intent(MainActivity.this, FeedbackActivity.class);
//                                startActivity (intent7);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 6:
//                                Intent intent6 = new Intent(MainActivity.this, ChangePasswordActivity.class);
//                                startActivity (intent6);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 7:
                                showLogOutDialog ();
                                break;
                            case 8:
//                                Intent intent8 = new Intent(MainActivity.this, MyProfileActivity.class);
//                                startActivity (intent8);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 9:
//                                Intent intent9 = new Intent(MainActivity.this, MyEmployeesActivity.class);
//                                startActivity (intent9);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                            case 10:
//                                Intent intent10 = new Intent(MainActivity.this, LeavePortalActivity.class);
//                                startActivity (intent10);
//                                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                                break;
                        }
                        return false;
                    }
                });

        switch (appDetailsPref.getIntPref (MainActivity.this, UserDetailsPref.USER_TYPE)) {
            case 1:
                drawerBuilder.addDrawerItems (
                        new PrimaryDrawerItem ().withName ("Home").withIcon (FontAwesome.Icon.faw_home).withIdentifier (1).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("My Projects").withIcon (FontAwesome.Icon.faw_wordpress).withIdentifier (2).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("My Employees").withIcon (FontAwesome.Icon.faw_wordpress).withIdentifier (9).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Feedback").withIcon (FontAwesome.Icon.faw_star).withIdentifier (5).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Change Password").withIcon (FontAwesome.Icon.faw_asterisk).withIdentifier (6).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Sign Out").withIcon (FontAwesome.Icon.faw_sign_out).withIdentifier (7).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this))
                );
                break;
        }
        result = drawerBuilder.build ();

  false;
                    }
                });
        
        switch (appDetailsPref.getIntPref (MainActivity.this, UserDetailsPref.USER_TYPE)) {
            case 1:
                drawerBuilder.addDrawerItems (
                        new PrimaryDrawerItem ().withName ("Home").withIcon (FontAwesome.Icon.faw_home).withIdentifier (1).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("My Projects").withIcon (FontAwesome.Icon.faw_wordpress).withIdentifier (2).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("My Employees").withIcon (FontAwesome.Icon.faw_wordpress).withIdentifier (9).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Feedback").withIcon (FontAwesome.Icon.faw_star).withIdentifier (5).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Change Password").withIcon (FontAwesome.Icon.faw_asterisk).withIdentifier (6).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this)),
                        new PrimaryDrawerItem ().withName ("Sign Out").withIcon (FontAwesome.Icon.faw_sign_out).withIdentifier (7).withSelectable (false).withTypeface (SetTypeFace.getTypeface (MainActivity.this))
                );
                break;
        }


  */

        result = drawerBuilder.build();
    }

    private void showLogOutDialog() {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .contentColor(getResources().getColor(R.color.primary_text))
                .positiveColor(getResources().getColor(R.color.primary_text))
                .negativeColor(getResources().getColor(R.color.primary_text))
                .content("Do you wish to Sign Out?")
                .positiveText("Yes")
                .negativeText("No")
                .typeface(SetTypeFace.getTypeface(MainActivity.this), SetTypeFace.getTypeface(MainActivity.this))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        userDetailsPref.putStringPref(MainActivity.this, UserDetailsPref.USER_NAME, "");
                        userDetailsPref.putStringPref(MainActivity.this, UserDetailsPref.USER_MOBILE, "");
                        userDetailsPref.putStringPref(MainActivity.this, UserDetailsPref.USER_EMAIL, "");
                        userDetailsPref.putStringPref(MainActivity.this, UserDetailsPref.USER_IMAGE, "");
                        userDetailsPref.putStringPref(MainActivity.this, UserDetailsPref.USER_TOKEN, "");
                        userDetailsPref.putIntPref(MainActivity.this, UserDetailsPref.USER_TYPE, 0);
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }).build();
        dialog.show();
    }
    
    /*
    public void getProjectList3 () {
        if (NetworkConnection.isNetworkAvailable (MainActivity.this)) {
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.HOME, true);
            
            AndroidNetworking.get (AppConfigURL.HOME)
                    .doNotCacheResponse ()
                    .getResponseOnlyFromNetwork ()
                    .addHeaders (AppConfigTags.HEADER_API_KEY, Constants.api_key)
                    .addHeaders (AppConfigTags.HEADER_EMPLOYEE_LOGIN_KEY, appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_LOGIN_KEY))
                    .build ()
                    .getAsString (new StringRequestListener () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    projectList.clear ();
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! is_error) {
                                        JSONArray jsonArray = jsonObj.getJSONArray (AppConfigTags.PROJECTS);
                                        projects_json = jsonObj.getJSONArray (AppConfigTags.PROJECTS).toString ();
                                        for (int i = 0; i < jsonArray.length (); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject (i);
                                            Project project = new Project (
                                                    jsonObject.getInt (AppConfigTags.PROJECT_ID),
                                                    jsonObject.getString (AppConfigTags.PROJECT_TITLE),
                                                    jsonObject.getString (AppConfigTags.CLIENT_NAME),
                                                    jsonObject.getString (AppConfigTags.PROJECT_CREATED_BY),
                                                    jsonObject.getString (AppConfigTags.PROJECT_HOURS)
                                            );
                                            projectList.add (i, project);
                                        }
                                        
                                        if (jsonArray.length () > 0) {
                                            rlNoResultFound.setVisibility (View.GONE);
                                        } else {
                                            rlNoResultFound.setVisibility (View.VISIBLE);
                                        }
                                        
                                        
                                        projectAdapter.notifyDataSetChanged ();
                                    } else {
                                        Utils.showSnackBar (MainActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace ();
                                    Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                }
                            } else {
                                Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            swipeRefreshLayout.setRefreshing (false);
                        }
                        
                        @Override
                        public void onError (ANError anError) {
                            swipeRefreshLayout.setRefreshing (false);
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, anError.toString (), true);
                            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    });
        } else {
            swipeRefreshLayout.setRefreshing (false);
            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }
    }
    
    public void getProjectList2 () {
        if (NetworkConnection.isNetworkAvailable (MainActivity.this)) {
            Utils.showLog (Log.INFO, AppConfigTags.URL, AppConfigURL.HOME, true);
            AndroidNetworking.get (AppConfigURL.HOME)
                    .doNotCacheResponse ()
                    .getResponseOnlyFromNetwork ()
                    .addHeaders (AppConfigTags.HEADER_API_KEY, Constants.api_key)
                    .addHeaders (AppConfigTags.HEADER_EMPLOYEE_LOGIN_KEY, appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_LOGIN_KEY))
                    .build ()
                    .getAsString (new StringRequestListener () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    projectList.clear ();
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean is_error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! is_error) {
                                        JSONArray jsonArray = jsonObj.getJSONArray (AppConfigTags.PROJECTS);
                                        projects_json = jsonObj.getJSONArray (AppConfigTags.PROJECTS).toString ();
                                        for (int i = 0; i < jsonArray.length (); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject (i);
                                            Project project = new Project (
                                                    jsonObject.getInt (AppConfigTags.PROJECT_ID),
                                                    jsonObject.getString (AppConfigTags.PROJECT_TITLE),
                                                    jsonObject.getString (AppConfigTags.CLIENT_NAME),
                                                    jsonObject.getString (AppConfigTags.PROJECT_CREATED_BY),
                                                    jsonObject.getString (AppConfigTags.PROJECT_HOURS)
                                            );
                                            projectList.add (i, project);
                                        }
                                        
                                        if (jsonArray.length () > 0) {
                                            rlNoResultFound.setVisibility (View.GONE);
                                        } else {
                                            rlNoResultFound.setVisibility (View.VISIBLE);
                                        }
                                        
                                        
                                        projectAdapter.notifyDataSetChanged ();
                                    } else {
                                        Utils.showSnackBar (MainActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace ();
                                    Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                }
                            } else {
                                Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            swipeRefreshLayout.setRefreshing (false);
                        }
                        
                        @Override
                        public void onError (ANError anError) {
                            swipeRefreshLayout.setRefreshing (false);
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, anError.toString (), true);
                            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                        }
                    });
        } else {
            swipeRefreshLayout.setRefreshing (false);
            Utils.showSnackBar (MainActivity.this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    Intent dialogIntent = new Intent (Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity (dialogIntent);
                }
            });
        }
    }
    */

    private void initApplication() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        final TimeZone tz = TimeZone.getDefault();
        Log.e("karman", "TimeZone   " + tz.getDisplayName(false, TimeZone.SHORT) + " Timezon id :: " + tz.getID());

        if (NetworkConnection.isNetworkAvailable(this)) {
            Utils.showLog(Log.INFO, AppConfigTags.URL, AppConfigURL.URL_INIT, true);
            final PackageInfo finalPInfo = pInfo;
            StringRequest strRequest = new StringRequest(Request.Method.POST, AppConfigURL.URL_INIT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utils.showLog(Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            /*
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    boolean error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
    
                                    if (! error) {
        
                                        boolean flag = false;
                                        for (String ext : new String[] {".png", ".jpg", ".jpeg"}) {
                                            if (jsonObj.getString (AppConfigTags.EMPLOYEE_IMAGE).endsWith (ext)) {
                                                flag = true;
                                                break;
                                            }
                                        }
        
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_NAME, jsonObj.getString (AppConfigTags.EMPLOYEE_NAME));
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_MOBILE, jsonObj.getString (AppConfigTags.EMPLOYEE_MOBILE));
                                        appDetailsPref.putIntPref (MainActivity.this, AppDetailsPref.EMPLOYEE_TYPE, jsonObj.getInt (AppConfigTags.EMPLOYEE_TYPE));
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_WORK_EMAIL, jsonObj.getString (AppConfigTags.EMPLOYEE_WORK_EMAIL));
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_GENDER, jsonObj.getString (AppConfigTags.EMPLOYEE_GENDER));
        
                                        if (flag) {
                                            appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_IMAGE, jsonObj.getString (AppConfigTags.EMPLOYEE_IMAGE));
                                        } else {
                                            appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_IMAGE, "");
                                        }
        
                                        server_date = jsonObj.getString (AppConfigTags.SERVER_DATE);
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                        String date1 = sdf.format (sdf.parse (server_date));
                                        String date2 = sdf.format (Calendar.getInstance ().getTime ());
                                        if (! date1.equalsIgnoreCase (date2)) {
                                            MaterialDialog dialog = new MaterialDialog.Builder (MainActivity.this)
                                                    .title ("Incorrect Date Found")
                                                    .content ("Please update the date/time of the device to current date in order to proceed")
                                                    .titleColor (getResources ().getColor (R.color.primary_text))
                                                    .positiveColor (getResources ().getColor (R.color.primary_text))
                                                    .contentColor (getResources ().getColor (R.color.primary_text))
                                                    .negativeColor (getResources ().getColor (R.color.primary_text))
                                                    .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                                                    .canceledOnTouchOutside (false)
                                                    .cancelable (false)
                                                    .positiveText (R.string.dialog_action_settings)
                                                    .negativeText (R.string.dialog_action_exit)
                                                    .onPositive (new MaterialDialog.SingleButtonCallback () {
                                                        @Override
                                                        public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                            startActivityForResult (new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), DATE_REQUEST_CODE);
                                                        }
                                                    })
                                                    .onNegative (new MaterialDialog.SingleButtonCallback () {
                                                        @Override
                                                        public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                            finish ();
                                                            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                                                        }
                                                    }).build ();
                                            dialog.show ();
                                        }

                                        if (jsonObj.getInt (AppConfigTags.VERSION_UPDATE) > 0) {
                                            if (jsonObj.getInt (AppConfigTags.VERSION_CRITICAL) == 1) {
                                                MaterialDialog dialog = new MaterialDialog.Builder (MainActivity.this)
                                                        .title ("New Update Available")
                                                        .content (jsonObj.getString (AppConfigTags.UPDATE_MESSAGE))
                                                        .titleColor (getResources ().getColor (R.color.primary_text))
                                                        .positiveColor (getResources ().getColor (R.color.primary_text))
                                                        .contentColor (getResources ().getColor (R.color.primary_text))
                                                        .negativeColor (getResources ().getColor (R.color.primary_text))
                                                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                                                        .canceledOnTouchOutside (false)
                                                        .cancelable (false)
                                                        .positiveText (R.string.dialog_action_update)
                                                        .negativeText (R.string.dialog_action_exit)
                                                        .onPositive (new MaterialDialog.SingleButtonCallback () {
                                                            @Override
                                                            public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                final String appPackageName = getPackageName ();
                                                                try {
                                                                    startActivity (new Intent(Intent.ACTION_VIEW, Uri.parse ("market://details?id=" + appPackageName)));
                                                                } catch (android.content.ActivityNotFoundException anfe) {
                                                                    startActivity (new Intent(Intent.ACTION_VIEW, Uri.parse ("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                                                }
                                                            }
                                                        })
                                                        .onNegative (new MaterialDialog.SingleButtonCallback () {
                                                            @Override
                                                            public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                finish ();
                                                                overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
                                                            }
                                                        }).build ();

                                                dialog.getActionButton (DialogAction.POSITIVE).setOnClickListener (new CustomListener (MainActivity.this, dialog, DialogAction.POSITIVE));
                                                dialog.getActionButton (DialogAction.NEGATIVE).setOnClickListener (new CustomListener (MainActivity.this, dialog, DialogAction.NEGATIVE));
                                                dialog.show ();
                                            } else {
                                                MaterialDialog dialog = new MaterialDialog.Builder (MainActivity.this)
                                                        .title ("New Update Available")
                                                        .content (jsonObj.getString (AppConfigTags.UPDATE_MESSAGE))
                                                        .titleColor (getResources ().getColor (R.color.primary_text))
                                                        .positiveColor (getResources ().getColor (R.color.primary_text))
                                                        .contentColor (getResources ().getColor (R.color.primary_text))
                                                        .negativeColor (getResources ().getColor (R.color.primary_text))
                                                        .typeface (SetTypeFace.getTypeface (MainActivity.this), SetTypeFace.getTypeface (MainActivity.this))
                                                        .canceledOnTouchOutside (true)
                                                        .cancelable (true)
                                                        .positiveText (R.string.dialog_action_update)
                                                        .negativeText (R.string.dialog_action_ignore)
                                                        .onPositive (new MaterialDialog.SingleButtonCallback () {
                                                            @Override
                                                            public void onClick (@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                                final String appPackageName = getPackageName ();
                                                                try {
                                                                    startActivity (new Intent(Intent.ACTION_VIEW, Uri.parse ("market://details?id=" + appPackageName)));
                                                                } catch (android.content.ActivityNotFoundException anfe) {
                                                                    startActivity (new Intent(Intent.ACTION_VIEW, Uri.parse ("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                                                }
                                                            }
                                                        }).build ();
                                                dialog.show ();
                                            }
                                        }

                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.CLIENTS, jsonObj.getJSONArray (AppConfigTags.CLIENTS).toString ());
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.EMPLOYEES, jsonObj.getJSONArray (AppConfigTags.EMPLOYEES).toString ());
                                        appDetailsPref.putStringPref (MainActivity.this, AppDetailsPref.ROLES, jsonObj.getJSONArray (AppConfigTags.ROLES).toString ());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            */
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Utils.showLog(Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString(), true);
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new Hashtable<>();
                    params.put(AppConfigTags.APP_VERSION, String.valueOf(finalPInfo.versionCode));
                    params.put(AppConfigTags.DEVICE, "ANDROID");
                    params.put(AppConfigTags.FIREBASE_ID, userDetailsPref.getStringPref(MainActivity.this, UserDetailsPref.FIREBASE));
                    params.put(AppConfigTags.TIMEZONE, tz.getID());
                    Utils.showLog(Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put(AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    // params.put (AppConfigTags.HEADER_EMPLOYEE_LOGIN_KEY, appDetailsPref.getStringPref (MainActivity.this, AppDetailsPref.EMPLOYEE_LOGIN_KEY));
                    Utils.showLog(Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            strRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            Utils.sendRequest(strRequest, 30);
        } else {
        }
    }

    class CustomListener implements View.OnClickListener {
        private final MaterialDialog dialog;
        Activity activity;
        DialogAction dialogAction;

        public CustomListener(Activity activity, MaterialDialog dialog, DialogAction dialogAction) {
            this.dialog = dialog;
            this.activity = activity;
            this.dialogAction = dialogAction;
        }

        @Override
        public void onClick(View v) {
            if (dialogAction == DialogAction.POSITIVE) {
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
            if (dialogAction == DialogAction.NEGATIVE) {
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        }
    }
}