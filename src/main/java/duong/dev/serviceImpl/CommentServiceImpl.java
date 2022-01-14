package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.request.CreateCommentRequestDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.CommentDTO;
import duong.dev.entity.Comment;
import duong.dev.mapper.CommentMapper;
import duong.dev.repository.CommentRepository;
import duong.dev.service.CommentInterface;

import javax.servlet.ServletException;

@Service
public class CommentServiceImpl implements CommentInterface {
	@Autowired
	private CommentRepository commentRepo;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private OrderServiceImpl  orderRepo;


	public List<CommentDTO> convertListDTO(List<Comment> listCommentsE) {
		List<CommentDTO> listCommentD = new ArrayList<CommentDTO>();
		for (Comment commentsE : listCommentsE) {
			listCommentD.add(commentMapper.convertToDTO(commentsE));
		}
		return listCommentsE.stream().map(elm->{
			return commentMapper.convertToDTO(elm);
		}).collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> readAll() throws IOException {
		List<Comment> listCommentE = commentRepo.findAll();
		return convertListDTO(listCommentE);

	}

	@Override
	public List<CommentDTO> findListCommentToProductId(Integer id) {
		Optional<Product> productOptional = productRepo.findById(id);
		if (!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không tìm thấy sản phẩm!");
		List<Comment> listCommentE = commentRepo.findListCommentByProductId(id);
		return convertListDTO(listCommentE);
	}

	@Override
	public CommentDTO create(CreateCommentRequestDTO commentD){
		Optional<Product> productOptional = productRepo.findById(commentD.getId());
		if (!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không tìm thấy sản phẩm!");
		Comment commentE = new Comment();
		commentE.setComment(commentD.getComment());
		commentE.setStatus(Status.STATUS_HOAT_DONG);
		commentE.setProduct(productOptional.get());
		try {
			commentE.setAccount(orderRepo.getAccount());
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		commentRepo.save(commentE);
		return commentMapper.convertToDTO(commentE);
		
	}

	@Override
	public CommentDTO update(CommentDTO commentD) throws IOException {
		
		return null;

	}

	@Override
	public CommentDTO delete(Comment commentE) throws IOException {
		commentRepo.delete(commentE);
		return commentMapper.convertToDTO(commentE);
	}

	@Override
	public Long findCountComment() {
		return commentRepo.findCountComment();
	}

}
