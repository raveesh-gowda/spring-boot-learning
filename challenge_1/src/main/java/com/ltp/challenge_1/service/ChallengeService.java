package com.ltp.challenge_1.service;

import com.ltp.challenge_1.Constants;
import com.ltp.challenge_1.Item;
import com.ltp.challenge_1.repository.ChallengeRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChallengeService {

    ChallengeRepository challengeRepository = new ChallengeRepository();

    public Item getItem(int index) {
        return challengeRepository.getItem(index);
    }

    public void addItem(Item item) {
        challengeRepository.addItem(item);
    }

    public void setItem(int index, Item item) {
        challengeRepository.setItem(index, item);
    }

    public List<Item> getItems() {
        return challengeRepository.getItems();
    }


    public int getIndexFromId(String id) {
        for (int i = 0; i < challengeRepository.getItems().size(); i++) {
            if (challengeRepository.getItems().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public boolean within5Days(Date newDate, Date oldDate) {
        long diff = Math.abs(newDate.getTime() - oldDate.getTime());
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) <= 5;
    }

    public Item getItemById(String id) {
        int index = getIndexFromId(id);
        return index == Constants.NOT_FOUND ? new Item() : getItem(index);
    }

    public String submitItem(Item item) {
        int index = getIndexFromId(item.getId());
        String status = Constants.SUCCESS_STATUS;

        if (index == Constants.NOT_FOUND) {
            addItem(item);
        } else if (within5Days(item.getDate(), getItems().get(index).getDate())) {
            setItem(index, item);
        } else {
            status = Constants.FAILED_STATUS;
        }

        return status;
    }

}
