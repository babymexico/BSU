package org.example;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseConverterTest {

    @Test
    public void testBaseConverter() {
        // Arrange
        double celsiusTemperature = 25.0;
        BaseConverter baseConverter = new BaseConverter(celsiusTemperature);

        // Act & Assert
        assertEquals(298.15, baseConverter.convertToKelvin(), 0.01);
        assertEquals(77.0, baseConverter.convertToFahrenheit(), 0.01);
    }

    @Test
    public void testFahrenheitConverter() {
        // Arrange
        double celsiusTemperature = 25.0;
        Locale usLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        BaseConverter usConverter = BaseConverter.createWithLocale(celsiusTemperature, usLocale);

        // Act & Assert
        assertEquals(298.15, usConverter.convertToKelvin(), 0.01);
        assertEquals(77.0, usConverter.convertToFahrenheit(), 0.01);
    }
}
