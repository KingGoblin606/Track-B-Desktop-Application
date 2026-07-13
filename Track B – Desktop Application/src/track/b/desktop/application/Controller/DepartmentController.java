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

        if (isDuplicateName(departmentName)) 
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

    private boolean isDuplicateName(String departmentName) 
    {

        for (Department department : getAllDepartments()) 
        {

            if (department.getDepartmentName()
                    .equalsIgnoreCase(departmentName.trim())) 
            {

                return true;
            }
        }

        return false;
    }
}