package com.laurentiuspilca.ssia.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ssia.config.PlainTextPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PlainTextPasswordEncoderTest {

    private final PlainTextPasswordEncoder encoder = new PlainTextPasswordEncoder();

    @Test
    @DisplayName("encode возвращает исходный пароль без изменений")
    void encode_returnsRawPassword() {
        String rawPassword = "mySecretPassword123";

        String encoded = encoder.encode(rawPassword);

        assertThat(encoded).isEqualTo(rawPassword);
    }

    @Test
    @DisplayName("encode возвращает пустую строку для пустого пароля")
    void encode_emptyPassword_returnsEmpty() {
        String encoded = encoder.encode("");

        assertThat(encoded).isEmpty();
    }

    @Test
    @DisplayName("matches возвращает true для совпадающего пароля")
    void matches_returnsTrueForMatchingPassword() {
        String rawPassword = "mySecretPassword123";
        String encoded = encoder.encode(rawPassword);

        boolean result = encoder.matches(rawPassword, encoded);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("matches возвращает false для несовпадающего пароля")
    void matches_returnsFalseForNonMatchingPassword() {
        String encoded = encoder.encode("correctPassword");

        boolean result = encoder.matches("wrongPassword", encoded);

        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("matches учитывает регистр символов")
    void matches_isCaseSensitive() {
        String encoded = encoder.encode("Password");

        assertThat(encoder.matches("password", encoded)).isFalse();
        assertThat(encoder.matches("PASSWORD", encoded)).isFalse();
        assertThat(encoder.matches("Password", encoded)).isTrue();
    }
}
