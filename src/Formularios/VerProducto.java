
package Formularios;

import Conexion.ConexionBD;
import java.awt.JobAttributes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerProducto extends javax.swing.JInternalFrame {
    
    DefaultTableModel tabla;

    public VerProducto() {
        initComponents();
        CargaListaProductos("");
    }
    
    String comparar(String cod){
        String cantidad_ver="";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM producto WHERE cod_pro='"+cod+"'");
            while(rs.next())
            {
                cantidad_ver=rs.getString(4);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VerProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad_ver;
    }

    void CargaListaProductos(String dato){
        String [] Titulo = {"Codigo","Nombre","Precio","Stock"};
        tabla=new DefaultTableModel(null,Titulo);
        String []Registro= new String[4];
        String mostrar="SELECT * FROM producto WHERE CONCAT (cod_pro,'',nombre) LIKE '%"+dato+"%'"; 
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs =st.executeQuery(mostrar);
            while(rs.next()){
                Registro[0]=rs.getString("cod_pro");
                Registro[1]=rs.getString("nombre");
                Registro[2]=rs.getString("precio");
                Registro[3]=rs.getString("stock");
                
                tabla.addRow(Registro);
            }
            tbVerProductos.setModel(tabla);
        } catch (SQLException ex) {
            Logger.getLogger(VerProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnEnviarPro = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        txtVerProduc = new javax.swing.JTextField();
        btnMostrarProduc = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVerProductos = new javax.swing.JTable();

        mnEnviarPro.setText("Enviar para Facturar");
        mnEnviarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEnviarProActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnEnviarPro);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PRODUCTOS");
        setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel1.setText("BUSCAR:");

        txtVerProduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVerProducActionPerformed(evt);
            }
        });

        btnMostrarProduc.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnMostrarProduc.setText("Mostrar todos los Productos");
        btnMostrarProduc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarProducActionPerformed(evt);
            }
        });

        tbVerProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbVerProductos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tbVerProductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVerProduc, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrarProduc))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtVerProduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarProduc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtVerProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVerProducActionPerformed
        // TODO add your handling code here:
        CargaListaProductos(txtVerProduc.getText());
    }//GEN-LAST:event_txtVerProducActionPerformed

    private void btnMostrarProducActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarProducActionPerformed
        // TODO add your handling code here:
        CargaListaProductos("");
    }//GEN-LAST:event_btnMostrarProducActionPerformed

    private void mnEnviarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEnviarProActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel tabladet = (DefaultTableModel) RegistroFactura.tbProducEnFac.getModel();
            String[]  dato=new String[5];
            int  fila = tbVerProductos.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun producto");
            }else{
                String cod_ver_produc=tbVerProductos.getValueAt(fila, 0).toString();
                String nom_ver_produc=tbVerProductos.getValueAt(fila, 1).toString();
                String precio_ver_produc=tbVerProductos.getValueAt(fila, 2).toString();
                int c=0;
                int j=0;
                String cant_ver_produc=JOptionPane.showInputDialog("ingrese cantidad");
                if((cant_ver_produc.equals("")) || (cant_ver_produc.equals("0"))){
                    JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");
                }else{
                    int canting=Integer.parseInt(cant_ver_produc);
                    int comp=Integer.parseInt(comparar(cod_ver_produc));
                    if(canting>comp){
                        JOptionPane.showMessageDialog(this,"Ud. no hay stock suficiente del producto");
                    }else{
                        for(int i=0;i<RegistroFactura.tbProducEnFac.getRowCount();i++){
                            Object com=RegistroFactura.tbProducEnFac.getValueAt(i,0);
                            if(cod_ver_produc.equals(com)){
                                j=i;
                                RegistroFactura.tbProducEnFac.setValueAt(cant_ver_produc, i, 3);
                                c=c+1;
                            }
                        }
                        if(c==0){
                            dato[0]=cod_ver_produc;
                            dato[1]=nom_ver_produc;
                            dato[2]=precio_ver_produc;
                            dato[3]=cant_ver_produc;
            
                            tabladet.addRow(dato);
                            RegistroFactura.tbProducEnFac.setModel(tabladet);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mnEnviarProActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMostrarProduc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnEnviarPro;
    private javax.swing.JTable tbVerProductos;
    private javax.swing.JTextField txtVerProduc;
    // End of variables declaration//GEN-END:variables
    ConexionBD cc= new ConexionBD();
    Connection cn=cc.conexion();
}
