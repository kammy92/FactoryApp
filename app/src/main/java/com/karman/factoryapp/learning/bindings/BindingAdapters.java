package com.karman.factoryapp.learning.bindings;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class BindingAdapters {
    @BindingAdapter("android:textInt")
    public static void setText(TextView view, int value) {
        if (view.getText() != null
                && (!view.getText().toString().isEmpty())
                && Integer.parseInt(view.getText().toString()) != value) {
            view.setText(Integer.toString(value));
        }
    }

    @BindingAdapter("android:textFloat")
    public static void setText(TextView view, float value) {
        if (view.getText() != null
                && (!view.getText().toString().isEmpty())
                && Integer.parseInt(view.getText().toString()) != value) {
            view.setText(Float.toString(value));
        }
    }

    @InverseBindingAdapter(attribute = "android:textInt")
    public static int getTextInt(TextView view) {
        return Integer.parseInt(view.getText().toString());
    }

    @InverseBindingAdapter(attribute = "android:textFloat")
    public static float getTextFloat(TextView view) {
        return Float.parseFloat(view.getText().toString());
    }

    @BindingAdapter("android:textIntAttrChanged")
    public static void setTextIntListener(EditText editText, final InverseBindingListener textAttrChanged) {
        if (textAttrChanged != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    textAttrChanged.onChange();
                }
            });
        }
    }

    @BindingAdapter("android:textFloatAttrChanged")
    public static void setTextFloatListener(EditText editText, final InverseBindingListener textAttrChanged) {
        if (textAttrChanged != null) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    textAttrChanged.onChange();
                }
            });
        }
    }
}
