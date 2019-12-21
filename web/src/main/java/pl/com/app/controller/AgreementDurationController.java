package pl.com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.app.dto.AgreementDurationDTO;
import pl.com.app.model.rest.ResponseMessage;
import pl.com.app.service.AgreementDurationService;

import java.util.List;

@RestController
@RequestMapping("/agreement-duration")
@RequiredArgsConstructor
public class AgreementDurationController {

    private final AgreementDurationService agreementDurationService;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<AgreementDurationDTO>>> findAll() {

        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(ResponseMessage.<List<AgreementDurationDTO>>builder().data(agreementDurationService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<AgreementDurationDTO>> findOne(@PathVariable Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseMessage.<AgreementDurationDTO>builder().data(agreementDurationService.findOne(id)).build());
    }


    @PostMapping
    public ResponseEntity<ResponseMessage<AgreementDurationDTO>> add(RequestEntity<AgreementDurationDTO> requestEntity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<AgreementDurationDTO>builder().data(agreementDurationService.add(requestEntity.getBody())).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<AgreementDurationDTO>> update(@PathVariable Long id, @RequestBody AgreementDurationDTO agreementDurationDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<AgreementDurationDTO>builder().data(agreementDurationService.update(id, agreementDurationDTO)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<AgreementDurationDTO>> delete(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<AgreementDurationDTO>builder().data(agreementDurationService.delete(id)).build());
    }

}
