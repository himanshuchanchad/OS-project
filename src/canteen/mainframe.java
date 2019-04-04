/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Himanshu
 */
public class mainframe extends javax.swing.JFrame {
     int current_user=0;
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    /**
     * Creates new form mainframe
     */
    public mainframe() {
        initComponents();
        login_page.setVisible(true);
        signup.setVisible(false);
        userpage.setVisible(false);
        admin_panel.setVisible(false);
         conn=dbc.connection();
         fetch();
        
    }
public void fetch()
{
    try
    {
     stmt=conn.createStatement();
     String sql="SELECT * FROM ORDERS";
     rs=stmt.executeQuery(sql);
     table_detail.setModel(DbUtils.resultSetToTableModel(rs));
     
    }
    catch(SQLException e)
    {
        JOptionPane.showMessageDialog(null,e);
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

        login_page = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        username_entry = new javax.swing.JTextField();
        password_entry = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        go_signup = new javax.swing.JButton();
        signup = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        username_add = new javax.swing.JTextField();
        password_add = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        userpage = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        admin_panel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_detail = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(5, 46, 112));
        setLocation(new java.awt.Point(500, 100));
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        login_page.setBackground(new java.awt.Color(5, 46, 112));
        login_page.setForeground(java.awt.Color.white);
        login_page.setToolTipText("");
        login_page.setPreferredSize(new java.awt.Dimension(920, 520));

        password.setForeground(new java.awt.Color(255, 255, 255));
        password.setText("PASSWORD :");

        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("USERNAME :");

        username_entry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_entryActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 255, 0));
        jButton1.setText("Sign In");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        go_signup.setBackground(new java.awt.Color(255, 0, 51));
        go_signup.setText("Sign up");
        go_signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go_signupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout login_pageLayout = new javax.swing.GroupLayout(login_page);
        login_page.setLayout(login_pageLayout);
        login_pageLayout.setHorizontalGroup(
            login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_pageLayout.createSequentialGroup()
                .addGap(0, 224, Short.MAX_VALUE)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(login_pageLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(go_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(login_pageLayout.createSequentialGroup()
                        .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(85, 85, 85)
                        .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username_entry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password_entry, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(391, 391, 391))
        );
        login_pageLayout.setVerticalGroup(
            login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_pageLayout.createSequentialGroup()
                .addContainerGap(222, Short.MAX_VALUE)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(username_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(go_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(233, 233, 233))
        );

        getContentPane().add(login_page, "card2");

        signup.setBackground(new java.awt.Color(122, 126, 200));
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setPreferredSize(new java.awt.Dimension(920, 520));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("NAME :");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PASSWORD :");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EMAIL :");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("USERNAME :");

        password_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password_addActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 255, 0));
        jButton3.setText("signup");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 51));
        jButton4.setText("Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signupLayout = new javax.swing.GroupLayout(signup);
        signup.setLayout(signupLayout);
        signupLayout.setHorizontalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(signupLayout.createSequentialGroup()
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(name)
                                    .addComponent(username_add, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(password_add)))))
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(404, Short.MAX_VALUE))
        );
        signupLayout.setVerticalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username_add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(password_add))
                .addGap(59, 59, 59)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        getContentPane().add(signup, "card3");

        userpage.setBackground(new java.awt.Color(0, 102, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 255, 102));
        jLabel5.setText("choice :");

        jComboBox1.setBackground(new java.awt.Color(102, 255, 102));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cake", "icecream", "dosa", "pizza", "rice" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 204, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 0));
        jButton2.setText("order now");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 0));
        jButton5.setText("Logout");

        jList1.setBackground(new java.awt.Color(0, 204, 204));
        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "cake", "bhel", "rice", "dosa", "lunch", "coffee" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout userpageLayout = new javax.swing.GroupLayout(userpage);
        userpage.setLayout(userpageLayout);
        userpageLayout.setHorizontalGroup(
            userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userpageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(userpageLayout.createSequentialGroup()
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE))
        );
        userpageLayout.setVerticalGroup(
            userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(85, 85, 85)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(378, Short.MAX_VALUE))
        );

        getContentPane().add(userpage, "card4");

        admin_panel.setBackground(new java.awt.Color(0, 153, 255));

        table_detail.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(table_detail);

        javax.swing.GroupLayout admin_panelLayout = new javax.swing.GroupLayout(admin_panel);
        admin_panel.setLayout(admin_panelLayout);
        admin_panelLayout.setHorizontalGroup(
            admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 297, Short.MAX_VALUE))
        );
        admin_panelLayout.setVerticalGroup(
            admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE))
        );

        getContentPane().add(admin_panel, "card5");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
       {
          stmt=conn.createStatement();
          String username=username_entry.getText();
          String password=password_entry.getText();
          if(username.equals("")||password.equals(""))
          {
           JOptionPane.showMessageDialog(null,"Enter the username or password ");   
          }
          else
          {
          String sql="SELECT *FROM userdata WHERE username='"+username+"' && password='"+password+"'";
          rs=stmt.executeQuery(sql);
          if(rs.next())
          {
              current_user=rs.getInt("id");
              System.out.println(current_user);
              login_page.setVisible(false);
              signup.setVisible(false);
              userpage.setVisible(true);
//              admin_panel.setVisible(true);
          }
          else
          {
              JOptionPane.showMessageDialog(null,"invalid password");
          }
          }
       }catch(HeadlessException | SQLException e)
       {
           
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void password_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password_addActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.out.println("clicked");
        try
       {
          stmt=conn.createStatement();
          String name1=name.getText();
          String email1=email.getText();
          String username1=username_add.getText();
          String password1=password_add.getText();
          if(name1.equals("")||email1.equals("")||username1.equals("")||password1.equals(""))
          {
              JOptionPane.showMessageDialog(null,"Enter all data");  
          }else
          {
          System.out.println("data parsed");
          String sql="INSERT INTO userdata(name,email,username,password) VALUES('"+name1+"','"+email1+"','"+username1+"','"+password1+"')";
          //String sql="SELECT *FROM userdata WHERE username='"+username+"' && password='"+password+"'";
          stmt.executeUpdate(sql);
          System.out.println("data inserted");
          signup.setVisible(false);
          login_page.setVisible(true);
           }
       }catch(HeadlessException | SQLException e)
       {
         JOptionPane.showMessageDialog(null,"Data insertion failed");  
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void go_signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go_signupActionPerformed
        login_page.setVisible(false);
        signup.setVisible(true);        
    }//GEN-LAST:event_go_signupActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void username_entryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_entryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_entryActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         login_page.setVisible(true);
        signup.setVisible(false);
        userpage.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      try
       {
          stmt=conn.createStatement();
         
       }catch(HeadlessException | SQLException e)
       {
         JOptionPane.showMessageDialog(null,"Data insertion failed");  
       }  
        
        
        
        
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
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel admin_panel;
    private javax.swing.JTextField email;
    private javax.swing.JButton go_signup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel login_page;
    private javax.swing.JTextField name;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField password_add;
    private javax.swing.JPasswordField password_entry;
    private javax.swing.JPanel signup;
    private javax.swing.JTable table_detail;
    private javax.swing.JLabel username;
    private javax.swing.JTextField username_add;
    private javax.swing.JTextField username_entry;
    private javax.swing.JPanel userpage;
    // End of variables declaration//GEN-END:variables
}