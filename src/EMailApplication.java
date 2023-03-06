import controllers.EMailController;
import controllers.UserController;
import dtos.requests.LoginUserRequest;
import dtos.requests.RegisterUserRequest;
import dtos.requests.SendEMailRequest;
import dtos.responses.LoginUserResponse;
import dtos.responses.RegisterUserResponse;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class EMailApplication {
    public static void main(String[] args) throws InterruptedException {
        UserController userController = new UserController();
        EMailController emailController = new EMailController();
        JOptionPane.showMessageDialog(null, "Hello, welcome to your Email Application", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
        int choice = JOptionPane.showOptionDialog(null, "Do you want to register or login?", "Email Application \uD83D\uDCE7", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Register", "Login"}, null);
        if (choice == JOptionPane.YES_OPTION) {
            String firstName = JOptionPane.showInputDialog(null, "Enter your first name:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
            String lastName = JOptionPane.showInputDialog(null, "Enter your last name:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
            String phoneNumber = JOptionPane.showInputDialog(null, "Enter your phone number:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
            String dateOfBirth = JOptionPane.showInputDialog(null, "Enter your date of birth (DD-MM-YYYY):", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
            String username = JOptionPane.showInputDialog(null, "Create email:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
            JPasswordField passwordField = new JPasswordField();
            JOptionPane.showConfirmDialog(null, passwordField, "Create password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);
            RegisterUserRequest registerRequest = new RegisterUserRequest(firstName, lastName, phoneNumber, dateOfBirth, username, password);
            RegisterUserResponse registerResponse = (RegisterUserResponse) userController.registerUser(registerRequest);
            JOptionPane.showMessageDialog(null, registerResponse.getMessage());
            loggingIn();
            login(userController, emailController);
        } else login(userController, emailController);
    }

    private static void loggingIn() throws InterruptedException {
        JOptionPane.showMessageDialog(null, "Logging you in immediately.", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
        Thread.sleep(3000);
        JOptionPane.showMessageDialog(null, "Logged in.", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void login(UserController userController, EMailController emailController) {
        String email = JOptionPane.showInputDialog(null, "Enter your email:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
        JPasswordField passwordField = new JPasswordField();
        JOptionPane.showConfirmDialog(null, passwordField, "Enter your password:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);
            LoginUserRequest loginRequest = new LoginUserRequest(email, password);
            LoginUserResponse loginResponse = (LoginUserResponse) userController.loginUser(loginRequest);
            if (loginResponse.getMessage().equals("Login successful.")) {
                int emailChoice;
                List<String> recipients;
                do {
                    emailChoice = JOptionPane.showOptionDialog(null, "What do you want to do?", "Email Application \uD83D\uDCE7", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Compose Email", "Inbox", "Outbox", "Drafts", "Trash", "Customize", "Logout"}, null);
                    switch (emailChoice) {
                        case 0 -> {
                            String recipient = JOptionPane.showInputDialog(null, "Enter recipient(s) (separate recipients with a ','):", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
                            String[] recipientArray = recipient.split(",");
                            recipients = Arrays.asList(recipientArray);
                            String subject = JOptionPane.showInputDialog(null, "Subject:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
                            String body = JOptionPane.showInputDialog(null, "Compose email:", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
                            SendEMailRequest sendEMailRequest = new SendEMailRequest();
                            sendEMailRequest.setSubject(subject);
                            sendEMailRequest.setRecipient(recipients);
                            sendEMailRequest.setMessage(body);
                            emailController.sendeMail(sendEMailRequest);
                        }
                        case 1 -> JOptionPane.showMessageDialog(null, emailController.findAlleMails());
                        case 2 -> JOptionPane.showMessageDialog(null, emailController.getOutbox());
                        case 3 -> JOptionPane.showMessageDialog(null, emailController.getDrafts());
                        case 4 -> JOptionPane.showMessageDialog(null, emailController.getTrash());
                        case 5 -> customizeApp();
                        case 6 -> JOptionPane.showMessageDialog(null, "Goodbye.", "Email Application \uD83D\uDCE7", JOptionPane.INFORMATION_MESSAGE);
                    }
                } while (emailChoice != 6);
            } else {
                JOptionPane.showMessageDialog(null, loginResponse.getMessage());
            }
        }

    private static void customizeApp() {
        Object[] options = {"Light Mode", "Dark Mode"};
        int choice = JOptionPane.showOptionDialog(null, "Select application theme:", "Customize Application", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            UIManager ui = new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("OptionPane.messageForeground", Color.black);
            UIManager.put("Button.background", Color.white);
            UIManager.put("Button.foreground", Color.black);
        } else if (choice == 1) {
            UIManager ui = new UIManager();
            UIManager.put("OptionPane.background", Color.black);
            UIManager.put("OptionPane.messageForeground", Color.white);
            UIManager.put("Button.background", Color.black);
            UIManager.put("Button.foreground", Color.white);
        }
    }
}