package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Test 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== Test 2: seller findByDepartment ===");
        Department department = new Department(2,null);
        List<Seller> list = sellerDao.findByDepartment(department);

        list.forEach(System.out::println);

        System.out.println("\n=== Test 3: seller findByDepartment ===");
        List<Seller> listAll = sellerDao.findAll();

        listAll.forEach(System.out::println);

        System.out.println("\n=== Test 4: seller insert ===");
        Seller newSeller = new Seller(null,"Greg","greg@gmail.com",new Date(),4000.0,department);
        sellerDao.insert(newSeller);

        System.out.println("Inserted new Id =" + newSeller.getId());

        System.out.println("\n=== Test 5: seller insert ===");
        seller = sellerDao.findById(1);
        seller.setName("Marta Waine");
        sellerDao.update(seller);
        System.out.println("Update completed");

        System.out.println("\n=== Test 6: seller delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();

        sellerDao.deleteById(id);
        System.out.println("Delete completed");

        sc.close();
    }
}
