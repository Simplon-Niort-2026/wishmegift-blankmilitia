package co.simplon.wishmegift.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class WishlistShareRequestDTO {

    @NotBlank
    @Email
    private String targetUserEmail;

    private String targetUserFirstname;
    private String targetUserLastname;

    public String getTargetUserEmail() {
        return targetUserEmail;
    }

    public void setTargetUserEmail(String targetUserEmail) {
        this.targetUserEmail = targetUserEmail;
    }

    public String getTargetUserFirstname() {
        return targetUserFirstname;
    }

    public void setTargetUserFirstname(String targetUserFirstname) {
        this.targetUserFirstname = targetUserFirstname;
    }

    public String getTargetUserLastname() {
        return targetUserLastname;
    }

    public void setTargetUserLastname(String targetUserLastname) {
        this.targetUserLastname = targetUserLastname;
    }
}
