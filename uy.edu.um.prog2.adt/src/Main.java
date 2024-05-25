import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        /*
        String csvFile = "universal_top_spotify_songs.csv";
        String line;
        String csvSplitBy = ";"; // Cambiado el delimitador a punto y coma

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i = 0;
            int counter = 0;
            while (((line = br.readLine()) != null && i < 748804)) {
                counter++;
                i++;
                String[] datos = line.split(csvSplitBy);

                String spotifyId = datos[0];
                String name = datos[1];
                String artist = datos[2]; // El índice de la columna "artists" es 2
                String aux_daily_rank = datos[3];
                int daily_rank = (Integer) aux_daily_rank;
                int daily_movement = Integer.parseInt(datos[4]);
                int weekly_movement = Integer.parseInt(datos[5]);
                String country = datos[6];
                LocalDate snapshot_date = LocalDate.parse(datos[7]);
                int duration_ms = Integer.parseInt(datos[10]);
                String album_name = datos[11];
                LocalDate album_release = LocalDate.parse(datos[12]);
                double tempo = Double.parseDouble(datos[23]);

                System.out.println(spotifyId + " | " + name + " | " + artist);
            }
            System.out.println(" ");
            System.out.println("La cantidad de filas del archivo es: " + counter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

         */
    }
}
