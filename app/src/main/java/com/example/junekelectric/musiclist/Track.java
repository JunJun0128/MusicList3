package com.example.junekelectric.musiclist;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import static com.example.junekelectric.musiclist.ArtistMenu.album_hash;
import static com.example.junekelectric.musiclist.ArtistMenu.artist_item;
//import static com.example.junekelectric.musiclist.R.id.tracks;

/**
 * Created by Junekelectric on 16/05/27.
 */
public class Track {
    public long id;
    public long albumId;
    public long artistId;
    public String path;
    public String title;
    public String album;
    public String artist;
    public Uri uri;
    public long duration;
    public int trackNo;

    public static final String[] COLUMNS = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.TRACK
    };

    public static List getItemsByAlbum(Context activity, long albumID) {

        List tracks = new ArrayList();
        ContentResolver resolver = activity.getContentResolver();
        String[] SELECTION_ARG = {""};
        SELECTION_ARG[0] = String.valueOf(albumID);
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                //内容を読み取ってる
                Track.COLUMNS,
                MediaStore.Audio.Media.ALBUM_ID + "= ?",
                SELECTION_ARG,
                null
        );
        while( cursor.moveToNext() ){
            if( cursor.getLong(cursor.getColumnIndex( MediaStore.Audio.Media.DURATION)) < 3000 ){continue;}
            tracks.add(new Track(cursor));
        }
        cursor.close();
        return tracks;
    }
    public static List getItemsByArtist(Context activity, long artistID) {

        List tracks = new ArrayList();
        String[] SELECTION_ARG = {""};
        SELECTION_ARG[0] = artist_item.artist;

        ContentResolver resolver = activity.getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                Track.COLUMNS,
                MediaStore.Audio.Media.ARTIST + "= ?",
                SELECTION_ARG,
                "TRACK  ASC"
        );
        album_hash.clear();
        while( cursor.moveToNext() ){
            if( cursor.getLong(cursor.getColumnIndex( MediaStore.Audio.Media.DURATION)) < 3000 ){continue;}
            tracks.add(new Track(cursor));
            album_hash.put(
                    cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.ALBUM )),
                    String.valueOf(cursor.getLong( cursor.getColumnIndex( MediaStore.Audio.Media.ALBUM_ID )))
            );
        }
    }

    public static List getItems(Context activity) {
        List tracks = new ArrayList();
        ContentResolver resolver = activity.getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                //内容を読み取ってる
                Track.COLUMNS,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            if (cursor.getLong(cursor.getColumnIndex(
                    MediaStore.Audio.Media.DURATION)) < 3000) {
                continue;
            }
            tracks.add(new Track(cursor));
        }
        cursor.close();
        return tracks;
    }

    public Track(Cursor cursor) {
        id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
        path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
        artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
        artistId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
        duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
        trackNo = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
        uri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
    }


}



