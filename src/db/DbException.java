package db;

public class DbException extends RuntimeException{

    //Exception personalizada
    public DbException(String msg){
        super(msg);
    }
}
