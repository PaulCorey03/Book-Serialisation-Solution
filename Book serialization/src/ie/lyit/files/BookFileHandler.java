package ie.lyit.files;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class BookFileHandler {
	// Constant FILENAME for the file to be created
	final String FILENAME = "books.bin";

	// Declare ArrayList called books - for storing a list of books
	private ArrayList<Book> books;
	

	// Default Constructor
	public BookFileHandler(){
		// Construct books ArrayList
		books = new ArrayList<Book>();
	}

	//////////////////////////////////////////////////////
	// Method Name : add()								//
	// Return Type : void								//
	// Parameters : None								//
	// Purpose : Reads one Book record from the user    //
	//           and adds it to the ArrayList books     //
	//////////////////////////////////////////////////////	
	public void add(){
		// Create a Book object
		Book book = new Book();
		// Read its details
		book.read();	
		// And add it to the books ArrayList
		books.add(book);
	}

	////////////////////////////////////////////////////////
	// Method Name : list()							      //
	// Return Type : void			  				      //
	// Parameters : None						 	      //
	// Purpose : Lists all Book records in the ArrayList  //
	////////////////////////////////////////////////////////	
	public void list(){
		// for every Book object in books
      for(Book tmpBook:books)
			// display it
			System.out.println(tmpBook);
	}
	
	////////////////////////////////////////////////////////////
	// Method Name : view()									  //
	// Return Type : void								      //
	// Parameters : None								      //
	// Purpose : Displays the required Book record on screen  //
	//         : And returns it, or null if not found         //   
	////////////////////////////////////////////////////////////	
	public Book view(){
		Scanner keyboard = new Scanner(System.in);		

		// Read the number of the book to be viewed from the user
		System.out.println("ENTER NUMBER OF BOOK : ");
		int bookToView=keyboard.nextInt();
		
		// for every Book object in books
	    for(Book tmpBook:books){
		   // if it's number equals the number of the bookToView
		   if(tmpBook.getLibraryNumber() == bookToView){
		      // display it
			  System.out.println(tmpBook);
			  return tmpBook;
		   }
		}
	    // if we reach this code the book was not found so return null
	    return null;		
	}

	///////////////////////////////////////////////////////////
	// Method Name : delete()								 //
	// Return Type : void									 //
	// Parameters : None									 //
	// Purpose : Deletes the required Book record from books //
	///////////////////////////////////////////////////////////	
	public void delete(){	
		// Call view() to find, display, & return the book to delete
		Book tempBook = view();
		// If the book != null, i.e. it was found then...
	    if(tempBook != null)
		   // ...remove it from books
	       books.remove(tempBook);
	}
	
	///////////////////////////////////////////////////////////
	// Method Name : edit()			  					     //
	// Return Type : void									 //
	// Parameters : None									 //
	// Purpose : Edits the required Book record in books     //
	///////////////////////////////////////////////////////////	
	public void edit(){	
		// Call view() to find, display, & return the book to edit
		Book tempBook = view();
		// If the book != null, i.e. it was found then...
	    if(tempBook != null){
		   // get it's index
		   int index=books.indexOf(tempBook);
		   // read in a new book and...
		   tempBook.read();
		   // reset the object in books
		   books.set(index, tempBook);
	    }
	}
	
	///////////////////////////////////////////////////////
	// Method Name : writeRecordsToFile()    			 //
	// Return Type : void								 //
	// Parameters : None								 //
	// Purpose : Writes the ArrayList books to the       //
	//		     File Books.bin before closing the File  //
	///////////////////////////////////////////////////////	
	public void writeRecordsToFile(){
		try{
			// Serialize the ArrayList...
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
	
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
	
			os.writeObject(books);
	
			os.close();
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot create file to store books.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	///////////////////////////////////////////////////////
	// Method Name : readRecordsFromFile()    			 //
	// Return Type : void								 //
	// Parameters : None								 //
	// Purpose : Reads the ArrayList from the File back  //
	//		     into books before closing the File      //
	///////////////////////////////////////////////////////	
	public void readRecordsFromFile(){
		try{
			// Deserialize the ArrayList...
			FileInputStream fis = new FileInputStream(FILENAME);
			
			ObjectInputStream is = new ObjectInputStream(fis);

			// COULD use code below to ensure it is an ArrayList
			// BUT no need-we are confident file contains an ArrayList
			// Object o = is.readObject(); 	// READ an object from the file
			// if(o instanceof ArrayList)  	// IF object is an ArrayList
			//    books=(ArrayList<Book>)o;//    ASSIGN object to books			
			books = (ArrayList<Book>)is.readObject();

			is.close();
		}
		catch(FileNotFoundException fNFE){
			System.out.println("Cannot find books file.");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}				
	}	

}
