import exceptions.EmptyHashException;
import exceptions.EmptyStackException;
import exceptions.InvalidKeyException;
import hash.Hash;
import heap.Heap;
import linkedList.LinkedList;
import linkedList.ListNode;
import linkedList.MyList;
import org.w3c.dom.NodeList;

import java.io.FilterOutputStream;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ObligatoryMethodsImpl implements ObligatoryMethods {
    ReadCSV file;

    //"C:\\Users\\agust\\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    //"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"
    public ObligatoryMethodsImpl() {
        file = new ReadCSV();
        file.uploadCSV("C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv");
    }

    //"C:\\Users\\agust\OneDrive\\Escritorio\\universal_top_spotify_songs.csv"
    @Override
    public void top10Song(String country, String date) throws EmptyHashException, InvalidKeyException {
        System.out.println(file.getWorld().getSize());
        System.out.println(file.getWorld().getFirstHash());
        String key = country + date;
        LinkedList<String> top50 = file.getWorld().get(key);
        for (int i = 0; i < 10; i++) {
            String songKey = top50.getValueNode(i);
            Song s = file.getSongs().get(songKey);
            System.out.println((i + 1) + " - " + s.getName() + "    " + s.getArtists());
        }
    }

    LinkedList<ListNode<String>> dateSongs = new LinkedList<>();

    public void top5RepeatedSongs(String date) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException {
        int counter = 0;
        Hash<String, Integer> songsAppearances = new Hash<>(5);
        Heap<Integer, String> top5 = new Heap<>();

        for (int i = 0; i < abbreviations.length; i++) {
            String key = abbreviations[i] + date;
            LinkedList<String> top50 = file.getWorld().get(key);
            if (counter == 0) {
                dateSongs.addFirst(top50.getHead());
                enlazarNodosRecursivo(dateSongs.getHead().getValue(), top50.getHead().getNext());

            } else {
                enlazarNodosRecursivo(dateSongs.getLast().getValue(), top50.getHead());
            }
            counter++;
        }

        for (int i = 0; i < dateSongs.getSize(); i++) {

            String key = dateSongs.getValueNode(i).getValue().trim();

            if (!songsAppearances.contains(key)) {
                songsAppearances.add(key, 1);

            } else {
                Integer appareances = songsAppearances.get(key);
                appareances++;
                songsAppearances.add(key, appareances);
            }

        }

        for (int i = 0; i < songsAppearances.getTable().length; i++) {
            if (songsAppearances.getTable()[i] != null) {
                top5.add(songsAppearances.getTable()[i].getValue(), songsAppearances.getTable()[i].getKey());
            }
        }
        System.out.println("Top 5 canciones en " + date);
        for (int i = 0; i < 5; i++) {
            System.out.println((i+1) + " - " + file.getSongs().get(top5.get()).getName() + "    " + file.getSongs().get(top5.get()).getArtists());
        }
    }

    public void top7Artist(String first, String last) throws EmptyHashException, InvalidKeyException, InvalidKeyException.EmptyHeapException, EmptyStackException.InvalidKeyException {
        MyList<LocalDate> dates = new LinkedList<>();
        MyList<LocalDate> betweenDates = new LinkedList<>();
        Heap<Integer, String> artist = new Heap<>();
        Hash<String, Integer> artistAppearances = new Hash<>(7);

        //Convertimos las fechas a LocalDate
        LocalDate firstDate = null;
        LocalDate lastDate = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            firstDate = LocalDate.parse(first, formatter);
            lastDate = LocalDate.parse(last, formatter);
        } catch (DateTimeParseException e) {
            System.err.println("Fecha no válida");
        }

        if (firstDate.isAfter(lastDate)) {
            System.err.println("La primera fecha es posterior a la última fecha.");
            return;  // Termina el método si el rango de fechas no es válido
        }

        System.out.println(file.getWorld().getTable().length);
        //Recorremos la lista de canciones para obtener cada fecha
        for (int i = 0; i < file.getWorld().getTable().length; i++) {
            if (file.getWorld().getTable()[i] != null) {
                if (file.getWorld().getTable()[i].getKey().length() == 12){
                    String fecha = file.getWorld().getTable()[i].getKey().substring(2);
                    LocalDate date = LocalDate.parse(fecha);
                    if (!dates.contains(date)) {
                        dates.addLast(date);
                    }
                }
            }
        }
        /*
        for (int i = 0; i < dates.getSize(); i++) {
            System.out.println(dates.getValueNode(i));
        }
         */
        for (int i = 0; i < dates.getSize(); i++) {
            LocalDate currentDate = dates.getValueNode(i);
            System.out.println("Sos gil");
            System.out.println("Current Date: " + currentDate);
            if (isBetween(currentDate, firstDate, lastDate)) {
                betweenDates.addLast(currentDate);
            }
            System.out.println("BetweenDates Size: " + betweenDates.getSize());
        }

        //Combinamos cada fecha con cada pais, accedemos a la lista, accedemos en cada cancion al otro hash
        //Accedemos a los artistas, y si tiene comas contabilizamos por separado

        for (int i = 0; i < betweenDates.getSize(); i++) {
            for (int j =0 ; j < abbreviations.length ; j++){
                String key = abbreviations[j] + betweenDates.getValueNode(i);
                LinkedList<String> top50 = file.getWorld().get(key);
                for (int k = 0; k < 50; k++) {
                    String songKey = top50.getValueNode(k);

                    String artstListS = file.getSongs().get(songKey).artists;
                    System.out.println(artstListS);
                    String[] artistList = artstListS.split(",");

                    for (int z = 0; z < artistList.length; z++){
                        if (!artistAppearances.contains(artistList[z])) {
                            System.out.println(artistAppearances.getSize());
                            artistAppearances.add(artistList[z], 1);
                        } else {
                            Integer appearances = artistAppearances.get(artistList[z]);
                            appearances++;
                            artistAppearances.add(artistList[z], appearances);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < artistAppearances.getTable().length; i++) {
            if (artistAppearances.getTable()[i] != null) {
                artist.add(artistAppearances.getTable()[i].getValue(), artistAppearances.getTable()[i].getKey());
            }
        }
        System.out.println(artist.getSize());
        for (int i = 0; i < 8; i++) {
            System.out.println((i+1) + " - " + artist.get());
            artist.delete(i);
        }
    }
    public void enlazarNodosRecursivo (ListNode < String > a, ListNode < String > b){
        if (b == null) // Verificar si b o su siguiente nodo son nulos
            return;

        this.dateSongs.addLast(b);
        a.setNext(b);
        enlazarNodosRecursivo(b, b.getNext());
    }

    public boolean isBetween(LocalDate date, LocalDate first, LocalDate last) {
        if (!date.isBefore(first)  && !date.isAfter(last)){
            System.out.println("Casti PUL");
        }

        return !date.isBefore(first) && !date.isAfter(last);
    }

    String[] abbreviations = {"GLB", "ZA", "VN", "VE", "UY", "US", "UA", "TW", "TR", "TH", "SV", "SK", "SG", "SE", "SA", "RO", "PY", "PT", "PL", "PK", "PH", "PE", "PA", "NZ", "NO",
            "NL", "NI", "NG", "MY", "MX", "MA", "LV", "LU", "LT", "KZ", "KR", "JP", "IT", "IS", "IN", "IL", "IE", "ID", "HU", "HN", "HK", "GT", "GR", "GB", "FR", "FI", "ES", "EG", "EE",
            "EC", "DO", "DK", "DE", "CZ", "CR", "CO", "CL", "CH", "CA", "BY", "BR", "BO",
            "BG", "BE", "AU", "AT", "AR", "AE"};

    String[] dates = {
            "2024-05-13", "2024-05-12", "2024-05-11", "2024-05-10", "2024-05-09", "2024-05-08", "2024-05-07", "2024-05-06",
            "2024-05-05", "2024-05-04", "2024-05-03", "2024-05-02", "2024-05-01", "2024-04-30", "2024-04-29", "2024-04-28",
            "2024-04-27", "2024-04-26", "2024-04-25", "2024-04-24", "2024-04-23", "2024-04-22", "2024-04-21", "2024-04-20",
            "2024-04-19", "2024-04-18", "2024-04-17", "2024-04-16", "2024-04-15", "2024-04-14", "2024-04-13", "2024-04-12",
            "2024-04-11", "2024-04-10", "2024-04-09", "2024-04-08", "2024-04-07", "2024-04-06", "2024-04-05", "2024-04-04",
            "2024-04-03", "2024-04-02", "2024-04-01", "2024-03-31", "2024-03-30", "2024-03-29", "2024-03-28", "2024-03-27",
            "2024-03-26", "2024-03-25", "2024-03-24", "2024-03-23", "2024-03-22", "2024-03-21", "2024-03-20", "2024-03-19",
            "2024-03-18", "2024-03-16", "2024-03-14", "2024-03-13", "2024-03-12", "2024-03-11", "2024-03-10", "2024-03-09",
            "2024-03-08", "2024-03-07", "2024-03-06", "2024-03-05", "2024-03-04", "2024-03-03", "2024-03-02", "2024-03-01",
            "2024-02-29", "2024-02-28", "2024-02-27", "2024-02-26", "2024-02-25", "2024-02-24", "2024-02-23", "2024-02-22",
            "2024-02-21", "2024-02-20", "2024-02-19", "2024-02-18", "2024-02-17", "2024-02-16", "2024-02-15", "2024-02-14",
            "2024-02-13", "2024-02-12", "2024-02-11", "2024-02-10", "2024-02-09", "2024-02-08", "2024-02-07", "2024-02-06",
            "2024-02-05", "2024-02-04", "2024-02-03", "2024-02-02", "2024-02-01", "2024-01-31", "2024-01-30", "2024-01-29",
            "2024-01-28", "2024-01-27", "2024-01-26", "2024-01-25", "2024-01-24", "2024-01-23", "2024-01-22", "2024-01-21",
            "2024-01-20", "2024-01-19", "2024-01-18", "2024-01-17", "2024-01-16", "2024-01-15", "2024-01-14", "2024-01-13",
            "2024-01-12", "2024-01-11", "2024-01-10", "2024-01-09", "2024-01-08", "2024-01-07", "2024-01-06", "2024-01-05",
            "2024-01-04", "2024-01-03", "2024-01-02", "2024-01-01", "2023-12-31", "2023-12-30", "2023-12-29", "2023-12-28",
            "2023-12-27", "2023-12-26", "2023-12-25", "2023-12-24", "2023-12-23", "2023-12-22", "2023-12-21", "2023-12-20",
            "2023-12-19", "2023-12-18", "2023-12-17", "2023-12-16", "2023-12-15", "2023-12-14", "2023-12-13", "2023-12-12",
            "2023-12-11", "2023-12-10", "2023-12-09", "2023-12-08", "2023-12-07", "2023-12-06", "2023-12-05", "2023-12-04",
            "2023-12-03", "2023-12-02", "2023-12-01", "2023-11-30", "2023-11-29", "2023-11-28", "2023-11-27", "2023-11-26",
            "2023-11-25", "2023-11-24", "2023-11-23", "2023-11-22", "2023-11-21", "2023-11-20", "2023-11-19", "2023-11-17",
            "2023-11-16", "2023-11-15", "2023-11-14", "2023-11-13", "2023-11-12", "2023-11-11", "2023-11-10", "2023-11-09",
            "2023-11-08", "2023-11-07", "2023-11-06", "2023-11-05", "2023-11-04", "2023-11-03", "2023-11-02", "2023-11-01",
            "2023-10-31", "2023-10-30", "2023-10-29", "2023-10-28", "2023-10-27", "2023-10-26", "2023-10-25", "2023-10-24",
            "2023-10-23", "2023-10-22", "2023-10-21", "2023-10-20", "2023-10-19", "2023-10-18"
            };

    /* Fechas que no estan
            2024-03-17
            2024-03-15
            2023-11-18
     */
}