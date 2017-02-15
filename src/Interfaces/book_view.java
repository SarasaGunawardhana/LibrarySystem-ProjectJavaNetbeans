/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import librarysystem.elc.search_it;
import librarysystem.elc.book;
import DBCONNECT.db_connect1;

public class book_view extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs,rs2 = null;
    int count=0;
    book h1 = new book();
    private void setdata(){
        int r = result_table.getSelectedRow();
        
        String id = result_table.getValueAt(r,0).toString();
        String name = result_table.getValueAt(r,1).toString();
        String author = result_table.getValueAt(r,2).toString();
        String description = result_table.getValueAt(r,3).toString();
        
        try{
        
        h1.setid(Integer.parseInt(id));
        h1.setname(name);
        h1.setauthor(author);
        h1.setdescription(description);
        //JOptionPane.showMessageDialog(null,"hi");
        }
        catch(Exception e){
            System.out.println("Error in setting data to book object. Error = "+e.getMessage());
        }
    }
    
    
    private void view_data(){
        search_it st1 = new search_it();
        
        String sql= "";
        
        if((st1.getBook().length() == 0)&&(st1.getAuthor().length() == 0)){
            sql = "SELECT id AS ID,name AS Name,author AS Author,description as Description FROM book WHERE status = 0";
        }
        else {
            //sql = "SELECT id AS ID,name AS Name,description as Description FROM course WHERE  name = '"+st1.getcode()+"'";
             sql = "SELECT id AS ID,name AS Name,author AS Author,description as Description,status AS Status FROM book WHERE (name LIKE '%"+st1.getBook()+"%' OR name LIKE '%"+st1.getAuthor()+"%')";
        }

        
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            //this will count the number of results
            //rs.last();
            //count = rs.getRow();
            //rs.beforeFirst();//set current row into begining

            //this.count_box.setText(""+count);
            result_table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            System.out.println("Error to Search data. Error = "+e.getMessage());
        }
    }
    
    public book_view() {
        initComponents();
        
        con = db_connect1.connect();
        
        view_data();
        
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        result_table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setUndecorated(true);
        getContentPane().setLayout(null);

        result_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        result_table.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        result_table.setMaximumSize(new java.awt.Dimension(650, 64));
        result_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                result_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(result_table);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 80, 590, 330);

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 20, 30, 30);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(730, 420, 50, 20);

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(750, 20, 0, 30);

        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel5);
        jLabel5.setBounds(750, 20, 0, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/course_view.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
           int x = JOptionPane.showConfirmDialog(null,"Do you want to signout ?");
            if(x == 0){
            index logout= new index();
            logout.setVisible(true);
            this.dispose();
            }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        book_search back=new book_search();
        back.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void result_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_result_tableMouseClicked
        
        setdata();
        book_result hr1 = new book_result();
        hr1.setVisible(true);
        this.dispose();
        //System.exit(0);
    }//GEN-LAST:event_result_tableMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(book_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(book_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(book_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(book_view.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new book_view().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable result_table;
    // End of variables declaration//GEN-END:variables
}
