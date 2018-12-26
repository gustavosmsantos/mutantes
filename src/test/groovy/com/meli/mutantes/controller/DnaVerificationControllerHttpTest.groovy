package com.meli.mutantes.controller

import com.meli.mutantes.domain.DnaType
import com.meli.mutantes.repository.DnaTypeCount
import com.meli.mutantes.repository.DnaTypeRepository
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import javax.transaction.Transactional


import static org.junit.Assert.assertThat
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner)
@Transactional
class DnaVerificationControllerHttpTest {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private DnaTypeRepository repository;

    @Test
    void "Test mutant request"() {

        def firstCount = repository.countHumansAndMutants()
        assertThat(firstCount.size(), Matchers.is(0))

        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))
                .andExpect(MockMvcResultMatchers.status().is(200))

        def counts = repository.countHumansAndMutants()
        assertThat(counts.size(), Matchers.is(1))

        def count = counts.get(0)
        assertThat(count.type, Matchers.is(DnaType.MUTANT))
        assertThat(count.count, Matchers.is(1L))

    }

    @Test
    void "Test human request"() {

        def firstCount = repository.countHumansAndMutants()
        assertThat(firstCount.size(), Matchers.is(0))

        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"]}"))
                .andExpect(MockMvcResultMatchers.status().is(403))

        def counts = repository.countHumansAndMutants()
        assertThat(counts.size(), Matchers.is(1))

        def count = counts.get(0)
        assertThat(count.type, Matchers.is(DnaType.HUMAN))
        assertThat(count.count, Matchers.is(1L))

    }

    @Test
    void "Multiple calls with same DNA should count once"() {

        def firstCount = repository.countHumansAndMutants()
        assertThat(firstCount.size(), Matchers.is(0))

        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))
                .andExpect(MockMvcResultMatchers.status().is(200))

        //Same request
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))
                .andExpect(MockMvcResultMatchers.status().is(200))

        def counts = repository.countHumansAndMutants()
        assertThat(counts.size(), Matchers.is(1))

        def count = counts.get(0)
        assertThat(count.type, Matchers.is(DnaType.MUTANT))
        assertThat(count.count, Matchers.is(1L))

    }

    @Test
    void "Test stats request"() {
        mockMvc.perform(post("/mutant")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dna\":[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]}"))

        def expectedResponse = "{\"count_mutant_dna\":1,\"count_human_dna\":0,\"ratio\":1.0}"
        mockMvc.perform(get("/stats"))
            .andExpect(MockMvcResultMatchers.status().is(200))
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
    }

}
