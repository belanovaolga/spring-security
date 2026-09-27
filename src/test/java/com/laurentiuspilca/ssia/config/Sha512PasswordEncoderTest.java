package com.laurentiuspilca.ssia.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ssia.config.Sha512PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class Sha512PasswordEncoderTest {

    private final Sha512PasswordEncoder encoder = new Sha512PasswordEncoder();

    @Test
    @DisplayName("encode возвращает детерминированный хэш")
    void encode_isDeterministic() {
        String rawPassword = "mySecretPassword123";

        String hash1 = encoder.encode(rawPassword);
        String hash2 = encoder.encode(rawPassword);

        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    @DisplayName("encode не возвращает исходный пароль")
    void encode_doesNotReturnRawPassword() {
        String rawPassword = "mySecretPassword123";

        String encoded = encoder.encode(rawPassword);

        assertThat(encoded).isNotEqualTo(rawPassword);
    }

    @Test
    @DisplayName("encode возвращает непустой результат для валидного пароля")
    void encode_returnsNonEmptyResult() {
        String encoded = encoder.encode("password");

        assertThat(encoded).isNotBlank();
    }

    @Test
    @DisplayName("encode даёт разный результат для разных паролей")
    void encode_differentPasswords_differentHashes() {
        String hash1 = encoder.encode("password1");
        String hash2 = encoder.encode("password2");

        assertThat(hash1).isNotEqualTo(hash2);
    }

    @Test
    @DisplayName("matches возвращает true для корректного пароля")
    void matches_returnsTrueForCorrectPassword() {
        String rawPassword = "mySecretPassword123";
        String encoded = encoder.encode(rawPassword);

        assertThat(encoder.matches(rawPassword, encoded)).isTrue();
    }

    @Test
    @DisplayName("matches возвращает false для некорректного пароля")
    void matches_returnsFalseForWrongPassword() {
        String encoded = encoder.encode("correctPassword");

        assertThat(encoder.matches("wrongPassword", encoded)).isFalse();
    }

    @Test
    @DisplayName("matches учитывает регистр символов")
    void matches_isCaseSensitive() {
        String encoded = encoder.encode("Password");

        assertThat(encoder.matches("password", encoded)).isFalse();
        assertThat(encoder.matches("Password", encoded)).isTrue();
    }
}
