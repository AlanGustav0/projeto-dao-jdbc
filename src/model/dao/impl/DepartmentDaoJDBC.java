package model.dao.impl;

import db.Database;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{

            st = connection.prepareStatement(
                    "INSERT INTO department (Name) VALUES "
                    + "(?)", Statement.RETURN_GENERATED_KEYS);
              st.setString(1,obj.getName());

              int rowsAffected = st.executeUpdate();

              if(rowsAffected > 0){
                  rs = st.getGeneratedKeys();
                  if(rs.next()){
                      int dep = rs.getInt(1);
                      obj.setId(dep);
                  }
              }else{
                  throw new DbException("Unexpected error!");
              }
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Database.closeStatment(st);
            Database.closeResultSet(rs);
        }
    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
