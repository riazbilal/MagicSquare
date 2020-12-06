# MagicSquare
The aim of this project was to be able to create a Semi Magic Square given an input size of for the length and width of a square. This project also included a built in tester that could be triggered with the usage of a "-t" flag!  

A Semi Magic Square is a square that is made up of smaller squares, the number of these smaller squares being equal to the area (length * width) of the originial square. Each of these squares contains some distinct positive number. This means that no numerical value can be repeated anywhere in this originial square, once the distinct numerical value is used once. A Semi Magic Square is a square in which the sums of the numbers of each column and each row equal the same unique number. The diagonals however, do not need to sum to a unique and identical number.

Compilation: 
 
 javac SemiMagic.java 

Execution: 
- To find the solution for either a 3x3, 4x4 or 5x5 sized square type, you must type run the program and type the length of one side of the square: 
    java SemiMagic (insert number between 3-5) 

    Example: java SemiMagic 3

- To run the internal tester and see the results and solutions for many different squares then use the "-t" flag: 
    
     Example: java SemiMagic -t
