package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Usuario;
import java.awt.image.ImageObserver;
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

    /**
     * Esta función recibe un String y devuelve
     * una List con todos los usuarios que contienen
     * esta combinacion en la tabla apellido de la BDD
     * juanjo_biblioteca alojada en AlwaysData.
     * @param apellido
     * @return 
     * Retorna una lista con todos los usuarios en cuyo
     * apellido coincide total o parcialmente con
     * el texto ingresado en el campo Apellido.
     */
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
    
/*    
public Usuario findByApellido(String apellido) throws DaoException {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
    EntityManager em = emf.createEntityManager();
    
    try {
        List<Usuario> lista = em.createQuery(
            "SELECT u FROM Usuario u WHERE LOWER(u.apellido) LIKE LOWER(:apellido)",
            Usuario.class
        )
        .setParameter("apellido", "%" + apellido + "%")
        .getResultList();

        if (lista.isEmpty()) {
            return null;
        } else if (lista.size() > 1) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Se encontraron " + lista.size() + " usuarios con ese apellido. Mostrando el primero.", 
                "Múltiples resultados", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        return lista.get(0);

    } catch (Exception e) {
        throw new DaoException("Error al buscar usuario: " + e.getMessage());
    } finally {
        em.close();
        emf.close();
    }
}
*/
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

    
    /**
     * Esta función que busca a todos los usuarios
     * almacenados en la BDD juanjo_biblioteca
     * @return 
     * Retorna una lista con todos los usuarios regitrados
     */
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
