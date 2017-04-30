package hotel;

import java.io.*;
import java.util.*;



public class Hotel {
	static Scanner input = new Scanner(System.in); //Scanner object made global
	static String[] hotel = new String[10]; //Declaring a static hotel array of size 10

	static int roomNum = 0;//roomNum variable made static
	static String customerName;//customer name made static
	static boolean flag;//boolean variable made static

	public static void main(String[] args) {
		System.out.println(" ---------------------------------------");
		System.out.println(" |********-Welcome to Taj Hotel-********|");
		System.out.println(" ----------------------------------------\n");

		initialize();//directed to initialize method
		displayLogin();//directed to login method
	}
	
	/*
	 *The value e is assigned to the array
	 */
	private static void initialize() {//private method to protect data
		for (int i = 0; i < hotel.length; i++) {
			hotel[i] = "e";//value e is assigned to hotel array within the loop 
		}
	}
	
	/* 
	 *To confirm the user login 
	 */
	private static void displayLogin() {//private method to protect data
		System.out.println("\n****** Login Menu ******");
		Scanner loginInput = new Scanner(System.in);
		System.out.print("Enter Username: ");
		String userName = loginInput.next();
		System.out.print("Enter Password: ");
		String password = loginInput.next();
		if (userName.equals("admin") && password.equals("admin123")) {//if userName and password equals
			//then display menu
			displayMenu();//directed to display menu method
		} else {//else display error message
			System.out.println("\n#Incorrect Username or Password, Please Try Again#");
			displayLogin();//login menu prompted again
		}
		loginInput.close();
	}
	
	/*
	 *Used to navigate into different methods
	 */
	private static void displayMenu() {
		while (true) {//run the menu continuously until the program is stopped (infinite loop)
			System.out.println("\n*******--  Menu --*******\n");
			System.out.println("Enter V: View All Rooms");
			System.out.println("Enter A: Add Customer to rooms");
			System.out.println("Enter E: Display Empty Rooms");
			System.out.println("Enter D: Delete Customer from Room");
			System.out.println("Enter F: Find Room from Customer Name");
			System.out.println("Enter S: Store program Array Data into a plain Text File");
			System.out.println("Enter L: Load program Data back from the File into the Array");
			System.out.println("Enter O: View Rooms Ordered Alphabetically by Name");
			System.out.print("Enter Q: Quit the Program :  ");

			String userInput = input.next();//prompted for input and stored in the userInput variable
			System.out.println();

			switch (userInput.toLowerCase()) {
			case "v":
				viewAllRooms();//directed to viewAll rooms method
				break;
			case "a":
				addCustomers();//directed to addCustomer method

				break;
			case "e":
				displayEmptyRooms();//directed to emptyRooms method

				break;
			case "d":
				deleteCustomer();//directed to deleteCustomer method
				if (!flag) {//validates whether customer name not deleted if true the condition gets executed
					System.out.println("Customer Name Not Found, Try Again\n");
				}
				break;
			case "f":
				findRoom();//directed to findRoom method
				if (!flag) {//if customer room not found by name the following condition will be executed
					System.out.println("Customer Name Not Found, Try Again\n");
				}
				break;
			case "s":
				storeValue();//directed to storeValue method
				break;
			case "l":
				readTextFile();//directed to readTextFile method
				break;
			case "o":
				firstSort();//directed to firstSort method
				break;
			case "q":
				System.exit(0);//terminates the program
				break;
			default://if a invalid input is entered the following output will be displayed
				System.err.print("\nPlease, Enter a Valid Input: ");
				break;
			}
		}
	}

	/*
	 *To view the rooms which are empty and to view rooms
	 *which are occupied by customers
	 */
	private static void viewAllRooms() {//private method to protect data
		int roomPositive = 1;//1 assigned to roomPositive variable
		for (int i = 0; i < hotel.length; i++) {
			if (hotel[i].equals("e")) {//if hotel at index i is equals to e the following line will be displayed
				System.out.println("Room " + (i + roomPositive) + " is Empty");
			} else {//else the the value at hotel index i will be displayed
				System.out.println("Room " + (i + roomPositive) + " is Occupied by " + hotel[i]);
			}
		}
		System.out.println();//line break
	}
	/*
	 *Add customer based on the room number to the array
	 */
	private static void addCustomers() {//private method to protect data
		int roomNegative = -1, roomPositive = 1;//integer variables declared and assigned values
		System.out.println("-----Add Customers-----\n");

		while (true) {//infinite loop
			System.out.print("\nEnter Room Number (1-10): ");
			while (!input.hasNextInt()) {
				System.err.print("Please, Enter a integer between (1-10) for Room No : ");
				input.next();// validating user input based on string value
			}
			roomNum = input.nextInt() + roomNegative;//user input + roomNegative value assigned
			if (roomNum > 9 || roomNum < 0) {//validating user input between (1-10)
				System.out.println("#Please, Enter a integer between (1-10)#");
				return;//if roomNum greater than 9 or less then 0 then returned to the displayMenu
			}

			System.out.print("Enter Customer Name for Room " + (roomNum + roomPositive) + " : ");//prompt for customer name
			customerName = input.next();//assign user input to customerName
			hotel[roomNum] = customerName;//customer name assigned to hotel array at index roomNum
			
			viewAllRooms();//directed to viewAllRooms in order to display currently added customer

			System.out.print("\nEnter (c) to Continue or (B) to go back to Main Menu: ");
			String confirmInput = input.next().toLowerCase();//user input converted to lower case and assigned to confirmInput
			if (confirmInput.equals("b")) {
				return;//return to displayMenu method
			} else if (confirmInput.equals("c")) {
				continue;//continue to add customers
			} else {
				while (true) {//infinite loop
					System.err.print("Please, Enter (c) to Continue or (B) to go back to Main Menu: ");//prompt message
					confirmInput = input.next();//assign user input value to confirm input
					if (confirmInput.equals("c")) {
						break;//continue to add customers
					} else if (confirmInput.equals("b")) {
						return;//return to displayMenu method
					}
				}
			}
		}
	}
	
	/*
	 *Display rooms which are currently Empty
	 */
	private static void displayEmptyRooms() {//private method to protect data
		int roomPositive = 1;//1 assigned to roomPositive variable
		System.out.println("-----Empty Rooms-----\n");
		for (int x = 0; x < hotel.length; x++) {
			if (hotel[x].equals("e")) {//if hotel array at index x equals to e then display the following line
				System.out.println("Room " + (x + roomPositive) + " is Empty");
			}
		}
		System.out.println();//line break
	}
	
	/*
	 *read text file and store data to array
	 */
	private static void readTextFile() {//private method to protect data
		try {
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/User/Desktop/Hotel.txt"));
			//bufferedReader object made to read the text file
			String line = "";// line variable will hold the value which was read from the file file by line
			int counter = 0;// The counter will keep track of how many lines we have already read
			
			/* Read in a line from the file and store it in "line".
          The counter prevents us from reading in too many lines.*/
			while (((line = reader.readLine()) != null)) {//if the text file is not null the loop will get executed
				hotel[counter] = line;//line value will be assigned to the hotel array at index counter
				counter++;//increment counter
			}
			reader.close();//reader should be closed in order to read the file
		} catch (Exception ex) {//if file not found
			System.err.println("File not Found");
		}
		viewAllRooms();//directed to viewAllRooms method in order to view the data retrieved from the text file
		System.out.println();//line break
	}
	
	/*
	 *Store Array data to text File 
	 */
	private static void storeValue() {//private method to protect data
		
		File file = new File("C:/Users/User/Desktop/Hotel.txt");//create file object and specify the path of the file
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			//creating a Print Writer Object to write to the file
			fw = new FileWriter(file, false);//over writing
			pw = new PrintWriter(fw, false);//over writing

			for (int i = 0; i < hotel.length; i++) {
				pw.println(hotel[i]);//print the hotel array at index i to the file
			}
			System.out.println("#Sucessfully Saved to Text File#\n");
		} catch (FileNotFoundException e) {
			System.err.println("#File not found please check the file#\n");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("#Something wrong with IO please check#\n");
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}
	
	/*
	 *Replacing customer name from the array by assigning e to the current location 
	 */
	private static void deleteCustomer() {//private method to protect data
		flag = false;//flag variable to check whether array value deleted or not
		System.out.println("-----Delete Customer-----\n");
		System.out.print("Enter Customer Name to Delete from Room: ");
		String customerName = input.next();//user input value assigned to customer variable
		for (int x = 0; x < hotel.length; x++) {
			if (hotel[x].equalsIgnoreCase(customerName)) {//if hotel at index x equals to customer name
				hotel[x] = "e";//replace customer name with e at hotel index x
				System.out.println("#Customer Successfully Deleted from Room#\n");
				flag = true;//if delete successful boolean flag is assigned true
			}
		}
	}
	
	/*
	 *Searching by customer name and returning the room no 
	 */
	private static void findRoom() {//private method to protect data
		int roomPositive = 1;//1 assigned to roomPositive variable
		flag = false;//flag variable to check whether array value found or not

		System.out.println("-----Find Rooms-----\n");
		System.out.print("Enter Customer Name to Find the following Room: ");
		String customerName = input.next();//user input value assigned to customer variable
		int roomNo;//roomNo variable declared
		for (int x = 0; x < hotel.length; x++) {
			if (hotel[x].equalsIgnoreCase(customerName)) {//if hotel at index x equals to customerName
				roomNo = x;//assign x value to roomNo
				System.out.println(customerName + " is currently Occupied in Room " + (roomNo + roomPositive) + "\n");
				flag = true;//if search successful boolean flag is assigned true
				break;//to exit the loop once the index found
			}
		}
	}
	
	/*
	 *Concatenate the following string array and perform a alphabetical sort
	 *replace the following string array using the replace all method.
	 */
	private static void firstSort() {//private method to protect data
		int roomPositive = 1;//1 assigned to roomPositive variable
		String[] hotelTemp = new String[10];//declare hotelTemp array at size 10
		String[] roomNoTemp = new String[10];//declare roomNoTemp array at size 10

		System.out.println("-----Rooms Ordered Alphabetically-----\n");

		for (int j = 0; j < hotel.length; j++)
			hotelTemp[j] = hotel[j];//hotel array at index j values assigned to hotelTemp array at index j

		for (int i = 0; i < hotel.length; i++) {
			if (hotel[i].equals("e")) {//if hotel array at index i is equals to e 
				hotel[i] = "Empty" + (i + roomPositive);// assign empty + i + roomPositive to hotel array 
			} else {
				hotel[i] = (hotelTemp[i] + " " + (i + roomPositive));//else assign hotelTemp array at index i
			}		//value + i+roomPositive value to hotel array at index i
		}

		int i;//declaring variable
		boolean flag = true;//will determine when the sort is finished
		String temp;//declared string temp variable

		while (flag) {//infinite loop 
			flag = false;//false assigned to flag variable
			for (i = 0; i < hotel.length - 1; i++) {//
				if (hotel[i].compareToIgnoreCase(hotel[i + 1]) > 0) {
					temp = hotel[i];//ascending sort
					hotel[i] = hotel[i + 1];//swapping
					hotel[i + 1] = temp;
					flag = true;//true assigned to flag variable
				}
			}
		}

		String replace;//declaring variable 
		for (int x = 0; x < hotel.length; x++) {
			replace = hotel[x].replaceAll("[a-zA-Z\\s]", "");//replace the following in hotel array at index x and assign
			//the value to replace variable
			roomNoTemp[x] = replace;//assign replace to roomNoTemp array at index x
		}

		secondSort(roomNoTemp, hotelTemp);//directed to secondSort method by passing roomNoTemp
		//and hotelTemp reference
	}
	
	/*
	 *print the following sorted customer names and room Numbers
	 */
	private static void secondSort(String[] roomNoTemp, String[] hotelTemp) {//private method to protect data
		int i;//declaring variable
		boolean flag = true;//will determine when the sort is finished
		String temp;//declared string temp variable

		while (flag) {//infinite loop
			flag = false;//false assigned to flag variable
			for (i = 0; i < hotelTemp.length - 1; i++) {
				if (hotelTemp[i].compareToIgnoreCase(hotelTemp[i + 1]) > 0) {
					temp = hotelTemp[i];//ascending sort
					hotelTemp[i] = hotelTemp[i + 1];//swapping
					hotelTemp[i + 1] = temp;
					flag = true;//true assigned to flag variable
				}
			}
		}

		for (int x = 0; x < hotelTemp.length; x++) {
			if (hotelTemp[x].equals("e")) {//if hotelTemp array at index x is equals to e 
				System.out.println("Room " + (roomNoTemp[x]) + " is Empty");//roomNoTemp at index x is displayed
			} else {//else roomNoTemp at index x + hotelTemp array at index x is displayed 
				System.out.println("Room " + (roomNoTemp[x]) + " is Occupied by " + hotelTemp[x]);
			}
		}
		hotel = hotelTemp;//hotelTemp reference assigned to hotel array
		System.out.println();//line break
		System.exit(0);//program terminated 
	}
}
