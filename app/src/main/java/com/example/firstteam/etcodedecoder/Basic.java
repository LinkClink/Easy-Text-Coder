package com.example.firstteam.etcodedecoder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class Basic extends AppCompatActivity
{
    private Button coding;
    private Button decoding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        coding = (Button)findViewById(R.id.coding_menu);
        decoding = (Button)findViewById(R.id.decoding_menu);
    }

    public void onClick(View view)
    {
        if(view == coding) coding();
        if(view == decoding) decoding();
    }

    public void coding()
    {
    Intent intent = new Intent(Basic.this, Coding.class);
    startActivityForResult(intent,1);
    }
    public void decoding()
    {
        Intent intent = new Intent(Basic.this, Decoding.class);
        startActivityForResult(intent,1);
    }

}
