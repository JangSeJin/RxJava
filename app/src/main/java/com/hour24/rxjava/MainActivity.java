package com.hour24.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    String TAG = "sjjang";
    Subscription mSubscription;

    public static Observable<String> observable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebFragment fragment = new WebFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commitAllowingStateLoss();

        observable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> sub) {
                        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                sub.onNext(">>hi!!!");
                                sub.onCompleted();
                            }
                        });

                        sub.unsubscribe();
                    }
                }
        );


        Subscription subscription = observable.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
                Log.i(TAG, ">>> onNext  : " + s);
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, ">>> onCompleted  : ");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, ">>> onError  : " + e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
            mSubscription = null;
        }
    }
}
