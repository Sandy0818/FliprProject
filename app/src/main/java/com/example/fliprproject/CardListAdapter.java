package com.example.fliprproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CardListAdapter extends BaseAdapter {

    String [] result;
    String[] dates;
    Context context;
    private static LayoutInflater inflater=null;

    public CardListAdapter(DisplayList displayList, String[] card_names, String[] card_dates)
    {
        result = card_names;
        dates = card_dates;
        context = displayList;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView tv_title;
        TextView tv_date;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        View view;
        view = inflater.inflate(R.layout.card_lists, null);

        holder.tv_title = view.findViewById(R.id.tv_title);
        holder.tv_date = view.findViewById(R.id.tv_date);

        holder.tv_title.setText(result[position]);
        holder.tv_date.setText(result[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
