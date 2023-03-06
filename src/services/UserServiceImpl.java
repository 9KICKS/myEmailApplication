package services;

import data.models.User;
import data.repositories.UserRepository;
import data.repositories.UserRepositoryImpl;
import dtos.requests.LoginUserRequest;
import dtos.requests.RegisterUserRequest;
import dtos.requests.SendEMailRequest;
import dtos.responses.LoginUserResponse;
import dtos.responses.RegisterUserResponse;
import utils.GenerateID;
import utils.Mapper;
import javax.swing.*;
import java.util.List;

public class UserServiceImpl implements UserService {
    EMailService  eMailService = new EMailServiceImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    private final List<User> users = userRepository.findAllUsers();

    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        try {
        for (User user : users) {
            if (user.getUsername().equals(registerUserRequest.getUsername())) {
                JOptionPane.showMessageDialog(null, "Username already exists, try another username.");
                throw new IllegalArgumentException("Username already exists.");
            }
            if (user.getPhoneNumber().equals(registerUserRequest.getPhoneNumber())) {
                JOptionPane.showMessageDialog(null, "Phone number already exists, try another phone number.");
                throw new IllegalArgumentException("Phone number already exists.");
            }
        }
        User user = Mapper.map(registerUserRequest);
        user.setUserId(GenerateID.generateId(users.size()));
        users.add(user);
        String message = "User successfully created.";
        JOptionPane.showMessageDialog(null, message);
        return new RegisterUserResponse(message, user.getUsername());
    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        throw ex;
        }
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUserRequest) {
        try {
            for (User user : users) {
                if (user.getUsername().equals(loginUserRequest.getUsername()) && user.getPassword().equals(loginUserRequest.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Login successful.");
                }
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Incorrect username or password.");
            throw ex;
        }
        return new LoginUserResponse("Login successful.");
    }

    @Override
    public void sendEmail(SendEMailRequest sendEmailRequest) {
        eMailService.sendEmail(sendEmailRequest);
    }

    @Override
    public void moveMailToTrash(int emailId, String emailType) {
        eMailService.moveMailToTrash(emailId, emailType);
    }
}