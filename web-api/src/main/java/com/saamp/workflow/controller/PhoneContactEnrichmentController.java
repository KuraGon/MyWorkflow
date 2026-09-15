package com.saamp.workflow.controller;

import com.saamp.workflow.service.PhoneContactEnrichmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phone-contacts")
@SecurityRequirement(name = "basicAuth")
public class PhoneContactEnrichmentController {

    private final PhoneContactEnrichmentService enrichmentService;

    @PostMapping("/enrich")
    public ResponseEntity<Map<String, Object>> enrichContacts() {
        int updated = enrichmentService.enrichMissingContacts();

        Map<String, Object> body = new HashMap<>();
        body.put("updatedContacts", updated);

        return ResponseEntity.ok(body);
    }
}
