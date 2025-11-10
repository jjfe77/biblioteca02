package biblioteca02.Dao;

public class DaoException extends Exception {

   
     
    public DaoException(String msg) {
        super(msg);
    }

   
    public DaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}