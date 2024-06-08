import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import hash.Hash;
import heap.Heap;
import linkedList.LinkedList;
import linkedList.ListNode;
import org.w3c.dom.NodeList;

import java.io.FilterOutputStream;
import java.security.Key;
import java.util.List;

public class ObligatoryMethodsImpl implements ObligatoryMethods{
    ReadCSV file;

//"C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    //"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"
    public ObligatoryMethodsImpl(){
        file = new ReadCSV();
        file.uploadCSV( "C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv");
    }

    //"C:\\Users\\agust\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {
        System.out.println(file.getWorld().getSize());
        System.out.println(file.getWorld().getFirstHash());
        String key = country + date;
        LinkedList<String> top50 = file.getWorld().get(key);
        for (int i = 0; i < top50.getSize(); i++){
            String songKey = top50.getValueNode(i);
            Song s = file.getSongs().get(songKey);
            System.out.println((i+1) + " - " + s.getName() + "    " + s.getArtists());
        }
    }
    LinkedList<ListNode<String>> dateSongs = new LinkedList<>();
    public void top5RepeatedSongs(String date) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException {
            int counter = 0;
            Hash<String, Integer> songsAppearances = new Hash<>(5);
            Heap<Integer, String> top5 = new Heap<>();

            for (int i = 0 ; i<abbreviations.length; i++){
                String key = abbreviations[i] + date;
                LinkedList<String> top50 = file.getWorld().get(key);
                if (counter == 0){
                    dateSongs.addFirst(top50.getHead());
                    enlazarNodosRecursivo(dateSongs.getHead().getValue(),top50.getHead().getNext());

                }
                else{
                    enlazarNodosRecursivo(dateSongs.getLast().getValue(), top50.getHead());
                }
                counter++;
            }

            for (int i = 0 ; i<dateSongs.getSize(); i++ ){

                String key = dateSongs.getValueNode(i).getValue().trim();

                if (!songsAppearances.contains(key)){
                    songsAppearances.add(key,1);

                }
                else{
                    Integer appareances = songsAppearances.get(key);
                    appareances++;
                    songsAppearances.add(key,appareances);
                }

            }

            for ( int i = 0; i < songsAppearances.getTable().length ; i++){
                if (songsAppearances.getTable()[i] != null){
                    top5.add(songsAppearances.getTable()[i].getValue(),songsAppearances.getTable()[i].getKey());
                }
            }
            System.out.println("Top 5 canciones en "+ date);
            for (int i = 0; i < 5; i++) {
                System.out.println(i+". " + file.getSongs().get(top5.get()).getName() +"    " + file.getSongs().get(top5.get()).getArtists());
            }
        }

    public void enlazarNodosRecursivo(ListNode<String> a, ListNode<String> b) {
        if (b == null) // Verificar si b o su siguiente nodo son nulos
            return;

        this.dateSongs.addLast(b);
        a.setNext(b);
        enlazarNodosRecursivo(b, b.getNext());
    }

    String[] abbreviations= {"GLB","ZA","VN","VE","UY","US","UA","TW","TR","TH","SV","SK","SG","SE","SA","RO","PY","PT","PL","PK","PH","PE","PA","NZ","NO",
            "NL","NI","NG","MY","MX","MA","LV","LU","LT","KZ","KR","JP","IT","IS","IN","IL","IE","ID","HU","HN","HK","GT","GR","GB","FR","FI","ES","EG","EE",
            "EC","DO","DK","DE","CZ","CR","CO","CL","CH","CA","BY","BR","BO",
            "BG","BE","AU","AT","AR","AE"};
}
