package utils;

public class TransactionManger {
	public static void begin(){
		System.out.println("��������");
	}
	public static void commit(){
		System.out.println("�����ύ");
	}
	public static void rollback(){
		System.out.println("����ع�");
	}
	public static void release(){
		System.out.println("�ͷ���Դ");
	}
}
