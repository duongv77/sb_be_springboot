package duong.dev;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean  
	public ModelMapper getModelMapper() {
		ModelMapper mapper = new ModelMapper(); //tạo mới 1 mapper
		//Cách mapping cho mapper theo phương thức tiêu chuẩn;
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return mapper;
	}
}
