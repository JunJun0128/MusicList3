package com.example.junekelectric.musiclist;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;
import java.util.zip.Inflater;

public class RootMenu extends Fragment {
    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.menu, container, false);;

        mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        /*PagerTabカスタム*/
        PagerTabStrip strip = (PagerTabStrip) rootView.findViewById(R.id.pager_title_strip);
        strip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        strip.setTextSpacing(50);
        strip.setNonPrimaryAlpha(0.3f);

        return rootView;
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new HomeSectionFragment();
                    break;
                case 1:
                    fragment = new TrackSectionFragment();
                    break;
                case 2:
                    fragment = new AlbumSectionFragment();
                    break;
                case 3:
                    fragment = new ArtistSectionFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return getString(R.string.title_section1);
//                case 1:
//                    return getString(R.string.title_section2);
//                case 2:
//                    return getString(R.string.title_section3);
//                case 3:
//                    return getString(R.string.title_section4);
//            }
//            return null;
//        }
    }

    public static class HomeSectionFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.menu_home, container, false);
            return v;
        }
    }

    public static class TrackSectionFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            MainActivity activity = (MainActivity) getActivity();

            List tracks = Track.getItems(activity);

            View v = inflater.inflate(R.layout.menu_tracks, container, false);
            ListView trackList = (ListView) v.findViewById(R.id.listTracks);
            ListTrackAdapter adapter = new ListTrackAdapter(activity, tracks);
            trackList.setAdapter(adapter);

            return v;
        }
    }

    public static class AlbumSectionFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            MainActivity activity = (MainActivity) getActivity();
            List albums = Album.getItems(activity);

            View v = inflater.inflate(R.layout.menu_albums, container, false);
            ListView albumList = (ListView) v.findViewById(R.id.listAlbums);
            ListAlbumAdapter adapter = new ListAlbumAdapter(activity, albums);
            albumList.setAdapter(adapter);

            albumList.setOnItemClickListener(activity.AlbumClickListener);
            albumList.setOnItemLongClickListener(activity.AlbumLongClickListener);

            return v;
        }
    }

    public static class ArtistSectionFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            MainActivity activity = (MainActivity) getActivity();
            List artists = Artist.getItems(activity);

            View v = inflater.inflate(R.layout.menu_artists, container, false);
            ListView artistList = (ListView) v.findViewById(R.id.listArtists);
            ListArtistAdapter adapter = new ListArtistAdapter(activity, artists);
            artistList.setAdapter(adapter);

            artistList.setOnItemClickListener(activity.ArtistClickListener);
            artistList.setOnItemLongClickListener(activity.ArtistLongClickListener);

            return v;
        }
    }

}
