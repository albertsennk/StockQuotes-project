package com.example.android.albertsenstockquotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText symbol;

    private ArrayList<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        symbol = findViewById(R.id.symbol);
        TextView name = findViewById(R.id.name);
        TextView price = findViewById(R.id.price);
        TextView tradeTime = findViewById(R.id.time);
        TextView perChange = findViewById(R.id.change);
        TextView weekRange = findViewById(R.id.range);
        Button send = findViewById(R.id.send);

        views.add(tradeTime);
        views.add(price);
        views.add(name);
        views.add(perChange);
        views.add(weekRange);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTask task = new MyAsyncTask(views, getApplicationContext());
                task.execute(symbol.getText().toString());
            }
        });
    }
}
