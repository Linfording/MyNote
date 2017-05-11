package aspect;

import java.lang.reflect.Method;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


import utils.ThreadLocalManager;
import utils.TransactionManger;

import anno.PrivilegeInfo;
import anno.Transactiona;

@Component
@Aspect
public class PrivilegeAspect {
	@Pointcut("execution(* service..*.*(..))")
	public void pointCut(){
		
	}
//	通过反射获取注解进行权限控制
//	获取Meethod对象方法一:通过class.getMethods()，循环遍历方法名，得到目标方法的Method对象
//	@Around("pointCut()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String methodName=proceedingJoinPoint.getSignature().getName();
		Class cls=proceedingJoinPoint.getTarget().getClass();
		Method[] methods=cls.getMethods();
		Object result=null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				if (method.isAnnotationPresent(PrivilegeInfo.class)) {
					PrivilegeInfo privilegeInfo=method.getAnnotation(PrivilegeInfo.class);
					String privilegeName=privilegeInfo.value();
					List<String> list=ThreadLocalManager.getList();
					System.out.println(list);
					if (list.contains(privilegeName)) {
						result=proceedingJoinPoint.proceed();
					}else {
						System.out.println("权限不足亲");
					}
				}else{
//					不需要权限
					result=proceedingJoinPoint.proceed();
				}
				return result;
			}
		}
		return result;
	}
//	joinpoint.getArgs()循环遍历目标对象参数列表获取参数类型--argsType
//	通过class.getMethod(methodname,class[] argsType)获取Method对象
	@Around("pointCut()")
	public Object around2(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String methodName=proceedingJoinPoint.getSignature().getName();
		Class cls=proceedingJoinPoint.getTarget().getClass();
		Object[] args=proceedingJoinPoint.getArgs();
		Class argsClass[]=new Class[args.length];
		if (args.length!=0) {
			for (int i=0;i<args.length;i++) {
				argsClass[i]=args[i].getClass();
			}
		}
		Method method=cls.getMethod(methodName, argsClass);
		Object result=null;
		if (method.isAnnotationPresent(PrivilegeInfo.class)) {
			PrivilegeInfo privilegeInfo=method.getAnnotation(PrivilegeInfo.class);
			String privilegeName=privilegeInfo.value();
			List<String> list=ThreadLocalManager.getList();
			System.out.println(list);
			if (list.contains(privilegeName)) {
				System.out.println("恭喜有权限");
				result=proceedingJoinPoint.proceed();
			}else {
				System.out.println("权限不足亲");
			}
		}else{
//			不需要权限
			System.out.println("不需要权限");
			result=proceedingJoinPoint.proceed();
		}
		return result;
		
	}
	
//	通过springAOP获取注解进行权限控制
//	@Around("pointCut() && @annotation(privilegeInfo)")
	public Object classAround(ProceedingJoinPoint proceedingJoinPoint,PrivilegeInfo privilegeInfo) throws Throwable{
		List<String> list=ThreadLocalManager.getList();
		Object result=null;
		if (list.contains(privilegeInfo.value())) {
			result =proceedingJoinPoint.proceed();
		}else {
			System.out.println("权限不足亲");
		}
		return result;
	}
}
