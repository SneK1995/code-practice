package practiceproblems.decpat;

public class FilterDecorator {
	
	protected Filter filter;
	
	public FilterDecorator(Filter filter) {
		this.filter=filter;
	}
	
	public Filter getFilter() {
		return filter;
	}
	
	public String showFilterDesc() {
		return filter.showFilter();
	}
}
