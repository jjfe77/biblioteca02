package biblioteca02.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author soled
 */
@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prestamo;
    
    @OneToOne
    private Usuario usuario;
    
    @OneToOne
    private Libro libro;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_prestamo;
    
    @Temporal(TemporalType.DATE)
    private Date fecha_devolucion;
    
    // true = devuelto, false = no devuelto
    private boolean devuelto;
    
    public Prestamo() {
        this.devuelto = false;
    }
    
    public Prestamo(Usuario usuario, Libro libro, Date fecha_prestamo, Date fecha_devolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.devuelto = false;
    }
    
    public int getId_prestamo() {
        return id_prestamo;
    }
    
    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Libro getLibro() {
        return libro;
    }
    
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public Date getFecha_prestamo() {
        return fecha_prestamo;
    }
    
    public void setFecha_prestamo(Date fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }
    
    public Date getFecha_devolucion() {
        return fecha_devolucion;
    }
    
    public void setFecha_devolucion(Date fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }
    
    public boolean isDevuelto() {
        return devuelto;
    }
    
    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }
    
    public void marcarDevuelto() {
        this.devuelto = true;
    }
    
    public boolean estaVencido() {
        if (devuelto) {
            return false;
        }
        Date hoy = new Date();
        return hoy.after(fecha_devolucion);
    }
    
    public String getEstado() {
        if (devuelto) {
            return "Devuelto";
        } else if (estaVencido()) {
            return "Vencido";
        } else {
            return "Activo";
        }
    }
    
    @Override
    public String toString() {
        return "Prestamo{" + 
               "id=" + id_prestamo + 
               ", usuario=" + (usuario != null ? usuario.getNombre() : "null") +
               ", libro=" + (libro != null ? libro.getTitulo() : "null") +
               ", devuelto=" + devuelto +
               '}';
    }
}