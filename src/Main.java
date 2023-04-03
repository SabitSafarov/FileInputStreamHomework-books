import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try(Reader reader1 = new FileReader("C:\\Users\\ssabi\\Desktop\\Task\\Дз коллекции, потоки 1\\csv\\book.csv");
            Scanner scr1 = new Scanner(reader1);
            Reader reader2 = new FileReader("C:\\Users\\ssabi\\Desktop\\Task\\Дз коллекции, потоки 1\\csv\\author.csv");
            Scanner scr2 = new Scanner(reader2);
            ) {

            Map<Integer, String> authors = new HashMap<>();
            scr2.nextLine();

            while (scr2.hasNextLine()) {
                String[] array = scr2.nextLine().split(",");
                authors.put(Integer.parseInt(array[0]), array[1]);
            }

            Map<String, String> books = new HashMap<>();
            scr1.nextLine();

            while (scr1.hasNextLine()) {
                String[] array = scr1.nextLine().split(",");
                if(array.length > 6) {
                    books.put(array[1] + array[2] + " - " + authors.get(Integer.parseInt(array[6])), array[5]);
                    continue;
                }
                books.put(array[1] + " - " + authors.get(Integer.parseInt(array[5])), array[4]);
            }

            for (Map.Entry<String, String> entry : books.entrySet()) {
                InputStream inputStream = new FileInputStream("C:\\Users\\ssabi\\Desktop\\Task\\Дз коллекции, потоки 1\\images\\" + entry.getValue());
                byte[] array = inputStream.readAllBytes();
                OutputStream out = new FileOutputStream("resault\\img\\" + entry.getKey() + ".jpeg");
                out.write(array);
            }


        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}