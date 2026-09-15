package com.saamp.workflow.controller;

import com.saamp.workflow.service.PricingService;
import com.workflow.api.PricingAdminApi;
import com.workflow.dto.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@SecurityRequirement(name = "basicAuth")
public class PricingController implements PricingAdminApi {

    private final PricingService pricingService;

    @Override
    public ResponseEntity<List<NatureDTO>> getNatures() {
        return ResponseEntity.ok(pricingService.getAllNatures());
    }

    @Override
    public ResponseEntity<List<TarifRuleDTO>> getRules(String natureId, String type) {
        return ResponseEntity.ok(pricingService.getRules(natureId, type));
    }

    @Override
    public ResponseEntity<PriceGridDTO> getPriceGrid(String ruleType, String ruleCode) {
        return ResponseEntity.ok(pricingService.getPriceGrid(ruleType, ruleCode));
    }

    @Override
    public ResponseEntity<PriceGridDTO> updatePriceGrid(String ruleType, String ruleCode, PriceGridDTO priceGrid) {
        return ResponseEntity.ok(pricingService.updatePriceGrid(ruleType, ruleCode, priceGrid));
    }
}