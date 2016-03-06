package com.pengfeiw.simplebook;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.pengfeiw.simplebook.library.SongContainer;

/**
 * A fragment representing a single Song detail screen.
 * This fragment is either contained in a {@link SongListActivity}
 * in two-pane mode (on tablets) or a {@link SongDetailActivity}
 * on handsets.
 */
public class SongDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private SongContainer.Song mItem;
    private SongContainer songContainer = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SongDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        songContainer = SongContainer.getInstance(this.getActivity().getApplicationContext());

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = songContainer.getSongById(getArguments().getString(ARG_ITEM_ID));
            this.getActivity().setTitle(mItem.title);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_song_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Typeface tf_mm3 = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/zawgyi.ttf");
            ((TextView) rootView.findViewById(R.id.song_detail)).setText(mItem.getContent());
            ((TextView) rootView.findViewById(R.id.song_detail)).setTypeface(tf_mm3);
        }

        return rootView;
    }
}
