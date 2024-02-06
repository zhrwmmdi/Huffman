# Huffman Encoding and Decoding

## Overview

This educational project focuses on the implementation of Huffman coding and decoding, a fundamental algorithm in the 
field of data structure and algorithms. Huffman coding is a compression technique that efficiently represents data by 
assigning variable-length codes based on character frequencies. Read more about Huffman algorithm [here](https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/).

## Features
* **Huffman Coding:** Efficiently compress text files using Huffman algorithm and save the result in a .com file.
* **Huffman Decoding:** Decompress encoded data and save the result in a .txt file.
* **Usage Examples:** Examples and sample code to demonstrate how to use the implemented algorithms.
* **Educational Purpose:** Designed for learning and understanding data structures and algorithms concepts.

## Getting Started

#### Prerequisities
Java Development Kit (JDK) installed.
#### How to Run
1. Clone the repository

`git clone https://github.com/zhrwmmdi/Huffman.git`

2. Navigate to the project directory

`cd huffman/src/main/java`

3. Compile and run the project

`javac Main.java`

`java Main`

## Project Structure
### Packages
The project consists of 3 packages: coding, data_handler, structure
* **coding:** This package contains HuffmanDecoding.java and HuffmanEncoding.java classes that are responsible for compressing 
and decompressing data. The Huffman algorithm and the process of coding & decoding is implemented here in this package.
* **data_handler:** This package contains classes which browse files (Browser.java), read their data (FileReader.java)
or store the result of coding package in an output file (FileWriter.java).
* **structure:** This package only contains Node.java class that is a vital element in Huffman algorithm tree.
### Classes
**HuffmanEncoding.java**
scans the input data and creates a hashmap that represents characters of the input file and their frequency.
This hashmap is later used for creating the Huffman tree using a priority queue. When the tree is ready, then each character
code is defined by rolling over the tree. Each character and its huffman code is saved in another hashmap for later usage.
The coded text is given to FileWriter.java class to be saved locally as a compressed file (.com).

**HuffmanDecoding.java**
gets the data that is saved in a compressed file. Each compressed file starts with characters and their codes,
all separated by space, followed by the coded data. This class scans this file and store each character and its code in 
a hashmap. By using this hashmap and iterating over coded data, the original text will be discovered and will be saved 
in a text file using FileWriter.java class.

**Browser.java**
is the GUI part of the project which is also the starting point. A file chooser is displayed for user to 
select a text or compressed file and do the proper function according to the file format. The browse() method of this
class is the only method that is called directly in the main class.

**FileReader.java**
reads the selected file and returns a string that contains the file data. This string is used later for encoding
or decoding.

**FileWriter.java**
is the ending part of the project. createOriginalTxtFile() gets the decoded data and store it in a local repository
as a text file. createCmpFile() method also stores the coded data in another local repository as a compressed file. The 
path of these repositories are given at the beginning of this class.

**Node.java**
is a vital structure used in creating the huffman tree for encoding. Each node has a right child and left child, in which
a character and its frequency is stored.

**Main.java**
is the main class that only needs a Browser object to run the whole project.

## Usage Examples
### Encoding

Let's start encoding with a text file that contains text "Huffman encoding and decoding!"
    ![Screenshot (331)](https://github.com/zhrwmmdi/Huffman/assets/130865277/72875e8b-f398-40ba-a23e-250d1321c807)


1. Run Main.java class in your IDE.
   
   ![Screenshot (328)](https://github.com/zhrwmmdi/Huffman/assets/130865277/790d0519-5544-4791-8b37-d5328b5a1bd1)

3. A file chooser will show up. Press Browse File button and Navigate to the text file location and press open button.

   ![Screenshot (329)](https://github.com/zhrwmmdi/Huffman/assets/130865277/dd8167a5-85df-437e-a16a-bcb60e9ca6b6)
   ![Screenshot (330)](https://github.com/zhrwmmdi/Huffman/assets/130865277/996e6f5a-e47d-4ebf-a2de-15bcf204edee)

3. In the run tab, you will see a confirmation message that shows the destination file path and the encoded data.

   ![Screenshot (334)](https://github.com/zhrwmmdi/Huffman/assets/130865277/ad5adab2-5e4a-4abd-9a6c-e1f09d80ffc4)

4. Now if you navigate to the file destination, a compressed file is saved there. The name of the file is the original text.

   ![Screenshot (336)](https://github.com/zhrwmmdi/Huffman/assets/130865277/bb2830bf-5452-49d6-88aa-51ffa6a4606c)

5. If you open the compressed file, each character and its code is stored at the begining and is followed by the coded data.

   ![Screenshot (335)](https://github.com/zhrwmmdi/Huffman/assets/130865277/86019b0c-7830-48da-9c51-8a293369c3ca)

### Decoding
1. Run Main.java.
2. Choose compressed(.com) format.
3. Navigate the file chooser to the compressed file destination and press open button.

 ![Screenshot (337)](https://github.com/zhrwmmdi/Huffman/assets/130865277/8fd09e47-955a-4a76-8810-e3b6460f947c)
   
5. In the run tab, you'll se a confirmation message that shows the destination file path and the decoded data.

   ![Screenshot (338)](https://github.com/zhrwmmdi/Huffman/assets/130865277/284d65dd-0b8a-4871-b4b1-1b68146dc2b2)

7. Now if you navigate to the destination file path, a text file is saved there that is exactly the same as its origin.
   
   ![Screenshot (341)](https://github.com/zhrwmmdi/Huffman/assets/130865277/76c699c4-69e6-414d-85e9-6b4e5db63c6f)










