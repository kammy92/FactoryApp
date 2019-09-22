package com.karman.factoryapp.controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karman.factoryapp.app.AppController;
import com.karman.factoryapp.callbacks.VolleyCallback;
import com.karman.factoryapp.utils.NetworkRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyController {
    public static final String TAG = VolleyController.class.getSimpleName();
    private static VolleyController mInstance;
    private RequestQueue mRequestQueue;
    private Context mCtx;

    private VolleyController(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyController(context);
        }
        return mInstance;
    }

    public void makeGetRequest(final String url, Map<String, String> headers, final VolleyCallback callback) {
        NetworkRequest rq = new NetworkRequest(Request.Method.GET,
                url, null, headers,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Response", response.toString());
                        try {
                            callback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Response", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError) {
                            err = "No internet Access!";
                        }
                        try {
                            if (err != "null") {
                                callback.onError(err);
                            } else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("header 8", "header 8 value");
                headers.put("header 9", "header 9 value");
                Log.e("karman", "header in post 2 " + headers);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

        };
        rq.setPriority(Request.Priority.HIGH);
        VolleyController.getInstance(mCtx.getApplicationContext()).addToRequestQueue(rq);
    }

    public void makePostRequest(final String url, Map<String, String> params, Map<String, String> headers, final VolleyCallback callback) {
        NetworkRequest rq = new NetworkRequest(Request.Method.POST,
                url, params, headers,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Response", response.toString());
                        try {
                            callback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Response", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError) {
                            err = "No internet Access!";
                        }
                        try {
                            if (err != "null") {
                                callback.onError(err);
                            } else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        rq.setPriority(Request.Priority.HIGH);
        VolleyController.getInstance(mCtx.getApplicationContext()).addToRequestQueue(rq);
    }

    public void makeRequest(final String url, final VolleyCallback callback) {
        NetworkRequest rq = new NetworkRequest(Request.Method.GET,
                url, null, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("Response", response.toString());
                        try {
                            callback.onSuccess(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("Response", error.toString());
                        String err = null;
                        if (error instanceof com.android.volley.NoConnectionError) {
                            err = "No internet Access!";
                        }
                        try {
                            if (err != "null") {
                                callback.onError(err);
                            } else {
                                callback.onError(error.toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        rq.setPriority(Request.Priority.HIGH);
        VolleyController.getInstance(mCtx.getApplicationContext()).addToRequestQueue(rq);
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void sendRequest(StringRequest strRequest, int timeout_seconds) {
        strRequest.setShouldCache(false);
        AppController.getInstance().getRequestQueue().getCache().clear();
        int timeout = timeout_seconds * 1000;
        AppController.getInstance().addToRequestQueue(strRequest);
        strRequest.setRetryPolicy(new DefaultRetryPolicy(timeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}