/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


/**
 * Code for end-of-chapter exercises on asymptotics.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Exercises {


    //running time is O(n)

    /**
     * Returns the sum of the integers in given array.
     */
    /*a)	Give a big-Oh characterization, in terms of n, of the running time of the
   example1 method from Exercises.java class in Lesson 4 examples.*/
    public static int example1(int[] arr) {
        int n = arr.length, total = 0;                           //! 1 op + 1 op+ 1 op
        for (int j = 0; j < n; j++)       // loop from 0 to n-1    //! 1 op + n ops + n-1 ops => n ops
            total += arr[j];                                       //! n ops
        return total;                                            //! 1 op
    }
    //! => 3 + 2n = O(n)


    /**
     * Returns the sum of the integers with even index in given array.
     */
  /*b)	Give a big-Oh characterization, in terms of n, of the running time of the
  example2 method from Exercises.java class in Lesson 4 examples.*/
    public static int example2(int[] arr) {
        int n = arr.length, total = 0;                               //! 1 op + 1 op
        for (int j = 0; j < n; j += 2)    // note the increment of 2   //! 1 op + n/2 ops + n/2-1 ops => n/2 ops
            total += arr[j];                                           //! n/2 ops
        return total;                                                 //! 1 op
    }
    //! => 3 + 2(n/2) = O(n)

    /**
     * Returns the sum of the prefix sums of given array.
     */
    /*c)	Give a big-Oh characterization, in terms of n, of the running time of the
  example3 method from Exercises.java class in Lesson 4 examples.*/
    public static int example3(int[] arr) {

        int n = arr.length; //3
        int total = 0;                            //! 1 op + 1 op

        for (int j = 0; j < n; j++)       // loop from 0 to n-1     //! 1 op + n ops + n-1 ops => n ops
            //j = 0
            //j<n => 1<3 false
            for (int k = 0; k <= j; k++)    // loop from 0 to j       //! n ops + n-1 ops + n-2 ops + ... + 1 op => n(n+1)/2 ops
                //k=3
                //k<=j 3<=2 false
                total += arr[j];  // total=6+3+3+10+10+10 => 6 steps                                   //! n(n+1)/2 ops

        return total; //42                                            //! 1 op
    }
    //! => 3 + n + n(n+1) = O(n^2)

    /**
     * Returns the sum of the prefix sums of given array.
     */
      /*d)	Give a big-Oh characterization, in terms of n, of the running time of the
  example4 method from Exercises.java class in Lesson 4 examples.*/
    public static int example4(int[] arr) {
        int n = arr.length, prefix = 0, total = 0;                  //! 1 op + 1 op + 1 op
        for (int j = 0; j < n; j++) {     // loop from 0 to n-1     //! 1 op + n ops + n-1 ops => n ops
            prefix += arr[j];                                       //! n ops
            total += prefix;                                        //! n ops
        }
        return total;                                               //! 1 op
    }
    //! => 4 + 3n = O(n)

    /**
     * Returns the number of times second array stores sum of prefix sums from first.
     */
    /*e)	Give a big-Oh characterization, in terms of n, of the running time of the
  example5 method from Exercises.java class in Lesson 4 examples.*/
    public static int example5(int[] first, int[] second) { // assume equal-length arrays
        int n = first.length, count = 0;                             //! 1 op + 1 op
        for (int i = 0; i < n; i++) {     // loop from 0 to n-1      //! 1 op + n ops + n-1 ops => n ops
            int total = 0;                                           //! n ops
            for (int j = 0; j < n; j++)     // loop from 0 to n-1    //! n ops + n-1 ops + n-2 ops + ... + 1 op => n(n+1)/2 ops
                for (int k = 0; k <= j; k++)  // loop from 0 to j    //! n(n+1)/2 ops + n(n+1)/2-1 ops + n(n+1)/2-2 ops + ... + n ops => n(n+1)(n+2)/6 ops
                    total += first[k];                               //! n(n+1)(n+2)/6 ops
            if (second[i] == total) count++;                         //! n ops
        }
        return count;                                                //! 1 op
    }
    //! => 3 + 3n + n(n+1)/2 + n(n+1)(n+2)/6 + n(n+1)(n+2)/6 = O(n^3)

    public static void main(String[] args) {
        int[]array={6,3,10};
        System.out.println(example3(array));
    }
}
