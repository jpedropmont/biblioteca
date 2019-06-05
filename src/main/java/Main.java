import java.util.Date;

@SuppressWarnings("deprecation")
public class Main {
	public static void main(String[] args) {
	   Date teste = new Date();
	   Date testeFinal = new Date();
	   testeFinal.setDate(teste.getDate()-2);
	   System.out.println(testeFinal.getDate());
	   System.out.println(testeFinal.getDay());
	}
}
