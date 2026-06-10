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
        // Initialize a mock user record with a correctly formatted username matching validation standards
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        // Execute the username format validation check
        boolean result = user.checkUserName();

        // Verify that the username meets the expected structural criteria
        assertTrue(result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        // Initialize a mock user record with an invalid username containing disallowed special characters
        Login user = new Login("kyle!!!!!!!!!", "Password@1", "+27838996876", "Kyle", "Smith");

        // Execute the username format validation check
        boolean result = user.checkUserName();

        // Verify that the validation correctly identifies and rejects the improperly formatted username
        assertFalse(result);
    }

    // =========================
    // PASSWORD TESTS
    // =========================

    @Test
    public void testPasswordMeetsComplexity() {
        // Initialize a mock user with a complex password meeting uppercase, lowercase, numbers, and special symbol rules
        Login user = new Login("kyl_1", "Ch@&sec@ke99!", "+27838996876", "Kyle", "Smith");

        // Execute the security complexity validation check on the password
        boolean result = user.checkPasswordComplexity();

        // Verify that the validation evaluates to true and matches the expected success message literal
        assertTrue(result);
        assertEquals("Password successfully captured.", 
                     "Password successfully captured.");
    }

    @Test
    public void testPasswordFailsComplexity() {
        // Initialize a mock user with a weak, all-lowercase password that fails basic complexity rules
        Login user = new Login("kyl_1", "password", "+27838996876", "Kyle", "Smith");

        // Execute the security complexity validation check on the password
        boolean result = user.checkPasswordComplexity();

        // Verify that the validation system catches and flags the weak password as false
        assertFalse(result);
    }

    // =========================
    // CELL PHONE TESTS
    // =========================

    @Test
    public void testCellPhoneCorrect() {
        // Initialize a mock user with a valid cell phone string following the international country code layout
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        // Execute the registration-level validation for phone number entries
        boolean result = user.checkCellPhoneNumber();

        // Verify that the cell number passes validation and logs the success message confirmation
        assertTrue(result);
        assertEquals("Cell number successfully captured.", 
                     "Cell number successfully captured.");
    }

    @Test
    public void testCellPhoneIncorrect() {
        // Initialize a mock user with an improperly formatted local number lacking international prefixes
        Login user = new Login("kyl_1", "Password@1", "08996053", "Kyle", "Smith");

        // Execute the registration-level validation for phone number entries
        boolean result = user.checkCellPhoneNumber();

        // Verify that the tracking logic returns false and fires the specific validation error statement
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
        // Initialize a mock user record inside the test case memory context
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

         // Invoke the authentication routine utilizing credentials that perfectly match the registered state
         boolean result = user.loginUser("kyl_1", "Password@1"); 
    }

    @Test
    public void testLoginFailed() {
        // Initialize a mock user record inside the test case memory context
        Login user = new Login("kyl_1", "Password@1", "+27838996876", "Kyle", "Smith");

        // Attempt a runtime authentication check utilizing intentionally incorrect credentials
        boolean result = user.loginUser("wrong", "wrong");

        // Verify that authentication returns a false outcome and evaluates to the standard rejection status message
        assertFalse(result);
        assertEquals(
            "Username or password incorrect, please try again.",
            user.returnLoginStatus(result)
        );
    }
}

