package com.example.cmd.testproject.JavaObjects;

import java.util.UUID;



public class Product {
    private String title,desc,imgUrl;
    private String price;
    private String id;
    private long mId;

//    public Product(String title, String desc, String imgUrl, String price) {
//        this.title = title;
//        this.desc = desc;
//        this.imgUrl = imgUrl;
//        this.price = price;
//        this.id = UUID.randomUUID().toString();
//    }

    public Product(Long id,String title, String desc, String imgUrl, String price) {
        this.mId = id;
        this.title = title;
        this.desc = desc;
        this.imgUrl = imgUrl;
        this.price = price;

    }

    public Product() {
    }

    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
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
