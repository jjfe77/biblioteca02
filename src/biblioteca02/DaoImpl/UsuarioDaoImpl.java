package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Entidades.Usuario;
import biblioteca02.JPanel.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuarioDaoImpl implements UsuarioDao{

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
        } 
            catch (Exception e) {
                throw new DaoException(e.getMessage());
        }
        
    }

    @Override
    public void update(Usuario data) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Usuario data) throws DaoException {
        /*try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("biblioteca02PU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Usuarios usuario = em.find(Usuarios.class, em);
            if (usuario !=null){
                em.remove(usuario);
                System.out.println("borrado");
            }
            else{
                System.out.println("No borrado");
            }
            
            
            em.persist(data);
            em.getTransaction().commit();  
            em.close();
            emf.close();
        } 
            catch (Exception e) {
                throw new DaoException(e.getMessage());
        }*/
    }

    @Override
    public List<Usuario> getAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario getById(long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

