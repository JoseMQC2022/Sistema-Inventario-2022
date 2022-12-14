
package Formularios;

import Conexion.ConexionBD;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteTB extends javax.swing.JInternalFrame {

    public ReporteTB() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnReportFac = new javax.swing.JButton();
        btnReportStock = new javax.swing.JButton();
        btnReportCli = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REPORTES");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnReportFac.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnReportFac.setText("Facturas");
        btnReportFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportFacActionPerformed(evt);
            }
        });

        btnReportStock.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnReportStock.setText("Stock");
        btnReportStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportStockActionPerformed(evt);
            }
        });

        btnReportCli.setFont(new java.awt.Font("Impact", 0, 12)); // NOI18N
        btnReportCli.setText("Clientes");
        btnReportCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportCliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReportFac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReportCli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnReportFac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportStock)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReportCli)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LogoRStore.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())))
        );

        setBounds(50, 50, 348, 206);
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportFacActionPerformed
        // TODO add your handling code here:
        try {
            ConexionBD con= new ConexionBD();
            Connection conn = con.conexion();
            
            JasperReport reporte = null;
            String path = "src\\Reporte\\ReportFacturas.jasper";
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnReportFacActionPerformed

    private void btnReportStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportStockActionPerformed
        // TODO add your handling code here:
        try {
            ConexionBD con= new ConexionBD();
            Connection conn = con.conexion();
            
            JasperReport reporte = null;
            String path = "src\\Reporte\\ReportProductos.jasper";
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnReportStockActionPerformed

    private void btnReportCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportCliActionPerformed
        // TODO add your handling code here:
        try {
            ConexionBD con= new ConexionBD();
            Connection conn = con.conexion();
            
            JasperReport reporte = null;
            String path = "src\\Reporte\\ReportClientes.jasper";
            
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, conn);
            
            JasperViewer view = new JasperViewer(jprint, false);
            
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            view.setVisible(true);
            
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnReportCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReportCli;
    private javax.swing.JButton btnReportFac;
    private javax.swing.JButton btnReportStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
