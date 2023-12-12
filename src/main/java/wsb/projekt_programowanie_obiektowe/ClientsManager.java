package wsb.projekt_programowanie_obiektowe;

import java.time.LocalDate;
import java.util.*;

public class ClientsManager implements Clients, Warehouse {

    public final List<Client> clientsList;

    public ClientsManager() {
        this.clientsList = new ArrayList<>();
    }

    private Client getClientFromList(String clientId) {
        if (!this.clientsList.isEmpty()) {
            for (Client client : this.clientsList) {
                if (Objects.equals(client.getId(), clientId)) {
                    return client;
                }
            }
        } else {
            throw new ClientNotFoundException("Error: The list of clients is empty!");
        }
        throw new ClientNotFoundException(String.format("Error: Client id: '%s' not found!", clientId));
    }

    private void groupMetalIngot(HashMap<SupportedMetalType, Double> clientMetalIngotList, SupportedMetalType metalType, double mass){
        if (clientMetalIngotList.get(metalType) + mass <= metalType.getDensity()) {
            clientMetalIngotList.put(metalType, clientMetalIngotList.get(metalType)+mass);
            System.out.println("Added: " + metalType + ", " + mass + "kg");
        } else {
            throw new FullWarehouseException("Error: Exceeded limit!");
        }
    }

    private void addNewMetalIngot(HashMap<SupportedMetalType, Double> clientMetalIngotList, SupportedMetalType metalType, double mass){
        if (mass <= metalType.getDensity()&& mass>0) {
            clientMetalIngotList.put(metalType,mass);
            System.out.println("Added new: " + metalType + ", " + mass + "kg");
        } else {
            throw new FullWarehouseException("Error: Exceeded limit!");
        }
    }

    private void allowedTypeMetal(Client client, SupportedMetalType metalType, double mass){
        if (!client.isPremiumAccount() && metalType == SupportedMetalType.GOLD) {
            throw new ProhibitedMetalTypeException("Error: Prohibited metal type!");
        } else {
            addNewMetalIngot(client.getMetalIngotList(),metalType, mass);
            System.out.println("Added new metalType: " + metalType + ", " + mass + "kg");
        }
    }



    @Override
    public String createNewClient(String firstName, String lastName) {
        this.clientsList.add(new Client(firstName, lastName));
        return "Client has been added.";
    }

    @Override
    public String activatePremiumAccount(String clientId) {
        if (!getClientFromList(clientId).isPremiumAccount()) {
            getClientFromList(clientId).setPremiumAccount(true);
        }
        return getClientFromList(clientId).getFullName();
    }

    @Override
    public String getClientFullName(String clientId) {
        return getClientFromList(clientId).getFullName();
    }

    @Override
    public LocalDate getClientCreationDate(String clientId) {
        return getClientFromList(clientId).getCreationDate();
    }

    @Override
    public boolean isPremiumClient(String clientId) {
        return getClientFromList(clientId).isPremiumAccount();
    }

    @Override
    public int getNumberOfClients() {
        return clientsList.size();
    }

    @Override
    public int getNumberOfPremiumClients() {
        int numberOfPremiumClients = 0;
        if (!this.clientsList.isEmpty()) {
            for (Client client : this.clientsList) {
                if (client.isPremiumAccount()) {
                    numberOfPremiumClients++;
                }
            }
        } else {
            throw new ClientNotFoundException("Error: The list of clients is empty!");
        }
        return numberOfPremiumClients;
    }

    @Override
    public void addMetalIngot(String clientId, SupportedMetalType metalType, double mass) throws ClientNotFoundException, ProhibitedMetalTypeException, FullWarehouseException {
        if (getClientFromList(clientId).getMetalIngotList().containsKey(metalType)) {
            groupMetalIngot(getClientFromList(clientId).getMetalIngotList(),metalType,mass);
        } else {
            System.out.println("nie");
            allowedTypeMetal(getClientFromList(clientId),metalType, mass);
        }
        System.out.println(getClientFromList(clientId).getMetalIngotList());
    }

    @Override
    public Map<SupportedMetalType, Double> getMetalTypesToMassStoredByClient(String clientId) {
        return  getClientFromList(clientId).getMetalIngotList();
    }

    @Override
    public double getTotalVolumeOccupiedByClient(String clientId) {
        double totalVolumeOccupied = 0.0;
        for (Double i : getClientFromList(clientId).getMetalIngotList().values()) {
            totalVolumeOccupied = totalVolumeOccupied + i;
        }
        return totalVolumeOccupied;
    }

    @Override
    public List<SupportedMetalType> getStoredMetalTypesByClient(String clientId) {
        return new ArrayList<>(getClientFromList(clientId).getMetalIngotList().keySet());
    }
}
