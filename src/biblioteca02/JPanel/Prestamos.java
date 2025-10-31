
   package biblioteca02.JPanel;

import biblioteca02.Dao.DaoException;
import biblioteca02.DaoImpl.PrestamoDaoImpl;
import biblioteca02.DaoImpl.UsuarioDaoImpl; 
import biblioteca02.DaoImpl.LibroDaoImpl;   
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;     
import biblioteca02.Entidades.Libro;       

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Prestamos extends javax.swing.JPanel {

    private PrestamoDaoImpl prestamoDao;
    private UsuarioDaoImpl usuarioDao; 
    private LibroDaoImpl libroDao; 

    private Usuario socioEncontrado = null;
    private Libro libroEncontrado = null;

   
    public Prestamos() {
        initComponents();
        
        try {
            prestamoDao = new PrestamoDaoImpl();
            usuarioDao = new UsuarioDaoImpl(); 
            libroDao = new LibroDaoImpl();

            cargarTabla(); 
            registrarEventos(); 

        } catch (Exception e) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, "Error al inicializar DAOs", e);
            JOptionPane.showMessageDialog(this, "Error de inicialización: " + e.getMessage(), "Error Grave", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void registrarEventos() {
        jButton1.addActionListener(this::accionBuscarSocio);      
        jButton2.addActionListener(this::accionBuscarLibro);     
        jButton3.addActionListener(this::accionAgregarPrestamo);  
        jButton4.addActionListener(this::accionEditarPrestamo);   
        jButton5.addActionListener(this::accionMarcarDevuelto);  
        jButton6.addActionListener(this::accionActualizarTabla);  
        
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                cargarCamposDesdeTabla();
            }
        });
    }

   
    private void cargarTabla() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); 

            List<Prestamo> prestamos = prestamoDao.listar();

            for (Prestamo p : prestamos) {
                
                String nombreSocio = p.getUsuario() != null ? p.getUsuario().getNombre() : "N/A";
                String apellidoSocio = p.getUsuario() != null ? p.getUsuario().getApellido() : "N/A";
                String dniSocio = p.getUsuario() != null ? p.getUsuario().getDni() : "N/A";

                String tituloLibro = p.getLibro() != null ? p.getLibro().getTitulo() : "N/A";
                String isbnLibro = p.getLibro() != null ? p.getLibro().getIsbn() : "N/A";
                String autorLibro = p.getLibro() != null ? p.getLibro().getAutor() : "N/A";

                model.addRow(new Object[]{
                    p.getId_prestamo(),
                    nombreSocio,
                    apellidoSocio,
                    dniSocio,
                    tituloLibro,
                    isbnLibro,
                    autorLibro,
                    p.getFecha_prestamo(),
                    p.getFecha_devolucion(),
                    p.getEstado()
                });
            }
        } catch (DaoException ex) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de préstamos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
    private void limpiarCampos() {
        jTextField1.setText(""); 
        jTextField2.setText(""); 
        jTextField3.setText(""); 
        jTextField4.setText(""); 
        jTextField5.setText(""); 
        jTextField6.setText(""); 
        jTextField7.setText(""); 
        jTextField8.setText(""); 
        
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        
        socioEncontrado = null;
        libroEncontrado = null;
        
        jTextField4.requestFocus();
    }
    
    
    private void cargarCamposDesdeTabla() {
        int fila = jTable1.getSelectedRow();
        if (fila != -1) {
            try {
               
                int idPrestamo = (Integer) jTable1.getValueAt(fila, 0);
                Prestamo p = prestamoDao.getById(idPrestamo);
                
                if (p != null) {
                    socioEncontrado = p.getUsuario();
                    if (socioEncontrado != null) {
                        jTextField4.setText(socioEncontrado.getApellido()); 
                        jTextField1.setText(socioEncontrado.getNombre());
                        jTextField2.setText(socioEncontrado.getApellido());
                        jTextField3.setText(socioEncontrado.getDni());
                    } else {
                        limpiarCampos(); 
                    }

                    libroEncontrado = p.getLibro();
                    if (libroEncontrado != null) {
                        jTextField5.setText(libroEncontrado.getTitulo()); 
                        jTextField6.setText(libroEncontrado.getTitulo());
                        jTextField7.setText(libroEncontrado.getIsbn());
                        jTextField8.setText(libroEncontrado.getAutor());
                    } else {
                        limpiarCampos(); 
                    }

                    jDateChooser1.setDate(p.getFecha_prestamo());
                    jDateChooser2.setDate(p.getFecha_devolucion());
                } else {
                    JOptionPane.showMessageDialog(this, "Préstamo no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                    limpiarCampos();
                }

            } catch (Exception ex) {
                Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al cargar datos del préstamo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    private void accionBuscarSocio(java.awt.event.ActionEvent evt) {
        String apellido = jTextField4.getText().trim();
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el apellido del socio para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        jTextField1.setText(""); 
        jTextField2.setText(""); 
        jTextField3.setText(""); 
        socioEncontrado = null; 

        try {
            socioEncontrado = usuarioDao.findByApellido(apellido); 
            
            if (socioEncontrado != null) {
                jTextField1.setText(socioEncontrado.getNombre());
                jTextField2.setText(socioEncontrado.getApellido());
                jTextField3.setText(socioEncontrado.getDni());
                JOptionPane.showMessageDialog(this, "Socio encontrado: " + socioEncontrado.getNombre() + " " + socioEncontrado.getApellido(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Socio no encontrado con el apellido: " + apellido, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (DaoException ex) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al buscar socio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
  
    private void accionBuscarLibro(java.awt.event.ActionEvent evt) {
        String titulo = jTextField5.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el título del libro para buscar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        jTextField6.setText(""); 
        jTextField7.setText(""); 
        jTextField8.setText(""); 
        libroEncontrado = null;
        
        try {
            libroEncontrado = libroDao.findByTitulo(titulo); 
            
            if (libroEncontrado != null) {
                jTextField6.setText(libroEncontrado.getTitulo());
                jTextField7.setText(libroEncontrado.getIsbn());
                jTextField8.setText(libroEncontrado.getAutor());
                JOptionPane.showMessageDialog(this, "Libro encontrado: " + libroEncontrado.getTitulo(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Libro no encontrado con el título: " + titulo, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } catch (DaoException ex) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al buscar libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionAgregarPrestamo(java.awt.event.ActionEvent evt) {
        if (socioEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un socio válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (libroEncontrado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar y seleccionar un libro válido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Date fechaPrestamo = jDateChooser1.getDate();
        Date fechaDevolucion = jDateChooser2.getDate();

        if (fechaPrestamo == null || fechaDevolucion == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar la fecha de préstamo y de devolución.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (fechaDevolucion.before(fechaPrestamo)) {
            JOptionPane.showMessageDialog(this, "La fecha de devolución no puede ser anterior a la de préstamo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Prestamo nuevoPrestamo = new Prestamo(socioEncontrado, libroEncontrado, fechaPrestamo, fechaDevolucion);

        try {
            prestamoDao.save(nuevoPrestamo);
            JOptionPane.showMessageDialog(this, "Préstamo agregado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (DaoException ex) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al guardar el préstamo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void accionEditarPrestamo(java.awt.event.ActionEvent evt) {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un préstamo de la tabla para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object idObj = jTable1.getValueAt(fila, 0);
        if (!(idObj instanceof Integer)) {
             JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        int idPrestamo = (Integer) idObj;

        Date nuevaFechaPrestamo = jDateChooser1.getDate();
        Date nuevaFechaDevolucion = jDateChooser2.getDate();

        if (nuevaFechaPrestamo == null || nuevaFechaDevolucion == null) {
             JOptionPane.showMessageDialog(this, "Debe seleccionar las nuevas fechas de préstamo y devolución.", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        if (nuevaFechaDevolucion.before(nuevaFechaPrestamo)) {
            JOptionPane.showMessageDialog(this, "La fecha de devolución no puede ser anterior a la de préstamo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Prestamo prestamoAEditar = prestamoDao.getById(idPrestamo);

            if (prestamoAEditar == null) {
                throw new DaoException("El préstamo a editar no existe.");
            }
            
            if (prestamoAEditar.isDevuelto()) {
                JOptionPane.showMessageDialog(this, "El préstamo ya fue devuelto y no puede ser editado.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            prestamoAEditar.setFecha_prestamo(nuevaFechaPrestamo);
            prestamoAEditar.setFecha_devolucion(nuevaFechaDevolucion);

            prestamoDao.update(prestamoAEditar);
            JOptionPane.showMessageDialog(this, "Préstamo ID " + idPrestamo + " actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            cargarTabla();
        } catch (DaoException ex) {
            Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al editar el préstamo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionMarcarDevuelto(java.awt.event.ActionEvent evt) {
        int fila = jTable1.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un préstamo de la tabla para marcar como devuelto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object idObj = jTable1.getValueAt(fila, 0);
        if (!(idObj instanceof Integer)) {
             JOptionPane.showMessageDialog(this, "No se pudo obtener el ID del préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        int idPrestamo = (Integer) idObj;
        String estadoActual = (String) jTable1.getValueAt(fila, 9); 


        if ("Devuelto".equals(estadoActual)) {
             JOptionPane.showMessageDialog(this, "Este préstamo ya ha sido devuelto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
             return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea marcar como devuelto el préstamo ID: " + idPrestamo + "?",
                "Confirmar Devolución", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                prestamoDao.marcarDevuelto(idPrestamo);
                JOptionPane.showMessageDialog(this, "Préstamo ID " + idPrestamo + " marcado como devuelto.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarTabla();
            } catch (DaoException ex) {
                Logger.getLogger(Prestamos.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error al marcar devuelto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void accionActualizarTabla(java.awt.event.ActionEvent evt) {
        limpiarCampos();
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Tabla de préstamos actualizada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
   


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1024, 745));

        jLabel1.setText("Nombre");

        jLabel2.setText("Apellido");

        jLabel3.setText("DNI");

        jLabel4.setText("Apellido Socio");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Buscar");

        jLabel5.setText("Titulo");

        jLabel6.setText("ISBN");

        jLabel7.setText("Autor");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Datos Personales ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Datos del libro");

        jLabel10.setText("Titulo Libro");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Buscar");

        jLabel11.setText("     Fecha de Prestamo");

        jLabel12.setText("    Fecha Devolucion");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Prestamo", "Nombre", "Apellido", "DNI", "Titulo", "ISBN", "Autor", "Fecha prestamo", "Fecha vencimiento", "Devolucion Actual"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Agregar");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Editar");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Eliminar");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Actualizar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4))))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(55, 55, 55)
                                .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                    .addComponent(jTextField6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(60, 60, 60)
                                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1045, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}