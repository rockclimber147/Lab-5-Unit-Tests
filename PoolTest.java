

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PoolTest {

    private Pool defaultPool = new Pool();
    private Pool testPool = new Pool("testPool",
            1000.0,
            40.0,
            7.0,
            0.5);;

    public void setUp() throws Exception {

        defaultPool = new Pool();
        testPool = new Pool("testPool",
                1000.0,
                40.0,
                7.0,
                0.5);
    }

    @Test
    public void containsConstantCalledDEFAULT_POOL_NAME() {
        assertTrue(Pool.DEFAULT_POOL_NAME.equals(Pool.DEFAULT_POOL_NAME));
    }

    @Test
    public void containsConstantCalledDEFAULT_POOL_TEMP_CELSIUS() {
        assertEquals(40.0, Pool.DEFAULT_POOL_TEMP_CELSIUS, 0.0);
    }

    @Test
    public void containsConstantCalledMINIMUM_POOL_TEMP_CELSIUS() {
        assertEquals(0.0, Pool.MINIMUM_POOL_TEMP_CELSIUS, 0.0);
    }

    @Test
    public void containsConstantCalledMAXIMUM_POOL_TEMP_CELSIUS() {
        assertEquals(100.0, Pool.MAXIMUM_POOL_TEMP_CELSIUS, 0.0);
    }

    @Test
    public void containsConstantCalledNEUTRAL_PH() {
        assertEquals(7.0, Pool.NEUTRAL_PH, 0.0);
    }

    @Test
    public void containsConstantCalledDEFAULT_NUTRIENT_COEFFICIENT() {
        assertEquals(0.5, Pool.DEFAULT_NUTRIENT_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMINIMUM_NUTRIENT_COEFFICIENT() {
        assertEquals(0.0, Pool.MINIMUM_NUTRIENT_COEFFICIENT, 0.0);
    }

    @Test
    public void containsConstantCalledMAXIMUM_NUTRIENT_COEFFICIENT() {
        assertEquals(1.0, Pool.MAXIMUM_NUTRIENT_COEFFICIENT, 0.0);
    }

    @Test
    public void staticPoolCounterIsCorrectlyTrackingPoolsInDefaultConstructor() {
        final int numberAlreadyCreated = Pool.getNumberCreated();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool aPool = new Pool();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Pool.getNumberCreated());
    }

    @Test
    public void staticPoolCounterIsCorrectlyTrackingPoolsInMultiParamConstructor() {
        final int numberAlreadyCreated = Pool.getNumberCreated();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i)
            new Pool("testPool",
                    1000.0,
                    Pool.DEFAULT_POOL_TEMP_CELSIUS,
                    Pool.NEUTRAL_PH,
                    Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(numberAlreadyCreated + numberCreated, Pool.getNumberCreated());
    }

    @Test
    public void defaultPoolIsSetToCorrectValues() {
        assertTrue(defaultPool.getName().equals(Pool.DEFAULT_POOL_NAME));
        assertEquals(0, defaultPool.getVolumeLitres(), 0.0);
        assertEquals(Pool.DEFAULT_POOL_TEMP_CELSIUS, defaultPool.getTemperatureCelsius(), 0.0);
        assertEquals(Pool.NEUTRAL_PH, defaultPool.getPH(), 0.0);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, defaultPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void multiParamPoolIsSetToCorrectValues() {
        assertTrue(testPool.getName().equals("Testpool"));
        assertEquals(1000.0, testPool.getVolumeLitres(), 0.0);
        assertEquals(Pool.DEFAULT_POOL_TEMP_CELSIUS, testPool.getTemperatureCelsius(), 0.0);
        assertEquals(Pool.NEUTRAL_PH, testPool.getPH(), 0.0);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, testPool.getNutrientCoefficient(), 0.0);
    }


    @Test
    public void identificationNumbersAreSequentialAndUniqueInDefaultConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool littlePool = new Pool();
            assertEquals(firstID + i, littlePool.getIdentificationNumber());
        }
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool newPool = new Pool("testPool",
                    1000.0,
                    Pool.DEFAULT_POOL_TEMP_CELSIUS,
                    Pool.NEUTRAL_PH,
                    Pool.DEFAULT_NUTRIENT_COEFFICIENT);
            assertEquals(firstID + i, newPool.getIdentificationNumber());
        }
    }

    @Test
    public void poolNameIsCorrectlyFormattedAndStored() {
        Pool newPool = new Pool("     testPool     ",
                1000.0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                Pool.NEUTRAL_PH,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertTrue(newPool.getName().equals("Testpool"));
    }

    @Test
    public void createExceptionWithWhitespaceName() {
        Pool newPool = new Pool("           ",
                1000.0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                Pool.NEUTRAL_PH,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
    }

    @Test
    public void multiParamConstructorSubsZeroForNegativeVolume() {
        Pool newPool = new Pool("testPool",
                -1.0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                Pool.NEUTRAL_PH,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(0.0, newPool.getVolumeLitres(), 0.0);
    }

    @Test
    public void multiParamConstructorSubsDEFAULTForNegativeTemperature() {
        Pool newPool = new Pool("testPool",
                0,
                -1.0,
                Pool.NEUTRAL_PH,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(Pool.DEFAULT_POOL_TEMP_CELSIUS, newPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void multiParamConstructorSubDEFAULTForOverlyHighTemperature() {
        Pool newPool = new Pool("testPool",
                0,
                1000.0,
                Pool.NEUTRAL_PH,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(Pool.DEFAULT_POOL_TEMP_CELSIUS, newPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void multiParamConstructorSubsNEUTRALForNegativePH() {
        Pool newPool = new Pool("testPool",
                0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                -1,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(Pool.NEUTRAL_PH, newPool.getPH(), 0.0);
    }

    @Test
    public void multiParamConstructorSubsNEUTRALForOverlargePH() {
        Pool newPool = new Pool("testPool",
                0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                15,
                Pool.DEFAULT_NUTRIENT_COEFFICIENT);
        assertEquals(Pool.NEUTRAL_PH, newPool.getPH(), 0.0);
    }

    @Test
    public void multiParamConstructorSubsDEFAULTForNegativeNutrientCoefficient() {
        Pool newPool = new Pool("testPool",
                0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                Pool.NEUTRAL_PH,
                -0.01);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, newPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void multiParamConstructorSubsDEFAULTForOverlargeNutrientCoefficient() {
        Pool newPool = new Pool("testPool",
                0,
                Pool.DEFAULT_POOL_TEMP_CELSIUS,
                Pool.NEUTRAL_PH,
                1.01);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, newPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void nameAccessorReturnsCorrectName() {
        assertTrue(defaultPool.getName().equals(Pool.DEFAULT_POOL_NAME));
        assertTrue(testPool.getName().equals("Testpool"));
    }

    @Test
    public void volumeAccessorReturnsCorrectVolume() {
        assertEquals(0.0, defaultPool.getVolumeLitres(), 0.0);
        assertEquals(1000.0, testPool.getVolumeLitres(), 0.0);
    }

    @Test
    public void temperatureAccessorReturnsCorrectTemperature() {
        assertEquals(40.0, defaultPool.getTemperatureCelsius(), 0.0);
        assertEquals(40.0, testPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void nutrientCoefficientAccessorReturnsCorrectPH() {
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, defaultPool.getNutrientCoefficient(), 0.0);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, testPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void volumeMutatorIgnoresNegativeArguments() {
        double volume = defaultPool.getVolumeLitres();
        defaultPool.setVolumeLitres(-0.01);
        assertEquals(volume, defaultPool.getVolumeLitres(), 0.0);
    }

    @Test
    public void temperatureMutatorIgnoresNegativeArguments() {
        double temperature = defaultPool.getTemperatureCelsius();
        defaultPool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS - 0.01);
        assertEquals(temperature, defaultPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void temperatureMutatorIgnoresOverlargeArguments() {
        double temperature = defaultPool.getTemperatureCelsius();
        defaultPool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS + 0.01);
        assertEquals(temperature, defaultPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void pHMutatorIgnoresNegativeArguments() {
        double pH = defaultPool.getPH();
        defaultPool.setPH(-0.01);
        assertEquals(pH, defaultPool.getPH(), 0.0);
    }

    @Test
    public void pHMutatorIgnoresOverlargeArguments() {
        double pH = defaultPool.getPH();
        defaultPool.setPH(14.01);
        assertEquals(pH, defaultPool.getPH(), 0.0);
    }

    @Test
    public void nutrientCoefficientMutatorIgnoresNegativeArguments() {
        double nutrientCoefficient = defaultPool.getNutrientCoefficient();
        defaultPool.setNutrientCoefficient(-0.01);
        assertEquals(nutrientCoefficient, defaultPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void nutrientCoefficientMutatorIgnoresOverlargeArguments() {
        double nutrientCoefficient = defaultPool.getNutrientCoefficient();
        defaultPool.setNutrientCoefficient(1.01);
        assertEquals(nutrientCoefficient, defaultPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void changeNutrientCoefficientWillNotPermitOverlargeNutrientCoefficients() {
        testPool.changeNutrientCoefficient(1.5);
        assertEquals(1.0, testPool.getNutrientCoefficient(), 0.0);
    }


    @Test
    public void changeNutrientCoefficientWillNotPermitNegativeNutrientCoefficients() {
        testPool.changeNutrientCoefficient(-1.5);
        assertEquals(Pool.MINIMUM_NUTRIENT_COEFFICIENT, testPool.getNutrientCoefficient(), 0.0);
    }

    @Test
    public void changeTemperatureWillNotPermitOverhighTemperature() {
        testPool.changeTemperature(150.0);
        assertEquals(Pool.MAXIMUM_POOL_TEMP_CELSIUS, testPool.getTemperatureCelsius(), 0.0);
    }


    @Test
    public void changeTemperatureWillNotPermitNegativeTemperature() {
        testPool.changeTemperature(-150.0);
        assertEquals(Pool.MINIMUM_POOL_TEMP_CELSIUS, testPool.getTemperatureCelsius(), 0.0);
    }

    @Test
    public void addFishToFishInPool() {
        Guppy guppy = new Guppy();
        testPool.addFish(guppy);
        assertTrue(testPool.getPopulation() == 1);
    }

    @Test
    public void applyNutrientCoefficient(){

    }

    @Test
    public void removeDeadFish() {
        Guppy guppy = new Guppy();
        testPool.addFish(guppy);
        guppy.setIsAlive(false);
        assertTrue(testPool.removeDeadFish() == 1);
    }

    @Test
    public void getVolumeRequiredInLitres() {
        Guppy guppy = new Guppy();
        Swordtail swordtail = new Swordtail();
        testPool.addFish(guppy);
        testPool.addFish(swordtail);
        assertEquals((Swordtail.MINIMUM_WATER_VOLUME_ML + Guppy.MINIMUM_WATER_VOLUME_ML)
                / Pool.ML_TO_LITRE_CONVERSION, testPool.getFishVolumeRequirementInLitres());
    }

}
