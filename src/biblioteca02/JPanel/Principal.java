
package biblioteca02.JPanel;

import java.awt.BorderLayout;

public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Gestión de Bibliotecas");
        Usuarios usuariol = new Usuarios () ;
        usuariol. setSize (1024, 745) ;
        usuariol. setLocation (0, 0);
        jPanelContent.removeAll () ;
        jPanelContent.add (usuariol, BorderLayout. CENTER);
        jPanelContent.revalidate () ;
        jPanelContent.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelContent = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuUsuarios = new javax.swing.JMenu();
        jMenuLibros = new javax.swing.JMenu();
        jMenuPrestamos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanelContentLayout = new javax.swing.GroupLayout(jPanelContent);
        jPanelContent.setLayout(jPanelContentLayout);
        jPanelContentLayout.setHorizontalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        jPanelContentLayout.setVerticalGroup(
            jPanelContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );

        jMenuUsuarios.setText("Usuarios");
        jMenuUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUsuariosMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuUsuarios);

        jMenuLibros.setText("Libros");
        jMenuLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuLibrosMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuLibros);

        jMenuPrestamos.setText("Prestamos");
        jMenuPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuPrestamosMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuPrestamos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUsuariosMouseClicked
        Usuarios usuariol = new Usuarios () ;
        usuariol. setSize (1024, 745) ;
        usuariol. setLocation (0, 0);
        jPanelContent.removeAll () ;
        jPanelContent.add (usuariol, BorderLayout. CENTER);
        jPanelContent.revalidate () ;
        jPanelContent.repaint();
    }//GEN-LAST:event_jMenuUsuariosMouseClicked

    private void jMenuLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuLibrosMouseClicked
        Libros librol = new Libros () ;
        librol. setSize (1024, 745) ;
        librol. setLocation (0, 0);
        jPanelContent.removeAll () ;
        jPanelContent.add (librol, BorderLayout. CENTER);
        jPanelContent.revalidate () ;
        jPanelContent.repaint();
    }//GEN-LAST:event_jMenuLibrosMouseClicked

    private void jMenuPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuPrestamosMouseClicked
        Prestamos prestamol = new Prestamos () ;
        prestamol. setSize (1024, 745) ;
        prestamol. setLocation (0, 0);
        jPanelContent.removeAll () ;
        jPanelContent.add (prestamol, BorderLayout. CENTER);
        jPanelContent.revalidate () ;
        jPanelContent.repaint();
    }//GEN-LAST:event_jMenuPrestamosMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuLibros;
    private javax.swing.JMenu jMenuPrestamos;
    private javax.swing.JMenu jMenuUsuarios;
    private javax.swing.JPanel jPanelContent;
    // End of variables declaration//GEN-END:variables


    
}
