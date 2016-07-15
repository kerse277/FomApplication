package com.fom.msesoft.fomapplication.adapter;

import lombok.Getter;
import lombok.Setter;

public class ItemData {



    @Getter
    @Setter
    private String imageUrl;


    public ItemData(String imageUrl1){


        this.imageUrl = imageUrl;
    }
}