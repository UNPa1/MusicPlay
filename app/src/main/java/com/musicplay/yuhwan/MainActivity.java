package com.musicplay.yuhwan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void FromPicture(View V)
    {
        Intent intent=new Intent(MainActivity.this,FromPic1.class);
        startActivity(intent);
    }

    public void FromStory(View V)
    {

    }

    public void StoryWithAll(View V)
    {

    }

    public void ReWrite(View V)
    {

    }

    public void MakeMovie(View V)
    {

    }
}
