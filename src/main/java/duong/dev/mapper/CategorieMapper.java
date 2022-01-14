package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CategorieDTO;
import duong.dev.dto.request.CategoryRequestDTO;
import duong.dev.entity.Categorie;

@Component
public class CategorieMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Categorie convertToEntity(CategorieDTO categorieDTO) {
		Categorie entity = new Categorie();
		mapper.map(categorieDTO, entity);
		return entity;
	}
	
	public Categorie convertToEntity1(CategoryRequestDTO categoryRequestDTO) {
		Categorie entity = new Categorie();
		mapper.map(categoryRequestDTO, entity);
		return entity;
	}
	
	public CategorieDTO convertToDTO(Categorie entity) {
		CategorieDTO categorieDTO = new CategorieDTO();
		mapper.map(entity, categorieDTO);
		return categorieDTO;
	}
	
	public CategoryRequestDTO convertToDTO1(Categorie entity) {
		CategoryRequestDTO categorieDTO = new CategoryRequestDTO();
		mapper.map(entity, categorieDTO);
		return categorieDTO;
	}
	
}
