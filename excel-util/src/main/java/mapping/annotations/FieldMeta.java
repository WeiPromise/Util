package mapping.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface FieldMeta {
    String fieldName() default "";
    int order();
    boolean idField() default false;
}
