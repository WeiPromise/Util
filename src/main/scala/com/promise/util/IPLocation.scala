package com.promise.util

import java.sql.{Connection, DriverManager, PreparedStatement}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/*
 * Created by leiwei on 2020/7/29 15:31      
 */
object IPLocation {

    def ip2Long(ip: String): Long={
        val fragments = ip.split("[.]")
        var ipNum = 0L
        fragments.foreach(ipflag=>{
            ipNum = ipflag.toLong | ipNum << 8L
        })
        ipNum
    }

    def binarySearch(lines: Array[(String, String, String)], ip: Long) : Int = {
        var low = 0
        var high = lines.length - 1

        while (low <= high) {
            val middle = (low + high) / 2
            if((ip >= lines(middle)._1.toLong) && (ip <= lines(middle)._2.toLong))
                return middle
            if(ip < lines(middle)._1.toLong)
                high=middle-1
            else {
                low=middle+1
            }
        }
        -1
    }

    def data2MySQL(iterator:Iterator[(String, Int)] ):Unit={

        var conn: Connection = null

        var ps : PreparedStatement = null

        val sql = "INSERT INTO location_count (location, total_count) VALUES (?, ?)"

        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/big_data?characterEncoding=utf8","root","888520")

            iterator.foreach(line => {
                ps= conn.prepareStatement(sql)
                ps.setString(1,line._1)
                ps.setInt(2,line._2)
                ps.executeUpdate()
            })
        }catch {
            case e:Exception => e.printStackTrace()
        }finally {
            if(ps !=null){
                ps.close()
            }
            if(conn !=null){
                conn.close()
            }
        }



    }

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[2]").setAppName("IPLocation")

        val sc = new SparkContext(sparkConf)

        val ipNumRange2LocationRdd = sc.textFile("C:\\Users\\Promise\\Desktop\\logs\\ip.txt").map(_.split("\\|")).map(x => (x(2), x(3), x(6)))

        val ipNumRange2LocationArray = ipNumRange2LocationRdd.collect()

        val broadCastArray = sc.broadcast(ipNumRange2LocationArray)

        val ipLogRDD: RDD[Array[String]] = sc.textFile("C:\\Users\\Promise\\Desktop\\logs\\20090121000132.394251.http.format").map(_.split("\\|"))

        val locationAndIp = ipLogRDD.map(_ (1)).mapPartitions(
            it => {
                val arr = broadCastArray.value
                it.map(ip => {
                    val ipNum = ip2Long(ip)
                    val index = binarySearch(arr, ipNum)
                    val t = arr(index)
                    (t._3, ip)
                }
                )
            }
        )

        val locationCount = locationAndIp.map(t => (t._1, 1)).reduceByKey(_ + _)

        println(locationCount.collect().toBuffer)

        locationCount.foreachPartition(data2MySQL)
        sc.stop()

    }

}
