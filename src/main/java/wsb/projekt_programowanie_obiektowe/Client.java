package wsb.projekt_programowanie_obiektowe;

import java.time.LocalDate;
import java.util.HashMap;

public class Client {
    private final String id;
    private final String firstName;
    private final String lastName;

    private final LocalDate creationDate;
    private boolean isPremiumAccount;

    private final HashMap<SupportedMetalType, Double> metalIngotList;

    public Client(String firstName, String lastName) {
        this.id =  lastName+" "+firstName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = LocalDate.now();
        this.metalIngotList = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public boolean isPremiumAccount() {
        return isPremiumAccount;
    }

    public void setPremiumAccount(boolean premiumAccount) {
        isPremiumAccount = premiumAccount;
    }

    public HashMap<SupportedMetalType, Double> getMetalIngotList() {
        return metalIngotList;
    }

}
