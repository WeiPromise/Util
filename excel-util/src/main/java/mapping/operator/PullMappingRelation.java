package mapping.operator;

import mapping.constant.ElementType;
import mapping.jdbcUtil.DBMysqlUtil;
import mapping.model.*;
import mapping.utils.RecommendExcelWrite;
import mapping.utils.TargetExcelRead;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by leiwei on 2020/8/5 18:09
 */
public class PullMappingRelation {

    private static final Logger LOGGER = Logger.getLogger(PullMappingRelation.class);

    private final   static List<String> TIMETYPE= Arrays.asList("DATE", "DATETIME","TIMESTAMP");

    public static void pull(RunParam runParam) throws Exception {

        LOGGER.info("检索目标表属性");
        Pair<List<TargetTableEntity>, List<TargetTable>> targetTable = TargetExcelRead.getTargetTable(runParam);


        LOGGER.info("检索原始表信息");
        DBMysqlUtil dbMysqlUtil = new DBMysqlUtil(runParam.getUrl(), runParam.getUser(), runParam.getPassword());
        //DBMysqlUtil dbMysqlUtil = new DBMysqlUtil("jdbc:mysql://127.0.0.1:3306/cona3_lw?characterEncoding=UTF-8", "root", "888520");
        String originTableSql="SELECT `origin_table_id`,`data_source_info_id`,`tb_name_en`,`tb_name_cn` from `origin_table`";
        if(runParam.getDataSourceInfoIds()!=null){
            String ids = runParam.getDataSourceInfoIds().replaceAll("，", ",");
            originTableSql="SELECT `origin_table_id`,`data_source_info_id`,`tb_name_en`,`tb_name_cn` from origin_table where `data_source_info_id` in ("+ids+")";
        }
        List<OriginTable> originTables = dbMysqlUtil.selectOriginTable(originTableSql);

        List<RecommendMappingResult> recommendMappingResults=new ArrayList<>();

        Map<Integer,List<OriginAttr>> OriginAttrMap=new HashMap<>();

        List<TargetTableEntity> targetTableEntities = targetTable.getKey();

        Map<String, List<TargetTableEntity>> collect = targetTableEntities.stream().collect(Collectors.groupingBy(TargetTableEntity::getNameEn));

        LOGGER.info("开始匹配可建立映射的原始表");
        for (OriginTable originTable : originTables) {
            LOGGER.info(originTable);

            String originAttrSql="SELECT * from origin_attr where origin_table_id="+originTable.getOriginTableId();
            List<OriginAttr> originAttrs = dbMysqlUtil.selectOriginAttr(originAttrSql);
            //后续使用
            OriginAttrMap.put(originTable.getOriginTableId(),originAttrs);

            for (List<TargetTableEntity> value : collect.values()) {
                if(value.size()>1){
                    LOGGER.info(value.get(0).getNameEn()+"有多个主键，N:"+value.size());
                    boolean pa=true;
                    Map<String,OriginAttr> map1=new HashMap<>();

                    for (TargetTableEntity targetTableEntity : value) {
                        String regular = targetTableEntity.getRegular();
                        Pattern pattern = Pattern.compile(regular);
                        int patt=0;
                        la:
                        for (OriginAttr originAttr : originAttrs) {
                            for (String sample : originAttr.getSamples()) {
                                if(StringUtils.isNotBlank(sample)&&pattern.matcher(sample).matches()){
                                    patt+=1;
                                    map1.put(targetTableEntity.getNameEn()+"_"+targetTableEntity.getPrimaryEn(),originAttr);
                                    break la;
                                }
                            }
                        }
                        if(patt==0)pa=false;
                    }
                    if(pa){
                       for (TargetTableEntity targetTableEntity : value) {
                            RecommendMappingResult recommendMappingResult=new RecommendMappingResult();
                            recommendMappingResult.setType(ElementType.ENTITY);
                            recommendMappingResult.setTargetTableNameEn(targetTableEntity.getNameEn());
                            recommendMappingResult.setDataSourceInfoId(originTable.getDataSourceInfoId());
                            recommendMappingResult.setOriginTableId(originTable.getOriginTableId());
                            recommendMappingResult.setOriginTableNameEn(originTable.getTbNameEn());
                            recommendMappingResult.setOriginTableNameCn(originTable.getTbNameCn());
                            recommendMappingResult.setPrimaryEn(targetTableEntity.getPrimaryEn());
                            recommendMappingResult.setPrimaryCn(targetTableEntity.getPrimaryCn());
                            OriginAttr attr = map1.get(targetTableEntity.getNameEn() + "_" + targetTableEntity.getPrimaryEn());
                            recommendMappingResult.setOriginTableMappingAttrId(attr.getOriginAttrId());
                            recommendMappingResult.setOriginTableMappingAttrNameEn(attr.getAttrNameEn());
                            recommendMappingResult.setOriginTableMappingAttrNameCn(attr.getAttrNameCn());
                            recommendMappingResult.setOriginTableMappingAttrColumnType(attr.getColumnType());
                            recommendMappingResults.add(recommendMappingResult);
                        }
                    }
                }else {

                    TargetTableEntity targetTableEntity=value.get(0);
                    String regular = targetTableEntity.getRegular();
                    Pattern pattern = Pattern.compile(regular);
                    int patt=0;
                    OriginAttr originAttr0=null;
                    la:
                    for (OriginAttr originAttr : originAttrs) {
                        for (String sample : originAttr.getSamples()) {
                            if(StringUtils.isNotBlank(sample)&&pattern.matcher(sample).matches()){
                                patt+=1;
                                originAttr0=originAttr;
                                break la;
                            }
                        }
                    }
                    if(patt>0){
                         RecommendMappingResult recommendMappingResult=new RecommendMappingResult();
                        recommendMappingResult.setType(ElementType.ENTITY);
                        recommendMappingResult.setTargetTableNameEn(targetTableEntity.getNameEn());
                        recommendMappingResult.setDataSourceInfoId(originTable.getDataSourceInfoId());
                        recommendMappingResult.setOriginTableId(originTable.getOriginTableId());
                        recommendMappingResult.setOriginTableNameEn(originTable.getTbNameEn());
                        recommendMappingResult.setOriginTableNameCn(originTable.getTbNameCn());
                        recommendMappingResult.setPrimaryEn(targetTableEntity.getPrimaryEn());
                        recommendMappingResult.setPrimaryCn(targetTableEntity.getPrimaryCn());
                        recommendMappingResult.setOriginTableMappingAttrId(originAttr0.getOriginAttrId());
                        recommendMappingResult.setOriginTableMappingAttrNameEn(originAttr0.getAttrNameEn());
                        recommendMappingResult.setOriginTableMappingAttrNameCn(originAttr0.getAttrNameCn());
                        recommendMappingResult.setOriginTableMappingAttrColumnType(originAttr0.getColumnType());
                        recommendMappingResults.add(recommendMappingResult);
                    }


                }
            }
        }

        //提出实体出来
        Map<String, List<RecommendMappingResult>> targetMappings = recommendMappingResults.stream().collect(Collectors.groupingBy(RecommendMappingResult::getTargetTableNameEn));
        LOGGER.info("可提出推荐目标表实体映射数量："+targetMappings.size());
        //处理关系/事件
        List<TargetTable> value = targetTable.getValue();
        for (TargetTable table : value) {
            //从主体入手
            String subjectNameEn = table.getSubjectNameEn();
            List<RecommendMappingResult> recommendMappingResultsSubject = targetMappings.get(subjectNameEn);
            if(recommendMappingResultsSubject==null)continue;

            Map<Integer, List<RecommendMappingResult>> listMap = recommendMappingResultsSubject.stream().collect(Collectors.groupingBy(RecommendMappingResult::getOriginTableId));

            for (List<RecommendMappingResult> results : listMap.values()) {

                List<Integer> usedAttr = results.stream().map(RecommendMappingResult::getOriginTableMappingAttrId).collect(Collectors.toList());
                RecommendMappingResult recommendMappingEntityResult = results.get(0);
                Integer originTableId = recommendMappingEntityResult.getOriginTableId();
                List<OriginAttr> originAttrs = OriginAttrMap.get(originTableId);
                Map<Integer, OriginAttr> attrMap = originAttrs.stream().collect(Collectors.toMap(OriginAttr::getOriginAttrId, Function.identity()));

                //验证是不是事件，有没有时间字段
                if(table.getType()==ElementType.EVENT){
                    boolean haveTime=false;
                    for (OriginAttr originAttr : originAttrs) {
                        if (TIMETYPE.contains(originAttr.getColumnType())) {
                            haveTime = true;
                            LOGGER.info("原始表："+recommendMappingEntityResult.getOriginTableNameCn()+"："+recommendMappingEntityResult.getOriginTableNameEn()+"有时间字段："+originAttr.getAttrNameEn());
                            break;
                        }
                    }
                    if(!haveTime)continue;
                }

                //验证客体
                String objectNameEn = table.getObjectNameEn();
                List<RecommendMappingResult> recommendMappingResultsObject = targetMappings.get(objectNameEn);
                if(recommendMappingResultsObject==null)continue ;
                RecommendMappingResult recommendMappingResultOb=null;
                for (RecommendMappingResult recommendMappingResultObject : recommendMappingResultsObject) {
                    if(recommendMappingResultObject.getOriginTableId().equals(originTableId)){
                        recommendMappingResultOb=recommendMappingResultObject;
                        LOGGER.info("原始表："+recommendMappingEntityResult.getOriginTableNameCn()+"："+recommendMappingEntityResult.getOriginTableNameEn()+"和目标表："+table.getNameCn()+":"+table.getNameEn()+"的主客体都可以建立了映射");
                        break;
                    }
                }
                if(recommendMappingResultOb==null){
                    LOGGER.warn("没有找到能和目标表"+table.getNameCn()+":"+table.getNameEn()+"的主客体都可以建立了映射的原始表");
                    continue;
                }

                //验证是否有两个字段（people）
                String targetTableNameEn = recommendMappingResultOb.getTargetTableNameEn();
                //获取正则
                List<TargetTableEntity> targetTableEntities1 = collect.get(targetTableNameEn);

                Map<String,OriginAttr> objectMap=new HashMap<>();
                for (TargetTableEntity targetTableEntity : targetTableEntities1) {
                    String regular = targetTableEntity.getRegular();
                    Pattern pattern = Pattern.compile(regular);
                    la:
                    for (OriginAttr originAttr : originAttrs) {
                        if(usedAttr.contains(originAttr.getOriginAttrId()))continue ;
                        for (String sample : originAttr.getSamples()) {
                            if(StringUtils.isNotBlank(sample)&&pattern.matcher(sample).matches()){
                                objectMap.put(targetTableEntity.getNameEn()+"_"+targetTableEntity.getPrimaryEn(),originAttr);
                                break la;
                            }
                        }
                    }
                }

                if(objectMap.size()!=targetTableEntities1.size()){
                    LOGGER.warn("目标表"+table.getNameEn()+"客体主键匹配字段数："+objectMap.size()+"客体主键数："+targetTableEntities1.size());
                    continue;
                }
                //构建主体
                for (Integer integer : usedAttr) {
                    OriginAttr originAttr = attrMap.get(integer);
                    if(originAttr==null)continue;
                    RecommendMappingResult recommendMappingResultOne=new RecommendMappingResult();
                    recommendMappingResultOne.setType(table.getType());
                    recommendMappingResultOne.setTargetTableNameEn(table.getNameEn());
                    recommendMappingResultOne.setDataSourceInfoId(recommendMappingEntityResult.getDataSourceInfoId());
                    recommendMappingResultOne.setOriginTableId(recommendMappingEntityResult.getOriginTableId());
                    recommendMappingResultOne.setOriginTableNameEn(recommendMappingEntityResult.getOriginTableNameEn());
                    recommendMappingResultOne.setOriginTableNameCn(recommendMappingEntityResult.getOriginTableNameCn());
                    recommendMappingResultOne.setPrimaryEn(table.getSubjet());
                    recommendMappingResultOne.setPrimaryCn(recommendMappingEntityResult.getPrimaryCn());
                    recommendMappingResultOne.setOriginTableMappingAttrId(originAttr.getOriginAttrId());
                    recommendMappingResultOne.setOriginTableMappingAttrNameEn(originAttr.getAttrNameEn());
                    recommendMappingResultOne.setOriginTableMappingAttrNameCn(originAttr.getAttrNameCn());
                    recommendMappingResultOne.setOriginTableMappingAttrColumnType(originAttr.getColumnType());
                    recommendMappingResults.add(recommendMappingResultOne);
                }
                //构建客体

                for (TargetTableEntity targetTableEntityObject : targetTableEntities1) {
                    OriginAttr originAttr=objectMap.get(targetTableEntityObject.getNameEn()+"_"+targetTableEntityObject.getPrimaryEn());
                    RecommendMappingResult recommendMappingResultTwo=new RecommendMappingResult();
                    recommendMappingResultTwo.setType(table.getType());
                    recommendMappingResultTwo.setTargetTableNameEn(table.getNameEn());
                    recommendMappingResultTwo.setDataSourceInfoId(recommendMappingEntityResult.getDataSourceInfoId());
                    recommendMappingResultTwo.setOriginTableId(recommendMappingEntityResult.getOriginTableId());
                    recommendMappingResultTwo.setOriginTableNameEn(recommendMappingEntityResult.getOriginTableNameEn());
                    recommendMappingResultTwo.setOriginTableNameCn(recommendMappingEntityResult.getOriginTableNameCn());
                    recommendMappingResultTwo.setPrimaryEn(table.getObject());
                    recommendMappingResultTwo.setPrimaryCn(targetTableEntityObject.getPrimaryCn());
                    recommendMappingResultTwo.setOriginTableMappingAttrId(originAttr.getOriginAttrId());
                    recommendMappingResultTwo.setOriginTableMappingAttrNameEn(originAttr.getAttrNameEn());
                    recommendMappingResultTwo.setOriginTableMappingAttrNameCn(originAttr.getAttrNameCn());
                    recommendMappingResultTwo.setOriginTableMappingAttrColumnType(originAttr.getColumnType());
                    recommendMappingResults.add(recommendMappingResultTwo);
                }

            }

        }
        Map<String, List<RecommendMappingResult>> count = recommendMappingResults.stream().collect(Collectors.groupingBy(RecommendMappingResult::getTargetTableNameEn));
        LOGGER.info("构建检索完成，可构建映射数量："+count.size());

        /*for (RecommendMappingResult recommendMappingResult : recommendMappingResults) {
            LOGGER.info(recommendMappingResult);
        }*/
        LOGGER.info("开始导出推荐映射excel表格："+runParam.getExcelPath());
        RecommendExcelWrite.writeRecommendMapping(recommendMappingResults,runParam.getExcelPath());

    }

    /*public static void main(String[] args) throws Exception {
        pull(null);
    }*/
}
