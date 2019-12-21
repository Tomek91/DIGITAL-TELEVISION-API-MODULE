package pl.com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.app.dto.CategoryDTO;
import pl.com.app.model.rest.ResponseMessage;
import pl.com.app.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<CategoryDTO>>> findAll() {

        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(ResponseMessage.<List<CategoryDTO>>builder().data(categoryService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<CategoryDTO>> findOne(@PathVariable Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseMessage.<CategoryDTO>builder().data(categoryService.findOne(id)).build());
    }


    @PostMapping
    public ResponseEntity<ResponseMessage<CategoryDTO>> add(RequestEntity<CategoryDTO> requestEntity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<CategoryDTO>builder().data(categoryService.add(requestEntity.getBody())).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<CategoryDTO>> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<CategoryDTO>builder().data(categoryService.update(id, categoryDTO)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<CategoryDTO>> delete(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<CategoryDTO>builder().data(categoryService.delete(id)).build());
    }

}
