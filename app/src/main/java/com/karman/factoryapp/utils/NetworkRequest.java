package com.karman.factoryapp.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class NetworkRequest extends Request<JSONObject> {

    Priority mPriority;
    private Response.Listener<JSONObject> listener;
    private Map<String, String> params = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<String, String>();

    public NetworkRequest(int method, String url, Map<String, String> params, Map<String, String> headers, Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = responseListener;
        this.params = params;
        this.headers = headers;
        setDefaultHeaders();
    }

    public void setDefaultHeaders() {
        this.headers.put("Header 1", "Header 1 Value");
        this.headers.put("Header 2", "Header 2 Value");
        this.headers.put("Header 3", "Header 3 Value");
        this.headers.put("Header 4", "Header 4 Value");
        this.headers.put("Header 5", "Header 5 Value");
    }

    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params;
    }

    ;

    public Map<String, String> getHeaders() throws AuthFailureError {
        Log.e("karman", "header sent " + headers);
        return headers;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        // TODO Auto-generated method stub
        listener.onResponse(response);
    }

    @Override
    public Priority getPriority() {
        // If we didn't use the setPriority method,
        // the priority is automatically set to NORMAL
        return mPriority != null ? mPriority : Priority.NORMAL;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

}