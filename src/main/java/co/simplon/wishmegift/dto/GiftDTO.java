package co.simplon.wishmegift.dto;

import co.simplon.wishmegift.entity.User;
import co.simplon.wishmegift.entity.Wishlist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

public class GiftDTO {

    private Long id;
    private String name;
    private Integer wishLevel;
    private Double price;
    private Boolean isBook = false;
    private String wishlistName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWishLevel() {
        return wishLevel;
    }

    public void setWishLevel(Integer wishLevel) {
        this.wishLevel = wishLevel;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getBook() {
        return isBook;
    }

    public void setBook(Boolean book) {
        isBook = book;
    }

    public String getWishlistName() {
        return wishlistName;
    }

    public void setWishlistName(String wishlistName) {
        this.wishlistName = wishlistName;
    }
}
