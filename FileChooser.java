import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;

public class FileChooser {
  private String path;

  public FileChooser(String openOrSave) {
    FileFilter filter = new FileNameExtensionFilter("Text file", "txt");
    JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
    jfc.addChoosableFileFilter(filter);

    int returnValue;
    switch (openOrSave) {
      case "save":
        returnValue = jfc.showSaveDialog(null);
        break;
      default:
        returnValue = jfc.showOpenDialog(null);
    }
    
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      path = selectedFile.getAbsolutePath();

      if (!path.endsWith(".txt")) {
        path += ".txt";
      }
    } else {
      path = null;
    }
  }

  public String getPath() {
    return path;
  }
}

