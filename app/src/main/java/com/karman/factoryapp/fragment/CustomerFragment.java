package com.karman.factoryapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.karman.factoryapp.R;
import com.karman.factoryapp.callbacks.VolleyCallback;
import com.karman.factoryapp.controller.VolleyController;
import com.karman.factoryapp.model.Customer;
import com.karman.factoryapp.model.CustomerResponse;
import com.karman.factoryapp.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CustomerFragment extends Fragment {
    Button button;
    VolleyController volleyController;
    CustomerResponse customerResponse;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Constants.TAG_CUSTOMER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_customer, container, false);

        volleyController = VolleyController.getInstance(getActivity());

        button = rootView.findViewById(R.id.bt1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("karman", "Test Click");
                Map<String, String> header = new HashMap<>();
                header.put("Header 0", "header 0");
                volleyController.makeGetRequest("https://reqres.in/api/users?page=2", header, new VolleyCallback() {
                    @Override
                    public void onSuccess(JSONObject result) throws JSONException {
                        JSONObject jsonObject = new JSONObject("{\n" +
                                "    \"data\": {\n" +
                                "        \"customers\": [\n" +
                                "            {\n" +
                                "                \"customer_id\": 1,\n" +
                                "                \"customer_name\": \"AMIT BANARAS\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 2,\n" +
                                "                \"customer_name\": \"ARSH MIXI\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 3,\n" +
                                "                \"customer_name\": \"ASHWANI\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 4,\n" +
                                "                \"customer_name\": \"BANGALI\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 5,\n" +
                                "                \"customer_name\": \"BHOLA\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 6,\n" +
                                "                \"customer_name\": \"DECENT ELECTRICAL\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"FARIDABAD\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 7,\n" +
                                "                \"customer_name\": \"DES RAJ\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 8,\n" +
                                "                \"customer_name\": \"FITWELL SPARES\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 9,\n" +
                                "                \"customer_name\": \"GINNI MIXI\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            },\n" +
                                "            {\n" +
                                "                \"customer_id\": 10,\n" +
                                "                \"customer_name\": \"GOYAL ENTERPRISES\",\n" +
                                "                \"customer_mobile\": \"\",\n" +
                                "                \"customer_email\": \"\",\n" +
                                "                \"customer_address\": \"\",\n" +
                                "                \"customer_status\": 1,\n" +
                                "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                                "            }\n" +
                                "        ],\n" +
                                "        \"total\": 55,\n" +
                                "        \"offset\": 0,\n" +
                                "        \"limit\": 10,\n" +
                                "        \"status\": 1,\n" +
                                "        \"search\": \"\",\n" +
                                "        \"success_type\": \"FetchedSuccessful\",\n" +
                                "        \"success_code\": 1111,\n" +
                                "        \"status_code\": 200,\n" +
                                "        \"message\": \"Customers fetched successfully.\",\n" +
                                "        \"more_info\": \"Customer Status : 0=> Inactive, 1=> Active. For more info please visit https://factory-app-cammy92.c9users.io/slim_final/1111\"\n" +
                                "    },\n" +
                                "    \"error\": {}\n" +
                                "}");
                        Log.e("karman", "JSON Response : " + jsonObject.toString());
                        Gson gson = new Gson();
                        customerResponse = gson.fromJson(jsonObject.toString(), CustomerResponse.class);

                        Log.e("karman", "response : " + customerResponse.toString());

                        Toast.makeText(getActivity(), "Hurray!!", Toast.LENGTH_LONG).show();
                        for (Customer customer : customerResponse.getCustomerList()){
                            Log.e("karman","Customer Name : " + customer.getCustomerName());
                        }
                    }

                    @Override
                    public void onError(String result) throws Exception {
                        Toast.makeText(getActivity(), "Oops!!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


        return rootView;
    }

}