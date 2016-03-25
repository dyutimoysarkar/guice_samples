package dyu.sample.privatemodules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SecLevelDependency implements CreatedByInjector {
	@Inject
	@Named("SecLevelDependency.id")
	private String id;
	
	@Inject
	private CommonDependency commonDependency;
	
	public void execute() {
		System.out.println("SecLevelDependency.id" + id);
		commonDependency.execute();
	}
}
