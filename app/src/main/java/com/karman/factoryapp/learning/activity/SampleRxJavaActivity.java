package com.karman.factoryapp.learning.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.karman.factoryapp.R;
import com.karman.factoryapp.databinding.ActivitySampleRxjavaBinding;
import com.karman.factoryapp.learning.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SampleRxJavaActivity extends AppCompatActivity {
    ActivitySampleRxjavaBinding binding;
    private static String TAG = SampleRxJavaActivity.class.getSimpleName();

    private Disposable disposable;

    Observable<User> animalsObservable = getAnimalsObservable();
    Observer<User> animalsObserver = getAnimalsObserver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_rxjava);
        binding.btExample1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        animalsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) throws Exception {
                        return user.getGender().equalsIgnoreCase("male");
                    }
                })
                .subscribe(animalsObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }

    private Observer<User> getAnimalsObserver() {
        return new Observer<User>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "Name: " + user.getFirstName());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<User> getAnimalsObservable() {
        final List<User> userList = new ArrayList<>();
        userList.add(new User("Karman", "Singh", "Male", 27));
        userList.add(new User("Mihir", "Bhardwaj", "Male", 27));
        userList.add(new User("Rohit", "Sharma", "Male", 30));
        userList.add(new User("Abhishek", "Kumar", "Male", 30));
        userList.add(new User("Nikita", "Sharma", "Female", 24));

        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                for (User user : userList) {
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

}