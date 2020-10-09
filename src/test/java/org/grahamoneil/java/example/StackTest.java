package org.grahamoneil.java.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StackTest {
    public Stack stack = new Stack();

    @Test
    /*
      Pushes 1,2,3,4 to the stack expected value of stack is
      [[1,2,3],[4,null,null]]
     */
    void pushFourItemsToStack() {
        ArrayList<String[]> expectedStack = new ArrayList<>();
        String[] myArrayOne = new String[Stack.ARRAY_SIZE];
        myArrayOne[0] = "1";
        myArrayOne[1] = "2";
        myArrayOne[2] = "3";
        expectedStack.add(0, myArrayOne);
        String[] myArrayTwo = new String[Stack.ARRAY_SIZE];
        myArrayTwo[0] = "4";
        expectedStack.add(1, myArrayTwo);
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        this.stack.push(4);
        Assertions.assertArrayEquals(expectedStack.toArray(), this.stack.stack.toArray());
    }

    @Test
    /*
      Pushes 1,2,3 to the stack and then pops 3 expected value of stack is
      [[1,2,null]]
      */
    void pushThreeItemsPopOneItemToStack() {
        ArrayList<String[]> expectedStack = new ArrayList<>();
        String[] myArray = new String[Stack.ARRAY_SIZE];
        myArray[0] = "1";
        myArray[1] = "2";
        expectedStack.add(0, myArray);
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        this.stack.pop(3);
        Assertions.assertArrayEquals(expectedStack.toArray(), this.stack.stack.toArray());
    }

    @Test
    /*
      Pushes 1,2,3,4 to the stack and then pops 4 expected value of stack is
      [[1,2,3]]
     */
    void pushFourItemsPopOneItemToStack() {
        ArrayList<String[]> expectedStack = new ArrayList<>();
        String[] myArray = new String[Stack.ARRAY_SIZE];
        myArray[0] = "1";
        myArray[1] = "2";
        myArray[2] = "3";
        expectedStack.add(0, myArray);
        this.stack.push(1);
        this.stack.push(2);
        this.stack.push(3);
        this.stack.push(4);
        this.stack.pop(4);
        Assertions.assertArrayEquals(expectedStack.toArray(), this.stack.stack.toArray());
    }
}
