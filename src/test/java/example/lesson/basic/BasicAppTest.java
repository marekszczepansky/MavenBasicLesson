package example.lesson.basic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAppTest {

    @Test
    void shouldReverseAString() {
        String reversed = BasicApp.reverse("Poznań");
        assertEquals("ńanzoP", reversed);
    }
}
