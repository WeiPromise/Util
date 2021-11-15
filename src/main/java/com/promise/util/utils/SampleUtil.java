package com.promise.util.utils;

import com.promise.util.dataUtil.RandomDateUtil;
import javafx.util.Pair;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by leiwei on 2019/7/2 14:36
 */
public class SampleUtil {

    private final static String[] XZQh = {"东城区","西城区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴区","怀柔区","平谷县","密云县","延庆县"};

    private final static String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
            "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
            "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
            "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
    private final static  String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
    private final static  String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";

    private final static  String[] sex1={"男","女"};

    private final static  String[] yx={"有效","无效"};

    private final static  String[] hyzk={"未婚","离异","已婚"};

    private final static  String[] fzqksm={"涉毒重点人","在逃杀人嫌疑犯","涉嫌诈骗嫌疑犯","经济犯","null"};


    private static List<People> getPeople(int num){

        List<People> peoples=new ArrayList<>();

        for(int j=0;j<num;j++){
            People people=new People();
            Random random = new Random();
            int index = random.nextInt(Surname.length - 1);
            String xing = Surname[index]; //获得一个随机的姓氏
            int i = random.nextInt(3);//可以根据这个数设置产生的男女比例
            Pair<String, String> name = getName(random, xing, i);
            Pair<String, String> fqname = getName(random, xing, 1);
            people.setXm(name.getKey());
            people.setFqxm(fqname.getKey());
            int yxIndex = random.nextInt(2);
            people.setZjyxq(yx[yxIndex]);
            int hyzkIndex = random.nextInt(3);
            people.setHyzk(hyzk[hyzkIndex]);
            int dz = random.nextInt(16);
            CodeEnum codeEnum = CodeEnum.get(dz);
            people.setDjdpcs(codeEnum.getName()+"派出所");
            people.setZjlx("身份证");
            people.setFzqksm(fzqksm[random.nextInt(5)]);

            String year = GenerateSfzh.getYear(0);
            String month = GenerateSfzh.getMonth();
            String day = GenerateSfzh.getDay();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String format = sdf.format(new Date());
            people.setNl(Integer.parseInt(format)-Integer.parseInt(year)+"");
            String sfzh=""+codeEnum.getCode()+year+month+day+GenerateSfzh.getSequence()+GenerateSfzh.getSex(name.getValue());
            String verify = GenerateSfzh.getVerify(sfzh);
            people.setZjhm(sfzh+verify);

            String fqyear=""+(Integer.parseInt(year)-(25+random.nextInt(10)));

            String fqsfzh=""+codeEnum.getCode()+fqyear+GenerateSfzh.getMonth()+GenerateSfzh.getDay()+GenerateSfzh.getSequence()+GenerateSfzh.getSex("男");
            String fqverify = GenerateSfzh.getVerify(fqsfzh);
            people.setFqsfzh(fqsfzh+fqverify);

            String lusj= RandomDateUtil.randomLocalDateTime(-1000,1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));

            people.setLusj(lusj);

            peoples.add(people);

            if(peoples.size()%100000==0){
                System.out.println("现在生产了："+peoples.size()+"条数据了");
            }
        }

        return peoples;

    }



    private static Pair<String,String> getName(Random random, String xing, int i) {
        String name;
        String sex;
        if(i==2){
            sex=sex1[1];
            int j = random.nextInt(girl.length()-2);
            if (j % 2 == 0) {
                name = xing + girl.substring(j, j + 2);
            } else {
                name = xing + girl.substring(j, j + 1);
            }

        }
        else{
            sex=sex1[0];
            int j = random.nextInt(boy.length()-2);
            if (j % 2 == 0) {
                name = xing + boy.substring(j, j + 2);
            } else {
                name = xing + boy.substring(j, j + 1);
            }

        }
        return new Pair<>(name,sex);
    }


    public static void main(String[] args) {
        String filePath = "./data.txt";

        for(int i=0;i<1;i++){

            List<People> people = getPeople(100000);


            FileWriter fwriter = null;

            try {
                // true表示不覆盖原来的内容，而是加到文件的后面。若要覆盖原来的内容，直接省略这个参数就好
                fwriter = new FileWriter(filePath, true);
                //fwriter = new FileWriter(filePath);
                String t="\t";
                int j=0;
                for (People person : people) {
                    j++;
                    if(j%100000==0){
                        System.out.println("现在写入了："+j*(i+1)+"条数据");
                    }
                    StringBuilder sb =new StringBuilder();
                    sb.append(person.getXm()).append(t).append(person.getNl()).append(t).append(person.getHyzk()).append(t).append(person.getZjyxq()).append(t).append(person.getDjdpcs()).append(t).append(person.getZjhm()).append(t).append(person.getZjlx()).append(t).append(person.getFzqksm()).append(t).append(person.getFqsfzh()).append(t).append(person.getFqxm()).append(t).append(person.getLusj()).append("\n");
                    fwriter.write(sb.toString());
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    fwriter.flush();
                    fwriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }


        }

    }

}
