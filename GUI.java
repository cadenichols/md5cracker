import acm.program.Program;
import acm.graphics.*;
import acm.gui.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.util.Arrays;
import java.util.ArrayList;

public class GUI extends Program {
  JTextArea inputArea, outputArea;
  Cracker c;

  public GUI() {
    // Set up the Cracker
    c = new Cracker();
    c.readDictionary("rockyou1000000.txt");

    start();
    setSize(900, 400); 
  }

  public void init() {
    GCanvas canvas = new GCanvas();
    add(canvas);

    Font font = new Font("Helvetica", Font.PLAIN, 20);
    Font titleFont = new Font("Helvetica", Font.BOLD, 30);
    Font smallFont = new Font("Helvetica", Font.ITALIC, 13);

    Border border = BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(Color.LIGHT_GRAY),
      BorderFactory.createEmptyBorder(10, 10, 10, 10)
    );

    JLabel titleLabel = new JLabel("MD5 Cracker");
    titleLabel.setFont(titleFont);
    canvas.add(titleLabel, 350, 30);

    JLabel inputLabel = new JLabel("Enter MD5 Hashes:");
    inputLabel.setFont(font);
    canvas.add(inputLabel, 30, 80);

    JLabel helpLabel = new JLabel("Input multiple hashes separated by commas or newlines:");
    helpLabel.setFont(smallFont);
    canvas.add(helpLabel, 30, 108);

    JLabel outputLabel = new JLabel("Cracked Hashes:");
    outputLabel.setFont(font);
    canvas.add(outputLabel, 470, 90);

    inputArea = new JTextArea();
    JScrollPane inputScroll = new JScrollPane(inputArea);
    inputScroll.setBorder(border);
    inputScroll.setSize(310, 200);
    canvas.add(inputScroll, 30, 130);

    outputArea = new JTextArea();
    JScrollPane outputScroll = new JScrollPane(outputArea);
    canvas.add(outputScroll, 470, 130);
    outputArea.setEditable(false);
    outputScroll.setSize(400, 200);
    outputScroll.setBorder(border);

    JButton crackButton = new JButton("Crack");
    canvas.add(crackButton, 358, 200);

    JButton importButton = new JButton("Import from file...");
    canvas.add(importButton, 30, 340);

    JButton exportButton = new JButton("Export to file...");
    canvas.add(exportButton, 470, 340);
        
    addActionListeners();
  }

  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Crack":
        crackHashes();
        break;
      case "Import from file...":
        importHashes();
        break;
      case "Export to file...":
        exportHashes();
    }
  }
  
  public void importHashes() {
    FileChooser fc = new FileChooser("open");
    String filepath = fc.getPath();
    if (filepath != null) {
      ArrayList<String> lines = Util.readFile(filepath);
      String result = "";
      for (String line : lines) {
        result += String.format("%s%n", line);
      }
      inputArea.setText(result);        
    }
  }

  public void exportHashes() {
    FileChooser fc = new FileChooser("save");
    String filepath = fc.getPath();
    if (filepath != null) {
      String outputText = outputArea.getText();
      String[] lines = outputText.split("\r?\n|\r");
      ArrayList<String> lineList = new ArrayList<>(Arrays.asList(lines));
      Util.writeFile(lineList, filepath);
    }
  }

  private void crackHashes() {
    String inputText = inputArea.getText().toUpperCase();

    // split input texts on commas and newlines
    String[] hashes = inputText.split("\r?\n|\r|,");

    // convert array to arraylist
    ArrayList<String> hashlist = new ArrayList<>(Arrays.asList(hashes));

    // crack list of hashes
    ArrayList<String> results = c.crackAll(hashlist);
    
    // combine results into output string
    String output = "";
    
    for (String line : results) {
      output += String.format("%s%n", line);
    }
    
    // put result into outputArea
    outputArea.setText(output);
  }
}
