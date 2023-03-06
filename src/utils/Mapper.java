package utils;

import data.models.EMail;
import data.models.User;
import dtos.requests.RegisterUserRequest;
import dtos.requests.SendEMailRequest;

public class Mapper {
    public static User map(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setFirstName(registerUserRequest.getFirstName());
        user.setLastName(registerUserRequest.getLastName());
        user.setPhoneNumber(registerUserRequest.getPhoneNumber());
        user.setDateOfBirth(registerUserRequest.getDateOfBirth());
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());
        return user;
    }

    public static EMail map(SendEMailRequest sendEmailRequest) {
        EMail eMail = new EMail();
        eMail.setRecipient(sendEmailRequest.getRecipient());
        eMail.setSubject(sendEmailRequest.getSubject());
        eMail.setMessage(sendEmailRequest.getMessage());
        return eMail;
    }
}