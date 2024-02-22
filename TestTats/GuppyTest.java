import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(Guppy.DEFAULT_GENUS, "Poecilia");
    }

    @Test
    public void containsConstantCalledDEFAULT_SPECIES() {
        assertEquals(Guppy.DEFAULT_SPECIES, "reticulata");
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
        final int numberAlreadyCreated = Guppy.getNumberOfFishBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            new Guppy();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Guppy.getNumberOfFishBorn());
    }

    @Test
    public void staticGuppyCounterIsCorrectlyTrackingNewbornGuppiesInMultiParamConstructor() {
        final int numberAlreadyCreated = Guppy.getNumberOfFishBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Guppy("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
        assertEquals(numberAlreadyCreated + numberCreated, Guppy.getNumberOfFishBorn());
    }

    @Test
    public void zeroParamGuppyIsSetToCorrectValues() {
        assertEquals("Poecilia", zeroParamGuppy.getGenus());
        assertEquals("reticulata", zeroParamGuppy.getSpecies());
        assertEquals(0, zeroParamGuppy.getAgeInWeeks());
        assertTrue(zeroParamGuppy.getIsFemale());
        assertEquals(0, zeroParamGuppy.getGenerationNumber());
        assertTrue(zeroParamGuppy.getIsAlive());
        assertEquals(0.5, zeroParamGuppy.getHealthCoefficient(), 0.0);
    }

    @Test
    public void multiParamGuppyIsSetToCorrectValues() {
        assertEquals("Poecilia", testGuppy.getGenus());
        assertEquals("reticulata", testGuppy.getSpecies());
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
        assertEquals("Poecilia", fry.getGenus());
        assertEquals("reticulata", fry.getSpecies());
    }

    @Test
    public void nullGenusThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Guppy(null, "a", 0, true, 0 , 0.5));
    }

    @Test
    public void emptyGenusThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Guppy("    ", "a", 0, true, 0 , 0.5));
    }

    @Test
    public void nullSpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Guppy("a", null, 0, true, 0 , 0.5));
    }

    @Test
    public void emptySpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Guppy("a", "    ", 0, true, 0 , 0.5));
    }

    @Test
    public void negativeAgeInWeeksBecomesZero() {
        Guppy fry = new Guppy("a",
                "b",
                -1,
                true,
                0 ,
                0.5);
        assertEquals(0, fry.getAgeInWeeks());
    }

    @Test
    public void zeroAgeInWeeksRemainsZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0 ,
                0.5);
        assertEquals(0, fry.getAgeInWeeks());
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
        assertEquals(Guppy.MAXIMUM_AGE_IN_WEEKS, fry.getAgeInWeeks());
    }

    @Test
    public void negativeGenerationNumberSetToZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                -1,
                0.5);
        assertEquals(0, fry.getGenerationNumber());
    }

    @Test
    public void zeroGenerationNumberSetToZero() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true, 0,
                0.5);
        assertEquals(0, fry.getGenerationNumber());
    }

    @Test
    public void createGuppyWithNegativeHealthCoefficient() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertEquals(Guppy.MINIMUM_HEALTH_COEFFICIENT, fry.getHealthCoefficient());
    }

    @Test
    public void createGuppyWithOverlargeHealthCoefficient() {
        Guppy fry = new Guppy("a",
                "b",
                0,
                true,
                0,
                Guppy.MAXIMUM_HEALTH_COEFFICIENT + 1.0);
        assertEquals(Guppy.MAXIMUM_HEALTH_COEFFICIENT, fry.getHealthCoefficient());

    }

    @Test
    public void testIncrementAge() {
        int oldAge = testGuppy.getAgeInWeeks();
        testGuppy.incrementAge();
        int newAge = testGuppy.getAgeInWeeks();
        assertEquals(newAge, (oldAge + 1));
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
        final int numberAlreadyCreated = Guppy.getNumberOfFishBorn();
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
    public void guppyWithNullGenusAndSpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Guppy(null, null, 0, true, 0, Guppy.MINIMUM_HEALTH_COEFFICIENT - 1.0));
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
}
