package wsb.projekt_programowanie_obiektowe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class ClientsManagerTest {
    ClientsManager clientsManagerUnderTestes = new ClientsManager();

    @Test
    void getClientFromListIfListIsEmpty () {

        //given
        Client givenClient = new Client("Adam", "Nowak");

        //when

        //then
        Assertions.assertThrows(ClientNotFoundException.class,()->clientsManagerUnderTestes.getClientFromList(givenClient.getId()));

    }

    @Test
    void getClientFromListIfClientNotFound () {

        //given
        String id = "wrong id";

        //when

        //then
        Assertions.assertThrows(ClientNotFoundException.class,()->clientsManagerUnderTestes.getClientFromList(id));

    }

    @Test
    void getClientFromListIfClientFound() {

        //given
        Client givenClient = new Client("Adam", "Nowak");

        //when
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(), givenClient.getLastName());

        //then
        Assertions.assertEquals(clientsManagerUnderTestes.clientsList.getFirst(),clientsManagerUnderTestes.getClientFromList(givenClient.getId()));

    }

    @Test
    void activatePremiumAccountIfClientIsNotPremiumAccount () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());

        //when
        String activatePremiumAccountMessage = clientsManagerUnderTestes.activatePremiumAccount(givenClient.getId());

        //then
        assertEquals("Now is a Premium Client.",activatePremiumAccountMessage);

    }

    @Test
    void getClientFullNameIfClientFulNameIsAdamNowak () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());

        //when
        String activatePremiumAccountMessage = clientsManagerUnderTestes.getClientFullName(givenClient.getId());

        //then
        assertEquals("Full name: Adam Nowak",activatePremiumAccountMessage);

    }

    @Test
    void getClientCreationDateIfClientDateIsNow () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());

        //when
        LocalDate ClientCreationDate = clientsManagerUnderTestes.getClientCreationDate(givenClient.getId());
        LocalDate localDate = LocalDate.now();

        //then
        assertEquals(localDate,ClientCreationDate);

    }

    @Test
    void isPremiumClientIfClientIsPremiumClient () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        clientsManagerUnderTestes.activatePremiumAccount(givenClient.getId());

        //when
        boolean isPremiumClient = clientsManagerUnderTestes.isPremiumClient(givenClient.getId());

        //then
        assertTrue(isPremiumClient);

    }

    @Test
    void isPremiumClientIfClientIsNotPremiumClient () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());

        //when
        boolean isPremiumClient = clientsManagerUnderTestes.isPremiumClient(givenClient.getId());

        //then
        assertFalse(isPremiumClient);

    }

    @Test
    void getNumberOfClientsIfNumberIs2 () {
        //given
        Client givenClient1 = new Client("Adam", "Nowak");
        Client givenClient2 = new Client("Jan", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient1.getFirstName(),givenClient1.getLastName());
        clientsManagerUnderTestes.createNewClient(givenClient2.getFirstName(),givenClient2.getLastName());

        //when
        int numberOfClients = clientsManagerUnderTestes.getNumberOfClients();

        //then
        assertEquals(2,numberOfClients);

    }

    @Test
    void getNumberOfClientsIfListClientsIsEmpty () {
        //given

        //when
        int numberOfClients = clientsManagerUnderTestes.getNumberOfClients();

        //then
        assertEquals(0,numberOfClients);

    }

    @Test
    void getNumberOfPremiumClientsIfPremiumClientsIs2AndNotPremiumClientsIs1 () {
        //given
        Client givenClient1 = new Client("Adam", "Nowak");
        Client givenClient2 = new Client("Jan", "Nowak");
        Client givenClient3 = new Client("Marek", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient1.getFirstName(),givenClient1.getLastName());
        clientsManagerUnderTestes.createNewClient(givenClient2.getFirstName(),givenClient2.getLastName());
        clientsManagerUnderTestes.createNewClient(givenClient3.getFirstName(),givenClient3.getLastName());
        clientsManagerUnderTestes.activatePremiumAccount(givenClient2.getId());
        clientsManagerUnderTestes.activatePremiumAccount(givenClient3.getId());

        //when
        int numberOfPremiumClients = clientsManagerUnderTestes.getNumberOfPremiumClients();

        //then
        assertEquals(2,numberOfPremiumClients);

    }

    @Test
    void getNumberOfPremiumClientsIfListClientsIsEmpty () {
        //given

        //when

        //then
        Assertions.assertThrows(ClientNotFoundException.class,()->clientsManagerUnderTestes.getNumberOfPremiumClients());
    }

    @Test
    void addMetalIngotIfClientListMetalIngotIsEmpty () {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER,500.0);
        HashMap<SupportedMetalType, Double> clientMetalIngotList = new HashMap<>();
        clientMetalIngotList.put(SupportedMetalType.SILVER, 500.0);

        //when

        //then
        Assertions.assertEquals(clientMetalIngotList,clientsManagerUnderTestes.clientsList.getFirst().getMetalIngotList());
    }

    @Test
    void addMetalIngotIfClientHasMetalIngotType() {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER,500.0);
        clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER,200.0);

        HashMap<SupportedMetalType, Double> clientMetalIngotList = new HashMap<>();
        clientMetalIngotList.put(SupportedMetalType.SILVER, 700.0);

        //when

        //then
        Assertions.assertEquals(clientMetalIngotList,clientsManagerUnderTestes.clientsList.getFirst().getMetalIngotList());
    }

    @Test
    void addMetalIngotIfClientIsNotPremiumProhibitedMetalType() {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());

        //when

        //then
        Assertions.assertThrows(ProhibitedMetalTypeException.class,()->clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.GOLD, 10.0));
    }

    @Test
    void addMetalIngotIfClientIsPremiumAddPremiumMetalType() {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        clientsManagerUnderTestes.activatePremiumAccount(givenClient.getId());

        HashMap<SupportedMetalType, Double> clientMetalIngotList = new HashMap<>();
        clientMetalIngotList.put(SupportedMetalType.GOLD, 700.0);

        //when
        clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.GOLD,700.0);


        //then
        Assertions.assertEquals(clientMetalIngotList,clientsManagerUnderTestes.clientsList.getFirst().getMetalIngotList());
       }

    @Test
    void addMetalIngotIfClientAddsTooMuchMass() {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        //when

        //then
        Assertions.assertThrows(FullWarehouseException.class,()->clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER, 10501.0));
    }

    @Test
    void addMetalIngotIfIsFullWarehouse() {
        //given
        Client givenClient = new Client("Adam", "Nowak");
        clientsManagerUnderTestes.createNewClient(givenClient.getFirstName(),givenClient.getLastName());
        clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER, 10500.0);
        //when

        //then
        Assertions.assertThrows(FullWarehouseException.class,()->clientsManagerUnderTestes.addMetalIngot(givenClient.getId(),SupportedMetalType.SILVER, 1.0));
    }

}