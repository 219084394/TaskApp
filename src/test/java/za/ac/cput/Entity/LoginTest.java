package za.ac.cput.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import za.ac.cput.Factory.LoginFactory;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    Login student1, student2, student3;

    @BeforeEach
    void setUp(){
        student1 = new Login.Builder().setStudentNumber(219264228).setPassword("Sol@4228").build();
        student2 = new Login.Builder().build();
        student3 = LoginFactory.build(219053117, "Car@3117");
        student1 = student2;
    }

    @Test
    void testObjectIdentity(){
        assertSame(student1, student2);
    }
    @Test
    void testObjectEquality(){
        assertEquals(student1, student2);
    }
    @Timeout(100)
    @Test
    void testTimeouts(){
        assertEquals(student1.getStudentNumber(), student2.getStudentNumber());
    }
    @Disabled
    @Test
    void testDisabling(){
        assertNotEquals(student1.getStudentNumber(), student3.getStudentNumber());
        System.out.println("Disabling");
    }
}