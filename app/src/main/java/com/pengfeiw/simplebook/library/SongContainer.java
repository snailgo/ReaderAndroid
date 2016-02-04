package com.pengfeiw.simplebook.library;

import android.content.Context;
import android.util.Log;

import com.google.common.io.CharStreams;

import java.io.IOException;
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

    /**
     * An array of sample (dummy) items.
     */
    public static List<Song> ITEMS = new ArrayList<Song>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Song> ITEM_MAP = new HashMap<String, Song>();

    static {
        // Add 3 sample items.
        addItem(new Song("1", "Some One Like You -- Adele", "SomeOneLikeYou.txt"));
    }

    private static void addItem(Song item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Song {
        private static String TAG = "Song";

        public String id;
        public String title;
        public String fileName;

        public Song(String id, String title, String fileName) {
            this.id = id;
            this.title = title;
            this.fileName = fileName;
        }

        @Override
        public String toString() {
            return title;
        }

        public String getContent(Context context) {
            Log.i(TAG, context.getFilesDir().getAbsolutePath() + "/" + fileName);
            String fileContent = "";
            InputStreamReader inReader = null;
            try {
                inReader = new InputStreamReader(context.getAssets().open(fileName));
                fileContent = CharStreams.toString(inReader);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inReader != null) {
                    try {
                        inReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return fileContent;
        }
    }
}
