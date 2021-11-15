package com.promise.util.utils;

/**
 * Created by leiwei on 2019/7/2 15:21
 */
public class GenerateSfzh {


    final static int[] wi = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2}; //

    // verify digit
    final static int[] vi = {1,0,'X',9,8,7,6,5,4,3,2}; //

    private static int[] ai = new int[18];

    public GenerateSfzh() {
    }

    public static String getYear(int nian){
        //第 7--10位   年份：1970-1990
        int number;
        number = (int)(Math.random()*((2000+nian)-(1990+nian))+(1990+nian));
        String str=null;
        if(number<10)
        {
            //转换成字符串

            str = Integer.toString(number);
            str = "0"+number;
            //System.out.println(str);
            return str;
        }
        else{

            //System.out.println(Integer.toString(number));
            return Integer.toString(number);
        }
    }

    public static String getMonth(){
        //第11 12 位      月份：01-12

        int number ;
        number = (int)(Math.random()*(12-1)+1);
        String str=null;
        if(number<10)
        {
            //转换成字符串

            str = Integer.toString(number);
            str = "0"+number;
            //System.out.println(str);
            return str;
        }
        else{

            //System.out.println(Integer.toString(number));
            return Integer.toString(number);
        }
    }

    public static String getDay(){
        //第13   14 位    日期：01-28

        int number ;
        number = (int)(Math.random()*(28-1)+1);
        String str=null;
        if(number<10)
        {
            //转换成字符串

            str = Integer.toString(number);
            str = "0"+number;
            //System.out.println(str);
            return str;
        }
        else{

            //System.out.println(Integer.toString(number));
            return Integer.toString(number);
        }
    }

    public static String getSequence(){
        //第15  16 位        两位：01-99
        int number ;
        number = (int)(Math.random()*(99-1)+1);
        String str=null;
        if(number<10)
        {
            //转换成字符串

            str = Integer.toString(number);
            str = "0"+number;
            //System.out.println(str);
            return str;
        }
        else{

            //System.out.println(Integer.toString(number));
            return Integer.toString(number);
        }
    }

    public static String getSex(String sex){
        // 第17 位  一位性别：0-9
        if("男".equals(sex)){
            while (true){
                int number ;
                number = (int)(Math.random()*(9-0)+0);
                if(number%2==1){
                    return Integer.toString(number);
                }
            }
        }else {
            while (true){
                int number ;
                number = (int)(Math.random()*(9-0)+0);
                if(number%2==0){
                    return Integer.toString(number);
                }
            }
        }


    }

    public static  String getVerify(String eightcardid) { //eightcardid
        int remaining = 0;

        if (eightcardid.length() == 17) {
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                String k = eightcardid.substring(i, i + 1);
                ai[i] = Integer.parseInt(k);
            }

            for (int i = 0; i < 17; i++) {
                sum = sum + wi[i]*ai[i];
            }
            remaining = sum % 11;
            return remaining == 2 ? "X" : String.valueOf(vi[remaining]);

        }
        else{
            return "出错了!";
        }

    }

}
