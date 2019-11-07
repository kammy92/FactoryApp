package com.karman.factoryapp.adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.karman.factoryapp.R;
import com.karman.factoryapp.callbacks.ListItemClick;
import com.karman.factoryapp.databinding.RowItemCustomerBinding;
import com.karman.factoryapp.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    ListItemClick listItemClick;
    private Activity activity;
    private List<Customer> customerList = new ArrayList<>();
    RowItemCustomerBinding binding;

    public CustomerAdapter(Activity activity, List<Customer> customerList) {
        this.activity = activity;
        this.customerList = customerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.row_item_customer, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        binding.setCustomer(customerList.get(position));
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public void setOnItemClickListener(final ListItemClick listItemClick) {
        this.listItemClick = listItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(RowItemCustomerBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//           listItemClick.onItemClick (v, getLayoutPosition ());
                }
            });
        }
    }
}


