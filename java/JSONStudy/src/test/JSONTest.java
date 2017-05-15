package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.junit.Test;

import bean.Person;

public class JSONTest {
//	StringArray===》JSONArray
	@Test
	public void test01(){
		String[] strArray={"张三","李四","王五","马六"};
		System.out.println(Arrays.toString(strArray));
//		数组转化为JSON串
		JSONArray jsonArray=JSONArray.fromObject(strArray);
		System.out.println(jsonArray);
	}
//	List===》JSONArray
	@Test
	public void test02(){
		List<String> list=new ArrayList<String>();
		list.add("张三");
		list.add("李四");
		list.add("王五");
		list.add("马六");
		System.out.println(list);
//		list转化为JSON串
		JSONArray jsonArray=JSONArray.fromObject(list);
		System.out.println(jsonArray);
	}
//	Map===》JSON
	@Test
	public void test03(){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("name1", "张三");
		map.put("name2", "李四");
		map.put("name3", "王五");
		map.put("name4", "马六");
		System.out.println(map);
//		map转化为JSON串
		JSONArray jsonArray=JSONArray.fromObject(map);
		System.out.println(jsonArray);
//		因为map底层不是数组，所以转出来的object格式。
//		直接用Array其实是先转了object，再添加到Array里
		JSONObject jsonObject=JSONObject.fromObject(map);
		System.out.println(jsonObject);
		jsonArray.add(jsonObject);
		System.out.println(jsonArray);
	}
	
//	bean===》JSON
	@Test
	public void test04(){
		Person person=new Person();
		person.setAge(18);
		person.setName("张三");
		person.setHeight(170);
		person.setWidth(100);
		System.out.println(person);
//		bean转化为JSON串
		JSONArray jsonArray=JSONArray.fromObject(person);
		System.out.println(jsonArray);
//		同map一样，因为底层不是数组，所以转出来的object格式。
//		直接用Array其实是先转了object，再添加到Array里
		JSONObject jsonObject=JSONObject.fromObject(person);
		System.out.println(jsonObject);
		jsonArray.add(jsonObject);
		System.out.println(jsonArray);
//		bean转JSON依赖于bean的get方法，如果不设置get方法，并不能获取到相应的值
	}
//	bean===》JSON
//	排它转化:排除某个属性进行转化
	@Test
	public void test05(){
		Person person=new Person();
		person.setAge(18);
		person.setName("张三");
		person.setHeight(170);
		person.setWidth(100);
		System.out.println(person);
//		Json的配置
		JsonConfig config=new JsonConfig();
		
//		排除width进行转换
		config.setExcludes(new String[]{"width"});
		
		JSONObject jsonObject=JSONObject.fromObject(person,config);
		System.out.println(jsonObject);
	}
//	beanlist===》JSONArray
	@Test
	public void test06(){
		List<Person> persons=new ArrayList<Person>();
		Person person=new Person();
		person.setAge(18);
		person.setName("张三");
		person.setHeight(170);
		person.setWidth(100);
		Person person2=new Person();
		person2.setAge(18);
		person2.setName("李四");
		person2.setHeight(170);
		person2.setWidth(100);
		persons.add(person);
		persons.add(person2);
		System.out.println(person);
		System.out.println(person2);
		System.out.println(persons);
//		map转化为JSON串
		JSONArray jsonArray=JSONArray.fromObject(persons);
		System.out.println(jsonArray);
//		[{"age":18,"height":170,"name":"张三","width":100},{"age":18,"height":170,"name":"李四","width":100}]
		
//		JSONObject jsonObject=JSONObject.fromObject(persons);
//		System.out.println(jsonObject);
//		报错:net.sf.json.JSONException: 'object' is an array. Use JSONArray instead
	}
	
//	JSON===》bean
	@Test
	public void test07(){
		String json="{\"age\":18,\"height\":170,\"name\":\"张三\"}";
		JSONObject jsonObject=JSONObject.fromObject(json);
		Person person=(Person) jsonObject.toBean(jsonObject,Person.class);
		System.out.println(person);
	}
}
