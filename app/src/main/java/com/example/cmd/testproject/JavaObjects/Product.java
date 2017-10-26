package com.example.cmd.testproject.JavaObjects;

import java.util.UUID;

/**
 * Created by cmd on 25.10.17.
 */

public class Product {
    private String title,desc,imgUrl;
    private String price;
    private UUID id;

    public Product(String title, String desc, String imgUrl, String price) {
        this.title = title;
        this.desc = desc;
        this.imgUrl = imgUrl;
        this.price = price;
        this.id = UUID.randomUUID();
    }

    public Product() {
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
