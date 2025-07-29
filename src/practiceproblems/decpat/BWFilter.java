package practiceproblems.decpat;

public class BWFilter extends FilterDecorator {

	public BWFilter(FilterDecorator decorate) {
		super(() -> decorate.showFilterDesc() + ", BW filter added");
	}
}