package utils;

import java.util.ArrayList;
import java.util.List;


public class ThreadLocalManager {
	private static ThreadLocal<List<String>> tl=new ThreadLocal<List<String>>(){
		protected java.util.List<String> initialValue() {
			List<String> list=new ArrayList<String>();
			list.add("insert");
			list.add("update");
			list.add("select");
			return list;
		};
	};
	private ThreadLocalManager() {}
	
	public static void setList(List<String> priList){
		tl.set(priList);
	}
	
	public static List<String> getList(){
		return tl.get();
	}
	
	public static boolean havePrivilegeName(String privilegeName){
		List<String> list=tl.get();
		return list.contains(privilegeName);
	}
}
