package utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class GetModelNameAndType {
	 public static void testReflect(Object model,String[] dataScoure)
{  
 // 获取实体类的所有属性，返回Field数组  
 Field[] field = model.getClass().getDeclaredFields();  
 // 获取属性的名字  
 String[] modelName = new String[field.length];  
 String[] modelType = new String[field.length];  
 for (int i = 0; i < field.length; i++)  
 {  
     // 获取属性的名字  
     String name = field[i].getName();  
     modelName[i] = name;  
     // 获取属性类型  
     String type = field[i].getGenericType().toString();  
     modelType[i] = type;  
       
     //关键。。。可访问私有变量  
     field[i].setAccessible(true);  
     //给属性设置  
     try {
		field[i].set(model,  field[i].getType().getConstructor(field[i].getType()).newInstance(dataScoure[i]));
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	}  

 /*    // 将属性的首字母大写  
     name = name.replaceFirst(name.substring(0, 1), name.substring(0, 1)  
             .toUpperCase());  

     if (type.equals("class java.lang.String"))  
     {   
         // 如果type是类类型，则前面包含"class "，后面跟类名  
         Method m = model.getClass().getMethod("get" + name);  
         // 调用getter方法获取属性值  
         String value = (String) m.invoke(model);  
         if (value != null)  
         {  

             System.out.println("attribute value:" + value);  
         }  

     }  
     if (type.equals("class java.lang.Integer"))  
     {  
         Method m = model.getClass().getMethod("get" + name);  
         Integer value = (Integer) m.invoke(model);  
         if (value != null)  
         {  
             System.out.println("attribute value:" + value);  
         }  
     }  
     if (type.equals("class java.lang.Short"))  
     {  
         Method m = model.getClass().getMethod("get" + name);  
         Short value = (Short) m.invoke(model);  
         if (value != null)  
         {  
             System.out.println("attribute value:" + value);  
         }  
     }  
     if (type.equals("class java.lang.Double"))  
     {  
         Method m = model.getClass().getMethod("get" + name);  
         Double value = (Double) m.invoke(model);  
         if (value != null)  
         {  
             System.out.println("attribute value:" + value);  
         }  
     }  
     if (type.equals("class java.lang.Boolean"))  
     {  
         Method m = model.getClass().getMethod("get" + name);  
         Boolean value = (Boolean) m.invoke(model);  
         if (value != null)  
         {  
             System.out.println("attribute value:" + value);  
         }  
     }  
     if (type.equals("class java.util.Date"))  
     {  
         Method m = model.getClass().getMethod("get" + name);  
         Date value = (Date) m.invoke(model);  
         if (value != null)  
         {  
             System.out.println("attribute value:"  
                     + value.toLocaleString());  
         }  
     }  */
 }  
}  

/*public static void main(String[] args) 
{  
 try  
 {  
     testReflect(new dayWord());  
 }  
 catch (SecurityException e)  
 {  
     e.printStackTrace();  
 }  
 catch (IllegalArgumentException e)  
 {  
     e.printStackTrace();  
 }  
 catch (NoSuchMethodException e)  
 {  
     e.printStackTrace();  
 }  
 catch (IllegalAccessException e)  
 {  
     e.printStackTrace();  
 }  
 catch (InvocationTargetException e)  
 {  
     e.printStackTrace();  
 }  
 catch (InstantiationException e)  
 {  
     e.printStackTrace();  
 }  
}   */
}
