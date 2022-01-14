package duong.dev.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Author;
import duong.dev.mapper.AuthorMapper;
import duong.dev.service.AuthorInterface;
import duong.dev.serviceImpl.AuthorServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminAuthorController {
	@Autowired private AuthorInterface authorSV;
	@Autowired AuthorMapper authorMapper;
	
	@PostMapping("/v2/admin/author")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody AuthorDTO authorD) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.create(authorD))
                .build());
	}
	
	@PutMapping("/v2/admin/author")
	private ResponseEntity<ResponseDTO<?>> update(@Valid @RequestBody AuthorDTO authorD) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.update(authorD))
                .build());
	}
	
	@DeleteMapping("/v2/admin/author/{id}")
	private ResponseEntity<ResponseDTO<?>> delete(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.delete(id))
                .build());
	}
	
	@GetMapping("/v2/admin/author/sort={sort}")
	private ResponseEntity<ResponseDTO<?>> readAll(@PathVariable("sort") String sort) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(authorSV.readAll(sort))
                .build());
	}
	
	@GetMapping("/v2/admin/author/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(authorSV.searchAuthor(sort, keyword))
				.build());
	}
	
	
	@PutMapping("/v2/admin/author/updateStatus/{id}")
	private ResponseEntity<ResponseDTO<?>> updateStatus(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(authorSV.updateStatus(id))
				.build());
	}
	
}
