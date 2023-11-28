package osen.taskboard.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String uuid;
    private String email;
    private String phone;
    private String role;
    private String login;
    private boolean active;
    private String activationCode;
}
