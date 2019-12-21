package pl.com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.app.model.rest.ResponseMessage;
import pl.com.app.service.DataInitializerService;

@RestController
@RequestMapping("/init-data")
@RequiredArgsConstructor
public class DataInitializerController {

    private final DataInitializerService dataInitializerService;

    @GetMapping
    public ResponseEntity<ResponseMessage<String>> dataInit() {
        dataInitializerService.initData();
        HttpHeaders httpHeaders = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseMessage.<String>builder().data("Init data ok.").build());
    }

}
