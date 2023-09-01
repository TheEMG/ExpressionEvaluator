# ExpressionEvaluator
Description: Program builds a polynomial and combines like terms,using a linked list. The linked 
 list traverses the  list in order to display the appropriate output and in order to 
 insert terms into descending order.The last digit of a line of the input file("input-1" )will then be used to
 evaluate the polynomial.
- For the "input-1"  the structure
  -    The first value of each integer pair will represent the coefficient of a term
and the second value will indicate the exponent of that term.  Two - - will terminate the program. The following is an example of what the output should be. 

        5 1 2 3 6 0 -3 2 -1 -1 3

        2*X^3 + (-3)*X^2 + 5*X + 6 = 48 FOR X = 3


## What did I learn
- The implementation of the ADT linked list
- How to traverse the linked list based on the degree of exponent and coefficient
- IF you don't overide the toString method you will get a hashcode location
- String builder used for efficiently constructing strings by appending multiple smaller strings together.
  Regardless of where it is created (in different methods or classes),
   accumulates the strings in memory. This allows you to efficiently build strings without creating multiple string objects.
