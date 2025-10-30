package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.util.List;

public interface PrestamoDao {
    
    void save(Prestamo data) throws DaoException;
    
    void update(Prestamo data) throws DaoException;
    
    Prestamo getById(int id) throws DaoException;
    
    List<Prestamo> listar() throws DaoException;
    
    Prestamo buscarPrestamoActivoPorUsuario(Usuario usuario) throws DaoException;
    
    List<Prestamo> listarPrestamosVencidos() throws DaoException;
    
    void marcarDevuelto(int id_prestamo) throws DaoException;
}