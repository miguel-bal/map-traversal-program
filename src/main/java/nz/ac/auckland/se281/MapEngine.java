package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** This class is the main entry point. */
public class MapEngine {

  Map<String, Country> countryMap = new HashMap<>();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // // add code here to create your data structures

    // // Data structure for countries
    for (String country : countries) {

      String[] countryInfo = country.split(",");
      String countryName = countryInfo[0];
      String continent = countryInfo[1];
      int countryCrossBorderTax = Integer.parseInt(countryInfo[2]);

      Country newCountry = new Country(countryName, continent, countryCrossBorderTax);

      countryMap.put(countryName, newCountry);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    MessageCli.INSERT_COUNTRY.printMessage();
    String input = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());

    Country validCountry = countryMap.get(input);
    MessageCli.COUNTRY_INFO.printMessage(
        validCountry.getCountryName(),
        validCountry.getCountryContinent(),
        String.valueOf(validCountry.getCountryCrossBorderTax()));
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
