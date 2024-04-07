package kr.co.hanbit.product.management;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//new ModelMapper로 매번 DI해도 되지만, 성능상 미리 빈으로 등록해서 사용하는 것이 좋다.
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
				.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true);
		return modelMapper;
	}
	@Bean
	public ApplicationRunner runner(DataSource dataSource) {
		return args -> {
			// 이 부분에 실행할 코드를 넣으면 된다.
			Connection connection = dataSource.getConnection();
		};
	}

}
