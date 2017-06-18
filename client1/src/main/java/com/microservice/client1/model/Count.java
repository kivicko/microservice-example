package com.microservice.client1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by kivi on 18.06.2017.
 */
@Document
public class Count {

    @Id
    private String id;

    private int number;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date saveDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\n" +
                "NUMBER: " + this.number + "\n" +
                "DATE: "+ this.saveDate + "\n";
    }
}
