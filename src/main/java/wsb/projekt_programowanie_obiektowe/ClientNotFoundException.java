package wsb.projekt_programowanie_obiektowe;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message){
        super(message);
    }
}
