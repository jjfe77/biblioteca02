package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction; 
import javax.persistence.Persistence;

public class PrestamoDaoImpl implements PrestamoDao {

   
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");

   
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    @Override
    public void guardar(Prestamo prestamo) throws DaoException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(prestamo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DaoException("Error al guardar el prestamo: " + e.getMessage(), e);
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
    public void actualizar(Prestamo prestamo) throws DaoException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(prestamo);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DaoException("Error al actualizar el prestamo: " + e.getMessage(), e);
        } finally {
            if (em != null) em.close();
        }
    }

   
    @Override
    public void eliminar(int idPrestamo) throws DaoException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Prestamo prestamoAEliminar = em.find(Prestamo.class, idPrestamo);
            if (prestamoAEliminar != null) {
                em.remove(prestamoAEliminar);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DaoException("Error al eliminar el prestamo  " + idPrestamo, e);
        } finally {
            if (em != null) em.close();
        }
    }
    
 

    @Override
    public void marcarDevuelto(int idPrestamo) throws DaoException {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Prestamo prestamo = em.find(Prestamo.class, idPrestamo);
            if (prestamo != null) {
         
                prestamo.marcarDevuelto(); 
                em.merge(prestamo);
            } else {
                throw new DaoException("Prestamo no encontrado  " + idPrestamo);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw new DaoException("Error al marcar el prestamo como devuelto: " + e.getMessage(), e);
        } finally {
            if (em != null) em.close();
        }
    }

    @Override
  
public List<Prestamo> listarTodos() throws DaoException {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery("SELECT p FROM Prestamo p ORDER BY p.fecha_prestamo DESC", Prestamo.class)
                 .getResultList();
    } catch (Exception e) {
        throw new DaoException("Error al listar los prestamos: " + e.getMessage(), e);
    } finally {
        if (em != null) em.close();
    }
}

   @Override
public List<Prestamo> buscarPorApellido(Usuario usuario) throws DaoException {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario = :usuario ORDER BY p.fecha_prestamo DESC", Prestamo.class)
                 .setParameter("usuario", "%" + usuario + "%")
                 .getResultList();
    } catch (Exception e) {
        throw new DaoException("Error al buscar prestamos por usuario: " + e.getMessage(), e);
    } finally {
        if (em != null) em.close();
    }
}
}