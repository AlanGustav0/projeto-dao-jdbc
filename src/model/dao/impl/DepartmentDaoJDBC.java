package model.dao.impl;

import db.Database;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement st = null;

        try{
            st = connection.prepareStatement(
                    "UPDATE department "
                            + "SET Name = ? "
                            + "WHERE Id = ?");

            st.setString(1,obj.getName());
            st.setInt(2,obj.getId());

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Database.closeStatment(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st = connection.prepareStatement(
                    "DELETE FROM department "
                            + "WHERE Id = ?");

            st.setInt(1,id);

            st.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Database.closeStatment(st);
        }
    }

    @Override
    public Department findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement(
                    "SELECT * FROM department WHERE Id = ?");

            st.setInt(1,id);
            rs = st.executeQuery();

            if(rs.next()){
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;

            }

            return null;


        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Database.closeStatment(st);
            Database.closeResultSet(rs);
        }
    }

    @Override
    public List<Department> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = connection.prepareStatement(
                    "SELECT * FROM department ORDER BY Name");

            rs = st.executeQuery();

            List<Department> list = new ArrayList<>();
            while(rs.next()){
                Department obj = new Department();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                list.add(obj);
            }

            return list;

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            Database.closeStatment(st);
            Database.closeResultSet(rs);
        }
    }
}
