import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readeCSV {
    public BufferedReader reader;
    public String line;
    public String parts[];

    public void readFile(String file_name) {
        try {

            reader = new BufferedReader(new FileReader(file_name));
            while ((line = reader.readLine()) != null){
                parts = line.split(" ; " );
                printLine();
                System.out.println();
            }
            reader.close();
            line = null;
            parts = null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void printLine(){
        for (int i = 0; i < parts.length; i++){
            System.out.println(parts[i] + "    |   ");
        }
    }
}
