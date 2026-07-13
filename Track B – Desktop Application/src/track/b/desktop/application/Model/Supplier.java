package track.b.desktop.application.Model;

public class Supplier 
{

    private int supplierId;
    private String supplierName;
    private String contactPerson;
    private String phoneNumber;
    private String email;
    private String streetAddress;
    private String city;
    private String postalCode;

    public Supplier() 
    {
    }

    public Supplier(
            int supplierId,
            String supplierName,
            String contactPerson,
            String phoneNumber,
            String email,
            String streetAddress,
            String city,
            String postalCode) 
    {

        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contactPerson = contactPerson;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getSupplierId() 
    {
        return supplierId;
    }

    public String getSupplierName() 
    {
        return supplierName;
    }

    public String getContactPerson() 
    {
        return contactPerson;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public String getEmail() 
    {
        return email;
    }

    public String getStreetAddress() 
    {
        return streetAddress;
    }

    public String getCity() 
    {
        return city;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }

    public void setSupplierId(int supplierId) 
    {
        this.supplierId = supplierId;
    }

    public void setSupplierName(String supplierName) 
    {
        this.supplierName = supplierName;
    }

    public void setContactPerson(String contactPerson) 
    {
        this.contactPerson = contactPerson;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) 
    {
        this.email = email;
    }

    public void setStreetAddress(String streetAddress) 
    {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() 
    {
        return supplierId + " - " + supplierName;
    }
}