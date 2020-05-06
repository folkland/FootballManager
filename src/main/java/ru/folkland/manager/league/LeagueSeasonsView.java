package ru.folkland.manager.league;

import lombok.Data;

@Data
public class LeagueSeasonsView {

    private int id;
    private String name;

    public LeagueSeasonsView(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
