package DART.models;

import DART.enums.MembershipEnum;
import DART.miscellaneous.Utilities;
import DART.models.products.Album;
import DART.models.products.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class Customer {
    private UUID Id;
    private String name;
    private String password;
    private MembershipEnum membership;
    private boolean upgradeRequest;
    private HashMap<UUID, ArrayList<Message>> inbox;

    public Customer(String name, String password, MembershipEnum membership) {
        Id = UUID.randomUUID(); // we don't need user input to make an ID, therefore it's not a parameter in constructor
        this.name = name;
        this.password = password;
        this.membership = membership;
        this.upgradeRequest = false;
        this.inbox = new HashMap<UUID, ArrayList<Message>>(); // we have an ArrayList of messages for each customer ID


    }

    public Customer() {

    }

    // Messaging stuff
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

    public MembershipEnum getMembership() {
        return membership;
    }

    public boolean getUpgradeRequest() {
        return upgradeRequest;
    }

    public void setName() {
        this.name = Utilities.stringInput();
    }

    public void setPassword() {

        this.password = Utilities.stringInput();
    }

    public void setMembership() {
        String input = Utilities.stringInput();
        MembershipEnum membership = MembershipEnum.valueOf(input);

    }

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


    public MembershipEnum getNextMembership() {

        if (getMembership() == MembershipEnum.BASIC) {
            return MembershipEnum.SILVER;
        } else if (getMembership() == MembershipEnum.SILVER) {
            return MembershipEnum.GOLD;
        } else if (getMembership() == MembershipEnum.GOLD) {
            return MembershipEnum.PLATINUM;
        } else System.out.println("Could not find next membership level.");
        return null;
    }

    @Override
    public String toString() {
        return getId() + " : " + getName() + " [" + getPassword() + "] - " + getMembership();
    }

    // What customers can do:

    public void rentProduct(Game g) {
        if (!g.getAvailable()) { // if its not available
            System.out.println("It's not available!");

        } else {
            g.setAvailable(false);
            g.setRentDate();
        }
    }

    public void returnGame(Game g) {
        if (g.getAvailable()) { // if its not available
            System.out.println("This game is not rented!");

        } else {
            g.setAvailable(true);
            g.setReturnDate();
        }
    }

    public void rentAlbum(Album s) {
        if (!s.getAvailable()) { // if its not available
            System.out.println("It's not available!");

        } else {
            s.setAvailable(false);
            s.setRentDate();
        }
    }

    public void returnAlbum(Album s) {
        if (s.getAvailable()) { // if its not available
            System.out.println("This game is not rented!");

        } else {
            s.setAvailable(true);
            s.setReturnDate();
        }
    }

    public void addAlbum(Album Album, ArrayList<Album> Albums) {
        Albums.add(Album);
    }

}
