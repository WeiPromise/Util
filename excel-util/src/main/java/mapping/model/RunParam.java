package mapping.model;

import mapping.RecommendMapping;
import mapping.annotations.ParamMeta;
import mapping.constant.OperatorModel;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.regex.Pattern;

public class RunParam {

    private static final Logger LOGGER = Logger.getLogger(RunParam.class);

    public static final String JAR_PATH = new File(RecommendMapping.class.getProtectionDomain().getCodeSource().getLocation().getFile()).getName();

    private static final Pattern PATTERN = Pattern.compile(".*\\.(xls|xlsx)");

    private OperatorModel model = null;

    private String sourceFile = null;

    private String dataSourceInfoIds = null;

    private String user = null;

    private String password = null;

    private String url = null;

    private String dbName = null;

    private String excelPath = null;

    public RunParam() {}


    public RunParam(CommandLine commandLine) {

        for (HashMap.Entry<String, Method> entry : METHOD_MAP.entrySet()) {
            String value = commandLine.getOptionValue(entry.getKey());
            if (StringUtils.isNotBlank(value)) {
                LOGGER.info(String.format("%s:%s:%s", entry.getKey(), entry.getValue().getAnnotation(ParamMeta.class).value(), value));
                Class model = entry.getValue().getParameterTypes()[0];
                if (model == OperatorModel.class) {
                    try {
                        setModel(OperatorModel.valueOf(value));
                    } catch (RuntimeException e) {
                        LOGGER.error("配置的model【" + value + "】不合法,请使用pull或push");
                        throw e;
                    }
                } else {
                    try {
                        entry.getValue().invoke(this, value);
                    } catch (ReflectiveOperationException e) {
                        LOGGER.error(e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        checkDb();

        if (model == OperatorModel.pull) {
            if (StringUtils.isBlank(getExcelPath())) {
                setExcelPath(".".concat(File.separator));
            }
        }else if(model == OperatorModel.push){
            if (StringUtils.isBlank(getSourceFile())) {
                String msg = "FilePath不能是空";
                LOGGER.error(msg);
                throw new RuntimeException(msg);
            }

        }
    }

    public void checkDb() {
        if (StringUtils.isBlank(getUrl())) {
            String msg = "url不能是空";
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }
        if (StringUtils.isBlank(getUser())) {
            String msg = "user不能是空";
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }

        if (StringUtils.isBlank(getPassword())) {
            String msg = "Password不能是空";
            LOGGER.error(msg);
            throw new RuntimeException(msg);
        }
    }


    public OperatorModel getModel() {
        return model;
    }

    @ParamMeta(value = "模式(pull(获取推荐文档)/push(构建表级映射))(必填)",order = 1)
    public void setModel(OperatorModel model) {
        this.model = model;
    }

    public String getSourceFile() {
        return sourceFile;
    }

    @ParamMeta(value = "源xlsx路径",order =6 )
    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getDataSourceInfoIds() {
        return dataSourceInfoIds;
    }

    @ParamMeta(value = "需要导出的数据源id,多个数据源逗号分隔(1,2,3),默认全部导出(push模式不需要)",order =5 )
    public void setDataSourceInfoIds(String dataSourceInfoIds) {
        this.dataSourceInfoIds = dataSourceInfoIds;
    }


    public String getUser() {
        return user;
    }

    @ParamMeta(value = "cona3库用户名(必填)",order = 3)
    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    @ParamMeta(value = "cona3库密码(必填)",order = 4)
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    @ParamMeta(value = "cona3数据库URL(如：jdbc:mysql://ws02.mlamp.cn:3306/cona3?characterEncoding=UTF-8)(必填)",order = 2)
    public void setUrl(String url) {
        this.url = url;
    }

    public String getExcelPath() {
        return excelPath;
    }

    @ParamMeta(value = "xlsx输出路径(默认当前路径)",order =7 )
    public void setExcelPath(String excelPath) {
        this.excelPath = excelPath;
    }



    private static final HashMap<String, Method> METHOD_MAP = new HashMap<>();

    public static final Options OPTIONS = new Options();

    public static class ParamCompare implements Comparator<Option> {

        @Override
        public int compare(Option o1, Option o2) {
            int order1=0;
            int order2=0;
            for (Method method : RunParam.class.getDeclaredMethods()) {
                if (method.isAnnotationPresent(ParamMeta.class)) {
                    String str = StringUtils.substring(method.getName(), 3);
                    if (StringUtils.equalsIgnoreCase(str, o1.getLongOpt())) {
                        order1 = method.getAnnotation(ParamMeta.class).order();
                    } else if (StringUtils.equalsIgnoreCase(str, o2.getLongOpt())) {
                        order2 = method.getAnnotation(ParamMeta.class).order();
                    }
                }
            }
            return Integer.compare(order1, order2);
        }
    }

    static {
        for (Method method : RunParam.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ParamMeta.class)) {
                String str = StringUtils.substring(method.getName(), 3);
                METHOD_MAP.put(str.substring(0, 1).toLowerCase() + str.substring(1), method);
            }
        }

        final HashMap<String, Integer> paramMap = new HashMap<>();

        for (HashMap.Entry<String, Method> entry1 : METHOD_MAP.entrySet()) {
            for (HashMap.Entry<String, Method> entry2 : METHOD_MAP.entrySet()) {
                if (entry1.getValue() != entry2.getValue()) {
                    int pre = StringUtils.getCommonPrefix(entry1.getKey(), entry2.getKey()).length();
                    Integer length1 = paramMap.get(entry1.getKey());
                    if (length1 == null) {
                        length1 = pre + 1;
                    }
                    paramMap.put(entry1.getKey(), Math.max(length1, pre + 1));
                }
            }
        }

        for (HashMap.Entry<String, Method> entry : METHOD_MAP.entrySet()) {
            String opt = entry.getKey().substring(0, paramMap.get(entry.getKey()));
            OPTIONS.addOption(opt, entry.getKey(), true, entry.getValue().getAnnotation(ParamMeta.class).value());
        }
    }
}
