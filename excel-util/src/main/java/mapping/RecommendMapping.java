package mapping;

import mapping.constant.OperatorModel;
import mapping.model.RunParam;
import mapping.operator.PullMappingRelation;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;

import java.io.File;


/**
 * Created by leiwei on 2020/8/5 16:42
 */
public class RecommendMapping {

    private static final Logger LOGGER = Logger.getLogger(RecommendMapping.class);

    static {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.setWidth(Short.MAX_VALUE);

        String cmd1 = "bash RecommendMapping.sh" + File.pathSeparator + ">";
        String clsName = " " + RecommendMapping.class.getName();

        helpFormatter.setOptionComparator(new RunParam.ParamCompare());

        helpFormatter.printHelp(cmd1.concat(RunParam.JAR_PATH).concat(clsName),RunParam.OPTIONS);

    }


    public static void main(String[] args) {

        CommandLine commandLine;
        try {
            commandLine = new PosixParser().parse(RunParam.OPTIONS, args, true);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return;
        }

        RunParam runParam = new RunParam(commandLine);

        if(runParam.getModel()== OperatorModel.pull){
            try {
                PullMappingRelation.pull(runParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(runParam.getModel()== OperatorModel.push){
           // PushMappingRelation.push(runParam);
        }
    }
}
