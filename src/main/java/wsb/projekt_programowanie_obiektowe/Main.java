package wsb.projekt_programowanie_obiektowe;

public class Main {
    public static void main(String[] args) {
        ClientsManager clientsManager = new ClientsManager();
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.createNewClient("Jan","Nowak"));
        System.out.println(clientsManager.createNewClient("Adam","Nowak"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.activatePremiumAccount("Nowak Jan"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getClientFullName("Nowak Jan"));
        System.out.println(clientsManager.getClientFullName("Nowak Adam"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getClientCreationDate("Nowak Jan"));
        System.out.println(clientsManager.getClientCreationDate("Nowak Adam"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.isPremiumClient("Nowak Jan"));
        System.out.println(clientsManager.isPremiumClient("Nowak Adam"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getNumberOfClients());
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getNumberOfPremiumClients());
        System.out.println("--------------------------------------------------");
        System.out.println("--------------------------------------------------");
        clientsManager.addMetalIngot("Nowak Jan", SupportedMetalType.GOLD,500.00);
        clientsManager.addMetalIngot("Nowak Jan", SupportedMetalType.SILVER,300.00);
        clientsManager.addMetalIngot("Nowak Jan", SupportedMetalType.GOLD,100.00);
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getMetalTypesToMassStoredByClient("Nowak Jan"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getTotalVolumeOccupiedByClient("Nowak Jan"));
        System.out.println("--------------------------------------------------");
        System.out.println(clientsManager.getStoredMetalTypesByClient("Nowak Jan"));
        System.out.println("--------------------------------------------------");








    }
}