package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateFavoriteRequestDTO;
import duong.dev.service.FavoriteInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class FavoriteController {
    @Autowired
    private FavoriteInterface favoriteSev;

    @PostMapping("/v2/user/favorite-create")
    private ResponseEntity<ResponseDTO<?>> createFavorite(@RequestBody @Valid CreateFavoriteRequestDTO createFavorite)throws IOException, ServletException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(favoriteSev.create(createFavorite))
                .build());
    }
}
