/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
import com.mycompany.chatapp.Login;
/**
 *
 * @author Lelethu Kamana
 */
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {  
        
        //User information is to entered through Scanner.
        Scanner input = new Scanner(System.in);
        
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
    }
}