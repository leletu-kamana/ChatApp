/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import com.mycompany.chatapp.Message;
import com.mycompany.chatapp.Login;
/**
 *
 * @author Lelethu Kamana
 */
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {  
        
        //User information is to entered through Scanner.
        Scanner input = new Scanner(System.in);
        ArrayList<Message> messageList = new ArrayList<>();
        
        String username;
        String password;
        String phone;
        String firstName;
        String lastName;
        
        System.out.println("========================================================");
        System.out.println("                WELCOM TO QUICKCHAT");
        System.out.println("========================================================");
        //--- REGISTRATION SECTION ---
        System.out.println("=== REGISTER ===");

        System.out.print("First Name: ");
        firstName = input.nextLine();

        System.out.print("Last Name: ");
        lastName = input.nextLine();
        
        // USERNAME LOOP
        System.out.println("\n=== USERNAME ===");
        while (true) {
            
            System.out.print("Enter Username (must contain an underscore('_') and less than 5 characters): ");
            username = input.nextLine();

            Login tempUser = new Login(username, "", "", firstName, lastName);

            if (tempUser.checkUserName()) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }
        
        // PASSWORD LOOP
        System.out.println("\n=== PASSWORD ===");
        while (true) {
            
            System.out.print("Enter Password (must contain at least one special character,number and capital letter, and should be 8 characters minimum): ");
            password = input.nextLine();

            Login tempUser = new Login(username, password, "", firstName, lastName);

            if (tempUser.checkPasswordComplexity()) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
        
        // PHONE LOOP 
        System.out.println("\n=== PHONE NUMBER ===");
        while (true) {
           
            System.out.print("Enter Cell Phone (must start with +27...): ");
            phone = input.nextLine();

            Login tempUser = new Login(username, password, phone, firstName, lastName);

            if (tempUser.checkCellPhoneNumber()) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        
        // FINAL USER OBJECT
        Login user = new Login(username, password, phone, firstName, lastName);

        System.out.println("\nUser successfully registered.");

        
        // ================= LOGIN =================
        System.out.println("\n=== LOGIN ===");

        while (true) {

            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            boolean isValid = user.loginUser(loginUser, loginPassword);

            if (isValid) {
                System.out.println(user.returnLoginStatus(true));
                break;
            } else {
                System.out.println("Username or password incorrect, please try again." );
            }
        }
        
            
            // === PART 2 ===
        boolean isValidPart2 = true;
        // The boolean will be set to call upon loginUser().
        // Uses to existing method isValid = user.loginUser().
        if (isValidPart2) {
 
            boolean running = true;
            // Options will be under the while loop, for the user to choose from.
            while (running) {
                // Will handle all application loops as per option and message.
                System.out.println("\n1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("\nChoose an option: ");
                /* 
                *The int choice classification will a choosing 
                * feature for the user as they dicide what to choose 
                * on the option list.
                * The switch will enable the user to change optio n 
                * choice from previous selection.
                */
                int choice = input.nextInt();
                input.nextLine();
                
                // Switch directs execution to the correct case based on the user's choice.
                switch (choice) {

                    case 1:
                        // =====================================================
                        // PART 2 - SECTION 3: MESSAGE COUNT AND FOR LOOP
                        // Ask how many messages the user wants to send,
                        // then loop exactly that many times using a for loop.
                        // =====================================================
                        System.out.println("\nHow many messages would you like to send?");
                        System.out.print("Number of messages: ");
                        int numMessages = input.nextInt();
                        input.nextLine(); // Clear the newline left by nextInt().
                        // will read the input the user chooses.
                        // The array will initailise the user selection choices based on what they want to do.
                        // The for loop runs once per message.
                        // 'i' starts at 0 but messageNumber is i+1 so it displays as 1, 2, 3...
                        for (int i = 0; i < numMessages; i++) {
                            // messageNumber is the human-readable counter shown to the user.
                            // It also gets passed to the Message constructor to generate the hash.
                            int messageNumber = i + 1;

                            System.out.println("\n=== Message " + messageNumber + " ===");
                            // =====================================================
                            // PART 2 - SECTION 4: RECIPIENT VALIDATION WHILE LOOP
                            // Keeps re-prompting until the recipient number is valid.
                            // Must start with +27 and be exactly 12 characters long.
                            // 'recipient' is declared outside the loop so it is accessible
                            // when the Message object is created after the loop ends.
                            // =====================================================
                            String recipient;
                            while (true) {
                                System.out.println("Enter recipient number:");
                                recipient = input.nextLine();
                                
                                // A temporary Message object is created with a placeholder text
                                // purely to call checkRecipientCell() for validation.
                                // This reuses the existing validation logic in Message.java
                                // without duplicating any code (as recommended by the guide).
                                Message tempMsg = new Message(messageNumber, recipient, "placeholder");
                                
                                if (tempMsg.checkRecipientCell().equals("Cell phone number successfully captured.")) {
                                    // Recipient number is valid - confirm and break out of the loop.
                                    System.out.println("Cell phone number successfully captured.");
                                    break;
                                } else {
                                    // Recipient number is invalid - print the error and loop again.
                                    System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.");
                                }
                            }
                            
                            // =====================================================
                            // PART 2 - SECTION 5: MESSAGE TEXT INPUT
                            // Collected after a valid recipient is confirmed.
                            // The Message object is then created with all three fields.
                            // =====================================================
                            System.out.println("\nEnter message:");
                            String text = input.nextLine(); 
                            // Create the final Message object with the validated recipient,
                            // message text, and message number (used for the hash).
                            // The constructor automatically generates the messageID and messageHash.
                            Message msg = new Message(messageNumber, recipient, text);
                            // Display the message length validation result.
                            // Returns "Message ready to send." if within 250 characters,
                            // or an error with the character count if it exceeds the limit.
                            System.out.println(msg.checkMessageLength());
                            // Only proceed to send options if the message is within the 250-character limit.
                            // The condition must match the exact string returned by checkMessageLength()
                            if (msg.checkMessageLength().equals("Message ready to send.")) {
                                // sentMessage() presents a sub-menu: Send, Disregard, or Store.
                                // It returns a String describing what action was taken.
                                System.out.println(msg.sentMessage());
                                // Add the message to the session list so it can be
                                // displayed when the user selects option 2.
                                messageList.add(msg);
                                // Display the full message details in the required order:
                                // Message ID, Message Hash, Recipient, Message text.
                                msg.printMessageDetails();
                            }
                            
                        }
                        break;

                    case 2:
                        // Feature not yet implemented. Placeholder output as required by the POE.
                        System.out.println("\nComing Soon.");
                        break;

                    case 3:
                        // User chose to quit. Set running to false so the while loop exits
                        // after this switch block completes.
                        System.out.println("\nQuiting Application......");
                        running = false;
                        break;

                    default:
                        // Handles any input that is not 1, 2, or 3.
                        // The loop continues so the menu is shown again immediately.
                        System.out.println("\nInvalid menu option. Please choose 1, 2, or 3.");
                }
            }
        } else {
            // Login gate failed - this branch executes if isValidPart2 is false.
            // In a complete implementation, this would prevent unauthenticated access.
            System.out.println("\nLogin failed.");
        }
    }       
}