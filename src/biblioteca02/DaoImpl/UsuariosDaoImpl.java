package biblioteca02.DaoImpl;

import biblioteca02.Dao.DaoException;
import biblioteca02.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UsuariosDaoImpl implements UsuariosDao{

    @Override
    public void save(Usuarios data) throws DaoException {
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
    public void update(Usuarios data) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Usuarios data) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuarios> getAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuarios getById(long id) throws DaoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
