package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.FavoriteDTO;
import duong.dev.entity.Favorite;

@Component
public class FavoriteMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Favorite convertToEntity(FavoriteDTO favoriteDTO) {
		Favorite entity = new Favorite();
		mapper.map(favoriteDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public FavoriteDTO convertToDTO(Favorite entity) {
		FavoriteDTO favoriteDTO = new FavoriteDTO();
		mapper.map(entity, favoriteDTO);
		return favoriteDTO;
	}
}
