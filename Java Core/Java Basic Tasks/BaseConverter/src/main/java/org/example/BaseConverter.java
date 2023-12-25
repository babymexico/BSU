package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class BaseConverter {
    private final double celsius;

    public BaseConverter() {
        this.celsius = 0.0;
    }

    public BaseConverter(double celsius) {
        this.celsius = celsius;
    }

    public double convertToKelvin() {
        return celsius + 273.15;
    }

    public double convertToFahrenheit() {
        return (celsius * 9 / 5) + 32;
    }

    public static BaseConverter createWithLocale(double celsius, Locale locale) {
        String country = locale.getCountry();
        if (isFahrenheitCountry(country)) {
            return new FahrenheitConverter(celsius);
        }

        return new BaseConverter(celsius);
    }

    private static boolean isFahrenheitCountry(String country) {
        List<String> fahrenheitCountries = Arrays.asList("BS", "US", "BZ", "KY", "PW");
        return fahrenheitCountries.contains(country);
    }

    private static class FahrenheitConverter extends BaseConverter {
        public FahrenheitConverter(double celsius) {
            super(celsius);
        }

        @Override
        public double convertToFahrenheit() {
            return super.convertToFahrenheit();
        }
    }
}
