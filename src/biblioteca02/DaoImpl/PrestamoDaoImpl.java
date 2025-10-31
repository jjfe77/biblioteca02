package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Dao.PrestamoDao;
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.time.LocalDate; 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class PrestamoDaoImpl implements PrestamoDao {
    
    private static final String PU = "biblioteca02PU";
    
    @Override
    public void guardar(Prestamo data) throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            Prestamo prestamoActivo = buscarPrestamoActivoPorUsuario(data.obtenerUsuario());
            
            if (prestamoActivo != null) {
                throw new DaoException("El usuario ya tiene un prestamo activo. Debe devolverlo primero.");
            }
            
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            
        } catch (DaoException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Error al guardar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void actualizar(Prestamo data) throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.merge(data);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Error al actualizar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public Prestamo buscarPorId(int id) throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Prestamo.class, id);
        } catch (Exception e) {
            throw new DaoException("Error al buscar por ID: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public List<Prestamo> listarTodos() throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createQuery("SELECT p FROM Prestamo p ORDER BY p.fecha_prestamo DESC", Prestamo.class)
                     .getResultList();
        } catch (Exception e) {
            throw new DaoException("Error al listar: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public Prestamo buscarPrestamoActivoPorUsuario(Usuario usuario) throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.createQuery("SELECT p FROM Prestamo p WHERE p.usuario = :usuario AND p.devuelto = false", Prestamo.class)
                     .setParameter("usuario", usuario)
                     .getSingleResult();
            
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new DaoException("Error al buscar prestamo activo: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public List<Prestamo> listarPrestamosVencidos() throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            LocalDate hoy = LocalDate.now(); 
            
            return em.createQuery("SELECT p FROM Prestamo p WHERE p.devuelto = false AND p.fecha_devolucion < :hoy ORDER BY p.fecha_devolucion ASC", Prestamo.class)
                     .setParameter("hoy", hoy)
                     .getResultList();
            
        } catch (Exception e) {
            throw new DaoException("Error al listar vencidos: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }

    @Override
    public void marcarComoDevuelto(int id_prestamo) throws DaoException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            
            Prestamo prestamo = em.find(Prestamo.class, id_prestamo);
            
            if (prestamo == null) {
                throw new DaoException("No se encontro el prestamo con ID: " + id_prestamo);
            }
            
            if (prestamo.estaDevuelto()) {
                throw new DaoException("Este prestamo ya fue devuelto.");
            }
            
            prestamo.marcarDevuelto(); 
            em.merge(prestamo);
            em.getTransaction().commit();
            
        } catch (DaoException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DaoException("Error al marcar devuelto: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}