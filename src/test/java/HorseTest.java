import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.awt.datatransfer.StringSelection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;

@ExtendWith(MockitoExtension.class)
public class HorseTest {


    @Test
    public void expectedExceptionWhenCreatingHorseWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
    }

    @Test
    public void expectedMessageExceptionWhenCreatingHorseWithNullName(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    public void expectedExceptionWhenUsingSpaceSymbolsForCreatingHorse(String input){
        assertThrows(IllegalArgumentException.class, () -> new Horse(input, 1.0, 1.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ","    "})
    public void expectedMessageExceptionWhenUsingSpaceSymbolsForCreatingHorse(String input){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(input, 1.0, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void expectedExceptionWhenCreatingHorseWithNegativeSpeed(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("George", -1.0, 1.0));
    }

    @Test
    public void expectedMessageExceptionWhenCreatingHorseWithNegativeSpeed(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("George", -1.0, 1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }


    @Test
    public void expectedExceptionWhenCreatingHorseWithNegativeDistance(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("George", 1.0, -1.0));
    }

    @Test
    public void expectedMessageExceptionWhenCreatingHorseWithNegativeDistance(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("George", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void expectedCorrectWorkingMethodGetName(){
        String name = "George";
        Horse horse = new Horse(name, 1.0, 1.0);
        assertEquals(name, horse.getName());
    }


    @Test
    public void expectedCorrectWorkingMethodGetSpeed(){
        double speed = 1.0;
        Horse horse = new Horse("George", speed, 1.0);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    public void expectedCorrectWorkingMethodGetDistance(){
        double distance = 1.0;
        Horse horseAllParams = new Horse("George", 1.0, distance);
        Horse horseTwoParams = new Horse("George", 1.0);

        assertAll("Тест с двумя входящими проверками.",
                () -> assertEquals(distance, horseAllParams.getDistance()),
                () -> assertEquals(0.0, horseTwoParams.getDistance())
        );
    }

    @Test
    public void expectedCallGetRandomDoubleInMove() {
        try (MockedStatic<Horse> mockStatic1 = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("George", 1.0, 1.0);
            horse.move();

            mockStatic1.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void shouldMockGetRandomDoubleInMove() {
        try(MockedStatic<Horse> mockStatic1 = Mockito.mockStatic(Horse.class)) {
            mockStatic1.when(() -> Horse.getRandomDouble(anyDouble(), anyDouble())).thenReturn(11.0);
            Horse horse = new Horse("George", 1.0, 1.0);
            horse.move();
            assertEquals(12.0, horse.getDistance());
        }

    }

}
