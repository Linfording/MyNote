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
//	ͨ�������ȡע�����Ȩ�޿���
//	��ȡMeethod���󷽷�һ:ͨ��class.getMethods()��ѭ���������������õ�Ŀ�귽����Method����
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
						System.out.println("Ȩ�޲�����");
					}
				}else{
//					����ҪȨ��
					result=proceedingJoinPoint.proceed();
				}
				return result;
			}
		}
		return result;
	}
//	joinpoint.getArgs()ѭ������Ŀ���������б��ȡ��������--argsType
//	ͨ��class.getMethod(methodname,class[] argsType)��ȡMethod����
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
				System.out.println("��ϲ��Ȩ��");
				result=proceedingJoinPoint.proceed();
			}else {
				System.out.println("Ȩ�޲�����");
			}
		}else{
//			����ҪȨ��
			System.out.println("����ҪȨ��");
			result=proceedingJoinPoint.proceed();
		}
		return result;
		
	}
	
//	ͨ��springAOP��ȡע�����Ȩ�޿���
//	@Around("pointCut() && @annotation(privilegeInfo)")
	public Object classAround(ProceedingJoinPoint proceedingJoinPoint,PrivilegeInfo privilegeInfo) throws Throwable{
		List<String> list=ThreadLocalManager.getList();
		Object result=null;
		if (list.contains(privilegeInfo.value())) {
			result =proceedingJoinPoint.proceed();
		}else {
			System.out.println("Ȩ�޲�����");
		}
		return result;
	}
}
