package pl.app.game.dto;

import java.util.Set;

public record SendRespond(Long questionId, Set<Long> answerIds) {
}
