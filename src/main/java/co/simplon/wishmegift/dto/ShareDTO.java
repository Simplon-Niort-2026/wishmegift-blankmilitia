package co.simplon.wishmegift.dto;

public class ShareDTO {

    private Long id;
    private Long wishlistId;
    private String wishlistTitle;
    private UserDTO user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getWishlistId() {
        return wishlistId;
    }
    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getWishlistTitle() {
        return wishlistTitle;
    }
    public void setWishlistTitle(String wishlistTitle) {
        this.wishlistTitle = wishlistTitle;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
