package Formularios;

import Conexion.ConexionBD;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RegistroFactura extends javax.swing.JInternalFrame {

    public RegistroFactura() {
        initComponents();
        this.setLocation(25,15 );
        txtNumeroFac.setEnabled(false);
        txtFecha.setEnabled(false);
        txtFecha.setDisabledTextColor(Color.blue);
        txtFecha.setText(FechaActual());
        NumeroFactura();
        CodigoCliente();
        CodigoEncargado();
    }
    
    void ActualizarStock(String producto_codigo,String producto_cantidad){
        int ActStock = Integer.parseInt(producto_cantidad);
        String cap="";
        int ActualizarStockFin;
        String consul="SELECT * FROM producto WHERE  cod_pro='"+producto_codigo+"'";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(consul);
            while(rs.next()){
                cap= rs.getString(4);
            }
        } catch (Exception e) {
        }
        ActualizarStockFin=Integer.parseInt(cap)-ActStock;
        String actualizar="UPDATE producto SET stock='"+ActualizarStockFin+"' WHERE cod_pro = '"+producto_codigo+"'";
        try {
            PreparedStatement pst = cn.prepareStatement(actualizar);
            pst.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    void NumeroFactura(){
        int j;
        int contador_fac=1;
        String numero_fac="";
        String c_fac="";
        String SQL="select max(cod_fac) from factura";
        // String SQL="select count(*) from factura";
        //String SQL="SELECT MAX(cod_emp) AS cod_emp FROM empleado";
        //String SQL="SELECT @@identity AS ID";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                c_fac=rs.getString(1);
            }
            if(c_fac==null){
                txtNumeroFac.setText("F000001");
            }else{
                char r1=c_fac.charAt(2);
                char r2=c_fac.charAt(3);
                char r3=c_fac.charAt(4);
                char r4=c_fac.charAt(5);
                char r5=c_fac.charAt(6);
                String r="";
                r=""+r1+r2+r3+r4+r5;
                j=Integer.parseInt(r);
                ValorNumeroFac gen= new ValorNumeroFac();
                gen.generar(j);
                txtNumeroFac.setText("F"+gen.serie_fac());
            }
        } catch (SQLException ex) {
           Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void CalcularPrecio(){
        String producto_precio;
        String producto_cantidad;
        double igv=0;
        double total=0;
        double subtotal=0;
        double precio;
        int cantidad;
        double imp=0.0;
        
            /*can=Integer.parseInt(cant);
            imp=pre*can;
            dato[4]=Float.toString(imp);*/
        
        for(int i=0;i<tbProducEnFac.getRowCount();i++){
            producto_precio=tbProducEnFac.getValueAt(i, 2).toString();
            producto_cantidad=tbProducEnFac.getValueAt(i, 3).toString();
            precio=Double.parseDouble(producto_precio);
            cantidad=Integer.parseInt(producto_cantidad);
            imp=precio*cantidad;
            total=total+imp;
            igv=total*0.18;
            subtotal=total-igv;
            // txtcod.setText(""+Math.rint(c*100)/100)
            tbProducEnFac.setValueAt(Math.rint(imp*100)/100, i, 4);
        }
        txtSubTotal.setText(Double.toString(subtotal));
        txtIgv.setText(""+Math.rint(igv*100)/100);
        txtTotal.setText(""+Math.rint(total*100)/100);   
    }
    
    void Facturar(){
        String InsertarSQL="INSERT INTO factura (cod_fac,cod_encar,cod_cliente,nombre_cli,total_fac,fecha_fac) VALUES (?,?,?,?,?,?)";
        String numero_factura=txtNumeroFac.getText();
        String codigo_cliente=txtCodigoCli.getText();
        String codigo_encargado=(String) boxCodEncar.getSelectedItem();
        String nombre_cliente=txtNombreCli.getText();
        String total=txtTotal.getText();
        String fecha=txtFecha.getText();
        try {
            PreparedStatement pst_fac = cn.prepareStatement(InsertarSQL);
            pst_fac.setString(1,numero_factura);
            pst_fac.setString(2,codigo_encargado);
            pst_fac.setString(3,codigo_cliente);
            pst_fac.setString(4,nombre_cliente);
            pst_fac.setString(5,total);
            pst_fac.setString(6,fecha);
            int n_fac= pst_fac.executeUpdate();
            if(n_fac>0){
                JOptionPane.showMessageDialog(null,"Los datos se guardaron correctamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void DetalleFacturar(){
        for(int i=0;i<tbProducEnFac.getRowCount();i++){
            String InsertarSQL="INSERT INTO det_factura(cod_fac,cod_pro,nombre,cantidad_pro,precio,precio_total) VALUES (?,?,?,?,?,?)";
            String cod_factura=txtNumeroFac.getText();
            String cod_producto=tbProducEnFac.getValueAt(i, 0).toString();
            String nom_producto=tbProducEnFac.getValueAt(i, 1).toString();
            String cant_producto=tbProducEnFac.getValueAt(i, 3).toString();
            String pre_unitario=tbProducEnFac.getValueAt(i, 2).toString();
            String pre_total=tbProducEnFac.getValueAt(i, 4).toString();
    
            try {
                PreparedStatement pst_det_fac = cn.prepareStatement(InsertarSQL);
                pst_det_fac.setString(1,cod_factura);
                pst_det_fac.setString(2,cod_producto);
                pst_det_fac.setString(3,nom_producto);
                pst_det_fac.setString(4,cant_producto);
                pst_det_fac.setString(5,pre_unitario);
                pst_det_fac.setString(6,pre_total);

                pst_det_fac.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void CodigoCliente(){
        int j;
        int contador_cod_cli=1;
        String numero_cod_cli="";
        String c_cod_cli="";
        String SQL="select max(cod_cliente)from cliente";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            if(rs.next()){              
                 c_cod_cli=rs.getString(1);
            }
            if(c_cod_cli==null){
                txtCodigoCli.setText("C001");
            }else{
                char r1=c_cod_cli.charAt(2);
                char r2=c_cod_cli.charAt(3);
                String r="";
                r=""+r1+r2;
                j=Integer.parseInt(r);
                ValorCodigos gen= new ValorCodigos();
                gen.generar(j);
                txtCodigoCli.setText("C"+gen.serie());
            }
        } catch (SQLException ex) {
           Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void CodigoEncargado(){
        String SQL="select cod_encar from encargado";
        try {
            Statement st = cn.createStatement();
            ResultSet rs=st.executeQuery(SQL);
            while(rs.next()){
                boxCodEncar.addItem(rs.getString("cod_encar"));
            }
        } catch (SQLException ex) {
           Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        boxCodEncar = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroFac = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtNombreCli = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDniCli = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtCodigoCli = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCelularCli = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnAgregarPro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProducEnFac = new javax.swing.JTable();
        btnCalcularPrecio = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtIgv = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO FACTURA");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoRStore.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        jLabel2.setText("R-STORE");

        jLabel6.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel6.setText("Direccion: Cal. Octavio Muñoz Najar Nro. 223 Int. 302");

        jLabel7.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel7.setText("Telefono: 983 582 942");

        jLabel8.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel8.setText("Correo: administracion@rstoreaqp.com");

        jLabel17.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel17.setText("Cod. Encargado:");

        boxCodEncar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxCodEncarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(113, 113, 113))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxCodEncar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(boxCodEncar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel3.setText("RUC: 20602246702");

        jLabel4.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel4.setText("FACTURA ELECTRONICA:");

        jLabel5.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel5.setText("N°");

        txtNumeroFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroFacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroFac))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNumeroFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel9.setText("Señor(a):");

        jLabel10.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel10.setText("DNI:");

        jLabel11.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel11.setText("Codigo Cliente:");

        txtCodigoCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoCliActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel12.setText("Celular:");

        jLabel16.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel16.setText("Fecha:");

        jLabel18.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel18.setText("Agregar producto:");

        btnAgregarPro.setText("Buscar");
        btnAgregarPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelularCli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDniCli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarPro)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNombreCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtDniCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtCelularCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodigoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(btnAgregarPro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbProducEnFac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Producto", "Nombre", "Cantidad", "Precio Unit.", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbProducEnFac);

        btnCalcularPrecio.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnCalcularPrecio.setText("Calcular Precio");
        btnCalcularPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPrecioActionPerformed(evt);
            }
        });

        btnFacturar.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });

        btnEliminarProducto.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnEliminarProducto.setText("Eliminar Producto");
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel13.setText("Sub Total:");

        txtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubTotalActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel14.setText("IGV:");

        jLabel15.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        jLabel15.setText("Total:");

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalcularPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFacturar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCalcularPrecio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFacturar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarProducto))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(jLabel13)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtIgv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(50, 50, 670, 447);
    }// </editor-fold>//GEN-END:initComponents

    public static String FechaActual(){
        Date fecha_act= new Date();
        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha_act);
    }
    
    private void txtNumeroFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroFacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroFacActionPerformed

    private void btnCalcularPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPrecioActionPerformed
        // TODO add your handling code here:
        if(tbProducEnFac.getRowCount()<1)
    {
        JOptionPane.showMessageDialog(this, "Error, no ingreso ningun producto");
    }
    else{
    CalcularPrecio();
    }
    }//GEN-LAST:event_btnCalcularPrecioActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        // TODO add your handling code here:
        if((txtCodigoCli.getText().equals("")) || (txtSubTotal.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "No ingreso cliente,productos o realice operacion");
        }else{
            String cap_codigo="",cap_cantidad="";
            for(int i=0;i<RegistroFactura.tbProducEnFac.getRowCount();i++){
                cap_codigo=RegistroFactura.tbProducEnFac.getValueAt(i, 0).toString();
                cap_cantidad=RegistroFactura.tbProducEnFac.getValueAt(i, 3).toString();
                ActualizarStock(cap_codigo, cap_cantidad);
            }
            
            Facturar();
            DetalleFacturar();
            
            String codigo_cliente,nombre_cliente,dni_cliente,celular_cliente;
            String sql="";
            codigo_cliente=txtCodigoCli.getText();
            nombre_cliente=txtNombreCli.getText();
            dni_cliente=txtDniCli.getText();
            celular_cliente=txtCelularCli.getText();
            
            sql="INSERT INTO cliente (cod_cliente,nombre_cli,dni_cli,celular_cli) VALUES (?,?,?,?)";
            try {
                PreparedStatement pst_cli  = cn.prepareStatement(sql);
                pst_cli.setString(1, codigo_cliente);
                pst_cli.setString(2, nombre_cliente);
                pst_cli.setString(3, dni_cliente);
                pst_cli.setString(4, celular_cliente);

                int n_pst_cli=pst_cli.executeUpdate();
                if(n_pst_cli>0){
                    Facturar();
                    DetalleFacturar();
                    JOptionPane.showMessageDialog(null, "Factura Realizada con Exito");
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegistroFactura.class.getName()).log(Level.SEVERE, null, ex);
            }

            txtCodigoCli.setText("");
            txtNombreCli.setText("");
            txtCelularCli.setText("");
            txtDniCli.setText("");

            txtIgv.setText("");
            txtSubTotal.setText("");
            txtTotal.setText("");

            DefaultTableModel modelo = (DefaultTableModel) tbProducEnFac.getModel();
            int a =tbProducEnFac.getRowCount()-1;
            int i;
            for(i=a;i>=0;i--){
                modelo.removeRow(i);
            }
            NumeroFactura();
            CodigoCliente();
        }
    }//GEN-LAST:event_btnFacturarActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        // TODO add your handling code here:
            DefaultTableModel modelo = (DefaultTableModel) tbProducEnFac.getModel();
        int fila = tbProducEnFac.getSelectedRow();
        if(fila>=0){
           modelo.removeRow(fila);
        }else{
            JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");
        }
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubTotalActionPerformed

    private void btnAgregarProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProActionPerformed
        // TODO add your handling code here:
        try {
            VerProducto producto_ver= new VerProducto();
            Principal.Panel.add(producto_ver);
            producto_ver.toFront();
            producto_ver.setVisible(true);

            } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAgregarProActionPerformed

    private void txtCodigoCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoCliActionPerformed
        // TODO add your handling code here:
        txtCodigoCli.transferFocus();
    }//GEN-LAST:event_txtCodigoCliActionPerformed

    private void boxCodEncarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxCodEncarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxCodEncarActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxCodEncar;
    private javax.swing.JButton btnAgregarPro;
    private javax.swing.JButton btnCalcularPrecio;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable tbProducEnFac;
    private javax.swing.JTextField txtCelularCli;
    private javax.swing.JTextField txtCodigoCli;
    private javax.swing.JTextField txtDniCli;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIgv;
    private javax.swing.JTextField txtNombreCli;
    private javax.swing.JTextField txtNumeroFac;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
    ConexionBD cc= new ConexionBD();
    Connection cn=cc.conexion();
}
