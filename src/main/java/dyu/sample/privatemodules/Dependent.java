package dyu.sample.privatemodules;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class Dependent {
	@Inject
	private SpSingleton singleton;
	
	@Inject
	private Injector injector;
	
	@Named("Dependent.dependentId")
	@Inject(optional=true)
	private Long dependentId;

	public SpSingleton getSingleton() {
		return singleton;
	}

	public Long getDependentId() {
		return dependentId;
	}
	
	public void print() {
		System.out.println("Executing dependent" + this.dependentId);
		System.out.println("Executing dependent:Singleton:" + this.singleton.getId());
		SecLevelDependency secLevelDependency = this.injector.getInstance(SecLevelDependency.class);
		secLevelDependency.execute();
		
	}
}
