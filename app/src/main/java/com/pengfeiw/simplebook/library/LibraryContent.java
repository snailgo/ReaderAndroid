package com.pengfeiw.simplebook.library;

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
public class LibraryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Artical> ITEMS = new ArrayList<Artical>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, Artical> ITEM_MAP = new HashMap<String, Artical>();

    static {
        // Add 3 sample items.
        addItem(new Artical("1", "Artical 1", "This is artical 1"));
        addItem(new Artical("2", "Artical 2", "This is artical 2"));
        addItem(new Artical("3", "Artical 3", "This is artical 3"));
    }

    private static void addItem(Artical item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Artical {
        public String id;
        public String title;
        public String content;

        public Artical(String id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        @Override
        public String toString() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}