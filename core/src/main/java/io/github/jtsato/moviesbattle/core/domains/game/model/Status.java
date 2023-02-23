package io.github.jtsato.moviesbattle.core.domains.game.model;

import java.util.Arrays;

/**
 * @author Jorge Takeshi Sato
 */

public enum Status {

    IN_PROGRESS {

        @Override
        public String getMessageKey() {
            return "enum-status-in-progress";
        }
    },

    OVER {

        @Override
        public String getMessageKey() {
            return "enum-status-over";
        }
    };

    public abstract String getMessageKey();

    public boolean is(final Status other) {
        return equals(other);
    }

    public boolean isNot(final Status other) {
        return !is(other);
    }

    public boolean in(final Status... others) {
        return Arrays.asList(others).contains(this);
    }

    public boolean notIn(final Status... others) {
        return Arrays.stream(others).noneMatch(this::equals);
    }
}