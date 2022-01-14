package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateCommentRequestDTO;
import duong.dev.service.CommentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class CommentController {
    @Autowired
    private CommentInterface commentSev;

    @GetMapping("/v1/comment/list-comment/product-id/{id}")
    private ResponseEntity<ResponseDTO<?>> getCategoryBig(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(commentSev.findListCommentToProductId(id))
                .build());
    }

    @PostMapping("/v1/user/comment")
    private ResponseEntity<ResponseDTO<?>> getCategoryBig(@RequestBody @Valid CreateCommentRequestDTO obj){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(commentSev.create(obj))
                .build());
    }

    @GetMapping("/v2/admin/comment-count")
    private ResponseEntity<ResponseDTO<?>> findCountComment(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(commentSev.findCountComment())
                .build());
    }
}
