package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Libro;
import java.util.List;

public interface LibroDao {

    // Guarda un nuevo libro en la base de datos
    void save(Libro data) throws DaoException;

    // Actualiza un libro existente
    void update(Libro data) throws DaoException;

    // Elimina un libro por su Titulo
    void eliminar(String titulo) throws DaoException;

    // Buscar un libro por su Titulo
    Libro findByTitulo(String titulo) throws DaoException;
    
    public abstract List<Libro> buscarPorAutor(String a, String b) throws DaoException;

    
}

    
    

