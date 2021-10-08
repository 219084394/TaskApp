package za.ac.cput;

import net.proteanit.sql.DbUtils;
import za.ac.cput.Factory.SubCrud;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Crud extends Login{
    public static String sT;
    private JPanel CtForm;
    public JTable table1;
    private JButton taskAddBtn;
    private JButton taskEditBtn;
    private JButton taskDelBtn;
    private JTextField taskNameTxt;
    private JTextField courseTxt;
    private JTextField dueTxt;
    private JPanel taskFieldsPanel;
    private JPanel taskFieldsBtnPanel;
    private JTextArea descTxt;
    private JTextField srchTxt;
    private JTextField load;
    private JButton srchBtn;
    private JPanel taskFieldPanel;
    private JLabel dateFromat;
    private JPanel headingPanel;
    private JLabel headingLablel;
    private JButton addSubtaskButton;
    private JButton clearBtn;
    private JComboBox comboBox1;
    private JButton loadUser;
    private JButton reloadButton;
    String z = Login.uN;


    public void setGUI(){
        JFrame frame = new JFrame("Tasks");
        frame.setContentPane(new Crud().CtForm);
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
            table1.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Crud() {
        Connect();
        table_load();
        taskAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName, course, taskDescription, dueDate;
                dueTxt.setColumns(8);

                taskName = taskNameTxt.getText();
                course = courseTxt.getText();
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
                        courseTxt.setText("");
                        descTxt.setText("");
                        dueTxt.setText("");
                        taskNameTxt.requestFocus();
                    }
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }


        });

        taskEditBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String taskName, course, taskDescription, dueDate,taskId;

                taskName = taskNameTxt.getText();
                course = courseTxt.getText();
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
                    courseTxt.setText("");
                    descTxt.setText("");
                    dueTxt.setText("");
                    srchTxt.setText("");
                    srchTxt.requestFocus();
                }catch(SQLException e1){
                    e1.printStackTrace();
                }
            }
        });

        srchBtn.addActionListener(new ActionListener() {
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
                        courseTxt.setText(Course);
                        descTxt.setText(TaskDescription);
                        dueTxt.setText(DueDate);
                        JOptionPane.showMessageDialog(null, "Task loaded");

                    }else {
                        taskNameTxt.setText("");
                        courseTxt.setText("");
                        descTxt.setText("");
                        dueTxt.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid Task ID");
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
        });

        taskDelBtn.addActionListener(new ActionListener() {
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
                        courseTxt.setText("");
                        descTxt.setText("");
                        dueTxt.setText("");
                        srchTxt.setText("");
                        taskNameTxt.requestFocus();
                    }catch(SQLException e1){
                        e1.printStackTrace();
                    }
            }
        });
        addSubtaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubCrud run = new SubCrud();
                run.setGUI();
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Clear")) {
                    taskNameTxt.setText("");
                    courseTxt.setText("");
                    descTxt.setText("");
                    srchTxt.setText("");
                    taskNameTxt.requestFocus();
                }
            }
        });
    }
    public static void main(String[] args) {
        new Crud().setGUI();

    }
}
