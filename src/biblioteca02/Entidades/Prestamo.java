package biblioteca02.Entidades;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prestamo")
public class Prestamo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToOne
    @JoinColumn(name = "numero_socio")
    private Usuario usuario;
    
    @OneToOne
    @JoinColumn(name = "id_libro")
    private Libro libro;
    
    @Column(name = "fecha_prestamo")
    private LocalDate fecha_prestamo; 
    
    @Column(name = "fecha_devolucion")
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
   
    public int getId() {
    return id;
    }

    public void setId(int id_prestamo) {
    this.id = id_prestamo;
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
    
    public LocalDate getFechaPrestamo() { 
        return fecha_prestamo;
    }
    
    public void setFechaPrestamo(LocalDate fecha_prestamo) { 
        this.fecha_prestamo = fecha_prestamo;
    }
    
    public LocalDate getFechaDevolucion() {
        return fecha_devolucion;
    }
    
    public void setFechaDevolucion(LocalDate fecha_devolucion) { 
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