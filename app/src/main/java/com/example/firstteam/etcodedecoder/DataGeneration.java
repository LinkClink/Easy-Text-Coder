package com.example.firstteam.etcodedecoder;

import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataGeneration

{
    public String save_date()
    {
        String pattern = "yyyy-MM-dd-HH:mm:ss ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        return date;
    }

}
