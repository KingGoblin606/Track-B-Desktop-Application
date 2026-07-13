package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.CleanerRepository;
import track.b.desktop.application.Model.Cleaner;

import java.util.ArrayList;

public class CleanerController 
{

    private final CleanerRepository repository;

    public CleanerController() 
    {
        repository = new CleanerRepository();
    }

    public ArrayList<Cleaner> getAllCleaners() 
    {
        return repository.findAll();
    }

    public Cleaner addCleaner(
            String name,
            String surname,
            String phoneNumber,
            String email,
            Integer departmentId) 
    {

        validateCleanerDetails(name, surname, phoneNumber, email);

        if (isDuplicateEmail(email, -1)) 
        {
            throw new IllegalArgumentException(
                    "A cleaner with this email already exists."
            );
        }

        int newId = repository.findMaxId() + 1;

        Cleaner cleaner = new Cleaner(
                newId,
                name.trim(),
                surname.trim(),
                phoneNumber.trim(),
                email.trim(),
                departmentId
        );

        repository.insert(cleaner);

        return cleaner;
    }

    public boolean updateCleaner(
            int cleanerId,
            String name,
            String surname,
            String phoneNumber,
            String email,
            Integer departmentId) {

        validateCleanerDetails(name, surname, phoneNumber, email);

        if (isDuplicateEmail(email, cleanerId)) {
            throw new IllegalArgumentException(
                    "A cleaner with this email already exists."
            );
        }

        Cleaner cleaner = findCleanerById(cleanerId);

        if (cleaner == null) 
        {
            return false;
        }

        cleaner.setName(name.trim());
        cleaner.setSurname(surname.trim());
        cleaner.setPhoneNumber(phoneNumber.trim());
        cleaner.setEmail(email.trim());
        cleaner.setDepartmentId(departmentId);

        repository.update(cleaner);

        return true;
    }

    public boolean deleteCleaner(int cleanerId) 
    {

        Cleaner cleaner = findCleanerById(cleanerId);

        if (cleaner == null) 
        {
            return false;
        }

        repository.delete(cleanerId);

        return true;
    }

    public Cleaner findCleanerById(int cleanerId) 
    {

        for (Cleaner cleaner : getAllCleaners()) 
        {

            if (cleaner.getCleanerId() == cleanerId) 
            {
                return cleaner;
            }
        }

        return null;
    }

    public ArrayList<Cleaner> searchCleaners(String searchText) 
    {

        ArrayList<Cleaner> searchResults = new ArrayList<>();

        if (searchText == null || searchText.trim().isEmpty()) 
        {
            return getAllCleaners();
        }

        String searchValue = searchText.trim().toLowerCase();

        for (Cleaner cleaner : getAllCleaners()) 
        {

            boolean idMatches =
                    String.valueOf(cleaner.getCleanerId())
                            .contains(searchValue);

            boolean nameMatches =
                    cleaner.getName()
                            .toLowerCase()
                            .contains(searchValue);

            boolean surnameMatches =
                    cleaner.getSurname()
                            .toLowerCase()
                            .contains(searchValue);

            boolean departmentMatches =
                    cleaner.getDepartmentName() != null
                            && cleaner.getDepartmentName()
                                    .toLowerCase()
                                    .contains(searchValue);

            if (idMatches
                    || nameMatches
                    || surnameMatches
                    || departmentMatches) 
            {

                searchResults.add(cleaner);
            }
        }

        return searchResults;
    }

    // excludeCleanerId lets updateCleaner() ignore the cleaner's own current email
    private boolean isDuplicateEmail(String email, int excludeCleanerId) 
    {

        for (Cleaner cleaner : getAllCleaners()) 
        {

            if (cleaner.getCleanerId() != excludeCleanerId
                    && cleaner.getEmail().equalsIgnoreCase(email.trim())) 
            {

                return true;
            }
        }

        return false;
    }

    private void validateCleanerDetails(
            String name,
            String surname,
            String phoneNumber,
            String email) 
    {

        if (name == null || name.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Cleaner name cannot be empty.");
        }

        if (surname == null || surname.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Cleaner surname cannot be empty.");
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
    }
}