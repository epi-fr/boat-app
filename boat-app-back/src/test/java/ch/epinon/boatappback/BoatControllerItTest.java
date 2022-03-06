package ch.epinon.boatappback;

import org.junit.ClassRule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class BoatControllerItTest {

    @ClassRule
    public static MySQLContainer<BoatDbContainer> mySQLContainer = BoatDbContainer.getInstance();

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void givenValidCreateBoatDto_whenCallPostAddBoat_thenResponseStatusIs201() throws Exception {
        this.mockMvc.perform(post("/boat")
                        .content(asJsonString(new CreateBoatDto("test", "testDesc")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void givenUnknownId_whenCallGetBoat_thenResponseStatusIs404() throws Exception {
        this.mockMvc.perform(get("/boat/150"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(3)
    void givenKnownId_whenCallGetBoat_thenResponseStatusIs200WithBoatBody() throws Exception {
        this.mockMvc.perform(get("/boat/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.description").value("testDesc"));
    }

    @Test
    @Order(4)
    void whenCallGetAllBoat_thenResponseStatusIs200WithBoatArrayBody() throws Exception {
        this.mockMvc.perform(get("/boat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }

    @Test
    @Order(5)
    void givenValidUpdateBoatDto_whenCallPostUpdateBoat_thenResponseStatusIs200() throws Exception {
        this.mockMvc.perform(put("/boat/1")
                        .content(asJsonString(new BoatDto(1L, "test", "testDesc")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(6)
    void givenNotValidCreateBoatDto_whenCallPostAddBoat_thenResponseStatusIs400() throws Exception {
        this.mockMvc.perform(post("/boat")
                        .content(asJsonString(new CreateBoatDto(null, "testDesc")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(7)
    void givenUnValidUpdateBoatDto_whenCallPostUpdateBoat_thenResponseStatusIs200() throws Exception {
        this.mockMvc.perform(put("/boat/1")
                        .content(asJsonString(new UpdateBoatDto(null, "testDesc")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(8)
    void givenValidUpdateBoatDtoOnUnknown_whenCallPostUpdateBoat_thenResponseStatusIs200() throws Exception {
        this.mockMvc.perform(put("/boat/1150")
                        .content(asJsonString(new UpdateBoatDto(null, "testDesc")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(9)
    void whenDeleteBoat_thenResponseStatusIs200() throws Exception {
        this.mockMvc.perform(delete("/boat/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @Order(10)
    void whenDeleteAnUnknownBoat_thenResponseStatusIs400() throws Exception {
        this.mockMvc.perform(delete("/boat/11"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }
}