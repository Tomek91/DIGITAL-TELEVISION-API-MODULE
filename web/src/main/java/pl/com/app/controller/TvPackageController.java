package pl.com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.app.dto.TvPackageDTO;
import pl.com.app.model.rest.ResponseMessage;
import pl.com.app.service.TvPackageService;

import java.util.List;

@RestController
@RequestMapping("/tv-packages")
@RequiredArgsConstructor
public class TvPackageController {

    private final TvPackageService tvPackageService;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<TvPackageDTO>>> findAll() {

        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(ResponseMessage.<List<TvPackageDTO>>builder().data(tvPackageService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<TvPackageDTO>> findOne(@PathVariable Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseMessage.<TvPackageDTO>builder().data(tvPackageService.findOne(id)).build());
    }


    @PostMapping
    public ResponseEntity<ResponseMessage<TvPackageDTO>> add(RequestEntity<TvPackageDTO> requestEntity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<TvPackageDTO>builder().data(tvPackageService.add(requestEntity.getBody())).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<TvPackageDTO>> update(@PathVariable Long id, @RequestBody TvPackageDTO tvPackageDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<TvPackageDTO>builder().data(tvPackageService.update(id, tvPackageDTO)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<TvPackageDTO>> delete(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<TvPackageDTO>builder().data(tvPackageService.delete(id)).build());
    }

}
