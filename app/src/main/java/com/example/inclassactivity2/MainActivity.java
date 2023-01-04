package com.example.inclassactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText titleText, authorText,pagesText;
    private CheckBox checkAvailable;
    private Button btnAdd, btnSaveAll;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String SHARE = "share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpSharedPrefs();
        setUpView();
        ArrayList<String> mylist = new ArrayList<>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleText.getText().toString();
                String author = authorText.getText().toString();
                Integer pages = Integer.parseInt(pagesText.toString());
                boolean available = checkAvailable.isSelected();
                BookData book = new BookData(title,author,pages,available);
                Gson gson = new Gson();
                mylist.add(gson.toJson(book));

            }
        });

        btnSaveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putStringSet(SHARE, (Set<String>) mylist);
            }
        });
    }

    private void setUpView() {
        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        pagesText = findViewById(R.id.pagesText);
        checkAvailable = findViewById(R.id.available);
        btnAdd = findViewById(R.id.btnAdd);
        btnSaveAll = findViewById(R.id.btnSaveAll);
    }

    private void setUpSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
}