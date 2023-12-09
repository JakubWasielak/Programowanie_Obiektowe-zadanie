package wsb.projekt_programowanie_obiektowe;

public class test {
    public static void main(String[] args) {
        ClientsManager clientsManager = new ClientsManager();
        System.out.println(clientsManager.createNewClient("Jan","Nowak"));
        System.out.println(clientsManager.createNewClient("Marek","Nowak"));
        System.out.println(clientsManager.activatePremiumAccount("Nowak Marek"));
        clientsManager.addMetalIngot("Nowak Jan",SupportedMetalType.GOLD,100.0);

    }
}
