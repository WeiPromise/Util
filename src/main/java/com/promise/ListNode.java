package com.promise;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            //如果链表为null或者只有一个节点，直接返回该头节点，不需要翻转
            return head;
        }
        //否则翻转，但是只处理head一个节点，将head.next委托递归给reverseList
        ListNode tail = head.next;//记录将来已翻转区域的尾节点
        ListNode newHead = reverseList(head.next);//将当前节点next后面的链表翻转，返回翻转后的头节点

        tail.next = head;//将已翻转区域的尾节点接上当前节点
        head.next = null;
        return  newHead;
    }
}
