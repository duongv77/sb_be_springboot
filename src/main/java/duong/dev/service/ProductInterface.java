package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.ProductDTO;
import duong.dev.dto.request.UpdateProductDTO;
import duong.dev.dto.response.ProductRateResponseDTO;
import duong.dev.dto.response.ProductResponseDTO;
import duong.dev.dto.response.PromotionDetailPRDTO;
import duong.dev.entity.Product;

import javax.servlet.ServletException;


public interface ProductInterface {

	public List<ProductDTO> readAll(String sort);

	public List<ProductRateResponseDTO> readAllCustom();

	public List<PromotionDetailPRDTO> readAllSortUser(String sort);

	public List<ProductDTO> searchProduct(String sort, String keyword);
	
	public ProductDTO create(UpdateProductDTO productD);
	
	public ProductDTO update(UpdateProductDTO productD);
	
	public ProductDTO delete(Integer id);
	
	public List<ProductDTO> showToPromotion(Integer id);
	
	public List<ProductDTO> getListProductToBlackList(Integer id);
	
	public PromotionDetailPRDTO getOnly(Integer id);
	
	public List<PromotionDetailPRDTO> getListProductVsSale();

	public List<PromotionDetailPRDTO> findListProductFavourites()throws ServletException, IOException;

	public List<PromotionDetailPRDTO> getSearchListProductVsSale(String keyword);

	public ProductDTO updateStatus(Integer id);

	public ProductDTO showDetail(Integer id);

	public List<PromotionDetailPRDTO> showProductRelated(Product product, Integer page);

	public List<PromotionDetailPRDTO> showProductRelatedCategory(Product product, Integer page);

	public List<PromotionDetailPRDTO> top10ProductNews();

	public List<PromotionDetailPRDTO> top10ProductSale();

	public List<PromotionDetailPRDTO> top10ProductTrending();

	public List<PromotionDetailPRDTO> top10ProductBanChay();

	public ProductResponseDTO findListToCateLV1();

	public List<PromotionDetailPRDTO> findListCustomForm(String sqlOld) ;

	public List<String> findListLanguage();

	public List<String> findListNxb();

}
