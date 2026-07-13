package track.b.desktop.application.Model;

public class User 
{

    private int userId;
    private String userName;
    private String passwordHash;
    private String email;
    private String role;

    public User() 
    {
    }

    public User(
            int userId,
            String userName,
            String passwordHash,
            String email,
            String role) 
    {

        this.userId = userId;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    public int getUserId() 
    {
        return userId;
    }

    public String getUserName() 
    {
        return userName;
    }

    public String getPasswordHash() 
    {
        return passwordHash;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getRole() 
    {
        return role;
    }

    public void setUserId(int userId) 
    {
        this.userId = userId;
    }

    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) 
    {
        this.passwordHash = passwordHash;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setRole(String role) 
    {
        this.role = role;
    }

    public boolean isAdmin() 
    {
        return "Admin".equalsIgnoreCase(role);
    }

    @Override
    public String toString() 
    {
        return userId + " - " + userName;
    }
}