package practiceproblems.loanpat;

public class ResourceManager {

	public void useResource(VoidMethod method, int size) {
		ResourceClass resource = new ResourceClass(size);
		try {
			System.out.println("Using resource => " + resource);
			method.execute(resource);
			System.out.println("resource => " + resource + " used");
		} catch(Exception e) {
			System.out.println("Exception occured while using resource " + resource);
			System.out.println("Exception => " + e.getLocalizedMessage());
		} finally {
			System.out.println("Freeing resource => " + resource);
			resource = null;
			System.out.println("Resource freed => " + resource);
		}
	}
}
