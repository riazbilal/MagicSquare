package cs445.a3;

import java.util.Scanner; 
import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;

public class SemiMagic 
{
	public static boolean isFullSolution(int[][] square) 
    {
        for (int i = 0; i < square.length; i++) // iterate the row of the square 
        { 
        	for (int j = 0; j < square.length; j++)			// iterate the column of the square
        	{
        		if (square[i][j] == 0)				// if one of the values within the square is 0 
        		{
        			return false; 				// it is not a full solution therefore return false
        		}
        	}
        }
        return true;
    }
    public static boolean reject(int[][] square) 
    {
        int n = square.length;
        int magicSum = (int) (n * ((Math.pow(n, 2) + 1)/2));
        boolean reject = false; // default value 
        int sumX = 0;  // initialize default values for sumX and sumY
        int sumY = 0;
        boolean rowHasZero = false; 
        boolean columnHasZero = false; 
        if (duplicates(square))  // if the square contains duplicates, return true (reject it)
        {
            return true; // reject it 
        }
        if (incorrectValue(square))
        {
            return true; // reject it
        }
        for (int i = 0; i < square.length; i++) // iterate the row of the square 
        {
        	for (int j = 0; j < square.length; j++)	// iterate the column of the square 
        	{
        		sumX += square[i][j]; // sumX = sum of all horizontal nums
        		sumY += square[j][i]; // sumY = sum of all vertical nums
               
        		if (sumX > magicSum || sumY > magicSum) // if row sum or column sum is greater than magic sum, return true (reject it )
        		{
        			return true; // reject it
        		}
                if (square[i][j] == 0) 
                {
                    rowHasZero = true; // row has a 0 
                }
                if (square[j][i] == 0)
                {
                    columnHasZero = true; // column has 0
                }
        	}
         if ((sumX != magicSum && !rowHasZero) || (sumY != magicSum && !columnHasZero)) // if the horizontal and column sum do not equal each other then it is not a semi-magic square
        {
            return true;  // reject it
        }
            sumX = 0; // reset sumX
            sumY = 0; // reset sumY
            rowHasZero = false; // reset row boolean
            columnHasZero = false; // reset column boolean 
        }
        return reject;	// default value (do not reject square)
    }

    private static int[][] absoluteValue(int[][] square)
    {
        int n = square.length;
        int[][] duplicateSquare = new int[n][n];
        for (int i = 0; i < square.length; i++)
        {
            for (int j = 0; j < square.length; j++)
            {
                duplicateSquare[i][j] = square[i][j];
                if (square[i][j] < 0)
                {
                    duplicateSquare[i][j] *= -1;
                }
            
            }
        }
        return duplicateSquare; 
    }

    private static boolean duplicates(int[][] square) // private check duplicate method
    {
        int test = 0;   // test variable to see if a duplicate is found
        boolean dupe = false; 
        for (int i = 0; i < square.length; i++) // iterate through square using for loops
        {
            for (int j = 0; j < square[0].length; j++)
            {
                for (int k = 0; k < square.length && square[i][j] != 0; k++)
                {
                    for (int l = 0; l < square[0].length; l++)
                    {
                        if (k!= i || l !=j)
                        {
                                if (square[i][j] == square[k][l]) // if two numbers are the same, then duplicate is found so increment test variable
                                {
                                    test++;
                                }
                        }
                        
                    }
                }
               if (test > 0) // if test variable incremented a dupe was found
               {
                dupe = true; // dupe found
               } 
            }
        }
        return dupe;    // default value false = no dupe found
    }
   
    private static boolean incorrectValue(int[][] square)
    {
        int n = square.length;
        int incorrectValue = n * n; 
        for (int i = 0; i < square.length; i++)
        {
            for (int j = 0; j < square.length; j++)
            {
                if (square[i][j] > incorrectValue)
                {
                    return true; 
                }
            }

        }
        return false; 
    }
    public static int[][] extend(int[][] square) 
    {
        int test = -1;  // intialize test number(-1) for numbers that become extended
        for (int i = 0; i < square.length; i++) // iterate through the 2d array 
        {
            for (int j = 0; j < square[i].length; j++)
            {
                if (square[i][j] == 0) // for all elements in square that do not equal 0
                {
                    square[i][j] = test; 
                    return square;
                }
             }
        }
        return null; 
    }

    public static int[][] next(int[][] square) 
    {
        for (int i = 0; i < square.length; i++)
        {
            for (int j = 0; j < square[i].length; j++)
            {
                if (square[i][j] < 0 && ((square[i][j] * -1) < (square.length * square.length)))
                {
                    square[i][j] -= 1;  //decrement extended value 
                    return square; 
                }
            }
        }
        return null;
    }

    static void testIsFullSolution() 
    {
        int[][] fullSolutions = new int[][] // creates a full magic square solution. 1. No duplicates, 2. No non-filled values, 3. Magic sum = n*(n^2+ 1)/2 
        {
            {8, 1, 6},
            {3, 5, 7},
            {4, 9, 2},
        };
        int[][] fullSolutions2 = new int[][]
        {
            {1, 15, 14, 4},
            {10, 11, 8, 5},
            {7, 6, 9, 12},
            {16, 2, 3, 13},
        };
        int[][] notFullSolutions = new int[][]	// creates a non-full magic square solution. 1. There is a non-filled value 0 at row[1], column[1]
        {
        	{8, 1, 6},
            {3, 0, 7},
            {4, 9, 2},
        };
        int[][] notFullSolutions2 = new int[][]
        {
            {1, 15, 14, 4},
            {0, 11, 8, 5},
            {7, 6, 0, 12},
            {16, 0, 3, 13},
        };
        System.err.println("These should be full:"); // prints statement that the following squares should be full solutions
        		if (isFullSolution(fullSolutions))	
                {		
        		  System.err.println("Full solution: ");
			    }        		
                else 
                {
                  System.err.println("Not Full Solutions:");
                }
                printSquare(fullSolutions); 
                if (isFullSolution(fullSolutions2))  
                {       
                  System.err.println("Full solution:");
                }               
                else 
                {
                  System.err.println("Not Full Solutions:");
                }
                printSquare(fullSolutions2); 

        System.err.println("These should NOT be full:"); //prints statement that the following squares should not be full solutions 
      			if (isFullSolution(notFullSolutions)) 
                {
                   System.err.println("Full Solutions: ");	// if its full solution print out magic square that is not a full solution  
            	}
                else 
                {
                   System.err.println("Not Full solution: ");
                }
                printSquare(notFullSolutions);  
                if (isFullSolution(notFullSolutions2)) 
                {
                   System.err.println("Full Solutions: ");    
                }
                else 
                {
                    System.err.println("Not Full solution: ");
                }
                printSquare(notFullSolutions2);  

                System.out.println("------------------------------------------");
    }

    
 
    static void testReject() 
    {
       int[][] notRejected = new int[][]   // should not be rejected since it is a semi-magic square
       {
       		{1, 5, 9},
            {6, 7, 2},
            {8, 3, 4},
       };
       int[][] rejected = new int[][]   // should be rejected since it contains duplicates
       {
       		{1, 5, 5},
       		{6, 7, 0},
       		{8, 7, 4},	
       };

       System.err.println("These should NOT be rejected:");
       	if (reject(notRejected))
       	{
       		System.err.println("Not rejected:");
       	}
        else 
        {
            System.err.println("rejected: ");
        }
       	printSquare(notRejected);
       System.err.println("These should be rejected:");
       	if (reject(rejected))
       	{
       		System.err.println("rejected: ");
       	}
        else 
        {
            System.err.println(" Not rejected:  ");
        }
        printSquare(rejected);
        System.out.println("------------------------------------------");
    }

    static void testExtend() 
    {
        int[][] noExtend = new int[][]
        {
            {1, 15, 14, 4},
            {10, 11, 8, 5},
            {7, 6, 9, 12},
            {16, 2, 3, 13},
        };
        int[][] extend = new int[][]
        {
            {0, 0, 0, 0},
            {10, 11, 8, 0},
            {7, 6, 9, 0},
            {16, 2, 3, 0},
        };

        System.err.println("These can NOT be extended:");
            System.err.println("\nNot Extended:");
            printSquare(noExtend);
    
        System.err.println("These can be extended:");
            System.err.println("\nExtended:");
            printSquare(extend);
            System.err.println("to \n ");
            printSquare(extend(extend));
        System.out.println("------------------------------------------");
         
    }

    static void testNext() 
    {
        int[][] noNext = new int[][]
        {
            {1, 15, 14, 4},
            {10, 11, 8, 5},
            {7, 6, 9, 12},
            {16, 2, 3, 13},
        };

        int[][] next = new int[][]
        {
            {-1, 0, 0, 0},
            {10, 11, 8, 0},
            {7, 6, 9, 0},
            {16, 2, 3, 0},
        };

        System.err.println("These can NOT be next'd:");
            System.err.println("\nNot Next'd: ");
            printSquare(noNext);

        System.err.println("These can be next'd");
            System.err.println("\nNext'd: ");
            printSquare(next);
            System.err.println("to \n");
            printSquare(next(next));
        System.out.println("------------------------------------------");


    }

    /**
     * Returns a string representation of a number, padded with enough zeros to
     * align properly for the current size square.
     * @param num the number to pad
     * @param size the size of the square that we are padding to
     * @return the padded string of num
     */
    static String pad(int num, int size) {
        // Determine the max value for a square of this size
        int max = size * size;
        // Determine the length of the max value
        int width = Integer.toString(max).length();
        // Convert num to string
        String result = Integer.toString(num);
        // Pad string with 0s to the desired length
        while (result.length() < width) {
            result = " " + result;
        }
        return result;
    }

    /**
     * Prints a square
     * @param square the square to print
     */
    public static void printSquare(int[][] square) 
    {
        if (square == null) {
            System.out.println("Null (no solution)");
            return;
        }
        int size = square.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(pad(square[i][j], size) + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Reads a square of a specified size from a plaintext file
     * @param filename the name of the file to read from
     * @param size the size of the square in the file
     * @return the square
     * @throws FileNotFoundException if the named file is not found
     */
    public static int[][] readSquare(String filename, int size)
                throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int[][] square = new int[size][size];
        int val = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                square[i][j] = scanner.nextInt();
            }
        }
        return square;
    }

    /**
     * Solves the magic square
     * @param square the partial solution
     * @return the solution, or null if none
     */
    public static int[][] solve(int[][] square) 
    {
        square = absoluteValue(square);
        if (reject(square)) return null;
        if (isFullSolution(square)) return square;
        int[][] attempt = extend(square);
        while (attempt != null) {
            int[][] solution = solve(attempt);
            if (solution != null) return solution;
            attempt = next(attempt);
        }
        return null;
    }

    public static void main(String[] args) 
    {
        if (args.length >= 1 && args[0].equals("-t")) {
            System.out.println("Running tests...");
            testIsFullSolution();
            testReject();
            testExtend();
            testNext();
        } else if (args.length >= 1) {
            try {
                // First get the specified size
                int size = Integer.parseInt(args[0]);

                int[][] square;
                if (args.length >= 2) {
                    // Read the square from the file
                    square = readSquare(args[1], size);
                } else {
                    // Initialize to a blank square
                    square = new int[size][size];
                }

                System.out.println("Initial square:");
                printSquare(square);

                System.out.println("\nSolution:");
                int[][] result = solve(square);
                printSquare(result);
            } catch (NumberFormatException e) {
                // This happens if the first argument isn't an int
                System.err.println("First argument must be the size");
            } catch (FileNotFoundException e) {
                // This happens if the second argument isn't an existing file
                System.err.println("File " + args[1] + " not found");
            }
        } else 
        {
            System.err.println("See usage in assignment description");
        }
    }
}