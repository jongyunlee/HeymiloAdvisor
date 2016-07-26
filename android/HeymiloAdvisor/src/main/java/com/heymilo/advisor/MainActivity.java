package com.heymilo.advisor;

import android.app.Activity;
import android.os.Bundle;
import com.heymilo.advisor.ui.BaseActivity;

public class MainActivity extends BaseActivity
{
    @Override
    protected int getLayoutResources() {
        return R.layout.main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
