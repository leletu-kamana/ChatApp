/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Lelethu Kamana
 */

public class LoginTest {

    // =========================
    // USERNAME TESTS
    // =========================

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        boolean result = user.checkUserName();

        assertTrue(result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login user = new Login("kyle!!!!!!!!!", "Password@1", "+27838996876", "Kyle", "Smith");

        boolean result = user.checkUserName();

        assertFalse(result);
    }

    // =========================
    // PASSWORD TESTS
    // =========================

    @Test
    public void testPasswordMeetsComplexity() {
        Login user = new Login("kyl_1", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        boolean result = user.checkPasswordComplexity();

        assertTrue(result);
        assertEquals("Password successfully captured.", 
                     "Password successfully captured.");
    }

    @Test
    public void testPasswordFailsComplexity() {
        Login user = new Login("kyl_1", "password", "+27838996876", "Kyle", "Smith");

        boolean result = user.checkPasswordComplexity();

        assertFalse(result);
    }

    // =========================
    // CELL PHONE TESTS
    // =========================

    @Test
    public void testCellPhoneCorrect() {
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        boolean result = user.checkCellPhoneNumber();

        assertTrue(result);
        assertEquals("Cell number successfully captured.", 
                     "Cell number successfully captured.");
    }

    @Test
    public void testCellPhoneIncorrect() {
        Login user = new Login("kyl_1", "Password@1", "08996053", "Kyle", "Smith");

        boolean result = user.checkCellPhoneNumber();

        assertFalse(result);
        assertEquals(
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.",
            "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again."
        );
    }

    // =========================
    // LOGIN TESTS
    // =========================

    @Test
    public void testLoginSuccess() {
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

         boolean result = user.loginUser("kyl_1", "Password@1"); 
    }

    @Test
    public void testLoginFailed() {
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        boolean result = user.loginUser("wrong", "wrong");

        assertFalse(result);
        assertEquals(
            "Username or password incorrect, please try again.",
            user.returnLoginStatus(result)
        );
    }
}

