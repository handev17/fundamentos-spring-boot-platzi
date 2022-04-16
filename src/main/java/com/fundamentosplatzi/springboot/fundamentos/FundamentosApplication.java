package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.PostRepository;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {
	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties  myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private PostRepository postRepository;



	@Autowired
	public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties  myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, PostRepository postRepository){
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.postRepository = postRepository;


	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args)  {
		//ejemplosAnteriores();
		saveUsersIndDatabase();
		getInformationJpqlFromUser();


	}
	private void getInformationJpqlFromUser(){
		LOGGER.info("Usuario con el metodo UserRepository.findByUserEmail "+userRepository.findByUserEmail("joyyyyy@gmail.com")
				.orElseThrow(()-> new RuntimeException("No se encontro el usuario")));
	}

	private void saveUsersIndDatabase(){
		User user1 = new User("Jonh","jonh@gmail.com", LocalDate.of(2021,03,20));
		User user2 = new User("Jonh1","jonh1@gmail.com", LocalDate.of(2021,04,21));
		User user3 = new User("Jonh2","jonh2@gmail.com", LocalDate.of(2021,05,22));
		User user4 = new User("Jonh3","jonh3@gmail.com", LocalDate.of(2021,06,23));
		User user5 = new User("Jonh4","jonh4@gmail.com", LocalDate.of(2021,07,24));
		User user6 = new User("Jonh5","jonh5@gmail.com", LocalDate.of(2021,03,25));
		List <User> list = Arrays.asList(user1,user2,user3,user4,user5);
		list.stream().forEach(userRepository :: save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.print(userPojo.getEmail() + " - " +userPojo.getPassword());
		try{
			int value = 10/0;
			LOGGER.debug("Mi valor es "+value);

		}catch(Exception e){
			LOGGER.error("Esto es un error al dividir por cero "+e.getMessage());
		}
	}
}
