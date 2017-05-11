package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servlet.ProductServlet;
import utils.ThreadLocalManager;
import utils.TransactionManger;

public class AOPExerciseTest {
//	代码结构如图所示
//	PersonServlet
//		PersonService
//			PersonDao
//用异常通知捕获servlet的所有的方法抛出的异常：
//目标对象所在的类			cn.tedu.big1601.servlet.PersonServlet
//抛出异常所在的方法    		save()
//抛出异常的名称       		 XxxException
//异常信息					message
//
//意义：
//异常处理类和业务逻辑类完全松耦合。
//时刻捕获生产生产环境中所有的错误，实时监控该系统，异常收集。
	@Test
	public void test01(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.error();
	}
//	计算servlet的各个类的各个方法的执行时间
//	1.目标类的名称
//	2.方法的名称           
//	3.执行的时间
//	控制台输出
//		
//	意义：用来监控程序的性能问题	
	@Test
	public void test02(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.save();
		productServlet.add();
	}
	
//	4、事务
//	public class PresonServiceImpl{
//		@Transactional
//		public void savePerson(){
//
//		}
//
//		public void queryPerson(){
//
//		}
//	}
//
//	写一个切面来完成
//	事务测试
	@Test
	public void test03(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.save();
	}
//	未加事务测试
	@Test
	public void test03_1(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.select();
	}
//	4.权限控制
//	传递 List方法    1.static     2.threadLocal
//	public class PersonServiceImpl{
//		@PrivilegeInfo(name="saveperson") //要访问PersonSserviceImpl中的savePerson方法必须具备"saveperson"的权限
//						public void savePerson(){
//							xxxxxxxxx
//						}
//					}
//	List<Privilege:name>  用户所拥有的权限
//
//	写一个切面：
//						把客户端用户拥有的权限要在切面中获取到，用ThreadLocal
//
//
//	说明:含有注解的方法需要进行权限控制.如果用户拥有该权限则执行方法,如果用户没有该权限则不能执行方法,并且提示用户  ”无此权限”.
//	@PrivilegeInfo(name="save")    name的值表示执行当前方法所需要的权限”save”
//
//	用户自己有一个权限列表 {update,delete}
	@Test
	public void test04(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		List<String> priList=new ArrayList<String>();
//		priList.add("insert");
//		priList.add("update");
//		priList.add("select");
//		ThreadLocalManager.setList(priList);
		productServlet.delete();
	}
}
