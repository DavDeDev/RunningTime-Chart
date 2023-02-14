/*-----------------------------------------------
|   Package: PACKAGE_NAME
|   Project: Lesson4Examples
|  Creation Date: 2/1/2023
|   Language: Java
|   
|   Author: David
|   Email: pietrocoladavid@gmail.com
|   
|   Description: 
|
*-----------------------------------------------*/

public class Exercise3 {
    public static void main(String[] args) {
        //An array A contains n−1 unique integers in the range [0,n−1], that is,
        // there is one number from this range that is not in A. Design an O(n)-time algorithm for finding that number.
        // You are only allowed to use O(1) additional space besides the array A itself. Write the java method that implements
        // this algorithm and a main method to test it.
        //Hint: Numbers in [0, n-1] form an arithmetic progression whose sum is known.

        int[] A = {0, 1, 2, 3, 4,  6, 7, 8,9}; //5 is missing

        System.out.println("The missing number is: " + (findMissingNumber(A)));

    }

    public static int findMissingNumber(int[] A) {
        int n = A.length; // 1 operation
        int sum = 0;    // 1 operation
        for (int i = 0; i < n; i++) { // 1 op + n ops + n-1 ops => n ops
            sum += A[i]; // n ops
        }
        int sumOfRange = (n * (n + 1)) / 2; // 1 op
        return sumOfRange - sum; // 1 op

    } // 1 op + 1 op + n ops + 1 op + 1 op => n ops => O(n)
}
