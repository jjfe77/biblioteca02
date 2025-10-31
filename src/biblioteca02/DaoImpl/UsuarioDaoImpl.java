package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public void save(Usuario data) throws DaoException {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(data);
            em.getTransaction().commit();
            em.close();
            emf.close();
            //JOptionPane.showMessageDialog(this, "Usuario agregado correctamente.");
        } catch (Exception e) {
            throw new DaoException(e.getMessage());
        }

    }

    @Override
    public void update(Usuario data) throws DaoException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/Generat 
        //EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(data); // merge actualiza el objeto en la base de datos
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Usuario> getAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Usuario getById(long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> buscarPorApellido(String apellido) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
        EntityManager em = emf.createEntityManager();
        List<Usuario> resultados = null;
        try {
            resultados = em.createQuery(
                    "SELECT u FROM Usuario u WHERE LOWER(u.apellido) LIKE LOWER(:apellido)",
                    Usuario.class
            )
                    .setParameter("apellido", "%" + apellido + "%")
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

    @Override
    public void eliminar(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<Usuario> listar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
        EntityManager em = emf.createEntityManager();

        List<Usuario> usuarios = new ArrayList<>();
        try {
            usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return usuarios;
    }

}
