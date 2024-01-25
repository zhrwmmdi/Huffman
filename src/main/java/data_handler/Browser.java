package data_handler;

import coding.HuffmanDecoding;
import coding.HuffmanEncoding;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Browser {
    private final JFrame frame;
    private String selectedFilePath;
    private File selectedFile;


    public Browser() {
        frame =  new JFrame();
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
    public void browse(){
        showFileChooser();
        FileReader reader = new FileReader();
        reader.readFile(this.getSelectedFilePath());
        chooseFunction();
    }
    private void chooseFunction(){
        if (selectedFile.getName().endsWith(".txt")){
            HuffmanEncoding encoder = new HuffmanEncoding();
            encoder.encode(FileReader.getStringData());
        } else if (selectedFile.getName().endsWith(".cmp")) {
            HuffmanDecoding decoding = new HuffmanDecoding();
            decoding.decode();
        }else {
            JOptionPane.showMessageDialog(null,"Wrong file format.\nPlease choose a proper file.","Wrong Format",JOptionPane.WARNING_MESSAGE);
            browse();
        }
        System.exit(0);
    }

    private void showFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter txt = new FileNameExtensionFilter("Text Files (*.txt)", "txt");
        FileNameExtensionFilter cmp = new FileNameExtensionFilter("Compressed Files (*.cmp)", "cmp");
        fileChooser.setFileFilter(cmp);
        fileChooser.setFileFilter(txt);

        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            selectedFilePath = selectedFile.getAbsolutePath();
        }else System.exit(0);
    }
    public String getSelectedFilePath() {
        return selectedFilePath;
    }
}
