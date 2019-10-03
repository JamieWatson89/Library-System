//This program will allow a clerk to enter,update,delete and search for a book in the given database.
 
package sql;
 
import java.util.*;
import java.sql.*;
 
public class BookSystem {
    public static void main(String[] args){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db?useSSL=false", //Establish connection with database.
                "myuser", "xxxx"); Statement stmt = conn.createStatement();)
             
        {   boolean system = true;
            while(system = true){               // While loop to ensure program returns to main menu every time a task is completed.
            System.out.println("\nMenu\n");
            System.out.println("1.Enter Book" + "\n2.Update Book" + "\n3.Delete Book" + "\n4.Search Book" + "\n5.Exit"
                    + "\n6.Display list of books");         // Menu options given to user to select from.
            System.out.print("\nPlease choose number for option you would like from above menu:");
            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();               // User input saved under variable choice.
             
            if (choice == 1) {                          // input checked and if eqaul,function will be carried out.
                System.out.print("\nPlease enter new book info in format:(id,'title','author',qty) ");  // This block allows user to input new book to database.
                Scanner enter = new Scanner(System.in);
                String enterBook = enter.nextLine();
                String sqlInsert = "insert into books " + " values" + enterBook;
                int countInserted = stmt.executeUpdate(sqlInsert);
                System.out.println(countInserted + " new book added!\n");
            }
 
            if (choice == 2) {
                System.out.print("Please enter id of the book you want to update: ");   // This block allows user to update existing book details.
                Scanner update = new Scanner(System.in);
                int updateBook = update.nextInt();
 
                System.out.print("Please enter what part of the book you would like to update:(id,title,author,qty) ");
                Scanner update2 = new Scanner(System.in);
                String updateBook2 = update2.nextLine();
                //Below an if else statement reviews user input and executes task  after comparison has been made.
                if (updateBook2.equals("id")) {
                    System.out.print("Please enter new id for selected book: ");
                    Scanner newId = new Scanner(System.in);
                    int id = newId.nextInt();
                    String idNew = "Update books set id = " + id + " where id = " + updateBook;
                    int countInserted = stmt.executeUpdate(idNew);
                    System.out.println("Book succesfully updated.");
                } else if (updateBook2.equals("title")) {
                    System.out.print("Please enter new title for selected book: ");
                    Scanner newTitle = new Scanner(System.in);
                    String title = newTitle.nextLine();
                    String titleNew = "Update books set title = '" + title + "' where id = " + updateBook;
                    int countInserted = stmt.executeUpdate(titleNew);
                    System.out.println("Book succesfully updated.");
                } else if (updateBook2.equals("author")) {
                    System.out.print("Please enter new author for selected book: ");
                    Scanner newAuthor = new Scanner(System.in);
                    String author = newAuthor.nextLine();
                    String authorNew = "Update books set author = '" + author + "' where id = " + updateBook;
                    int countInserted = stmt.executeUpdate(authorNew);
                    System.out.println("Book succesfully updated.");
                } else if (updateBook2.equals("qty")) {
                    System.out.print("Please enter new qty for selected book: ");
                    Scanner newQty = new Scanner(System.in);
                    int qty = newQty.nextInt();
                    String qtyNew = "Update books set qty = " + qty + " where id = " + updateBook;
                    int countInserted = stmt.executeUpdate(qtyNew);
                    System.out.println("Book succesfully updated.");
                }
            }
            //The next block of code takes user input a deletes corresponding book with id equal to input .
            if (choice == 3) {                  
                System.out.print("Please enter id of the book you want to delete: ");
                Scanner delete = new Scanner(System.in);
                int deleteBook = delete.nextInt();
                String dBook = "Delete from books where id = " + deleteBook;
                int countInserted = stmt.executeUpdate(dBook);
                System.out.println("Book succesfully deleted.");
            }
            //The next block of code takes user input in form of the book id and then searches and displays requested book data.
            if (choice == 4) {
                System.out.print("Please enter id of the book you want to search for: ");
                Scanner search = new Scanner(System.in);
                int searchBook = search.nextInt();
                String searchQuery = "select * from books where id = " + searchBook;
                ResultSet rsetSearch = stmt.executeQuery(searchQuery);
                System.out.println("Your searched book is: \n" + "ID\t\tTitle\t\t  Author\tQty\n");
                while (rsetSearch.next()) {
                    System.out.println(rsetSearch.getInt("ID") + ", " + rsetSearch.getString("TITLE") + ", "
                            + rsetSearch.getString("AUTHOR") + ", " + rsetSearch.getInt("QTY"));
                }
 
            }
            //The next block of code terminates the program if user input equal to option on menu.
            if (choice == 5) {
                System.out.println("Bye");
                break;
                 
                 
 
            }
            //This next block of code allows the user to view all books in database to obtain id of book for reference.
            if (choice == 6) {
                String strSelect = "select* from books";
                System.out.println("\nThe List of books are: ");
                ResultSet rset = stmt.executeQuery(strSelect);
 
                while (rset.next()) {
                    System.out.println(rset.getInt("ID") + ", " + rset.getString("TITLE") + ", "
                            + rset.getString("AUTHOR") + ", " + rset.getInt("QTY"));
                }
            }
            if (choice > 6) {
                System.out.print("\nOption not on menu, please try again.\n");}     //If user option out of bounds a prompt is given.
             
             
        }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
