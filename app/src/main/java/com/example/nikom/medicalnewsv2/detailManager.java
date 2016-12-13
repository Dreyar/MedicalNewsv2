package com.example.nikom.medicalnewsv2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by nikom on 13/12/2016.
 */

public class detailManager extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailedfragment);

        if (savedInstanceState == null) {
            insideWebView iwv = new insideWebView();

            String url = getIntent().getStringExtra("url");

            Bundle extras = new Bundle();
            extras.putString("url",url);
            iwv.setArguments(extras);

            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.detailWebView, iwv);
            transaction.commit();
        }
    }
}
