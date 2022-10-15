package me.binhle.mailgenerator.service;

import me.binhle.mailgenerator.exception.MissingParameterValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StringFormatServiceImplTest {
    @InjectMocks
    private StringFormatServiceImpl stringFormatService;

    @Test
    public void whenFormat_shouldReturnFormattedContent() {
        // Prepare data
        String template = "Hi {user}, welcome to {site}";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        String site = "ABC Solutions";
        parameters.put("user", username);
        parameters.put("site", site);
        parameters.put("time", "2022/10/16 00:00:00");

        // Invoke method
        String actualFormattedContent = stringFormatService.format(template, parameters);

        // Verity
        assertEquals("Hi Bob, welcome to ABC Solutions", actualFormattedContent);
    }

    @Test
    public void whenFormatWithoutParameters_shouldReturnOriginalContent() {
        // Prepare data
        String template = "Hi Alice, welcome to ABX Solutions";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        String site = "ABC Solutions";
        parameters.put("user", username);
        parameters.put("site", site);

        // Invoke method
        String actualFormattedContent = stringFormatService.format(template, parameters);

        // Verity
        assertEquals(template, actualFormattedContent);
    }

    @Test
    public void whenFormatMissingLeftBrace_shouldReturnFormattedContent() {
        // Prepare data
        String template = "Hi user}, welcome to {site}";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        String site = "ABC Solutions";
        parameters.put("user", username);
        parameters.put("site", site);
        parameters.put("time", "2022/10/16 00:00:00");

        // Invoke method
        String actualFormattedContent = stringFormatService.format(template, parameters);

        // Verity
        assertEquals("Hi user}, welcome to ABC Solutions", actualFormattedContent);
    }

    @Test
    public void whenFormatMissingRightBrace_shouldReturnFormattedContent() {
        // Prepare data
        String template = "Hi {user, welcome to {site}";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        String site = "ABC Solutions";
        parameters.put("user", username);
        parameters.put("site", site);
        parameters.put("time", "2022/10/16 00:00:00");

        // Invoke method
        String actualFormattedContent = stringFormatService.format(template, parameters);

        // Verity
        assertEquals("Hi {user, welcome to ABC Solutions", actualFormattedContent);
    }

    @Test
    public void whenFormatContainsBraces_shouldReturnFormattedContent() {
        // Prepare data
        String template = "Hi {user}} {}}{, welcome to {{site}";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        String site = "ABC Solutions";
        parameters.put("user", username);
        parameters.put("site", site);
        parameters.put("time", "2022/10/16 00:00:00");

        // Invoke method
        String actualFormattedContent = stringFormatService.format(template, parameters);

        // Verity
        assertEquals("Hi Bob} {}}{, welcome to {ABC Solutions", actualFormattedContent);
    }

    @Test
    public void whenTemplateNull_throwIllegalArgumentException() {
        // Invoke method
        Exception exception = assertThrows(IllegalArgumentException.class, () -> stringFormatService.format(null, new HashMap<>()));

        // Verity
        assertEquals("'template' can not be null", exception.getMessage());
    }

    @Test
    public void whenParametersNull_throwIllegalArgumentException() {
        // Prepare data
        String template = "Hi {user}, welcome to {site}";

        // Invoke method
        Exception exception = assertThrows(IllegalArgumentException.class, () -> stringFormatService.format(template, null));

        // Verity
        assertEquals("'parameters' can not be null", exception.getMessage());
    }

    @Test
    public void whenMissingParameterValue_throwMissingParameterValueException() {
        // Prepare data
        String template = "Hi {user}, welcome to {site}";
        Map<String, String> parameters = new HashMap<>();
        String username = "Bob";
        parameters.put("user", username);

        // Invoke method
        Exception exception = assertThrows(MissingParameterValueException.class, () -> stringFormatService.format(template, parameters));

        // Verity
         assertEquals("Missing parameter value for 'site'", exception.getMessage());
    }
}