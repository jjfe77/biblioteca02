
package biblioteca02.DaoImpl;


import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Libro;
import java.awt.HeadlessException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;




public class LibroDaoImpl implements LibroDao {


    
    @Override
    public void save(Libro data) throws DaoException {
    
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            em.close();
            emf.close();
        } catch (Exception e) {
            throw new DaoException("Error al guardar el libro: " + e.getMessage());
        }
    }   

  
    @Override
    public void update(Libro data) throws DaoException {
      
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
    EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();
        em.merge(data); 
        em.getTransaction().commit();

        
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Libro actualizado correctamente:\n" + data.getTitulo(),
            "Actualización exitosa",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );

    } catch (HeadlessException e) {
        
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Error al actualizar el libro:\n" + e.getMessage(),
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
        throw new DaoException("Error al actualizar el libro: " + e.getMessage());

    } finally {
        
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
 }
  
    
    @Override
    public void eliminar(String titulo) throws DaoException {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
    EntityManager em = emf.createEntityManager();

    try {
        em.getTransaction().begin();

   
        Libro libro = em.find(Libro.class, titulo);

        if (libro != null) {
            em.remove(libro);
            em.getTransaction().commit();

            javax.swing.JOptionPane.showMessageDialog(
                null,"Libro eliminado correctamente:\n" + libro.getTitulo(),"Eliminación exitosa", javax.swing.JOptionPane.INFORMATION_MESSAGE );
        } else {
           
            javax.swing.JOptionPane.showMessageDialog(
                null,
                " No se encontró un libro con el Titulo " + titulo,"Aviso",javax.swing.JOptionPane.WARNING_MESSAGE);
        }

    } catch (HeadlessException e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        javax.swing.JOptionPane.showMessageDialog(
            null,"Error al eliminar el libro:\n" + e.getMessage(),"Error",javax.swing.JOptionPane.ERROR_MESSAGE);

        throw new DaoException("Error al eliminar el libro: " + e.getMessage());

    } finally {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
 }
    
    
    @Override
    public Libro findByTitulo(String titulo) throws DaoException {
        
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
    EntityManager em = emf.createEntityManager();

    try {
        List<Libro> lista = em.createQuery(
            "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class).setParameter("titulo", "%" + titulo + "%").getResultList();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron libros con ese título.", "Sin resultados", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Se encontraron " + lista.size() + " libro(s).", "Resultados", JOptionPane.INFORMATION_MESSAGE);
        }

        return (Libro) lista;

    } catch (HeadlessException e) {
        throw new DaoException("Error al buscar libros: " + e.getMessage());
    } finally {
        em.close();
        emf.close();
    }
  }
    
    @Override
    public List<Libro> buscarPorAutor(String autor, String titulo) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
        EntityManager em = emf.createEntityManager();
        List<Libro> resultados = null;
        try {
            resultados = em.createQuery(
                    //"SELECT u FROM Libro u WHERE LOWER(u.autor) LIKE LOWER(:autor)",
                    "SELECT u FROM libro u WHERE LOWER (u.autor) LIKE LOWER(:autor)  AND LOWER (u.titulo) LIKE LOWER(:titulo)",
                    Libro.class
            )
                    .setParameter("autor", "%" + autor + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            resultados = java.util.Collections.emptyList();
        } finally {
            em.close();
            emf.close();
        }
        return resultados;
    }

 }
    
    

    
    



