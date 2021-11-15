package com.promise;

import java.util.Arrays;

/**
 * Created by leiwei on 2021/3/30 15:03
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, -1, 0, 6, 1, 7, 9, 4, 2};

        //构建大顶堆
        buildMaxHeap(arr,arr.length);
        //交换堆顶和当前末尾的节点，重置大顶堆
        //将剩余的元素重新构建大顶堆，其实就是调整根节点以及其调整后影响的子节点，因为其他节点之前已经满足大顶堆性质
        for(int i = arr.length-1;i>0;i--){
            swap(arr,0,i);
            heapify(arr,0,i);
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void buildMaxHeap(int[] arr, int len) {
        //找到第一个非叶子节点，然后往后排
        for (int i=(int)Math.floor(len/2)-1;i>=0;i--){
            heapify(arr,i,len);
        }

    }

    private static void heapify(int[] arr, int rootIndex, int len) {
        //找到孩子节点
        int leftIndex=2*rootIndex+1;
        int rightIndex=2*rootIndex+2;
        //找出最大数索引
        int maxIndex=rootIndex;
        if(leftIndex<len&&arr[leftIndex]>arr[maxIndex]){
            maxIndex=leftIndex;
        }
        if(rightIndex<len&&arr[rightIndex]>arr[maxIndex]){
            maxIndex=rightIndex;
        }
        if(maxIndex!=rootIndex){
            //交换数据、检查子序列
            swap(arr,rootIndex,maxIndex);
            heapify(arr,maxIndex,len);
        }

    }

    private static void swap(int[] arr, int rootIndex, int maxIndex) {

        int temp=arr[rootIndex];
        arr[rootIndex]=arr[maxIndex];
        arr[maxIndex]=temp;
    }
}
