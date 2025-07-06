package com.example.foodapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private String name;
    private int imageResource;
    private String description;
    private int price;

    public Food(String name, int imageResource, String description, int price) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.price = price;
    }

    protected Food(Parcel in) {
        name = in.readString();
        imageResource = in.readInt();
        description = in.readString();
        price = in.readInt();
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(imageResource);
        parcel.writeString(description);
        parcel.writeInt(price);
    }
}
