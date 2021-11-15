package com.promise;

/**
 * Created by leiwei on 2021/4/8 19:48
 *
 * 二分查找的变异，在循环数组中查找给定某个target的index，要求时间复杂度为：O(logn) 
 *
 * before:
 * 1，3，4，5，7，8
 *
 * after:
 * 5，7，8，1，3，4
 *
 * 函数头：
 * int binsearch(int array[], int size, int target){ } 
 * input : array = [5，7，8，1，3，4]；
 * target=3,
 * output: index=4
 */
public class MyBinarySearch {


    public static void main(String[] args) {
        int[] arr = {9,10,11,12,1,2,3,4,5,6,7,8};
        Integer index = sortSearch(arr, 0, arr.length - 1, 1);
        System.out.println(index);

    }

    private static Integer sortSearch(int[] arr,int low,int high,int target){
        if(arr==null||arr.length==0){
            return -1;
        }

        if(low>high){
            return -1;
        }

        int mid=(high+low)/2;

        if(arr[mid]==target){
            return mid;
        }else if(arr[mid]>=arr[low]){
            if(target>arr[mid]||target<arr[low]){
                low=mid+1;
                return sortSearch(arr,low,high,target);
            }else {
                high=mid-1;
                return sortSearch(arr,low,high,target);
            }
        }  else {
            if(target<arr[mid]||target>=arr[low]){
                high=mid-1;
                return sortSearch(arr,low,high,target);
            }else {
                low=mid+1;
                return sortSearch(arr,low,high,target);
            }

        }

    }
}
