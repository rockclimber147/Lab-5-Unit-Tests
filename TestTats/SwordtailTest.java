import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwordtailTest {
    private Swordtail zeroParamSwordtail;
    private Swordtail testSwordtail;

    @BeforeEach
    public void setUp() {

        zeroParamSwordtail = new Swordtail();
        testSwordtail = new Swordtail(  "Poecilia",
                "reticulata",
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
        assertEquals(Swordtail.DEFAULT_GENUS, "Xiphophorus");
    }

    @Test
    public void containsConstantCalledDEFAULT_SPECIES() {
        assertEquals(Swordtail.DEFAULT_SPECIES, "hellerii");
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
    public void staticSwordtailCounterIsCorrectlyTrackingNewbornGuppiesInZeroParamConstructor() {
        final int numberAlreadyCreated = Swordtail.getNumberOfFishBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            new Swordtail();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Swordtail.getNumberOfFishBorn());
    }

    @Test
    public void staticSwordtailCounterIsCorrectlyTrackingNewbornGuppiesInMultiParamConstructor() {
        final int numberAlreadyCreated = Swordtail.getNumberOfFishBorn();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Swordtail("Poecilia",
                    "reticulata",
                    1,
                    true,
                    3,
                    0.75);
        assertEquals(numberAlreadyCreated + numberCreated, Swordtail.getNumberOfFishBorn());
    }

    @Test
    public void zeroParamSwordtailIsSetToCorrectValues() {
        assertEquals("Xiphophorus", zeroParamSwordtail.getGenus());
        assertEquals("hellerii", zeroParamSwordtail.getSpecies());
        assertEquals(0, zeroParamSwordtail.getAgeInWeeks());
        assertTrue(zeroParamSwordtail.getIsFemale());
        assertEquals(0, zeroParamSwordtail.getGenerationNumber());
        assertTrue(zeroParamSwordtail.getIsAlive());
        assertEquals(0.6, zeroParamSwordtail.getHealthCoefficient(), 0.0);
    }

    @Test
    public void multiParamSwordtailIsSetToCorrectValues() {
        assertEquals("Poecilia", testSwordtail.getGenus());
        assertEquals("reticulata", testSwordtail.getSpecies());
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
            Swordtail fry = new Swordtail("Poecilia",
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
        Swordtail fry = new Swordtail("  poECILIA    ",
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
        assertThrows(IllegalArgumentException.class, () -> new Swordtail(null, "a", 0, true, 0 , 0.5));
    }

    @Test
    public void emptyGenusThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Swordtail("    ", "a", 0, true, 0 , 0.5));
    }

    @Test
    public void nullSpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Swordtail("a", null, 0, true, 0 , 0.5));
    }

    @Test
    public void emptySpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Swordtail("a", "    ", 0, true, 0 , 0.5));
    }

    @Test
    public void negativeAgeInWeeksBecomesZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                -1,
                true,
                0 ,
                0.5);
        assertEquals(0, fry.getAgeInWeeks());
    }

    @Test
    public void zeroAgeInWeeksRemainsZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0 ,
                0.5);
        assertEquals(0, fry.getAgeInWeeks());
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
        assertEquals(Swordtail.MAXIMUM_AGE_IN_WEEKS, fry.getAgeInWeeks());
    }

    @Test
    public void negativeGenerationNumberSetToZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                -1,
                0.5);
        assertEquals(0, fry.getGenerationNumber());
    }

    @Test
    public void zeroGenerationNumberSetToZero() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true, 0,
                0.5);
        assertEquals(0, fry.getGenerationNumber());
    }

    @Test
    public void createSwordtailWithNegativeHealthCoefficient() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.MINIMUM_HEALTH_COEFFICIENT - 1.0);
        assertEquals(Swordtail.MINIMUM_HEALTH_COEFFICIENT, fry.getHealthCoefficient());
    }

    @Test
    public void createSwordtailWithOverlargeHealthCoefficient() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.MAXIMUM_HEALTH_COEFFICIENT + 1.0);
        assertEquals(Swordtail.MAXIMUM_HEALTH_COEFFICIENT, fry.getHealthCoefficient());

    }

    @Test
    public void testIncrementAge() {
        int oldAge = testSwordtail.getAgeInWeeks();
        testSwordtail.incrementAge();
        int newAge = testSwordtail.getAgeInWeeks();
        assertEquals(newAge, (oldAge + 1));
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
    public void matureFishNeedCorrectVolumeOfWater() {
        Swordtail fry = new Swordtail();
        for (int i = Swordtail.MATURE_FISH_AGE_IN_WEEKS + 1; i <= Swordtail.MAXIMUM_AGE_IN_WEEKS; ++i) {
            fry.setAgeInWeeks(i);
            double volumeNeeded = Swordtail.MINIMUM_WATER_VOLUME_ML * 3.0;
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
        final int numberAlreadyCreated = Swordtail.getNumberOfFishBorn();
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
    public void guppyWithNullGenusAndSpeciesThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Swordtail(null, null, 0, true, 0, Swordtail.MINIMUM_HEALTH_COEFFICIENT - 1.0));
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
    public void spawnIsNotFemaleReturnsNull() {
        Swordtail fry = new Swordtail("a",
                "b",
                10,
                false,
                0,
                Swordtail.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }

    @Test
    public void spawnYoungerThanMinimumAgeReturnsNull() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                true,
                0,
                Swordtail.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }

    @Test
    public void spawnIsNotFemaleAndYoungerThanMinimumAgeReturnsNull() {
        Swordtail fry = new Swordtail("a",
                "b",
                0,
                false,
                0,
                Swordtail.DEFAULT_HEALTH_COEFFICIENT);
        assertNull(fry.spawn());
    }
}
