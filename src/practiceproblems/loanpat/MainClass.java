package practiceproblems.loanpat;

public class MainClass {

	public static void main(String[] args) {
		ResourceManager manager = new ResourceManager();
		manager.useResource(r -> {
			int sum=0;
			for(int i=1;i<=r.getResource();i++) {sum+=i;}
			System.out.println("Sum => " + sum);
		}, 10);
		manager.useResource(r -> {
			double avg=0;
			int sum=0;
			for(int i=1;i<=r.getResource();i++) {sum+=i;}
			avg=(double)sum/(double)r.getResource();
			System.out.println("Avg => " + avg);
		}, 20);
	}
}
