package pl.app.backend.game.dto;

import java.util.Set;

public record SendRespond(Long questionId, Set<Long> answerIds) {
}
