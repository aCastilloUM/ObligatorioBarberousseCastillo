import exceptions.EmptyHashException;
import exceptions.InvalidKeyException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws EmptyHashException, InvalidKeyException {
        readerCSV file = new readerCSV();
        file.readFile("C:\\Users\\agust\\OneDrive\\Escritorio\\ExcelObligatorio.csv");

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("Obligatorio Programacion 2 año 2024");
        System.out.println(" ");
        System.out.println("Bienvenido al consultor! ");
        System.out.println(" ");

        while (!salir) {
            System.out.println("Ingrese el numero de consulta que desea realizar: ");
            System.out.println("1. Top 10 canciones en un país y una fecha dada.");
            System.out.println("2. Top 5 canciones que aparecen en más top 50 en un día dado.");
            System.out.println("3. Top 7 artistas que más aparecen en los top 50 para un rango de fechas dado.");
            System.out.println("4. Cantidad de veces que aparece un artista específico en un top 50 en una fecha dada.");
            System.out.println("5. Cantidad de canciones con un tempo en un rango específico para un rango específico de fechas.");
            System.out.println("6. Salir.");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Ha seleccionado la consulta 1.");
                    // Llama a un método que maneje la consulta 1
                    break;
                case 2:
                    System.out.println("Ha seleccionado la consulta 2.");
                    // Llama a un método que maneje la consulta 2
                    break;
                case 3:
                    System.out.println("Ha seleccionado la consulta 3.");
                    // Llama a un método que maneje la consulta 3
                    break;
                case 4:
                    System.out.println("Ha seleccionado la consulta 4.");
                    // Llama a un método que maneje la consulta 4
                    break;
                case 5:
                    System.out.println("Ha seleccionado la consulta 5.");
                    // Llama a un método que maneje la consulta 5
                    break;
                case 6:
                    salir = true;
                    System.out.println("Ha seleccionado salir. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 6.");
                    break;
            }

            // Preguntar si desea salir o realizar otra consulta
            if (!salir) {
                boolean respuestaValida = false;
                while (!respuestaValida) {
                    System.out.println("¿Desea realizar otra consulta? (si/no): ");
                    String continuar = scanner.next();
                    if (continuar.equals("si")) {
                        respuestaValida = true;
                        salir = false;
                    } else if (continuar.equals("no")) {
                        respuestaValida = true;
                        salir = true;
                        System.out.println("¡Hasta luego!");
                    } else {
                        System.out.println("Respuesta no válida. Por favor, ingrese 'si' o 'no'.");
                    }
                }
            }
        }
    }
}

//"C:\\Users\\agust\\OneDrive\\Escritorio\\ExcelObligatorio.csv"

//"C:\\Users\\Lu\\Documents\\UM2024\\Programacion II\\universal_top_spotify_songs.csv"