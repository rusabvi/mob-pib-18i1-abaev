import java.io.*;

public class NewFile {

    public static void main(String args[]) {
        int [][] massive = {{200, 10}, {120, 20}, {30, 200}, {25, 250}, {90, 25}};
        String path = "C://Files//Massive.txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            for (int[] m : massive) {
                for (int n : m)
                    bw.write(String.valueOf(n) + " ");
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
