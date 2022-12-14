    package Formularios;

import Conexion.ConexionBD;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FacturaTB extends javax.swing.JInternalFrame {

    public FacturaTB() {
        initComponents();
        CargarFacturas();
        dateFecha.setEnabled(false);
    }
    
    void CargarFacturas(){
        DefaultTableModel tabla= new DefaultTableModel();
        String []titulos={"Codigo","Cod. Encargado","Cod. Cliente","Cliente","Total","Fecha"};
        tabla.setColumnIdentifiers(titulos);
        this.TablaFactura.setModel(tabla);
        String consulta= "SELECT * FROM factura";
        String []Datos= new String [6];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next()){
                Datos[0]=rs.getString("cod_fac");
                Datos[1]=rs.getString("cod_encar");
                Datos[2]=rs.getString("cod_cliente");
                Datos[3]=rs.getString("nombre_cli");
                Datos[4]=rs.getString("total_fac");
                Datos[5]=rs.getString("fecha_fac");
                
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductosTB.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnVerFac = new javax.swing.JMenuItem();
        mnBorFac = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFactura = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdbtnCodigo = new javax.swing.JRadioButton();
        rdbtnFecha = new javax.swing.JRadioButton();
        rdbtnFacturas = new javax.swing.JRadioButton();
        txtCodigo = new javax.swing.JTextField();
        dateFecha = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();

        mnVerFac.setText("Ver Detalles");
        mnVerFac.setToolTipText("");
        mnVerFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnVerFacActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnVerFac);

        mnBorFac.setText("Eliminar");
        mnBorFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBorFacActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnBorFac);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURAS");

        TablaFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaFactura.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(TablaFactura);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        rdbtnCodigo.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        rdbtnCodigo.setText("Codigo:");
        rdbtnCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnCodigoActionPerformed(evt);
            }
        });

        rdbtnFecha.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        rdbtnFecha.setText("Fecha:");
        rdbtnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnFechaActionPerformed(evt);
            }
        });

        rdbtnFacturas.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        rdbtnFacturas.setText("Todas las facturas:");
        rdbtnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbtnFacturasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbtnCodigo)
                            .addComponent(rdbtnFecha))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdbtnFacturas)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbtnCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdbtnFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdbtnFacturas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnBuscar.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(50, 50, 684, 376);
    }// </editor-fold>//GEN-END:initComponents

    private void rdbtnCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnCodigoActionPerformed
        // TODO add your handling code here:
        if(rdbtnCodigo.isSelected()==true){
            txtCodigo.setEnabled(true);
            txtCodigo.requestFocus();
            dateFecha.setDate(null);
            dateFecha.setEnabled(false);
        }
    }//GEN-LAST:event_rdbtnCodigoActionPerformed

    private void rdbtnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnFechaActionPerformed
        // TODO add your handling code here:
        if(rdbtnFecha.isSelected()==true){
            dateFecha.setEnabled(true);
            txtCodigo.setEnabled(false);
            txtCodigo.setText("");
        }
    }//GEN-LAST:event_rdbtnFechaActionPerformed

    private void rdbtnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbtnFacturasActionPerformed
        // TODO add your handling code here:
        if(rdbtnFacturas.isSelected()==true){
            dateFecha.setEnabled(false);
            dateFecha.setDate(null);
            txtCodigo.setText("");
            txtCodigo.setEnabled(false);
            CargarFacturas();
        }
    }//GEN-LAST:event_rdbtnFacturasActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        String numero=txtCodigo.getText();
        String consulta="";
        if(rdbtnCodigo.isSelected()==true){
            consulta= "SELECT * FROM factura WHERE cod_fac='"+numero+"'";
        }
        if(rdbtnFecha.isSelected()==true){
            Date fecha=dateFecha.getDate();
            SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
            String fec=""+formatofecha.format(fecha);
            consulta= "SELECT * FROM factura WHERE fecha_fac='"+fec+"'";
        }
        if(rdbtnFacturas.isSelected()==true){
            consulta= "SELECT * FROM factura ";
        }
        DefaultTableModel tabla= new DefaultTableModel();
        String []titulos={"Codigo","Cod. Encargado","Cod. Cliente","Cliente","Total","Fecha"};
        tabla.setColumnIdentifiers(titulos);
        this.TablaFactura.setModel(tabla);

        String []Datos= new String [7];
        try {
            Statement st = cn.createStatement();
            ResultSet rs= st.executeQuery(consulta);
            while(rs.next()){
                Datos[0]=rs.getString("cod_fac");
                Datos[1]=rs.getString("cod_encar");
                Datos[2]=rs.getString("cod_cli");
                Datos[3]=rs.getString("nombre_cli");
                Datos[4]=rs.getString("total_fac");
                Datos[5]=rs.getString("fecha_fac");

                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductosTB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void mnBorFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBorFacActionPerformed
        // TODO add your handling code here:
        int filafac=TablaFactura.getSelectedRow();
    try {
            if(filafac==-1){
                JOptionPane.showMessageDialog(null, "Seleccione algun dato");
            }else{
                String  cod=(String)TablaFactura.getValueAt(filafac, 0);
                String eliminarSQL="DELETE FROM factura WHERE cod_fac = '"+cod+"'";
                try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Factura Borrado");
                    CargarFacturas();
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }catch (Exception e){
        }
    }//GEN-LAST:event_mnBorFacActionPerformed

    private void mnVerFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnVerFacActionPerformed
        // TODO add your handling code here:
        int filasele= TablaFactura.getSelectedRow();
        if(filasele==-1){
            JOptionPane.showMessageDialog(null, "No Seleciono ninguna fila");
        }else{
            DetFactura detalle = new DetFactura();
            Principal.Panel.add(detalle);
            detalle.toFront();
            detalle.setVisible(true);
            String cod_factura=TablaFactura.getValueAt(filasele, 0).toString();
            String cod_encargado=TablaFactura.getValueAt(filasele, 1).toString();
            String cod_cliente=TablaFactura.getValueAt(filasele, 2).toString();
            String nombre_cliente=TablaFactura.getValueAt(filasele, 3).toString();
            String total_factura=TablaFactura.getValueAt(filasele, 4).toString();
            String fecha_factura=TablaFactura.getValueAt(filasele, 5).toString();
            DetFactura.txtFacCod.setText(cod_factura);
            DetFactura.txtEncCod.setText(cod_encargado);
            DetFactura.txtCliCod.setText(cod_cliente);
            DetFactura.txtNomCli.setText(nombre_cliente);
            DetFactura.txtTotFac.setText(total_factura);
            DetFactura.txtFecFac.setText(fecha_factura);
            String total_det;
            double igv=0;
            double total=0;
            double subtotal=0;
            total_det=DetFactura.txtTotFac.getText();
            total=Double.parseDouble(total_det);
            subtotal=total-igv;
            igv=total*0.18;
            subtotal=total-igv;
            DetFactura.txtSubTotal.setText(Double.toString(subtotal));
            DetFactura.txtIGV.setText(""+Math.rint(igv*100)/100);
            DefaultTableModel model = (DefaultTableModel) DetFactura.tbDetalle.getModel();
            String ver="SELECT * FROM det_factura WHERE cod_fac='"+cod_factura+"'";
            String []datos= new String[5]   ;
            try {
                    Statement st = cn.createStatement();
                    ResultSet rs= st.executeQuery(ver);
                    while(rs.next())
                    {
                        datos[0]=rs.getString("cod_pro");
                        datos[1]=rs.getString("nombre");
                        datos[2]=rs.getString("cantidad_pro");
                        datos[3]=rs.getString("precio");
                        datos[4]=rs.getString("precio_total");
                        model.addRow(datos);

                    }
                    DetFactura.tbDetalle.setModel(model);
                } catch (SQLException ex) {
                Logger.getLogger(FacturaTB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_mnVerFacActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TablaFactura;
    private javax.swing.JButton btnBuscar;
    private com.toedter.calendar.JDateChooser dateFecha;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnBorFac;
    private javax.swing.JMenuItem mnVerFac;
    private javax.swing.JRadioButton rdbtnCodigo;
    private javax.swing.JRadioButton rdbtnFacturas;
    private javax.swing.JRadioButton rdbtnFecha;
    private javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
    ConexionBD cc= new ConexionBD();
    Connection cn=cc.conexion();
}
