
package biblioteca02.Entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libro;
    private String isbn;
    private String titulo;
    private String autor;
    private String genero;
    private String editorial;
    private int año;

    
    // Constructor vacío 
    public Libro() {
    }

    // Constructor con parámetros 
    public Libro(int id_libro, String isbn, String titulo, String autor, String genero, String editorial, int año) {
        this.id_libro = id_libro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editorial = editorial;
        this.año = año;
    }

    
// Getters y Setters 
    
public String getIsbn() { return isbn; }
public void setIsbn(String isbn) { this.isbn = isbn; }

public String getTitulo() { return titulo; }
public void setTitulo(String titulo) { this.titulo = titulo; }

public String getAutor() { return autor; }
public void setAutor(String autor) { this.autor = autor; }

public String getGenero() { return genero; }
public void setGenero(String genero) { this.genero = genero; }

public String getEditorial() { return editorial; }
public void setAdicional(String adicional) { this.editorial = adicional; }

public int getAño() { return año; }
public void setAño(int año) { this.año = año; }


// Metodo toString 
    @Override
    public String toString() {
        return "Libro{" + "id_libro=" + id_libro +  ", isbn=" + isbn +  ", titulo=" + titulo +  
        ", autor=" + autor +  ", genero=" + genero + ", editorial=" + editorial +  ", año=" + año +  '}';
    }
    
 }

