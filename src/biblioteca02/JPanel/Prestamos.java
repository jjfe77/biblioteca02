package biblioteca02.JPanel;

import biblioteca02.Dao.DaoException;
import biblioteca02.DaoImpl.LibroDaoImpl;
import biblioteca02.DaoImpl.PrestamoDaoImpl;
import biblioteca02.DaoImpl.UsuarioDaoImpl;
import biblioteca02.Entidades.Libro;
import biblioteca02.Entidades.Prestamo;
import biblioteca02.Entidades.Usuario;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Prestamos extends javax.swing.JFrame {
    
    private UsuarioDaoImpl usuarioDao;
    private LibroDaoImpl libroDao;
    private PrestamoDaoImpl prestamoDao;
    
    private Usuario usuarioSeleccionado;
    private Libro libroSeleccionado;

    public Prestamos() {
        initComponents();
        usuarioDao = new UsuarioDaoImpl();
        libroDao = new LibroDaoImpl();
        prestamoDao = new PrestamoDaoImpl();
        
        configurarTablas();
        agregarEventos();
    }
    
    private void configurarTablas() {
        DefaultTableModel modeloUsuarios = (DefaultTableModel) jTable1.getModel();
        modeloUsuarios.setRowCount(0);
        
        DefaultTableModel modeloLibros = (DefaultTableModel) jTable2.getModel();
        modeloLibros.setRowCount(0);
        
        DefaultTableModel modeloPrestamos = (DefaultTableModel) jTable3.getModel();
        modeloPrestamos.setRowCount(0);
    }
    
    private void agregarEventos() {
        jButton1.addActionListener(e -> buscarUsuario());
        jButton2.addActionListener(e -> buscarLibro());
        jButton9.addActionListener(e -> buscarPrestamoPorDni());
        jButton3.addActionListener(e -> agregarPrestamo());
        jButton4.addActionListener(e -> marcarDevuelto());
        jButton5.addActionListener(e -> eliminarPrestamo());
        jButton6.addActionListener(e -> listarTodosPrestamos());
        jButton7.addActionListener(e -> limpiar());
        jButton8.addActionListener(e -> actualizarTabla());
        
        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                seleccionarUsuario();
            }
        });
        
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable2.getSelectedRow() != -1) {
                seleccionarLibro();
            }
        });
    }
    
    private void buscarUsuario() {
        String apellido = jTextField1.getText().trim();
        
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un apellido");
            return;
        }
        
        try {
            List<Usuario> usuarios = usuarioDao.buscarApellido(apellido);
            
            if (usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron usuarios");
                return;
            }
            
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            
            for (Usuario u : usuarios) {
                modelo.addRow(new Object[]{
                    u.obtenerDNI(),
                    u.obtenerNombre(),
                    u.obtenerApellido()
                });
            }
            
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }
    
    private void buscarLibro() {
        String titulo = jTextField2.getText().trim();
        
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un titulo");
            return;
        }
        
        try {
            Libro libro = libroDao.buscarTitulo(titulo);
            
            if (libro == null) {
                JOptionPane.showMessageDialog(this, "Libro no encontrado");
                return;
            }
            
            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            modelo.setRowCount(0);
            modelo.addRow(new Object[]{
                libro.obtenerISBN(),
                libro.obtenerTitulo(),
                libro.obtenerAutor()
            });
            
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }
    
    private void buscarPrestamoPorDni() {
        String dni = jTextField3.getText().trim();
        
        if (dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un DNI");
            return;
        }
        
        try {
            List<Prestamo> prestamos = prestamoDao.buscarPorDni(dni);
            
            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron prestamos");
                return;
            }
            
            cargarPrestamosEnTabla(prestamos);
            
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }
    
    private void seleccionarUsuario() {
        int fila = jTable1.getSelectedRow();
        if (fila != -1) {
            String dni = jTable1.getValueAt(fila, 0).toString();
            try {
                usuarioSeleccionado = usuarioDao.buscarDni(dni);
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, "Error al seleccionar usuario");
            }
        }
    }
    
    private void seleccionarLibro() {
        int fila = jTable2.getSelectedRow();
        if (fila != -1) {
            String isbn = jTable2.getValueAt(fila, 0).toString();
            try {
                libroSeleccionado = libroDao.buscarIsbn(isbn);
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, "Error al seleccionar libro");
            }
        }
    }
    
    private void agregarPrestamo() {
        if (usuarioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario");
            return;
        }
        
        if (libroSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro");
            return;
        }
        
        Date fechaEntregaDate = jDateChooser1.getDate();
        Date fechaDevolucionDate = jDateChooser2.getDate();
        
        if (fechaEntregaDate == null || fechaDevolucionDate == null) {
            JOptionPane.showMessageDialog(this, "Seleccione ambas fechas");
            return;
        }
        
        LocalDate fechaEntrega = fechaEntregaDate.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaDevolucion = fechaDevolucionDate.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        
        if (fechaDevolucion.isBefore(fechaEntrega)) {
            JOptionPane.showMessageDialog(this, "La fecha de devolucion no puede ser anterior a la de entrega");
            return;
        }
        
        long diasDiferencia = ChronoUnit.DAYS.between(fechaEntrega, fechaDevolucion);
        
        if (diasDiferencia > 7) {
            JOptionPane.showMessageDialog(this, "El prestamo no puede exceder 7 dias");
            return;
        }
        
        if (diasDiferencia == 0) {
            JOptionPane.showMessageDialog(this, "El prestamo debe ser al menos de 1 dia");
            return;
        }
        
        try {
            Prestamo prestamo = new Prestamo(usuarioSeleccionado, libroSeleccionado, 
                    fechaEntrega, fechaDevolucion);
            
            prestamoDao.guardar(prestamo);
            JOptionPane.showMessageDialog(this, "Prestamo registrado correctamente");
            limpiar();
            
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void marcarDevuelto() {
        int fila = jTable3.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un prestamo");
            return;
        }
        
        int idPrestamo = (int) jTable3.getValueAt(fila, 0);
        String estado = jTable3.getValueAt(fila, 9).toString();
        
        if (estado.equals("Devuelto")) {
            JOptionPane.showMessageDialog(this, "El prestamo ya fue devuelto");
            return;
        }
        
        int confirmar = JOptionPane.showConfirmDialog(this, 
                "Marcar como devuelto?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirmar == JOptionPane.YES_OPTION) {
            try {
                prestamoDao.marcarDevuelto(idPrestamo);
                JOptionPane.showMessageDialog(this, "Prestamo marcado como devuelto");
                actualizarTabla();
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }
    
    private void eliminarPrestamo() {
        int fila = jTable3.getSelectedRow();
        
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un prestamo");
            return;
        }
        
        int confirmar = JOptionPane.showConfirmDialog(this, 
                "Eliminar el prestamo?", "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirmar == JOptionPane.YES_OPTION) {
            DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
            modelo.removeRow(fila);
            JOptionPane.showMessageDialog(this, "Prestamo eliminado de la vista");
        }
    }
    
    private void listarTodosPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDao.listarTodos();
            
            if (prestamos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay prestamos registrados");
                return;
            }
            
            cargarPrestamosEnTabla(prestamos);
            
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }
    
    private void actualizarTabla() {
        int fila = jTable3.getSelectedRow();
        if (fila != -1) {
            int idPrestamo = (int) jTable3.getValueAt(fila, 0);
            try {
                Prestamo prestamo = prestamoDao.buscarPorId(idPrestamo);
                String estado = prestamo.estaDevuelto() ? "Devuelto" : 
                        (prestamo.estaVencido() ? "Vencido" : "En prestamo");
                jTable3.setValueAt(estado, fila, 9);
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, "Error al actualizar");
            }
        } else {
            listarTodosPrestamos();
        }
    }
    
    private void limpiar() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        
        DefaultTableModel modelo1 = (DefaultTableModel) jTable1.getModel();
        modelo1.setRowCount(0);
        
        DefaultTableModel modelo2 = (DefaultTableModel) jTable2.getModel();
        modelo2.setRowCount(0);
        
        usuarioSeleccionado = null;
        libroSeleccionado = null;
    }
    
    private void cargarPrestamosEnTabla(List<Prestamo> prestamos) {
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0);
        
        for (Prestamo p : prestamos) {
            String estado;
            if (p.estaDevuelto()) {
                estado = "Devuelto";
            } else if (p.estaVencido()) {
                estado = "Vencido";
            } else {
                estado = "En prestamo";
            }
            
            modelo.addRow(new Object[]{
                p.obtenerId(),
                p.obtenerUsuario().obtenerDNI(),
                p.obtenerUsuario().obtenerNombre(),
                p.obtenerUsuario().obtenerApellido(),
                p.obtenerLibro().obtenerISBN(),
                p.obtenerLibro().obtenerTitulo(),
                p.obtenerLibro().obtenerAutor(),
                p.obtenerFechaPrestamo(),
                p.obtenerFechaDevolucion(),
                estado
            });
        }
    }
}
 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Apellido");

        jButton1.setText("Buscar");

        jLabel2.setText("Titulo");

        jButton2.setText("Buscar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ISBN", "Titulo", "Autor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Buscador de Socios");

        jLabel4.setText("Buscador de Libros");

        jLabel5.setText("Fecha Entrega");

        jLabel6.setText("Fecha Devolucion");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Prestamo", "DNI", "Nombre", "Apellido", "ISBN", "Titulo", "Autor", "Fecha Entrega", "Fecha devolucion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton3.setText("Agregar");

        jButton4.setText("Editar");

        jButton5.setText("Eliminar");

        jButton6.setText("Listar Todos");

        jButton7.setText("Limpiar");

        jButton8.setText("Actualizar");

        jLabel7.setText("DNI");

        jButton9.setText("Buscar");

        jLabel8.setText("Buscador de Prestamo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(22, 22, 22)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1)))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(89, 89, 89)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton6)
                                .addGap(63, 63, 63)
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(36, 36, 36)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
