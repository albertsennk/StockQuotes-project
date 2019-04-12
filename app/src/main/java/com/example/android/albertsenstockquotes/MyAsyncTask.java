package com.example.android.albertsenstockquotes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.cofc.stock.Stock;

public class MyAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

    private TextView time, price, name, change, range;
    private Context context;

    public MyAsyncTask(ArrayList<View> views, Context context) {
        this.time = (TextView) views.get(0);
        this.price = (TextView) views.get(1);
        this.name = (TextView) views.get(2);
        this.change = (TextView) views.get(3);
        this.range = (TextView) views.get(4);
        this.context = context;
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        Stock symbol = new Stock(strings[0]);

//        String time, price, name, change, range;
        ArrayList<String> items = new ArrayList<>();

        try {
            symbol.load();
//            time = symbol.getLastTradeTime();
//            price = symbol.getLastTradePrice();
//            name = symbol.getName();
//            change = symbol.getChange();
//            range = symbol.getRange();
            items.add(symbol.getLastTradeTime());
            items.add(symbol.getLastTradePrice());
            items.add(symbol.getName());
            items.add(symbol.getChange());
            items.add(symbol.getRange());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ASYNC", "doInBackground: ", e);
//            Toast.makeText(this)
            return null;
        }

        return items;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);

        if (strings != null) {
            this.time.setText(strings.get(0));
            this.price.setText(strings.get(1));
            this.name.setText(strings.get(2));
            this.change.setText(strings.get(3));
            this.range.setText(strings.get(4));
        } else {
            Toast.makeText(context, "Stock Symbol Invalid, Please Try Again", Toast.LENGTH_SHORT).show();
        }
    }
}
