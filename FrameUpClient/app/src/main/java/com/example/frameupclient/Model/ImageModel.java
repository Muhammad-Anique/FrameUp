package com.example.frameupclient.Model;


public class ImageModel {

    private String ImageUrl;
    public ImageModel()
    {

    }

    public ImageModel(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
