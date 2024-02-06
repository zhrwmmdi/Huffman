package data_handler;

import coding.HuffmanDecoding;
import coding.HuffmanEncoding;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class Browser {
    private String selectedFilePath;
    private File selectedFile;
    private final JFrame frame = new JFrame("Data Structure Final Project");
    private final JButton browseButton  = new JButton("Browse File");;

    public Browser() {
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setResizable(false);

        browseButton.setBounds(100,180,200,50);

        JLabel title = new JLabel("Huffman");
        JLabel subTitle = new JLabel("Coding & Decoding");
        JLabel name = new JLabel("by Zahra Mohammadi");

        Font font = new Font(title.getFont().getName(), Font.BOLD, 24);
        title.setFont(font);

        title.setBounds(150,90,100,20);
        subTitle.setBounds(145,120,110,20);
        name.setBounds(141,330,118,20);

        frame.add(title);
        frame.add(subTitle);
        frame.add(browseButton);
        frame.add(name);
        frame.setVisible(true);
    }

    public void browse() {
        browseButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                showFileChooser();
                FileReader reader = new FileReader();
                if (selectedFile.getName().endsWith(".cmp")) {
                    reader.readCmpFile(this.getSelectedFilePath());
                }else {
                    reader.readTxtFile(this.getSelectedFilePath());
                }

                chooseFunction();
            }

            private String getSelectedFilePath() {
                return selectedFilePath;
            }
        });
    }

    private void chooseFunction() {
        if (selectedFile.getName().endsWith(".txt")) {
            HuffmanEncoding encoder = new HuffmanEncoding();
            encoder.encode(FileReader.getStringData());
        } else if (selectedFile.getName().endsWith(".cmp")) {
            HuffmanDecoding decoding = new HuffmanDecoding();
            decoding.decode(FileReader.getStringData());
        } else {
            JOptionPane.showMessageDialog(null, "Wrong file format.\nPlease choose a proper file.", "Wrong Format", JOptionPane.WARNING_MESSAGE);
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
        } else System.exit(0);
    }
}
