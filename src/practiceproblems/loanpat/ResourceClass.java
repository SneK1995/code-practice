package practiceproblems.loanpat;

public class ResourceClass {

	private int resource;
	
	public ResourceClass(int resource) {
		this.resource=resource;
	}
	
	public int getResource() {
		return resource;
	}
	
	@Override
	public String toString() {
		return "" + resource;
	}
}
