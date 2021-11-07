public class Solution19 {
    public static void main(String[] args) throws Exception {
        TripleStack stack = new TripleStack(2);
        stack.push(0, 1);
        stack.push(0, 2);
        stack.push(1, 3);
        stack.push(1, 4);

        System.out.println(stack.peek(1));
        stack.pop(1);
        System.out.println(stack.peek(1));

    }

    static class TripleStack {
        TripleStack(int stackSize) {
            this.stackSize = stackSize;
            for (int i = 0; i < 3; i++) {
                st[i] = i * stackSize;
                size[i] = 0;
            }
            stack = new int[stackSize * 3];
        }

        int[] size = new int[3];
        int[] st = new int[3];
        int[] stack;
        int stackSize;

        boolean push(int stackNum, int value) {
            if (stackSize(stackNum) == stackSize) {
                // full, reject pushing
                return false;
            }
            stack[st[stackNum] + size[stackNum]] = value;
            size[stackNum]++;
            return true;
        } 

        int peek(int stackNum) throws Exception {
            if (stackSize(stackNum) == 0) {
                throw new Exception("empty stack peeking");
            }
            return stack[st[stackNum] + size[stackNum] - 1];
        }

        int pop(int stackNum) throws Exception {
            if (stackSize(stackNum) == 0) {
                throw new Exception("empty stack peeking");
            }
            return stack[st[stackNum] + (--size[stackNum])];
        }

        boolean isEmpty(int stackNum) {
            return stackSize(stackNum) == 0;
        }

        int stackSize(int stackNum) {
            return size[stackNum];
        }
    }
}
