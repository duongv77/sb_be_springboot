package duong.dev.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import duong.dev.dto.ResponseDTO;

import java.util.stream.Collectors;

@RestControllerAdvice
public class BaseErrorsHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException appException){
        return new ResponseEntity<ResponseDTO<?>>(ResponseDTO.builder()
                .messageCode(appException.getMessageCode())
                .messageName(appException.getMessageName()).build(), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<?> handleAppException(MethodArgumentNotValidException ex){
        return ResponseDTO.builder().messageCode("002")
                .messageName("Tham số đầu vào không hợp lệ")
                .data(ex.getBindingResult().getAllErrors().stream()
                        .map(FieldError.class::cast)
                        .collect(Collectors.toMap(FieldError::getField,
                                error -> String.valueOf(error.getDefaultMessage()),(p, q) -> p)))
                .build();
    }
    
}