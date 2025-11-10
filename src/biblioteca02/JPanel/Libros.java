
package biblioteca02.JPanel;

import biblioteca02.Entidades.Libro;
import biblioteca02.DaoImpl.LibroDao;
import biblioteca02.DaoImpl.LibroDaoImpl;
import biblioteca02.Dao.DaoException;
import biblioteca02.DaoImpl.UsuarioDaoImpl;
import biblioteca02.Entidades.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Libros extends javax.swing.JPanel {

    public Libros() {
        initComponents();   
    }
   
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblLibros = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        lblISBN = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblAutor = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        lblAño = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        txtTitulo = new javax.swing.JTextField();
        txtGenero = new javax.swing.JTextField();
        txtEditorial = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_Libros = new javax.swing.JTable();
        txtAño = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        lblEditorial = new javax.swing.JLabel();
        btn_Agregar = new javax.swing.JButton();
        btn_Editar = new javax.swing.JButton();
        btn_Eliminar = new javax.swing.JButton();
        btn_Guardar = new javax.swing.JButton();
        btn_LimpiarCampos = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1024, 745));

        lblLibros.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblLibros.setText("         LIBROS");

        lblBuscar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblBuscar.setText("BUSCAR");

        lblISBN.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblISBN.setText(" ISBN");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setText("TITULO");

        lblAutor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAutor.setText("AUTOR");

        lblGenero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGenero.setText("GENERO");

        lblAño.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAño.setText("AÑO");

        txtISBN.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtAutor.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtGenero.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        txtEditorial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        Tabla_Libros.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Tabla_Libros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ISBN", "TITULO", "AUTOR", "GENERO", "EDITORIAL", "AÑO"
            }
        ));
        jScrollPane1.setViewportView(Tabla_Libros);

        jToolBar1.setRollover(true);

        lblEditorial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblEditorial.setText("EDITORIAL");

        btn_Agregar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_Agregar.setText("AGREGAR");
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Editar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_Editar.setText("EDITAR");
        btn_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarActionPerformed(evt);
            }
        });

        btn_Eliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_Eliminar.setText("ELIMINAR");
        btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarActionPerformed(evt);
            }
        });

        btn_Guardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_Guardar.setText("GUARDAR");
        btn_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_GuardarActionPerformed(evt);
            }
        });

        btn_LimpiarCampos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btn_LimpiarCampos.setText("LIMPIAR CAMPOS");
        btn_LimpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarCamposActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setText("BUSCAR x Autor y Titulo");
        btnBuscar.setToolTipText("Ingrese Autor y Titulo  en los campos correspondientes y oprima Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_Agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_Guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_LimpiarCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(447, 447, 447))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblAño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblISBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblAutor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEditorial, javax.swing.GroupLayout.DEFAULT_SIZE, 604, Short.MAX_VALUE)
                            .addComponent(txtGenero)
                            .addComponent(txtISBN)
                            .addComponent(txtAutor)
                            .addComponent(txtAño)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(10, 10, 10)
                                .addComponent(lblAutor))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblISBN)
                                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addComponent(txtAutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lblGenero)
                        .addGap(18, 18, 18)
                        .addComponent(lblEditorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAño)
                            .addComponent(txtAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEditorial, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_Agregar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Editar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Eliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_Guardar)
                        .addGap(18, 18, 18)
                        .addComponent(btn_LimpiarCampos)))
                .addContainerGap(625, Short.MAX_VALUE))
        );

        lblLibros.getAccessibleContext().setAccessibleName("LIBROS");
        lblISBN.getAccessibleContext().setAccessibleName("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1072, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(214, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

// BTN BUSCAR LIBRO (X TITULO) 
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String autor = txtAutor.getText().trim();
        String titulo = txtTitulo.getText().trim();
        if (autor.isEmpty() && titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un autor y/o Titulo para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LibroDaoImpl dao = new LibroDaoImpl();
        List<Libro> lista = dao.buscarPorAutor(autor, titulo);

        DefaultTableModel model = (DefaultTableModel) Tabla_Libros.getModel();
        model.setRowCount(0);

        if (lista != null && !lista.isEmpty()) {
            for (Libro u : lista) {
                Object[] fila = new Object[]{
                    u.getIsbn(),
                    u.getTitulo(),
                    u.getAutor(),
                    u.getGenero(),
                    u.getEditorial(),
                    u.getAño()
                };
                model.addRow(fila);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron autores con ese apellido.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

// BTN LIMPIAR CAMPOS
    
    private void btn_LimpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarCamposActionPerformed
        txtISBN.setText("");
        txtTitulo.setText("");
        txtAutor.setText("");
        txtGenero.setText("");
        txtEditorial.setText("");
        txtAño.setText("");

        txtISBN.requestFocus();

    }//GEN-LAST:event_btn_LimpiarCamposActionPerformed

// BTN GUARDAR LIBRO 
    
    private void btn_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_GuardarActionPerformed

        LibroDaoImpl dao = new LibroDaoImpl() {};
        Libro dataLibro = new Libro();

        dataLibro.setIsbn(txtISBN.getText());
        dataLibro.setTitulo(txtTitulo.getText());
        dataLibro.setAutor(txtAutor.getText());
        dataLibro.setGenero(txtGenero.getText());
        dataLibro.setAdicional(txtEditorial.getText());
        dataLibro.setAño(Integer.parseInt(txtAño.getText()));

        try {

            List<Libro> existentes = dao.findByTitulo(txtTitulo.getText());

            if (!existentes.isEmpty()) {

                Libro libroAActualizar = existentes.get(0);

                dataLibro.setId_libro(libroAActualizar.getId_libro());
                dao.update(dataLibro);

                JOptionPane.showMessageDialog(this, "Libro actualizado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);

            } else {

                dao.save(dataLibro);
                JOptionPane.showMessageDialog(this, "Libro guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo Año debe ser numrico.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_GuardarActionPerformed

// BTN ELIMINAR LIBRO 
    private void btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarActionPerformed

        int fila = Tabla_Libros.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,  "Debe seleccionar un libro de la tabla.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String Titulo = (String) Tabla_Libros.getValueAt(fila, 1);

        int confirm = JOptionPane.showConfirmDialog(this,"¿Desea eliminar el libro seleccionado?","Confirmar eliminación",JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                LibroDaoImpl dao = new LibroDaoImpl() {};
                dao.eliminar(Titulo);

                JOptionPane.showMessageDialog(this, "Libro eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (DaoException ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar el libro: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_EliminarActionPerformed

// BTN EDITAR LIBRO 
    
    private void btn_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarActionPerformed

        int fila = Tabla_Libros.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(this,"Debe seleccionar un libro de la tabla.","Advertencia",JOptionPane.WARNING_MESSAGE);
            return;
        }

        Libro dataLibro = new Libro();
        dataLibro.setIsbn(txtISBN.getText());
        dataLibro.setTitulo(txtTitulo.getText());
        dataLibro.setAutor(txtAutor.getText());
        dataLibro.setGenero(txtGenero.getText());
        dataLibro.setAdicional(txtEditorial.getText());
        dataLibro.setAño(Integer.parseInt(txtAño.getText()));

        try {
            LibroDaoImpl dao = new LibroDaoImpl() {};
            dao.update(dataLibro);

            JOptionPane.showMessageDialog(this,"Libro actualizado correctamente.","Éxito",JOptionPane.INFORMATION_MESSAGE);

        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(this,"Error al actualizar el libro: " + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_EditarActionPerformed

// BTN AGREGAR LIBRO 
    
    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed

        LibroDao dao = new LibroDaoImpl() {} ;
        Libro dataLibro = new Libro();

        dataLibro.setIsbn(txtISBN.getText());
        dataLibro.setTitulo(txtTitulo.getText());
        dataLibro.setAutor(txtAutor.getText());
        dataLibro.setGenero(txtGenero.getText());
        dataLibro.setAdicional(txtEditorial.getText());
        dataLibro.setAño(Integer.parseInt(txtAño.getText()));

        try {
            dao.save(dataLibro);
            JOptionPane.showMessageDialog(this,
                "Libro agregado correctamente", "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
        } catch (DaoException ex) {
            Logger.getLogger(Libros.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,
                "Error al guardar el libro: " + ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
        }

        System.out.println(dataLibro);
    }//GEN-LAST:event_btn_AgregarActionPerformed


                     



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla_Libros;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btn_Agregar;
    private javax.swing.JButton btn_Editar;
    private javax.swing.JButton btn_Eliminar;
    private javax.swing.JButton btn_Guardar;
    private javax.swing.JButton btn_LimpiarCampos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblAño;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblEditorial;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblISBN;
    private javax.swing.JLabel lblLibros;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtAño;
    private javax.swing.JTextField txtEditorial;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

    


}