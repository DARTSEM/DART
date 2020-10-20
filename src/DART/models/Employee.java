package DART.models;

// identify all the classes you need, all the models, you need a CUSTOMER to store customers 
// what is a customer and an employee in the system

import DART.models.products.Album;
import DART.models.products.Game;
import DART.models.products.Product;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Employee {

    private UUID Id;
    private String firstName;
    private String lastName;
    private int birthYear;
    private String address1;
    private String address2;
    private double monthlySalary;

    public Employee(String firstName, String lastName, int birthYear, String address1, String address2,
                    double monthlySalary) {

        this.Id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.address1 = address1;
        this.address2 = address2;
        this.monthlySalary = monthlySalary;
    }

    public Employee() {

    }


    public UUID getId() {
        return Id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getAddress() {
        return address1 + "\r\n" + address2;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public int getAge() {
        return LocalDate.now().getYear() - getBirthYear();
    }

    public Double calculateBonusSalary() {

        Double bonusSalary = 4000.0;
        Integer age = getAge();

        if (age >= 22 && age <= 30) {
            bonusSalary = 6000.0;
        } else if (age > 30) {
            bonusSalary = 7500.0;
        }
        return bonusSalary;
    } // some changes here

    public Double calculateNetSalary() {
        Double netSalary = getMonthlySalary() * 12;

        if (netSalary >= 100000) {
            netSalary *= 0.7;
        }
        return netSalary + calculateBonusSalary();
    }

    private static DecimalFormat df2 = new DecimalFormat("#.##");
    @Override
    public String toString() {
        return getId() + " : " + getName() + " " + getBirthYear() + " (" + getAge() + " years old) " +
                df2.format(calculateNetSalary()) + " SEK.";
        // product.Method  VS  product
    }

    // WHAT THE EMPLOYEES CAN DO:

    // PRODUCTS stuff

    public void printAllProducts(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            System.out.println(currentProduct);
        }
    }


    // GAMES stuff
    public static void addGame(Game game, List<Product> games) {
        games.add(game);
    }


    public void removeGame(String ID, List<Product> games) {
        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getId().toString().equals(ID)) {
                games.remove(games.get(i));
                return;
            }
        }
        System.out.println("Game with entered ID not found");
    }

    public void printAllGames(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            if (currentProduct instanceof Game) {
                System.out.println(currentProduct);
            }
        }
    }

    // Album stuff

    public static void addAlbum(Album Album, List<Product> Albums) {
        Albums.add(Album);
    }

    public void removeAlbum(String ID, List<Product> Albums) {
        for (int i = 0; i < Albums.size(); i++) {
            if (Albums.get(i).getId().toString().equals(ID)) {
                Albums.remove(Albums.get(i));
                return;
            }
        }
        System.out.println("Album with entered ID not found");
    }

    public void printAllAlbums(List<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            Product currentProduct = products.get(i);
            if (currentProduct instanceof Album) {
                System.out.println(currentProduct);
            }
        }
    }

    // CUSTOMER stuff

    public static void addCustomer(Customer customer, ArrayList<Customer> customers) {
        customers.add(customer);
    }

    public void removeCustomer(String ID, ArrayList<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().toString().equals(ID)) {
                customers.remove(customers.get(i));
                return;
            }
        }
    }

    public void modifyCustomer(String ID, ArrayList<Customer> customers, String modification) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId().toString().equals(ID)) {
                switch (modification) {
                    case "name" -> customers.get(i).setName();
                    case "password" -> customers.get(i).setPassword();
                    case "membership" -> customers.get(i).upgradeMembership();
                    default -> System.out.print("Did not recognize that modification.");
                }
                return;
            }
        }
    }

    public void printAllCustomers(ArrayList<Customer> customers) {
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).toString());
        }
    }
    /* public Customer findCustomer(UUID id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            System.err.println("Could not find this customer with this specific ID! Try again.");
        }
        return customer;
        */

}
