package aspect;

import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
//	����ṹ��ͼ��ʾ
//	PersonServlet
//		PersonService
//			PersonDao
//���쳣֪ͨ����servlet�����еķ����׳����쳣��
//Ŀ��������ڵ���			cn.tedu.big1601.servlet.PersonServlet
//�׳��쳣���ڵķ���    		save()
//�׳��쳣������       		 XxxException
//�쳣��Ϣ					message
	@Pointcut("execution(* servlet.ProductServlet.*(..))")
	public void pointCut(){
		
	}
	
	@AfterThrowing(value="pointCut()",throwing="throwable")
	public void throwable(JoinPoint joinPoint,Throwable throwable){
		System.out.println("Ŀ�����������:"+joinPoint.getTarget().getClass());
		System.out.println("�׳��쳣���ڵķ���:"+joinPoint.getSignature().getName());
		System.out.println("�׳��쳣������:"+throwable.getClass());
		System.out.println("�쳣��Ϣ :"+throwable.getMessage());
	}
//	@Before("pointCut()")
	public void before(){
		System.out.println("MyAspect before");
	}
	@Around("pointCut()")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//		long timeBefore=new Date().getTime();
//		proceedingJoinPoint.proceed();
//		long timeAfter=new Date().getTime();
//		System.out.println("Ŀ�귽��:"+proceedingJoinPoint.getSignature());
//		System.out.println("Ŀ�귽��ִ��ʱ��:"+(timeAfter-timeBefore)+"ms");

		long beginTime=System.currentTimeMillis();
		proceedingJoinPoint.proceed();
		long afterTime=System.currentTimeMillis();
		System.out.println("Ŀ�귽��:"+proceedingJoinPoint.getSignature().getName());
		System.out.println("Ŀ�귽��ִ��ʱ��:"+(afterTime-beginTime)+"ms");
	}
}
