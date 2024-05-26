package nz.ac.auckland.se281;

public class IncorrectCountryNameException extends RuntimeException {

  public IncorrectCountryNameException(String countryName) {
    super(countryName);
  }
}
