package biblioteca02.Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

<<<<<<< HEAD
=======
/**
 *
 * @author Juanjo
 * @author Candela
 * @author Brenda
 */
>>>>>>> candela-1
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
    
    private LocalDate fecha_prestamo; 
    private LocalDate fecha_devolucion; 
    
    private boolean devuelto;
    
    public Prestamo() {
        this.devuelto = false;
    }
    
    public Prestamo(Usuario usuario, Libro libro, LocalDate fecha_prestamo, LocalDate fecha_devolucion) {
        this.usuario = usuario;
        this.libro = libro;
        this.fecha_prestamo = fecha_prestamo;
        this.fecha_devolucion = fecha_devolucion;
        this.devuelto = false;
    }
    
    public int obtenerId() {
        return id_prestamo;
    }
    
    public void establecerId(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }
    
    public Usuario obtenerUsuario() {
        return usuario;
    }
    
    public void establecerUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Libro obtenerLibro() {
        return libro;
    }
    
    public void establecerLibro(Libro libro) {
        this.libro = libro;
    }
    
    public LocalDate obtenerFechaPrestamo() { 
        return fecha_prestamo;
    }
    
    public void establecerFechaPrestamo(LocalDate fecha_prestamo) { 
        this.fecha_prestamo = fecha_prestamo;
    }
    
    public LocalDate obtenerFechaDevolucion() {
        return fecha_devolucion;
    }
    
    public void establecerFechaDevolucion(LocalDate fecha_devolucion) { 
        this.fecha_devolucion = fecha_devolucion;
    }
   
    public boolean estaDevuelto() {
        return devuelto;
    }
    
    public void marcarDevuelto() {
        this.devuelto = true;
    }
    
    public boolean estaVencido() {
        if (devuelto) {
            return false;
        }
        
        LocalDate hoy = LocalDate.now(); 
        return hoy.isAfter(fecha_devolucion);
    }
}