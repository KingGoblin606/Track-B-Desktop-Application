package track.b.desktop.application.Controller;

import track.b.desktop.application.Data.DepartmentRepository;
import track.b.desktop.application.Model.Department;

import java.util.ArrayList;

public class DepartmentController 
{

    private final DepartmentRepository repository;

    public DepartmentController() 
    {
        repository = new DepartmentRepository();
    }

    public ArrayList<Department> getAllDepartments() 
    {
        return repository.findAll();
    }

    public Department addDepartment(String departmentName) 
    {

        if (departmentName == null || departmentName.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }

        if (isDuplicateName(departmentName,-1)) 
        {
            throw new IllegalArgumentException("This department already exists.");
        }

        int newId = repository.findMaxId() + 1;

        Department department = new Department(
                newId,
                departmentName.trim()
        );

        repository.insert(department);

        return department;
    }

    private boolean isDuplicateName(String departmentName, int excludeDepartmentId) 
    {

        for (Department department : getAllDepartments()) 
        {

            if (department.getDepartmentId() != excludeDepartmentId
                    && department.getDepartmentName().equalsIgnoreCase(departmentName.trim())) 
            {

                return true;
            }
        }

        return false;
    }
    
        public Department findDepartmentById(int departmentId) 
        {

        for (Department department : getAllDepartments()) 
        {

            if (department.getDepartmentId() == departmentId) 
            {
                return department;
            }
        }

        return null;
    }

    public boolean updateDepartment(int departmentId, String departmentName) 
    {

        if (departmentName == null || departmentName.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Department name cannot be empty.");
        }

        if (isDuplicateName(departmentName, departmentId)) 
        {
            throw new IllegalArgumentException("This department already exists.");
        }

        Department department = findDepartmentById(departmentId);

        if (department == null) 
        {
            return false;
        }

        department.setDepartmentName(departmentName.trim());
        repository.update(department);

        return true;
    }
}