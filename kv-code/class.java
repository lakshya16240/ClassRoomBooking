public class temp{
	public static void a(){
		System.out.println("hello a");
	}

	public static void main(String[] args) {
		B.a();
		temp.a();
	}
}
class B extends temp{

}
