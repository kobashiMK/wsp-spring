package com.example.wsp.spring.model;

import java.time.LocalDateTime;

//DataTransferObject
public class Retrospect {

    private String text;
    private LocalDateTime postedAt;

    public Retrospect(String text){
        this.text = text;
        this.postedAt = LocalDateTime.now();
        this.print();
    }
    public Retrospect() {
        this.text = "";
        this.postedAt = LocalDateTime.MIN;
    }

    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    public LocalDateTime getPostedAt(){
        return postedAt;
    }
    public void setPostedAt(LocalDateTime postedAt){
        this.postedAt = postedAt;
    }

    public void print(){
        System.out.println(String.join(",",super.toString(),text,postedAt.toString()));
    }
}
