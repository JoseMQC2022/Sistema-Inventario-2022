package Formularios;

import Conexion.ConexionBD;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class ProductosTB extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    
    public ProductosTB() {
        initComponents();
        this.setLocation(50,25 );
        Bloqueo();
        Cargar("");
    }
    
    void Bloqueo(){
    txtCodigo.setEnabled(false);
    txtNombre.setEnabled(false);
    txtPrecio.setEnabled(false);
    txtStock.setEnabled(false);
    btnRegistrar.setEnabled(false);
    btnNuevo.setEnabled(true);
    btnCancelar.setEnabled(false);
    btnModificar.setEnabled(false);
    }
    
    void Limpiar(){
    txtCodigo.setText("");
    txtNombre.setText("");
    txtPrecio.setText("");
    txtStock.setText("");
    }
    
    void Desbloqueo(){
    txtCodigo.setEnabled(true);
    txtNombre.setEnabled(true);
    txtPrecio.setEnabled(true);
    txtStock.setEnabled(true);
    
    btnRegistrar.setEnabled(true);
    btnNuevo.setEnabled(false);
    btnCancelar.setEnabled(true);
    }
    
    void Cargar(String valor) {
        try{
            String [] titulos={"Codigo","Nombre","Precio","Stock"};
            String [] registro= new String[4];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from producto WHERE CONCAT (nombre,'',precio) LIKE '%"+valor+"%'";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registro[0]=rs.getString(1);
                registro[1]=rs.getString(2);
                registro[2]=rs.getString(3);
                registro[3]=rs.getString(4);
                model.addRow(registro);      
            }
            TablaProducto.setModel(model);
            TablaProducto.getColumnModel().getColumn(0).setPreferredWidth(150);
            TablaProducto.getColumnModel().getColumn(1).setPreferredWidth(300);
            TablaProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
            }catch(Exception e){
                System.out.println(e.getMessage());
        }
    }
    
    
    void BuscarProductoEditar(String cod) {
        try{
            String codigo="",nombre="",precio="",stock="";
            String cons="select * from producto WHERE cod_pro='"+cod+"'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                codigo=rs.getString(1);
                nombre=rs.getString(2);
                precio=rs.getString(3);
                stock=rs.getString(4);
            }
            txtCodigo.setText(codigo);
            txtNombre.setText(nombre);
            txtPrecio.setText(precio);
            txtStock.setText(stock);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            }
    }
        
    void Codigos(){
        int j;
        int contador=1;
        String numero="";
        String c="";
        String SQL="select max(cod_pro) from producto";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                c=rs.getString(1);
            }
            if(c==null){
                txtCodigo.setText("P001");
            }else{
                char r1=c.charAt(2);
                char r2=c.charAt(3);
                String r="";
                r=""+r1+r2;
                j=Integer.parseInt(r);
                ValorCodigos gen= new ValorCodigos();
                gen.generar(j);
                txtCodigo.setText("P"+gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(FacturaTB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuContext = new javax.swing.JPopupMenu();
        Actualizar = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProducto = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnMostrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnVolver1 = new javax.swing.JButton();

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        MenuContext.add(Actualizar);

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        MenuContext.add(Eliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("STOCK PRODUCTOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jLabel1.setText("Codigo:");

        jLabel3.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jLabel3.setText("Precio:");

        jLabel4.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jLabel4.setText("Stock:");

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });

        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jLabel2.setText("Nombre:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaProducto.setComponentPopupMenu(MenuContext);
        jScrollPane1.setViewportView(TablaProducto);

        jLabel10.setFont(new java.awt.Font("Impact", 0, 11)); // NOI18N
        jLabel10.setText("Buscar:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnMostrar.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRegistrar.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnVolver1.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnVolver1.setText("Volver");
        btnVolver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolver1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnVolver1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnRegistrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnCancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnVolver1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMostrar)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(50, 50, 668, 342);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
        txtCodigo.transferFocus();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
        Cargar(txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        // TODO add your handling code here:
        String buscar=txtBuscar.getText();
        if(txtBuscar!=null){
            try{
                String [] titulos={"Codigo","Nombre","Precio","Stock"};
                String [] registro= new String[4];
                model=new DefaultTableModel(null,titulos);

                String cons="select * from producto WHERE CONCAT (nombre,'',precio) LIKE '%"+buscar+"%'";
                Statement st= cn.createStatement();
                ResultSet rs = st.executeQuery(cons);
                while(rs.next()){
                    registro[0]=rs.getString(1);
                    registro[1]=rs.getString(2);
                    registro[2]=rs.getString(3);
                    registro[3]=rs.getString(4);
                    model.addRow(registro);      
                }
                TablaProducto.setModel(model);
                TablaProducto.getColumnModel().getColumn(0).setPreferredWidth(150);
                TablaProducto.getColumnModel().getColumn(1).setPreferredWidth(300);
                TablaProducto.getColumnModel().getColumn(2).setPreferredWidth(100);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
        }else{
            Cargar("");
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        Bloqueo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        String codigo,nombre,precio,stock;
        String sql="";
        codigo=txtCodigo.getText();
        nombre=txtNombre.getText();
        precio=txtPrecio.getText();
        stock=txtStock.getText();
        sql="INSERT INTO producto (cod_pro,nombre,precio,stock) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst  = cn.prepareStatement(sql);
            pst.setString(1, codigo);
            pst.setString(2, nombre);
            pst.setString(3, precio);
            pst.setString(4, stock);
            int n=pst.executeUpdate();
            if(n>0){
                JOptionPane.showMessageDialog(null, "Producto registrado con Exito");
                Bloqueo();
            }
            Cargar("");
        } catch (SQLException ex) {
            Logger.getLogger(ProductosTB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        Desbloqueo();
        Limpiar();
        txtCodigo.requestFocus();
        Codigos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        String sql="UPDATE producto SET precio = '"+txtPrecio.getText()+"',nombre ='"+txtNombre.getText()+"',stock = '"+txtStock.getText()+"' WHERE cod_pro = '"+txtCodigo.getText()+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Actualizado");
            Cargar("");
            Bloqueo();
            Limpiar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnVolver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolver1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVolver1ActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        // TODO add your handling code here:
        try {
            int filaMod=TablaProducto.getSelectedRow();
            if(filaMod==-1){
                JOptionPane.showMessageDialog(null, "Seleccione alguna fila");
            }else{
                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtPrecio.setEnabled(true);
                txtStock.setEnabled(true);
                btnRegistrar.setEnabled(false);
                btnModificar.setEnabled(true);
                btnRegistrar.setEnabled(false);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
                String cod=(String)TablaProducto.getValueAt(filaMod, 0);
                BuscarProductoEditar(cod);
            }
        }catch (Exception e) {
        }
    }//GEN-LAST:event_ActualizarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        int filasel= TablaProducto.getSelectedRow();
        try {
            if(filasel==-1){
                JOptionPane.showMessageDialog(null, "Seleccione algun dato");
            }else{
                String  cod=(String)TablaProducto.getValueAt(filasel, 0);
                String eliminarSQL="DELETE FROM producto WHERE cod_pro = '"+cod+"'";
                try {
                    PreparedStatement pst  = cn.prepareStatement(eliminarSQL);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Producto Borrado");
                    Cargar("");
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }catch (Exception e){
        }
    }//GEN-LAST:event_EliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Actualizar;
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JPopupMenu MenuContext;
    private javax.swing.JTable TablaProducto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnVolver1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
    ConexionBD cc= new ConexionBD();
    Connection cn=cc.conexion();
}
