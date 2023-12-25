package org.example;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        double celsiusTemperature = 25.0;

        // Using the default locale
        Locale defaultLocale = Locale.getDefault();
        BaseConverter baseConverterDefault = BaseConverter.createWithLocale(celsiusTemperature, defaultLocale);
        printTemperatures("Default Locale", celsiusTemperature, baseConverterDefault);

        // Using the US locale
        Locale usLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        BaseConverter usConverter = BaseConverter.createWithLocale(celsiusTemperature, usLocale);
        printTemperatures("US Locale", celsiusTemperature, usConverter);
    }

    private static void printTemperatures(String localeName, double celsiusTemperature, BaseConverter converter) {
        System.out.println("\n" + localeName + ":");
        System.out.println("Temperature in Celsius: " + celsiusTemperature);
        System.out.println("Temperature in Kelvin: " + converter.convertToKelvin());
        System.out.println("Temperature in Fahrenheit: " + converter.convertToFahrenheit());
    }
}
