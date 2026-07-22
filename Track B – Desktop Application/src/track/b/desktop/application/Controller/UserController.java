package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.UserRepository;
import track.b.desktop.application.Model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserController 
{

    private final UserRepository repository;
    private User currentUser;

    public UserController() 
    {
        repository = new UserRepository();
    }

    public User register(
            String userName,
            String password,
            String email,
            String role) 
    {

        validateRegistrationDetails(userName, password, email, role);

        if (isDuplicateUserName(userName)) 
        {
            throw new IllegalArgumentException("This username is already taken.");
        }

        if (repository.findByEmail(email.trim()) != null) 
        {
            throw new IllegalArgumentException("This email is already registered.");
        }

        int newId = repository.findMaxId() + 1;

        User user = new User(
                newId,
                userName.trim(),
                hashPassword(password),
                email.trim(),
                role
        );

        repository.insert(user);

        return user;
    }

    public User login(String email, String password) 
    {

        if (email == null || email.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (password == null || password.isEmpty()) 
        {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        User user = repository.findByEmail(email.trim());

        if (user == null || !user.getPasswordHash().equals(hashPassword(password))) 
        {
            throw new IllegalArgumentException("Incorrect email or password.");
        }

        currentUser = user;

        return user;
    }

    public void logout() 
    {
        currentUser = null;
    }

    public User getCurrentUser() 
    {
        return currentUser;
    }

    public boolean isLoggedIn() 
    {
        return currentUser != null;
    }

    private boolean isDuplicateUserName(String userName) 
    {

        for (User user : repository.findAll()) 
        {

            if (user.getUserName().equalsIgnoreCase(userName.trim())) 
            {
                return true;
            }
        }

        return false;
    }

    private void validateRegistrationDetails(
            String userName,
            String password,
            String email,
            String role) 
    {

        if (userName == null || userName.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Username cannot be empty.");
        }

        if (password == null || password.length() < 6) 
        {
            throw new IllegalArgumentException("Password must be at least 6 characters.");
        }

        if (email == null || email.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (!email.contains("@")) 
        {
            throw new IllegalArgumentException("Email address is not valid.");
        }

        if (role == null
                || (!role.equalsIgnoreCase("User") && !role.equalsIgnoreCase("Admin"))) 
        {
            throw new IllegalArgumentException("Role must be either 'User' or 'Admin'.");
        }
    }

    private String hashPassword(String password) 
    {

        try 
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) 
            {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) 
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) 
        {
            throw new RuntimeException("Password hashing failed: " + e.getMessage(), e);
        }
    }
}