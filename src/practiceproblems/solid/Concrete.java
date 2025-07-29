package practiceproblems.solid;

public class Concrete extends TestImpl {

	@Override
	public void execute() {
		System.out.println("executed");
	}

	@Override
	public void absMethod() {
		System.out.println("absMethod");
	}
	
	public static void main(String[] args) {
		Concrete con = new Concrete();
		con.execute();
		con.absMethod();
	}
}
