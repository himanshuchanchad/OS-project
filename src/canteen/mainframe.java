/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteen;
import java.util.ArrayList;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.*;
/**
 *
 * @author Himanshu
 */
public class mainframe extends javax.swing.JFrame {
    int priority=0;
     int current_user=0;
    Connection conn=null;
    Statement stmt=null;
    ResultSet rs=null;
    int value=0;
    int lunch=20,chinese=30,dosa=5,uthappa=4,coffee=3,tea=3;
    int lunch_value=0,chinese_value=0,dosa_value=0,uthappa_value=0,coffee_value=0,tea_value=0;
     static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
   
   
    public mainframe() {
        initComponents();
        login_page.setVisible(true);
        admin_login.setVisible(false);
        signup.setVisible(false);
        userpage.setVisible(false);
        admin_panel.setVisible(false);
         conn=dbc.connection();
       fetch();
        
    }
public ArrayList<order_detail> ordetail(){
    ArrayList<order_detail> ordetail =new ArrayList<>();
    try
       {
          stmt=conn.createStatement();
          System.out.println("rs");
          String sql="SELECT * FROM orders WHERE ordercompleted !=1  ORDER BY cooking desc, ordercompleted asc,priority desc ,ordervalue asc, orderdate asc";
          rs=stmt.executeQuery(sql);
          System.out.println("rs");
          order_detail orderd;
          while(rs.next())
          {
              System.out.println(rs.getInt("unique_id"));
              orderd=new order_detail(rs.getInt("unique_id"),rs.getString("detail_order"),rs.getDate("orderdate").toString(),rs.getInt("ordervalue"),rs.getInt("ordercompleted"),rs.getInt("priority"),rs.getInt("cooking"));
              ordetail.add(orderd);
          }
          
       }catch(HeadlessException | SQLException e)
       {
          System.out.println("ddfdfww"+e);  
       }
    return ordetail;
}
public boolean   availables(String s,int val){
    switch(s)
    {
        case "lunch":
        if(lunch_value>=val)
        {
            return true;      
        }
        break;
        case "chinese":
        if(chinese_value>=val)
        {
            return true;
        }
        break;
        case "dosa":
        if(dosa_value>=val)
        {
            return true;
        }
        break;
        case "uthappa":
        if(uthappa_value>=val)
        {
            
            return true;
        }
        break;
        case "coffee":
        if(coffee_value>=val)
        {
            return true;
        }   
        break;
        case "tea":
        if(tea_value>=val)
        {                       return true;

        }
        break;
        default :
                     return false;

    }
    return false;
}
public int  decrement(String s,int val){
    switch(s)
    {
        case "lunch":
        if(lunch_value>=val)
        {
            lunch_value=lunch_value-val;
            lunch_field.setText(String.valueOf(lunch_value));
            return 1;
            
        }
        break;
        case "chinese":
        if(chinese_value>=val)
        {
            chinese_value=chinese_value-val;
            chinese_field.setText(String.valueOf(chinese_value));
            return 1;
        }
        break;
        case "dosa":
        if(dosa_value>=val)
        {
            dosa_value=dosa_value-val;
            dosa_field.setText(String.valueOf(dosa_value));
            return 1;
        }
        break;
        case "uthappa":
        if(uthappa_value>=val)
        {
            uthappa_value=uthappa_value-val;
            uthappa_field.setText(String.valueOf(uthappa_value));
            return 1;
        }
        break;
        case "coffee":
        if(coffee_value>=val)
        {
            coffee_value=coffee_value-val;
            coffee_field.setText(String.valueOf(coffee_value));
            return 1;
        }
        break;
        case "tea":
        if(tea_value>=val)
        {
            tea_value=coffee_value-val;
            tea_field.setText(String.valueOf(tea_value));
            return 1;
        }
        break;
        default :
           return 0;
    }
    return 0;
}
    /**
     *
     */
public void fetch()
{
   ArrayList<order_detail> list=ordetail();
   DefaultTableModel model=(DefaultTableModel)admintable.getModel();
   Object[] row =new Object[7];
   for(int i=0;i<list.size();i++)
   {
       row[0]=list.get(i).getuid();
       row[1]=list.get(i).getorder_detail();
       row[2]=list.get(i).getdate();
       row[3]=list.get(i).getordervalue();
       row[4]=list.get(i).getorder_status();
       row[5]=list.get(i).getpriority();
       row[6]=list.get(i).getcooking();
       model.addRow(row);
       
   }
   
}
public int value(String s)
{
    int val;
    switch(s)
    {
        case "lunch":
        val=lunch;
        break;
        case "chinese":
        val=chinese;
        break;
        case "dosa":
        val=dosa;
        break;
        case "uthappa":
        val=uthappa;
        break;
        case "coffee":
        val=coffee;
        break;
        case "tea":
        val=tea;
        break;
        default :
           val=0;
         break;
        
    }
    return val;
}
public void refresh()
{
    int order_status=0;
    int cooking=0;
    try
    {
    stmt=conn.createStatement();
    String sql="SELECT * FROM orders WHERE id='"+current_user+"' ORDER BY unique_id DESC LIMIT 1";
    rs=stmt.executeQuery(sql);
    if(rs.next())
    {
      order_status=rs.getInt("ordercompleted");
      cooking=rs.getInt("cooking");
      if(order_status==0 && cooking==0 )
      {
          labelorderstatus.setText("YOUR ORDERS IS IN WAITING STATE");
      }
      if(order_status==1)
      {
        labelorderstatus.setText("YOUR ORDERS is being prepared collect your order");  
      }
      else if(cooking==1)
      {
          labelorderstatus.setText("Your order is being prepared ! ");
      }
      
    }
    }
    catch(HeadlessException | SQLException e)
    {
        System.out.println(e); 
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

        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        login_page = new javax.swing.JPanel();
        password = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        username_entry = new javax.swing.JTextField();
        password_entry = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        go_signup = new javax.swing.JButton();
        admin_page_redirector = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
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
        category = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        userpage = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        order_button = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        select1 = new javax.swing.JComboBox<>();
        order1 = new javax.swing.JTextField();
        select2 = new javax.swing.JComboBox<>();
        select3 = new javax.swing.JComboBox<>();
        order3 = new javax.swing.JTextField();
        order2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelorderstatus = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        admin_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        admintable = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        uniqueid = new javax.swing.JTextField();
        Cooked = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Cooked1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        availability = new javax.swing.JButton();
        admin_login = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        adminusername = new javax.swing.JTextField();
        adminpassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        admin_button = new javax.swing.JButton();
        admin_button1 = new javax.swing.JButton();
        available = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lunch_field = new javax.swing.JTextField();
        chinese_field = new javax.swing.JTextField();
        dosa_field = new javax.swing.JTextField();
        uthappa_field = new javax.swing.JTextField();
        tea_field = new javax.swing.JTextField();
        coffee_field = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        jTextField1.setText("jTextField1");

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

        admin_page_redirector.setBackground(new java.awt.Color(255, 0, 51));
        admin_page_redirector.setText("Admin");
        admin_page_redirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_page_redirectorActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\Himanshu\\Documents\\NetBeansProjects\\OS project\\canteen.jpg")); // NOI18N

        javax.swing.GroupLayout login_pageLayout = new javax.swing.GroupLayout(login_page);
        login_page.setLayout(login_pageLayout);
        login_pageLayout.setHorizontalGroup(
            login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_pageLayout.createSequentialGroup()
                .addContainerGap(243, Short.MAX_VALUE)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(login_pageLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(go_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(login_pageLayout.createSequentialGroup()
                        .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(username_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(335, 335, 335))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, login_pageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(admin_page_redirector, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        login_pageLayout.setVerticalGroup(
            login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(login_pageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo)
                    .addComponent(admin_page_redirector, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(login_pageLayout.createSequentialGroup()
                        .addComponent(password_entry, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addGroup(login_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(go_signup, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "student", "professor" }));

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Category");

        javax.swing.GroupLayout signupLayout = new javax.swing.GroupLayout(signup);
        signup.setLayout(signupLayout);
        signupLayout.setHorizontalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username_add, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password_add, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(416, Short.MAX_VALUE))
        );
        signupLayout.setVerticalGroup(
            signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signupLayout.createSequentialGroup()
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signupLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username_add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password_add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
        );

        getContentPane().add(signup, "card3");

        userpage.setBackground(new java.awt.Color(0, 102, 102));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 255, 102));
        jLabel5.setText("choice ");

        order_button.setBackground(new java.awt.Color(102, 204, 0));
        order_button.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        order_button.setForeground(new java.awt.Color(51, 51, 0));
        order_button.setText("order now");
        order_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_buttonActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 0));
        jButton5.setText("Logout");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        select1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "lunch", "chinese", "dosa", "uthappa", "coffee", "tea" }));

        order1.setText("0");

        select2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "tea", "lunch", "chinese", "dosa", "uthappa", "coffee" }));

        select3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "lunch", "chinese", "dosa", "uthappa", "coffee", "tea" }));

        order3.setText("0");

        order2.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 255, 102));
        jLabel6.setText("Qty");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ORDER STATUS : ");

        labelorderstatus.setBackground(new java.awt.Color(255, 255, 255));
        labelorderstatus.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelorderstatus.setForeground(new java.awt.Color(255, 255, 255));

        jButton6.setBackground(new java.awt.Color(204, 0, 0));
        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("refresh");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userpageLayout = new javax.swing.GroupLayout(userpage);
        userpage.setLayout(userpageLayout);
        userpageLayout.setHorizontalGroup(
            userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpageLayout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(select1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(select2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 302, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(order2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(order1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(order3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(userpageLayout.createSequentialGroup()
                .addGap(351, 351, 351)
                .addComponent(order_button, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userpageLayout.createSequentialGroup()
                .addContainerGap(316, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(279, 279, 279))
            .addGroup(userpageLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userpageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelorderstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        userpageLayout.setVerticalGroup(
            userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userpageLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(41, 41, 41)
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(select1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(order1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(select2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(order2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(select3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(order3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(order_button, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(userpageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(labelorderstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        getContentPane().add(userpage, "card4");

        admin_panel.setBackground(new java.awt.Color(0, 153, 255));

        admintable.setBackground(new java.awt.Color(255, 255, 153));
        admintable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "unique id", "order list", "datetime", "order value", " order  status ", "priority", "cooking"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(admintable);
        if (admintable.getColumnModel().getColumnCount() > 0) {
            admintable.getColumnModel().getColumn(0).setResizable(false);
            admintable.getColumnModel().getColumn(0).setPreferredWidth(2);
            admintable.getColumnModel().getColumn(1).setResizable(false);
            admintable.getColumnModel().getColumn(1).setPreferredWidth(75);
            admintable.getColumnModel().getColumn(2).setResizable(false);
            admintable.getColumnModel().getColumn(2).setPreferredWidth(10);
            admintable.getColumnModel().getColumn(3).setResizable(false);
            admintable.getColumnModel().getColumn(3).setPreferredWidth(10);
            admintable.getColumnModel().getColumn(4).setResizable(false);
            admintable.getColumnModel().getColumn(4).setPreferredWidth(5);
            admintable.getColumnModel().getColumn(5).setResizable(false);
            admintable.getColumnModel().getColumn(5).setPreferredWidth(5);
            admintable.getColumnModel().getColumn(6).setResizable(false);
            admintable.getColumnModel().getColumn(6).setPreferredWidth(2);
        }

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Unique id");

        uniqueid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        Cooked.setBackground(new java.awt.Color(255, 0, 51));
        Cooked.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cooked.setForeground(new java.awt.Color(255, 255, 255));
        Cooked.setText("Prepared");
        Cooked.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CookedActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 102, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Cooked1.setBackground(new java.awt.Color(255, 0, 51));
        Cooked1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        Cooked1.setForeground(new java.awt.Color(255, 255, 255));
        Cooked1.setText("Cooking");
        Cooked1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cooked1ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 102, 102));
        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("LOGOUT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        availability.setBackground(new java.awt.Color(255, 0, 51));
        availability.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        availability.setForeground(new java.awt.Color(255, 255, 255));
        availability.setText("availability");
        availability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availabilityActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout admin_panelLayout = new javax.swing.GroupLayout(admin_panel);
        admin_panel.setLayout(admin_panelLayout);
        admin_panelLayout.setHorizontalGroup(
            admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, admin_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(admin_panelLayout.createSequentialGroup()
                        .addGroup(admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(admin_panelLayout.createSequentialGroup()
                                .addGroup(admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(admin_panelLayout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(admin_panelLayout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Cooked1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(uniqueid, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Cooked, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 64, Short.MAX_VALUE))
                            .addGroup(admin_panelLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(availability, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        admin_panelLayout.setVerticalGroup(
            admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
            .addGroup(admin_panelLayout.createSequentialGroup()
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(admin_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availability, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(uniqueid, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(Cooked1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(Cooked, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(admin_panel, "card5");

        admin_login.setBackground(new java.awt.Color(102, 153, 255));
        admin_login.setForeground(new java.awt.Color(255, 51, 0));
        admin_login.setPreferredSize(new java.awt.Dimension(982, 848));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("username :");

        adminusername.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        adminusername.setMinimumSize(new java.awt.Dimension(10, 10));

        adminpassword.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("password :");

        admin_button.setBackground(new java.awt.Color(255, 0, 0));
        admin_button.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        admin_button.setText("Login");
        admin_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_buttonActionPerformed(evt);
            }
        });

        admin_button1.setBackground(new java.awt.Color(255, 0, 0));
        admin_button1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        admin_button1.setText("Back");
        admin_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout admin_loginLayout = new javax.swing.GroupLayout(admin_login);
        admin_login.setLayout(admin_loginLayout);
        admin_loginLayout.setHorizontalGroup(
            admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_loginLayout.createSequentialGroup()
                .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(admin_loginLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(35, 35, 35)
                        .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(adminusername, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(admin_loginLayout.createSequentialGroup()
                        .addGap(302, 302, 302)
                        .addComponent(admin_button, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(admin_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        admin_loginLayout.setVerticalGroup(
            admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(admin_loginLayout.createSequentialGroup()
                .addGap(187, 187, 187)
                .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminusername, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74)
                .addGroup(admin_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(admin_button, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(admin_button1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(351, Short.MAX_VALUE))
        );

        getContentPane().add(admin_login, "card6");

        available.setBackground(new java.awt.Color(51, 51, 255));

        jLabel12.setBackground(new java.awt.Color(0, 0, 102));
        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("lunch");

        jLabel13.setBackground(new java.awt.Color(0, 0, 102));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("dosa");

        jLabel14.setBackground(new java.awt.Color(0, 0, 102));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("chinese");

        jLabel15.setBackground(new java.awt.Color(0, 0, 102));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("uthappa");

        jLabel16.setBackground(new java.awt.Color(0, 0, 102));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("tea");

        jLabel17.setBackground(new java.awt.Color(51, 255, 255));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("coffee");

        dosa_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosa_fieldActionPerformed(evt);
            }
        });

        coffee_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coffee_fieldActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 51, 102));
        jButton8.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("SAVE");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout availableLayout = new javax.swing.GroupLayout(available);
        available.setLayout(availableLayout);
        availableLayout.setHorizontalGroup(
            availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableLayout.createSequentialGroup()
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availableLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(availableLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(151, 151, 151)
                                .addComponent(lunch_field, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(availableLayout.createSequentialGroup()
                                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chinese_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dosa_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(uthappa_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(coffee_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tea_field, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(availableLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(433, Short.MAX_VALUE))
        );
        availableLayout.setVerticalGroup(
            availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(availableLayout.createSequentialGroup()
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(availableLayout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(lunch_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, availableLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chinese_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dosa_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(uthappa_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tea_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(availableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(coffee_field, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(available, "card7");

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
          String category1=category.getSelectedItem().toString();
          if(category1.equals("professor"))
          {
              priority=1;
          }
          if(name1.equals("")||email1.equals("")||username1.equals("")||password1.equals(""))
          {
              JOptionPane.showMessageDialog(null,"Enter all data");  
          }else
          {
          System.out.println("data parsed");
          String sql="INSERT INTO userdata(name,email,username,password,priority) VALUES('"+name1+"','"+email1+"','"+username1+"','"+password1+"','"+priority+"')";
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

    private void username_entryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_entryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_entryActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
         login_page.setVisible(true);
        signup.setVisible(false);
        userpage.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void order_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_buttonActionPerformed
try
    {
    stmt=conn.createStatement();
    String sql="SELECT * FROM userdata WHERE id='"+current_user+"'";
    rs=stmt.executeQuery(sql);
    if(rs.next())
    {
     priority=Integer.parseInt(rs.getString("priority"));
        System.out.println("priority :"+priority);
    }
    }
    catch(HeadlessException | SQLException e)
    {
        System.out.println(e); 
    }      
        System.out.println("clicked ");
      int cooking_status=0;
      String select1s=select1.getSelectedItem().toString();
      String select2s=select2.getSelectedItem().toString();
      String select3s=select3.getSelectedItem().toString();
      int orderqty1=Integer.parseInt(order1.getText());
      int orderqty2=Integer.parseInt(order2.getText());
      int orderqty3=Integer.parseInt(order3.getText());
      if(availables(select1s,orderqty1))
      {
            if ((availables(select2s,orderqty2)))
                    {
                        if(availables(select3s,orderqty3)) {
                cooking_status=1;
                decrement(select1s,orderqty1);
                decrement(select2s,orderqty2);
                decrement(select3s,orderqty3);
                                
                                
            }
      }
      }
      String s=select1s+":"+orderqty1+select2s+":"+orderqty2+select3s+":"+orderqty3;
      int totaltime=0;
      totaltime+=value(select1s)*orderqty1;
      totaltime+=value(select2s)*orderqty2;
      totaltime+=value(select3s)*orderqty3;
      System.out.println("sql query  ");
      try
       {
          LocalDateTime now = LocalDateTime.now();
          stmt=conn.createStatement();
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
          System.out.println(date);
          int temp=0;
         String sql="INSERT INTO orders(id,orderdate,ordervalue,ordercompleted,priority,cooking,detail_order) VALUES('"+current_user+"','"+date+"','"+totaltime+"','"+cooking_status+"','"+priority+"','"+temp+"','"+s+"')";
         stmt.executeUpdate(sql);
         JOptionPane.showMessageDialog(null,"Ordered successful");
       }catch(HeadlessException | SQLException e)
       {
         JOptionPane.showMessageDialog(null,"Data insertion failed");  
       } 
          System.out.println("parsed");
          
          try{
           this.fetch();   
          }catch(Exception e)
          {
              System.out.println(e);
          }
        
          System.out.println("fetched");
      Document document = new Document();
         try {
             System.out.println("writing in files");
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Himanshu\\Desktop\\bill.pdf"));

            //open
            document.open();
 System.out.println("writing complete");
            Paragraph p = new Paragraph();
            p.add("CANTEEN BILL ");
            p.setAlignment(Element.ALIGN_CENTER);
            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
            document.add(p);
            document.add(new Paragraph("\n\n\n", f));
            Paragraph p2 = new Paragraph();
            p2.add("ORDERS :"+select1s +" \tQTY :"+orderqty1); //no alignment
            Paragraph p3 = new Paragraph();
            p3.add("ORDERS :"+select2s +" \tQTY :"+orderqty2);
            Paragraph p4 = new Paragraph();
            p4.add("ORDERS :"+select3s +" \tQTY :"+orderqty3);
            document.add(p2);
           
            
            document.add(new Paragraph("\n\n\n", f));
            document.add(new Paragraph("Estimated time for order : "+totaltime+"minute", f));
            document.add(new Paragraph("\n\n\n", f));
            document.add(new Paragraph("PAY MONEY AT THE COUNTER", f));
            //close
            document.close();
            
            System.out.println("Done");
         
        } catch (FileNotFoundException | DocumentException e) {
        } catch (IOException e) {
             System.out.println("NOT created" +e );
        }
        
        
        
    }//GEN-LAST:event_order_buttonActionPerformed

    private void admin_page_redirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_page_redirectorActionPerformed
        login_page.setVisible(false);
        admin_login.setVisible(true);
    }//GEN-LAST:event_admin_page_redirectorActionPerformed

    private void admin_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_buttonActionPerformed
       try
       {
          stmt=conn.createStatement();
          String username=adminusername.getText();
          String password=adminpassword.getText();
          if(username.equals("")||password.equals(""))
          {
           JOptionPane.showMessageDialog(null,"Enter the username or password ");   
          }
          else
          {
          if(username.equals("root") && password.equals("root"))
          {
              admin_login.setVisible(false);
              admin_panel.setVisible(true);
          }
          else
          {
              JOptionPane.showMessageDialog(null,"invalid password");
          }
          }
       }catch(HeadlessException | SQLException e)
       {
           
       } 
    }//GEN-LAST:event_admin_buttonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel)admintable.getModel();
        model.setRowCount(0);
        fetch();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CookedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CookedActionPerformed
         try {
             stmt=conn.createStatement();
             int uid=Integer.parseInt(uniqueid.getText());
             String sql="UPDATE orders SET ordercompleted=1 WHERE unique_id='"+uid+"'";
             stmt.executeUpdate(sql);
             uniqueid.setText("");
             DefaultTableModel model = (DefaultTableModel)admintable.getModel();
             model.setRowCount(0);
             fetch();
      System.out.println(uid);
         } catch (SQLException ex) {
             System.out.println(ex);
         }
        
    }//GEN-LAST:event_CookedActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      login_page.setVisible(true);
      userpage.setVisible(false);
      current_user=0;
      priority=0;
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        refresh();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Cooked1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cooked1ActionPerformed
         try {
             stmt=conn.createStatement();
             int uid=Integer.parseInt(uniqueid.getText());
             String sql="UPDATE orders SET cooking=1 WHERE unique_id='"+uid+"'";
             stmt.executeUpdate(sql);
             uniqueid.setText("");
             DefaultTableModel model = (DefaultTableModel)admintable.getModel();
             model.setRowCount(0);
             fetch();
      System.out.println(uid);
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }//GEN-LAST:event_Cooked1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        admin_panel.setVisible(false);
        login_page.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void admin_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_button1ActionPerformed
      admin_login.setVisible(false);
      login_page.setVisible(true);
    }//GEN-LAST:event_admin_button1ActionPerformed

    private void availabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availabilityActionPerformed
      admin_panel.setVisible(false);
      available.setVisible(true);
 lunch_field.setText(String.valueOf(lunch_value));
 chinese_field.setText(String.valueOf(chinese_value));
 dosa_field.setText(String.valueOf(dosa_value));
 uthappa_field.setText(String.valueOf(uthappa_value));
 coffee_field.setText(String.valueOf(coffee_value));
 tea_field.setText(String.valueOf(tea_value)); 
      
    }//GEN-LAST:event_availabilityActionPerformed

    private void dosa_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosa_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dosa_fieldActionPerformed

    private void coffee_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coffee_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coffee_fieldActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
 lunch_value=Integer.parseInt(lunch_field.getText());
 chinese_value=Integer.parseInt(chinese_field.getText());
 dosa_value=Integer.parseInt(dosa_field.getText());
 uthappa_value=Integer.parseInt(uthappa_field.getText());
 coffee_value=Integer.parseInt(coffee_field.getText());
 tea_value=Integer.parseInt(tea_field.getText()); 
 admin_panel.setVisible(true);
 available.setVisible(false);
    }//GEN-LAST:event_jButton8ActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         //</editor-fold>
         
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cooked;
    private javax.swing.JButton Cooked1;
    private javax.swing.JButton admin_button;
    private javax.swing.JButton admin_button1;
    private javax.swing.JPanel admin_login;
    private javax.swing.JButton admin_page_redirector;
    private javax.swing.JPanel admin_panel;
    private javax.swing.JPasswordField adminpassword;
    private javax.swing.JTable admintable;
    private javax.swing.JTextField adminusername;
    private javax.swing.JButton availability;
    private javax.swing.JPanel available;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JTextField chinese_field;
    private javax.swing.JTextField coffee_field;
    private javax.swing.JTextField dosa_field;
    private javax.swing.JTextField email;
    private javax.swing.JButton go_signup;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelorderstatus;
    private javax.swing.JPanel login_page;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField lunch_field;
    private javax.swing.JTextField name;
    private javax.swing.JTextField order1;
    private javax.swing.JTextField order2;
    private javax.swing.JTextField order3;
    private javax.swing.JButton order_button;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField password_add;
    private javax.swing.JPasswordField password_entry;
    private javax.swing.JComboBox<String> select1;
    private javax.swing.JComboBox<String> select2;
    private javax.swing.JComboBox<String> select3;
    private javax.swing.JPanel signup;
    private javax.swing.JTextField tea_field;
    private javax.swing.JTextField uniqueid;
    private javax.swing.JLabel username;
    private javax.swing.JTextField username_add;
    private javax.swing.JTextField username_entry;
    private javax.swing.JPanel userpage;
    private javax.swing.JTextField uthappa_field;
    // End of variables declaration//GEN-END:variables
}
