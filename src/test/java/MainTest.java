import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {


    @Test
    @Disabled
    public void expectedExecutionTimeNoMore22Seconds() {
        assertTimeout(Duration.ofSeconds(22),() -> Main.main(new String[0]));
    }
}
