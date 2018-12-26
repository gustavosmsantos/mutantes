package com.meli.mutantes.controller;

import com.meli.mutantes.controller.model.DnaVerificationRequest;
import com.meli.mutantes.services.DNAVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DnaVerificationController {

    @Autowired
    private DNAVerifier verifier;

    @PostMapping("/mutant")
    public ResponseEntity<Void> checkMutant(@Valid @RequestBody DnaVerificationRequest request) {

        boolean isMutant = verifier.isMutant(request.getDna().toArray(new String[0]));

        ResponseEntity.BodyBuilder builder =
                isMutant ? ResponseEntity.ok() : ResponseEntity.status(HttpStatus.FORBIDDEN);

        return builder.build();
    }

}
