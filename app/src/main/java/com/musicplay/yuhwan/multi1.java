package com.musicplay.yuhwan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class multi1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi1);
    }

    public void group1(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",1);
        startActivity(intent);
        finish();
    }
    public void group2(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",2);
        startActivity(intent);
        finish();
    }
    public void group3(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",3);
        startActivity(intent);
        finish();
    }
    public void group4(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",4);
        startActivity(intent);
        finish();
    }
    public void group5(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",5);
        startActivity(intent);
        finish();
    }
    public void group6(View v)
    {
        Intent intent = new Intent(multi1.this, multi2.class);
        intent.putExtra("Group",6);
        startActivity(intent);
        finish();
    }
}
