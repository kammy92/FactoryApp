package com.karman.factoryapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.karman.factoryapp.R;
import com.karman.factoryapp.adapter.CustomerAdapter;
import com.karman.factoryapp.callbacks.VolleyCallback;
import com.karman.factoryapp.controller.VolleyController;
import com.karman.factoryapp.databinding.FragmentCustomerBinding;
import com.karman.factoryapp.model.Customer;
import com.karman.factoryapp.network.model.customer.CustomerResponse;
import com.karman.factoryapp.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerFragment extends Fragment {
    RecyclerView rvCustomer;
    VolleyController volleyController;
    CustomerResponse customerResponse;
    CustomerAdapter customerAdapter;
    List<Customer> customerList = new ArrayList<>();
    FragmentCustomerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(Constants.TAG_CUSTOMER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer,
                container,false);

        volleyController = VolleyController.getInstance(getActivity());

        rvCustomer = binding.rvCustomers;
        customerAdapter = new CustomerAdapter(getActivity(), customerList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        rvCustomer.setAdapter(customerAdapter);
        rvCustomer.setHasFixedSize(true);
        rvCustomer.setLayoutManager(linearLayoutManager);
        rvCustomer.setItemAnimator(new DefaultItemAnimator());
//        rvEmployees.addItemDecoration (new RecyclerViewMargin ((int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), (int) Utils.pxFromDp (getActivity (), 16), 1, 0, RecyclerViewMargin.LAYOUT_MANAGER_LINEAR, RecyclerViewMargin.ORIENTATION_VERTICAL));
/*
        rvCustomer.addItemDecoration(
                new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()) {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView
                            parent, RecyclerView.State state) {
                        int position = parent.getChildAdapterPosition(view);
                        // hide the divider for the last child
                        if (position == parent.getAdapter().getItemCount() - 1) {
                            outRect.setEmpty();
                        } else {
                            super.getItemOffsets(outRect, view, parent, state);
                        }
                    }
                }
        );*/


        Log.e("karman", "Test Click");
        Map<String, String> header = new HashMap<>();
        header.put("Header 0", "header 0");
        volleyController.makeGetRequest("https://reqres.in/api/users?page=2", header, new VolleyCallback() {
            @Override
            public void onSuccess(JSONObject result) throws JSONException {
                customerList.clear();
                JSONObject jsonObject = new JSONObject("{\n" +
                        "    \"data\": {\n" +
                        "        \"customers\": [\n" +
                        "            {\n" +
                        "                \"customer_id\": 1,\n" +
                        "                \"customer_name\": \"AMIT BANARAS\",\n" +
                        "                \"customer_mobile\": \"1\",\n" +
                        "                \"customer_email\": \"1\",\n" +
                        "                \"customer_address\": \"1\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 2,\n" +
                        "                \"customer_name\": \"ARSH MIXI\",\n" +
                        "                \"customer_mobile\": \"2\",\n" +
                        "                \"customer_email\": \"2\",\n" +
                        "                \"customer_address\": \"2\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 3,\n" +
                        "                \"customer_name\": \"ASHWANI\",\n" +
                        "                \"customer_mobile\": \"3\",\n" +
                        "                \"customer_email\": \"3\",\n" +
                        "                \"customer_address\": \"3\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 4,\n" +
                        "                \"customer_name\": \"BANGALI\",\n" +
                        "                \"customer_mobile\": \"4\",\n" +
                        "                \"customer_email\": \"4\",\n" +
                        "                \"customer_address\": \"4\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 5,\n" +
                        "                \"customer_name\": \"BHOLA\",\n" +
                        "                \"customer_mobile\": \"5\",\n" +
                        "                \"customer_email\": \"5\",\n" +
                        "                \"customer_address\": \"5\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 6,\n" +
                        "                \"customer_name\": \"DECENT ELECTRICAL\",\n" +
                        "                \"customer_mobile\": \"6\",\n" +
                        "                \"customer_email\": \"6\",\n" +
                        "                \"customer_address\": \"FARIDABAD\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 7,\n" +
                        "                \"customer_name\": \"DES RAJ\",\n" +
                        "                \"customer_mobile\": \"7\",\n" +
                        "                \"customer_email\": \"7\",\n" +
                        "                \"customer_address\": \"7\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 8,\n" +
                        "                \"customer_name\": \"FITWELL SPARES\",\n" +
                        "                \"customer_mobile\": \"8\",\n" +
                        "                \"customer_email\": \"8\",\n" +
                        "                \"customer_address\": \"8\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 9,\n" +
                        "                \"customer_name\": \"GINNI MIXI\",\n" +
                        "                \"customer_mobile\": \"9\",\n" +
                        "                \"customer_email\": \"9\",\n" +
                        "                \"customer_address\": \"9\",\n" +
                        "                \"customer_status\": 1,\n" +
                        "                \"created_at\": \"2018-01-06 05:30:00\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "                \"customer_id\": 10,\n" +
                        "                \"customer_name\": \"GOYAL ENTERPRISES\",\n" +
                        "                \"customer_mobile\": \"10\",\n" +
                        "                \"customer_email\": \"10\",\n" +
                        "                \"customer_address\": \"10\",\n" +
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
//customerDataResponse = (CustomerDataResponse) baseResponse.getDataResponse();
//                        Log.e("karman", "response : " + baseResponse.toString());

                customerList.addAll(customerResponse.getCustomerDataResponse().getCustomerList());
                customerAdapter.notifyDataSetChanged();

                Toast.makeText(getActivity(), "Hurray!!", Toast.LENGTH_LONG).show();
                for (Customer customer : customerResponse.getCustomerDataResponse().getCustomerList()) {
                    Log.e("karman", "Customer Name : " + customer.getCustomerName());
                }
            }

            @Override
            public void onError(String result) throws Exception {
                Toast.makeText(getActivity(), "Oops!!", Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }

}