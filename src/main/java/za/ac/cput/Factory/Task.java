package za.ac.cput.Factory;

import net.proteanit.sql.DbUtils;
import za.ac.cput.Crud;
import za.ac.cput.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Task {
    public static String sT;
    private JPanel taskPnl;
    private JPanel taskHeading;
    private JTable taskTable;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton addSubtaskButton;
    private JButton addButton;
    private JButton clearButton;
    private JButton backButton;
    private JButton searchButton;
    private JTextField taskNameTxt;
    private JTextField dueTxt;
    private JComboBox courseCodeBox;
    private JTextArea descTxt;
    private JTextField srchTxt;
    String z = Login.uN;

    public void setGUI(){
        JFrame frame = new JFrame("Tasks");
        frame.setContentPane(new Task().taskPnl);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    Connection con2;
    PreparedStatement pst;
    public void Connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost/tableinfo","root","");
            System.out.println("success");
            System.out.println(z);
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){
        }
    }

    void table_load(){
        try{
            pst = con2.prepareStatement("select * from tasks where Student_Number='"+z+"'");
            ResultSet rs = pst.executeQuery();
            taskTable.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateComboBox(){
        String sql = "select * from user_course";
        try{
            pst = con2.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                courseCodeBox.addItem(rs.getString("CourseCode"));
                courseCodeBox.setSelectedItem(null);
            }

        }catch(Exception e){}
    }

    public Task(){
        Connect();
        table_load();
        updateComboBox();
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String id = srchTxt.getText();

                    pst = con2.prepareStatement("select TaskName,CourseCode,TaskDescription,DueDate from tasks where TaskId = ?");
                    pst.setString(1,id);
                    ResultSet rs = pst.executeQuery();
                    if(id.equals("")){
                        JOptionPane.showMessageDialog(null, "Task ID not entered");
                    }

                    else if(rs.next() == true)
                    {
                        sT = id;
                        String TaskName = rs.getString(1);
                        String Course = rs.getString(2);
                        String TaskDescription = rs.getString(3);
                        String DueDate = rs.getString(4);

                        taskNameTxt.setText(TaskName);
                        //.setText(Course);
                        courseCodeBox.setSelectedItem(Course);
                        descTxt.setText(TaskDescription);
                        dueTxt.setText(DueDate);
                        JOptionPane.showMessageDialog(null, "Task loaded");

                    }else {
                        taskNameTxt.setText("");
                        //courseTxt.setText("");
                        courseCodeBox.setSelectedItem("");
                        descTxt.setText("");
                        dueTxt.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Task ID");
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    taskNameTxt.setText("");
                    //courseTxt.setText("");
                    courseCodeBox.setSelectedItem(null);
                    descTxt.setText("");
                    srchTxt.setText("");
                    taskNameTxt.requestFocus();
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName, course, taskDescription, dueDate;
                dueTxt.setColumns(8);

                taskName = taskNameTxt.getText();
                course = courseCodeBox.getSelectedItem().toString();
                taskDescription = descTxt.getText();
                dueDate = dueTxt.getText();
                String studentNumber = z;

                try{
                    if(taskName.equals("")){
                        JOptionPane.showMessageDialog(null, "No fields added");
                    }else {
                        pst = con2.prepareStatement("insert into tasks(Student_Number,TaskName,CourseCode,TaskDescription,DueDate)values(?,?,?,?,?)");
                        pst.setString(1,studentNumber);
                        pst.setString(2,taskName);
                        pst.setString(3,course);
                        pst.setString(4,taskDescription);
                        pst.setString(5,dueDate);
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Task Added");
                        table_load();
                        taskNameTxt.setText("");
                        courseCodeBox.setSelectedItem(null);
                        descTxt.setText("");
                        dueTxt.setText("");
                        taskNameTxt.requestFocus();
                    }
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        addSubtaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = srchTxt.getText();

                if(id.equals("")){
                    JOptionPane.showMessageDialog(null, "Task ID not entered");
                }else{
                    SubCrud run = new SubCrud();
                    run.setGUI();
                }
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName, course, taskDescription, dueDate,taskId;

                taskName = taskNameTxt.getText();
                course = courseCodeBox.getSelectedItem().toString();
                taskDescription = descTxt.getText();
                dueDate = dueTxt.getText();
                taskId = srchTxt.getText();

                try{
                    pst = con2.prepareStatement("update tasks set TaskName = ?,CourseCode = ?,TaskDescription = ?,DueDate = ? where TaskId = ?");
                    pst.setString(1,taskName);
                    pst.setString(2,course);
                    pst.setString(3,taskDescription);
                    pst.setString(4,dueDate);
                    pst.setString(5,taskId);
                    pst.executeUpdate();
                    if(taskId.equals("")){
                        JOptionPane.showMessageDialog(null, "No task loaded");
                    }else {
                        JOptionPane.showMessageDialog(null, "Task Updated");
                    }
                    table_load();
                    taskNameTxt.setText("");
                    courseCodeBox.setSelectedItem(null);
                    descTxt.setText("");
                    dueTxt.setText("");
                    srchTxt.setText("");
                    srchTxt.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskId;
                taskId = srchTxt.getText();

                try{
                    pst = con2.prepareStatement("delete from tasks where TaskId = ?");
                    pst.setString(1,taskId);
                    pst.executeUpdate();
                    if(taskId.equals("")){
                        JOptionPane.showMessageDialog(null, "No task loaded");
                    }else {
                        JOptionPane.showMessageDialog(null, "Task Deleted");
                    }
                    table_load();
                    taskNameTxt.setText("");
                    courseCodeBox.setSelectedItem(null);
                    descTxt.setText("");
                    dueTxt.setText("");
                    srchTxt.setText("");
                    taskNameTxt.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        new Task().setGUI();
    }
}
