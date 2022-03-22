package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.List;
import java.util.Scanner;

public class Program2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n=== Test 1: Department insert ===");
        Department dep = new Department(null,"Technology");
        departmentDao.insert(dep);

        System.out.println("Inserted new Id = " + dep.getId());


        System.out.println("\n=== Test 2: Department update ===");
        dep = new Department(1,"Sales");
        departmentDao.update(dep);

        System.out.println("Updated Id = " + dep.getId());

        System.out.println("\n=== Test 3: department delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();

        departmentDao.deleteById(id);
        System.out.println("Delete completed");

        System.out.println("\n=== Test 4: findAll department ===");
        List<Department> listDep = departmentDao.findAll();
        listDep.forEach(System.out::println);

        sc.close();
    }
}
