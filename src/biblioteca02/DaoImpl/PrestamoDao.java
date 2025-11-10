package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.util.List;


public interface PrestamoDao {
    

    void guardar(Prestamo prestamo) throws DaoException;
  
    void actualizar(Prestamo prestamo) throws DaoException;
    

    void eliminar(int idPrestamo) throws DaoException;
    
  
    void marcarDevuelto(int idPrestamo) throws DaoException;
    
   
    List<Prestamo> listarTodos() throws DaoException;
   
  
    List<Prestamo> buscarPorApellido(Usuario usuario) throws DaoException;
    
    
}