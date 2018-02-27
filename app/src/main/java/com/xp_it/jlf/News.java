package com.xp_it.jlf;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by roxanne7 on 27/02/2018.
 */

public class News {
    @SerializedName("id")
    long ID;
    String title;
    String content;
    String list[];
    String imageFile;
    String image;
    Date createdAt;
    Date updatedAt;
}
