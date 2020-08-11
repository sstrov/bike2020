package fscms.cmm.util.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableList {
	
	String caption() default "";

	boolean visible() default true;

	int order() default 99;

	String format() default "";

	String filter() default "";
	
	String[] group() default {};

}
