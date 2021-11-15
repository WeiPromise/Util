#!/bin/bash

#jar包的存放路径
toolsDir=$(cd `dirname $0`;pwd)
rootDir=$(cd ${toolsDir}/../..;pwd)
jarPath=${rootDir}/executor-jar/cona-target-recommend-mapping-jar-with-dependencies.jar


javaCmd="java -classpath $jarPath scopa.cona.target.recommend.mapping.RecommendMapping $@"
echo "java命令: ${javaCmd}"
echo ""
${javaCmd}