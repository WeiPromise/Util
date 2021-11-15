package com.promise;

/**
 * Created by leiwei on 2021/3/29 20:45
 */
public class MaxSequence {

    static int[] a=new int[]{1,-2,3,5,-4,3};

    static int start=0;
    static int end=0;

    public static void main(String[] args) {

        findMaxSum(a);
        for(int i=start;i<=end;i++){
            System.out.print(a[i]+",");
        }


    }

    private static void findMaxSum(int[] arr){
        int sum,max,i;
        sum = max = arr[0];
        for(i=1;i<arr.length;i++){
            if(sum<0){
                sum=arr[i];
                start=end=i;
            }else {
                sum+=arr[i];
            }
            if(sum>max){
                max=sum;
                end=i;
            }
        }

        System.out.println(max);
    }






}
