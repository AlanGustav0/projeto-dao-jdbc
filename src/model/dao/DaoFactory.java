package model.dao;

import db.Database;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;
/*
    Criamos uma "fábrica de Daos", dessa forma essa classe vai expor um método que retorna o tipo da interface, mas internamente
    ela irá instanciar uma implementação. Com isso não precisamos expor a implmentação, ou seja, como é feita a instancia dessa classe.
    É também uma maneira de fazer uma injeção de dependência.

 */
public class DaoFactory {

    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(Database.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(Database.getConnection());
    }}
