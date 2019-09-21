package com.karman.factoryapp.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.karman.factoryapp.R;
import com.karman.factoryapp.utils.*;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private CoordinatorLayout clMain;
    private ImageView ivLogo;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvForgotPassword;
    private TextView tvLogin;
    private TextView tvShowHide;
    private UserDetailsPref userDetailsPref;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initListeners();
        initData();
    }

    public void initView() {
        clMain = (CoordinatorLayout) findViewById(R.id.clMain);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        tvShowHide = (TextView) findViewById(R.id.tvShowHide);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
    }

    public void initData() {
        userDetailsPref = UserDetailsPref.getInstance();
        progressDialog = new ProgressDialog(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ivLogo.setClipToOutline(true);
        }

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
    }

    public void initListeners() {
        Utils.setTypefaceToAllViews(this, etUsername);
        tvLogin.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        tvShowHide.setOnClickListener(this);
        clMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Utils.hideSoftKeyboard(LoginActivity.this);
                return false;
            }
        });
    }

    private void showForgotPasswordDialog() {
        final MaterialDialog.Builder mBuilder = new MaterialDialog.Builder(LoginActivity.this)
                .content("Enter your User Id")
                .contentColor(getResources().getColor(R.color.primary_text))
                .positiveColor(getResources().getColor(R.color.primary_text))
                .negativeColor(getResources().getColor(R.color.primary_text))
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .typeface(SetTypeFace.getTypeface(LoginActivity.this), SetTypeFace.getTypeface(LoginActivity.this))
                .alwaysCallInputCallback()
                .canceledOnTouchOutside(true)
                .cancelable(true)
                .negativeText(getResources().getString(R.string.dialog_action_cancel))
                .positiveText(getResources().getString(R.string.dialog_action_ok))
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        //      forgotPassword (dialog.getInputEditText ().getText ().toString ().trim ());
                    }
                });

        mBuilder.input(null, null, new MaterialDialog.InputCallback() {
            @Override
            public void onInput(MaterialDialog dialog, CharSequence input) {
                if (input.toString().length() > 0) {
                    dialog.getInputEditText().setError(null);
                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(true);
                } else {
                    dialog.getActionButton(DialogAction.POSITIVE).setEnabled(false);
                }
            }
        });

        MaterialDialog dialog = mBuilder.build();
        dialog.show();
    }

    public boolean validation() {
        if (TextUtils.isEmpty(etUsername.getText().toString().trim()) || etUsername.getText().toString().trim() == null && TextUtils.isEmpty(etPassword.getText().toString().trim()) || etPassword.getText().toString().trim() == null) {
            Toast.makeText(this, "Please fill UserId & Password", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(etUsername.getText().toString().trim()) || etUsername.getText().toString().trim() == null) {
            Toast.makeText(this, "Please fill UserId", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText().toString().trim()) || etPassword.getText().toString().trim() == null) {
            Toast.makeText(this, "Please fill Password", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin:
                if (validation()) {
                    sendLoginDetailsToServer(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                }
                break;
            case R.id.tvForgotPassword:
                showForgotPasswordDialog();
                break;
            case R.id.tvShowHide:
                if (etPassword.getText().toString().trim().length() > 0)
                    if (tvShowHide.getText().toString().equalsIgnoreCase("SHOW")) {
                        tvShowHide.setText("HIDE");
                        //  ed_password.setInputType (InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        etPassword.setSelection(etPassword.getText().length());
                        etPassword.setTypeface(SetTypeFace.getTypeface(LoginActivity.this));
                    } else {
                        // ed_password.setInputType (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        etPassword.setSelection(etPassword.getText().length());
                        etPassword.setTypeface(SetTypeFace.getTypeface(LoginActivity.this));
                        tvShowHide.setText("SHOW");
                    }
                break;
        }
    }

    private void sendLoginDetailsToServer(final String username, final String password) {
        if (NetworkConnection.isNetworkAvailable(LoginActivity.this)) {
            Utils.showProgressDialog(LoginActivity.this, progressDialog, getResources().getString(R.string.progress_dialog_text_please_wait), true);
            Utils.showLog(Log.INFO, AppConfigTags.URL, AppConfigURL.LOGIN, true);
            StringRequest strRequest1 = new StringRequest(Request.Method.POST, AppConfigURL.LOGIN,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Utils.showLog(Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response).getJSONObject(AppConfigTags.DATA);

                                    userDetailsPref.putStringPref(LoginActivity.this, UserDetailsPref.USER_NAME, jsonObject.getString(AppConfigTags.USER_NAME));
                                    userDetailsPref.putStringPref(LoginActivity.this, UserDetailsPref.USER_EMAIL, jsonObject.getString(AppConfigTags.USER_EMAIL));
                                    userDetailsPref.putStringPref(LoginActivity.this, UserDetailsPref.USER_MOBILE, jsonObject.getString(AppConfigTags.USER_MOBILE));
                                    userDetailsPref.putStringPref(LoginActivity.this, UserDetailsPref.USER_IMAGE, jsonObject.getString(AppConfigTags.USER_IMAGE));
                                    userDetailsPref.putStringPref(LoginActivity.this, UserDetailsPref.USER_TOKEN, jsonObject.getString(AppConfigTags.USER_TOKEN));
                                    userDetailsPref.putIntPref(LoginActivity.this, UserDetailsPref.USER_TYPE, jsonObject.getInt(AppConfigTags.USER_TYPE));
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                } catch (Exception e) {
                                    Utils.showSnackBar(LoginActivity.this, clMain, getResources().getString(R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace();
                                }
                            } else {
                                Utils.showSnackBar(LoginActivity.this, clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                Utils.showLog(Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss();
                        }
                    },
                    new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Utils.showLog(Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString(), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                try {
                                    JSONObject jsonObject = new JSONObject(new String(response.data)).getJSONObject(AppConfigTags.ERROR);
                                    Utils.showLog(Log.ERROR, AppConfigTags.ERROR, jsonObject.toString(), true);
                                    Utils.showSnackBar(LoginActivity.this, clMain, "Error: " + jsonObject.getString(AppConfigTags.ERROR_TYPE) + " (Code: " + jsonObject.getInt(AppConfigTags.ERROR_CODE) + ")", Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                } catch (Exception e) {
                                    Utils.showSnackBar(LoginActivity.this, clMain, getResources().getString(R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_dismiss), null);
                                }
                            }

                            progressDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String>();
                    params.put(AppConfigTags.LOGIN_USERNAME, username);
                    params.put(AppConfigTags.LOGIN_PASSWORD, password);
                    Utils.showLog(Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    PackageInfo pInfo = null;
                    try {
                        pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                    Map<String, String> params = new HashMap<>();
                    params.put(AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    params.put(AppConfigTags.HEADER_DEVICE_ID, String.valueOf(pInfo.versionCode));
                    params.put(AppConfigTags.HEADER_DEVICE_TYPE, Constants.device_type);
                    Utils.showLog(Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest(strRequest1, 60);
        } else {
            Utils.showSnackBar(this, clMain, getResources().getString(R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources().getString(R.string.snackbar_action_go_to_settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialogIntent = new Intent(Settings.ACTION_SETTINGS);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(dialogIntent);
                }
            });
        }

    }

    /*
    private void forgotPassword (final String username) {
        if (NetworkConnection.isNetworkAvailable (LoginActivity.this)) {
            Utils.showProgressDialog (LoginActivity.this, progressDialog, getResources ().getString (R.string.progress_dialog_text_please_wait), true);
            Utils.showLog (Log.INFO, "" + AppConfigTags.URL, AppConfigURL.FORGOT_PASSWORD, true);
            StringRequest strRequest1 = new StringRequest (Request.Method.POST, AppConfigURL.FORGOT_PASSWORD,
                    new com.android.volley.Response.Listener<String> () {
                        @Override
                        public void onResponse (String response) {
                            Utils.showLog (Log.INFO, AppConfigTags.SERVER_RESPONSE, response, true);
                            if (response != null) {
                                try {
                                    JSONObject jsonObj = new JSONObject (response);
                                    boolean error = jsonObj.getBoolean (AppConfigTags.ERROR);
                                    String message = jsonObj.getString (AppConfigTags.MESSAGE);
                                    if (! error) {
                                        Utils.showSnackBar (LoginActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    } else {
                                        Utils.showSnackBar (LoginActivity.this, clMain, message, Snackbar.LENGTH_LONG, null, null);
                                    }
                                    progressDialog.dismiss ();
                                } catch (Exception e) {
                                    progressDialog.dismiss ();
                                    Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_exception_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                    e.printStackTrace ();
                                }
                            } else {
                                Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                                Utils.showLog (Log.WARN, AppConfigTags.SERVER_RESPONSE, AppConfigTags.DIDNT_RECEIVE_ANY_DATA_FROM_SERVER, true);
                            }
                            progressDialog.dismiss ();
                        }
                    },
                    new com.android.volley.Response.ErrorListener () {
                        @Override
                        public void onErrorResponse (VolleyError error) {
                            Utils.showLog (Log.ERROR, AppConfigTags.VOLLEY_ERROR, error.toString (), true);
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                Utils.showLog (Log.ERROR, AppConfigTags.ERROR, new String (response.data), true);
                            }
                            Utils.showSnackBar (LoginActivity.this, clMain, getResources ().getString (R.string.snackbar_text_error_occurred), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_dismiss), null);
                            progressDialog.dismiss ();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams () throws AuthFailureError {
                    Map<String, String> params = new Hashtable<String, String> ();
                    params.put (AppConfigTags.USERNAME, username);
                    Utils.showLog (Log.INFO, AppConfigTags.PARAMETERS_SENT_TO_THE_SERVER, "" + params, true);
                    return params;
                }
    
                @Override
                public Map<String, String> getHeaders () throws AuthFailureError {
                    Map<String, String> params = new HashMap<> ();
                    params.put (AppConfigTags.HEADER_API_KEY, Constants.api_key);
                    Utils.showLog (Log.INFO, AppConfigTags.HEADERS_SENT_TO_THE_SERVER, "" + params, false);
                    return params;
                }
            };
            Utils.sendRequest (strRequest1, 60);
        } else {
            Utils.showSnackBar (this, clMain, getResources ().getString (R.string.snackbar_text_no_internet_connection_available), Snackbar.LENGTH_LONG, getResources ().getString (R.string.snackbar_action_go_to_settings), new View.OnClickListener () {
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
            if (dialogAction == DialogAction.NEGATIVE) {
                dialog.dismiss();
            } else if (dialogAction == DialogAction.POSITIVE) {
                if (dialog.getInputEditText().getText().toString().length() > 0) {
                    dialog.dismiss();
                } else {
                    dialog.getInputEditText().setError("Invalid Email");
                }
            }
        }
    }
}