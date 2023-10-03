package com.testframework.models.enums;

import com.testframework.generations.GenerateRandom;

public enum ProfessionalCategory {
    ALL("All", 0),
    ACTOR("Actor", 1),
    ARCHAEOLOGIST("Archaeologist", 2),
    AUTHOR("Author", 3),
    BAKER("Baker", 4),
    BUS_DRIVER("Bus driver", 5),
    CAR_SERVICE("Car service", 6),
    CHEF("Chef/Cook", 7),
    CLEANER("Cleaner", 8),
    DENTIST("Dentist", 9),
    DESIGNER("Designer", 10),
    DOCTOR("Doctor", 11),
    ELECTRICIAN("Electrician", 12),
    FACTORY_WORKER("Factory worker", 13),
    FARMER("Farmer", 14),
    FIREFIGHTER("Firefighter", 15),
    FISHERMAN("Fisherman", 16),
    GARDENER("Gardener", 17),
    HAIRDRESSER("Hairdresser", 18),
    JOURNALIST("Journalist", 19),
    JUDGE("Judge", 20),
    LAWYER("Lawyer", 21),
    LECTURER("Lecturer", 22),
    LIFEGUARD("Lifeguard", 23),
    MECHANIC("Mechanic", 24),
    MODEL("Model", 25),
    MUSICIAN("Musician", 26),
    NANNY("Nanny", 27),
    NEWSREADER("Newsreader", 28),
    NURSES("Nurses", 29),
    OPTICIAN("Optician", 30),
    PAINTER("Painter", 31),
    PHARMACIST("Pharmacist", 32),
    PHOTOGRAPHER("Photographer", 33),
    PILOT("Pilot", 34),
    POLICE_OFFICER("Police Officer", 35),
    POLICEMAN("Policeman", 36),
    POLITICIAN("Politician", 37),
    POSTMAN("Postman", 38),
    REAL_ESTATE_AGENT("Real estate agent", 39),
    RECEPTIONIST("Receptionist", 40),
    SCIENTIST("Scientist", 41),
    SECRETARY("Secretary", 42),
    SHOP_ASSISTANT("Shop assistant", 43),
    SOFTWARE_DEVELOPER("Software developer", 44),
    SOLDIER("Soldier", 45),
    TAILOR("Tailor", 46),
    TEACHERS("Teachers", 47),
    TRANSLATOR("Translator", 48),
    TRAVEL_AGENT("Travel agent", 49),
    VETERINARIAN("Veterinarian", 50),
    WAITER_WAITRESS("Waiter/Waitress", 51),
    WINDOW_CLEANER("Window cleaner", 52),
    WRITER("Writer", 53),
    MARKETING("Marketing", 54),
    ACCOUNTANT("Accountant", 55);

    private final String stringValue;
    private final int id;

    ProfessionalCategory(String stringValue, int id) {
        this.stringValue = stringValue;
        this.id = id;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getId() {
        return id;
    }

    public static ProfessionalCategory getProfessionalCategoryById(int id) {
        for (ProfessionalCategory category : ProfessionalCategory.values()) {
            if (category.getId()==id) return category;
        }
        throw new IllegalArgumentException("No such professional category found.");
    }

    public static int selectRandomId() {
        int lowerBound = 1;
        int upperBound = ProfessionalCategory.getMaxId();
        return GenerateRandom.generateRandomBoundedInteger(lowerBound, upperBound);
    }

    private static int getMaxId() {
        ProfessionalCategory[] categories = ProfessionalCategory.values();
        return categories[categories.length - 1].id;
    }
}
