/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author Lelethu Kamana
 */
public class Login {
  // Variables are being stored are the  user's datails.
    String username;
    String password;
    String cellPhoneNumber;
    String firstName;
    String lastName;
    
     public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }
     
    // The boolean check of the users username to ensure it consists of
    // An "_" and no more than 5 characters long.
    public boolean checkUserName() {
        // Must contain underscore("_") as a username requirement
        // Must cosists of <=5 at length of username 
        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    } 
     
    // Boolean characteristics required for password Complexity.
    public boolean checkPasswordComplexity() {
        // Boolean for incorrect entries. 
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        // Password is to be looped passwords is incorrect.
        for (int i = 0; i < password.length(); i++) {
           // Char requirement for password.
            char c = password.charAt(i);
            
           // Char is to be looped to meet requirements for password.
           if (Character.isUpperCase(c)) {
            hasCapital = true; // To state if the Capital Letter Requirement is meet. 
            } else if (Character.isDigit(c)) {
                hasNumber = true; // To state if the Number Requirement is meet.
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;  // To state if the Special Character is meet.
            } 
        }
        
        if (password.length() >=8 && hasCapital && hasNumber && hasSpecial) {
            return true;
        } else {
            return false;
        }
    }
    
    //Boolean characteristics required for CellPhone Number.
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() <= 12) {
            return true;
        } else {
            return false;
        }
    }
    
    //Registration for User Method.
    public String registerUser() {
        
        //Loop user back to username requirements if not meet by User. And indicates what user must do.
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } 
        //Loop user back to password requirements if not meet by User. And indicates what user must do.
         if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
         
        //Loop user back to Cell Phone Number requirements if not meet by User. And indicates what user must do.
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        
        return "User registered successfully."; 
    }
    
    // Login using with SAME details as registered with.
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
            return true;
        } else {
            return false;
        }
    }
    // Login Status when login is successful or incorrect details.
    public String returnLoginStatus(boolean success) {
        
        if (success) {
            return "Welcome " + username + "; it is great to see you again."; //To be stated when login is successful.
        } else {
            return "Username or password incorrect, please try again."; // To be stated when the login is incorrect.
        }
    }  
}
