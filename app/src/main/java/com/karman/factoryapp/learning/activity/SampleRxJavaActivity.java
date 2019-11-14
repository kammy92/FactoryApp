package com.karman.factoryapp.learning.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.karman.factoryapp.R;
import com.karman.factoryapp.databinding.ActivitySampleRxjavaBinding;
import com.karman.factoryapp.learning.model.User;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SampleRxJavaActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySampleRxjavaBinding binding;
    private static String TAG = SampleRxJavaActivity.class.getSimpleName();

    private Disposable disposable;

    ObservableList<String> primitiveDataList = new ObservableArrayList<>();
    ObservableList<User> customDataList = new ObservableArrayList<>();

    StringBuilder output = new StringBuilder();

    Observer<String> primitiveDataObserver;
    Observer<User> customDataObserver;

    Observable<String> primitiveDataObservable;
    Observable<User> customDataObservable;

    boolean isPrimitive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_rxjava);
        initListener();
    }

    private void initListener() {
        binding.tvCustom.setOnClickListener(this);
        binding.tvPrimitive.setOnClickListener(this);
        binding.tvSimple.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private Observer<String> getPrimitiveDataObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                output.append(s + ", ");
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                binding.tvOutput.setText(output.toString());
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<String> getPrimitiveDataObservable() {
        primitiveDataList.add("Ant");
        primitiveDataList.add("Ape");
        primitiveDataList.add("Bat");
        primitiveDataList.add("Bee");
        primitiveDataList.add("Bear");
        primitiveDataList.add("Butterfly");
        primitiveDataList.add("Cat");
        primitiveDataList.add("Crab");
        primitiveDataList.add("Cod");
        primitiveDataList.add("Dog");
        primitiveDataList.add("Dove");
        primitiveDataList.add("Fox");
        primitiveDataList.add("Frog");
        String str[] = new String[primitiveDataList.size()];
        for (int j = 0; j < primitiveDataList.size(); j++) {
            str[j] = primitiveDataList.get(j);
        }
        return Observable.fromArray(str);
//        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }

    private Observer<User> getCustomDataObserver() {
        return new Observer<User>() {

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "User Name : " + user.getFirstName());
                output.append(user.getFirstName() + ", ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                binding.tvOutput.setText(output.toString());
                Log.d(TAG, "All notes are emitted!");
            }
        };
    }

    private Observable<User> getCustomDataObservable() {
        customDataList.add(new User("Karman", "Singh", "Male", 27));
        customDataList.add(new User("Mihir", "Bhardwaj", "Male", 27));
        customDataList.add(new User("Rohit", "Sharma", "Male", 30));
        customDataList.add(new User("Abhishek", "Kumar", "Male", 30));
        customDataList.add(new User("Nikita", "Sharma", "Female", 24));
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for (User user : customDataList) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(user);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_primitive:
                isPrimitive = true;
                resetData();
                primitiveDataObservable = getPrimitiveDataObservable();
                binding.tvData.setText(primitiveDataList.toString());
                break;
            case R.id.tv_custom:
                isPrimitive = false;
                resetData();
                customDataObservable = getCustomDataObservable();
                binding.tvData.setText(customDataList.toString());
                break;
            case R.id.tv_simple:
                output.setLength(0);
                if (isPrimitive){
                    primitiveDataObservable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(getPrimitiveDataObserver());
                } else {
                    customDataObservable
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(getCustomDataObserver());
                }
                break;
        }
    }

    private void resetData() {
        primitiveDataList.clear();
        customDataList.clear();
        output.setLength(0);
        binding.tvOutput.setText(output.toString());
    }
}