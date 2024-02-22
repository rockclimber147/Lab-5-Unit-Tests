import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PoolTest {
    private final Pool zeroParamPool = new Pool();
    private final Pool testPool = new Pool("test", 6.9, 6.9, 6.9, 0.69);

    @Test
    public void containsConstantDEFAULT_POOL_NAME() {
        assertEquals("Unnamed", Pool.DEFAULT_POOL_NAME);
    }

    @Test
    public void containsConstantDEFAULT_POOL_TEMP_CELSIUS() {
        assertEquals(40.0, Pool.DEFAULT_POOL_TEMP_CELSIUS);
    }

    @Test
    public void containsConstantMINIMUM_POOL_TEMP_CELSIUS() {
        assertEquals(0.0, Pool.MINIMUM_POOL_TEMP_CELSIUS);
    }

    @Test
    public void containsConstantMAXIMUM_POOL_TEMP_CELSIUS() {
        assertEquals(100.0, Pool.MAXIMUM_POOL_TEMP_CELSIUS);
    }

    @Test
    public void containsConstantNEUTRAL_PH() {
        assertEquals(7.0, Pool.NEUTRAL_PH);
    }

    @Test
    public void containsConstantDEFAULT_NUTRIENT_COEFFICIENT() {
        assertEquals(0.50, Pool.DEFAULT_NUTRIENT_COEFFICIENT);
    }

    @Test
    public void containsConstantMINIMUM_NUTRIENT_COEFFICIENT() {
        assertEquals(0.0, Pool.MINIMUM_NUTRIENT_COEFFICIENT);
    }

    @Test
    public void containsConstantMAXIMUM_NUTRIENT_COEFFICIENT() {
        assertEquals(1.0, Pool.MAXIMUM_NUTRIENT_COEFFICIENT);
    }

    @Test
    public void staticPoolCounterIsCorrectlyTrackingNewPoolsInZeroParamConstructor() {
        final int numberOfPools = Pool.getNumberCreated();
        final int expected = 100;
        for (int i = 0; i < expected; i++) {
            new Pool();
        }
        assertEquals(numberOfPools + expected, Pool.getNumberCreated());
    }

    @Test
    public void staticPoolCounterIsCorrectlyTrackingNewPoolsInMultiParamConstructor() {
        final int numberOfPools = Pool.getNumberCreated();
        final int expected = 100;
        for (int i = 0; i < expected; i++) {
            new Pool("a", 0.0, 4.0, 3.0, 0.3);
        }
        assertEquals(numberOfPools + expected, Pool.getNumberCreated());
    }

    @Test
    public void zeroParamPoolIsSetToCorrectValues() {
        assertEquals("Unnamed", zeroParamPool.getName());
        assertEquals(40.0, zeroParamPool.getTemperatureCelsius());
        assertEquals(7.0, zeroParamPool.getPH());
        assertEquals(0.50, zeroParamPool.getNutrientCoefficient());
    }

    @Test
    public void multiParamPoolIsSetToCorrectValues() {
        assertEquals("Test", testPool.getName());
        assertEquals(6.9, testPool.getTemperatureCelsius());
        assertEquals(6.9, testPool.getPH());
        assertEquals(0.69, testPool.getNutrientCoefficient());
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInZeroParamConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool pool = new Pool();
            assertEquals(firstID + i, pool.getIdentificationNumber());
        }
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool pool = new Pool("testing", 6.9, 6.9, 6.9, 0.69);
            assertEquals(firstID + i, pool.getIdentificationNumber());
        }
    }

    @Test
    public void poolNameAreCorrectlyFormattedAndStored() {
        Pool pool = new Pool(" TeStInG", 6.9, 6.9, 6.9, 0.69);
        assertEquals("Testing", pool.getName());
    }

    @Test
    public void nullNameThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Pool(null, 6.9, 6.9, 6.9, 0.69));
    }

    @Test
    public void emptyNameThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Pool("     ", 6.9, 6.9, 6.9, 0.69));
    }

    @Test
    public void underZeroLitreBecomesZero() {
        Pool pool = new Pool("a", -4.4, 6.9, 6.9, 0.69);
        assertEquals(0, pool.getVolumeLitres());
    }

    @Test
    public void zeroLitreRemainsZero() {
        Pool pool = new Pool("a", 0, 6.9, 6.9, 0.69);
        assertEquals(0, pool.getVolumeLitres());
    }

    @Test
    public void underZeroTempBecomesDefault() {
        Pool pool = new Pool("a", 6.9, -6.9, 6.9, 0.69);
        assertEquals(40.0, pool.getTemperatureCelsius());
    }

    @Test
    public void zeroTempRemainsZero() {
        Pool pool = new Pool("a", 6.9, 0, 6.9, 0.69);
        assertEquals(0, pool.getTemperatureCelsius());
    }

    @Test
    public void overMaxTempBecomesDefault() {
        Pool pool = new Pool("a", 6.9, 420.0, 6.9, 0.69);
        assertEquals(40.0, pool.getTemperatureCelsius());
    }

    @Test
    public void maxTempRemainsMaxTemp() {
        Pool pool = new Pool("a", 6.9, 100.0, 6.9, 0.69);
        assertEquals(100.0, pool.getTemperatureCelsius());
    }

    @Test
    public void underZeroPHBecomesNeutral() {
        Pool pool = new Pool("a", 6.9, 6.9, -6.9, 0.69);
        assertEquals(7.0, pool.getPH());
    }

    @Test
    public void zeroPHRemainsZero() {
        Pool pool = new Pool("a", 6.9, 6.9, 0, 0.69);
        assertEquals(0, pool.getPH());
    }

    @Test
    public void overMaxPHBecomesNeutral() {
        Pool pool = new Pool("a", 6.9, 6.9, 69, 0.69);
        assertEquals(7.0, pool.getPH());
    }

    @Test
    public void maxPHRemainsMaxTemp() {
        Pool pool = new Pool("a", 6.9, 6.9, 14.0, 0.69);
        assertEquals(14.0, pool.getPH());
    }

    @Test
    public void underZeroNutrientCoefficientBecomesDefault() {
        Pool pool = new Pool("a", 6.9, 6.9, 6.9, -0.69);
        assertEquals(0.5, pool.getNutrientCoefficient());
    }

    @Test
    public void zeroNutrientCoefficientRemainsZero() {
        Pool pool = new Pool("a", 6.9, 6.9, 6.9, 0);
        assertEquals(0, pool.getNutrientCoefficient());
    }

    @Test
    public void overMaxNutrientCoefficientBecomesDefault() {
        Pool pool = new Pool("a", 6.9, 6.9, 6.9, 1.69);
        assertEquals(0.5, pool.getNutrientCoefficient());
    }

    @Test
    public void maxNutrientCoefficientRemainsMaxTemp() {
        Pool pool = new Pool("a", 6.9, 100.0, 6.9, 1.0);
        assertEquals(1.0, pool.getNutrientCoefficient());
    }

    @Test
    public void normalSetVolumeLitre() {
        testPool.setVolumeLitres(69);
        assertEquals(69, testPool.getVolumeLitres());
    }

    @Test
    public void testSetLitresUnderZeroLitresIgnored() {
        double oldVolume = testPool.getVolumeLitres();
        testPool.setVolumeLitres(-69);
        assertEquals(oldVolume, testPool.getVolumeLitres());
    }

    @Test
    public void testSetLitresZeroLitresRemainsZero() {
        testPool.setVolumeLitres(0);
        assertEquals(0, testPool.getVolumeLitres());
    }

    @Test
    public void normalSetTemperature() {
        testPool.setTemperatureCelsius(69);
        assertEquals(69, testPool.getTemperatureCelsius());
    }

    @Test
    public void testSetTempUnderMinIgnored() {
        double oldTemp = testPool.getTemperatureCelsius();
        testPool.setTemperatureCelsius(-69);
        assertEquals(oldTemp, testPool.getTemperatureCelsius());
    }

    @Test
    public void testSetTempMINIMUM_POOL_TEMP_CELSIUSRemainsSame() {
        testPool.setTemperatureCelsius(0);
        assertEquals(0, testPool.getTemperatureCelsius());
    }

    @Test
    public void testSetTempMAXIMUM_POOL_TEMP_CELSIUSRemainsSame() {
        testPool.setTemperatureCelsius(100.0);
        assertEquals(100.0, testPool.getTemperatureCelsius());
    }

    @Test
    public void testSetTempOverMaxIgnored() {
        double oldTemp = testPool.getTemperatureCelsius();
        testPool.setTemperatureCelsius(420);
        assertEquals(oldTemp, testPool.getTemperatureCelsius());
    }

    @Test
    public void normalSetPH() {
        testPool.setPH(8);
        assertEquals(8, testPool.getPH());
    }

    @Test
    public void testSetPHUnderZeroPHIgnored() {
        double oldPH = testPool.getPH();
        testPool.setPH(-69);
        assertEquals(oldPH, testPool.getPH());
    }

    @Test
    public void testSetPHZeroPHRemains() {
        testPool.setPH(0);
        assertEquals(0, testPool.getPH());
    }

    @Test
    public void testSetPHMaxRemains() {
        testPool.setPH(14);
        assertEquals(14, testPool.getPH());
    }

    @Test
    public void testSetPHOverMaxIgnored() {
        double oldPH = testPool.getPH();
        testPool.setPH(69);
        assertEquals(oldPH, testPool.getPH());
    }

    @Test
    public void normalSetNutrientCoefficient() {
        testPool.setNutrientCoefficient(0.9);
        assertEquals(0.9, testPool.getNutrientCoefficient());
    }

    @Test
    public void testSetNutrientUnderMinIgnored() {
        double oldCoefficient = testPool.getNutrientCoefficient();
        testPool.setNutrientCoefficient(-0.6);
        assertEquals(oldCoefficient, testPool.getNutrientCoefficient());
    }

    @Test
    public void testSetNutrientMinRemains() {
        testPool.setNutrientCoefficient(0);
        assertEquals(0, testPool.getNutrientCoefficient());
    }

    @Test
    public void testSetNutrientMaxRemains() {
        testPool.setNutrientCoefficient(1);
        assertEquals(1, testPool.getNutrientCoefficient());
    }

    @Test
    public void testSetNutrientOverMaxIgnored() {
        double oldCoefficient = testPool.getNutrientCoefficient();
        testPool.setNutrientCoefficient(69);
        assertEquals(oldCoefficient, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientNegativeNumber() {
        double oldCoefficient = testPool.getNutrientCoefficient();
        testPool.changeNutrientCoefficient(-0.3);
        assertEquals(oldCoefficient - 0.3, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientPositiveNumber() {
        double oldCoefficient = testPool.getNutrientCoefficient();
        testPool.changeNutrientCoefficient(0.3);
        assertEquals(oldCoefficient + 0.3, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientCoefficientToUnderMin() {
        testPool.changeNutrientCoefficient(-69);
        assertEquals(0, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientCoefficientToOverMax() {
        testPool.changeNutrientCoefficient(69);
        assertEquals(1, testPool.getNutrientCoefficient());
    }


    @Test
    public void changeTempNegativeNumber() {
        double oldTemp = testPool.getTemperatureCelsius();
        testPool.changeTemperature(-0.3);
        assertEquals(oldTemp - 0.3, testPool.getTemperatureCelsius());
    }

    @Test
    public void changeTempPositiveNumber() {
        double oldTemp = testPool.getTemperatureCelsius();
        testPool.changeTemperature(0.3);
        assertEquals(oldTemp + 0.3, testPool.getTemperatureCelsius());
    }

    @Test
    public void changeTempToUnderMin() {
        testPool.changeTemperature(-420);
        assertEquals(0, testPool.getTemperatureCelsius());
    }

    @Test
    public void changeTempToOverMax() {
        testPool.changeTemperature(420);
        assertEquals(100, testPool.getTemperatureCelsius());
    }

    @Test
    public void addFishWhenNull() {
        assertFalse(testPool.addFish(null));
    }

    @Test
    public void addFishWhenGuppy() {
        Guppy testGuppy = new Guppy();
        assertTrue(testPool.addFish(testGuppy));
    }

    @Test
    public void addFishWhenSwordtail() {
        Swordtail testSwordtail = new Swordtail();
        assertTrue(testPool.addFish(testSwordtail));
    }

    @Test
    public void getPopulationNoDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(5, pool.getPopulation());
    }

    @Test
    public void getPopulationAllDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail();
            swordtail.setIsAlive(false);
            pool.addFish(swordtail);
        }
        assertEquals(0, pool.getPopulation());
    }

    @Test
    public void getPopulationSomeDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail();
            if (i % 2 == 0) {
                swordtail.setIsAlive(false);
            }
            pool.addFish(swordtail);
        }
        assertEquals(2, pool.getPopulation());
    }

    @Test
    public void applyNutrientCoefficientNoFishDies() {
        Pool pool = new Pool();
        pool.setNutrientCoefficient(1.0);
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(0, pool.applyNutrientCoefficient());
    }

    @Test
    public void applyNutrientCoefficientSomeFishDie() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        int numOfDeaths = pool.applyNutrientCoefficient();
        assertTrue(numOfDeaths >= 0 && numOfDeaths <= 5);
    }

    @Test
    public void applyNutrientCoefficientAllFishDie() {
        Pool pool = new Pool();
        pool.setNutrientCoefficient(0.0);
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(5, pool.applyNutrientCoefficient());
    }

    @Test
    public void applyNutrientCoefficientEmptyPool() {
        Pool pool = new Pool();
        assertEquals(0, pool.applyNutrientCoefficient());
    }

    @Test
    public void removeDeadFishNoDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(0, pool.removeDeadFish());
    }

    @Test
    public void removeDeadFishAllDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(5, pool.removeDeadFish());
    }

    @Test
    public void removeDeadFishSomeDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail();
            if (i % 2 == 0) {
                swordtail.setIsAlive(false);
            }
            pool.addFish(swordtail);
        }
        assertEquals(3, pool.removeDeadFish());
    }

    @Test
    public void getAverageAgeNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.getAverageAgeInWeeks());
    }

    @Test
    public void getAverageAgeOneFish() {
        Pool pool = new Pool();
        Guppy guppy = new Guppy("a", "b", 23, true, 2, 0.6);
        pool.addFish(guppy);
        assertEquals(23, pool.getAverageAgeInWeeks());
    }

    @Test
    public void getAverageAgeMultipleFishNoDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", 25 + i, true, 2, 0.6);
            pool.addFish(guppy);
        }
        assertEquals(27, pool.getAverageAgeInWeeks());
    }

    @Test
    public void getAverageAllDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", 25 + i, true, 2, 0.6);
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.getAverageAgeInWeeks());
    }

    @Test
    public void getAverageSomeDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", i * 2, true, 2, 0.6);
            if (i % 2 == 0) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(4, pool.getAverageAgeInWeeks());
    }

    @Test
    public void getVolumeReqNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqOneGuppy() {
        Pool pool = new Pool();
        Guppy guppy = new Guppy();
        pool.addFish(guppy);
        assertEquals(0.25, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqMultipleFishNoDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            pool.addFish(guppy);
        }
        assertEquals(1.25, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqAllDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqSomeDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i % 2 == 0) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(0.5, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqEachGuppyDifferentReq() {
        Pool pool = new Pool();
        Guppy guppy1 = new Guppy("a", "b", 0, true, 2, 0.6);
        Guppy guppy2 = new Guppy("a", "b", 23, true, 2, 0.6);
        Guppy guppy3 = new Guppy("a", "b", 48, true, 2, 0.6);
        pool.addFish(guppy1);
        pool.addFish(guppy2);
        pool.addFish(guppy3);
        assertEquals(0.25 + (0.25 * 23 / 10) + 0.25 * 1.5, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getVolumeReqEachSwordtailDifferentReq() {
        Pool pool = new Pool();
        Swordtail swordtail1 = new Swordtail("a", "b", 0, true, 2, 0.6);
        Swordtail swordtail2 = new Swordtail("a", "b", 23, true, 2, 0.6);
        Swordtail swordtail3 = new Swordtail("a", "b", 101, true, 2, 0.6);
        pool.addFish(swordtail1);
        pool.addFish(swordtail2);
        pool.addFish(swordtail3);
        assertEquals(1 + (1.0 * 23 / 10) + 1 * 3.0, pool.getFishVolumeRequirementInLitres());
    }

    @Test
    public void getFemalePercentNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentAllDeadFish() {
        Pool pool = new Pool();
        for (int i =0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentOneFish() {
        Pool pool = new Pool();
        pool.addFish(new Guppy());
        assertEquals(1, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentNoFemale() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", 0, false, 2, 0.6);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentAllFemale() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(1, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentSomeFemale() {
        Pool pool = new Pool();
        for (int i = 0; i < 6; i++) {
            Guppy guppy;
            if (i % 2 == 0) {
                guppy = new Guppy("a", "b", 0, false, 2, 0.6);
            } else {
                guppy = new Guppy("a", "b", 0, true, 2, 0.6);
            }
            pool.addFish(guppy);
        }
        assertEquals(0.5, pool.getFemalePercentage());
    }

    @Test
    public void getFemalePercentSomeDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 6; i++) {
            Guppy guppy;
            if (i % 2 == 0) {
                guppy = new Guppy("a", "b", 0, false, 2, 0.6);
            } else {
                guppy = new Guppy("a", "b", 0, true, 2, 0.6);
            }
            if (i % 3 == 0) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(2.0 / 4.0, pool.getFemalePercentage());
    }

    @Test
    public void getAverageHealthNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.getAverageHealthCoefficient());
    }

    @Test
    public void getAverageHealthOneFish() {
        Pool pool = new Pool();
        Swordtail swordtail = new Swordtail();
        pool.addFish(swordtail);
        assertEquals(0.6, pool.getAverageHealthCoefficient());
    }

    @Test
    public void getAverageHealthAllDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail();
            swordtail.setIsAlive(false);
            pool.addFish(swordtail);
        }
        assertEquals(0, pool.getAverageHealthCoefficient());
    }

    @Test
    public void getAverageHealthNoDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Swordtail("a", "b", 3, true, 5, (i + 1) / 10.0));
        }
        assertEquals(0.3, pool.getAverageHealthCoefficient());
    }

    @Test
    public void getAverageHealthSomeDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail("a", "b", 3, true, 5, (i + 1) / 10.0);
            if (i == 3) {
                swordtail.setIsAlive(false);
            }
            pool.addFish(swordtail);
        }
        assertEquals(1.1 / 4, pool.getAverageHealthCoefficient());
    }

    @Test
    public void getMedianAgeNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.getMedianAge());
    }

    @Test
    public void getMedianAgeOneFish() {
        Pool pool = new Pool();
        pool.addFish(new Guppy("a", "b", 5, false, 2, 0.6));
        assertEquals(5, pool.getMedianAge());
    }

    @Test
    public void getMedianAgeAllDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", 8, false, 2, 0.6);
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.getMedianAge());
    }

    @Test
    public void getMedianAgeAllAliveEven() {
        Pool pool = new Pool();
        for (int i = 0; i < 6; i++) {
            Guppy guppy = new Guppy("a", "b", i + 1, false, 2, 0.6);
            pool.addFish(guppy);
        }
        assertEquals(3.5, pool.getMedianAge());
    }

    @Test
    public void getMedianAgeAllAliveOdd() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", i + 1, false, 2, 0.6);
            pool.addFish(guppy);
        }
        assertEquals(3, pool.getMedianAge());
    }

    @Test
    public void getMedianAgeSomeDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", i + 1, false, 2, 0.6);
            if (i == 4) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(2.5, pool.getMedianAge());
    }

    @Test
    public void spawnWithNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.spawn());
    }

    @Test
    public void spawnWithOnlyMaleFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy("a", "b", i + 1, false, 2, 0.6);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.spawn());
    }

    @Test
    public void spawnWithOnlyDeadFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.spawn());
    }

    @Test
    public void spawnWithSomeFish() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            pool.addFish(guppy);
        }
        int numOfNewBorn = pool.spawn();
        assertTrue(numOfNewBorn >= 0 && numOfNewBorn <= 5 * Guppy.MAXIMUM_NUMBER_OF_FRY);
    }

    @Test
    public void incrementAgeNoFish() {
        Pool pool = new Pool();
        assertEquals(0, pool.incrementAges());
    }

    @Test
    public void incrementAgeAllFishAlreadyDead() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.incrementAges());
    }

    @Test
    public void incrementAgeWithNoDeaths() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            pool.addFish(new Guppy());
        }
        assertEquals(0, pool.incrementAges());
    }

    @Test
    public void incrementAgeWithSomeDeaths() {
        Pool pool = new Pool();
        for (int i= 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i == 3 || i == 2) {
                guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
            }
            pool.addFish(guppy);
        }
        assertEquals(2, pool.incrementAges());
    }

    @Test
    public void incrementAgeWithSomeDeathsWithSomeFishAlreadyDead() {
        Pool pool = new Pool();
        for (int i= 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i == 1 || i == 2) {
                guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
            } else if (i == 4) {
                guppy.setIsAlive(false);
                guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
            }
            pool.addFish(guppy);
        }
        assertEquals(2, pool.incrementAges());
    }

    @Test
    public void incrementAgeToDeathForAllGuppy() {
        Pool pool = new Pool();
        for (int i= 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setAgeInWeeks(Guppy.MAXIMUM_AGE_IN_WEEKS);
            pool.addFish(guppy);
        }
        assertEquals(5, pool.incrementAges());
    }

    @Test
    public void incrementAgeToDeathForAllSwordtail() {
        Pool pool = new Pool();
        for (int i = 0; i < 5; i++) {
            Swordtail swordtail = new Swordtail();
            swordtail.setAgeInWeeks(Swordtail.MAXIMUM_AGE_IN_WEEKS);
            pool.addFish(swordtail);
        }
        assertEquals(5, pool.incrementAges());
    }

    @Test
    public void adjustForCrowdingNoFish() {
        Pool pool = new Pool();
        pool.setVolumeLitres(5);
        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingAllFishAlreadyDead() {
        Pool pool = new Pool();
        pool.setVolumeLitres(5);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            guppy.setIsAlive(false);
            pool.addFish(guppy);
        }
        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingNotOverLimit() {
        Pool pool = new Pool();
        pool.setVolumeLitres(5);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            pool.addFish(guppy);
        }
        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingExactlyAtLimit() {
        Pool pool = new Pool();
        pool.setVolumeLitres(1.25);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            pool.addFish(guppy);
        }
        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingOverLimit() {
        Pool pool = new Pool();
        pool.setVolumeLitres(0.75);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            pool.addFish(guppy);
        }
        assertEquals(2, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingUnderLimitWithDeadFish() {
        Pool pool = new Pool();
        pool.setVolumeLitres(5);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i == 3 || i == 2) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(0, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingOverLimitWithDeadFish() {
        Pool pool = new Pool();
        pool.setVolumeLitres(0.5);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i == 3 || i == 2) {
                guppy.setIsAlive(false);
            }
            pool.addFish(guppy);
        }
        assertEquals(1, pool.adjustForCrowding());
    }

    @Test
    public void adjustForCrowdingTestWeakestFishRemoved() {
        Pool pool = new Pool();
        pool.setVolumeLitres(1);
        for (int i = 0; i < 5; i++) {
            Guppy guppy = new Guppy();
            if (i == 3) {
                guppy.setHealthCoefficient(0.2);
            }
            pool.addFish(guppy);
        }
        double preRemovalHealthCoefficientAvg = pool.getAverageHealthCoefficient();
        assertEquals(1, pool.adjustForCrowding());
        assertNotEquals(preRemovalHealthCoefficientAvg, pool.getAverageHealthCoefficient(), 0.0);
    }
}
