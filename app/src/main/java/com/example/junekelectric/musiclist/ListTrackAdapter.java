package com.example.junekelectric.musiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ListTrackAdapter extends ArrayAdapter<Track> {
    //Linuxを始めようPART1 4 5でも使う。

    LayoutInflater mInflater;


    public ListTrackAdapter(Context context, List item) {
        super(context, 0, item);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Track item = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_track, null);
            holder = new ViewHolder();
            holder.tracksTextView = (TextView) convertView.findViewById(R.id.title);
            holder.artistTextView = (TextView) convertView.findViewById(R.id.artist);
            holder.durationTextView = (TextView) convertView.findViewById(R.id.duration);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        long dm = item.duration / 60000;
        long ds = (item.duration - (dm * 60000)) / 1000;

        holder.artistTextView.setText(item.artist);
        holder.tracksTextView.setText(item.title);
        holder.durationTextView.setText(String.format("%d:%02d", dm, ds));
        return convertView;
    }

    static class ViewHolder {
        TextView tracksTextView;
        TextView artistTextView;
        TextView durationTextView;
    }
}






