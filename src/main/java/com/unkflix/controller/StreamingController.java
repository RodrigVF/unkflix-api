package com.unkflix.controller;

import com.unkflix.controller.request.StreamingRequest;
import com.unkflix.controller.response.StreamingResponse;
import com.unkflix.entity.Streaming;
import com.unkflix.mapper.StreamingMapper;
import com.unkflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unkflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;



    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streamings = streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return ResponseEntity.ok(streamings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        if (streamingService.findById(id).isPresent()) {
            streamingService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Streaming de ID: " + id + " não encontrado!");
        }
    }
}
