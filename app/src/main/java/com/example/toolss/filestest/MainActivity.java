package com.example.toolss.filestest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InputStream is = this.openFileInput("abc.txt");
            Scanner scanner = new Scanner(is);

            EditText text = (EditText)(findViewById(R.id.editText));

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
            EditText text = (EditText)(findViewById(R.id.editText));
            OutputStream os = this.openFileOutput("abc.txt",MODE_PRIVATE);
            PrintStream out = new PrintStream(os);
            out.println(text.getText().toString());
            out.flush();
            out.close();

        }
        catch (FileNotFoundException e){}
    }
}