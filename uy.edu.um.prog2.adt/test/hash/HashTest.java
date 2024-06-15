package hash;

import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTest {

    Hash<String, String> hash;
    @BeforeEach
    public void Hash(){
        hash = new Hash<>(10);
        hash.add("7fzHQizxTqy8wTXwlrgPQQ","MILLION DOLLAR BABY");
        hash.add("6AI3ezQ4o3HUoP6Dhudph3","Not Like Us");
        hash.add("2qSkIjg1o9h3YT9RAgYN75","Espresso");
        hash.add("7221xIgOnuakPdLqT0F3nP","I Had Some Help (Feat. Morgan Wallen)");
    }
    @Test
    public void containsTest (){
        assertTrue(hash.contains("7fzHQizxTqy8wTXwlrgPQQ"));

        assertFalse(hash.contains("7jz3QizxKqy8fTXwldgPQQ"));
    }

    @Test
    public void addTest(){
        hash.add("2GxrNKugF82CnoRFbQfzPf","i like the way you kiss me");

        assertEquals(true,hash.contains("2GxrNKugF82CnoRFbQfzPf"));
    }
    @Test
    public void addRevaueTest() throws EmptyHashException, InvalidKeyException {
        hash.add("2qSkIjg1o9h3YT9RAgYN75","Revaluamos");

        assertEquals("Revaluamos",hash.get("2qSkIjg1o9h3YT9RAgYN75"));
    }
    @Test
    public void addReHashTest(){
        hash.add("2GxrNKugF82CnoRFbQfzPf","i like the way you kiss me");
        hash.add("2OzhQlSqBEmt7hmkYxfT6m","Fortnight (feat. Post Malone)");
        hash.add("6XjDF6nds4DE2BBbagZol6","Gata Only");
        hash.add("2FQrifJ1N335Ljm3TjTVVf","A Bar Song (Tipsy)");;
        hash.add("3xkHsmpQCBMytMJNiDf3Ii","Beautiful Things");

        assertEquals(23,hash.getCapacity());
    }

    @Test
    public void removeTest() throws InvalidKeyException {
        hash.remove("7221xIgOnuakPdLqT0F3nP");
        assertFalse(hash.contains("7221xIgOnuakPdLqT0F3nP"));

        assertThrows(InvalidKeyException.class, () -> {
            hash.remove("0321xORdunakPdkqZO42Dj");
        });


    }

    @Test
    public void getTest() throws EmptyHashException, InvalidKeyException {
        hash.add("6XjDF6nds4DE2BBbagZol6","Gata Only");
        assertEquals("Gata Only",hash.get("6XjDF6nds4DE2BBbagZol6"));

        assertThrows(InvalidKeyException.class, () -> {
            hash.get("0321xORdunakPdkqZO42Dj");
        });

        hash.remove("6XjDF6nds4DE2BBbagZol6");
        hash.remove("7fzHQizxTqy8wTXwlrgPQQ");
        hash.remove("6AI3ezQ4o3HUoP6Dhudph3");
        hash.remove("2qSkIjg1o9h3YT9RAgYN75");
        hash.remove("7221xIgOnuakPdLqT0F3nP");

        assertThrows(EmptyHashException.class, () -> {
            hash.get("7221xIgOnuakPdLqT0F3nP");
        });
    }

    @Test
    public void rehashTest(){
        hash.reHash();
        assertEquals(23,hash.getCapacity());
    }

}