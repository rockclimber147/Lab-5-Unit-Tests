

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Here are some unit tests for your Swordtail. You must pass all of these.
 * Passing all of these tests does not mean your Swordtail is perfect, though.
 * How are your comments?
 * Are visibilities correct?
 * Is mutability locked down?
 * Is scope minimized?
 * Is your style correct? Are elements in the class in the correct order?
 * Are instance variables initialized in the same order as they are declared?
 */
class SwordtailTest {

    private Swordtail zeroParamSwordtail;
    private Swordtail testSwordtail;

    @BeforeEach
    public void setUp() {

        zeroParamSwordtail = new Swordtail();
        testSwordtail = new Swordtail(  "Xiphophorus",
                "hellerii",
                1,
                true,
                3,
                0.75);
    }

    @Test
    public void containsConstantCalledYOUNG_FISH_AGE_IN_WEEKS() {
        assertEquals(10, Swordtail.YOUNG_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMATURE_FISH_AGE_IN_WEEKS() {
        assertEquals(50, Swordtail.MATURE_FISH_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMAXIMUM_AGE_IN_WEEKS() {
        assertEquals(150, Swordtail.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void containsConstantCalledMINIMUM_WATER_VOLUME_ML() {
        assertEquals(1000.0, Swordtail.MINIMUM_WATER_VOLUME_ML, 0.0);
    }

    @Test
    public void containsConstantCalledDEFAULT_GENUS() {
        assertTrue(Swordtail.DEFAULT_GENUS.equals("Xiphophorus"));
    }

    @Test
    public void containsConstantCalledDEFAULT_SPECIES() {
        assertTrue(Swordtail.DEFAULT_SPECIES.equals("hellerii"));
    }

    @Test
    public void containsConstantCalledDEFAULT_HEALTH_COEFFICIENT() {
        assertEquals(0.6, Swordtail.DEFAULT_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMINIMUM_HEALTH_COEFFICIENT() {
        assertEquals(0.0, Swordtail.MINIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMAXIMUM_HEALTH_COEFFICIENT() {
        assertEquals(1.0, Swordtail.MAXIMUM_HEALTH_COEFFICIENT, 0.0);
    }

    @Test
    public void staticSwordtailCounterIsCorrectlyTrackingNewbornSwordtailsInZeroParamConstructor() {
        final int numberAlreadyCreated = Swordtail.getNumberOfSwordtailsBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Swordtail someSwordtail = new Swordtail();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Swordtail.getNumberOfSwordtailsBorn());
    }

    @Test
    public void staticSwordtailCounterIsCorrectlyTrackingNewbornSwordtailsInMultiParamConstructor() {
        final int numberAlreadyCreated = Swordtail.getNumberOfSwordtailsBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Swordtail("Xiphophorus",
                    "hellerii",
                    1,
                    true,
                    3,
                    0.75);
        assertEquals(numberAlreadyCreated + numberCreated, Swordtail.getNumberOfSwordtailsBorn());
    }

    @Test
    public void zeroParamSwordtailIsSetToCorrectValues() {
        assertTrue(zeroParamSwordtail.getGenus().equals("Xiphophorus"));
        assertTrue(zeroParamSwordtail.getSpecies().equals("hellerii"));
        assertEquals(0, zeroParamSwordtail.getAgeInWeeks());
        assertTrue(zeroParamSwordtail.getIsFemale());
        assertEquals(0, zeroParamSwordtail.getGenerationNumber());
        assertTrue(zeroParamSwordtail.getIsAlive());
        assertEquals(0.6, zeroParamSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void multiParamSwordtailIsSetToCorrectValues() {
        assertTrue(testSwordtail.getGenus().equals("Xiphophorus"));
        assertTrue(testSwordtail.getSpecies().equals("hellerii"));
        assertEquals(1, testSwordtail.getAgeInWeeks());
        assertTrue(testSwordtail.getIsFemale());
        assertEquals(3, testSwordtail.getGenerationNumber());
        assertTrue(testSwordtail.getIsAlive());
        assertEquals(0.75, testSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInZeroParamConstruction() {
        Swordtail first = new Swordtail();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Swordtail fry = new Swordtail();
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Swordtail first = new Swordtail();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Swordtail fry = new Swordtail("Xiphophorus",
                    "hellerii",
                    1,
                    true,
                    3,
                    0.75);
            assertEquals(firstID + i, fry.getIdentificationNumber());
        }
    }

    @Test
    public void genusAndSpeciesAreCorrectlyFormattedAndStored() {
        Swordtail fry = new Swordtail("  Xiphophorus    ",
                "  hellerii   ",
                1,
                true,
                3,
                0.75);
        assertTrue(fry.getGenus().equals("Xiphophorus"));
        assertTrue(fry.getSpecies().equals("hellerii"));
    }

    @Test
    public void nullGenusReplacedWithDefaultGenus() {
        Swordtail fry = new Swordtail(null,
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Xiphophorus"));
    }

    @Test
    public void emptyGenusReplacedWithDefaultGenus() {
        Swordtail fry = new Swordtail("    ",
                "a",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getGenus().equals("Xiphophorus"));

    }

    @Test
    public void nullSpeciesReplacedWithDefaultSpecies() {
        Swordtail fry = new Swordtail("a",
                null,
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("hellerii"));

    }

    @Test
    public void emptySpeciesReplacedWithDefaultSpecies() {
        Swordtail fry = new Swordtail("a",
                "    ",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getSpecies().equals("hellerii"));

    }

    @Test
    public void negativeAgeInWeeksBecomesZero() {
            Swordtail fry = new Swordtail("a",
                "b",
                -1,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void zeroAgeInWeeksRemainsZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == 0);
    }

    @Test
    public void maximumAgeInWeeksIsAlive() {
        Swordtail fry = new Swordtail("a",
                "b",
                Swordtail.MAXIMUM_AGE_IN_WEEKS,
                true,
                0 ,
                0.5);
        assertTrue(fry.getIsAlive());
    }

    @Test
    public void overlargeAgeInWeeksIsReducedToMax() {
        Swordtail fry = new Swordtail("a",
                "b",
                (Swordtail.MAXIMUM_AGE_IN_WEEKS + 100),
                true,
                0 ,
                0.5);
        assertTrue(fry.getAgeInWeeks() == Swordtail.MAXIMUM_AGE_IN_WEEKS);
    }

    @Test
    public void negativeGenerationNumberSetToZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                -1,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void zeroGenerationNumberSetToZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true, 0,
                0.5);
        assertTrue(fry.getGenerationNumber() == 0);
    }

    @Test
    public void createSwordtailWithNegativeHealthCoefficient() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertTrue(fry.getHealthCoefficient() == Swordtail.MINIMUM_HEALTH_COEFFICIENT);
    }

    @Test
    public void createSwordtailWithOverlargeHealthCoefficient() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.MAXIMUM_HEALTH_COEFFICIENT + 1.0);
        assertTrue(fry.getHealthCoefficient() == Swordtail.MAXIMUM_HEALTH_COEFFICIENT);

    }

    @Test
    public void testIncrementAge() {
        int oldAge = testSwordtail.getAgeInWeeks();
        testSwordtail.incrementAge();
        int newAge = testSwordtail.getAgeInWeeks();
        assertTrue(newAge == (oldAge + 1));
    }

    @Test
    public void testIncrementAgeKillsSwordtailWhenMaxAgeIsReached() {
        testSwordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS);
        assertTrue(testSwordtail.getIsAlive());
        testSwordtail.incrementAge();
        assertFalse(testSwordtail.getIsAlive());
    }

    @Test
    public void ageMutatorIgnoresInvalidArguments() {
        int age = zeroParamSwordtail.getAgeInWeeks();
        zeroParamSwordtail.setAgeInWeeks(-1);
        assertEquals(age, zeroParamSwordtail.getAgeInWeeks());

        zeroParamSwordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS + 1);
        assertEquals(age, zeroParamSwordtail.getAgeInWeeks());
    }

    @Test
    public void ageMutatorAcceptsValidArguments() {
        testSwordtail.setAgeInWeeks(0);
        assertEquals(0, testSwordtail.getAgeInWeeks());

        testSwordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS);
        assertEquals(Swordtail.MAXIMUM_AGE_IN_WEEKS, testSwordtail.getAgeInWeeks());

        testSwordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS - 10);
        assertEquals(Swordtail.MAXIMUM_AGE_IN_WEEKS - 10, testSwordtail.getAgeInWeeks());
    }

    @Test
    public void healthCoefficientMutatorIgnoresInvalidArguments() {
        double healthCoefficient = zeroParamSwordtail.getHealthCoefficient();
        zeroParamSwordtail.setHealthCoefficient(-0.01);
        assertEquals(healthCoefficient, zeroParamSwordtail.getHealthCoefficient(), 0.0);

        zeroParamSwordtail.setHealthCoefficient(1.01);
        assertEquals(healthCoefficient, zeroParamSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void healthCoefficientMutatorAcceptsValidArguments() {

        testSwordtail.setHealthCoefficient(1.0);
        assertEquals(1.0, testSwordtail.getHealthCoefficient(), 0.0);

        testSwordtail.setHealthCoefficient(0.5);
        assertEquals(0.5, testSwordtail.getHealthCoefficient(), 0.0);

        testSwordtail.setHealthCoefficient(0.0);
        assertEquals(0.0, testSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void babyFishNeedMinimalVolumeOfWater() {
        Swordtail fry = new Swordtail();
        for (int i = 0; i < Swordtail.YOUNG_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            assertEquals(Swordtail.MINIMUM_WATER_VOLUME_ML, fry.getVolumeNeeded(), 0.0);
        }
    }

    @Test
    public void youngFishNeedCorrectVolumeOfWater() {
        Swordtail fry = new Swordtail();
        for (int i = Swordtail.YOUNG_FISH_AGE_IN_WEEKS; i <= Swordtail.MATURE_FISH_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Swordtail.MINIMUM_WATER_VOLUME_ML * fry.getAgeInWeeks() / Swordtail.YOUNG_FISH_AGE_IN_WEEKS;
            assertEquals(volumeNeeded, fry.getVolumeNeeded(), 0.001);
        }
    }

    @Test
    public void deadFishNeedNoWater() {
        Swordtail fry = new Swordtail();
        fry.setAgeInWeeks(150);
        fry.incrementAge();
        assertEquals(0.0, fry.getVolumeNeeded(), 0.0);
    }

    @Test
    public void changeHealthCoefficientWillNotPermitOverlargeHealthCoefficients() {
        testSwordtail.changeHealthCoefficient(1.5);
        assertEquals(1.0, testSwordtail.getHealthCoefficient(), 0.0);
    }


    @Test
    public void changeHealthCoefficientWillNotPermitNegativeHealthCoefficients() {
        testSwordtail.changeHealthCoefficient(-1.5);
        assertEquals(0.0, testSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void changeHealthCoefficientToZeroOrLowerKillsTheSwordtail() {
        testSwordtail.changeHealthCoefficient(-1.5);
        assertFalse(testSwordtail.getIsAlive());
    }

    @Test
    void testSetIsAliveToFalse() {
        testSwordtail.setIsAlive(false);
        final boolean actual = testSwordtail.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetIsAliveNoChangeFromDead() {
        testSwordtail.setIsAlive(false);
        testSwordtail.setIsAlive(true);
        final boolean actual = testSwordtail.getIsAlive();
        assertFalse(actual);
    }

    @Test
    void testSetAgeInWeeksNoChangeIfSwordtailDead() {
        final int expected = testSwordtail.getAgeInWeeks();
        testSwordtail.setIsAlive(false);
        testSwordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS);
        final int actual = testSwordtail.getAgeInWeeks();
        assertEquals(expected, actual);
    }

    @Test
    void testChangeHealthCoefficientToZeroResultsSwordtailDeath() {
        final double delta = -1.0;
        testSwordtail.changeHealthCoefficient(delta);
        assertFalse(testSwordtail.getIsAlive());
    }

    @Test
    void testToString() {
        Swordtail defaultSwordtail = new Swordtail();
        final int numberAlreadyCreated = Swordtail.getNumberOfSwordtailsBorn();
        final String expected = "Swordtail{genus='Xiphophorus', species='hellerii', ageInWeeks=0, " +
                "isFemale=true, generationNumber=0, isAlive=true, healthCoefficient=0.6, " +
                "identificationNumber=" + numberAlreadyCreated + "}";
        final String actual = defaultSwordtail.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void createSwordtailWithZeroHealthCoefficientIsDead() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertFalse(fry.getIsAlive());
    }

    @Test
    public void SwordtailWithNullGenusAndSpecies() {
        Swordtail fry = new Swordtail(null,
                null,
                0,
                true,
                0,
                Swordtail.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        String actual = fry.getGenus();
        String expected = Swordtail.DEFAULT_GENUS;
        assertEquals(expected, actual);
    }

    @Test
    public void containsConstantCalledFIRST_GENERATION() {
        assertEquals(0, Swordtail.FIRST_GENERATION);
    }

    @Test
    public void changeHealthCoefficientWillPermitPositiveHealthCoefficients() {
        testSwordtail.changeHealthCoefficient(0.05);
        assertEquals(0.8, testSwordtail.getHealthCoefficient(), 0.001);
    }

    @Test
    public void changeHealthCoefficientWillPermitNegativeHealthCoefficients() {
        testSwordtail.changeHealthCoefficient(-0.05);
        assertEquals(0.7, testSwordtail.getHealthCoefficient(), 0.001);
    }

        @Test
    public void incrementAgeOneDeath() {
        Guppy guppy = new Guppy(  "Poecilia",
                "reticulata",
                Guppy.MAXIMUM_AGE_IN_WEEKS,
                true,
                3,
                1.0);
        Swordtail swordtail = new Swordtail(  "Xiphophorus",
                "hellerii",
                4,
                false,
                3,
                0.60);

        testPool.addFish(guppy);
        testPool.addFish(swordtail);

        assertEquals(1, testPool.incrementAges());
    }
}
