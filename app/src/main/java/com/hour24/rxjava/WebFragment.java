package com.hour24.rxjava;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import rx.Subscriber;
import rx.Subscription;


public class WebFragment extends Fragment {

    String TAG = "sjjang";
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();

        final TextView textView = (TextView) view.findViewById(R.id.test);


        Subscription subscription = MainActivity.observable.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
                textView.setText(s);
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
}