package com.laurentiuspilca.ssia.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import ssia.config.ProjectConfig;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderBeanTest {

    private final PasswordEncoder encoder = new ProjectConfig().passwordEncoder();

    @Test
    @DisplayName("По умолчанию используется bcrypt — encode добавляет префикс {bcrypt}")
    void defaultEncoderIsBcrypt() {
        String encoded = encoder.encode("password");

        assertThat(encoded).startsWith("{bcrypt}");
    }

    @Test
    @DisplayName("Поддерживаются зарегистрированные алгоритмы: noop, bcrypt, scrypt")
    void supportsRegisteredAlgorithms() {
        assertThat(encoder.matches("password", "{noop}password")).isTrue();
        assertThat(encoder.matches("password", encoder.encode("password"))).isTrue(); // {bcrypt}

        // scrypt — через сам scrypt-энкодер, чтобы получить валидный хэш
        var scrypt = org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
                .defaultsForSpringSecurity_v5_8();
        assertThat(encoder.matches("password", "{scrypt}" + scrypt.encode("password"))).isTrue();
    }

    @Test
    @DisplayName("Неизвестный алгоритм вызывает ошибку — конфигурация не расширяется молча")
    void unknownAlgorithmThrows() {
        assertThat(org.assertj.core.api.Assertions
                .catchThrowable(() -> encoder.matches("password", "{md5}whatever")))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
