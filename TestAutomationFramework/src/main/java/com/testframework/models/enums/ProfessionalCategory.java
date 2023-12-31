package com.testframework.models.enums;

import com.testframework.generations.GenerateRandom;
import lombok.Getter;

/**
 * Professional Category representing the possible categories in the application.
 */
@Getter
public enum ProfessionalCategory {
    ALL("All", 100),
    ACCOUNTANT("Accountant", 101),
    ACTOR("Actor", 102),
    ARCHAEOLOGIST("Archaeologist", 103),
    ASTRONAUT("Astronaut", 104),
    AUTHOR("Author", 105),
    BAKER("Baker", 106),
    BUS_DRIVER("Bus driver", 107),
    CAR_SERVICE("Car service", 108),
    CHEF("Chef/Cook", 109),
    CLEANER("Cleaner", 110),
    DENTIST("Dentist", 111),
    DESIGNER("Designer", 112),
    DOCTOR("Doctor", 113),
    ELECTRICIAN("Electrician", 114),
    FACTORY_WORKER("Factory worker", 115),
    FARMER("Farmer", 116),
    FIREFIGHTER("Firefighter", 117),
    FISHERMAN("Fisherman", 118),
    GARDENER("Gardener", 119),
    HAIRDRESSER("Hairdresser", 120),
    JOURNALIST("Journalist", 121),
    JUDGE("Judge", 122),
    LAWYER("Lawyer", 123),
    LECTURER("Lecturer", 124),
    LIFEGUARD("Lifeguard", 125),
    MECHANIC("Mechanic", 126),
    MODEL("Model", 127),
    MUSICIAN("Musician", 128),
    NANNY("Nanny", 129),
    NEWSREADER("Newsreader", 130),
    NURSES("Nurses", 131),
    OPTICIAN("Optician", 132),
    PAINTER("Painter", 133),
    PHARMACIST("Pharmacist", 134),
    PHOTOGRAPHER("Photographer", 135),
    PILOT("Pilot", 136),
    PLUMBER("Plumber", 137),
    POLICE_OFFICER("Police Officer", 138),
    POLICEMAN("Policeman", 139),
    POLITICIAN("Politician", 140),
    POSTMAN("Postman", 141),
    REAL_ESTATE_AGENT("Real estate agent", 142),
    RECEPTIONIST("Receptionist", 143),
    SCIENTIST("Scientist", 144),
    SECRETARY("Secretary", 145),
    SHOP_ASSISTANT("Shop assistant", 146),
    SOFTWARE_DEVELOPER("Software developer", 147),
    SOLDIER("Soldier", 148),
    TAILOR("Tailor", 149),
    TEACHERS("Teachers", 150),
    TRANSLATOR("Translator", 151),
    TRAVEL_AGENT("Travel agent", 152),
    VETERINARIAN("Veterinarian", 153),
    WAITER_WAITRESS("Waiter/Waitress", 154),
    WINDOW_CLEANER("Window cleaner", 155),
    WRITER("Writer", 156),
    MARKETING("Marketing", 157);
    private final String stringValue;
    private final int id;

    ProfessionalCategory(String stringValue, int id) {
        this.stringValue = stringValue;
        this.id = id;
    }

    /**
     * Returns the ProfessionalCategory corresponding to the provided ID.
     *
     * @param id the ID of the professional category
     * @return the ProfessionalCategory corresponding to the provided ID
     * @throws IllegalArgumentException if no such professional category is found
     */
    public static ProfessionalCategory getProfessionalCategoryById(int id) {
        for (ProfessionalCategory category : ProfessionalCategory.values()) {
            if (category.getId() == id) return category;
        }
        throw new IllegalArgumentException("No such professional category found.");
    }

    /**
     * Returns the ProfessionalCategory corresponding to the provided category name.
     *
     * @param categoryName the professional category in String format
     * @return the ProfessionalCategory corresponding to the provided category name
     * @throws IllegalArgumentException if no such professional category is found
     */
    public static ProfessionalCategory getProfessionalCategoryByString(String categoryName) {
        for (ProfessionalCategory category : ProfessionalCategory.values()) {
            if (category.getStringValue().equalsIgnoreCase(categoryName)) return category;
        }
        throw new IllegalArgumentException("No such professional category found.");
    }

    /**
     * @return a random ID within the range of professional category IDs
     */
    public static int selectRandomId() {
        int lowerBound = 100;
        int upperBound = ProfessionalCategory.getMaxId();
        return GenerateRandom.generateRandomBoundedInteger(lowerBound, upperBound);
    }

    /**
     * @return the maximum ID of the professional categories
     */
    private static int getMaxId() {
        ProfessionalCategory[] categories = ProfessionalCategory.values();
        return categories[categories.length - 1].id;
    }
}
