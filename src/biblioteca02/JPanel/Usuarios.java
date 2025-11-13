package biblioteca02.JPanel;

import biblioteca02.Dao.DaoException;
import biblioteca02.DaoImpl.UsuarioDao;
import biblioteca02.DaoImpl.UsuarioDaoImpl;
import biblioteca02.Entidades.Usuario;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import org.eclipse.persistence.platform.database.HSQLPlatform;

public class Usuarios extends javax.swing.JPanel {

    public Usuarios() {
        initComponents();
        placeHolder();
    }
    Integer control = 0;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldDNI = new javax.swing.JTextField();
        jTextFieldDomicilio = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        jTextFieldMail = new javax.swing.JTextField();
        jButtonAgregar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUsuarios = new javax.swing.JTable();
        jButtonLimpiar = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonEditar = new javax.swing.JButton();
        jButtonListarTodos = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 745));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 3, 36)); // NOI18N
        jLabel1.setText("Usuarios");

        jLabelApellido.setText("Apellido");

        jLabel2.setText("Nombre");

        jLabel3.setText("DNI");

        jLabel4.setText("Domicilio");

        jLabel5.setText("Telefono");

        jLabel6.setText("Mail");

        jTextFieldDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDNIKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDNIKeyTyped(evt);
            }
        });

        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyTyped(evt);
            }
        });

        jButtonAgregar.setText("Agregar");
        jButtonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setToolTipText("Complete el campo apellido o parte de el para buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jTableUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Socio Nº", "Nombre", "Apellido", "DNI", "Direccion", "Telefono", "Mail"
            }
        ));
        jScrollPane1.setViewportView(jTableUsuarios);

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Guardar");
        jButtonUpdate.setEnabled(false);
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonEditar.setText("Editar");
        jButtonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarActionPerformed(evt);
            }
        });

        jButtonListarTodos.setText("Listar Usuarios");
        jButtonListarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonListarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNombre))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldDNI)
                                    .addComponent(jTextFieldDomicilio)
                                    .addComponent(jTextFieldTelefono)
                                    .addComponent(jTextFieldMail))))
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonListarTodos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(139, 139, 139))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelApellido)
                                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5))
                            .addComponent(jTextFieldTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonListarTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarActionPerformed
        UsuarioDao dao = new UsuarioDaoImpl();
        Usuario dataUsuario = new Usuario();
        validarMail();
        if (control == 1) {
            return;
        }

        dataUsuario.setNombre(jTextFieldNombre.getText());
        dataUsuario.setApellido(jTextFieldApellido.getText());
        dataUsuario.setDni(jTextFieldDNI.getText());
        dataUsuario.setDireccion(jTextFieldDomicilio.getText());
        dataUsuario.setTelefono(jTextFieldTelefono.getText());
        dataUsuario.setMail(jTextFieldMail.getText());
        try {
            dao.save(dataUsuario);
            JOptionPane.showMessageDialog(this, "Usuario agregado correctamente", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (DaoException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(dataUsuario);

    }//GEN-LAST:event_jButtonAgregarActionPerformed
    
    
    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int fila = jTableUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtenemos el ID del usuario (asumimos que la primera columna es el ID)
        Integer idUsuario = (Integer) jTableUsuarios.getValueAt(fila, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desea eliminar el usuario seleccionado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            UsuarioDaoImpl dao = new UsuarioDaoImpl();
            //----------------------------------------
            /*
            Pobar Javadoc
            dao.buscarPorApellido
            */
            //-----------------------------------------
            
            
            
            dao.eliminar(idUsuario);
            JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente.");
            //cargarTablaUsuarios(); // método para refrescar la tabla
            //jButtonBuscar.doClick();
            jButtonListarTodos.doClick();
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldDNI.setText("");
        jTextFieldDomicilio.setText("");
        jTextFieldTelefono.setText("");
        jTextFieldMail.setText("");
        placeHolder();
        jButtonAgregar.setEnabled(true);
        jButtonBuscar.setEnabled(true);
        jButtonEliminar.setEnabled(true);
        jButtonEliminar.setEnabled(true);
        jButtonUpdate.setEnabled(false);
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private Integer validarMail() {
        String email = jTextFieldMail.getText();
        control = 0;

        // Expresión regular básica para formato correo
        String patronEmail = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

        if (!email.matches(patronEmail)) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese un correo electrónico válido (ejemplo@dominio.com)",
                    "Error de validación",
                    JOptionPane.ERROR_MESSAGE);
            control = 1;

        }
        
        return control;
    }
    
    

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        int fila = jTableUsuarios.getSelectedRow();
        if (fila == -1 || jTextFieldApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla");
            return;
        }
        validarMail();
        if (control == 1) {
            return;
        }
        // Obtenemos el ID del usuario de la tabla
        Integer idUsuario = (Integer) jTableUsuarios.getValueAt(fila, 0); // asumiendo que la columna 0 es el ID

        // Creamos objeto Usuario con los datos de los textfields
        Usuario usuario = new Usuario();
        usuario.setNumero_socio(idUsuario);
        usuario.setNombre(jTextFieldNombre.getText());
        usuario.setApellido(jTextFieldApellido.getText());
        usuario.setDni(jTextFieldDNI.getText());
        usuario.setDireccion(jTextFieldDomicilio.getText());
        usuario.setTelefono(jTextFieldTelefono.getText());
        usuario.setMail(jTextFieldMail.getText());

        // Llamamos al DAO para actualizar
        UsuarioDao dao = new UsuarioDaoImpl();
        try {
            dao.update(usuario);
        } catch (DaoException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");

        usuario.setTelefono(jTextFieldTelefono.getText());
        
        //jButtonBuscarActionPerformed(evt);
        jButtonUpdate.setEnabled(false);
        jButtonAgregar.setEnabled(true);
        jButtonBuscar.setEnabled(true);
        jButtonEliminar.setEnabled(true);
        jButtonBuscar.doClick();
        jButtonLimpiar.doClick();
        //jButtonLimpiarActionPerformed(evt);

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarActionPerformed
        jButtonAgregar.setEnabled(false);
        jButtonBuscar.setEnabled(false);
        jButtonEliminar.setEnabled(false);
        int fila = jTableUsuarios.getSelectedRow(); // obtenemos la fila seleccionada
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario de la tabla primero");
            return;
        }

        // Obtenemos los datos de la fila seleccionada (ajusta las columnas según tu tabla)
        Integer id = (Integer) jTableUsuarios.getValueAt(fila, 0);
        String nombre = (String) jTableUsuarios.getValueAt(fila, 1);
        String apellido = (String) jTableUsuarios.getValueAt(fila, 2);
        String dni = (String) jTableUsuarios.getValueAt(fila, 3);
        String direccion = (String) jTableUsuarios.getValueAt(fila, 4);
        String telefono = (String) jTableUsuarios.getValueAt(fila, 5);
        String mail = (String) jTableUsuarios.getValueAt(fila, 6);

        // Cargamos los datos en los textfields
        jTextFieldNombre.setText(nombre);
        jTextFieldApellido.setText(apellido);
        jTextFieldDNI.setText(dni);
        jTextFieldDomicilio.setText(direccion);
        jTextFieldTelefono.setText(telefono);
        jTextFieldMail.setText(mail);
        jButtonUpdate.setEnabled(true);

    }//GEN-LAST:event_jButtonEditarActionPerformed

    private void jButtonListarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonListarTodosActionPerformed
        jButtonEliminar.setEnabled(true);
        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        List<Usuario> lista = dao.listar(); // tu método listar() existente

        DefaultTableModel modelo = (DefaultTableModel) jTableUsuarios.getModel();
        modelo.setRowCount(0);

        for (Usuario u : lista) {
            modelo.addRow(new Object[]{
                u.getNumero_socio(),
                u.getNombre(),
                u.getApellido(),
                u.getDni(),
                u.getDireccion(),
                u.getTelefono(),
                u.getMail()
            });
        }
    }//GEN-LAST:event_jButtonListarTodosActionPerformed
  
    //-------------------------------------------------------
    

    private void validarEntradaNumerica(java.awt.event.KeyEvent evt, javax.swing.JTextField campo, int maxDigitos) {
        char c = evt.getKeyChar();

        // --- Etapa 1: validar lo que se está escribiendo (KeyTyped)
        if (evt.getID() == java.awt.event.KeyEvent.KEY_TYPED) {
            if (Character.isISOControl(c)) {
                return;
            }
            if (!Character.isDigit(c)) {
                evt.consume();
                return;
            }
            if (campo.getText().length() >= maxDigitos) {
                evt.consume();
                return;
            }
        }

        // --- Etapa 2: limpieza general (KeyReleased)
        if (evt.getID() == java.awt.event.KeyEvent.KEY_RELEASED) {
            String texto = campo.getText();
            texto = texto.replaceAll("[^\\d]", ""); // eliminar no numéricos
            if (texto.length() > maxDigitos) {
                texto = texto.substring(0, maxDigitos); // limitar longitud
            }
            campo.setText(texto);
        }
    }

    private void jTextFieldDNIKeyTyped(java.awt.event.KeyEvent evt) {
        validarEntradaNumerica(evt, jTextFieldDNI, 8);
    }

    private void jTextFieldDNIKeyReleased(java.awt.event.KeyEvent evt) {
        validarEntradaNumerica(evt, jTextFieldDNI, 8);
    }

    private void jTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {
        validarEntradaNumerica(evt, jTextFieldTelefono, 10);
    }

    private void jTextFieldTelefonoKeyReleased(java.awt.event.KeyEvent evt) {
        validarEntradaNumerica(evt, jTextFieldTelefono, 10);
    }

    //------------------------------------------------------
    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        jButtonEliminar.setEnabled(true);
        jButtonAgregar.setEnabled(false);
        String apellido = jTextFieldApellido.getText().trim();
        if (apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un apellido para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        List<Usuario> lista = dao.buscarPorApellido(apellido);

        DefaultTableModel model = (DefaultTableModel) jTableUsuarios.getModel();
        model.setRowCount(0);

        if (lista != null && !lista.isEmpty()) {
            for (Usuario u : lista) {
                Object[] fila = new Object[]{
                    u.getNumero_socio(),
                    
                    u.getNombre(),
                    u.getApellido(),
                    u.getDni(),
                    u.getDireccion(),
                    u.getTelefono(),
                    u.getMail()
                };
                model.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron usuarios con ese apellido.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void placeHolder() {
        // Placeholder para el campo Nombre
        jTextFieldTelefono.setText("Ingrese 10 digitos");
        jTextFieldTelefono.setForeground(Color.GRAY);

        jTextFieldTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (jTextFieldTelefono.getText().equals("Ingrese 10 digitos")) {
                    jTextFieldTelefono.setText("");
                    jTextFieldTelefono.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (jTextFieldTelefono.getText().isEmpty()) {
                    jTextFieldTelefono.setForeground(Color.GRAY);
                    jTextFieldTelefono.setText("Ingrese 10 digitos");
                }
            }
        });

        // Placeholder para el campo DNI
        jTextFieldDNI.setText("Ingrese 8 digitos");
        jTextFieldDNI.setForeground(Color.GRAY);

        jTextFieldDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (jTextFieldDNI.getText().equals("Ingrese 8 digitos")) {
                    jTextFieldDNI.setText("");
                    jTextFieldDNI.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (jTextFieldDNI.getText().isEmpty()) {
                    jTextFieldDNI.setForeground(Color.GRAY);
                    jTextFieldDNI.setText("Ingrese 8 digitos");
                }
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAgregar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonEditar;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JButton jButtonListarTodos;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableUsuarios;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDomicilio;
    private javax.swing.JTextField jTextFieldMail;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables

}
