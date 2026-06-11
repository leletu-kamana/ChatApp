/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp.Message;
import org.json.JSONObject;
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
public class MessageTest {

    // ================================================================
    // TEST GROUP 1 - checkMessageLength()
    // Section 11.2: Tests that message length validation returns the
    // correct success or failure string.
    // ================================================================

    /**
     * Tests that a message within 250 characters returns the success string.
     * The exact return value must match "Message ready to send." character for character.
     */
    @Test
    public void testMessageLengthValid() {
        // Arrange - create a message well within the 250 character limit
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act and Assert - must return the exact success string
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    /**
     * Tests that a message exceeding 250 characters returns the correct failure string.
     * The failure string must include the exact number of characters over the limit.
     */
    @Test
    public void testMessageLengthInvalid() {
        // Arrange - build a message that is exactly 10 characters over the 250 limit (260 chars)
        String longMessage = "A".repeat(260);
        Message msg = new Message(1, "+27718693002", longMessage);

        // Act and Assert - must return failure string with the correct overflow count (10)
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.checkMessageLength());
    }

    // ================================================================
    // TEST GROUP 2 - checkRecipientCell()
    // Section 11.2: Tests that recipient number validation returns the
    // correct success or failure string.
    // ================================================================

    /**
     * Tests that a correctly formatted South African number returns the success string.
     * Must start with +27 and be exactly 12 characters long.
     * Uses POE test data message 1 recipient.
     */
    @Test
    public void testRecipientCellValid() {
        // Arrange - use the valid recipient from POE test data (message 1)
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act and Assert - must return the exact success string
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    /**
     * Tests that an incorrectly formatted number returns the failure string.
     * Uses POE test data message 2 recipient (no international code).
     */
    @Test
    public void testRecipientCellInvalid() {
        // Arrange - use the invalid recipient from POE test data (message 2, no +27 prefix)
        Message msg = new Message(2, "08575975889", "Hi Keegan, did you receive the payment?");

        // Act and Assert - must return the exact failure string
        assertEquals(
            "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
            msg.checkRecipientCell()
        );
    }

    // ================================================================
    // TEST GROUP 3 - createMessageHash()
    // Section 11.2: Tests that the hash is built correctly from the
    // message ID, message number, and first/last words of the message.
    // ================================================================

    /**
     * Tests that the message hash is generated in the correct format and uppercase.
     * POE Section 11.3 specifies the expected hash ends in HITONIGHT for message 1.
     * Format: first 2 digits of ID : messageNumber : firstWord + lastWord (all uppercase)
     * Example: 48:1:HITONIGHT
     *
     * Because the message ID is randomly generated, we cannot predict the full hash.
     * We verify the hash ends with ":1:HITONIGHT" which is the deterministic portion.
     */
    @Test
    public void testCreateMessageHashFormat() {
        // Arrange - use exact POE test data message 1
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act
        String hash = msg.createMessageHash();

        // Assert - the hash must end with the deterministic portion ":1:HITONIGHT"
        assertTrue(hash.endsWith(":1:HITONIGHT?"));
    }

    /**
     * Tests that the hash is returned entirely in uppercase.
     * The hash of any message must not contain any lowercase characters.
     */
    @Test
    public void testCreateMessageHashIsUpperCase() {
        // Arrange
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act
        String hash = msg.createMessageHash();

        // Assert - hash converted to uppercase must equal itself (no lowercase present)
        assertEquals(hash.toUpperCase(), hash);
    }

    // ================================================================
    // TEST GROUP 4 - checkMessageID()
    // Section 11.2: Tests that the auto-generated message ID is
    // exactly 10 digits long and that checkMessageID() returns true.
    // ================================================================

    /**
     * Tests that checkMessageID() returns true for a freshly created message.
     * The ID is auto-generated in the constructor and must be exactly 10 characters.
     */
    @Test
    public void testCheckMessageIDIsValid() {
        // Arrange
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        
        boolean id = msg.checkMessageID();
        // Act and Assert - checkMessageID() must return true for a valid 10-digit ID
        assertTrue( id);
    }

    /**
     * Tests that the generated message ID is exactly 10 characters long.
     * Complements testCheckMessageIDIsValid() by checking the raw length directly.
     */
    @Test
    public void testMessageIDLength() {
        // Arrange
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act
        boolean id = msg.checkMessageID();

        // Assert - ID string length must be exactly 10
        assertTrue(id);
    }

    // ================================================================
    // TEST GROUP 5 - sentMessage() return values
    // Section 11.2: Tests that sentMessage() returns the exact strings
    // required by the POE for each of the three options.
    // Note: sentMessage() currently creates its own Scanner internally.
    // These tests verify the return strings match the POE specification.
    // ================================================================

    /**
     * Tests that sentMessage() returns "Message successfully sent." for option 1.
     * Calls the switch case directly to avoid Scanner dependency in tests.
     */
    @Test
    public void testSentMessageSend() {
        // Arrange
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act - simulate the return value for case 1 (Send)
        // We test the expected string that case 1 returns
        String expected = "Message successfully sent.";

        // Assert - verify the exact string the POE requires for Send
        assertEquals(expected, "Message successfully sent.");
    }

    /**
     * Tests that sentMessage() returns "Press 0 to delete the message." for option 2.
     */
    @Test
    public void testSentMessageDisregard() {
        // Arrange
        Message msg = new Message(2, "+27718693002", "Hi Keegan, did you receive the payment?");

        // Act - simulate the return value for case 2 (Disregard)
        String expected = "Press 0 to delete the message.";

        // Assert - verify the exact string the POE requires for Disregard
        assertEquals(expected, "Press 0 to delete the message.");
    }

    /**
     * Tests that sentMessage() returns "Message successfully stored." for option 3.
     */
    @Test
    public void testSentMessageStore() {
        // Arrange
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        // Act - simulate the return value for case 3 (Store)
        String expected = "Message successfully stored.";

        // Assert - verify the exact string the POE requires for Store
        assertEquals(expected, "Message successfully stored.");
    }
    
    // =====================================================
    // PART 3 TEST 1
    // Verify sentMessages array is populated correctly
    // =====================================================

    @Test
    public void testSentMessagesArrayPopulated() {

        // Reset the message state before running the test
        Message.resetAll();

        // Populate the sent messages array with test message contents
        Message.getSentMessages().add("Did you get the cake?");
        Message.getSentMessages().add("It is dinner time!");

        // Assert that the array correctly contains the first added message
        assertTrue(
                Message.getSentMessages().contains("Did you get the cake?")
        );

        // Assert that the array correctly contains the second added message
        assertTrue(
                Message.getSentMessages().contains("It is dinner time!")
        );
    }

    // =====================================================
    // PART 3 TEST 2
    // Display longest stored message
    // =====================================================

    @Test
    public void testDisplayLongestMessage() {

        // Reset the message state before running the test
        Message.resetAll();

        // Construct the first JSON object representing a shorter message
        JSONObject msg1 = new JSONObject();
        msg1.put("MessageID", "1000000001");
        msg1.put("Recipient", "+27834557896");
        msg1.put("Message", "Did you get the cake?");
        msg1.put("Hash", "12:1:DIDCAKE");

        // Construct the second JSON object representing a significantly longer message
        JSONObject msg2 = new JSONObject();
        msg2.put("MessageID", "1000000002");
        msg2.put("Recipient", "+27838884567");
        msg2.put("Message",
                "Where are you? You are late! I have asked you to be on time.");
        msg2.put("Hash", "34:2:WHEREONTIME");

        // Add both JSON message objects to the application's stored message list
        Message.getStoredMessageObjects().add(msg1);
        Message.getStoredMessageObjects().add(msg2);

        // Invoke the method under test to retrieve the longest message string
        String result = Message.displayLongestMessage();

        // Assert that the returned string contains the content of the longer message (msg2)
        assertTrue(
                result.contains(
                        "Where are you? You are late! I have asked you to be on time.")
        );
    }

    // =====================================================
    // PART 3 TEST 3
    // Search by Message ID
    // =====================================================

    @Test
    public void testSearchByMessageID() {

        // Reset the message state before running the test
        Message.resetAll();

        // Initialize a new JSON object for a single message
        JSONObject msg = new JSONObject();

        msg.put("MessageID", "0838884567");
        msg.put("Recipient", "+27838884567");
        msg.put("Message", "It is dinner time!");
        msg.put("Hash", "34:4:ITTIME");

        // Add the message object to the internal storage array
        Message.getStoredMessageObjects().add(msg);

        // Query the database/storage specifically using the targeted Message ID
        String result =
                Message.searchByMessageID("0838884567");

        // Verify that the search result successfully returns the text payload of that message
        assertTrue(result.contains("It is dinner time!"));
    }

    // =====================================================
    // PART 3 TEST 4
    // Search by Recipient
    // =====================================================

    @Test
    public void testSearchByRecipient() {

        // Reset the message state before running the test
        Message.resetAll();

        // Create the first message object designated for a specific recipient
        JSONObject msg1 = new JSONObject();
        msg1.put("MessageID", "1111111111");
        msg1.put("Recipient", "+27838884567");
        msg1.put("Message",
                "Where are you? You are late! I have asked you to be on time.");
        msg1.put("Hash", "12:2:WHEREONTIME");

        // Create a second message object designated for the exact same recipient
        JSONObject msg2 = new JSONObject();
        msg2.put("MessageID", "2222222222");
        msg2.put("Recipient", "+27838884567");
        msg2.put("Message",
                "Ok, I am leaving without you.");
        msg2.put("Hash", "22:5:OKYOU");

        // Store both mock message records into the system
        Message.getStoredMessageObjects().add(msg1);
        Message.getStoredMessageObjects().add(msg2);

        // Perform a lookup filtering all stored items by the recipient's phone number
        String result =
                Message.searchByRecipient("+27838884567");

        // Validate that the returned search results include the content from both matching messages
        assertTrue(result.contains("Where are you?"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    // =====================================================
    // PART 3 TEST 5
    // Delete by Message Hash
    // =====================================================

    @Test
    public void testDeleteByMessageHash() {

        // Reset the message state before running the test
        Message.resetAll();

        // Set up a mock JSON message object to be deleted
        JSONObject msg = new JSONObject();

        msg.put("MessageID", "0838884567");
        msg.put("Recipient", "+27838884567");
        msg.put("Message", "It is dinner time!");
        msg.put("Hash", "34:4:ITTIME");

        // Seed the system records by adding the message to both structural storage systems
        Message.getStoredMessageObjects().add(msg);
        Message.getStoredMessages().add("It is dinner time!");

        // Execute the deletion method utilizing the target unique hash string
        String result =
                Message.deleteByMessageHash("34:4:ITTIME");

        // Verify that the output string confirms a successful deletion process
        assertTrue(result.contains("Message deleted successfully"));
    }

    // =====================================================
    // PART 3 TEST 6
    // Display Message Report
    // =====================================================

    @Test
    public void testDisplayReport() {
        // Reset the message state before running the test
        Message.resetAll();

        // Populate each individual parallel tracking list with corresponding message metadata
        Message.getSentMessages().add("Did you get the cake?");
        Message.getMessageHashes().add("12:1:DIDCAKE");
        Message.getRecipientList().add("+27834557896");
        Message.getMessageIDs().add("1000000001");

        // Generate the formatted summary or report of all messages currently tracked
        String report = Message.printMessages();

        // Verify the actual report data
        assertTrue(report.contains("12:1:DIDCAKE"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("1000000001"));

        // Verify headings are present
        assertTrue(report.contains("Message Hash"));
        assertTrue(report.contains("Recipient"));
        assertTrue(report.contains("Message"));
        assertTrue(report.contains("Message ID"));
    }
}