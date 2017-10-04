import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class Util {
  public static ArrayList<String> readFile(String filename) {
    ArrayList<String> list = new ArrayList<String>();
    BufferedReader br;
    String line;

    try {
      File f = new File(filename);
      FileReader fr = new FileReader(f);
      br = new BufferedReader(fr);
      while ((line = br.readLine()) != null) {
        list.add(line);
      }
      br.close();
    } catch (java.io.FileNotFoundException fnfe) {
      return null;
    } catch (java.io.IOException ioe) {
      return null;
    }

    return list;
  }

  public static void writeFile(ArrayList<String> hashes, String filename) {
    try {
      File f = new File(filename);
      FileWriter fw = new FileWriter(f);
      BufferedWriter bw = new BufferedWriter(fw);

      for (int i = 0; i < hashes.size(); i++) {
        String hash = hashes.get(i);
        bw.write(hash);
        bw.newLine();
      }
      bw.close();
    }
    catch (java.io.FileNotFoundException fnfe) {
      System.out.println("FileNotFoundException: " + fnfe.getMessage());
      return;
    }
    catch (java.io.IOException ioe) {
      System.out.println("IOException: " + ioe.getMessage());
      return;
    }
  }
}
