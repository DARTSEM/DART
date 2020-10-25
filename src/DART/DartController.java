package DART;

import DART.enums.MembershipEnum;
import DART.enums.ProductType;
import DART.miscellaneous.Utilities;
import DART.models.*;
import DART.models.products.Album;
import DART.models.products.Game;
import DART.models.products.Product;
import DART.models.products.Rating;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


public class DartController {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static List<Product> products = new ArrayList<Product>();
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<Rental> rentals = new ArrayList<Rental>();
    public static HashMap<ArrayList<Integer>, Product> ratingsHash = new HashMap<>();
    public static ArrayList<Rating> rating = new ArrayList<>();


    public static void rentProduct(Customer customer, Product product, LocalDate rentDate) {
        Rental rental = new Rental(customer, product, rentDate);

        DartController.rentals.add(rental);
    }

    //update membership and credits, check if free w redeemable credits
    //allow but not require rating and review, included in item
    public static Double returnProduct(Rental rental, LocalDate returnDate) {
        rental.returnRental(returnDate);
        //returns total amount incurred
        return rental.totalRentFee();


    }

    //modify to reflect changes in architecture
    //returns all rentals associated with specific customer ID
    public static Collection<Rental> getRentalsForCustomer(Customer customer) {
        ArrayList<Rental> customerRentals = new ArrayList<>();
        for (Rental rental : DartController.rentals) {
            if (customer.getId().equals(rental.getCustomer().getId())) {
                customerRentals.add(rental);
            }
        }
        return customerRentals;
    }

    public static Product mostProfitable() {
        HashMap<Product, Double> map = new HashMap<>();
        for (Rental rental : DartController.rentals) {
            Product product = rental.getProduct();

            //gets value in collection, but if null then returns default (gets back 0 as current gross if not in the map)
            double currentGross = map.getOrDefault(product, 0.0);

            //sets product to be current gross plus the rental fee to what I am looking at
            map.put(product, currentGross + rental.totalRentFee()); //tallies up rental sum plus the rental we are
            // looking at
        }
        //contains all products rented and all fees ever charged in transaction history

        Product maxProduct = null;
        double maxValue = 0;

        //Map.entry iterates over what we already have in hashmap
        for(Map.Entry<Product, Double> entry : map.entrySet()) { //hash table (collection of map entries)
            //iterating over entries in map, looking at value for each product
            if (entry.getValue() > maxValue) { //store entry if greater than max value, otherwise it is skipped over
                maxProduct = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        //now maxValue contains largest value, and maxProduct contains the product that eared that value
        return maxProduct;
    }

    public static Customer bestCustomer() {
        HashMap<Customer, Double> map = new HashMap<>();
        for (Rental rental : DartController.rentals) {
            Customer customer = rental.getCustomer();

            //gets value in collection, but if null then returns default (gets back 0 as current gross if not in the map)
            double currentGross = map.getOrDefault(customer, 0.0);

            //sets customer to be current gross plus the rental fee to what I am looking at
            map.put(customer, currentGross + rental.totalRentFee()); //tallies up rental sum plus the rental we are
            // looking at
        }
        //contains all customers' rentals and all fees ever charged in transaction history

        Customer maxCustomer = null;
        double maxValue = 0;

        //Map.entry iterates over what we already have in hashmap
        for(Map.Entry<Customer, Double> entry : map.entrySet()) { //hash table (collection of map entries)
            //iterating over entries in map, looking at value for each product
            if (entry.getValue() > maxValue) { //store entry if greater than max value, otherwise it is skipped over
                maxCustomer = entry.getKey();
                maxValue = entry.getValue();
            }
        }
        //now maxValue contains largest value, and maxCustomer contains the customer that eared that value
        return maxCustomer;
    }

    public static void rentalFrequency() {
        HashMap<Product, Integer> map = new HashMap<>();
        for (Rental rental : DartController.rentals) {
            Product product = rental.getProduct();

            //gets value in collection, but if null then returns default (gets back 0 as current gross if not in the map)
            Integer currentCount = map.getOrDefault(product,0);

            //sets product to be current gross plus the rental fee to what I am looking at
            map.put(product, currentCount + 1); //tallies up reach instance of specific rental we are looking at
        }
        //contains all products rented and all fees ever charged in transaction history


        //Map.entry iterates over what we already have in hashmap
        for(Map.Entry<Product, Integer> entry : map.entrySet()) { //hash table (collection of map entries)
            //iterating over entries in map, looking at value for each product
            System.out.print("The rental frequency for '" + entry.getKey().getTitle() + "' is " + entry.getValue() + "\n");
        }
    }

    //modify to reflect changes in architecture
    // calculates total profit for all rentals
    public static Double getTotalProfit() {
        Double totalProfit = 0.0;
        for (Rental rental : DartController.rentals) {
            totalProfit += rental.totalRentFee();
        }
        return totalProfit;
    }

    //pass in enumeration albums, games, all
    //filters products by type
    //modify to reflect changes in architecture

    public DartController() {

    }

    public static void exampleCustomers() {
        Employee employee = new Employee();
        Customer example = new Customer("Drake Axelrod", "p", MembershipEnum.GOLD);
        Customer example2 = new Customer("Guy O'Hare", "p", MembershipEnum.SILVER);
        Customer example3 = new Customer("John McJohn", "p", MembershipEnum.BASIC);
        Customer example4 = new Customer("Edmonton Son of Daniel", "p", MembershipEnum.PLATINUM);
        Employee.addCustomer(example, customers);
        Employee.addCustomer(example2, customers);
        Employee.addCustomer(example3, customers);
        Employee.addCustomer(example4, customers);
    }

    public static void exampleProducts() {
        Employee employee = new Employee();
        Album exampleAlbum = new Album("Mongolian Thiccest Throat Remastered", "Khanis Ginger", 2000,
                200, true);
        Album exampleAlbum2 = new Album("The Fishe", "Lewis Mercy", 2000,
                50, true);
        Album exampleAlbum3 = new Album("Bruh", "Bruh Sound Effects", 2011,
                25.5, true);
        Employee.addAlbum(exampleAlbum, products);
        Employee.addAlbum(exampleAlbum2, products);
        Employee.addAlbum(exampleAlbum3, products);
        exampleAlbum.ratings.add(4);
        exampleAlbum2.ratings.add(3);
        exampleAlbum3.ratings.add(2);


        Game exampleGame = new Game("Abandoned 4 Demise 2", "Action", 2009, 112, true);
        Game example2Game = new Game("Groundrim", "RPG", 2011, 32, true);
        Game example3Game = new Game("Not Portal 2", "Puzzle", 2011, 4, true);
        Employee.addGame(exampleGame, products);
        Employee.addGame(example2Game, products);
        Employee.addGame(example3Game, products);
        exampleGame.ratings.add(3);
        exampleGame.ratings.add(1);
        exampleGame.ratings.add(4);
        example2Game.ratings.add(1);
        example2Game.ratings.add(3);
        example2Game.ratings.add(2);
        example3Game.ratings.add(5);
        example3Game.ratings.add(5);
        example3Game.ratings.add(2);
    }

    public static void exampleEmployees() {
        Manager manager = new Manager();
        Employee exampleEmployee = new Employee("Daniel", "McWorker", 2000,
                "Bruh Street", "Sideway Boulevard", 30000);
        Employee example2Employee = new Employee("Toshiba", "McWorker", 1970,
                "Dog Street", "Phone Property", 27500);
        Employee example3Employee = new Employee("Sven", "Painter", 1997,
                "Canvas Street", "", 21500);
        manager.addEmployee(exampleEmployee, employees);
        manager.addEmployee(example2Employee, employees);
        manager.addEmployee(example3Employee, employees);
    }


    public static int intInput(String message) {
        System.out.println(message);
        return Utilities.intInput();
    }

    public static double doubleInput(String message) {
        System.out.println(message);
        return Utilities.doubleInput();
    }
    public static double doubleInputNoNegative(String message, String error) {
        System.out.println(message);
        return Utilities.doubleInputNoNegative(error);
    }

    public static String stringInput(String message) {
        System.out.println(message);
        return Utilities.stringInput();
    }

    public static String stringInputNoEmpty(String message, String error) {
        System.out.println(message);
        return Utilities.stringInputNoEmpty(error);
    }
    // difference between these methods with the one in the Utilities is that they include a message.

    public static void render(String message) {
        System.out.println(message);
    }

    public static void renderSuccess(String message) {
        System.out.println(message);
    }

    public static void renderExit(String message) {
        System.out.println("Returning to previous menu.");
    }

    public static void renderError(String message) {
        System.out.println(message);
    }

    public static void mainMenuPrint() {
        System.out.println(Utilities.line() + "Main Menu:\n" +
                "Welcome to DART, your good old game rental system. The competition has no steam to keep up!\n" +
                "\n" +
                "Please specify your role by entering one of the options given:\n" +
                "1. Enter “M” for Manager \n" + //Password = "admin1234"
                "2. Enter “E” for Employee \n" + //Password = "password123"
                "3. Enter “C” for Customer\n" + // example customers password = "p"
                "4. Enter “X” to exit system \n");
    }

    public static void managerMenuPrint() {
        System.out.println(Utilities.line() + "Manager Screen - Type one of the options below:\n" +
                "1. Add an employee\n" +
                "2. View all employees\n" +
                "3. View all transactions\n" +
                "4. View most profitable product\n" +
                "5. View rental frequency\n" +
                "6. View best customer\n" +
                "7. Add from text file\n" +
                "8. Export rentals to text file\n" +
                "9. Return to Main Menu\n");
    }

    public static void employeeMenuPrint() {
        System.out.println(Utilities.line() + "Employee Screen - Type one of the options below:\n" +
                "1. Register a product\n" +
                "2. Register a customer\n" +
                "3. View or remove products\n" +
                "4. View, modify, or remove customers\n" +
                "5. Customer requests\n" +
                "6. Show total rent profit\n" +
                "7. Return to Main Menu\n");
    }

    public static void customerMenuPrint() {
        System.out.println(Utilities.line() + "Customer Screen - Type one of the options below:\n" +
                "1. Rent a game\n" +
                "2. Return a game\n" +
                "3. Upgrade Membership\n" +
                "4. View inbox and send messages\n" +
                "5. Search products\n" +
                "6. View products sorted by...\n" +
                "7. Return to Main Menu\n");
    }

    public static void customerMessagesPrint() {
        System.out.println(Utilities.line() + "Messages Screen - Type one of the options below:\n" +
                "1. Send a message\n" +
                "2. View messages\n" +
                "3. Delete messages\n" +
                "4. Return to Customer Menu\n");
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Initializing DART . . .\n");
        exampleProducts();
        exampleEmployees();
        exampleCustomers();
        Collections.sort(products); // sorts all alphabetically
        Collections.sort(customers);
        Collections.sort(employees);
        mainMethod();
    }

    public static void mainMethod() throws FileNotFoundException {


        mainMenuPrint();

        String value = stringInput("");
        value = value.toUpperCase(); // converts all letters in the string to uppercase
        // System.out.println("Here"); dunno why this is here
        int index;
        do {
            switch (value) {
                case "M" -> {
                    String mPassword;
                    do {
                        System.out.println("Please input your password, or press 3 to go back to the main menu:");
                        mPassword = Utilities.stringInput();

                        if (mPassword.equals("admin1234")) {
                            renderSuccess("Successfully logged in.");
                        } else if (mPassword.equals("3")) {
                            mainMethod();
                        } else {
                            System.out.println("Wrong password!");
                        }
                    } while (!mPassword.equals("admin1234"));

                    do {
                        managerMenuPrint();
                        Manager manager = new Manager();
                        int option = intInput("");
                        switch (option) {
                            case 1 -> {
                                String firstName = stringInputNoEmpty("Please enter the employee's first name: ", "Employee name cannot be empty.");
                                String lastName = stringInputNoEmpty("Please enter the employee's last name: ", "Employee name cannot be empty.");
                                int birthYear = intInput("Please enter the birth year of the employee");
                                String address1 = stringInput("Please enter the address 1 of the employee");
                                String address2 = stringInput("Please enter the address 2 of the employee");
                                double salary = doubleInputNoNegative("Please enter the salary", "Employee salary cannot be negative.");

                                Employee e = new Employee(firstName, lastName, birthYear, address1, address2, salary);

                                manager.addEmployee(e, employees);
                                renderSuccess("Employee successfully added!");
                            }
                            case 2 -> {
                                manager.printAllEmployees(employees);
                                int option2 = intInput("1. Remove an employee");
                                switch (option2) {
                                    case 1 -> {
                                        String uuid = stringInput("Please enter the ID of the employee you want to " +
                                                "remove:");
                                        manager.removeEmployee(uuid, employees);
                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 3 -> {
                                //rent history
                                render("All transactions of all products:\n");
                                manager.printAllRentals(rentals);
                            }
                            case 4 -> {
                                //most profitable item
                                render("The most profitable product is:\n" + mostProfitable());

                            }
                            case 5 -> {
                                //rent frequency of all items
                                render("Displaying the frequency of products that have been rented:\n");
                                rentalFrequency();
                            }
                            case 6 -> {
                                //retrieve best customer
                                render("Here is your best customer:\n" + bestCustomer());
                            }
                            case 7 -> {
                                Scanner input = new Scanner(new File("src/Stock.txt"));
                                input.useDelimiter(";");

                                while (input.hasNext()) { //this basically keeps going through stock.txt until it runs out of text
                                    String inputType = input.nextLine();
                                    String[] inputSplitter = inputType.split(";"); //this is awesome! it basically splits the input
                                    //using the provided splitter into different strings!
                                    if (inputType.contains("Game")) {                  // i would've used enums for game and album,
                                        // but wanted to keep the code consistent with employee and customer, which arent enums.
                                        String title = inputSplitter[1];
                                        String genre = inputSplitter[2];
                                        Double dailyRentFee = Double.parseDouble(inputSplitter[3]); //since inputSplitter can only divide a string, we turn this from a string to a double.
                                        int releaseYear = Integer.parseInt(inputSplitter[4]); //same as above but with int.

                                        Game g = new Game(title, genre, releaseYear, dailyRentFee, true); //a game is always available upon release
                                        Employee.addGame(g, products);
                                        System.out.println(g);

                                    } else if (inputType.contains("Album")) {
                                        String title = inputSplitter[1];
                                        String artist = inputSplitter[2];
                                        Double dailyRentFee = Double.parseDouble(inputSplitter[3]);
                                        int releaseYear = Integer.parseInt(inputSplitter[4]);

                                        Album s = new Album(title, artist, releaseYear, dailyRentFee, true);
                                        Employee.addAlbum(s, products);
                                        System.out.println(s);
                                    }
                                    else if(inputType.contains("Employee")){
                                        String firstname = inputSplitter[1];
                                        String lastname = inputSplitter[2];
                                        int birthYear = Integer.parseInt(inputSplitter[3]);
                                        String address1 = inputSplitter[4];
                                        String address2 = inputSplitter[5];
                                        double salary = Double.parseDouble(inputSplitter[6]);

                                        Employee e = new Employee(firstname, lastname, birthYear, address1, address2, salary);
                                        manager.addEmployee(e, employees);
                                        System.out.println(e);

                                    } else if(inputType.contains("Customer")){
                                        String name = inputSplitter[1];
                                        String password = inputSplitter[2];
                                        MembershipEnum membership = MembershipEnum.valueOf(inputSplitter[3]);  //this basically takes in the string declared in stock.txt
                                        // and tries to find an enum written in the same manner.
                                        Customer c = new Customer(name, password, membership);
                                        Employee.addCustomer(c, customers);
                                        System.out.println(c);

                                    }
                                }
                            }
                            case 8 -> {
                                try {
                                    FileWriter fw = new FileWriter("Rentals.txt"); //filewriter writes to files. duh. it creates rentals.txt
                                                                                            //if it doesn't already exist.
                                    for (int i = 0; i < rentals.size(); i++) {
                                        Rental r = rentals.get(i);
                                        String customerId = String.valueOf(r.getCustomer().getId());
                                        String productId = String.valueOf(r.getProduct().getId());
                                        String itemTitle = String.valueOf(r.getProduct().getTitle());
                                        String totalCost = String.valueOf(r.totalRentFee()); //basically just turns the elements into strings.
                                        String s = customerId +";"+ productId +";"+ itemTitle +";"+ totalCost + "\n"; //this string is the one added into the filewriter.
                                                                                                                    //this is because we need to add the line splitter.
                                        fw.write(s);
                                    }
                                    fw.close();
                                } catch (IOException e){
                                    e.printStackTrace();
                                }
                            }

                            case 9 -> {
                                mainMethod();
                            }
                            default -> {
                                renderError("Your input was wrong.");
                                mainMethod();
                            }

                        }
                    } while (mPassword.equals("admin1234"));
                }
                case "E" -> {
                    String ePassword;
                    do {
                        render("Please input your password, or press 3 to go back to the main menu:");
                        ePassword = Utilities.stringInput();

                        if (ePassword.equals("password123")) {
                            renderSuccess("Successfully logged in.");
                        } else if (ePassword.equals("3")) {
                            mainMethod();
                        } else {
                            renderError("Wrong password!");
                        }
                    } while (!ePassword.equals("password123"));

                    do {
                        employeeMenuPrint();
                        Employee employee = new Employee();
                        int option = intInput("");
                        switch (option) {
                            case 1 -> {
                                int option2 = intInput("1. Games\n" +
                                        "2. Albums");
                                switch (option2) {
                                    case 1 -> {
                                        String title = stringInputNoEmpty("Enter the game's title: ", "Game name cannot be empty.");
                                        String genre = stringInput("Enter the game's genre: ");
                                        int releaseYear = LocalDate.now().getYear() + 1;
                                        while (releaseYear >= LocalDate.now().getYear()) {
                                            releaseYear = intInput("Enter the game's release year: ");
                                            if (releaseYear >= LocalDate.now().getYear()) {
                                                render("Wait a minute, Doc. Are you telling me you built a time machine...out of a DeLorean?!" +
                                                        "\nYour release year can't be set in the future.");
                                            }
                                        }
                                        double dailyRentFee = doubleInputNoNegative("Enter the daily rent fee: ", "Game daily rent fee cannot be negative.");

                                        Game g = new Game(title, genre, releaseYear, dailyRentFee, true);
                                        Employee.addGame(g, products);
                                        renderSuccess("Game created!");
                                    }
                                    case 2 -> {
                                        String title = stringInputNoEmpty("Enter the album's title: ", "Album title name cannot be empty.");
                                        String artist = stringInputNoEmpty("Enter the album's artist: ", "Album artist name cannot be empty");

                                        int releaseYear = LocalDate.now().getYear() + 1;

                                        while (releaseYear >= LocalDate.now().getYear()) {
                                            releaseYear = intInput("Enter the album's release year: ");
                                            if (releaseYear >= LocalDate.now().getYear()) {
                                                render("Guess you guys aren't ready for that yet... but your kids are gonna love it." +
                                                        "\nYour release year can't be set in the future.");
                                            }
                                        }
                                        double dailyRentFee = doubleInputNoNegative("Enter the daily rent fee: ", "Album rent fee cannot be negative.");

                                        Album s = new Album(title, artist, releaseYear, dailyRentFee, true);
                                        Employee.addAlbum(s, products);
                                        renderSuccess("Song Album created!");
                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 2 -> {
                                String name = stringInputNoEmpty("Enter the customer's full name: ", "Customer name cannot be empty.");
                                String password = "";
                                while (password.length() < 4 || password.contains(" ")) {
                                    password = stringInput("Enter the customer's password: ");
                                    if (password.length() < 4) {
                                        render("The password you set must be higher than three characters long!");
                                    }
                                    if (password.contains(" ")) {
                                        render("The password you set can't contain any spaces!");
                                    }
                                }
                                MembershipEnum membership = MembershipEnum.BASIC;

                                Customer c = new Customer(name, password, membership);
                                Employee.addCustomer(c, customers);
                                renderSuccess("Customer successfully added!");
                            }

                            case 3 -> {
                                // view products

                                render("===GAMES===");
                                employee.printAllGames(products);

                                render("===SONG ALBUMS===");
                                employee.printAllAlbums(products);

                                int option3 = intInput("\n1. Remove Game\n" +
                                        "2. Remove Album");
                                switch (option3) {
                                    case 1 -> {
                                        String uuid = stringInput("Please enter the ID of the product you want to " +
                                                "remove");
                                        employee.removeGame(uuid, products);
                                        renderSuccess("Game removed!");

                                    }
                                    case 2 -> {
                                        String uuid = stringInput("Please enter the ID of the album you want to" +
                                                " remove");
                                        employee.removeAlbum(uuid, products);
                                        renderSuccess("Song Album removed!");

                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 4 -> {
                                // view customers
                                employee.printAllCustomers(customers);
                                int option2 = intInput("1. Remove customer\n" +
                                        "2. Modify customer details\n" +
                                        "3. Return to previous menu");
                                switch (option2) {
                                    case 1 -> {
                                        String uuid = stringInput("Please enter the ID of the customer you want " +
                                                "to remove");
                                        employee.removeCustomer(uuid, customers);
                                    }
                                    case 2 -> {
                                        String uuid = stringInput("Enter the ID of the customer you want to modify: ");
                                        String modification = stringInput("What would you like to modify? Type " +
                                                "an option:\n" +
                                                "NAME\n" +
                                                "PASSWORD\n" +
                                                "MEMBERSHIP - will upgrade by one instance\n");

                                        modification = modification.toLowerCase();
                                        employee.modifyCustomer(uuid, customers, modification);
                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 5 -> {
                                Customer c = null;
                                render("The following customers have requested to upgrade:");
                                for (int i = 0; i < customers.size(); i++) {
                                    if (customers.get(i).getUpgradeRequest()) {
                                        System.out.println(customers.get(i).toString());
                                    }
                                }
                                int option2 = intInput("1. Upgrade all customers\n" +
                                        "2. Upgrade a specific customer.\n" +
                                        "3. Reject all upgrades.\n" +
                                        "4. Return to Employee menu.");
                                switch (option2) {
                                    case 1 -> {
                                        for (int i = 0; i < customers.size(); i++) {
                                            if (customers.get(i).getUpgradeRequest()) {
                                                customers.get(i).upgradeMembership();
                                            }
                                        }
                                    }
                                    case 2 -> {
                                        String uuid = stringInput("Enter the ID of the customer you want to upgrade: ");
                                        for (int i = 0; i < customers.size(); i++) {
                                            if (customers.get(i).getUpgradeRequest() && customers.get(i).getId().
                                                    toString().equals(uuid)) {
                                                customers.get(i).upgradeMembership();
                                            }
                                        }
                                    }
                                    case 3 -> {
                                        for (int i = 0; i < customers.size(); i++) {
                                            if (customers.get(i).getUpgradeRequest()) {
                                                customers.get(i).setUpgradeRequestFalse();
                                            }
                                        }
                                    }
                                    default -> {
                                        renderExit("Returning to previous menu.");
                                    }
                                }
                            }
                            case 6 -> { // total rent profit
                                render("The total rent profit is: " + getTotalProfit());
                            }
                            case 7 -> {
                                mainMethod();
                            }
                            default -> {
                                renderError("Your input was wrong.");
                            }
                        }
                    } while (ePassword.equals("password123"));
                }
                case "C" -> {
                    Employee employee = new Employee();
                    Customer c = null;
                    String cPassword = null;
                    do {
                        //Looks through all IDs in the arraylist
                        String loginUUID = stringInput("Please enter your login ID:");
                        for (int i = 0; i < customers.size(); i++) {
                            //Finds the ID matching the loginUUID input
                            if (customers.get(i).getId().toString().equals(loginUUID)) {

                                c = customers.get(i);
                            }
                        }
                        if (c == null) {
                            renderError("Could not find the ID, contact an employee if you don't have one!");
                            mainMethod();
                        } else {
                            renderSuccess("Attempting to log in as " + c.getName());
                            cPassword = stringInput("Please enter your password:");
                        }
                    } while (c == null);

                    do {
                        if (cPassword.equals(c.getPassword())) {
                            renderSuccess("Successfully logged in.");
                        } else if (cPassword.equals("3")) {
                            mainMethod();
                        } else {
                            renderError("Wrong password!");
                            mainMethod();
                        }
                    } while (!cPassword.equals(c.getPassword()));

                    do {
                        customerMenuPrint();
                        c.setMembershipValues();
                        int option = intInput("");
                        switch (option) {
                            case 1 -> {
                                render("===GAMES===");

                                employee.printAllGames(products);

                                render("===SONG ALBUMS===");

                                employee.printAllAlbums(products);

                                if (c.getAmountRent() < c.getMaxRent()) {
                                    if (c.getMembership() == MembershipEnum.GOLD || c.getMembership() == MembershipEnum.PLATINUM) {
                                        render("\nYou currently have " + c.getCreditsAmount() + " credits to spend.\n" +
                                                "For 5 credits you can rent a product for free!\n" +
                                                "You will receive " + c.getCreditsReceived() + " credits per item rented!");
                                        if (c.getCreditsAmount() >= 5 && !c.getNextProductFree()) {
                                            int optionCredits = intInput("Would you like to use your credits?\n" +
                                                    "1. Yes\n" +
                                                    "2. No");
                                            if (optionCredits == 1) {
                                                c.setNextProductFree(true);
                                                c.resetCreditsAmount();
                                            }
                                        } else if (c.getNextProductFree())
                                            render("Note: Your next product will be free and your credits will reset.");
                                    } // This above prints if the user decides to leave the menu after answering Yes.

                                    Product p = null;
                                    String uuid = stringInput("Please enter the ID of the product you want to rent:");


                                    for (int i = 0; i < products.size(); i++) {
                                        if (products.get(i).getId().toString().equals(uuid)) {

                                            p = products.get(i);
                                        }
                                    }

                                    if (p == null || !p.getAvailable()) {
                                        renderError("Could not find the ID or product was already rented!");

                                    } else {
                                        if (c.getNextProductFree()) {
                                            c.setDiscount(0);
                                            renderSuccess("Your product was rented for free!");
                                        }

                                        c.rentingBenefits();
                                        rentProduct(c, p, LocalDate.now());
                                        c.setMembershipValues(); //  resets Discount values in case free product
                                        renderSuccess("Rented " + p.getTitle() + "!");

                                    }
                                } else {
                                    double withDecimals = c.getMaxRent();
                                    int maxRentText = (int) withDecimals; // forces it to not have decimals
                                    String plural = "";
                                    if (c.getMaxRent() > 1) {
                                        plural = "s";
                                    } else {
                                        plural = "";
                                    }
                                    render("You are only allowed to rent " + maxRentText  + " product" + plural + "!");
                                }
                            }
                            //  Return a game
                            case 2 -> {
                                render("===GAMES===");
                                employee.printAllGames(products);

                                render("===SONG ALBUMS===");
                                employee.printAllAlbums(products);

                                Rental returns = null;
                                String uuid = stringInput("Please enter the ID of the game you want to return:");

                                for (Rental rental : rentals) {
                                    if (uuid.equals(rental.getProduct().getId().toString())) {
                                        returns = rental;
                                    }
                                }

                                if (returns == null) {
                                    renderError("Could not find the ID!");
                                } else {

                                    Product p = null;

                                    for (int i = 0; i < products.size(); i++) {
                                        if (products.get(i).getId().toString().equals(uuid)) {

                                            p = products.get(i);
                                        }
                                    }

                                    returns.returnRental(LocalDate.now());

                                    if(!returns.getReturnDate().isAfter(returns.getRentDate())) {
                                        renderError("Invalid operation. Upon returning an item, the number of days rented must be positive.");

                                    } else {

                                        renderSuccess("Successfully returned a product!");
                                        c.changeAmountRent(-1);


                                        String writtenReview;
                                        Integer productRating;
                                        render("Leave a numerical rating between 1 and 5.");
                                        do{
                                        productRating = Utilities.intInput();
                                        if (productRating <= 0 || productRating > 5){
                                            render("Incorrect input. Please input a numerical rating between 1 and 5.");
                                        }
                                        } while (productRating <= 0 || productRating > 5);
                                        render("Would you like to leave a written review?\n" +
                                                "1. Yes" +
                                                "\n2. No");
                                        Integer input = Utilities.intInput();
                                        if (input == 1) {
                                            //leave written review!
                                            writtenReview = Utilities.stringInput();
                                        } else if (input == 2) {
                                            // dont leave written review!
                                            writtenReview = "No written review";
                                        } else {
                                            renderError("Wrong input! Cancelling written review.");
                                            writtenReview = "No written review";

                                        }

                                        Rating r = new Rating(productRating, writtenReview);
                                        p.ratings.add(productRating);
                                        rating.add(r);
                                        ratingsHash.put(p.ratings, p);
                                        renderSuccess("Successfully submitted your review!");

                                    }

                                }
                            }
                            case 3 -> {
                                int option3 = intInput("Hey " + c.getName() + "! If you like DART, you will love DART" +
                                        " Memberships!\n" +
                                        "SILVER - 10% discount and rent up to 3 products.\n" +
                                        "GOLD - 15% discount and rent up to 5 products. Returning a product gives you " +
                                        "2 credits.\n" +
                                        "PLATINUM - 25% discount and rent up to 7 products. Returning a product gives " +
                                        "you 3 credits.\n" +
                                        "You currently have a " + c.getMembership() + " membership. To upgrade, press 1.");
                                switch (option3) {
                                    case 1 -> {
                                        if (c.getMembership() != MembershipEnum.PLATINUM) {
                                            c.setUpgradeRequestTrue();
                                            renderSuccess("Your membership will be upgraded to " + c.getNextMembership() +
                                                    " as soon as an employee accepts it.");
                                        } else {
                                            render("Could not request an upgrade, your membership is already at maximum!");
                                        }
                                    }
                                    case 2 -> {
                                        renderExit("Returning to previous menu.");
                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 4 -> {
                                customerMessagesPrint();
                                int option4 = intInput("");
                                switch (option4) {
                                    case 1 -> {
                                        UUID recipientID = UUID.fromString
                                                (stringInput("Enter recipient ID of this message:"));

                                        String title = stringInput("Give a title to the message: ");
                                        String content = stringInput("Write your message: ");
                                        Message message = new Message(c.getId(), content, title);
                                        for (Customer customerMsgVar : customers) {
                                            if (customerMsgVar.getId().equals(recipientID)) {
                                                customerMsgVar.addMessage(message);
                                                System.out.println(title + " has been saved!");
                                                break;

                                            }
                                        }
                                    }

                                    case 2 -> {
                                        System.out.println("Hello there " + c.getName() + "! Here are your messages:");
                                        for (UUID Id : c.getInboxIDs()) { // for each object in set of UUIDs
                                            System.out.println(Id);
                                        }
                                        String input = stringInput("Which inbox would you like to view?");
                                        try {
                                            ArrayList<Message> messages = c.getUserInbox(UUID.fromString(input));
                                            if (messages != null) {
                                                System.out.println();
                                                for (Message message : messages) {
                                                    System.out.println(message);
                                                    System.out.println();
                                                    // adds formatting
                                                }

                                            }
                                        } catch (Exception e) {
                                            System.out.println("Could not find the right inbox! Please try again.");
                                        }
                                    }
                                    case 3 -> {
                                        System.out.println("Hello there " + c.getName() + "! Here are your messages to " +
                                                "delete:");
                                        for (UUID Id : c.getInboxIDs()) { // for each object in set of UUIDs
                                            System.out.println(Id);
                                        }
                                        String input = stringInput("Which inbox would you like to delete in?");
                                        try {
                                            UUID Id = UUID.fromString(input);
                                            ArrayList<Message> messages = c.getUserInbox(Id);
                                            if (messages != null) {
                                                System.out.println();
                                                int removeIndex = 0;
                                                for (Message message : messages) {
                                                    System.out.println(removeIndex);
                                                    System.out.println(message);
                                                    System.out.println();
                                                    removeIndex++;
                                                }
                                                removeIndex = intInput("Which message would you like to remove?");
                                                if (c.removeMessage(removeIndex, Id)) {
                                                    System.out.println("Successfully removed: " + removeIndex);

                                                } else {
                                                    System.out.println("Failed to remove: " + removeIndex +
                                                            ". Please try again.");
                                                }

                                            }
                                        } catch (Exception e) {
                                            renderError("UUID does not exist! Try again.");
                                        }


                                    }
                                    case 4 -> {

                                    }
                                }
                            }

                            case 5 -> {
                                System.out.println("Would you like to search games or albums?\n1.Games (via genre)\n2.Albums (via release year)");
                                int search = Utilities.intInput();
                                if (search == 1) {
                                    System.out.println("What genre are you looking for?");
                                    String query = Utilities.stringInput();
                                    for (Product product : products) {
                                        Game game = (product instanceof Game ? (Game) product : null);
                                        if (game == null) {
                                            continue;
                                        }
                                        if (game.getGenre().equals(query))
                                            System.out.println(game);
                                    }

                                } else if (search == 2) {
                                    System.out.println("What year was the album released?");
                                    int query = Utilities.intInput();
                                    for (Product product : products) {
                                        Album album = (product instanceof Album ? (Album) product : null);
                                        if (album == null) {
                                            continue;
                                        }
                                        if (album.getReleaseYear() == query)
                                            System.out.println(album);
                                    }
                                }
                            }

                            case 6 -> {


                                option = intInput("Would you like to sort by:\n" +
                                        "1. Best average ratings\n" +
                                        "2. Release year");

                                switch(option) {
                                    case 1 -> {

                                        render ("Showing products sorted by best rated.");

                                        products.sort(new Comparator<Product>() {
                                            @Override
                                            public int compare(Product o1, Product o2) {
                                                return Double.compare(o1.getAverageRatings(), o2.getAverageRatings());
                                            }
                                        }.reversed());

                                        System.out.println("===GAMES===");
                                        employee.printAllGames(products);

                                        System.out.println("===SONG ALBUMS===");
                                        employee.printAllAlbums(products);


                                    }
                                    case 2 -> {

                                        render ("Showing products sorted by release year.");

                                        products.sort(new Comparator<Product>() {
                                            @Override
                                            public int compare(Product o1, Product o2) {
                                                return Integer.compare(o1.getReleaseYear(), o2.getReleaseYear());
                                            }
                                        }.reversed());

                                        System.out.println("===GAMES===");
                                        employee.printAllGames(products);


                                        System.out.println("===SONG ALBUMS===");
                                        employee.printAllAlbums(products);


                                    }default -> {
                                        renderExit("Wrong input. Returning to previous menu.");
                                    }
                                }

                            }
                            case 7 -> {
                                mainMethod();
                            }
                            default -> {
                                renderError("Your input was wrong.");
                            }
                        }
                    } while (cPassword.equals(c.getPassword()) || c != null);
                }
                case "X" -> {
                    System.exit(0);

                }
                case "HALLOWEEN" -> {
                    System.out.println("Boo!");
                    mainMethod();
                }
                default -> {
                    renderError("Your input was wrong.");
                    mainMethod();
                }
            }
        } while (value != "X");
    }
}
