package com.example.firstteam.etcodedecoder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Coding extends AppCompatActivity
{
    private Button opentxt;
    private Button savetxt;

    String path = null;
    Intent fileIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);

        opentxt = (Button) findViewById(R.id.coding_open_txt);
        savetxt = (Button) findViewById(R.id.coding_save_txt);

    }

    public void onClick(View view)
    {
        if(view == opentxt) GetPath();
        if(view == savetxt)
        {
            Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();
        }



    }


    public void GetPath()
    {
        fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("*/*");
        startActivityForResult(fileIntent,5);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case 5:
            {
                if(resultCode==RESULT_OK)
                {
                    path = data.getData().getPath();
                    Toast.makeText(getApplicationContext(),path,Toast.LENGTH_LONG).show();
                }
                  break;
            }
        }
    }
}
