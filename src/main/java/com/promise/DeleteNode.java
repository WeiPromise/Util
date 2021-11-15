package com.promise;

/**
 * Created by leiwei on 2021/4/25 19:45
 */


class Node{
    private Node next;
    private Integer value;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}


public class DeleteNode {

    public static Node deleteNode(Node node,int n){

        if(node==null)return node;

        Node fast=node.getNext();
        while (n-- > 0){
            fast=node.getNext();
        }
        if(fast==null)return node.getNext();
        Node slow=node.getNext();

        while (fast!=null){
            fast=fast.getNext();
            slow=slow.getNext();
        }
        slow.setNext(slow.getNext().getNext());

        return node;

    }


    public static void main(String[] args) {

    }

}
