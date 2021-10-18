package za.ac.cput.Factory;

import net.proteanit.sql.DbUtils;
import za.ac.cput.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JTextField lectEmailTxt;
    private JTextField courseId;
    private JButton searchButton;
    private JButton clearButton;
    private JLabel courseHeading;
    private JPanel myCoursePnl;
    private JButton addLectButton;
    private JComboBox crsLctBox;
    private JLabel crsLectEmail;
    private JButton LectClear;
    private JButton LectUpdate;
    private JButton LectDelete;
    private JTextField lectIdTxt;
    private JButton lectSrchBtn;
    String z = Login.uN;

    Connection con2;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    PreparedStatement pst3;
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

    public void updateComboBox(){
        String sql = "select * from lecturer";
        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                crsLctBox.addItem(rs.getString("LectEmail"));
                crsLctBox.setSelectedItem(null);
            }

        }catch(Exception e){}
    }

    public void fillLecturer(){
        String mail = lectEmailTxt.getText();
        String sql = "select * from lecturer where LectEmail = '"+mail+"'";
        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                lectFirstName.setText(rs.getString("LectFname"));
                lectLastName.setText(rs.getString("LectLname"));
                lectEmailTxt.setText(rs.getString("LectEmail"));
            }

        }catch(Exception e){}
    }

    public void fillCourse(){
        String lectId = null;
        String course = myCourseCode.getText();
        String sql = "select * from course where CourseCode = '"+course+"'";

        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                //lectId = rs.getString("LectId");
                myCourseCode.setText(rs.getString("CourseCode"));
                myCourseName.setText(rs.getString("CourseName"));
            }

        }catch(Exception e){}
    }

    public void fillCourseLecturer(){
        String course = myCourseCode.getText();
        String mail = lectEmailTxt.getText();
        String lecturer = "select LectCode from course where CourseCode = '"+course+"'";

        try{
            pst = con2.prepareStatement(lecturer);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                lectEmailTxt.setText(rs.getString("LectCode"));
            }
        }catch(Exception e){}
    }

    public void fillLec(){
        String mail = lectEmailTxt.getText();
        String sql = "select LectEmail from lecturer where LectCode = '"+mail+"'";
        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                lectEmailTxt.setText(rs.getString("LectEmail"));
                String box = lectEmailTxt.getText();
                crsLctBox.setSelectedItem(box);
                lectEmailTxt.setText("");
            }
        }catch(Exception e){}
    }

    public void getLecId(){
        String mail = lectEmailTxt.getText();
        String sql = "select LectCode from lecturer where LectEmail = '"+mail+"'";
        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                lectEmailTxt.setText(rs.getString("LectCode"));
            }
        }catch(Exception e){}
    }

    public MyCourse(){
        Connect();
        table_load();
        updateComboBox();
        addLectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lectFname, lectLname, lectEmail;
                lectFname = lectFirstName.getText();
                lectLname = lectLastName.getText();
                lectEmail = lectEmailTxt.getText();

                try{
                    if(lectFname.equals("")){
                        JOptionPane.showMessageDialog(null, "No fields added");
                    }else {
                        pst = con2.prepareStatement("insert into lecturer(LectFname,LectLname,LectEmail)values(?,?,?)");
                        pst.setString(1,lectFname);
                        pst.setString(2,lectLname);
                        pst.setString(3,lectEmail);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Lecturer Added");
                        table_load();
                        lectFirstName.setText("");
                        lectLastName.setText("");
                        /*lectLastName.setSelectedItem(null);*/
                        lectEmailTxt.setText("");
                    }
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String courseCode, lectEmail, courseName;
               courseCode = myCourseCode.getText();
               lectEmail = crsLctBox.getSelectedItem().toString();
               courseName = myCourseName.getText();

                try{
                    if(courseName.equals("")){
                        JOptionPane.showMessageDialog(null, "No fields added");
                    }else {
                        pst = con2.prepareStatement("INSERT INTO course(CourseCode,LectCode,CourseName) VALUES (?,( SELECT LectCode FROM lecturer WHERE LectEmail = '"+lectEmail+"'), ?)");
                        pst.setString(1,courseCode);
                        pst.setString(2,courseName);
                        pst1 = con2.prepareStatement("INSERT INTO user_course(CourseCode,Student_Number) VALUES (?,'"+z+"')");
                        pst1.setString(1,courseCode);
                        pst2 = con2.prepareStatement("INSERT INTO course_lecturer(CourseCode,LectCode) VALUES (?,( SELECT LectCode FROM lecturer WHERE LectEmail = '"+lectEmail+"'))");
                        pst2.setString(1,courseCode);
                        pst.executeUpdate();
                        pst1.executeUpdate();
                        pst2.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Course Added");
                        table_load();
                        myCourseName.setText("");
                        crsLctBox.setSelectedItem(null);
                        myCourseCode.setText("");
                        myCourseName.requestFocus();
                    }
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCourse();
                fillCourseLecturer();
                fillLec();
            }
        });
        LectClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    lectFirstName.setText("");
                    lectLastName.setText("");
                    lectEmailTxt.setText("");
                    lectFirstName.requestFocus();
                }
            }
        });
        LectDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = lectEmailTxt.getText();

                try{
                    pst = con2.prepareStatement("delete from lecturer where LectEmail = '"+mail+"'");
                    pst.executeUpdate();
                    if(mail.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter a lecturer email address");
                    }else {
                        JOptionPane.showMessageDialog(null, "Lecturer Deleted");
                    }
                    table_load();
                    lectFirstName.setText("");
                    lectLastName.setText("");
                    lectEmailTxt.setText("");
                    lectFirstName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        LectUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lectFname, lectLname, lectEmail;

                lectFname = lectFirstName.getText();
                lectLname = lectLastName.getText();
                lectEmail = lectEmailTxt.getText();

                try{
                    pst = con2.prepareStatement("update lecturer set LectFname = ?,LectLname = ?,LectEmail = ? where LectEmail = '"+lectEmail+"'");
                    pst.setString(1,lectFname);
                    pst.setString(2,lectLname);
                    pst.setString(3,lectEmail);
                    pst.executeUpdate();
                    if(lectEmail.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter a lecturer email address");
                    }else {
                        JOptionPane.showMessageDialog(null, "Lecturer Updated");
                    }
                    table_load();
                    lectFirstName.setText("");
                    lectLastName.setText("");
                    lectEmailTxt.setText("");
                    lectFirstName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        lectSrchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillLecturer();
                String mail = lectEmailTxt.getText();
                String fname = lectFirstName.getText();
                String lname = lectLastName.getText();

                    if(mail.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter a Lecturer email address");
                    }
                    else if(fname.equals("") && lname.equals("")) {
                        lectFirstName.setText("");
                        lectLastName.setText("");
                        lectEmailTxt.setText("");
                        JOptionPane.showMessageDialog(null,"Lecturer not found");
                    }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    myCourseCode.setText("");
                    myCourseName.setText("");
                    crsLctBox.setSelectedItem(null);
                    myCourseName.requestFocus();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseName, lectEmail,courseCode,lectId;

                courseName = myCourseName.getText();
                lectEmail = crsLctBox.getSelectedItem().toString();
                courseCode = myCourseCode.getText();
                /*getLecId();
                lectId = lectEmailTxt.getText();*/

                try{
                    pst = con2.prepareStatement("update course set CourseCode = '"+courseCode+"',CourseName = '"+courseName+"' where CourseCode = '"+courseCode+"'");
                    pst.executeUpdate();
                    if(courseName.equals("") && courseCode.equals("") && lectEmail.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter your course details");
                    }else {
                        JOptionPane.showMessageDialog(null, "Course Updated");
                    }
                    table_load();
                    myCourseName.setText("");
                    crsLctBox.setSelectedItem(null);
                    myCourseCode.setText("");
                    myCourseName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String courseCode = myCourseCode.getText();

                try{
                    pst = con2.prepareStatement("delete from user_course where CourseCode = '"+courseCode+"' and Student_Number = '"+z+"'");
                    pst.executeUpdate();
                    if(courseCode.equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter a course code");
                    }else {
                        JOptionPane.showMessageDialog(null, "Course Deleted");
                    }
                    table_load();
                    myCourseCode.setText("");
                    myCourseName.setText("");
                    crsLctBox.setSelectedItem(null);
                    myCourseName.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new MyCourse().setGUI();

    }
}
