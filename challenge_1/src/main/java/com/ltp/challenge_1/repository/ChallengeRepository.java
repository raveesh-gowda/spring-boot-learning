package com.ltp.challenge_1.repository;

import com.ltp.challenge_1.Item;

import java.util.ArrayList;
import java.util.List;

public class ChallengeRepository {

    private final List<Item> items = new ArrayList<>();

    public Item getItem(int index) {
        return items.get(index);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setItem(int index, Item item) {
        items.set(index, item);
    }

    public List<Item> getItems() {
        return items;
    }

}
