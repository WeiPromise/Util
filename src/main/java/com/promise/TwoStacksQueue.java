package com.promise;

import java.util.Stack;

/**
 * Created by leiwei on 2021/3/26 19:46
 * <p>
 * 19:34
 * 面试官
 * 由两个栈组成的队列
 * 用两个栈实现队列，支持队列的基本操作。
 * 输入描述
 * 第一行输入一个整数N，表示对队列进行的操作总数。
 * <p>
 * 下面N行每行输入一个字符串S，表示操作的种类。
 * <p>
 * 如果S为"add"，则后面还有一个整数X表示向队列尾部加入整数X。
 * <p>
 * 如果S为"poll"，则表示弹出队列头部操作。
 * <p>
 * 如果S为"peek"，则表示询问当前队列中头部元素是多少。
 * 输出描述
 * 对于每一个为"peek"的操作，输出一行表示当前队列中头部元素是多少。
 * 示例1
 * 输入
 * 6
 * add 1
 * add 2
 * add 3
 * peek
 * poll
 * peek
 * 输出
 * 1
 * 2
 */
public class TwoStacksQueue {


    private Stack<Integer> stackPush;//压入数据栈
    private Stack<Integer> stackPop; //弹出数据栈

    public TwoStacksQueue() {
        this.stackPop = new Stack<>();
        this.stackPush = new Stack<>();
    }

    /**
     * 入队操作
     * 直接将数据压入压入数据栈
     * @param push
     */
    public void push(int push) {
        this.stackPush.push(push);
    }


    /**
     * 出队操作
     * @return
     */
    public int poll() throws Exception {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new Exception("队列中没有数据");
        } else if (stackPop.isEmpty()) {
            //弹出数据栈为空，可以将整个压入数据栈中的数据倒入弹出数据栈
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    /**
     * 返回队头元素
     * @return
     * @throws Exception
     */
    public int peek() throws Exception {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new Exception("队列中没有数据");
        }else if (stackPop.isEmpty()) {
            //弹出数据栈为空，可以将整个压入数据栈中的数据倒入弹出数据栈
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }


    public static void main(String[] args) throws Exception {
        TwoStacksQueue twoStacksQueue = new TwoStacksQueue();
        int p = 0;
        for (; p < 100; p++) {
            int i = (int) (Math.random() * 1000);
            twoStacksQueue.push(i);
            System.out.println(i + "入队" );
        }
        for (; p > 1; p--) {
            int pop = twoStacksQueue.poll();
            System.out.println("出队元素:" + pop );
        }
    }


}
