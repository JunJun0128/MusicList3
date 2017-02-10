package com.example.junekelectric.musiclist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by junekelectric on 16/11/11.
 */
public class AlbumMenu extends Fragment {
    private static Album album_item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View partView =inflater.inflate(R.layout.part_album, container, false);

        MainActivity activity = (MainActivity)getActivity();
        album_item = activity.getFocusedAlbum();

        TextView album_title =  (TextView) partView.findViewById(R.id.album_title);
        TextView album_artist = (TextView) partView.findViewById(R.id.album_artist);
        TextView album_tracks = (TextView) partView.findViewById(R.id.album_tracks);
        ImageView album_art =  (ImageView) partView.findViewById(R.id.album_art);

        album_title.setText(album_item.album);
        album_artist.setText(album_item.artist);
        album_tracks.setText(String.valueOf(album_item.tracks)+"tracks");

        String path = album_item.albumArt;
        album_art.setImageResource(R.drawable.dummy);
        if(path!=null){
            album_art.setTag(path);
            ImageGetTask task = new ImageGetTask(album_art);
            task.execute(path);}

        partView.findViewById(R.id.album_info).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {}});
        partView.findViewById(R.id.tracktitle).setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) {}});

        List tracks  = Track.getItemsByAlbum(getActivity(), album_item.albumId);
        ListView trackList = (ListView) partView.findViewById(R.id.list);
        ListTrackAdapter adapter = new ListTrackAdapter(activity, tracks);
        trackList.setAdapter(adapter);

        return partView;
    }
}

