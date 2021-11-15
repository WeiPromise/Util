package com.promise.util

import org.apache.hadoop.conf.Configuration
import org.apache.log4j.Logger
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/*
 * Created by leiwei on 2020/11/6 11:24      
 */
object sparkSql {

    private val logger = Logger.getLogger(this.getClass)

    private val USER_PRINCIPAL = "spark.yarn.principal"

    private val USER_KEYTABLE_FILE = "spark.yarn.keytab"


    def initContextWithSpark():SparkSession = {

        val warehouseLocation = "spark-warehouse"

        val conf = new Configuration()



        val spark = SparkSession
                .builder()
                .appName("Spark Hive Example")
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .enableHiveSupport()
                .getOrCreate()









    }



}
