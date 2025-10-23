package biblioteca02.Dao;

import java.util.List;

public interface Dao<T> {
    
    public abstract void save(T data) throws DaoException;
    
    public abstract void update(T data) throws DaoException;

   public abstract List<T> getAll() throws DaoException;
    
    public abstract T getById(long id) throws DaoException;    
    
    public abstract void eliminar(Integer id) throws DaoException;
    
    //public abstract List<T> buscarPorApellido(String a) throws DaoException;
}
