package com.seanbroomfield;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

/**
 * Created by seanbroomfield on 9/12/15.
 */
public class ListAdapter extends ArrayAdapter<Integer> {

    private ArrayList<Integer> statListPlayer1;
    private ArrayList<Integer> statListPlayer2;
    private ArrayList<String> categories;
    private Context context;
    Player player1, player2;

    public ListAdapter(ArrayList<Integer> statListPlayer1, ArrayList<Integer> statsListPlayer2, ArrayList<String> categories, Context context) {
        super(context, R.layout.row, statListPlayer1);
        this.statListPlayer1 = statListPlayer1;
        this.statListPlayer2 = statsListPlayer2;
        this.context = context;
        this.categories = categories;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;

        if(v == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row, null);

            holder.player1 = (TextView) v.findViewById(R.id.player1stat);
            holder.player2 = (TextView) v.findViewById(R.id.player2stat);
            holder.category = (TextView) v.findViewById(R.id.category);
            holder.leftArrow = (ImageView) v.findViewById(R.id.left_Arrow);
            holder.rightArrow = (ImageView) v.findViewById(R.id.right_Arrow);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.player1.setText(String.valueOf(statListPlayer1.get(position)));
        holder.player2.setText(String.valueOf(statListPlayer2.get(position)));
        holder.category.setText(categories.get(position));
        if(statListPlayer1.get(position) > statListPlayer2.get(position)) {
            holder.leftArrow.setVisibility(View.VISIBLE);
            holder.rightArrow.setVisibility(View.INVISIBLE);
        } else if(statListPlayer1.get(position) < statListPlayer2.get(position)) {
            holder.rightArrow.setVisibility(View.VISIBLE);
            holder.leftArrow.setVisibility(View.INVISIBLE);
        } else {
            holder.leftArrow.setVisibility(View.VISIBLE);
            holder.rightArrow.setVisibility(View.VISIBLE);
        }

        return v;
    }

    static class ViewHolder {
        TextView player1, player2, category;
        ImageView leftArrow, rightArrow, player1Emblem, player2Emblem;
    }
}
