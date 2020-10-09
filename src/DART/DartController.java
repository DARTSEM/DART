package DART;

import DART.enums.MembershipEnum;
import DART.enums.ProductType;
import DART.miscellaneous.Utilities;
import DART.models.*;
import DART.models.products.Album;
import DART.models.products.Game;
import DART.models.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DartController {

    public static ArrayList<Customer> customers = new ArrayList<Customer>();
    public static List<Product> products = new ArrayList<Product>();
    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    // public ArrayList<Rental> rentals = new ArrayList<Rental>();

    public DartController() {

    }

    public static void exampleCustomers() {
        Employee employee = new Employee();
        Customer example = new Customer("Drake Axelrod", "p", MembershipEnum.GOLD);
        Customer example2 = new Customer("Guy O'Hare", "p", MembershipEnum.SILVER);
        Customer example3 = new Customer("John McJohn", "p", MembershipEnum.BASIC);
        Customer example4 = new Customer("Edmonton Son of Daniel", "p", MembershipEnum.PLATINUM);
        employee.addCustomer(example, customers);
        employee.addCustomer(example2, customers);
        employee.addCustomer(example3, customers);
        employee.addCustomer(example4, customers);
    }

    public static void exampleProducts() {
        Employee employee = new Employee();
        Album dudebro = new Album("Mongolian Thiccest Throat Remastered", "Khanis Ginger", 2000,
                200);
        employee.addAlbum(dudebro, products);
        Game exampleGame = new Game("Abandoned 4 Demise 2", "Action", 122.3);
        Game example2Game = new Game("Groundrim", "Adventure", 100.0);
        Game example3Game = new Game("Not Portal 2", "Puzzle", 60.0);
        employee.addGame(exampleGame, products);
        employee.addGame(example2Game, products);
        employee.addGame(example3Game, products);
    }

    public static void exampleEmployees() {
        Manager manager = new Manager();
        Employee exampleEmployee = new Employee("Daniel", "McWorker", 2000,
                "Bruh Street", "Sideway Boulevard", 30000);
        Employee example2Employee = new Employee("Toshiba", "McWorker", 1970,
                "Dog Street", "Phone Property", 27500);
        manager.addEmployee(exampleEmployee, employees);
        manager.addEmployee(example2Employee, employees);
    }


    public static int intInput(String message) {
        System.out.println(message);
        return Utilities.intInput();
    }

    public static double doubleInput(String message) {
        System.out.println(message);
        return Utilities.doubleInput();
    }

    public static String stringInput(String message) {
        System.out.println(message);
        return Utilities.stringInput();
    }

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
                "3. Return to Main Menu\n");
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
                "5. Return to Main Menu\n");
    }

    public static void customerMessagesPrint() {
        System.out.println(Utilities.line() + "Messages Screen - Type one of the options below:\n" +
                "1. Send a message\n" +
                "2. View messages\n" +
                "3. Delete messages\n" +
                "4. Return to Customer Menu\n");
    }

    // Membership related:
    public double getDailyRentFeeDiscounted(Product p, Customer c) {
        return p.getDailyRentFee() * c.getDiscount();
    }


    public static void main(String[] args) {
        System.out.println("Initializing DART . . .\n");
        exampleProducts();
        exampleEmployees();
        exampleCustomers();
        mainMethod();
    }

    public static void mainMethod() {


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
                        System.out.println("Please input your password, or 3 to go back to the main menu:");
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
                                String firstname = stringInput("Please enter the first name of the employee");
                                String lastname = stringInput("Please enter the last name of the employee");
                                int birthyear = intInput("Please enter the birth year of the employee");
                                String address1 = stringInput("Please enter the address 1 of the employee");
                                String address2 = stringInput("Please enter the address 2 of the employee");
                                double salary = doubleInput("Please enter the salary");

                                Employee e = new Employee(firstname, lastname, birthyear, address1, address2, salary);

                                manager.addEmployee(e, employees);
                                renderSuccess("Employee successfully added!");
                            }
                            case 2 -> {
                                manager.printAllEmployees(employees);
                                int option2 = intInput("If you would like to remove an employee, press 1.");
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
                        System.out.println("Please input your password, or 3 to go back to the main menu:");
                        ePassword = Utilities.stringInput();

                        if (ePassword.equals("password123")) {
                            renderSuccess("Successfully logged in.");
                        } else if (ePassword.equals("3")) {
                            mainMethod();
                        } else {
                            System.out.println("Wrong password!");
                        }
                    } while (!ePassword.equals("password123"));

                    do {
                        employeeMenuPrint();
                        Employee employee = new Employee();
                        int option = intInput("");
                        switch (option) {
                            case 1 -> {
                                int option2 = intInput("For games, press 1, for Albums, press 2.");
                                switch (option2) {
                                    case 1 -> {
                                        String title = stringInput("Enter the game's title: ");
                                        String genre = stringInput("Enter the game's genre: ");
                                        double dailyRentFee = doubleInput("Enter the daily rent fee: ");

                                        Game g = new Game(title, genre, dailyRentFee);
                                        employee.addGame(g, products);
                                        renderSuccess("Game created!");
                                    }
                                    case 2 -> {
                                        String title = stringInput("Enter the album's title: ");
                                        String artist = stringInput("Enter the album's artist: ");
                                        int releaseYear = intInput("Enter the album's release year: ");
                                        double dailyRentFee = doubleInput("Enter the daily rent fee: ");

                                        Album s = new Album(title, artist, releaseYear, dailyRentFee);
                                        employee.addAlbum(s, products);
                                        renderSuccess("Song Album created!");
                                    }
                                    default -> {

                                    }
                                }
                            }
                            case 2 -> {
                                String name = stringInput("Enter the customer's full name: ");
                                String password = stringInput("Enter the customer's password: ");
                                String input = stringInput("Set customer's membership:");
                                MembershipEnum membership = MembershipEnum.valueOf(input);


                                Customer c = new Customer(name, password, membership);
                                employee.addCustomer(c, customers);
                                renderSuccess("Customer successfully added!");
                            }

                            case 3 -> {
                                // view products

                                System.out.println("===GAMES===");

                                employee.printAllGames(products);

                                System.out.println("===SONG ALBUMS===");

                                employee.printAllAlbums(products);

                                int option3 = intInput("\nIf you would like to remove a game, press 1, or album, " +
                                        "press 2.");
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
                                int option2 = intInput("If you would like to remove an customer, press 1. " +
                                        "Modify press 2. Or press 3 to return.");
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
                                                "MEMBERSHIP\n");

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
                                Game game = null;
                                double totalRentFee = 0;
                                for (int i = 0; i < products.size(); i++) {
                                    if (!products.get(i).getAvailable()) {
                                        totalRentFee += products.get(i).totalRentFee();

                                    }
                                }
                                System.out.println("The total rent fee is: " + totalRentFee);
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
                            System.out.println("Could not find the ID, contact an employee if you don't have one!");
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
                            System.out.println("Wrong password!");
                            mainMethod();
                        }
                    } while (!cPassword.equals(c.getPassword()));

                    do {
                        customerMenuPrint();
                        c.setMembershipValues();
                        int option = intInput("");
                        switch (option) {
                            case 1 -> {
                                System.out.println("===GAMES===");

                                employee.printAllGames(products);

                                System.out.println("===SONG ALBUMS===");

                                employee.printAllAlbums(products);

                                if (c.getAmountRent() < c.getMaxRent()) {
                                    if (c.getMembership() == MembershipEnum.GOLD || c.getMembership() == MembershipEnum.PLATINUM) {
                                        render("\nYou currently have " + c.getCreditsAmount() + " credits to spend.\n" +
                                                "For 5 credits you can rent a product for free!\n" +
                                                "You will receive " + c.getCreditsReceived() + " credits per item rented!");
                                        if (c.getCreditsAmount() >= 5) {
                                            int optionCredits = intInput("Would you like to use your credits? 1 for Yes, 2 for No.");
                                            if (optionCredits == 1) {
                                                c.resetCreditsAmount();
                                            }
                                        }
                                    }
                                    Product p = null;
                                    String uuid = stringInput("Please enter the ID of the product you want to rent:");
                                    for (int i = 0; i < products.size(); i++) {
                                        Product currentProduct = products.get(i);
                                        if (currentProduct instanceof Game) {
                                            p = (Game) products.get(i);

                                        }
                                    }

                                    if (p == null) {
                                        System.out.println("Could not find the ID!");
                                        // getDailyRentFeeDiscounted(g, c);
                                        mainMethod(); // temporary solution, need to fix the while loop
                                    } else {
                                        c.rentProduct(p);
                                        renderSuccess("Rented a game!");
                                    }
                                } else {
                                    render("You are only allowed to rent " + c.getMaxRent() + " product(s)!");
                                }
                            }

                            case 2 -> {
                                System.out.println("===GAMES===");

                                employee.printAllGames(products);

                                System.out.println("===SONG ALBUMS===");

                                employee.printAllAlbums(products);

                                Game game = null;
                                String uuid = stringInput("Please enter the ID of the game you want to return:");
                                for (int i = 0; i < products.size(); i++) {
                                    Product currentProduct = products.get(i);
                                    if (currentProduct.getId().toString().equals(uuid)) {
                                        if (products.get(i).getProductType() == ProductType.ALBUM)
                                            game = (Game) products.get(i);
                                    }
                                }
                                if (game == null) {
                                    System.out.println("Could not find the ID!");
                                } else {
                                    c.returnGame(game);
                                    renderSuccess("Rented a game!");
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
                                            System.out.println("UUID does not exist! Try again.");
                                        }


                                    }
                                    case 4 -> {

                                    }
                                }
                            }

                            case 5 -> {
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
                default -> {
                    renderError("Your input was wrong.");
                    mainMethod();
                }
            }
        } while (value != "X");
    }

    public UUID readUUID() {
        String input = Utilities.stringInput();
        UUID retVal = null; //initialize as null otherwise compiler doesn't know what to do with it/ have to give it
        //initial value
        try {
            retVal = UUID.fromString(input); //converts UUID from a string
            return retVal;
        } catch (IllegalArgumentException e) {
            return retVal;
        }
    }
}
