package com.example.firstteam.etcodedecoder;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OpenFile extends AppCompatActivity
{
    private InputStream inputStream = null;
    private BufferedReader br = null;
    StringBuilder text = new StringBuilder();
    private String line;

    // Support only latin + symbol (now)
    // Copy text with file
    public StringBuilder copy_to_editview(Uri op_file_uri, Context context)
    {
        try
        {
            inputStream = context.getContentResolver().openInputStream(op_file_uri);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = br.readLine()) != null)
            {
                text.append(line);
                text.append('\n');
            }
            br.close();
        }
        catch (IOException e)
        {
            Toast.makeText(context.getApplicationContext(),"Open-Error " + e ,Toast.LENGTH_LONG).show();
        }
        return text;
    }
}
