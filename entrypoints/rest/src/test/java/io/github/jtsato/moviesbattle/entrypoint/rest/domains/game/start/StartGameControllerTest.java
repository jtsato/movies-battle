package io.github.jtsato.moviesbattle.entrypoint.rest.domains.game.start;

import io.github.jtsato.moviesbattle.core.domains.game.model.Game;
import io.github.jtsato.moviesbattle.core.domains.game.model.Status;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.start.StartGameCommand;
import io.github.jtsato.moviesbattle.core.domains.game.usecase.start.StartGameUseCase;
import io.github.jtsato.moviesbattle.core.domains.player.model.Player;
import io.github.jtsato.moviesbattle.entrypoint.rest.common.WebRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Jorge Takeshi Sato
 */

@DisplayName("Start Game Controller Test")
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = StartGameController.class)
class StartGameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StartGameUseCase startGameUseCase;

    @MockBean
    private WebRequest webRequest;

    @DisplayName("Successful to start new game")
    @Test
    void SuccessfulToStartNewGame() throws Exception {
        // Arrange
        when(webRequest.getEmail()).thenReturn("joe.doe.one@xyz.com");
        when(webRequest.getFullName()).thenReturn("Joe Doe");

        final StartGameCommand command = new StartGameCommand("joe.doe.one@xyz.com", "Joe Doe");
        when(startGameUseCase.execute(command)).thenReturn(createRegisteredGame());

        // Act
        // Assert
        mockMvc.perform(post("/v1/games")
            .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.player.id", is(1)))
            .andExpect(jsonPath("$.player.name", is("John Smith")))
            .andExpect(jsonPath("$.player.email", is("john.smith.zero@xyz.com")))
            .andExpect(jsonPath("$.player.createdAt", is("2020-03-12T22:04:59.123")))
            .andExpect(jsonPath("$.player.updatedAt", is("2020-03-12T22:04:59.123")))
            .andExpect(jsonPath("$.status", is("IN_PROGRESS")))
            .andExpect(jsonPath("$.createdAt", is("2020-03-12T22:04:59.123")))
            .andExpect(jsonPath("$.updatedAt", is("2020-03-12T22:04:59.123")));

        verify(startGameUseCase, times(1)).execute(command);
        verifyNoMoreInteractions(startGameUseCase);
    }

    private static Game createRegisteredGame() {
        return new Game(1L, createJohnSmithPlayer(), Status.IN_PROGRESS,
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123")
        );
    }

    private static Player createJohnSmithPlayer() {
        return new Player(1L, "John Smith", "john.smith.zero@xyz.com",
                LocalDateTime.parse("2020-03-12T22:04:59.123"),
                LocalDateTime.parse("2020-03-12T22:04:59.123"));
    }
}