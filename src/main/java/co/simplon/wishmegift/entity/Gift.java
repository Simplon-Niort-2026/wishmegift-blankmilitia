package co.simplon.wishmegift.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private Integer wishLevel;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isBook = false;

    @ManyToOne
    @JoinColumn(name = "wishlist_id")
    @JsonIgnoreProperties({"gifts"})
    private Wishlist wishlist;

    @OneToOne
    private User user;

    public Gift() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Wishlist getWishlist() {
        return wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
