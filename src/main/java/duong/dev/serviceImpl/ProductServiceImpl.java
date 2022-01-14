package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duong.dev.common.CommonObject;
import duong.dev.dto.response.ProductRateResponseDTO;
import duong.dev.dto.response.ProductResponseDTO;
import duong.dev.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.request.UpdateProductDTO;
import duong.dev.dto.response.PromotionDetailPRDTO;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.exception.AppException;
import duong.dev.mapper.ProductMapper;
import duong.dev.repository.ProductRepository;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.ProductInterface;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;

@Service
public class ProductServiceImpl implements ProductInterface {
	@Autowired private ProductRepository productRepo;
	@Autowired private ProductMapper productMapper;
	@Autowired private PromotionRepository promotionRepo;
	@Autowired private OrderServiceImpl orderService;
	@Autowired
	private EntityManager entityManager;

	@Override
	public PromotionDetailPRDTO getOnly(Integer id) {
		Optional<Product> productOptional = productRepo.findById(id);
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");

		Product product = productOptional.get();
		PromotionDetailPRDTO newE = new PromotionDetailPRDTO();
		newE.setProduct(productMapper.convertToDTO(product));
		Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(product.getId());
		if(promotion.isPresent()) newE.setSale(promotion.get().getSale());
		return newE;
	}

	public List<ProductDTO> convertListDTO(List<Product> listProductE) {
		List<ProductDTO> listProductD = new ArrayList<ProductDTO>();
		for (Product productE : listProductE) {
			listProductD.add(productMapper.convertToDTO(productE));
		}
		return listProductD;
	}

	@Override
	public List<ProductDTO> readAll(String sort){
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Product> listProductE = productRepo.findAll(sort2);
			return convertListDTO(listProductE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Product> listProductE = productRepo.findAll(sort2);
		return convertListDTO(listProductE);
	}

	@Override
	public List<ProductRateResponseDTO> readAllCustom() {
		List<Product> listProduct = productRepo.findAll();
		return listProduct.stream().map(elm->{
			return productMapper.convertToDTO2(elm);
		}).collect(Collectors.toList());
	}

	@Override
	public List<PromotionDetailPRDTO> readAllSortUser(String sort) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Product> listProductE = productRepo.findAll(sort2);
			return convertToPromotionDetailPRDTO(listProductE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Product> listProductE = productRepo.findAll(sort2);
		return convertToPromotionDetailPRDTO(listProductE);
	}

	@Override
	public List<ProductDTO> searchProduct(String sort, String keyword) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Product> listProductE = productRepo.searchProduct(keyword, sort2);
			return convertListDTO(listProductE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Product> listProductE = new ArrayList<>();
		if(keyword.trim().equals("")){
			listProductE = productRepo.findAll(sort2);
		}else{
			listProductE = productRepo.searchProduct(keyword, sort2);

		}
		return convertListDTO(listProductE);
	}

	@Override
	public ProductDTO create(UpdateProductDTO productD){	
		Product product = convertProduct(productD);
		productRepo.save(product);
		return productMapper.convertToDTO(product);
		
	}

	@Override
	public ProductDTO delete(Integer id){
		Optional<Product> productOptional = productRepo.findById(id);
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		try {
			productRepo.delete(productOptional.get());
			return productMapper.convertToDTO(productOptional.get());
		} catch (Exception e) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không thể xóa sản phẩm này!");
		}
	}
	
	public Product convertProduct(UpdateProductDTO productD) {
		Product product = new Product();
		product.setAuthor(productD.getAuthor());
		product.setCategorie(productD.getCategorie());
		product.setRegion(productD.getRegion());
		product.setTitle(productD.getTitle());
		product.setId(productD.getId());
		product.setForm(productD.getForm());
		product.setImage(productD.getImage());
		product.setLanguage(productD.getLanguage());
		product.setName(productD.getName());
		product.setNumberpages(productD.getNumberpages());
		product.setPrice(productD.getPrice());
		product.setPublisher(productD.getPublisher());
		product.setPublishyear(productD.getPublishyear());
		product.setQuantity(productD.getQuantity());
		product.setSupplier(productD.getSupplier());
		product.setCreateDate(java.time.LocalDateTime.now()+"");
		if(product.getQuantity()<=0) {
			product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
		}else {
			product.setAvailable(Status.STATUS_HOAT_DONG);
		}
		return product;
	}
	
	@Override
	public ProductDTO update(UpdateProductDTO productD){
		Optional<Product> productOptional = productRepo.findById(productD.getId());
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		Product product = productOptional.get();
		product.setPrice(productD.getPrice());
		product.setName(productD.getName());
		product.setNumberpages(productD.getNumberpages());
		product.setQuantity(productD.getQuantity());
		if(product.getQuantity()<=0) product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
		product.setAuthor(productD.getAuthor());
		product.setDescription(productD.getDescription());
		product.setCategorie(productD.getCategorie()==null? CommonObject.CATEGORY_KHONG_XAC_DINH: productD.getCategorie());
		product.setForm(productD.getForm());
		product.setImage(productD.getImage());
		product.setLanguage(productD.getLanguage());
		product.setPublisher(productD.getPublisher());
		product.setPublishyear(productD.getPublishyear());
		product.setRegion(productD.getRegion());
		product.setSupplier(productD.getSupplier());
		product.setTitle(productD.getTitle());
		productRepo.save(product);
		return productMapper.convertToDTO(product);
	}

	@Override
	public List<ProductDTO> showToPromotion(Integer id) {
		List<Product> products = productRepo.getByCate(id);
		return convertListDTO(products);
	}

	@Override
	public List<ProductDTO> getListProductToBlackList(Integer id) {
		List<Product> listProduct = productRepo.listProductBlackList(id);
		return convertListDTO(listProduct);
	}
	
	@Override
	public List<PromotionDetailPRDTO> getListProductVsSale(){
		List<Product> listProduct = productRepo.findListProductStatus1();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> findListProductFavourites() throws ServletException, IOException {
		Account account = orderService.getAccount();
		System.out.println("oke oke");
		List<Product> listProduct = productRepo.findListProductFavourites(account.getId());
		List<PromotionDetailPRDTO> listReturn = convertToPromotionDetailPRDTO(listProduct);
		return listReturn;
	}

	@Override
	public List<PromotionDetailPRDTO> getSearchListProductVsSale(String keyword) {
		List<Product> listProduct = productRepo.searchListProductStatus1(keyword);
		return convertToPromotionDetailPRDTO(listProduct);
	}

	public List<PromotionDetailPRDTO> convertToPromotionDetailPRDTO(List<Product> listProduct) {
		return listProduct.stream().map(elm->{
			PromotionDetailPRDTO newE = new PromotionDetailPRDTO();
			newE.setProduct(productMapper.convertToDTO(elm));
			Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(elm.getId());
			if(promotion.isPresent()) newE.setSale(promotion.get().getSale());
			return newE;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PromotionDetailPRDTO> showProductRelated(Product product, Integer page) {
		if(product.getTitle()==null) return null;
		Sort sort = Sort.by("createDate").descending();
		Pageable pageable = PageRequest.of(page, 4, sort);
		List<Product> listProduct = productRepo.findProductByTitle(product.getTitle().getId(), pageable);
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> showProductRelatedCategory(Product product, Integer page) {
		if(product.getCategorie()==null) return null;
		Sort sort = Sort.by("createDate").descending();
		Pageable pageable = PageRequest.of(page, 5, sort);
		List<Product> listProduct = productRepo.findProductByCategorieId(product.getCategorie().getId(), pageable);
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> top10ProductNews() {
		List<Product> listProduct = productRepo.top10ProductPlus();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> top10ProductSale() {
		List<Product> listProduct = productRepo.top10ProductBestSeller();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> top10ProductTrending() {
		List<Product> listProduct = productRepo.top10ProductTrending();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<PromotionDetailPRDTO> top10ProductBanChay() {
		List<Product> listProduct = productRepo.top10ProductBanChay();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public ProductResponseDTO findListToCateLV1() {
		List<Product> listProduct3 = productRepo.findListToCateLV1(3);
		List<Product> listProduct4 = productRepo.findListToCateLV1(4);
		List<Product> listProduct5 = productRepo.findListToCateLV1(5);
		List<Product> listProduct6 = productRepo.findListToCateLV1(6);
		ProductResponseDTO newListReturn = new ProductResponseDTO();
		newListReturn.setListProduct1(convertToPromotionDetailPRDTO(listProduct3));
		newListReturn.setListProduct2(convertToPromotionDetailPRDTO(listProduct4));
		newListReturn.setListProduct3(convertToPromotionDetailPRDTO(listProduct5));
		newListReturn.setListProduct4(convertToPromotionDetailPRDTO(listProduct6));
		return newListReturn;
	}

	@Override
	public List<PromotionDetailPRDTO> findListCustomForm(String sqlOld)  {
		StringBuilder sql = new StringBuilder("SELECT p FROM Product p ");
		sql.append(sqlOld);
		Query query = entityManager.createQuery(sql.toString(), Product.class);
		List<Product> listProduct = query.getResultList();
		return convertToPromotionDetailPRDTO(listProduct);
	}

	@Override
	public List<String> findListLanguage() {
		return productRepo.findListLanguage();
	}

	@Override
	public List<String> findListNxb() {
		return productRepo.findListNXB();
	}

	@Override
	public ProductDTO updateStatus(Integer id) {
		Optional<Product> productOptional = productRepo.findById(id);
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		Product product = productOptional.get();
		if (product.getAvailable() == Status.STATUS_HOAT_DONG) {
			product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
		} else {
			if(product.getQuantity()<=0)
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Sản phẩm đã bán hết!");
			product.setAvailable(Status.STATUS_HOAT_DONG);
		}
		productRepo.save(product);
		return productMapper.convertToDTO(product);
	}


	@Override
	public ProductDTO showDetail(Integer id) {
		Optional<Product> productOptional = productRepo.findById(id);
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại !");
		return productMapper.convertToDTO(productOptional.get());
	}


}
