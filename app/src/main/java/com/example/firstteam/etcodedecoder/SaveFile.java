package com.example.firstteam.etcodedecoder;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class SaveFile

{
    // Content (text) save to file functions
    public void writeToFile(String content, Context context, String fileName, Uri save_uri)
    {
        OutputStream fileout= null;
        try
        {
            fileout = (FileOutputStream) context.getContentResolver().openOutputStream(save_uri);

            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(content.toString());
            outputWriter.close();

            Toast.makeText(context, "File saved successfully!",Toast.LENGTH_SHORT).show();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
