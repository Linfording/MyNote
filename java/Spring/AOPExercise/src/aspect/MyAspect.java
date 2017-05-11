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
//	代码结构如图所示
//	PersonServlet
//		PersonService
//			PersonDao
//用异常通知捕获servlet的所有的方法抛出的异常：
//目标对象所在的类			cn.tedu.big1601.servlet.PersonServlet
//抛出异常所在的方法    		save()
//抛出异常的名称       		 XxxException
//异常信息					message
	@Pointcut("execution(* servlet.ProductServlet.*(..))")
	public void pointCut(){
		
	}
	
	@AfterThrowing(value="pointCut()",throwing="throwable")
	public void throwable(JoinPoint joinPoint,Throwable throwable){
		System.out.println("目标对象所在类:"+joinPoint.getTarget().getClass());
		System.out.println("抛出异常所在的方法:"+joinPoint.getSignature().getName());
		System.out.println("抛出异常的名称:"+throwable.getClass());
		System.out.println("异常信息 :"+throwable.getMessage());
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
//		System.out.println("目标方法:"+proceedingJoinPoint.getSignature());
//		System.out.println("目标方法执行时间:"+(timeAfter-timeBefore)+"ms");

		long beginTime=System.currentTimeMillis();
		proceedingJoinPoint.proceed();
		long afterTime=System.currentTimeMillis();
		System.out.println("目标方法:"+proceedingJoinPoint.getSignature().getName());
		System.out.println("目标方法执行时间:"+(afterTime-beginTime)+"ms");
	}
}
