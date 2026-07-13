package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.SupplierRepository;
import track.b.desktop.application.Model.Supplier;

import java.util.ArrayList;

public class SupplierController 
{

    private final SupplierRepository repository;

    public SupplierController() 
    {
        repository = new SupplierRepository();
    }

    public ArrayList<Supplier> getAllSuppliers() 
    {
        return repository.findAll();
    }

    public Supplier addSupplier(
            String supplierName,
            String contactPerson,
            String phoneNumber,
            String email,
            String streetAddress,
            String city,
            String postalCode) 
    {

        validateSupplierDetails(
                supplierName,
                contactPerson,
                phoneNumber,
                email,
                streetAddress,
                city,
                postalCode
        );

        if (isDuplicateEmail(email, -1)) 
        {
            throw new IllegalArgumentException(
                    "A supplier with this email already exists."
            );
        }

        int newId = repository.findMaxId() + 1;

        Supplier supplier = new Supplier(
                newId,
                supplierName.trim(),
                contactPerson.trim(),
                phoneNumber.trim(),
                email.trim(),
                streetAddress.trim(),
                city.trim(),
                postalCode.trim()
        );

        repository.insert(supplier);

        return supplier;
    }

    public boolean updateSupplier(
            int supplierId,
            String supplierName,
            String contactPerson,
            String phoneNumber,
            String email,
            String streetAddress,
            String city,
            String postalCode) 
    {

        validateSupplierDetails(
                supplierName,
                contactPerson,
                phoneNumber,
                email,
                streetAddress,
                city,
                postalCode
        );

        if (isDuplicateEmail(email, supplierId)) 
        {
            throw new IllegalArgumentException(
                    "A supplier with this email already exists."
            );
        }

        Supplier supplier = findSupplierById(supplierId);

        if (supplier == null) {
            return false;
        }

        supplier.setSupplierName(supplierName.trim());
        supplier.setContactPerson(contactPerson.trim());
        supplier.setPhoneNumber(phoneNumber.trim());
        supplier.setEmail(email.trim());
        supplier.setStreetAddress(streetAddress.trim());
        supplier.setCity(city.trim());
        supplier.setPostalCode(postalCode.trim());

        repository.update(supplier);

        return true;
    }

    public boolean deleteSupplier(int supplierId) 
    {

        Supplier supplier = findSupplierById(supplierId);

        if (supplier == null) {
            return false;
        }

        repository.delete(supplierId);

        return true;
    }

    public Supplier findSupplierById(int supplierId) 
    {

        for (Supplier supplier : getAllSuppliers()) 
        {

            if (supplier.getSupplierId() == supplierId) 
            {
                return supplier;
            }
        }

        return null;
    }

    public ArrayList<Supplier> searchSuppliers(String searchText) 
    {

        ArrayList<Supplier> searchResults = new ArrayList<>();

        if (searchText == null || searchText.trim().isEmpty()) 
        {
            return getAllSuppliers();
        }

        String searchValue = searchText.trim().toLowerCase();

        for (Supplier supplier : getAllSuppliers()) 
        {

            boolean idMatches =
                    String.valueOf(supplier.getSupplierId())
                            .contains(searchValue);

            boolean nameMatches =
                    supplier.getSupplierName()
                            .toLowerCase()
                            .contains(searchValue);

            boolean contactMatches =
                    supplier.getContactPerson()
                            .toLowerCase()
                            .contains(searchValue);

            boolean emailMatches =
                    supplier.getEmail()
                            .toLowerCase()
                            .contains(searchValue);

            if (idMatches
                    || nameMatches
                    || contactMatches
                    || emailMatches) 
            {

                searchResults.add(supplier);
            }
        }

        return searchResults;
    }

    // excludeSupplierId lets updateSupplier() ignore the supplier's own current email
    private boolean isDuplicateEmail(String email, int excludeSupplierId) 
    {

        for (Supplier supplier : getAllSuppliers()) 
        {

            if (supplier.getSupplierId() != excludeSupplierId
                    && supplier.getEmail().equalsIgnoreCase(email.trim())) 
            {

                return true;
            }
        }

        return false;
    }

    private void validateSupplierDetails(
            String supplierName,
            String contactPerson,
            String phoneNumber,
            String email,
            String streetAddress,
            String city,
            String postalCode) 
    {

        if (supplierName == null || supplierName.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Supplier name cannot be empty.");
        }

        if (contactPerson == null || contactPerson.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Contact person cannot be empty.");
        }

        if (phoneNumber == null || phoneNumber.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }

        if (email == null || email.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Email cannot be empty.");
        }

        if (!email.contains("@")) 
        {
            throw new IllegalArgumentException("Email address is not valid.");
        }

        if (streetAddress == null || streetAddress.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Street address cannot be empty.");
        }

        if (city == null || city.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("City cannot be empty.");
        }

        if (postalCode == null || postalCode.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Postal code cannot be empty.");
        }
    }
}