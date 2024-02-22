

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Here are some unit tests for your Guppy. You must pass all of these.
 * Passing all of these tests does not mean your Guppy is perfect, though.
 * How are your comments?
 * Are visibilities correct?
 * Is mutability locked down?
 * Is scope minimized?
 * Is your style correct? Are elements in the class in the correct order?
 * Are instance variables initialized in the same order as they are declared?
 */
class GuppyTest {

    private Guppy zeroParamGuppy;
    private Guppy testGuppy;

    @BeforeEach
    public void setUp() {

        zeroParamGuppy = new Guppy();
        testGuppy = new Guppy(  "Poecilia",
                "reticulata",
                1,
                true,
                3,
                0.75);
    }

    @Test
    public void containsConstantCalledYOUNG_FISH_AGE_IN_WEEKS() {
        assertEquals(10, Guppy.YOUNG_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMATURE_FISH_AGE_IN_WEEKS() {
        assertEquals(30, Guppy.MATURE_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMAXIMUM_AGE_IN_WEEKS() {
        assertEquals(50, Guppy.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMINIMUM_WATER_VOLUME_ML() {
        assertEquals(250.0, Guppy.MINIMUM_WATER_VOLUME_ML, 0.0);
    }

    @Test
    public void containsConstantCalledDEFAULT_GENUS() {
        assertTrue(Guppy.DEFAULT_GENUS.equals("Poecilia"));
    }

    @Test
    public void containsConstantCalledDEFAULT_SPECIES() {
        assertTrue(Guppy.DEFAULT_SPECIES.equals("reticulata"));
    }

    @Test
    public void containsConstantCalledDEFAULT_HEALTH_COEFFICIENT() {
        assertEquals(0.5, Guppy.DEFAULT_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMINIMUM_HEALTH_COEFFICIENT() {
        assertEquals(0.0, Guppy.MINIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMAXIMUM_HEALTH_COEFFICIENT() {
        assertEquals(1.0, Guppy.MAXIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void staticGuppyCounterIsCorrectlyTrackingNewbornGuppiesInZeroParamConstructor() {
        final int numberAlreadyCreated = Guppy.getNumberOfGuppiesBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Guppy someGuppy = new Guppy();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Guppy.getNumberOfGuppiesBorn());
    }

    @Test
    public void staticGuppyCounterIsCorrectlyTrackingNewbornGuppiesInMultiParamConstructor() {
        final int numberAlreadyCreated = Guppy.getNumberOfGuppiesBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Guppy("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
        assertEquals(numberAlreadyCreated + numberCreated, Guppy.getNumberOfGuppiesBorn());
    }

    @Test
    public void zeroParamGuppyIsSetToCorrectValues() {
        assertTrue(zeroParamGuppy.getGenus().equals("Poecilia"));
        assertTrue(zeroParamGuppy.getSpecies().equals("reticulata"));
        assertEquals(0, zeroParamGuppy.getAgeInWeeks());
        assertTrue(zeroParamGuppy.getIsFemale());
        assertEquals(0, zeroParamGuppy.getGenerationNumber());
        assertTrue(zeroParamGuppy.getIsAlive());
        assertEquals(0.5, zeroParamGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void multiParamGuppyIsSetToCorrectValues() {
        assertTrue(testGuppy.getGenus().equals("Poecilia"));
        assertTrue(testGuppy.getSpecies().equals("reticulata"));
        assertEquals(1, testGuppy.getAgeInWeeks());
        assertTrue(testGuppy.getIsFemale());
        assertEquals(3, testGuppy.getGenerationNumber());
        assertTrue(testGuppy.getIsAlive());
        assertEquals(0.75, testGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInZeroParamConstruction() {
        Guppy first = new Guppy();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Guppy fry = new Guppy();
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Guppy first = new Guppy();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Guppy fry = new Guppy("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void genusAndSpeciesAreCorrectlyFormattedAndStored() {
        Guppy fry = new Guppy("  poECILIA    ",
                "  retICUlata   ",
                1,
                true,
                3,
                0.75);
        assertTrue(fry.getGenus().equals("Poecilia"));
        assertTrue(fry.getSpecies().equals("reticulata"));
    }

    @Test
    public void nullGenusReplacedWithDefaultGenus() {
        Guppy fry = new Guppy(null,
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Poecilia"));
    }

    @Test
    public void emptyGenusReplacedWithDefaultGenus() {
        Guppy fry = new Guppy("    ",
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Poecilia"));

    }

    @Test
    public void nullSpeciesReplacedWithDefaultSpecies() {
        Guppy fry = new Guppy("a",
                null,
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("reticulata"));

    }

    @Test
    public void emptySpeciesReplacedWithDefaultSpecies() {
        Guppy fry = new Guppy("a",
                "    ",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("reticulata"));

    }

    @Test
    public void negativeAgeInWeeksBecomesZero() {
            Guppy fry = new Guppy("a",
                "b",
                -1,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void zeroAgeInWeeksRemainsZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void maximumAgeInWeeksIsAlive() {
        Guppy fry = new Guppy("a",
                "b",
                Guppy.MAXIMUM_AGE_IN_WEEKS,
                true,
                0 ,
                0.5);
        assertTrue(fry.getIsAlive());
    }

    @Test
    public void overlargeAgeInWeeksIsReducedToMax() {
        Guppy fry = new Guppy("a",
                "b",
                (Guppy.MAXIMUM_AGE_IN_WEEKS + 100),
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == Guppy.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void negativeGenerationNumberSetToZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                -1,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void zeroGenerationNumberSetToZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true, 0,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void createGuppyWithNegativeHealthCoefficient() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertTrue(fry.getHealthCoefficient() == Guppy.MINIMUM_HEALTH_COEFFICIENT);
    }

    @Test
    public void createGuppyWithOverlargeHealthCoefficient() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.MAXIMUM_HEALTH_COEFFICIENT + 1.0);
        assertTrue(fry.getHealthCoefficient() == Guppy.MAXIMUM_HEALTH_COEFFICIENT);

    }

    @Test
    public void testIncrementAge() {
        int oldAge = testGuppy.getAgeInWeeks();
        testGuppy.incrementAge();
        int newAge = testGuppy.getAgeInWeeks();
        assertTrue(newAge == (oldAge + 1));
    }

    @Test
    public void testIncrementAgeKillsGuppyWhenMaxAgeIsReached() {
        testGuppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
        assertTrue(testGuppy.getIsAlive());
        testGuppy.incrementAge();
        assertFalse(testGuppy.getIsAlive());
    }

    @Test
    public void ageMutatorIgnoresInvalidArguments() {
        int age = zeroParamGuppy.getAgeInWeeks();
        zeroParamGuppy.setAgeInWeeks(-1);
        assertEquals(age, zeroParamGuppy.getAgeInWeeks());

        zeroParamGuppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS + 1);
        assertEquals(age, zeroParamGuppy.getAgeInWeeks());
    }

    @Test
    public void ageMutatorAcceptsValidArguments() {
        testGuppy.setAgeInWeeks(0);
        assertEquals(0, testGuppy.getAgeInWeeks());

        testGuppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
        assertEquals(Guppy.MAXIMUM_AGE_IN_WEEKS, testGuppy.getAgeInWeeks());

        testGuppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS - 10);
        assertEquals(Guppy.MAXIMUM_AGE_IN_WEEKS - 10, testGuppy.getAgeInWeeks());
    }

    @Test
    public void healthCoefficientMutatorIgnoresInvalidArguments() {
        double healthCoefficient = zeroParamGuppy.getHealthCoefficient();
        zeroParamGuppy.setHealthCoefficient(-0.01);
        assertEquals(healthCoefficient, zeroParamGuppy.getHealthCoefficient(), 0.0);

        zeroParamGuppy.setHealthCoefficient(1.01);
        assertEquals(healthCoefficient, zeroParamGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void healthCoefficientMutatorAcceptsValidArguments() {

        testGuppy.setHealthCoefficient(1.0);
        assertEquals(1.0, testGuppy.getHealthCoefficient(), 0.0);

        testGuppy.setHealthCoefficient(0.5);
        assertEquals(0.5, testGuppy.getHealthCoefficient(), 0.0);

        testGuppy.setHealthCoefficient(0.0);
        assertEquals(0.0, testGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void babyFishNeedMinimalVolumeOfWater() {
        Guppy fry = new Guppy();
        for (int i = 0; i < Guppy.YOUNG_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            assertEquals(Guppy.MINIMUM_WATER_VOLUME_ML, fry.getVolumeNeeded(), 0.0);
        }
    }

    @Test
    public void youngFishNeedCorrectVolumeOfWater() {
        Guppy fry = new Guppy();
        for (int i = Guppy.YOUNG_FISH_AGE_IN_WEEKS; i <= Guppy.MATURE_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Guppy.MINIMUM_WATER_VOLUME_ML * fry.getAgeInWeeks() / Guppy.YOUNG_FISH_AGE_IN_WEEKS;
            assertEquals(volumeNeeded, fry.getVolumeNeeded(), 0.001);
        }
    }

    @Test
    public void matureFishNeedCorrectVolumeOfWater() {
        Guppy fry = new Guppy();
        for (int i = Guppy.MATURE_FISH_AGE_IN_WEEKS + 1; i <= Guppy.MAXIMUM_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Guppy.MINIMUM_WATER_VOLUME_ML * 1.5;
            assertEquals(volumeNeeded, fry.getVolumeNeeded(), 0.001);
        }
    }

    @Test
    public void deadFishNeedNoWater() {
        Guppy fry = new Guppy();
        fry.setAgeInWeeks(50);
        fry.incrementAge();
        assertEquals(0.0, fry.getVolumeNeeded(), 0.0);
    }

    @Test
    public void changeHealthCoefficientWillNotPermitOverlargeHealthCoefficients() {
        testGuppy.changeHealthCoefficient(1.5);
        assertEquals(1.0, testGuppy.getHealthCoefficient(), 0.0);
    }


    @Test
    public void changeHealthCoefficientWillNotPermitNegativeHealthCoefficients() {
        testGuppy.changeHealthCoefficient(-1.5);
        assertEquals(0.0, testGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void changeHealthCoefficientToZeroOrLowerKillsTheGuppy() {
        testGuppy.changeHealthCoefficient(-1.5);
        assertFalse(testGuppy.getIsAlive());
    }

    @Test
    void testSetIsAliveToFalse() {
        testGuppy.setIsAlive(false);
        final boolean actual = testGuppy.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetIsAliveNoChangeFromDead() {
        testGuppy.setIsAlive(false);
        testGuppy.setIsAlive(true);
        final boolean actual = testGuppy.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetAgeInWeeksNoChangeIfGuppyDead() {
        final int expected = testGuppy.getAgeInWeeks();
        testGuppy.setIsAlive(false);
        testGuppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
        final int actual = testGuppy.getAgeInWeeks();
        assertEquals(expected, actual);
    }

    @Test
    void testChangeHealthCoefficientToZeroResultsGuppyDeath() {
        final double delta = -1.0;
        testGuppy.changeHealthCoefficient(delta);
        assertFalse(testGuppy.getIsAlive());
    }

    @Test
    void testToString() {
        Guppy defaultGuppy = new Guppy();
        final int numberAlreadyCreated = Guppy.getNumberOfGuppiesBorn();
        final String expected = "Guppy{genus='Poecilia', species='reticulata', ageInWeeks=0, " +
                "isFemale=true, generationNumber=0, isAlive=true, healthCoefficient=0.5, " +
                "identificationNumber=" + numberAlreadyCreated + "}";
        final String actual = defaultGuppy.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void createGuppyWithZeroHealthCoefficientIsDead() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertFalse(fry.getIsAlive());
    }

    @Test
    public void guppyWithNullGenusAndSpecies() {
        Guppy fry = new Guppy(null,
                null,
                0,
                true,
                0,
                Guppy.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        String actual = fry.getGenus();
        String expected = Guppy.DEFAULT_GENUS;
        assertEquals(expected, actual);
    }

    @Test
    public void containsConstantCalledFIRST_GENERATION() {
        assertEquals(0, Guppy.FIRST_GENERATION);
    }

    @Test
    public void changeHealthCoefficientWillPermitPositiveHealthCoefficients() {
        testGuppy.changeHealthCoefficient(0.05);
        assertEquals(0.8, testGuppy.getHealthCoefficient(), 0.001);
    }

    @Test
    public void changeHealthCoefficientWillPermitNegativeHealthCoefficients() {
        testGuppy.changeHealthCoefficient(-0.05);
        assertEquals(0.7, testGuppy.getHealthCoefficient(), 0.001);
    }

    @Test
    public void spawnIsNotFemaleReturnsNull() {
        Guppy fry = new Guppy("a",
                "b",
                10,
                false,
                0,
                Guppy.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }

    @Test
    public void spawnYoungerThanMinimumAgeReturnsNull() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }

    @Test
    public void spawnIsNotFemaleAndYoungerThanMinimumAgeReturnsNull() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                false,
                0,
                Guppy.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }

    @Test
    public void spawnConstructsFryInLegalRange() {
        testGuppy.setAgeInWeeks(Guppy.MATURE_FISH_AGE_IN_WEEKS);
        ArrayList<Fish> listOfFry;
        // verify that listOfFry has fry in it
        do {
            listOfFry = testGuppy.spawn();
        } while (listOfFry == null || listOfFry.isEmpty());
        assertTrue(listOfFry.size() <= 100);

        final double expectedHealthCoefficient = (1 + testGuppy.getHealthCoefficient()) / 2.0;
        final int expectedGenerationNumber = testGuppy.getGenerationNumber() + 1;
        // The only uncertain value is isFemale, everything else is determinable
        for (Fish fry : listOfFry) {
            assertEquals(testGuppy.getGenus(), fry.getGenus());
            assertEquals(testGuppy.getSpecies(), fry.getSpecies());
            assertEquals(0, fry.getAgeInWeeks());
            assertEquals(expectedGenerationNumber, fry.getGenerationNumber());
            assertEquals(expectedHealthCoefficient, fry.getHealthCoefficient());
            assertTrue(fry.getIsAlive());
        }
    }
}
