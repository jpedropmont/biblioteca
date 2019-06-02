import java.util.Date;

public class Main {
	public static void main(String[] args) {
	   Date teste = new Date();
	   Date testeFinal = new Date();
	   testeFinal.setDate(teste.getDate() + 31);
	   System.out.println(testeFinal);
	}
}
