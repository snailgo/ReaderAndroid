package com.pengfeiw.simplebook.library;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SongContainer {
    private static String TAG = "SongContainer";

    //Resource for container self.
    private static SongContainer instance=null;
    private Context appContext = null;


    //Resource for accessing song.
    private static List<Song> ITEMS = new ArrayList<Song>();
    private static Map<String, Song> ITEM_MAP = new HashMap<String, Song>();

    /**
     *  Read songs from txt file. If the file is really large, this will cause some delay.
     *  Songs in Songs.txt will be repackaged as Song object.
     *
     *  Song.txt uses semicolon to separate different songs.
     *  A line start with songPromptString will be recognized as song title and the following
     *  content will be the song body until file end or another songPromptString.
     */
    private void init() {
        Log.i(TAG, "Initialize song container.");
        String songPromptString = "SONG TITLE:";
        InputStream songIn = null;
        try {
            songIn = appContext.getAssets().open("Songs.txt", AssetManager.ACCESS_STREAMING);
            BufferedReader fileReader = new BufferedReader((new InputStreamReader(songIn)));
            String line;
            String title = "";
            StringBuilder body = new StringBuilder();
            int count = 0;

            while(true) {
                line = fileReader.readLine();

                if (line == null) {
                    if (count > 0 ) {
                        addItem(new Song(Integer.toString(count), title, body.toString()));
                    }
                    break;
                }

                if (line.startsWith(songPromptString)) {
                    if (count > 0) {
                        addItem(new Song(Integer.toString(count), title, body.toString()));
                        body.setLength(0);
                    }
                    count ++;
                    title = line.substring(songPromptString.length()).trim();
                    Log.i(TAG, "Start parse song: " + title);
                    continue;
                }
                body.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (songIn != null) {
                    songIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private SongContainer(Context appContext) {
        this.appContext = appContext;
        init();
    }

    public static SongContainer getInstance(Context appContext) {
        if (instance == null) {
            instance = new SongContainer(appContext);
        }
        return instance;
    }

    private static void addItem(Song item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public Song getSongById(String songId) {
        return ITEM_MAP.get(songId);
    }

    public List<Song> getSongList() {
        return ITEMS;
    }

    // model of song.
    public static class Song {
        private static String TAG = "Song";

        public String id;
        public String title;
        public String body;

        public Song(String id, String title, String body) {
            this.id = id;
            this.title = title;
            this.body = body;
        }

        @Override
        public String toString() {
            return title;
        }

        public String getContent() {
            return body;
        }
    }
}
