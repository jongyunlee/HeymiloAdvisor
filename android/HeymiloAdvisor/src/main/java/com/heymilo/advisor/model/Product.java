package com.heymilo.advisor.model;

import com.google.gson.annotations.SerializedName;
import java.sql.Timestamp;

public class Product {

    @SerializedName("id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("price") private int price;
    @SerializedName("star") private float star;
    @SerializedName("image") private String image;
    @SerializedName("review_count") private int reviewCount;
    @SerializedName("ingredients") private String ingredients;

    public String getIngredients() {
	return ingredients;
    }

    public void setIngredients(String ingredients) {
	this.ingredients = ingredients;
    }

    public Product(String name, int price, float star, String image, int reviewCount) {
	this.name = name;
	this.price = price;
	this.star = star;
	this.image = image;
	this.reviewCount = reviewCount;
    }

    public Product(String name, int price, float star,
		   String image, int reviewCount, String ingredients) {
	this.name = name;
	this.price = price;
	this.star = star;
	this.image = image;
	this.reviewCount = reviewCount;
	this.ingredients = ingredients;
    }

    public int getReviewCount() {
	return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
	this.reviewCount = reviewCount;
    }


    public String getImage() {
	return image;
    }

    public void setImage(String image) {
	this.image = image;
    }


    public float getStar() {
	return star;
    }

    public void setStar(float star) {
	this.star = star;
    }


    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }


    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }


    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }
}
