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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coding extends AppCompatActivity
{
    private Button opentxt;
    private Button savetxt;
    private EditText edit_text;
    private Spinner encode_spinner;

    Uri get_file_uri;
    Intent fileIntent;

    String save_text = null;
    String save_filename;
    String mimeType ="text/plain";

    StringBuilder builder = new StringBuilder();

    //Class
    DataGeneration datepars = new DataGeneration();
    OpenFile openFile = new OpenFile();
    SaveFile saveFile = new SaveFile();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding);

        encode_spinner = (Spinner) findViewById(R.id.coding_spinner_encod);
        opentxt = (Button) findViewById(R.id.coding_open_txt);
        savetxt = (Button) findViewById(R.id.coding_save_txt);
        edit_text = (EditText) findViewById(R.id.editview_coding);

        set_spinner_adapter();
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
        fileIntent.setType(mimeType);
        startActivityForResult(fileIntent,5);
    }

    public void SaveTextToFile()
    {
        save_filename = "code.result."+datepars.save_date()+".txt";

        fileIntent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        fileIntent.addCategory(Intent.CATEGORY_OPENABLE);
        fileIntent.setType(mimeType);

        fileIntent.putExtra(Intent.EXTRA_TITLE, save_filename);
        startActivityForResult(fileIntent, 4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        switch (requestCode)
        {
            case 5:
            {
                //check when file is not initialisation
                if(resultCode==RESULT_OK)
                {
                    get_file_uri = data.getData();
                    edit_text.setText(builder = openFile.copy_to_editview(get_file_uri,getApplicationContext(),encode_spinner.getSelectedItem().toString()));
                }
             break;
            }

            case 4:
            {
                //check when file is not initialisation
                if(resultCode==RESULT_OK)
                {
                    get_file_uri = data.getData();
                    save_text = edit_text.getText().toString();
                    saveFile.writeToFile(save_text, getApplicationContext(), save_filename, get_file_uri);
                }
             break;
            }
        }
    }

    //set adapter to spinner
    public void set_spinner_adapter()
    {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.open_file_encoding, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        encode_spinner.setAdapter(adapter);
    }
}