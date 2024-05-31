package nz.ac.auckland.se281;

/** Represents a country with its name, continent, and cross-border tax. */
public class Country {
  private String countryName;
  private String countryContinent;
  private int countryCrossBorderTax;

  /**
   * Constructs a new country instance with the country name, country continent, and country
   * cross-border tax.
   *
   * @param countryName the name of the country
   * @param countryContinent the continent of the country
   * @param countryCrossBorderTax the cross-border tax of the country
   */
  public Country(String countryName, String countryContinent, int countryCrossBorderTax) {
    this.countryName = countryName;
    this.countryContinent = countryContinent;
    this.countryCrossBorderTax = countryCrossBorderTax;
  }

  // Getter functions
  public String getCountryName() {
    return countryName;
  }

  public String getCountryContinent() {
    return countryContinent;
  }

  public int getCountryCrossBorderTax() {
    return countryCrossBorderTax;
  }
}
