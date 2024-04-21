package ru.korepanov.gamification.game.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.korepanov.gamification.challenge.ChallengeSolvedEvent;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class GameControllerTest { //todo замокировать дургие модули

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<ChallengeSolvedEvent> jsonChallengeSolvedDTO;

    @Test
    void postResult() throws Exception {
        var challengeSolvedDTO = new ChallengeSolvedEvent(
                1L,
                true,
                41,
                32,
                1L,
                "Ivan"
        );
        mockMvc.perform(post("/attempts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonChallengeSolvedDTO.write(challengeSolvedDTO).getJson()))
                .andExpect(status().isOk());
    }
}