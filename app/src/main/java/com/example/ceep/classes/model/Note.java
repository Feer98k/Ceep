package com.example.ceep.classes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ceep.classes.constants.general.ColorsEnum;

import java.io.Serializable;
@Entity
public class Note implements Serializable {

    private String title;
    private String description;
    private long position;
    private ColorsEnum defaultColor = ColorsEnum.WHITE;
    @PrimaryKey(autoGenerate = true)
    private int id;



    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ColorsEnum getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(ColorsEnum defaultColor) {
        this.defaultColor = defaultColor;
    }
}