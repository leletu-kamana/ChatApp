# Chat Application - Part 2 & 3

---

## Student Information
- Name: Leletu Kamana
- Student Number: ST10514888
- Module: PROG5121

---

## Project Information
- This project is focused on building the foundations of registration, login, and messaging systems for a simple chat application. I will develop a **Java with Maven Project in NetBeans** that centers around secure user  authentication and a strict input validation.

---

## Features
### Create Message Hash:
- Should contain a **first and last words**.
- Should consist of **a retrived message number**.
- Should contain **retrieved 2 chars from message ID**.

### Generate Message ID:
- Must contain exactly **10 characters long(>=10)**.
- Must be **generated at random** for each message sent.

### Recipient Cell Phone Number:
- Must and should be an **South African international code(+27...)**.
- Must contain exactly **12 characters**.

### Local JSON Database Persistence:
- Must convert message objects into dynamic JSON elements and serialize them to local file storage (Message.json) in append mode.
- Must read and rebuild runtime parallel lists line-by-line automatically at application startup to avoid session data loss.

### Advanced Message Querying & Data Lifecycle Management:
- Must search across all active structures using multi-criteria options such as locating specific message detail blocks by Message ID or listing multiple records by Recipient.
- Must search live arrays via a target unique cryptographic hash string and successfully isolate and drop matching indices to support precise message deletions.

### Metric Evaluation & Activity Auditing Reports:
- Must scan stored records to compute string character lengths, isolating and reporting the absolute longest text file entries back to the console interface.
- Must process session parallel arrays dynamically to render structured operational audit reports containing complete lifecycle summaries (Hashes, IDs, Recipients, Body texts, and System states).

---

## Difficulties Faced
- I struggled with writing the syntax in the IDE as I have not mastered **arrays** yet.
- The "**MessageTest**" struggles were extreme as I had to repeat the code over 10 times to make it work and give the desired results.
- Adding on **MainApp**, the array syntax proved to be difficult as the array had been looping infinitely until the desired result was achieved.
- Managing multiple parallel static lists across runtime instances created tracking mismatches, which required designing a strict sequential index synchronizer during add and remove operations.
- Handling external file streams within JSON serialization structures introduced exceptions that required robust error handling boundaries to avoid application crashes when reading missing databases.
  
---
