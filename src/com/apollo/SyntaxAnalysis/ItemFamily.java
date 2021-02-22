package com.apollo.SyntaxAnalysis;

import java.util.ArrayList;

public class ItemFamily {
    ArrayList<ItemSet> items = new ArrayList<ItemSet>();

    public ItemFamily () {
        // default
    }
    public ItemFamily (ItemSet I) {
        items.add(I);
    }

    public int size() {
        return items.size();
    }

    public boolean add(ItemSet I) {
        return items.add(I);
    }

    public ItemSet get (int index) {
        return items.get(index);
    }

    public int onlyhash() {
        String tmp = "";
        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < get(i).production.size(); j++) {
                tmp += get(i).get(j).Head;
            }
        }
        return tmp.hashCode();
    }
}
