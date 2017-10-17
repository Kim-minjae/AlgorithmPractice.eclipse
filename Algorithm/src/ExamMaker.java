
public class ExamMaker {
	public static void main(String args[]) {
		
		for(int i = 1; i<100; i++) {
			System.out.println(i + " " + ((int)((100-i)*Math.random()) +i+1) +" " +(int)(Math.random()*100) );
		}
		
	}
}
