package rs.fon.queue;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import rs.fon.queue.blogic.impl.FacultyStand;
import rs.fon.queue.blogic.util.Constants;



@Configuration
public class QueueDataConfig {

	@Bean
	public FacultyStand stand_1() {
		return new FacultyStand(Constants.STAND_1);
	}
	
	
	
	
	@Bean
	public FacultyStand stand_2() {
		return new FacultyStand(Constants.STAND_2);
	}
	
	@Bean
	public FacultyStand stand_3() {
		return new FacultyStand(Constants.STAND_3);
	}
	
	@Bean
	public FacultyStand stand_4() {
		return new FacultyStand(Constants.STAND_4);
	}
	
	@Bean
	public FacultyStand stand_5() {
		return new FacultyStand(Constants.STAND_5);
	}
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean emf;
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf.getObject());
		return transactionManager;
	}
	
	
	
	
	
	
	
}
