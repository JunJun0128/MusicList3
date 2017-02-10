package com.example.junekelectric.musiclist;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by junekelectric on 16/12/11.
 */

public class ArtistMenu extends Fragment {
    public static Artist artist_item;
    public final static String default_msg = Resources.getSystem().getString(R.string.ArtistMenu_default_msg);
    private static ListTrackAdapter trackAdapter;
    public ImageView album_art;
    public View partView;
    public static HashMap album_hash = new HashMap();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View partView = inflater.inflate(R.layout.part_artist, container, false);

        ArrayAdapter adapter = new ArrayAdapter(partView, R.layout.spinner);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        adapter.add(default_msg);
        //default_msg はストリング名

        MainActivity activity = (MainActivity) getActivity();

        artist_item = activity.getFocusedArtist();

        TextView artist_artist = (TextView) partView.findViewById(R.id.artist);
        TextView artist_tracks = (TextView) partView.findViewById(R.id.tracks);
        TextView artist_albums = (TextView) partView.findViewById(R.id.albums);
        ImageView artist_art = (ImageView) partView.findViewById(R.id.album_art);

        artist_artist.setText(artist_item.artist);
        artist_tracks.setText(String.valueOf(artist_item.tracks) + "tracks");
        artist_albums.setText(String.valueOf(artist_item.albums) + "album");

        Set<Map.Entry<String, String>> s = album_hash.entrySet();
        for (Iterator<Map.Entry<String, String>> i = s.iterator(); i.hasNext(); ) {
            adapter.add(objectStrip((String) i.next().toString()));

        }
        Spinner spinner = (Spinner) partView.findViewById(R.id.album_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                String item = (String) spinner.getSelectedItem();
                if (item.equals(default_msg)) setList(null);
                else setList(item);
                String path = ImageGetTask.searchArtPath(getActivity(), item);

                album_art = (ImageView) partView.findViewById(R.id.album_art);
                album_art.setImageResource(R.drawable.dummy);
                if (path != null) {
                    album_art.setTag(path);
                    ImageGetTask task = new ImageGetTask(album_art);
                    task.execute(path);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        String path = artist_item.album_art;
        artist_art.setImageResource(R.drawable.dummy);
        if (path != null) {
            artist_art.setTag(path);
            ImageGetTask task = new ImageGetTask(artist_art);
            task.execute(path);
        }

        partView.findViewById(R.id.album_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        partView.findViewById(R.id.tracktitle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        List tracks = Track.getItemsByArtist(getActivity(), artist_item.artistId);
        ListView trackList = (ListView) partView.findViewById(R.id.list);
        ListTrackAdapter adapter = new ListTrackAdapter(activity, tracks);
        trackList.setAdapter(adapter);

        return partView;
    }


}