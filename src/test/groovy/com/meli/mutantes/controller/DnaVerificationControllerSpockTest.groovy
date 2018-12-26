package com.meli.mutantes.controller

import com.meli.mutantes.controller.model.DnaVerificationRequest
import com.meli.mutantes.repository.DnaTypeRepository
import com.meli.mutantes.services.DNAVerifier
import org.junit.Test
import org.springframework.http.HttpStatus
import spock.lang.Specification

class DnaVerificationControllerSpockTest extends Specification {

    private DNAVerifier verifier = Mock(DNAVerifier)

    private DnaTypeRepository repository = Mock(DnaTypeRepository)

    private DnaVerificationController controller = new DnaVerificationController(verifier, repository)

    @Test
    void "Test mutant service response response"() {
        given:
        def request = new DnaVerificationRequest(["ANYDNA"])

        and:
        verifier.isMutant(_) >> isMutant

        expect:
        def response = controller.checkMutant(request)
        response.statusCode == expectedStatusCode

        where:
        isMutant | expectedStatusCode
        true     | HttpStatus.OK
        false    | HttpStatus.FORBIDDEN
    }

}
