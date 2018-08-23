package test.rest.demo.entity;

import java.util.Arrays;

public enum PersonStatus {
    UNDEFINED(0),
    BABY(10),
    TEENAGER(20),
    YOUNG(30),
    ADULT(60),
    RETIRED(100),
    DUNCAN_MAC_LEOD(999);

    private final int maxAge;

    PersonStatus(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    /**
     * Determine PersonStatus by age
     * @param age person's age
     * @return PersonStatus depending on age
     */
    public static PersonStatus determine(int age) {
        if (age < 0 || age > 999) throw new IllegalArgumentException("Wrong input: " + age);
        return Arrays.stream(PersonStatus.values())
                .filter(personStatus -> age <= personStatus.maxAge).findFirst()
                .orElse(PersonStatus.UNDEFINED);
    }
}
