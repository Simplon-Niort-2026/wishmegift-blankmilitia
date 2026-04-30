package co.simplon.wishmegift.dto;

import co.simplon.wishmegift.entity.Gift;
import co.simplon.wishmegift.entity.Theme;

import java.time.LocalDate;
import java.util.List;

public class WishlistDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private Theme theme;
    private String userFirstname;
    private String userLastname;
    private List<String> giftsNames;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public List<String> getGiftsNames() {
        return giftsNames;
    }

    public void setGiftsNames(List<String> giftsNames) {
        this.giftsNames = giftsNames;
    }
}
