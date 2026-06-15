package com.ucr.reco.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PasswordDto {
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&_#.-]).{8,}$",
            message = "La contraseña debe tener mayúsculas, minúsculas, números y caracteres especiales"
    )
    private String password;

    public PasswordDto() {

    }

    public PasswordDto(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
