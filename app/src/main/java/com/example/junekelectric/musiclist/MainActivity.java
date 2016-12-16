package com.example.junekelectric.musiclist;
import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;



//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        List <Track> tracks = Track.getItems(this);
////        ListView trackList = (ListView)findViewById(R.id.list);
////        ListTrackAdapter adapter = new ListTrackAdapter(this, tracks);
////        trackList.setAdapter(adapter);
//
//        List <Album> albums = Album.getItems(this);
//        ListView trackList = (ListView)findViewById(R.id.list);
//        ListAlbumAdapter adapter = new ListAlbumAdapter(this, albums);
//        trackList.setAdapter(adapter);
//    }

    public class MainActivity extends FragmentActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.root, new RootMenu(), "Root");
            ft.commit();

            setContentView(R.layout.activity_main);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        enum FrgmType {fRoot, fAlbum, fArtist}
        private FrgmType fTop;
        private Artist focusedArtist;
        public void focusArtist(Artist item)  {if(item != null) focusedArtist  = item;}
        public Album getFocusedArtist() {return focusedArtist;}

        private Album focusedAlbum;
        public void focusAlbum(Album item) {
            if (item != null) focusedAlbum = item;
        }
        public Album getFocusedAlbum() {
            return focusedAlbum;
        }

        public void setNewFragment(FrgmType CallFragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            fTop = CallFragment;
            switch (CallFragment) {
                case fRoot:
                    ft.replace(R.id.root, new RootMenu(), "Root");
                    break;
                case fAlbum:
                    ft.replace(R.id.root, new AlbumMenu(), "album");
                    break;
                //case fAlbum :ft.replace(R.id.root, new RootMenu()/*ダミー*/, "album"); break;
            }
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();

            switch(CallFragment)
            {
                case fRoot   : ft.replace(R.id.root, new RootMenu(),     "Root"); break;
                case fAlbum  : ft.replace(R.id.root, new AlbumMenu(),   "album"); break;
                case fArtist : ft.replace(R.id.root, new ArtistMenu(), "artist"); break;
            }
        }

        public AdapterView.OnItemClickListener AlbumClickListener
                = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                ListView lv = (ListView) parent;
                focusAlbum((Album) lv.getItemAtPosition(position));
                setNewFragment(FrgmType.fAlbum);
            }
        };

        public void popBackFragment() {
            FragmentManager fm = getSupportFragmentManager();
            fm.popBackStack();
        }

        public AdapterView.OnItemLongClickListener AlbumLongClickListener
                = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view,
                                           int position, long id) {
                ListView lv = (ListView) parent;
                Album item = (Album) lv.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "LongClick:" + item.album, Toast.LENGTH_LONG).show();
                return true;
            }
        };

        public AdapterView.OnItemClickListener ArtistClickListener
                = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                ListView lv = (ListView) parent;
                focusArtist((Artist) lv.getItemAtPosition(position));
                setNewFragment(FrgmType.fArtist);
            }
        };

        public AdapterView.OnItemLongClickListener ArtistLongClickListener
                = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView parent, View view,
                                           int position, long id) {
                ListView lv = (ListView) parent;
                Artist item = (Artist) lv.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "LongClick:" + item.artist, Toast.LENGTH_LONG).show();
                return true;
            }
        };
        
        //This app will search your music files

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    }