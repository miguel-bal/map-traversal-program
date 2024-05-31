package nz.ac.auckland.se281;

/** Exception to detect when the inputted country name does not exist. */
public class IncorrectCountryNameException extends RuntimeException {

  public IncorrectCountryNameException(String countryName) {
    super(countryName);
  }
}
