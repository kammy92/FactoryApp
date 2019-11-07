package com.karman.factoryapp.learning.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class User extends BaseObservable {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private int age;
    private float salary;

    public User() {
    }


    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.karman.factoryapp.BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.karman.factoryapp.BR.lastName);
    }

    @Bindable
    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
        notifyPropertyChanged(com.karman.factoryapp.BR.dob);
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
        notifyPropertyChanged(com.karman.factoryapp.BR.gender);
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(com.karman.factoryapp.BR.age);
    }

    @Bindable
    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
        notifyPropertyChanged(com.karman.factoryapp.BR.salary);
    }

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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
