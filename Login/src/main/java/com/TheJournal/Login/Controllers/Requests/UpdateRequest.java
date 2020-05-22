package com.TheJournal.Login.Controllers.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRequest {

    private String email;
    private String password;
    private String name;
    private boolean updateEmail;
    private boolean updatePassword;
    private boolean updateName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUpdateEmail() {
        return updateEmail;
    }

    public void setUpdateEmail(boolean updateEmail) {
        this.updateEmail = updateEmail;
    }

    public boolean isUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(boolean updatePassword) {
        this.updatePassword = updatePassword;
    }

    public boolean isUpdateName() {
        return updateName;
    }

    public void setUpdateName(boolean updateName) {
        this.updateName = updateName;
    }
}
