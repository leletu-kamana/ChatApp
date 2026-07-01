/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import com.mycompany.chatapp.Message;
import com.mycompany.chatapp.Login;
import com.mycompany.chatapp.Message;
import static com.mycompany.chatapp.Message.getLoadStoredMessages;
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
        System.out.println("                WELCOME TO QUICKCHAT");
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
        // Display the header for the login section
        System.out.println("\n=== LOGIN ===");

        // Track the number of failed attempts and the overall login status
        int loginAttempts = 0;
        boolean loginSuccess = false;

        // Loop allows the user up to 3 attempts to log in successfully
        while (loginAttempts < 3) {

            // Prompt for and capture the username input
            System.out.print("Enter Username: ");
            String loginUser = input.nextLine();

            // Prompt for and capture the password input
            System.out.print("Enter Password: ");
            String loginPassword = input.nextLine();

            // Authenticate the provided credentials against the user object
            boolean isValid = user.loginUser(loginUser, loginPassword);

            // If credentials are valid, handle successful login sequence
            if (isValid) {
                System.out.println(user.returnLoginStatus(true));
                loginSuccess = true;
                break; // Exit the loop immediately upon successful authentication
            } else {
                // Increment the counter if authentication fails
                loginAttempts++;

                System.out.println("Username or password incorrect, please try again.");

                // If the user has remaining attempts, display the countdown
                if (loginAttempts < 3) {
                    System.out.println("Attempts remaining: " + (3 - loginAttempts));
                }
            }
        }
        
        // If all 3 attempts fail
        if (!loginSuccess) {

            // Display a formatted menu informing the user they ran out of login attempts
            System.out.println("\n=================================================");
            System.out.println("Maximum login attempts reached (3).");
            System.out.println("1) Return to Registration");
            System.out.println("2) Exit Program");
            System.out.println("=================================================");
            System.out.print("Choose option: ");

            // Read the user's numeric choice and clear the scanner's buffer newline character
            int failedChoice = input.nextInt();
            input.nextLine();

            // Evaluate the user's menu choice to determine the next application state
            switch (failedChoice) {

                // Case 1: User chose to go back to the registration screen
                case 1:
                    System.out.println("Returning to registration...");
                    main(args); // restart application by recursively invoking the main method
                    return; // Exit the current main method execution branch

                // Case 2: User explicitly chose to stop running the application
                case 2:
                    System.out.println("Application terminated.");
                    System.exit(0); // Safely shut down the JVM with a standard success status code
                    break;

                // Default Case: Handles any unexpected input numerical values by shutting down safely
                default:
                    System.out.println("Invalid option. Application terminated.");
                    System.exit(0);
            }
        }
        
        // Load stored messages from the JSON file after successful login
        Message.loadStoredMessages();
        
            
            // === PART 2 ===
        boolean isValidPart2 = true;
        // The boolean will be set to call upon loginUser().
        // Uses to existing method isValid = user.loginUser().
        if (isValidPart2) {
 
            boolean running = true;
            // Options will be under the while loop, for the user to choose from.
            while (running) {
                
                // Will handle all application loops as per option and message.
                System.out.println("\n========== MAIN MENU ==========");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.println("4) Stored Messages Menu");
                System.out.println("===============================");
                System.out.print("\nChoose an MENU option: ");
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
                        System.out.println("========================================");
                        System.out.println("\nHow many messages would you like to send?");
                        System.out.print("Number of messages: ");
                        int numMessages = input.nextInt();
                        input.nextLine(); 
                        System.out.println("=========================================");
                        // Clear the newline left by nextInt().
                        // will read the input the user chooses.
                        // The array will initailise the user selection choices based on what they want to do.
                        // The for loop runs once per message.
                        // 'i' starts at 0 but messageNumber is i+1 so it displays as 1, 2, 3...
                        for (int i = 0; i < numMessages; i++) {
                            // messageNumber is the human-readable counter shown to the user.
                            // It also gets passed to the Message constructor to generate the hash.
                            int messageNumber = i + 1;

                            System.out.println("\n========= Message " + messageNumber + " ===========");
                            // =====================================================
                            // PART 2 - SECTION 4: RECIPIENT VALIDATION WHILE LOOP
                            // Keeps re-prompting until the recipient number is valid.
                            // Must start with +27 and be exactly 12 characters long.
                            // 'recipient' is declared outside the loop so it is accessible
                            // when the Message object is created after the loop ends.
                            // =====================================================
                            String recipient;
                            while (true) {
                                System.out.print("Enter recipient number: ");
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
                             System.out.println("----------------------------------------------");
                            // =====================================================
                            // PART 2 - SECTION 5: MESSAGE TEXT INPUT
                            // Collected after a valid recipient is confirmed.
                            // The Message object is then created with all three fields.
                            // =====================================================
                            System.out.print("Enter message: ");
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
                                // Displays whether the meessage is sent,disregarded or stored
                                // Is decided using a switch system manipulations
                                System.out.println("Message Status: " + msg.getMessageStatus());
                            }   
                        }
                        System.out.println("Total messages processed: " + numMessages);
                        System.out.println("====================================================");
                        break;

                    case 2:
                        // Show all sent messages from this session using the report method
                        // Print status for each Message object tracked in messageList
                        System.out.println("===================================================");
                        System.out.println(Message.printMessages());
                        
                        // Display status for each message in the session
                        if (!messageList.isEmpty()) {
                            System.out.println("\n=== Message Status Summary ===");
                            for (int i = 0; i < messageList.size(); i++) {
                                Message msg = messageList.get(i);
                                System.out.println("Message " + (i + 1) + " Status: " + msg.getMessageStatus());
                            }
                        }
                        break;

                    case 3:
                        // User chose to quit. Set running to false so the while loop exits
                        // after this switch block completes.
                        System.out.println("\n Goodbye " + firstName +" " + lastName + ";Quiting Application......");
                        isValidPart2 = false;
                        running = false;
                        break;
                        
                   case 4:
                       // PART 3 - Launch the stored messages sub-menu

                        // =========================================================
                        // PART 3 - STORED MESSAGES SUB-MENU
                        // Launches when the user selects option 4 from the main menu.
                        // Offers six features using the data stored in the parallel arrays.
                        // Each feature calls a method in Message.java - MainApp stays clean.
                        // =========================================================

                        /**
                         * Displays the stored messages sub-menu and handles all six options.
                         * Loops until the user selects option g to return to the main menu.
                         *
                         * @param input the shared Scanner passed in from main()
                         */
                        boolean subRunning = true;

                        while (subRunning) {
                            System.out.println("\n============= STORED MESSAGES MENU ================");
                            System.out.println("a) Display all stored messages");
                            System.out.println("b) Display longest message");
                            System.out.println("c) Search by Message ID");
                            System.out.println("d) Search by Recipient");
                            System.out.println("e) Delete message by Hash");
                            System.out.println("f) Display full message report");
                            System.out.println("g) Return to main menu");
                            System.out.println("======================================================");
                            System.out.print("\nChoose an option: ");

                            String subChoice = input.nextLine().trim().toLowerCase();

                            switch (subChoice) {

                                case "a":
                                    // Display all stored messages loaded from the JSON file
                                    System.out.println(Message.getLoadStoredMessages());
                                    break;

                                case "b":
                                    // Find and display the longest stored message with full details
                                    System.out.println(Message.displayLongestMessage());
                                    
                                    break;
 
                                case "c":
                                    // Search for a message using its ID
                                    System.out.print("Enter Message ID to search: ");
                                    String searchID = input.nextLine().trim();
                                    System.out.println("\n=== Search Result ===");
                                    System.out.println(Message.searchByMessageID(searchID));
                                    System.out.println("----------------------------------------");
                                    break;

                                case "d":
                                    // Search for all messages sent to a given recipient
                                    System.out.print("Enter recipient number to search: ");
                                    String searchRecipient = input.nextLine().trim();
                                    System.out.println("\n=== Search Result ===");
                                    System.out.println(Message.searchByRecipient(searchRecipient));
                                    
                                    break;

                                case "e":
                                    // Delete a message using its hash
                                    System.out.print("Enter message hash to delete: ");
                                    String deleteHash = input.nextLine().trim();
                                    System.out.println("\n=== Delete Result ===");
                                    System.out.println(Message.deleteByMessageHash(deleteHash));
                                    System.out.println("----------------------------------------");
                                    break;

                                case "f":
                                    // Display the full formatted report of all sent messages
                                    System.out.println(Message.printMessages());
                                    break;

                                case "g":
                                    // Return to the main menu
                                    System.out.println("Returning to main menu...");
                                    subRunning = false;
                                    break;

                                default:
                                    System.out.println("Invalid option. Please choose a, b, c, d, e, f, or g.");
                            }
                        }
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

