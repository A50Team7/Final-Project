package com.testframework.models.enums;

import com.testframework.generations.GenerateRandom;
import lombok.Getter;

/**
 * Location model representing the possible locations/cities in the application.
 */
@Getter
public enum Location {

    SOFIA("Sofia", 1),
    PLOVDIV("Plovdiv", 2),
    VARNA("Varna", 3),
    BURGAS("Burgas", 4),
    RUSE("Ruse", 5),
    STARA_ZAGORA("Stara Zagora", 6),
    PLEVEN("Pleven", 7),
    SLIVEN("Sliven", 8),
    DOBRICH("Dobrich", 9),
    SHUMEN("Shumen", 10),
    PERNIK("Pernik", 11),
    HASKOVO("Haskovo", 12),
    VRATSA("Vratsa", 13),
    KYUSTENDIL("Kyustendil", 14),
    MONTANA("Montana", 15),
    LOVECH("Lovech", 16),
    RAZGRAD("Razgrad", 17),
    BORINO("Borino", 18),
    MADAN("Madan", 19),
    ZLATOGRAD("Zlatograd", 20),
    PAZARDZHIK("Pazardzhik", 21),
    SMOLYAN("Smolyan", 22),
    BLAGOEVGRAD("Blagoevgrad", 23),
    NEDELINO("Nedelino", 24),
    RUDOZEM("Rudozem", 25),
    DEVIN("Devin", 26),
    VELIKO_TARNOVO("Veliko Tarnovo", 27),
    VIDIN("Vidin", 28),
    KIRKOVO("Kirkovo", 29),
    KRUMOVGRAD("Krumovgrad", 30),
    DZHEBEL("Dzhebel", 31),
    SILISTRA("Silistra", 32),
    SARNITSA("Sarnitsa", 33),
    SHIROKA_LAKA("Shiroka Laka", 34),
    YAMBOL("Yambol", 35),
    DOSPAT("Dospat", 36),
    KARDZHALI("Kardzhali", 37),
    GABROVO("Gabrovo", 38),
    TARGOVISHTE("Targovishte", 39);

    private final String stringValue;
    private final int id;

    Location(String stringValue, int id) {
        this.stringValue = stringValue;
        this.id = id;
    }

    /**
     * Returns the Location corresponding to the provided ID.
     *
     * @param id the ID of the location
     * @return the Location corresponding to the provided ID
     * @throws IllegalArgumentException if no such location is found
     */
    public static Location getLocationById(int id) {
        for (Location location : Location.values()) {
            if (location.getId() == id) return location;
        }
        throw new IllegalArgumentException("No such location found.");
    }

    /**
     * Generates and returns a random ID within the range of location IDs.
     *
     * @return a random ID within the range of location IDs
     */
    public static int selectRandomId() {
        int lowerBound = 1;
        int upperBound = Location.getMaxId();
        return GenerateRandom.generateRandomBoundedInteger(lowerBound, upperBound);
    }

    /**
     * Returns the maximum ID of the locations.
     *
     * @return the maximum ID of the locations
     */
    private static int getMaxId() {
        Location[] locations = Location.values();
        return locations[locations.length - 1].id;
    }

}
