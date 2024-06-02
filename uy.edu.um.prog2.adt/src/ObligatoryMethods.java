import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;

public interface ObligatoryMethods {
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException;
}
