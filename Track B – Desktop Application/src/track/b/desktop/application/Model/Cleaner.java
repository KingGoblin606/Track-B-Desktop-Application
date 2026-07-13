package track.b.desktop.application.Model;

public class Cleaner 
{

    private int cleanerId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Integer departmentId;   // nullable - department assignment is optional
    private String departmentName;  // populated for display only, not written to DB directly

    public Cleaner() 
    {
    }

    public Cleaner(
            int cleanerId,
            String name,
            String surname,
            String phoneNumber,
            String email,
            Integer departmentId) 
    {

        this.cleanerId = cleanerId;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.departmentId = departmentId;
    }

    public int getCleanerId() 
    {
        return cleanerId;
    }

    public String getName() 
    {
        return name;
    }

    public String getSurname() 
    {
        return surname;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public String getEmail() 
    {
        return email;
    }

    public Integer getDepartmentId() 
    {
        return departmentId;
    }

    public String getDepartmentName() 
    {
        return departmentName;
    }

    public void setCleanerId(int cleanerId) 
    {
        this.cleanerId = cleanerId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setSurname(String surname) 
    {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setDepartmentId(Integer departmentId) 
    {
        this.departmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) 
    {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() 
    {
        return cleanerId + " - " + name + " " + surname;
    }
}