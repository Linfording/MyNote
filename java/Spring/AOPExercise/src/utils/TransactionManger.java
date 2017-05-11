package utils;

public class TransactionManger {
	public static void begin(){
		System.out.println("开启事务");
	}
	public static void commit(){
		System.out.println("事务提交");
	}
	public static void rollback(){
		System.out.println("事务回滚");
	}
	public static void release(){
		System.out.println("释放资源");
	}
}
