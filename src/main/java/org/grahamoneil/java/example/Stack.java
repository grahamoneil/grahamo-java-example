package org.grahamoneil.java.example;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Stack</h1>
 * The Stack class is designed to populate an ArrayList of Arrays.
 * Each array has a size equal to the value of the ARRAY_SIZE class member.
 * The values in the Arrays must be in numeric order.
 *
 * @author  Graham O'Neil
 * @version 1.0
 * @since   2020-10-09
 */

public class Stack {
    public static final int ARRAY_SIZE = 3;
    public final List<String[]> stack;
    public int arrayListPosition;
    public int positionInArray;

    public Stack() {
        stack = new ArrayList<>();
    }

    /**
     * This method is used to calculate the position in the stack for a given integer.
     * For example 10 would have arrayListPosition=3 and positionInArray=0
     */
    private void calculatePositions(Integer i) {
        this.arrayListPosition = i / Stack.ARRAY_SIZE;
        this.positionInArray = i % Stack.ARRAY_SIZE;
        if (this.positionInArray == 0) {
            this.arrayListPosition = this.arrayListPosition - 1;
            this.positionInArray = Stack.ARRAY_SIZE;
        }
        this.positionInArray = this.positionInArray - 1;
    }

    /**
     * This method is used to remove a value from an array within the stack class member.
     * If a value passed in does not exist it will be ignored.
     */
    public void pop(Integer i) {
        this.calculatePositions(i);
        this.removeFromStack(i);
    }

    /**
     * This method is used to add a value to an array within the stack class member.
     * If a value currently exits it will be ignored.
     */
    public void push(Integer i) {
        this.calculatePositions(i);
        this.updateOrAddArrayToStack(i);
    }

    /**
     * This method will remove all Arrays at the end of the ArrayList is all values are null.
     */
    private void removeEndingNullArrays() {
        for (int x = this.stack.size() - 1; x >= 0; x--) {
            for (int y = 0; y < Stack.ARRAY_SIZE; y++) {
                if (this.stack.get(x)[y] != null) {
                    return;
                }
            }
            this.stack.remove(x);
        }
    }

    /**
     * This method will remove a value from an Array within the stack ArrayList if it is present.
     */
    private void removeFromStack(Integer i) {
        if (this.stack.size() >= this.arrayListPosition) {
            if (this.stack.get(this.arrayListPosition)[this.positionInArray].equals(i.toString())) {
                this.stack.get(this.arrayListPosition)[this.positionInArray] = null;
            }
        }
        this.removeEndingNullArrays();
    }

    /**
     * This method will add an Array to the stack ArrayList and update the value in the Array
     * if the current value is null.
     */
    private void updateOrAddArrayToStack(Integer i) {
        if (this.stack.size() == 0) {
            String[] myArray = new String[Stack.ARRAY_SIZE];
            for (int y = 0; y < positionInArray + 1; y++) {
                if (y == positionInArray) {
                    myArray[y] = i.toString();
                }
            }
            this.stack.add(0, myArray);
        } else {
            if (this.arrayListPosition >= this.stack.size()) {
                for (int x = this.stack.size(); x < this.arrayListPosition + 1; x++) {
                    String[] myArray = new String[Stack.ARRAY_SIZE];
                    for (int y = 0; y < positionInArray + 1; y++) {
                        if (y == positionInArray && this.stack.size() == this.arrayListPosition) {
                            myArray[y] = i.toString();
                        }
                    }
                    this.stack.add(x, myArray);
                }
            }
            if (this.stack.get(this.arrayListPosition)[this.positionInArray] == null) {
                this.stack.get(this.arrayListPosition)[this.positionInArray] = i.toString();
            }
        }
    }

}
