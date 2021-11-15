package com.promise;

import java.util.Arrays;

/**
 * Created by leiwei on 2021/4/19 10:28
 */
public class MergeSort {


    public static void main(String[] args) {
        int[] arr1={1,2,3,5,7,10};
        int[] arr2={4,6,7,8,9,11,13};
        int[] arr = sort(arr1,arr2);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] arr1, int[] arr2) {
        int Max1=arr1[arr1.length-1];
        int Max2=arr2[arr2.length-1];

        int[] maxArr=Max1>Max2?arr1:arr2;
        int[] minArr=Max1>Max2?arr2:arr1;
        int maxCurIndex=maxArr.length-1;
        int minCurIndex=minArr.length-1;
        int[] result=new int[maxArr.length+minArr.length];
        int resultIndex=0;
        recursion(maxArr,minArr,result,maxCurIndex,minCurIndex,resultIndex);
        return result;


    }


    private static void recursion(int[] arr1,int[]arr2,int[] result,int maxCurIndex,int minCurIndex, int resultIndex){

        for (int i=maxCurIndex;i>=0;i--){
            if(arr1[i]>=arr2[minCurIndex]){
                result[resultIndex++]=arr1[i];
                maxCurIndex--;
            }else {
                recursion(arr2,arr1,result,minCurIndex,maxCurIndex,resultIndex);
            }
        }

        if(maxCurIndex<0){
            for (int i=minCurIndex;i>=0;i--){
                result[resultIndex++]=arr2[i];
            }
        }
    }
}
