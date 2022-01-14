package duong.dev.service;


import java.util.List;

import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.request.CategoryRequestDTO;
import duong.dev.entity.Categorie;

public interface CategorieInterface {

	public List<CategoryRequestDTO> realAll();
	
	public List<CategorieDTO> listSubCategory(Integer id);
	
	public List<CategoryRequestDTO> readNameCategory();
	
	public List<CategoryRequestDTO> readTypeBigCategory();
	
	public List<CategorieDTO> listCateLv1();

	public List<CategoryRequestDTO> searchCategory(String sort, String keyword);
	
	public CategoryRequestDTO createBigcategory(CategoryRequestDTO categoryRequestDTO) ;
	
	public CategoryRequestDTO update(CategoryRequestDTO categoryRequestDTO) ;
	
	public CategoryRequestDTO updateStatus(Integer id);
	
	public CategoryRequestDTO delete(Integer id);
	
	public List<CategorieDTO> finListCateNoPromotion(Integer id);
	
	public List<CategorieDTO> listCateLv2();

	public List<CategorieDTO> readCateoryBig();

	public List<CategorieDTO> readCateoryByCateGoryId(Integer id);

	public List<CategorieDTO> readCateoryShowNav();

	
}
