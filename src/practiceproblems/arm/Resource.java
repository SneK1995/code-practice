package practiceproblems.arm;

import java.util.function.Consumer;

public class Resource {

	public Resource op1() {
		System.out.println("Executing op1");
		return this;
	}

	public Resource op2() {
		System.out.println("Executing op2");
		return this;
	}
	
	public void close() {
		System.out.println("Resource cleaned up.");
	}
	
	private Resource() {}
	
	public static void use(Consumer<Resource> block) {
		Resource resource = new Resource();
		try {
			block.accept(resource);
		}finally {
			resource.close();
		}
		
	}

	public static void main(String[] args) {
		use(resource -> {
			resource.op1();
			resource.op2();
		});
	}
}
