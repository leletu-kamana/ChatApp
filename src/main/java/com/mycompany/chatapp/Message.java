/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Lelethu Kamana
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

public class Message {

    // === PART 2 ===
    // The fields below consists of a message data handlers.
    //=============================================
    // SECTION 4.1 - Create the Message Class
    //=============================================
    private String messageID; // 10-digit auto-generated
    private int messageNumber; // from loop counter
    private String recipient; // validated cell number
    private String messageText; // max of 250 chars
    private String messageHash; // auto-generated
    private String sendMessage; // Sent, Stored, or Disregerded
    private String messageStatus; // status of the message (Created, Sent, Disregarded, Stored)

    // =============================================
    // PART 3 - SECTION 1: FIVE PARALLEL ARRAYS
    // These static lists are shared across all Message objects.
    // They stay alive for the whole session and fill up as the user
    // sends, discards, or stores messages.
    // Static means one copy exists for the whole application, not per object.
    // =============================================
    private static List<String> sentMessages       = new ArrayList<>();  // text of sent messages
    private static List<String> disregardedMessages = new ArrayList<>(); // text of discarded messages
    private static List<String> storedMessages     = new ArrayList<>();  // text loaded from JSON file
    private static List<JSONObject> storedMessageObjects = new ArrayList<>(); // full JSON objects loaded from file
    private static List<String> messageHashes      = new ArrayList<>();  // hash for every sent message
    private static List<String> messageIDs         = new ArrayList<>();  // ID for every sent message
    private static List<String> recipientList      = new ArrayList<>();  // recipient for every sent message
    private static List<String> statusList = new ArrayList<>(); // tracks status per message entry
    
    private static int totalMessages = 0;
    private static String printedMessages = "";
    
    //====================================================
    // SECTION 4.2 - Methods Required in the Message Class
    //====================================================
    // The constructor will intitailise fields for the logic.
    public Message(int messageNumber, String recipient, String messageText) {
        // Logic to follow under the Message Public Brackets.
       
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();

        // BUG 3 FIX: messageStatus was never initialised in the constructor.
        // Without this, getMessageStatus() throws a NullPointerException because
        // switch(null) is not allowed in Java. Default to "Created" at construction.
        this.messageStatus = "Created";
    }
    
    public String createMessageHash() {
        // Step 1: To retrive 2 chars of the massege ID.
        String idPart = messageID.substring(0, 2);
        // Step 2: Separates to the message into words.
        String[] words = messageText.split(" ");
        // Step 3: Retrive the first and last words.
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        // Step 4: Construction of the Hash String and return it as UPPERCASE
        // Format: the idPart, "i", messageNumber, ":", firstWord and lastWord.
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord; // Formation of hash.
        return hash.toUpperCase();
    }
    
    //=================================
    // SECTION 5.1 - Generate 10 digit ID
    //=================================
    public String generateMessageID() {
       Random random = new Random();
       String id = "";
       
       // for loop consttructs tthe validation to be 10 digit exactly.
       // from 0 to 9
       // the id = id + digit manipulates the string 
       for (int i = 0; i < 10; i++) {
           int digit = random.nextInt(10); 
           id = id + digit;
       }
       
       return id;
    }
    
    //================================
    // SECTION 5.2 - Validate ID Length
    //================================
    public boolean checkMessageID() {
        // validates if the ID lenght is 10 chars or less.
        if (messageID.length() == 10) {
              return true;
        } else {
            return false;
        } 
    }
    
    //====================================
    //SECTION 6 - Recipient Cell Number
    //====================================
    public String checkRecipientCell() {
        // Recreation of the part 1 checkCellPhoneNumber Validatiion.
        // The checkCellPhoneNumber in part 1 is to checkRecipientCell as it will be ID for recipient.
        // Should contain a South African international code(+27).
        // Shold be 12 digit number.
        if (recipient.startsWith("+27") && recipient.length() == 12) {
           return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    //==========================================
    // SECTION 7 - Message Text Validation
    //==========================================
    public String checkMessageLength() {
        // validates if the message consists of 250 chars and less.
        // error message is to be displayed when message exceeds 250 chars.
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int over = messageText.length() - 250;
            return "Message exceeds 250 characters by " + over + "; please reduce the size.";
        }
    }
    
    /**
     * Presents an interactive console sub-menu to the user to determine the routing action 
     * for the newly created message. Based on user choice, it updates the message's status, 
     * logs data to the live memory parallel tracking collections, or writes persistent records via external handlers.
     * * Menu Routing Behaviors:
     * 1) Send Message: Marks the state as "Sent" and appends the text, hash, ID, and recipient details 
     * into the active runtime parallel lists so it can be tracked in the main session report.
     * 2) Disregard Message: Marks the state as "Disregarded" and adds the text to the discard tracker.
     * 3) Store Message: Marks the state as "Stored" and serializes the state to a structured local JSON file.
     * * @return A status feedback message string intended for console display, confirming the outcome of the action.
     */
    public String sentMessage() {
        // Initialize an isolated Scanner instance to capture input options inside the method scope
        Scanner input = new Scanner(System.in);
        // Render the message processing menu to the console
        System.out.println("---------------------------------------------------------");
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send Message"); 
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later"); 
        
        // Capture the user's action selection
        System.out.print("\nOption choice(1, 2 or 3): ");
        int option = input.nextInt();
        input.nextLine();// scanner will detect the option list. 
        
        // Route application execution based on the chosen menu index
        switch (option) { 
            case 1:
                // Transition message status to Sent
                this.messageStatus = "Sent";
                
                // Synchronously populate all parallel tracking structures to register the message for the active session
                sentMessages.add(this.messageText);
                messageHashes.add(this.messageHash);
                messageIDs.add(this.messageID);
                recipientList.add(this.recipient);
                statusList.add(this.messageStatus); // <--- Log Status
                return "Message successfully sent.";
                
            case 2:
                // Transition message status to Disregarded
                this.messageStatus = "Disregarded";
                
                // Track the content inside the disregarded messages repository
                disregardedMessages.add(this.messageText);
                statusList.add(this.messageStatus); // <--- Log Status
                return "Press 0 to delete the message."; 
                
            case 3:
                // Transition message status to Stored
                this.messageStatus = "Stored";
                
                // Trigger file writing mechanism to persist the message data structurally in 'Message.json'
                storeMessage(); // call your JSON method 
                statusList.add(this.messageStatus); // <--- Log Status
                return "Message successfully stored."; 
            default:
                return "Invalid option selected"; // handle unexpected input -- logic goes here
        }
    } 
    
    public void storeMessage() { 
        try {

            // Create new JSON object
            JSONObject obj = new JSONObject();

            // Add message data into JSON object
            obj.put("MessageID", this.messageID);
            obj.put("Recipient", this.recipient);
            obj.put("Message", this.messageText);
            obj.put("Hash", messageHash);

            // Open messages.json file in append mode
            FileWriter file = new FileWriter("Message.json", true);

            // Write JSON object to file
            file.write(obj.toString());

            // Move to next line after each message
            file.write(System.lineSeparator());

            // Close file after writing
            file.close();

            // Dynamically push elements straight into active memory runtime arrays 
            storedMessages.add(this.messageText);
            storedMessageObjects.add(obj);

        } catch (IOException e) {

            // If error occurs during saving
            System.out.println("Error saving message.");
        }
    }
    
     // =============================================
    // PART 3 - SECTION 5: LOAD STORED MESSAGES FROM JSON
    // Reads Message.json line by line and loads each stored message
    // into the storedMessages array so it is available in the sub-menu.
    // Called once from MainApp right after the user logs in.
    // =============================================

    /**
     * Reads the Message.json file and loads each stored message into the
     * storedMessages array. Called once at startup after login.
     * If no file exists yet, the method exits silently without crashing.
     */
    public static void loadStoredMessages() {
        // Clear the lists first to avoid duplicates if called more than once
        storedMessages.clear();
        storedMessageObjects.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader("Message.json"))) {
            String line;
            // Read each line - each line is one JSON object
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // Parse the line as a JSON object
                    JSONObject obj = new JSONObject(line);
                    // Extract the message text field and add to the arrays
                    String messageContent = obj.getString("Message");
                    storedMessages.add(messageContent);
                    storedMessageObjects.add(obj);
                }
            }
            System.out.println("Stored messages loaded: " + storedMessageObjects.size() + " message(s) found.");

        } catch (IOException e) {
            // File does not exist yet - this is normal on first run, no crash
            System.out.println("No previous stored messages found. Starting fresh.");
        }
    }
    
    // =============================================
    // PART 3 - SECTION 3: DISPLAY LONGEST MESSAGE
    // Searches the storedMessages array and returns the longest one.
    // =============================================

    /**
     * Searches the storedMessages array and returns the message with
     * the most characters. Returns a message if the array is empty.
     *
     * @return the longest stored message string
     */
    public static String displayLongestMessage() {
      // Return early with a warning if no messages have been loaded from the database file
        if (storedMessageObjects.isEmpty()) {
            return "Longest messages stored on database not found .";
        }

        // Find the message with the longest text
        JSONObject longestMessage = null;
        int maxLength = 0;
        
        for (JSONObject obj : storedMessageObjects) {
            String messageText = obj.getString("Message");
            if (messageText.length() > maxLength) {
                maxLength = messageText.length();
                longestMessage = obj;
            }
        }
        
        // If no message found (shouldn't happen), return error
        if (longestMessage == null) {
            return "No messages found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("      === LONGEST STORED MESSAGE ===    \n");
        report.append("========================================\n");

        // Display the details of the longest message with numbering
        report.append("\nMessage:\n");
        report.append("Message Text   : ").append(longestMessage.getString("Message")).append("\n");
        report.append("Message Length : ").append(maxLength).append(" characters\n");

        return report.toString();
    }
    
    // =============================================
    // PART 3 - SECTION 4.1: SEARCH BY MESSAGE ID
    // Searches the messageIDs array for a match and returns
    // the corresponding message at the same index (parallel array).
    // =============================================

    /**
     * Searches the messageIDs array for the given ID and returns
     * the matching sent message using the same array index.
     *
     * @param id the message ID to search for
     * @return the matching message text, or a not-found message
     */
    public static String searchByMessageID(String id) {
        // Loop through the messageIDs array to find a match
        for (JSONObject obj : storedMessageObjects) {
            if (obj.getString("MessageID").equals(id)) {
                return "Message ID : " + obj.getString("MessageID")
                     + "\nRecipient  : " + obj.getString("Recipient")
                     + "\nMessage    : " + obj.getString("Message")
                     + "\nHash       : " + obj.getString("Hash")
                     + "\nMessage Status: Stored";
            }
        }
        // No match was found
        return "Message not found.";
    }

    // =============================================
    // PART 3 - SECTION 4.2: SEARCH BY RECIPIENT
    // Searches all messages for a given recipient number.
    // May return more than one result if multiple messages share a recipient.
    // =============================================

    /**
     * Searches all sent messages for the given recipient number.
     * Returns all matching messages since one recipient may have received multiple.
     *
     * @param searchRecipient the recipient cell number to search for
     * @return all matching messages formatted as a string, or not-found message
     */
    public static String searchByRecipient(String searchRecipient) {
        StringBuilder results = new StringBuilder();
        
        
        for (int i = 0; i < storedMessageObjects.size(); i++) {
            JSONObject obj = storedMessageObjects.get(i);
            // Loop through the recipientList to find all matching entries
            if (obj.getString("Recipient").equals(searchRecipient)) {
                results.append("\nMessage ").append(i + 1).append(":\n");
                results.append("Message ID : ").append(obj.getString("MessageID")).append("\n")
                       .append("Recipient  : ").append(obj.getString("Recipient")).append("\n")
                       .append("Message    : ").append(obj.getString("Message")).append("\n")
                       .append("Hash       : ").append(obj.getString("Hash")).append("\n")
                       .append("Message Status: Stored\n")
                       .append("----------------------------------------\n");
            }
        } 
        
        if (results.length() == 0) {
            return "No messages found for recipient: " + searchRecipient;
        }
        return results.toString().trim();
    }
    
    // =============================================
    // PART 3 - SECTION 4.3: DELETE BY MESSAGE HASH
    // Finds the entry in messageHashes that matches the given hash
    // and removes it along with corresponding entries in other arrays.
    // =============================================

    /**
     * Deletes the message matching the given hash from all parallel arrays.
     * Removes the same index from sentMessages, messageHashes, messageIDs,
     * and recipientList to keep the arrays in sync.
     *
     * @param hash the message hash to search for and delete
     * @return a success message with the deleted text, or a not-found message
     */
    public static String deleteByMessageHash(String hash) {
        for (int i = 0; i < storedMessageObjects.size(); i++) {
            if (storedMessageObjects.get(i).getString("Hash").equals(hash)) {
                JSONObject deleted = storedMessageObjects.get(i);
                storedMessageObjects.remove(i);
                storedMessages.remove(i);
                return "Message deleted successfully:\n"
                     + "Message ID : " + deleted.getString("MessageID")
                     + "\nRecipient  : " + deleted.getString("Recipient")
                     + "\nMessage    : " + deleted.getString("Message")
                     + "\nHash       : " + deleted.getString("Hash");
            }
        }
        // No matching hash was found
        return "Hash not found.";
    }
    
    // =============================================
    // PART 3 - SECTION 6: DISPLAY MESSAGE REPORT
    // Loops through sentMessages and prints hash, recipient,
    // and message text for every sent message in the session.
    // =============================================

    /**
     * Builds and returns a formatted report of all sent messages.
     * Each entry shows the Message Hash, Recipient, and Message text.
     * Uses parallel arrays so the same index retrieves all three fields.
     *
     * @return a formatted String report of all sent messages
     */
    public static String printMessages() {
        // Return early if no messages have been sent this session
        if (sentMessages.isEmpty()) {
            return "No messages have been sent in this session.";
        }

        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("         === MESSAGE REPORT ===         \n");
        report.append("========================================\n");

        // Loop through all sent messages using the index to retrieve parallel fields
        for (int i = 0; i < sentMessages.size(); i++) {
            report.append("Message Hash : ").append(messageHashes.get(i)).append("\n");
            report.append("Recipient    : ").append(recipientList.get(i)).append("\n");
            report.append("Message      : ").append(sentMessages.get(i)).append("\n");
            report.append("Message ID   : ").append(messageIDs.get(i)).append("\n");
            String status = i < statusList.size() ? statusList.get(i) : "Unknown Status";
            report.append("Message Status: ").append(status).append("\n");
            report.append("----------------------------------------\n");
        }

        return report.toString();
    }
    
    /**
     * Displays all message details.
     *
     */
    public void printMessageDetails() {
        System.out.println("Message ID: " + messageID);
        System.out.println("Message Hash: " + messageHash);
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + messageText);
    }
    
    /**
     * Evaluates the current internal state variable of the message object 
     * and maps it to a standardized, human-readable status string.
     * This ensures consistent status reporting across UI outputs and session logs.
     * * @return A clean String representing the lifecycle state: "Sent", 
     * "Disregarded", "Stored", or "Unknown Status" if undefined.
     */
    public String getMessageStatus() {
        // Inspect the current string value assigned to the messageStatus field
        switch (messageStatus) {
            case "Sent":
                // Returned if option 1 was selected in the sentMessage sub-menu
                return "Sent";
                
            case "Disregarded":
                // Returned if option 2 was selected in the sentMessage sub-menu
                return "Disregarded";
                
            case "Stored":
                // Returned if option 3 was selected in the sentMessage sub-menu
                return "Stored";
            default:
                // Fallback state if the message has been instantiated but no routing action is taken yet
                return "Unknown Status";
        }
    }
    
    /**
     * Triggers a fresh reload from the file system, then compiles and returns a 
     * formatted presentation block of all stored records with individual statuses.
     *
     * @return Formatted report text block string.
     */
    public static String getLoadStoredMessages() {
        // Refresh live arrays from disk
        loadStoredMessages();
        
        if (storedMessageObjects.isEmpty()) {
            return "No persistent stored messages found in database.";
        }

        StringBuilder report = new StringBuilder();
        report.append("========================================\n");
        report.append("         === STORED MESSAGES ===        \n");
        report.append("========================================\n");

        for (int i = 0; i < storedMessageObjects.size(); i++) {
            JSONObject obj = storedMessageObjects.get(i);
            
            // Add message numbering (1-based index)
            report.append("\nMessage ").append(i + 1).append(":\n");
            report.append("Message Text   : ").append(obj.getString("Message")).append("\n");
           
            report.append("----------------------------------------\n");
        }

        return report.toString();
    }
    
    // =============================================
    // GETTERS USED BY JUNIT TESTS
    // =============================================

    // Getter to retrieve the list of all sent plain-text messages
    public static List<String> getSentMessages() {
        return sentMessages;
    }

    // Getter to retrieve the list of all stored plain-text messages
    public static List<String> getStoredMessages() {
        return storedMessages;
    }

    // Getter to retrieve the list of structured JSONObject records representing stored messages
    public static List<JSONObject> getStoredMessageObjects() {
        return storedMessageObjects;
    }

    // Getter to retrieve the list of generated unique cryptographic or identification hashes
    public static List<String> getMessageHashes() {
        return messageHashes;
    }

    // Getter to retrieve the list of distinct Message IDs assigned to tracking records
    public static List<String> getMessageIDs() {
        return messageIDs;
    }

    // Getter to retrieve the list of target recipient contact addresses or phone numbers
    public static List<String> getRecipientList() {
        return recipientList;
    }
    
    // Utility method to clear and completely reset all data collection structures back to empty states
    public static void resetAll() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        storedMessageObjects.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipientList.clear();
        statusList.clear();
    }
}
