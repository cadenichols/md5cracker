import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Cracker {
  ArrayList<String> dictionary;
  HashMap<String, String> dictHashes;

  public Cracker() {
    dictionary = new ArrayList<String>();
  }

  public void readDictionary(String filename) {
    dictionary = Util.readFile(filename);
    dictHashes = new HashMap<String, String>();

    for (String word : dictionary) {
      String hash = makeHash(word);
      dictHashes.put(hash, word);
    }
  }

  public String makeHash(String input) {
    MessageDigest md;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (java.security.NoSuchAlgorithmException e) {
      return null;
    }
    md.reset();
    md.update(input.getBytes());
    String hash = DatatypeConverter.printHexBinary(md.digest());
    return hash.toUpperCase();
  }

  public String crackHash(String targetHash) {
    if (dictHashes.containsKey(targetHash.trim().toUpperCase())) {
      return dictHashes.get(targetHash);
    }
    return null;
  }

  public ArrayList<String> crackAll(String filename) {
    ArrayList<String> targetHashes = Util.readFile(filename);
    return crackAll(targetHashes);
  }

  public ArrayList<String> crackAll(ArrayList<String> targetHashes) {
    ArrayList<String> results = new ArrayList<String>();
    for (String targetHash : targetHashes) {
      String result = crackHash(targetHash);
      
      if (result != null) {
        results.add(targetHash + " : " + result);
      }
    }
    return results;
  }
}

/* 
The Crackers:
Pollywanna
Cheesin
Holgrayn
Ritz
*/
