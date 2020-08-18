package kr.heesu.calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberTest {

    @Test
    void ExceptionTest() {
        assertThatThrownBy(() -> new Number(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void plusTest() {
        Number number = new Number(3);
        Number operand = new Number(2);


    }
}