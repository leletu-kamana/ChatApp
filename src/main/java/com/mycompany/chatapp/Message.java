/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Lelethu Kamana
 */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public String sentMessage() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nWhat would you like to do with this message?");
        System.out.println("1) Send Message"); 
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message to send later"); 
        
        System.out.print("\nOption choice(1, 2 or 3): ");
        int option = input.nextInt();
        input.nextLine();// scanner will detect the option list. 
        
        switch (option) { 
            case 1:  
                return "Message successfully sent.";
            case 2: 
                return "Press 0 to delete the message."; 
            case 3: 
                storeMessage(); // call your JSON method 
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

        } catch (IOException e) {

            // If error occurs during saving
            System.out.println("Error saving message.");
        }
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
    
    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nRecipient: " + recipient
                + "\nMessage: " + messageText;
    }
}
