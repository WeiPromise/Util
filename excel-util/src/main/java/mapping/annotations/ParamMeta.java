package mapping.annotations;

import java.lang.annotation.*;

/**
 * ParamMeta 参数Meta,用在启动参数上,注解在set方法
 *
 * @author leiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ParamMeta {

    String value();

    int order();
}
