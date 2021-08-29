package za.ac.cput.Factory;

import za.ac.cput.Entity.Login;

public class LoginFactory {
    public static Login build(int studentNumber, String password) {
        return new Login.Builder()
                .setStudentNumber(studentNumber)
                .setPassword(password)
                .build();
    }
}
