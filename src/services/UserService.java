package services;

import dtos.requests.LoginUserRequest;
import dtos.requests.RegisterUserRequest;
import dtos.requests.SendEMailRequest;
import dtos.responses.LoginUserResponse;
import dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);
    LoginUserResponse login(LoginUserRequest loginUserRequest);

    void sendEmail(SendEMailRequest sendEmailRequest);
    void moveMailToTrash(int emailId, String emailType);
}