package com.example.junekelectric.musiclist;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junekelectric on 16/09/09.
 */
public class ListArtistAdapter extends ArrayAdapter<Artist> {
    LayoutInflater mInflater;


    public ListArtistAdapter(Context context, List item) {
        super(context, 0, item);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Artist item = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_artist, null);
            holder = new ViewHolder();
            holder.tracksTextView = (TextView) convertView.findViewById(R.id.tracks);
            holder.artistTextView = (TextView) convertView.findViewById(R.id.artist);
            holder.albumTextView = (TextView) convertView.findViewById(R.id.albums);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //holder.tracksTextView.setText(item.tracks);
        holder.tracksTextView.setText(String.valueOf(item.tracks) + "tracks");
        holder.artistTextView.setText(item.artist);
        holder.albumTextView.setText(String.valueOf(item.albums) + "album");
        //holder.albumTextView.setText(item.albums);
        return convertView;
    }

    static class ViewHolder {
        TextView tracksTextView;
        TextView artistTextView;
        TextView albumTextView;

    }
    //LINUXを始めようpart3

//    public long id;
//    public String artist;
//    public String artistKey;
//    public int albums;
//    public int tracks;
//
//    public static final String[] FILLED_PROJECTION = {
//            MediaStore.Audio.Artists._ID,
//            MediaStore.Audio.Artists.ARTIST,
//            MediaStore.Audio.Artists.ARTIST_KEY,
//            MediaStore.Audio.Artists.NUMBER_OF_ALBUMS,
//            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
//    };
//
//    public ListArtistAdapter(Cursor cursor) {
//        id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Artists._ID));
//        artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST));
//        artistKey = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST_KEY));
//        albums = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS));
//        tracks = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS));
//    }
//
//    public static List getItems(Context activity) {
//        List artists = new ArrayList();
//        ContentResolver resolver = activity.getContentResolver();
//        Cursor cursor = resolver.query(
//                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
//                Artist.FILLED_PROJECTION,
//                null,
//                null,
//                "ARTIST ASC"
//        );
//
//        while (cursor.moveToNext()) {
//            artists.add(new Artist(cursor));
//        }
//
//        cursor.close();
//        return artists;
//    }
}
