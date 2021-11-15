package com.promise.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileStatus, FileSystem, Path}

/*
 * Created by leiwei on 2020/6/24 16:42      
 */
object HdfsFile {

    private val  CORE_SITE_PATH  = "C:\\Users\\Promise\\Desktop\\test\\core-site.xml"
    private val  HDFS_SITE_PATH  = "C:\\Users\\Promise\\Desktop\\test\\hdfs-site.xml"

    def main(args: Array[String]): Unit = {
        val conf: Configuration = new Configuration()
        conf.addResource(new Path(CORE_SITE_PATH))
        conf.addResource(new Path(HDFS_SITE_PATH))
        System.setProperty("HADOOP_USER_NAME", "hdfs")

        val fs: FileSystem = FileSystem.get(conf)

        val statuses: Array[FileStatus] = fs.listStatus(new Path("/cona3_lw"))

        statuses.foreach(f=>{
            if(f.isFile){
                println(f.getPath.getName)
            }
        })


    }

}
