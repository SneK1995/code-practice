package practiceproblems.decpat;

public class MainClass {

	public static void main(String[] args) {
		FilterDecorator decorater = new FilterDecorator(() -> "Image added");
		System.out.println(decorater.showFilterDesc());
		decorater = new WarmFilter(decorater);
		System.out.println(decorater.showFilterDesc());
		decorater = new BWFilter(decorater);
		System.out.println(decorater.showFilterDesc());
	}
}
