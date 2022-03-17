package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("\n=== Test 1: Department insert ===");
        Department dep = new Department(null,"Technology");
        departmentDao.insert(dep);

        System.out.println("Inserted new Id =" + dep.getId());
    }
}
