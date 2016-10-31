package de.fh_kiel;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author jpr
 */
@Aspect
public class CheckNullAdvice {


    @Around("execution(* de.fh_kiel..*Service.*(..)) && @annotation(de.fh_kiel.CheckNull)")
    public Object checkForNull(ProceedingJoinPoint joinPoint) throws Throwable {

        if (Arrays.stream(joinPoint.getArgs()).anyMatch(o -> o == null)) {
            final CheckNull annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(CheckNull.class);
            if (annotation.silent()) {

                // an argument was null and the method body will not be executed
                return null;
            }
            throw new IllegalArgumentException("An argument was null and the method body will not be executed");

        }
        // execute original code as argument is not null or more tha
        return joinPoint.proceed();
    }


}
