package co.simplon.wishmegift.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class WishlistShareRequestDTO {

    @NotBlank
    @Email
    private String targetUserEmail;

    public String getTargetUserEmail() {
        return targetUserEmail;
    }

    public void setTargetUserEmail(String targetUserEmail) {
        this.targetUserEmail = targetUserEmail;
    }
}
