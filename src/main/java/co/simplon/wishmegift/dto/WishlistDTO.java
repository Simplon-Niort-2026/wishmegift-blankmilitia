package co.simplon.wishmegift.dto;

import java.util.List;

public class WishlistDTO {

    private Long id;
    private String title;
    private List<UserDTO> sharedWith;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserDTO> getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(List<UserDTO> sharedWith) {
        this.sharedWith = sharedWith;
    }
}
