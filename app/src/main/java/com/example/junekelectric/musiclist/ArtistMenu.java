package com.example.junekelectric.musiclist;

import android.view.View;
import android.widget.ImageView;
import java.util.HashMap;

/**
 * Created by junekelectric on 16/12/11.
 */
public class ArtistMenu {
    private static Artist artist_item;
    //private final static String default_msg = "全てのトラック";
    private final static String default_msg = res/value/strings.xml;
    private static ListTrackAdapter track_adapter;
    private ImageView album_art;
    private View partView;
    private static HashMap album_hash = new HashMap();

}
