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
//	����ṹ��ͼ��ʾ
//	PersonServlet
//		PersonService
//			PersonDao
//���쳣֪ͨ����servlet�����еķ����׳����쳣��
//Ŀ��������ڵ���			cn.tedu.big1601.servlet.PersonServlet
//�׳��쳣���ڵķ���    		save()
//�׳��쳣������       		 XxxException
//�쳣��Ϣ					message
//
//���壺
//�쳣�������ҵ���߼�����ȫ����ϡ�
//ʱ�̲��������������������еĴ���ʵʱ��ظ�ϵͳ���쳣�ռ���
	@Test
	public void test01(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.error();
	}
//	����servlet�ĸ�����ĸ���������ִ��ʱ��
//	1.Ŀ���������
//	2.����������           
//	3.ִ�е�ʱ��
//	����̨���
//		
//	���壺������س������������	
	@Test
	public void test02(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.save();
		productServlet.add();
	}
	
//	4������
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
//	дһ�����������
//	�������
	@Test
	public void test03(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.save();
	}
//	δ���������
	@Test
	public void test03_1(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductServlet productServlet=(ProductServlet) context.getBean("productServlet");
		productServlet.select();
	}
//	4.Ȩ�޿���
//	���� List����    1.static     2.threadLocal
//	public class PersonServiceImpl{
//		@PrivilegeInfo(name="saveperson") //Ҫ����PersonSserviceImpl�е�savePerson��������߱�"saveperson"��Ȩ��
//						public void savePerson(){
//							xxxxxxxxx
//						}
//					}
//	List<Privilege:name>  �û���ӵ�е�Ȩ��
//
//	дһ�����棺
//						�ѿͻ����û�ӵ�е�Ȩ��Ҫ�������л�ȡ������ThreadLocal
//
//
//	˵��:����ע��ķ�����Ҫ����Ȩ�޿���.����û�ӵ�и�Ȩ����ִ�з���,����û�û�и�Ȩ������ִ�з���,������ʾ�û�  ���޴�Ȩ�ޡ�.
//	@PrivilegeInfo(name="save")    name��ֵ��ʾִ�е�ǰ��������Ҫ��Ȩ�ޡ�save��
//
//	�û��Լ���һ��Ȩ���б� {update,delete}
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
