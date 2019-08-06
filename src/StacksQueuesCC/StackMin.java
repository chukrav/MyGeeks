package StacksQueuesCC;

import java.util.Stack;

/*3.2 Stack Min: How would you design a stack which, in addition to push and pop, has a function min
        which returns the minimum element? Push, pop and min should all operate in 0(1) time
        To further understand this question, let's walk through it with a short example:
        push(5); // stack is {5}, min is 5
        push(6); // stack is {6, 5}, min is 5
        push(3); // stack is {3, 6, 5}, min is 3
        push(7); // stack is {7, 3, 6, 5}, min is 3
        pop(); // pops 7. stack is {3, 6, 5}, min is 3
        pop(); // pops 3. stack is {6, 5}. min is 5.

    pp 243    */
public class StackMin {

    public static class StackWithMin extends Stack<NodeWithMin> {
        public void push(int value) {
            int newMin = Math.min(value, min());
            super.push(new NodeWithMin(value, newMin));
        }

        public int min() {
            if (this.isEmpty()) {
                return Integer.MAX_VALUE; // Error value
            } else {
                return peek().min;
            }
        }
    }

    static class NodeWithMin {
        public int value;
        public int min;
        public NodeWithMin(int v, int min){
            value = v;
            this.min = min;
        }
    }

    public static void main(String[] args) {

//        StackWithMin stack = new StackWithMin();
        StackWithMin2 stack = new StackWithMin2();
        stack.push(5);
        stack.push(6);
        stack.push(3);
        stack.push(7);
        System.out.println("min: "+stack.min());
        stack.pop();
        System.out.println("min: "+stack.min());
        stack.pop();
        System.out.println("min: "+stack.min());


    }

    //    We can (maybe) do a bit better than this by using an additional stack which keeps track of the mins.
    public static class StackWithMin2 extends Stack<Integer> {
        Stack<Integer> s2;
        public StackWithMin2() {
            s2 = new Stack<Integer>();
        }

        public void push(int value){
            if (value <= min()) {
                s2.push(value);
            }
            super.push(value);
        }

        public Integer pop() {
            int value = super.pop();
            if (value == min()) {
                s2.pop();
            }
            return value;
        }

        public int min() {
            if (s2.isEmpty()) {
                return Integer.MAX_VALUE;
            } else {
                return s2.peek();
            }
        }
    }

}
