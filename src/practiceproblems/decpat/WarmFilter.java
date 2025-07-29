package practiceproblems.decpat;

public class WarmFilter extends FilterDecorator {

	public WarmFilter(FilterDecorator decorate) {
		super(() -> decorate.showFilterDesc() + ", Warm filter added");
	}
}
