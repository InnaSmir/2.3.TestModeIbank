package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegistrationForm {
    private String login;
    private String password;
    private String status;
}
