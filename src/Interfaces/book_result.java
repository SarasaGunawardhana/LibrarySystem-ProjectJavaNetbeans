/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import librarysystem.elc.book;
import DBCONNECT.db_connect1;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DateFormatter;
import librarysystem.elc.member;

public class book_result extends javax.swing.JFrame {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs,rs2 = null;
    book b1=new book();
    member m1=new member();
    int count=0;
            /*Calendar cal=new GregorianCalendar();
                int day=cal.get(Calendar.DATE);
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);*/
    
    Date date=new Date();
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal2=Calendar.getInstance();
    SimpleDateFormat sec=new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal3=Calendar.getInstance();
    SimpleDateFormat three=new SimpleDateFormat("yyyy-MM-dd");
    public book_result() {
        initComponents();

        con = DBCONNECT.db_connect1.connect();
        
        //JOptionPane.showConfirmDialog(null,h2.getname());
        jTextField1.setText(b1.getname());
        jTextField2.setText(b1.getauthor());
        jTextArea1.setText(b1.getdescription());
        //class_box.setText("Class : "+h2.getclass());
        
    }
    
    private void check_reserved()
    {
        String sql="SELECT br.bname FROM book b,borrower_record br WHERE b.id=br.id AND br.borrower_no='"+m1.get_id()+"' AND bname='"+b1.getname()+"' ";
        String sqll="SELECT r_person_no FROM reservation WHERE r_person_no='"+m1.get_id()+"' AND r_bookname='"+b1.getname()+"'";
        String sqlll="SELECT r_person_no FROM reservation WHERE r_bookname='"+b1.getname()+"'";
        try{               
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            int size=0;
            while (rs.next()) {
            size++;
            }
            if(size>0)
            {
                try{
                    ps = con.prepareStatement(sqll);
                    rs=ps.executeQuery();
            
                    int size1=0;
                    while (rs.next()) {
                    size1++; 
                    }
                    //String pno = rs.getString("r_person_no"); 
                    if(size1>0)
                    {
                       JOptionPane.showMessageDialog(null,"You already reserved this book !.");
                    }
                    else
                    {
                        try{
                            ps = con.prepareStatement(sqlll);
                            rs=ps.executeQuery();
            
                            int size11=0;
                            while (rs.next()) {
                            size11++;
                            if(size11>0)
                            {
                                JOptionPane.showMessageDialog(null,"someone already reserved this book !.");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"no one reserved this book !.");
                            }
                    }   
                        }
                        catch(HeadlessException e)
                        {
                            System.out.println("Error in searching. Error = "+e.getMessage());
                        }
                    }
                }
                catch(SQLException | HeadlessException e)
                {
                    System.out.println("Error in searching. Error = "+e.getMessage());
                }
                
                
              //String name = rs.getString("bname"); 
              
            }
            else
            {
                JOptionPane.showMessageDialog(null,"no one Ordered this book !");
            }
        }
        catch(SQLException | HeadlessException e)
        {
            System.out.println("Error in searching. Error = "+e.getMessage());
        }
    }
    private void order()
    {
        String sql="SELECT br.bname,b.id FROM book b,borrower_record br WHERE b.id=br.id AND br.borrower_no='"+m1.get_id()+"' AND bname='"+b1.getname()+"' ";
        String sqll="SELECT br.bname FROM book b,borrower_record br WHERE b.id=br.id AND bname='"+b1.getname()+"' ";
        //String sqlll="SELECT r_person_no FROM reservation WHERE r_bookname='"+b1.getname()+"'";
        String sql_l_l="SELECT m.fname,m.sname,m.email,m.telephone,m.dob FROM member m WHERE m.id = '"+m1.get_id()+"'";
         try{               
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            int size=0;
            while (rs.next()) {
            size++;
            }
            
            if(size>0)
            {
                JOptionPane.showMessageDialog(null,"You already ordered this book !");
            }
            else
            {
                    try{
                    ps = con.prepareStatement(sqll);
                    rs=ps.executeQuery();
            
                    int size1=0;
                    while (rs.next()) {
                    size1++; 
                    }
                    
                        if(size1>0)
                        {
                            JOptionPane.showMessageDialog(null,"someone already ordered this book !");
                        }
                        else
                        {
                                   String sql_e="SELECT r_person_no FROM reservation WHERE r_person_no='"+m1.get_id()+"' AND r_bookname='"+b1.getname()+"'";
                                   
                            try{
                                ps = con.prepareStatement(sql_e);
                                rs=ps.executeQuery();
            
                                int cnt=0;
                                while (rs.next()) {
                                cnt++; 
                                }
                            
                                                                    
                                if(cnt>0)
                                {
                                    String check_reservation_period="SELECT borrowdate,handoverdate FROM reservation WHERE r_person_no='"+m1.get_id()+"'";
                                    Date today = null;
                                   
                                    
                                    try{
                                        ps = con.prepareStatement(check_reservation_period);
                                        rs=ps.executeQuery();
            
                                        rs.next();
                                            Date date1=rs.getDate("borrowdate");
                                            Date date2=rs.getDate("handoverdate");
                                        
                                                
                                                
                                                String sdate=sec.format(cal2.getTime());
                                                DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
                                        try {
                                             today=(Date)formatter.parse(sdate);//convert string to date type
                                            //long difference=(date2.getTime()-date1.getTime())/(24*60*60*1000);
                                        } catch (ParseException ex) {
                                            Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                        
                                        if(date2.compareTo(today)>0||date2.compareTo(today)==0)//date 2 is after today(correct)
                                        {
                                            Date borrowDate=null;
                                            Date handoverDate=null;
                                            try{
                                                ps = con.prepareStatement(sql_l_l);
                                                rs = ps.executeQuery();
            
                                                rs.next();
                                                String bname=rs.getString("fname");
                                               
                                                String sdf1=sdf.format(cal.getTime());
                                                DateFormat formattersdf=new SimpleDateFormat("yyyy-MM-dd");
                                                try {
                                                    borrowDate=(Date)formattersdf.parse(sdf1);//convert string to date type
                                            
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                                String sql_l="INSERT INTO borrower_record(id,borrower_no,bname,bookname,borrowdate) VALUES('"+b1.getid()+"','"+m1.get_id()+"','"+b1.getname()+"','"+bname+"','"+sdf.format(cal.getTime())+"')";
                            
                                    
                                                try{                               
                                                    ps = con.prepareStatement(sql_l);
                                                    ps.execute();
                                    
                                                    cal.add(Calendar.DATE,14);//increament the date by 14 days
                                                    String sdf2=sdf.format(cal.getTime());
                                                    DateFormat formattersdf2=new SimpleDateFormat("yyyy-MM-dd");
                                                    
                                                    try {
                                                    handoverDate=(Date)formattersdf2.parse(sdf2);//convert string to date type
                                            
                                                    } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    
                                                    //String handover="INSERT INTO borrower_record(handoverdate) VALUES('"+handoverDate+"')";         
                                                    String handover="UPDATE borrower_record SET handoverdate ='"+sdf.format(cal.getTime())+"'WHERE id='"+b1.getid()+"' AND borrower_no='"+m1.get_id()+"'";
                                                    cal.add(Calendar.DATE,-14);//recorrect cal
                                                        try{
                                                            ps = con.prepareStatement(handover);
                                                            ps.execute();
                                                            JOptionPane.showMessageDialog(null,"You ordered the book");
                                                        }
                                                        catch(SQLException | HeadlessException e)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Error of inserting Data that!"+e.getMessage());
                                                        }           
                                                }
                                                catch(SQLException | HeadlessException e)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Error of inserting Data some!"+e.getMessage());
                                                }
                            
                            
                                            }
                                            catch(HeadlessException e)
                                            {
                                                JOptionPane.showMessageDialog(null," !"+e.getMessage());
                                            }
                                        }
                                        else if(date2.compareTo(today)<0)//date 2 is before today
                                        {
                                           JOptionPane.showMessageDialog(null," Date Expired!"); 
                                        }
                                        
                                    }
                                    catch(SQLException | HeadlessException e)
                                    {
                                        JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
                                    }
                                    
                                }
                            
                            
                                else{
                                        String sql_ee="SELECT r_person_no FROM reservation WHERE r_bookname='"+b1.getname()+"'"; 
                                        
                                        try
                                        {
                                            ps = con.prepareStatement(sql_ee);
                                            rs = ps.executeQuery();
                                            
                                            int cnt2=0;
                                            while (rs.next()) {
                                            cnt2++; 
                                            }
                                            
                                            if(cnt2>0)
                                            {
                                                JOptionPane.showMessageDialog(null,"Someone Already Reserved this book");
                                                //should check whether other person's reservation date expired or not
                                            }
                                            else
                                            {
                                                 Date borrowDate=null;
                                            Date handoverDate=null;
                                            try{
                                                ps = con.prepareStatement(sql_l_l);
                                                rs = ps.executeQuery();
            
                                                rs.next();
                                                String bname=rs.getString("fname");
                                               
                                                String sdf1=sdf.format(cal.getTime());
                                                DateFormat formattersdf=new SimpleDateFormat("yyyy-MM-dd");
                                                try {
                                                    borrowDate=(Date)formattersdf.parse(sdf1);//convert string to date type
                                            
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                
                                                String sql_l="INSERT INTO borrower_record(id,borrower_no,bname,bookname,borrowdate) VALUES('"+b1.getid()+"','"+m1.get_id()+"','"+b1.getname()+"','"+bname+"','"+sdf.format(cal.getTime())+"')";
                            
                                    
                                                try{                               
                                                    ps = con.prepareStatement(sql_l);
                                                    ps.execute();
                                    
                                                    cal.add(Calendar.DATE,14);//increament the date by 14 days
                                                    String sdf2=sdf.format(cal.getTime());
                                                    DateFormat formattersdf2=new SimpleDateFormat("yyyy-MM-dd");
                                                    
                                                    try {
                                                    handoverDate=(Date)formattersdf2.parse(sdf2);//convert string to date type
                                            
                                                    } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                    }
                                                    
                                                    //String handover="INSERT INTO borrower_record(handoverdate) VALUES('"+sdf.format(cal.getTime())+"') WHERE id='"+b1.getid()+"' AND borrower_no='"+m1.get_id()+"'";         
                                                    String handover="UPDATE borrower_record SET handoverdate ='"+sdf.format(cal.getTime())+"'WHERE id='"+b1.getid()+"' AND borrower_no='"+m1.get_id()+"'";
                                                    cal.add(Calendar.DATE,-14);//recorrect cal
                                                        try{
                                                            ps = con.prepareStatement(handover);
                                                            ps.execute();
                                                            JOptionPane.showMessageDialog(null,"You ordered the book");
                                                        }
                                                        catch(SQLException | HeadlessException e)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Error of inserting Data Second!"+e.getMessage());
                                                        }           
                                                }
                                                catch(SQLException | HeadlessException e)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Error of inserting Data first!"+e.getMessage());
                                                }
                            
                            
                                            }
                                            catch(HeadlessException e)
                                            {
                                                JOptionPane.showMessageDialog(null," !"+e.getMessage());
                                            }
                                            }//end
                                        }
                                        catch(SQLException | HeadlessException e)
                                        {
                                            JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
                                        }
                                        
                                        
                                }
                            }
                            catch(SQLException | HeadlessException e)
                            {
                                JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
                            }
                        }
                    }
                    catch(SQLException | HeadlessException e)
                    {
                        JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
                    } 
            }
         }
         catch(SQLException | HeadlessException e)
         {
            JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
         }
        
    }

   private void reserve()
   {
       String sql_e="SELECT r_person_no FROM reservation WHERE r_person_no='"+m1.get_id()+"' AND r_bookname='"+b1.getname()+"'";
       String sql_ee="SELECT r_person_no FROM reservation WHERE r_bookname='"+b1.getname()+"'"; 
       //String check_reservation_period="SELECT borrowdate,handoverdate FROM reservation WHERE r_person_no='"+m1.get_id()+"'";
       
       try{
           ps = con.prepareStatement(sql_e);
           ps.execute();
           
           int cnt=0;
           while (rs.next()) {
           cnt++; 
           }
           if(cnt>0)
           {
               JOptionPane.showMessageDialog(null,"you already reserved this book");
           }
           else
           {
               try{
                   ps = con.prepareStatement(sql_ee);
                   ps.execute();
                   
                   int cnt1=0;
                   while (rs.next()) {
                   cnt1++; 
                   }
                    if(cnt1>0)
                    {
                        JOptionPane.showMessageDialog(null,"someone already reserved this book");
                    }
                    else
                    {
                        Date rDate=null;
                        Date rendDate=null;
                        String one=three.format(cal3.getTime());
                                                DateFormat formatterone=new SimpleDateFormat("yyyy-MM-dd");
                                                try {
                                                    rDate=(Date)formatterone.parse(one);//convert string to date type
                                            
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                                cal3.add(Calendar.DATE,14);
                        String two=three.format(cal3.getTime());
                                                DateFormat formattertwo=new SimpleDateFormat("yyyy-MM-dd");
                                                try {
                                                    rendDate=(Date)formattertwo.parse(two);//convert string to date type
                                            
                                                } catch (ParseException ex) {
                                                    Logger.getLogger(book_result.class.getName()).log(Level.SEVERE, null, ex);
                                                }                        
                        
                        String reservation="INSERT INTO reservation(id,r_person_no,r_bookname,rdate,renddate) VALUES('"+b1.getid()+"','"+m1.get_id()+"','"+b1.getname()+"','"+one+"','"+two+"')";
                    
                        try{
                            ps = con.prepareStatement(reservation);
                            ps.execute();
                   
                           
                            
                        }
                        catch(SQLException | HeadlessException e)
                        {
                            JOptionPane.showMessageDialog(null,"Error of inserting Data"+e.getMessage());
                        }
                    
                    }
                   
                   
               }
               catch(SQLException | HeadlessException e)
               {
                   JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
               }
           }
       }
       catch(SQLException | HeadlessException e)
       {
           JOptionPane.showMessageDialog(null,"Error of searching Data"+e.getMessage());
       }  
   
   }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setUndecorated(true);
        getContentPane().setLayout(null);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(270, 80, 210, 30);
        getContentPane().add(jTextField2);
        jTextField2.setBounds(270, 140, 210, 30);

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 10, 30, 50);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3);
        jLabel3.setBounds(750, 20, 30, 30);

        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4);
        jLabel4.setBounds(720, 420, 70, 20);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(270, 200, 210, 120);

        jButton1.setText("CHECK RESERVATION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(20, 400, 150, 40);

        jButton2.setText("RESERVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(510, 230, 90, 40);

        jButton3.setText("ORDER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(510, 280, 90, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/book_result.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        this.check_reserved();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        book_view bv=new book_view();
        bv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        index in2=new index();
        in2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.order();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        this.reserve();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(book_result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(book_result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(book_result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(book_result.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new book_result().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
