
    
package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Libro;


public interface LibroDao {
    
    void save(Libro data) throws DaoException;
    void update(Libro data) throws DaoException;
    void eliminar(Integer id) throws DaoException;
    
    
}

    
    

