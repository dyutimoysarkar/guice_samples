package dyu.sample.privatemodules;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SpSingleton {
	private String id;
	
	@Inject
	public SpSingleton(@Named("Singleton.id")String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	
}
