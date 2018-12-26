package com.meli.mutantes.controller;

import com.meli.mutantes.controller.model.DnaVerificationRequest;
import com.meli.mutantes.controller.model.StatsResponse;
import com.meli.mutantes.domain.DnaType;
import com.meli.mutantes.domain.DnaTypeEntity;
import com.meli.mutantes.repository.DnaTypeCount;
import com.meli.mutantes.repository.DnaTypeRepository;
import com.meli.mutantes.services.DNAVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.meli.mutantes.domain.DnaType.HUMAN;
import static com.meli.mutantes.domain.DnaType.MUTANT;

@RestController
public class DnaVerificationController {

    private DNAVerifier verifier;

    private DnaTypeRepository repository;

    public DnaVerificationController(@Autowired DNAVerifier verifier,
                                     @Autowired DnaTypeRepository dnaTypeRepository) {
        this.verifier = verifier;
        this.repository = dnaTypeRepository;
    }

    @CacheEvict(cacheNames = "stats")
    @PostMapping("/mutant")
    public ResponseEntity<Void> checkMutant(@Valid @RequestBody DnaVerificationRequest request) {

        boolean isMutant = verifier.isMutant(request.getDna().toArray(new String[0]));
        saveDnaResult(request.getDna(), isMutant);

        ResponseEntity.BodyBuilder builder =
                isMutant ? ResponseEntity.ok() : ResponseEntity.status(HttpStatus.FORBIDDEN);

        return builder.build();
    }

    @Cacheable(cacheNames = "stats")
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> viewStats() {

        List<DnaTypeCount> dnaTypeCounts = repository.countHumansAndMutants();

        StatsResponse bodyResponse = new StatsResponse();
        bodyResponse.setCountMutantDna(filterCountByType(dnaTypeCounts, MUTANT));
        bodyResponse.setCountHumanDna(filterCountByType(dnaTypeCounts, HUMAN));

        return ResponseEntity.ok(bodyResponse);
    }


    private void saveDnaResult(List<String> dna, boolean isMutant) {
        Integer hashed = String.join("", dna).hashCode();
        DnaTypeEntity entity = new DnaTypeEntity();
        entity.setHashedDna(hashed);
        entity.setDnaType(isMutant ? MUTANT : HUMAN);
        repository.save(entity);
    }

    private Long filterCountByType(List<DnaTypeCount> dnaTypeCounts, DnaType type) {
        return dnaTypeCounts.stream().filter(c -> type == c.getType())
                .findFirst().map(DnaTypeCount::getCount).orElse(0L);
    }

}
