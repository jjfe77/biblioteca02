package biblioteca02.Dao;

import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.util.List;

public interface PrestamoDao {
    
    void guardar(Prestamo data) throws DaoException;
    
    void actualizar(Prestamo data) throws DaoException;
    
    Prestamo buscarPorId(int id) throws DaoException;
    
    List<Prestamo> listarTodos() throws DaoException;
    
    Prestamo buscarPrestamoActivo(Usuario usuario) throws DaoException;
    
    List<Prestamo> listarVencidos() throws DaoException;
    
    void marcarDevuelto(int id_prestamo) throws DaoException;
    
    List<Prestamo> buscarPorDni(String dni) throws DaoException;
}