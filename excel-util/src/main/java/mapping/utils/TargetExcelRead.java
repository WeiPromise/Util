package mapping.utils;

import mapping.constant.ElementType;
import mapping.model.RunParam;
import mapping.model.TargetTable;
import mapping.model.TargetTableEntity;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by leiwei on 2020/8/5 18:23
 */
public class TargetExcelRead {

    private static final Logger LOGGER = Logger.getLogger(TargetExcelRead.class);

    public static Pair<List<TargetTableEntity>, List<TargetTable>> getTargetTable(RunParam runParam) throws Exception {

        String home = TargetExcelRead.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        LOGGER.info(home);
        //FileInputStream stream = new FileInputStream(new File(home+"/target.xlsx"));
        //FileInputStream stream = new FileInputStream(new File("C:\\Users\\Promise\\Desktop\\StandardAtlas.xlsx"));
        InputStream stream;
        if (runParam.getSourceFile() == null) {
            ClassPathResource classPathResource = new ClassPathResource("StandardAtlas.xlsx");
            stream = classPathResource.getInputStream();
        } else {
            File file = new File(runParam.getSourceFile());
            stream = new FileInputStream(file);
        }

        Workbook workbook = new XSSFWorkbook(stream);

        Sheet sheetEntity = workbook.getSheet(ElementType.ENTITY.getElementTypeCn());
        List<Integer> entitys = Collections.singletonList(7);
        List<TargetTableEntity> entityList = ExcelUtil.coverDataToBean(sheetEntity, TargetTableEntity.class, entitys);


        Sheet sheetEvent = workbook.getSheet(ElementType.EVENT.getElementTypeCn());

        List<TargetTable> targetList = ExcelUtil.coverDataToBean(sheetEvent, TargetTable.class, null);

        targetList = targetList.stream().peek(t -> t.setType(ElementType.EVENT)).collect(Collectors.toList());


        Sheet sheetRelation = workbook.getSheet(ElementType.RELATION.getElementTypeCn());

        List<TargetTable> targetRelationList = ExcelUtil.coverDataToBean(sheetRelation, TargetTable.class, null);

        targetRelationList = targetRelationList.stream().peek(t -> t.setType(ElementType.RELATION)).collect(Collectors.toList());

        targetList.addAll(targetRelationList);

        return Pair.of(entityList, targetList);
    }


    public static void main(String[] args) throws Exception {

        Pair<List<TargetTableEntity>, List<TargetTable>> targetTable = getTargetTable(new RunParam());

        List<TargetTableEntity> targetTableEntities = targetTable.getKey();
        List<TargetTable> targetTables = targetTable.getValue();

        for (TargetTableEntity targetTableEntity : targetTableEntities) {
            LOGGER.info(targetTableEntity);
        }

        for (TargetTable table : targetTables) {
            LOGGER.info(table);
        }

    }
}
