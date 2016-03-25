package dyu.sample.privatemodules;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;

public class PMExecutor {
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new AbstractModule(){

			@Override
			protected void configure() {
				bind(IClient.class).to(ClientClass.class);
				
			}
			
		}, new PrivateModule() {
			
			@Override
			protected void configure() {
				install(new AbstractModule() {
					
					@Override
					protected void configure() {
						// TODO Auto-generated method stub
						bind(String.class).annotatedWith(Names.named("Singleton.id")).toInstance("Blue-Singleton");
						bind(Long.class).annotatedWith(Names.named("Dependent.dependentId")).toInstance(1000L);
						bind(String.class).annotatedWith(Names.named("SecLevelDependency.id")).toInstance("Blue");
						bind(String.class).annotatedWith(Names.named("CommonDependency.id")).toInstance("Blue-Common");						
						bind(Long.class).annotatedWith(Names.named("CommonDependency.count")).toInstance(5000L);						
					}
				});
				bind(Dependent.class).annotatedWith(Blue.class).to(Dependent.class);
				expose(Dependent.class).annotatedWith(Blue.class);
				bind(SpSingleton.class).in(Singleton.class);
				
			}
		}, new PrivateModule() {
			
			@Override
			protected void configure() {
				install(
						Modules.override(
								new AbstractModule() {
					
					@Override
					protected void configure() {
						// TODO Auto-generated method stub
						bind(String.class).annotatedWith(Names.named("Singleton.id")).toInstance("Red-Singleton");
						bind(Long.class).annotatedWith(Names.named("Dependent.dependentId")).toInstance(2000L);
						bind(String.class).annotatedWith(Names.named("SecLevelDependency.id")).toInstance("Red");
						bind(String.class).annotatedWith(Names.named("CommonDependency.id")).toInstance("Red-Common");						
						bind(Long.class).annotatedWith(Names.named("CommonDependency.count")).toInstance(25000L);						
						
					}
				}).with(new AbstractModule() {
					
					@Override
					protected void configure() {
						bind(String.class).annotatedWith(Names.named("Singleton.id")).toInstance("Red-Override-Singleton");
						bind(Long.class).annotatedWith(Names.named("Dependent.dependentId")).toInstance(11000L);
						bind(String.class).annotatedWith(Names.named("SecLevelDependency.id")).toInstance("Red-Override");
						bind(String.class).annotatedWith(Names.named("CommonDependency.id")).toInstance("Red-Override-Common");						
						
					}
				}
								)
								);
				bind(Dependent.class).annotatedWith(Red.class).to(Dependent.class);
				expose(Dependent.class).annotatedWith(Red.class);
				bind(SpSingleton.class).in(Singleton.class);

				
			}
		});
		
		IClient client = injector.getInstance(IClient.class);
		client.execute();
	}
}
