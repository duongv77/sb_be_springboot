package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.FavoriteDTO;
import duong.dev.dto.request.CreateFavoriteRequestDTO;
import duong.dev.entity.Favorite;

import javax.servlet.ServletException;


public interface FavoriteInterface {

	public List<FavoriteDTO> readAll() throws IOException;
	
	public FavoriteDTO create(CreateFavoriteRequestDTO favoriteD)throws IOException, ServletException;
	
	public FavoriteDTO delete(Favorite favoriteE) throws IOException ;
}
