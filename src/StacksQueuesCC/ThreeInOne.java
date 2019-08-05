package StacksQueuesCC;

/*3.1 Three in One: Describe how you could use a single array to implement three stacks.
        SOLUTION
        -------- ·······----
        Like many problems, this one somewhat depends on how well we'd like to support these stacks. If we're
        okay with simply allocating a fixed amount of space for each stack, we can do that.This may mean though
        that one stack runs out of space, while the others are nearly empty.
        Alternatively, we can be flexible in our space allocation, but this significantly increases the complexity of
        the problem.

        pp 238  */

import java.util.EmptyStackException;

public class ThreeInOne {

    //    Approach 1: Fixed Division
    static class FixedMultiStack {
        private int numberOfStacks = 3;
        private int stackCapacity;
        private int[] values;
        private int[] sizes;

        public FixedMultiStack(int stackSize) {
            stackCapacity = stackSize;
            values = new int[stackSize * numberOfStacks];
            sizes = new int[numberOfStacks];
        }

        /* Push value onto stack. */
        public void push(int stackNum, int value) throws FullStackException {
             /* Check that we have space for the next element */
            if (isFull(stackNum)) {
                throw new FullStackException("Stack is full");
            }
/* Increment stack pointer and then update top value. */
            sizes[stackNum]++;
            values[indexOfTop(stackNum)] = value;
        }

        /* Pop item from top stack. */
        public int pop(int stackNum) throws EmptyStackException {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            int topindex = indexOfTop(stackNum);
            int value = values[topindex]; // Get top
            values[topindex] = 0; // Clear
            sizes[stackNum]--; // Shrink
            return value;
        }

        /* Return top element. */
        public int peek(int stackNum) throws EmptyStackException {
            if (isEmpty(stackNum)) {
                throw new EmptyStackException();
            }
            return values[indexOfTop(stackNum)];
        }

        /* Return if stack is empty. */
        public boolean isEmpty(int stackNum) {
            return sizes[stackNum] == 0;
        }

        /* Return if stack is full. */
        public boolean isFull(int stackNum) {
            return sizes[stackNum] == stackCapacity;
        }

        /* Returns index of the top of the stack. */
        private int indexOfTop(int stackNum) {
            int offset = stackNum * stackCapacity;
            int size = sizes[stackNum];
            return offset + size - 1;
        }

    }

    public static void main(String[] args) {
        FixedMultiStack stack = new FixedMultiStack(10);
//        int index = stack.indexOfTop(2);
//        System.out.println("index: "+index);
        try {
            stack.push(0,1);
            stack.push(0,2);
            stack.push(0,3);

            stack.push(1,4);
            stack.push(1,5);
            stack.push(1,6);

            stack.push(2,7);
            stack.push(2,8);
            stack.push(2,9);

        } catch (FullStackException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("stack[0].pop: "+stack.pop(0));
            System.out.println("stack[1].pop: "+stack.pop(1));
            System.out.println("stack[2].pop: "+stack.pop(2));
            System.out.println("stack[0].pop: "+stack.pop(0));
            System.out.println("stack[1].pop: "+stack.pop(1));
            System.out.println("stack[2].pop: "+stack.pop(2));

            System.out.println("stack[0].pop: "+stack.pop(0));
//            System.out.println("stack[0].pop: "+stack.pop(0));
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }

    }

//    Approach 2: Flexible Divisions



    static class FullStackException extends Exception {
        public FullStackException(String errorExcept) {
            super(errorExcept);
        }

    }
}


