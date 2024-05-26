package nz.ac.auckland.se281;

public class Country {
  public String countryName;
  public String countryContinent;
  public int countryCrossBorderTax;

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
