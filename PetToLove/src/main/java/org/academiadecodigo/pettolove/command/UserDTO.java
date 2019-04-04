package org.academiadecodigo.pettolove.command;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

public class UserDTO {


    private Integer id;

    @NotBlank(message = "Username is mandatory.")
    @NotNull(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must contain between 6 and 20 characters.")
    @Pattern(regexp = "[a-zA-Z0-9_\\-.]*$", message = "Username contains invalid characters.")
    private String username;

    @NotNull(message = "Password is mandatory.")
    @NotBlank(message = "Password is mandatory.")
    @Size(min = 4, max = 64)
    private String password;

    @NotBlank(message = "Name is mandatory.")
    @NotNull(message = "Name is mandatory.")
    @Pattern(regexp = "[a-zA-Z ]*$")
    @Size(min = 3, max = 64)
    private String name;

    @Pattern(regexp = "^\\+?[0-9]*$", message = "Phone number contains invalid characters")
    private String phone;

    @Email
    @NotBlank(message = "Email is mandatory.")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
