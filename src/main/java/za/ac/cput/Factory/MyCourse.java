package za.ac.cput.Factory;

import net.proteanit.sql.DbUtils;
import za.ac.cput.Crud;
import za.ac.cput.Login;

import javax.swing.*;
import java.sql.*;

public class MyCourse {
    private JTable courseTable;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addButton;
    private JTextField myCourseName;
    private JTextField myCourseCode;
    private JTextField lectFirstName;
    private JTextField lectLastName;
    private JTextField lectEmail;
    private JTextField courseId;
    private JButton searchButton;
    private JButton clearButton;
    private JLabel courseHeading;
    private JPanel myCoursePnl;
    private JButton backButton;
    String z = Login.uN;

    Connection con2;
    PreparedStatement pst;
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/tableinfo","root","");
            System.out.println("success");
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
    }

    public void table_load(){
        try{
            pst = con2.prepareStatement("select * from user_course where Student_number='"+z+"'");
            ResultSet rs = pst.executeQuery();
            courseTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setGUI(){
        JFrame frame = new JFrame("My Courses");
        frame.setContentPane(new MyCourse().myCoursePnl);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public MyCourse(){
        Connect();
        table_load();
    }

    public static void main(String[] args) {
        new MyCourse().setGUI();

    }
}
