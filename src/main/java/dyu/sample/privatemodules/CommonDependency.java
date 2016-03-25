package dyu.sample.privatemodules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CommonDependency {
	
	/**
	 *  Doesn't inject optional depedency, however if field count is injected as mandatory then it is fine. 
	 *	Please note count shouldn't be injected as optional else we face same issue.
	 */
	@Inject(optional=true)
	@Named("CommonDependency.id")
	private String id;
	
	private Long count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
	public void execute() {
		System.out.println("CommonDependency.id: " + id);
		System.out.println("CommonDependency.count: " + count);
	}
	
}
