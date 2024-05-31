package nz.ac.auckland.se281;

import java.util.*;

/** This class is the main entry point. */
public class MapEngine {

  Map<String, Country> countryMap = new HashMap<>();
  Map<String, List<String>> adjacencyMap = new HashMap<>();

  // CountryGraph fullCountryMap = new CountryGraph();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    // add code here to create your data structures

    // Data structure for countries
    for (String country : countries) {
      String[] countryInfo = country.split(",");
      String countryName = countryInfo[0];
      String continent = countryInfo[1];
      int countryCrossBorderTax = Integer.parseInt(countryInfo[2]);

      Country newCountry = new Country(countryName, continent, countryCrossBorderTax);

      countryMap.put(countryName, newCountry);
    }

    // Data structure for adjacencies
    for (String adjacency : adjacencies) {
      List<String> adjacentCountries = new LinkedList<>();

      String[] adjacencyInfo = adjacency.split(",");
      String countryName = adjacencyInfo[0];

      for (int i = 1; i < adjacencyInfo.length; i++) {
        adjacentCountries.add(adjacencyInfo[i]);
      }

      adjacencyMap.put(countryName, adjacentCountries);
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    // Ask for country
    MessageCli.INSERT_COUNTRY.printMessage();

    // Store country
    Country validCountry = askCountry();

    // Display country information
    MessageCli.COUNTRY_INFO.printMessage(
        validCountry.getCountryName(),
        validCountry.getCountryContinent(),
        String.valueOf(validCountry.getCountryCrossBorderTax()));
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {

    // Ask for start country
    MessageCli.INSERT_SOURCE.printMessage();
    Country sourceCountry = askCountry();

    // Ask for end country
    MessageCli.INSERT_DESTINATION.printMessage();
    Country destinationCountry = askCountry();

    // List<String> sourceCountryAdjacencies = adjacencyMap.get(sourceCountry.getCountryName());

    List<String> initialPath =
        findShortestPath(sourceCountry.getCountryName(), destinationCountry.getCountryName());

    MessageCli.ROUTE_INFO.printMessage(initialPath.toString());
    MessageCli.CONTINENT_INFO.printMessage(continentPath(initialPath).toString());
  }

  /** this method is invoked when the user has to input a country name. */
  public Country askCountry() {
    String input;
    boolean isValid = false;
    do {
      input = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
      try {
        isValidInput(input);
        isValid = true;
      } catch (IncorrectCountryNameException e) {
        MessageCli.INVALID_COUNTRY.printMessage(input);
      }
    } while (!isValid);

    return countryMap.get(input); // change to adjacency map to get adjacent countries
  }

  /** this method is invoked when the user has to input a country name. */
  public List<String> getAdjacencies() {

    //
    String input;
    boolean isValid = false;
    do {
      input = Utils.capitalizeFirstLetterOfEachWord(Utils.scanner.nextLine());
      try {
        isValidInput(input);
        isValid = true;
      } catch (IncorrectCountryNameException e) {
        MessageCli.INVALID_COUNTRY.printMessage(input);
      }
    } while (!isValid);

    return adjacencyMap.get(input);
  }

  /** this method is invoked when looking to validate the user's input for a country name. */
  public String isValidInput(String input) throws IncorrectCountryNameException {
    if (!countryMap.containsKey(input)) {
      throw new IncorrectCountryNameException("Invalid country name.");
    } else {
      return input;
    }
  }

  /** this method is invoked to find the path between two countries */
  public List<String> findShortestPath(String sourceCountry, String destinationCountry) {

    // Initialise visited and queue
    List<String> visited = new ArrayList<>();
    Queue<String> queue = new LinkedList<>();
    Map<String, String> parent = new HashMap<>();

    // Add source country to queue and visited
    queue.add(sourceCountry);
    visited.add(sourceCountry);
    parent.put(sourceCountry, null);

    while (!queue.isEmpty()) {

      // Remove first element from queue and store as country
      String country = queue.poll();

      if (country.equals(destinationCountry)) {
        List<String> route = new ArrayList<>();
        while (country != null) {
          route.add(country);
          country = parent.get(country);
        }
        Collections.reverse(route);
        return route;
      }

      for (String c : adjacencyMap.get(country)) {
        if (!parent.containsKey(c)) {

          // For every adjacent country in the adjacency list
          // Add to visited and queue if not in visited
          visited.add(c);
          queue.add(c);
          parent.put(c, country);
        }
      }
    }
    return null;
  }

  // public int totalCrossBorderTax(List<String> path) {
  //   int totalTax = 0;
  //   for (String country : path) {
  //     totalTax += countryMap.get(country).getCountryCrossBorderTax();
  //   }
  //   return totalTax;
  // }

  public List<String> continentPath(List<String> path) {
    List<String> continentPath = new ArrayList<>();
    for (String country : path) {
      continentPath.add(countryMap.get(country).getCountryContinent());
    }
    return continentPath;
  }
}
