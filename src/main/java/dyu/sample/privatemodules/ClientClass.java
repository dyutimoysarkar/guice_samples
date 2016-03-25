package dyu.sample.privatemodules;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;

public class ClientClass implements IClient{
	private Dependent dependent1;
	private Dependent dependent2;
//	private Dependent dependent3;
	
	@Inject
	public ClientClass(Injector injector) {
		super();
		this.dependent1 = injector.getInstance(Key.get(Dependent.class,Blue.class));
		this.dependent2 = injector.getInstance(Key.get(Dependent.class,Red.class));
	}


	public void execute() {
		dependent1.print();
		dependent2.print();
		dependent1.print();
	}
	
}
