import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class HippodromeTest {

    @Test
    public void expectedExceptionsWhenCreatingHippodromeWithNullListHorses() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void expectedMessageExceptionsWhenCreatingHippodromeWithNullListHorses() {
       Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
       assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    public void expectedExceptionsWhenCreatingHippodromeWithEmptyListHorses() {
        List<Horse> list = new ArrayList<Horse>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
    }

    @Test
    public void expectedMessageExceptionsWhenCreatingHippodromeWithEmptyListHorses() {
        List<Horse> list = new ArrayList<Horse>();
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    public void shouldCompareListHorsesInParameterAndAfterMethodGetHorses(){
        List<Horse> list = new ArrayList<>();
        for (int i = 0; i <30; i++) {
            list.add(new Horse("Horse" + i, 1));
        }

        Hippodrome hippodrome = new Hippodrome(list);

        Horse[] horses1 = new Horse[list.size()];
        horses1 = list.toArray(horses1);
        Horse[] horses2 = new Horse[hippodrome.getHorses().size()];
        horses2 = hippodrome.getHorses().toArray(horses2);


        assertArrayEquals(horses1,horses2);

    }
    @Mock
    Horse horse;

    @Test
    public void expectedMethodMove50timesWithListMockHorses(){

        List<Horse> list = new ArrayList<>();
        for (int i = 0; i <50; i++) {
            list.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();

       Mockito.verify(horse, times(list.size())).move();

    }


    @Test
    public void expectedMethodGetWinnerReturnsHorseWithMaxDistance(){
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("Horse1", 1,2));
        list.add(new Horse("Horse2", 1,3));
        list.add(new Horse("Horse3", 1,4));

        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list.get(2), hippodrome.getWinner());
    }

}
