package it.fm3.alcolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class AlcolistApplication {
	//TODO ATTUALMENTE I FILE PUSHATI NON VANNO NELLA CARTELLA CODE
	//MA NELLA CARTELLA SRC ALL PATH PIU' ESTERNO DEL REPOSITORY
	public static void main(String[] args) {
		SpringApplication.run(AlcolistApplication.class, args);
	}

}
