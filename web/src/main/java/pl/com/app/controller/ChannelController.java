package pl.com.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.app.dto.ChannelDTO;
import pl.com.app.model.rest.ResponseMessage;
import pl.com.app.service.ChannelService;

import java.util.List;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping
    public ResponseEntity<ResponseMessage<List<ChannelDTO>>> findAll() {

        HttpHeaders httpHeaders = new HttpHeaders();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body(ResponseMessage.<List<ChannelDTO>>builder().data(channelService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<ChannelDTO>> findOne(@PathVariable Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseMessage.<ChannelDTO>builder().data(channelService.findOne(id)).build());
    }


    @PostMapping
    public ResponseEntity<ResponseMessage<ChannelDTO>> add(RequestEntity<ChannelDTO> requestEntity) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<ChannelDTO>builder().data(channelService.add(requestEntity.getBody())).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage<ChannelDTO>> update(@PathVariable Long id, @RequestBody ChannelDTO channelDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<ChannelDTO>builder().data(channelService.update(id, channelDTO)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<ChannelDTO>> delete(@PathVariable Long id) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(null)
                .body(ResponseMessage.<ChannelDTO>builder().data(channelService.delete(id)).build());
    }

}
