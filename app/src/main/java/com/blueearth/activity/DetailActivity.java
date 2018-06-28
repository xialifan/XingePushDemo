package com.blueearth.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import blueearth.xingepushdemo.R;

public class DetailActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        MainActivity.logData(getIntent(),"detail activity oncreate");

    }
}
