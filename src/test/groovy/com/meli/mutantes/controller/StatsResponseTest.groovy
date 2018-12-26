package com.meli.mutantes.controller

import com.meli.mutantes.controller.model.StatsResponse
import org.junit.Test
import spock.lang.Specification

class StatsResponseTest extends Specification {

    @Test
    void "Test ratio calc"() {

        given:
        def response = new StatsResponse()
        response.setCountMutantDna(countMutantDna)
        response.setCountHumanDna(countHumanDna)

        expect:
        response.ratio() == ratio

        where:
        countMutantDna | countHumanDna | ratio
        0L             | 0L            | 0d
        1L             | 0L            | 1d
        0L             | 1L            | 0d
        40L            | 100L          | 0.4d

    }

}
