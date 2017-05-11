package aspect;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import utils.TransactionManger;

import anno.Transactiona;
@Component
@Aspect
public class TranAspect {
//		4、事务
//	public class PresonServiceImpl{
//		@Transactional
//		public void savePerson(){
//		}
//		public void queryPerson(){
//		}
//	}
//	写一个切面来完成
	@Pointcut("execution(* service..*.*(..))")
	public void pointCut(){
		
	}
	
//	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String methodName=proceedingJoinPoint.getSignature().getName();
		Class cls=proceedingJoinPoint.getTarget().getClass();
		Method[] methods=cls.getMethods();
		Object result=null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				if (method.isAnnotationPresent(Transactiona.class)) {
					try {
						TransactionManger.begin();
						result=proceedingJoinPoint.proceed();
						TransactionManger.commit();
					} catch (Exception e) {
						TransactionManger.rollback();
					} finally{
						TransactionManger.release();
					}
				}else {
					result=proceedingJoinPoint.proceed();
				}
				return result;
			}
		}
		return result;
	}
	/**
	 * 
	 * @throws Throwable 
	 * @annotation 是注解的关键字，不能随便定义
	 * 
	 */
	@Around("pointCut() && @annotation(transaction)")
	public Object classAround(ProceedingJoinPoint proceedingJoinPoint,Transactiona transaction) throws Throwable{
		TransactionManger.begin();
		Object result=proceedingJoinPoint.proceed();
		TransactionManger.commit();
		return result;
	}
}
