package co.simplon.wishmegift.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    @JsonIgnoreProperties({"shares", "gifts"})
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"wishlist", "password"})
    private User user;


    public Share() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
