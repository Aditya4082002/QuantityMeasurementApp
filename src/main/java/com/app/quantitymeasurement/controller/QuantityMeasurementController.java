package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.dto.QuantityInputDTO;
import com.app.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;
   
    
    //COMPARE
    @PostMapping("/compare")
    public QuantityMeasurementDTO compare(@Valid @RequestBody QuantityInputDTO input) {
        return service.compare(input);
    }

    //ADD
    @PostMapping("/add")
    public QuantityMeasurementDTO add(@Valid @RequestBody QuantityInputDTO input) {
        return service.add(input);
    }

    //SUBTRACT
    @PostMapping("/subtract")
    public QuantityMeasurementDTO subtract(@Valid @RequestBody QuantityInputDTO input) {
        return service.subtract(input);
    }

    //DIVIDE
    @PostMapping("/divide")
    public QuantityMeasurementDTO divide(@Valid @RequestBody QuantityInputDTO input) {
        return service.divide(input);
    }

    //CONVERT
    @PostMapping("/convert")
    public QuantityMeasurementDTO convert(@Valid @RequestBody QuantityInputDTO input) {
        return service.convert(input);
    }

    //HISTORY
    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementDTO> getHistory(@PathVariable String operation) {
        return service.getHistoryByOperation(operation);
    }

    //COUNT
    @GetMapping("/count/{operation}")
    public long count(@PathVariable String operation) {
        return service.countByOperation(operation);
    }
}