/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package biblioteca02.Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Juanjo
 */
@Entity
@Table (name="prestamo")
public class Prestamo implements Serializable {

    @Id
    @GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id_prestamo;
    
    @ManyToOne 
    @JoinColumn (name= "numero_socio")
    private Usuario usuario; //la relacion con usuario ya que 1 usuario puede tener muchos prestamos
    //pero 1 prestado solo va ser de un usuario
    
    private Date fecha_prestamo;
    private Date fecha_devolucion;
    private boolean devuelto; //boolean para indicar si devolvieron o no el libro
    private Libro libro;
    
    
    public Prestamo (){ 
    }
    
    public Prestamo(int id_prestamo, Usuario usuario, Libro libro, Date fecha_prestamo,Date fecha_devolucion,
            boolean devuelto) {
     this.id_prestamo = id_prestamo;
     this.usuario= usuario;
     this.fecha_prestamo= fecha_prestamo;
     this.fecha_devolucion= fecha_devolucion;
     this.devuelto = devuelto;
    
 
    }
    
    
    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }
    
    public  Usuario getUsuario () {
    
        return usuario;
    }
    
    public void SetUsuario (Usuario usuario) {
    
        this.usuario= usuario;
    }
    
     public Libro getLibro(Libro libro) {
    
        return libro;
    }
    
    public void SetLibro (Libro libro) {
    
        this.libro= libro;
    }
    
    
     public  Date getFecha_prestamo () {
    
        return fecha_prestamo;
    }
    
    public void SetFecha_prestamo (Date fecha_prestamo) {
    
        this.fecha_prestamo= fecha_prestamo;
    }
    
        public  boolean isDevuelto () { //es un get pero se usa is x q es un boleano
    
        return devuelto;
    }
    
    public void SetDevuelto (boolean devuelto) {
    
        this.devuelto= devuelto;
    }
    
    
    @Override 
    public String toString (){
    return "Prestamo{"+ "id_prestamo=" + id_prestamo + 
            ", usuario=" +(usuario!=null? usuario.getNombre()+ "" + usuario.getApellido():"null")+
            ", libro=" +(libro!=null? libro.getTitulo():"null")+
            ", fecha_prestamo=" + fecha_prestamo +
            ", fecha_devolucion=" + fecha_devolucion +
            ", devuelto=" + devuelto +
            '}';
    }
    
}

 