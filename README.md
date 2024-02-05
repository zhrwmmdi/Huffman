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
#### Packages
The project consists of 3 packages: coding, data_handler, structure
* **coding:** This package contains HuffmanDecoding.java and HuffmanEncoding.java classes that are responsible for compressing 
and decompressing data. The Huffman algorithm and the process of coding & decoding is implemented here in this package.
* **data_handler:** This package contains classes which browse files (Browser.java), read their data (FileReader.java)
or store the result of coding package in an output file (FileWriter.java).
* **structure:** This package only contains Node.java class that is a vital element in Huffman algorithm tree.
#### Classes
**HuffmanEncoding.java**

This class scans the input data and creates a hashmap that represents characters of the input file and their frequency.
This hashmap is later used for creating the Huffman tree using a priority queue. When the tree is ready, then each character
code is defined by rolling over the tree. Each character and its huffman code is saved in another hashmap for later usage.
The coded text is given to FileWriter.java class to be saved locally as a compressed file (.com).

**HuffmanDecoding.java**

This class gets the data that is saved in a compressed file. Each compressed file starts with characters and their codes,
all separated by space, followed by the coded data. This class scans this file and store each character and its code in 
a hashmap. By using this hashmap and iterating over coded data, the original text will be discovered and will be saved 
in a text file using FileWriter.java class.

**Browser.java**

This class in the GUI part of the project which is also the starting point. A file chooser is displayed for user to 
select a text or compressed file and do the proper function according to the file format. The browse() method of this
class is the only method that is called directly in the main class.

**FileReader.java**

This class reads the selected file and returns a string that contains the file data. This string is used later for encoding
or decoding.

**FileWriter.java**

This is the ending part of the project. createOriginalTxtFile() gets the decoded data and store it in a local repository
as a text file. createCmpFile() method also stores the coded data in another local repository as a compressed file. The 
path of these repositories are given at the beginning of this class.

**Node.java** 

As mentioned before, this class is a vital structure used in creating the huffman tree for encoding. Each node has a right
child and left child, in which a character and its frequency is stored.

**Main.java**

This is the main class that only needs a Browser object to run the whole project.


