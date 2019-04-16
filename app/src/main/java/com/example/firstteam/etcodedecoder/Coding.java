package com.example.firstteam.etcodedecoder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coding extends AppCompatActivity
{
    private Button opentxt;
    private Button savetxt;
    private EditText edit_text;

    Uri op_file_uri;
    Intent fileIntent;

    StringBuilder builder = new StringBuilder();

    //Class
    DataGeneration datepars = new DataGeneration();
    OpenFile openFile = new OpenFile();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);

        opentxt = (Button) findViewById(R.id.coding_open_txt);
        savetxt = (Button) findViewById(R.id.coding_save_txt);
        edit_text = (EditText) findViewById(R.id.editview_coding);

    }


    public void onClick(View view)
    {
        if(view == opentxt) GetPath();
        if(view == savetxt) SaveTextToFile();
    }

    // Open activity to get uri to file
    public void GetPath()
    {
        fileIntent = new Intent(Intent.ACTION_GET_CONTENT);
        fileIntent.setType("*/*");
        startActivityForResult(fileIntent,5);

    }

    public void SaveTextToFile()
    {

        int WRITE_REQUEST_CODE = 43;
        String mimeType ="text/plain";
        String fileName = "code.result."+datepars.save_date()+".txt";

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType(mimeType);

        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, WRITE_REQUEST_CODE);

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
                    op_file_uri = data.getData();
                    edit_text.setText(builder = openFile.copy_to_editview(op_file_uri,getApplicationContext()));
                }
             break;
            }
        }
    }

}