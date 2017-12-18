package com.example.toolss.filestest;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private SeekBar seekBar;
    private ToggleButton toggleButton1;
    private ToggleButton toggleButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (EditText)(findViewById(R.id.editText));
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        toggleButton2 = (ToggleButton) findViewById(R.id.leftRight);
        toggleButton1 = (ToggleButton) findViewById(R.id.upDown);
        toggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) text.setGravity(Gravity.LEFT);
                else text.setGravity(Gravity.RIGHT);
            }
        });
        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)text.setGravity(Gravity.TOP);
                else text.setGravity(Gravity.BOTTOM);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                text.setTextSize(i);
                SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
                editor.putInt("fontSize",i);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        try {
            InputStream is = this.openFileInput("abc.txt");
            Scanner scanner = new Scanner(is);



            while(scanner.hasNext()){
                String s = scanner.nextLine();
                text.append(s + "\n");

            }

            //File file = new File(getFilesDir() + "abc.txt");
            //OutputStream os = new FileOutputStream (file);

        }
             catch (FileNotFoundException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
            //PrintStream out = new PrintStream(os);

    }

    @Override
    protected void onPause() {
        super.onPause();
        try{
            OutputStream os = this.openFileOutput("abc.txt",MODE_PRIVATE);
            PrintStream out = new PrintStream(os);
            out.println(text.getText().toString());
            out.flush();
            out.close();

        }
        catch (FileNotFoundException e){}
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        int fontSize = preferences.getInt("fontSize",50);
        text.setTextSize(fontSize);
        seekBar.setProgress(fontSize);
    }
}




//one hundred string