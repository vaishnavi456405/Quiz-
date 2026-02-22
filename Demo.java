package com.gqt;

import java.util.*;

//Book Class
class Book {
 private int bookId;
 private String title;
 private boolean issued;

 // Constructor
 public Book(int bookId, String title) {
     this.bookId = bookId;
     this.title = title;
     this.issued = false; // default not issued
 }

 public int getBookId() {
     return bookId;
 }

 public String getTitle() {
     return title;
 }

 public boolean isIssued() {   // ✅ This method was missing
     return issued;
 }

 public void setIssued(boolean issued) {
     this.issued = issued;
 }
}

//Library Class
class Library {
 private List<Book> books = new ArrayList<>();

 public void addBook(Book book) {
     books.add(book);
     System.out.println("Book Added Successfully!");
 }

 public void viewBooks() {
     for (Book b : books) {
         System.out.println("ID: " + b.getBookId() +
                 ", Title: " + b.getTitle() +
                 ", Issued: " + b.isIssued());
     }
 }

 public void issueBook(int bookId) {
     for (Book b : books) {
         if (b.getBookId() == bookId && !b.isIssued()) {
             b.setIssued(true);
             System.out.println("Book Issued Successfully!");
             return;
         }
     }
     System.out.println("Book not available or already issued.");
 }
}

//Main Class
public class Demo {
 public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
     Library library = new Library();

     while (true) {
         System.out.println("\n1. Add Book");
         System.out.println("2. View Books");
         System.out.println("3. Issue Book");
         System.out.println("4. Exit");

         int choice = sc.nextInt();

         switch (choice) {
             case 1:
                 System.out.print("Enter Book ID: ");
                 int id = sc.nextInt();
                 sc.nextLine(); // consume newline
                 System.out.print("Enter Book Title: ");
                 String title = sc.nextLine();
                 library.addBook(new Book(id, title));
                 break;

             case 2:
                 library.viewBooks();
                 break;

             case 3:
                 System.out.print("Enter Book ID to Issue: ");
                 int issueId = sc.nextInt();
                 library.issueBook(issueId);
                 break;

             case 4:
                 System.out.println("Exiting...");
                 return;

             default:
                 System.out.println("Invalid Choice!");
         }
     }
 }
}