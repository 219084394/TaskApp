package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Profile  extends JFrame implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblStudentNumber, lblFirstName, lblLastName, lblEmail, lblPassword, lblProfile;
    private JTextField studentNo, firstName, lastName, email, password;
    private JCheckBox editDet;
    private JButton btnSave, btnBack;
    String user = Login.uN;
    //String newUser = SignUp.nUser;

    public Profile(){
        super("Profile");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth = new JPanel();
        lblStudentNumber = new JLabel("Student Number:");
        lblFirstName = new JLabel("First Name:");
        lblLastName = new JLabel("Last Name:");
        lblEmail = new JLabel("Email:");
        lblPassword = new JLabel("Password:");
        studentNo = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        password = new JTextField();
        editDet = new JCheckBox("Edit Details");
        btnSave = new JButton("Save");
        btnBack = new JButton("Back");
        lblProfile = new JLabel("Profile", SwingConstants.CENTER);
    }
    public void setProfileGUI(){
        //panelNorth.setLayout(new GridLayout(1,1));
        panelCenter.setLayout(new GridLayout(6,2,0,6));
        panelSouth.setLayout(new GridLayout(1,1));

        //panelNorth.setBorder(BorderFactory.createEmptyBorder(25,25,5,25));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(50,25,25,25));
        panelSouth.setBorder(BorderFactory.createEmptyBorder(0,25,25,25));

        // btnBack.setIcon(backIcon);
        //  btnBack.setPreferredSize(new Dimension(0, 40));
        // btnBack.setLayout(null);

        btnSave.setPreferredSize(new Dimension(0, 30));

        studentNo.setPreferredSize(new Dimension(200, 20));
        firstName.setPreferredSize(new Dimension(200, 20));
        lastName.setPreferredSize(new Dimension(200, 20));
        email.setPreferredSize(new Dimension(200, 20));
        password.setPreferredSize(new Dimension(200, 20));

        studentNo.setEnabled(false);
        firstName.setEnabled(false);
        lastName.setEnabled(false);
        email.setEnabled(false);
        password.setEnabled(false);

        panelNorth.setBackground(new Color(0,29,64));
        panelCenter.setBackground(new Color(0,29,64));
        panelSouth.setBackground(new Color(0,29,64));

        lblStudentNumber.setForeground(Color.white);
        lblFirstName.setForeground(Color.white);
        lblLastName.setForeground(Color.white);
        lblEmail.setForeground(Color.white);
        lblPassword.setForeground(Color.white);
        editDet.setForeground(Color.white);

        editDet.setContentAreaFilled(false);

        btnSave.setBackground(new Color(250, 134, 0));
        btnSave.setForeground(Color.white);
        btnBack.setBackground(new Color(250, 134, 0));
        btnBack.setForeground(Color.white);

        //panelNorth.add(btnBack);
        //panelNorth.add(lblProfile);

        panelCenter.add(lblStudentNumber);
        panelCenter.add(studentNo);
        panelCenter.add(lblFirstName);
        panelCenter.add(firstName);
        panelCenter.add(lblLastName);
        panelCenter.add(lastName);
        panelCenter.add(lblEmail);
        panelCenter.add(email);
        panelCenter.add(lblPassword);
        panelCenter.add(password);
        panelCenter.add(editDet);
        panelSouth.add(btnSave);
        panelSouth.add(btnBack);

        editDet.addActionListener(this);
        btnSave.addActionListener(this);
        btnBack.addActionListener(this);

        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableinfo?autoReconnect=true&useSSL=false", "root", "");
            Statement stm = con.createStatement();
            String sql = "SELECT * FROM user where Student_Number='" +user+ "'";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
                String stdNo = rs.getString(1);
                String fName = rs.getString(2);
                String lName = rs.getString(3);
                String eMessage = rs.getString(4);
                String passwordUsed = rs.getString(5);

                studentNo.setText(stdNo);
                firstName.setText(fName);
                lastName.setText(lName);
                email.setText(eMessage);
                password.setText(passwordUsed);
            }
            con.close();
            stm.close();
        }
        catch(Exception ev){
            System.out.println(ev.getMessage());
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (editDet.isSelected()){
            firstName.setEnabled(true);
            lastName.setEnabled(true);
            email.setEnabled(true);
            password.setEnabled(true);
        }
        else{
            firstName.setEnabled(false);
            lastName.setEnabled(false);
            email.setEnabled(false);
            password.setEnabled(false);
        }

        if (e.getActionCommand().equals("Save")){
            if(password.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"User requires a password");
                return;
            }
            if(password.getText().length() < 5){
                JOptionPane.showMessageDialog(null,"Password too short");
                return;
            }
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tableinfo?autoReconnect=true&useSSL=false", "root", "");
                Statement stm = con.createStatement();
                String value= studentNo.getText();
                String nameUsed = firstName.getText();
                String surname = lastName.getText();
                String emailUsed = email.getText();
                String passUsed = password.getText();
                String sql = "UPDATE user SET FirstName='"+nameUsed+"', LastName='" +surname+"', Email='" +emailUsed+"', Password='" +passUsed+" 'WHERE Student_Number='" +value+ "'";
                stm.executeUpdate(sql);

                con.close();
                stm.close();

            }
            catch(Exception ev){
                System.out.println(ev.getMessage());
            }
            JOptionPane.showMessageDialog(null, "Saving details");
            Home home = new Home();
            home.setGUI();
            this.dispose();
        }
        else if (e.getActionCommand().equals("Back")){
            Home home = new Home();
            home.setGUI();
            this.dispose();
        }
    }

    public static void main(String[] args) {

        new Profile().setProfileGUI();
    }
}
