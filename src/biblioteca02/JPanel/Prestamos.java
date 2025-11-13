package biblioteca02.JPanel;

import biblioteca02.Entidades.Prestamo;
import biblioteca02.Dao.DaoException;
import biblioteca02.DaoImpl.LibroDaoImpl;
import biblioteca02.DaoImpl.PrestamoDaoImpl;
import biblioteca02.DaoImpl.UsuarioDaoImpl;
import biblioteca02.Entidades.Libro;
import biblioteca02.Entidades.Usuario;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Prestamos extends javax.swing.JPanel {

    private UsuarioDaoImpl usuarioDao;
    private LibroDaoImpl libroDao;
    private PrestamoDaoImpl prestamoDao;

    private List<Usuario> usuariosEnTabla;
    private List<Libro> librosEnTabla;

    private Usuario usuarioSeleccionado;
    private Libro libroSeleccionado;
    private Prestamo prestamoSeleccionado;

    public Prestamos() {
        initComponents();
        jDateChooser1.setDate(new Date()); 
        jDateChooser2.setDate(null);
        this.usuarioDao = new UsuarioDaoImpl();
        this.libroDao = new LibroDaoImpl();
        this.prestamoDao = new PrestamoDaoImpl();
         jDateChooser1.setEnabled(false);
         jDateChooser2.setEnabled(false);
        
        agregarEventos();
        configurarTablas();
        sincronizarFechaDevolucion();
    }

    private void configurarTablas() {
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable2.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
    }

    private void agregarEventos() {
        jButton1.addActionListener(e -> buscarUsuario());
        jButton2.addActionListener(e -> buscarLibro());
        jButton9.addActionListener(e -> buscarPrestamosPorApellido());
        jButton3.addActionListener(e -> agregarPrestamo());
        jButton4.addActionListener(e -> editarPrestamo());
        jButton5.addActionListener(e -> eliminarPrestamo());
        jButton6.addActionListener(e -> listarTodosPrestamos());
        jButton7.addActionListener(e -> limpiar());
        jButton8.addActionListener(e -> actualizarTabla());
        jButton10.addActionListener(e -> marcarDevuelto());

        jTable1.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarUsuario();
        });
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) seleccionarLibro();
        });
        
        
        
        jDateChooser1.addPropertyChangeListener("date", evt -> {
            sincronizarFechaDevolucion();
        });
        
        jTable3.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) cargarPrestamoSeleccionado();
        });
    }

    private void sincronizarFechaDevolucion() {
        Date fechaEntregaDate = jDateChooser1.getDate();
        
        if (fechaEntregaDate != null) {
            LocalDate fechaEntrega = fechaEntregaDate.toInstant()
                                        .atZone(ZoneId.systemDefault())
                                        .toLocalDate();
            
            LocalDate fechaDevolucion = fechaEntrega.plusDays(7);
            
            Date fechaDevolucionDate = Date.from(fechaDevolucion.atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            jDateChooser2.setDate(fechaDevolucionDate);
        } else {
            jDateChooser2.setDate(null);
        }
    }

    private void buscarUsuario() {
        String apellido = jTextField1.getText().trim();
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un apellido.");
            return;
        }
        try {
            List<Usuario> encontrados = usuarioDao.buscarPorApellido(apellido); 
            
            List<Usuario> filtrados = new ArrayList<>();
            for (Usuario u : encontrados) {
                if (u.getApellido() != null && u.getApellido().toLowerCase().startsWith(apellido.toLowerCase())) {
                    filtrados.add(u);
                }
            }

            //this.usuariosEnTabla = filtrados;
            this.usuariosEnTabla = encontrados;
            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            modelo.setRowCount(0);
            if (usuariosEnTabla.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron usuarios que empiecen con " + apellido);
                return;
            }
            for (Usuario u : usuariosEnTabla) {
                modelo.addRow(new Object[]{u.getDni(), u.getNombre(), u.getApellido()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al buscar usuario: " + e.getMessage());
        }
    }

    private void buscarLibro() {
        String titulo = jTextField2.getText().trim();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un titulo para buscar.");
            return;
        }

        try {
            List<Libro> resultados = libroDao.findByTitulo(titulo); 

            List<Libro> filtrados = new ArrayList<>();
            for (Libro libro : resultados) {
                if (libro.getTitulo() != null && libro.getTitulo().toLowerCase().startsWith(titulo.toLowerCase())) {
                    filtrados.add(libro);
                }
            }

            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            modelo.setRowCount(0);

            if (filtrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron libros que empiecen con " + titulo);
                return;
            }

            //for (Libro libro : filtrados) {
            for (Libro libro : resultados) {
                modelo.addRow(new Object[]{
                    libro.getIsbn(),
                    libro.getTitulo(),
                    libro.getAutor(),
                });
            }

            this.librosEnTabla = resultados;

        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar libro: " + e.getMessage());
        }
    }

    private void buscarPrestamosPorApellido() {
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0);
        String apellidoParcial = jTextField3.getText().trim();
        if (apellidoParcial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el apellido a buscar.");
            return;
        }
        try {
            List<Prestamo> todos = prestamoDao.listarTodos();
            List<Prestamo> filtrados = new ArrayList<>();
            for (Prestamo p : todos) {
                String ape = p.getUsuario().getApellido();
                if (ape != null && ape.toLowerCase().contains(apellidoParcial.toLowerCase())) {
                    filtrados.add(p);
                }
            }

            if (filtrados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron prestamos para apellidos que empiecen con " + apellidoParcial);
                ((DefaultTableModel) jTable3.getModel()).setRowCount(0);
                return;
            }

            cargarPrestamosEnTabla(filtrados);
            //cargarPrestamosEnTabla(todos);
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar los prestamos: " + e.getMessage());
        }
    }

    private void seleccionarUsuario() {
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada != -1 && this.usuariosEnTabla != null && !this.usuariosEnTabla.isEmpty()) {
            this.usuarioSeleccionado = this.usuariosEnTabla.get(filaSeleccionada);
        }
    }

    private void seleccionarLibro() {
        int filaSeleccionada = jTable2.getSelectedRow();
        
        if (filaSeleccionada != -1 && this.librosEnTabla != null && !this.librosEnTabla.isEmpty()) {
            this.libroSeleccionado = this.librosEnTabla.get(filaSeleccionada);
        }
    }

    private void cargarPrestamoSeleccionado() {
        int fila = jTable3.getSelectedRow();
        if (fila == -1) {
            return;
        }

        try {
            Object idObj = jTable3.getValueAt(fila, 0);
            if (idObj == null) {
                return;
            }
            
            int idPrestamo = Integer.parseInt(idObj.toString());

            List<Prestamo> prestamos = prestamoDao.listarTodos();
            prestamoSeleccionado = null;
            
            for (Prestamo p : prestamos) {
                if (p.getId() == idPrestamo) {
                    prestamoSeleccionado = p;
                    break;
                }
            }

            if (prestamoSeleccionado == null) {
                return;
            }
            jDateChooser1.setEnabled(true);
            jDateChooser2.setEnabled(true);
            LocalDate fechaPrestamo = prestamoSeleccionado.getFechaPrestamo();
            LocalDate fechaDev = prestamoSeleccionado.getFechaDevolucion();
            
            if (fechaPrestamo != null) {
                Date fechaPrestamoDate = Date.from(fechaPrestamo.atStartOfDay(ZoneId.systemDefault()).toInstant());
                jDateChooser1.setDate(fechaPrestamoDate);
            } else {
                jDateChooser1.setDate(null);
            }
            
            if (fechaDev != null) {
                Date fechaDevDate = Date.from(fechaDev.atStartOfDay(ZoneId.systemDefault()).toInstant());
                jDateChooser2.setDate(fechaDevDate);
            } else {
                jDateChooser2.setDate(null);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar prestamo: " + e.getMessage());
        }
    }

private void agregarPrestamo() {
    
    if (usuarioSeleccionado == null || libroSeleccionado == null) {
        JOptionPane.showMessageDialog(this, "Seleccione un usuario y un libro.");
        return;
    }

    
    LocalDate fechaEntrega = LocalDate.now();  
    LocalDate fechaDevolucion = fechaEntrega.plusDays(7);  

    try {
      
        List<Prestamo> prestamos = prestamoDao.listarTodos();
        for (Prestamo p : prestamos) {
           if (p.getUsuario().getDni() == usuarioSeleccionado.getDni() && !p.estaDevuelto()) {
    String estadoPrestamo = p.estaVencido() ? "vencido" : "en préstamo";
    JOptionPane.showMessageDialog(
        this,
        "El usuario ya tiene un prestamo " + estadoPrestamo + ".\nDebe devolverlo antes de solicitar uno nuevo.",
        "Prestamo no permitido",
        JOptionPane.WARNING_MESSAGE
    );
    return;
}

        }

        Prestamo prestamo = new Prestamo(usuarioSeleccionado, libroSeleccionado, fechaEntrega, fechaDevolucion);
        prestamoDao.guardar(prestamo);
        
        JOptionPane.showMessageDialog(this, 
            "Prestamo registrado correctamente.\n" +
            "Fecha de entrega: " + fechaEntrega + "\n" +
            "Fecha de devolución: " + fechaDevolucion,
            "Exito",
            JOptionPane.INFORMATION_MESSAGE);
        
        jTextField3.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
        buscarPrestamosPorApellido();
        
        
        
        limpiar(); 

    } catch (DaoException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al guardar el prestamo: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}
    private void editarPrestamo() {
    if (prestamoSeleccionado == null) {
        JOptionPane.showMessageDialog(this, "Primero seleccione un prestamo desde la tabla para editar.");
        return;
    }
    
    Date nuevaEntregaDate = jDateChooser1.getDate();
    Date nuevaDevolucionDate = jDateChooser2.getDate();
    
    if (nuevaEntregaDate == null || nuevaDevolucionDate == null) {
        JOptionPane.showMessageDialog(this, "Seleccione ambas fechas.");
        return;
    }
    
    LocalDate nuevaEntrega = nuevaEntregaDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
    LocalDate nuevaDevolucion = nuevaDevolucionDate.toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
    LocalDate hoy = LocalDate.now();
    

    if (nuevaEntrega.isAfter(hoy)) {
        JOptionPane.showMessageDialog(this, 
            "La fecha de entrega no puede ser posterior a hoy.\n" +
            "Fecha seleccionada: " + nuevaEntrega + "\n" +
            "Fecha actual: " + hoy,
            "Error de validación",
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (nuevaDevolucion.isBefore(nuevaEntrega)) {
        JOptionPane.showMessageDialog(this, 
            "La fecha de devolucion no puede ser anterior a la de entrega.",
            "Error de validacion",
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    long diasDiferencia = ChronoUnit.DAYS.between(nuevaEntrega, nuevaDevolucion);
    
    if (diasDiferencia != 7) {
        JOptionPane.showMessageDialog(this, 
            "El prestamo debe ser exactamente de 7 dias \n" +
            "Diferencia actual: " + diasDiferencia + " dias",
            "Error de validacion",
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    try {
        prestamoSeleccionado.setFechaPrestamo(nuevaEntrega);
        prestamoSeleccionado.setFechaDevolucion(nuevaDevolucion);
        
        prestamoDao.actualizar(prestamoSeleccionado);
        
        JOptionPane.showMessageDialog(this, 
            "Prestamo actualizado correctamente.\n",
            "Exito",
            JOptionPane.INFORMATION_MESSAGE);
        jTextField3.setText(jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString());
        //limpiar();
        prestamoSeleccionado = null;
        
    } catch (DaoException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al editar prestamo: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    buscarPrestamosPorApellido();
}

    private void eliminarPrestamo() {
        int fila = jTable3.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un prestamo para eliminar.");
            return;
        }
        
        if (JOptionPane.showConfirmDialog(this, 
                "Desea eliminar el prestamo seleccionado?", 
                "Confirmar", 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                int idPrestamo = (int) jTable3.getValueAt(fila, 0);
                prestamoDao.eliminar(idPrestamo);
                
                JOptionPane.showMessageDialog(this, 
                    "Prestamo eliminado correctamente.\n",//Presione Actualizar para ver los cambios.
                    "Exito",
                    JOptionPane.INFORMATION_MESSAGE);
                listarTodosPrestamos();
                
            } catch (DaoException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar prestamo: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
private void marcarDevuelto() {
    int fila = jTable3.getSelectedRow();
    jTextField3.setText((String) jTable3.getValueAt(fila, 3));
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, 
            "Seleccione un prestamo de la tabla para marcarlo como devuelto.",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
       
        int idPrestamo = (int) jTable3.getValueAt(fila, 0);
        String estadoActual = (String) jTable3.getValueAt(fila, 9); 
        
     
        if ("Devuelto".equals(estadoActual)) {
            JOptionPane.showMessageDialog(this, 
                "Este prestamo ya fue marcado como devuelto anteriormente.",
                "Informacion",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
       
        int confirmar = JOptionPane.showConfirmDialog(this, 
            "¿Esta seguro de marcar este prestamo como devuelto?\n" +
            "El socio podra solicitar un nuevo prestamo", 
            "Confirmar devolucion", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }
        
       
        List<Prestamo> prestamos = prestamoDao.listarTodos();
        Prestamo prestamoADevolver = null;
        
        for (Prestamo p : prestamos) {
            if (p.getId() == idPrestamo) {
                prestamoADevolver = p;
                break;
            }
        }
        
        if (prestamoADevolver == null) {
            JOptionPane.showMessageDialog(this, 
                "No se encontro el prestamo seleccionado.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
     
        prestamoADevolver.marcarDevuelto();
        
       
        prestamoDao.actualizar(prestamoADevolver);
        
        JOptionPane.showMessageDialog(this, 
            "Prestamo marcado como devuelto correctamente.\n" +
            "El socio '" + prestamoADevolver.getUsuario().getNombre() + " " + 
            prestamoADevolver.getUsuario().getApellido() + "' ya puede solicitar un nuevo prestamo.",
            "Exito",
            JOptionPane.INFORMATION_MESSAGE);
    
        
       
        jTable3.clearSelection();
        
    } catch (DaoException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al marcar como devuelto: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error inesperado: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    jButton9.doClick();
}
    private void listarTodosPrestamos() {
        try {
            List<Prestamo> prestamos = prestamoDao.listarTodos();
            cargarPrestamosEnTabla(prestamos);
        } catch (DaoException e) {
            JOptionPane.showMessageDialog(this, "Error al listar prestamos: " + e.getMessage());
        }
    }

    private void actualizarTabla() {
        listarTodosPrestamos();
    }

    private void limpiar() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        
        jDateChooser1.setDate(new Date());
        
        sincronizarFechaDevolucion();
         jDateChooser1.setEnabled(false);
    jDateChooser2.setEnabled(false);
        
        ((DefaultTableModel) jTable1.getModel()).setRowCount(0);
        ((DefaultTableModel) jTable2.getModel()).setRowCount(0);

        usuarioSeleccionado = null;
        libroSeleccionado = null;
        usuariosEnTabla = null;
        librosEnTabla = null;
        prestamoSeleccionado = null;

        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
    }

    private void cargarPrestamosEnTabla(List<Prestamo> prestamos) {
        DefaultTableModel modelo = (DefaultTableModel) jTable3.getModel();
        modelo.setRowCount(0);
        for (Prestamo p : prestamos) {
            String estado = p.estaDevuelto() ? "Devuelto" : p.estaVencido() ? "Vencido" : "En prestamo";
            modelo.addRow(new Object[]{
                p.getId(),
                p.getUsuario().getDni(), p.getUsuario().getNombre(), p.getUsuario().getApellido(),
                p.getLibro().getIsbn(), p.getLibro().getTitulo(), p.getLibro().getAutor(),
                p.getFechaPrestamo(), p.getFechaDevolucion(), estado
            });
        }
    }

  

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

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

        jDateChooser2.setEnabled(false);

        jLabel1.setText("Apellido");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Buscador de Socios");

        jLabel5.setText("Fecha Entrega");

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

        jLabel6.setText("Fecha Devolucion");

        jTable3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPane3.setViewportView(jTable3);

        jButton1.setText("Buscar");

        jLabel7.setText("Apellido ");

        jLabel2.setText("Titulo");

        jButton9.setText("Buscar");

        jButton2.setText("Buscar");

        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setText("Agregar");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Buscador de Libros");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Buscador de Prestamo");

        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setText("Editar");

        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setText("Eliminar");

        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton6.setText("Listar Todos");

        jButton7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton7.setText("Limpiar");

        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton8.setText("Actualizar");
        jButton8.setEnabled(false);

        jButton10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton10.setText("Devolver");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(294, 294, 294))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(jButton1)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(33, 33, 33)
                                            .addComponent(jButton2)))
                                    .addGap(41, 41, 41)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(jButton3)
                            .addGap(52, 52, 52)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(48, 48, 48)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6)
                            .addGap(68, 68, 68)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(54, 54, 54)
                            .addComponent(jButton8)
                            .addGap(54, 54, 54)
                            .addComponent(jButton10))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(121, 125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel6)
                        .addGap(27, 27, 27)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 6, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(78, 78, 78))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
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
