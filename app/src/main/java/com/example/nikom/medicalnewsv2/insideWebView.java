package com.example.nikom.medicalnewsv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewFragment;

/**
 * Created by nikom on 13/12/2016.
 */

public class insideWebView extends WebViewFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        Bundle arguments = getArguments();
        String url = arguments.getString("url");

        WebView webView = getWebView();
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);


        return view;
    }
}
