package DART.models;

import DART.miscellaneous.Utilities;
import DART.models.customer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Customer implements Comparable<Customer> {
    private UUID Id;
    private String name;
    private String password;
    private CustomerMembership membership;
    private boolean upgradeRequest;
    private HashMap<UUID, ArrayList<Message>> inbox;
    private int amountRent;
    private int creditsReceived;
    private int creditsAmount;
    private boolean nextProductFree;
    public Customer(String name, String password) {
        Id = UUID.randomUUID(); // we don't need user input to make an ID, therefore it's not a parameter in constructor
        this.name = name;
        this.password = password;
        this.membership = new BasicCustomer();
        this.upgradeRequest = false;
        this.inbox = new HashMap<UUID, ArrayList<Message>>(); // we have an ArrayList of messages for each customer ID
        this.amountRent = 0; // the amount of products the customer has rented. It increases when they rent!
        this.creditsReceived = 0;
        this.creditsAmount = 0;
        this.nextProductFree = false;
    }

    public Customer(String name, String password, CustomerMembership membership) {
        Id = UUID.randomUUID(); // we don't need user input to make an ID, therefore it's not a parameter in constructor
        this.name = name;
        this.password = password;
        this.membership = membership;
        this.upgradeRequest = false;
        this.inbox = new HashMap<UUID, ArrayList<Message>>(); // we have an ArrayList of messages for each customer ID
        this.amountRent = 0; // the amount of products the customer has rented. It increases when they rent!
        this.creditsReceived = 0;
        this.creditsAmount = 0;
        this.nextProductFree = false;
    }

    // Related to Messaging
    public ArrayList<Message> getUserInbox(UUID Id) {
        return inbox.get(Id);
    } // fetches user inbox based off ID

    public void addMessage(Message message) {

        ArrayList<Message> messages = inbox.get(message.getSenderID());
        if (messages == null) {
            messages = new ArrayList<Message>();
        }
        messages.add(message);
        inbox.put(message.getSenderID(), messages);

    }

    public boolean removeMessage(int msgIndex, UUID senderID) {
        ArrayList<Message> messages = inbox.get(senderID);
        if (messages == null) {
            return false;
        }
        if (msgIndex < 0 || msgIndex >= messages.size()) {
            return false;

        }
        messages.remove(msgIndex);
        if (messages.isEmpty()) {
            inbox.put(senderID, null);
        }
        return true;

    }

    public Set<UUID> getInboxIDs() {
        return inbox.keySet();
    } // returns all the

    public UUID getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean getUpgradeRequest() {
        return upgradeRequest;
    }

    public CustomerMembership getMembership() { return membership; }

    public double getDiscount() {
        return membership.getDiscount();
    }

    public double getAmountRent() {
        return amountRent;
    }

    public double getMaxRent() {
        return membership.maxRent();
    }

    public int getCreditsReceived() {
        return creditsReceived;
    }

    public int getCreditsAmount() {
        return creditsAmount;
    }

    public boolean getNextProductFree() {
        return nextProductFree;
    }

    // "rentingBenefits" is triggered when a product is rented
    public void rentingBenefits() {
        this.amountRent = this.amountRent + 1;
        this.nextProductFree = false;
        this.creditsAmount = this.creditsAmount + membership.getCredits();
    }

    public void resetCreditsAmount() {
        this.creditsAmount = 0;
    }

    public void setName() {
        this.name = Utilities.stringInput();
    }

    public void setPassword() { this.password = Utilities.stringInput(); }

    public void setNextProductFree(boolean value) { this.nextProductFree = value; }

    public void setUpgradeRequestTrue() {
        this.upgradeRequest = true;
    }

    public void setUpgradeRequestFalse() {
        this.upgradeRequest = false;
    }

    // Membership stuff
    public void upgradeMembership() {
        this.membership = getNextMembership();
    }

    public CustomerMembership getNextMembership() {

        if (membership instanceof BasicCustomer) {
            return new SilverCustomer();
        } else if (membership instanceof SilverCustomer) {
            return new GoldCustomer();
        } else if (membership instanceof GoldCustomer) {
            return new PlatinumCustomer();
        } else System.out.println("Could not find next membership level.");
        return null;
    }

    @Override
    public String toString() {
        return getId() + " : " + getName() + " [" + getPassword() + "] - " + getMembership();
    }

    @Override
    public int compareTo(Customer anotherCustomer) { //sorts products alphabetically by default

        int compare = this.name.compareTo(anotherCustomer.getName());

        if(compare < 0) {
            return -1;
        } else if (compare == 0) {
            return 0;
        } else {
            return 1;
        }
    }

}