import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PoolTest {

    private Pool zeroParamPool;
    private Pool testPool;

    @BeforeEach
    void setUp() {
        zeroParamPool = new Pool();
        testPool = new Pool("namedPool",
                1000.0,
                40.0,
                7.0,
                0.50
                );
    }

    @Test
    public void containsConstantCalledDEFAULT_POOL_NAME(){ assertTrue(Pool.DEFAULT_POOL_NAME.equals("Unnamed"));};
    @Test
     public void containsConstantCalledDEFAULT_POOL_TEMP_CELSIUS(){ assertEquals(40.0, Pool.DEFAULT_POOL_TEMP_CELSIUS, 0.0 );};
    @Test
     public void containsConstantCalledMINIMUM_POOL_TEMP_CELSIUS(){ assertEquals(0.0, Pool.MINIMUM_POOL_TEMP_CELSIUS, 0.0 );};
    @Test
     public void containsConstantCalledMAXIMUM_POOL_TEMP_CELSIUS(){ assertEquals(100.0, Pool.MAXIMUM_POOL_TEMP_CELSIUS, 0.0 );};
    @Test
    public void containsConstantCalledNEUTRAL_PH(){ assertEquals(7.0, Pool.NEUTRAL_PH, 0.0 );};
    @Test
     public void containsConstantCalledDEFAULT_NUTRIENT_COEFFICIENT(){ assertEquals(0.50, Pool.DEFAULT_NUTRIENT_COEFFICIENT, 0.0 );};
    @Test
     public void containsConstantCalledMINIMUM_NUTRIENT_COEFFICIENT(){ assertEquals(0.0, Pool.MINIMUM_NUTRIENT_COEFFICIENT, 0.0 );};
    @Test
     public void containsConstantCalledMAXIMUM_NUTRIENT_COEFFICIENT(){ assertEquals(1.0, Pool.MAXIMUM_NUTRIENT_COEFFICIENT, 0.0);}
    @Test
    public void staticPoolCounterIsCorrectlyTrackingNewbornPoolsInZeroParamConstructor() {
        final int numberAlreadyCreated = Pool.getNumberCreated();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool aPool = new Pool();
        }
        assertEquals(numberAlreadyCreated + numberCreated, Pool.getNumberCreated());
    }
    @Test
    public void staticPoolCounterIsCorrectlyTrackingNewbornPoolsInMultiParamConstructor() {
        final int numberAlreadyCreated = Pool.getNumberCreated();
        final int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool aPool = new Pool("namedPool",
                1000.0,
                40.0,
                7.0,
                0.50
                );
        }
        assertEquals(numberAlreadyCreated + numberCreated, Pool.getNumberCreated());
    }
    @Test
    public void zeroParamPoolIsSetToCorrectValues() {
        assertTrue(zeroParamPool.getName().equals("Unnamed"));
        assertEquals(40.0, zeroParamPool.getTemperatureCelsius(), 0.0);
        assertEquals(7.0, zeroParamPool.getpH(), 0.0);
        assertEquals(0.50, zeroParamPool.getNutrientCoefficient(), 0.0);
    }
    @Test
    public void multiParamPoolIsSetToCorrectValues() {
        assertTrue(testPool.getName().equals("Namedpool"));
        assertEquals(40.0, testPool.getTemperatureCelsius(), 0.0);
        assertEquals(7.0, testPool.getpH(), 0.0);
        assertEquals(0.50, testPool.getNutrientCoefficient(), 0.0);
        assertEquals(1000.0, testPool.getVolumeLitres(), 0.0);
    }

    @Test
    public void identificationNumbersAreSequentialAndUniqueInZeroParamConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool newPool = new Pool();
            assertEquals(firstID + i, newPool.getIdentificationNumber());
        }
    }
    @Test
    public void identificationNumbersAreSequentialAndUniqueInMultiParamConstruction() {
        Pool first = new Pool();
        int firstID = first.getIdentificationNumber() + 1;
        int numberCreated = 100;
        for (int i = 0; i < numberCreated; ++i) {
            Pool aPool = new Pool("namedPool",
                    1000.0,
                    40.0,
                    7.0,
                    0.50
            );
            assertEquals(firstID + i, aPool.getIdentificationNumber());
        }
    }
    @Test
    public void nameIsCorrectlyFormattedAndStored() {
        Pool aPool = new Pool("namedPool",
                1000.0,
                40.0,
                7.0,
                0.50
        );
        assertTrue(aPool.getName().equals("Namedpool"));
    }
    @Test
    public void nullNameExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Pool aPool = new Pool(null,
                    1000.0,
                    40.0,
                    7.0,
                    0.50
            );
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }
    @Test
    public void emptyNameExceptionThrown() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Pool aPool = new Pool("   ",
                    1000.0,
                    40.0,
                    7.0,
                    0.50
            );
        });

        String expectedMessage = "Name cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }
    @Test
    public void volumeCannotBeNegative() {
        Pool pool = new Pool("Test", -100, 25, 7, 0.5);
        assertTrue(pool.getVolumeLitres() >= 0);
    }

    @Test
    public void testTemperatureBelowMinimum() {
        Pool pool = new Pool("Test", 1000, -10, 7, 0.5);
        assertTrue(pool.getTemperatureCelsius() >= Pool.MINIMUM_POOL_TEMP_CELSIUS);
    }

    @Test
    public void testTemperatureAboveMaximum() {
        Pool pool = new Pool("Test", 1000, 50, 7, 0.5);
        assertTrue(pool.getTemperatureCelsius() <= Pool.MAXIMUM_POOL_TEMP_CELSIUS);
    }

    @Test
    public void testPHBelowMinimum() {
        Pool pool = new Pool("Test", 1000, 25, -1, 0.5);
        assertEquals(Pool.NEUTRAL_PH, pool.getpH());
    }

    @Test
    public void testPHAboveMaximum() {
        Pool pool = new Pool("Test", 1000, 25, 15, 0.5);
        assertEquals(Pool.NEUTRAL_PH, pool.getpH());
    }

    @Test
    public void testNutrientCoefficientBelowMinimum() {
        Pool pool = new Pool("Test", 1000, 25, 7, -0.1);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, pool.getNutrientCoefficient());
    }

    @Test
    public void testNutrientCoefficientAboveMaximum() {
        Pool pool = new Pool("Test", 1000, 25, 7, 1.1);
        assertEquals(Pool.DEFAULT_NUTRIENT_COEFFICIENT, pool.getNutrientCoefficient());
    }
    @Test
    public void whenVolumeIsNegativeThenVolumeIsNotChanged() {
        Pool pool = new Pool("Test", 1000, 25, 15, 0.5);
        double initialVolume = pool.getVolumeLitres();
        pool.setVolumeLitres(-500);
        assertEquals(initialVolume, pool.getVolumeLitres());
    }

    @Test
    public void whenVolumeIsNonNegativeThenVolumeIsUpdated() {
        Pool pool = new Pool("Test", 1000, 25, 15, 0.5);
        pool.setVolumeLitres(1500);
        assertEquals(1500, pool.getVolumeLitres());
    }

    @Test
    public void setTemperatureWithinBoundsThenTemperatureIsUpdated() {
        double newTemperature = Pool.MINIMUM_POOL_TEMP_CELSIUS + 1;
        testPool.setTemperatureCelsius(newTemperature);
        assertEquals(newTemperature, testPool.getTemperatureCelsius());
    }

    @Test
    public void setTemperatureBelowMinimumThenTemperatureIsNotChanged() {
        double initialTemperature = testPool.getTemperatureCelsius();
        testPool.setTemperatureCelsius(Pool.MINIMUM_POOL_TEMP_CELSIUS - 1);
        assertEquals(initialTemperature, testPool.getTemperatureCelsius());
    }

    @Test
    public void setTemperatureAboveMaximumThenTemperatureIsNotChanged() {
        double initialTemperature = testPool.getTemperatureCelsius();
        testPool.setTemperatureCelsius(Pool.MAXIMUM_POOL_TEMP_CELSIUS + 1);
        assertEquals(initialTemperature, testPool.getTemperatureCelsius());
    }
    @Test
    public void setPHWithinBoundsThenPHIsUpdated() {
        double newPH = 8.5;
        testPool.setPH(newPH);
        assertEquals(newPH, testPool.getpH());
    }

    @Test
    public void setPHBelowMinimumThenPHIsNotChanged() {
        double initialPH = testPool.getpH();
        testPool.setPH(-1.0);
        assertEquals(initialPH, testPool.getpH());
    }

    @Test
    public void setPHAboveMaximumThenPHIsNotChanged() {
        double initialPH = testPool.getpH();
        testPool.setPH(15.0);
        assertEquals(initialPH, testPool.getpH());
    }
    @Test
    public void setNutrientCoefficientWithinBoundsThenNutrientCoefficientIsUpdated() {
        double newNutrientCoefficient = 0.8;
        testPool.setNutrientCoefficient(newNutrientCoefficient);
        assertEquals(newNutrientCoefficient, testPool.getNutrientCoefficient());
    }

    @Test
    public void setNutrientCoefficientBelowMinimumThenNutrientCoefficientIsNotChanged() {
        double initialNutrientCoefficient = testPool.getNutrientCoefficient();
        testPool.setNutrientCoefficient(-0.1);
        assertEquals(initialNutrientCoefficient, testPool.getNutrientCoefficient());
    }

    @Test
    public void setNutrientCoefficientAboveMaximumThenNutrientCoefficientIsNotChanged() {
        double initialNutrientCoefficient = testPool.getNutrientCoefficient();
        testPool.setNutrientCoefficient(1.1);
        assertEquals(initialNutrientCoefficient, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientCoefficientWithinBoundsThenNutrientCoefficientIsUpdated() {
        testPool.setNutrientCoefficient(0.5);
        double delta = 0.2;
        double expected = 0.5 + delta;
        testPool.changeNutrientCoefficient(delta);
        assertEquals(expected, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientCoefficientToExceedMaximumThenNutrientCoefficientIsCappedAtMaximum() {
        testPool.setNutrientCoefficient(0.5);
        double delta = 0.6;
        testPool.changeNutrientCoefficient(delta);
        assertEquals(Pool.MAXIMUM_NUTRIENT_COEFFICIENT, testPool.getNutrientCoefficient());
    }

    @Test
    public void changeNutrientCoefficientToBelowMinimumThenNutrientCoefficientIsKeptAtMinimum() {
        testPool.setNutrientCoefficient(0.5);
        double delta = -0.6;
        testPool.changeNutrientCoefficient(delta);
        assertEquals(Pool.MINIMUM_NUTRIENT_COEFFICIENT, testPool.getNutrientCoefficient());
    }
    @Test
    public void changeTemperatureWithinBoundsThenTemperatureIsUpdated() {
        testPool.setTemperatureCelsius(25.0);
        double delta = 5.0;
        double expected = 25.0 + delta;
        testPool.changeTemperature(delta);
        assertEquals(expected, testPool.getTemperatureCelsius());
    }

    @Test
    public void changeTemperatureToExceedMaximumThenTemperatureIsCappedAtMaximum() {
        testPool.setTemperatureCelsius(25.0);
        double delta = 100.0;
        testPool.changeTemperature(delta);
        assertEquals(Pool.MAXIMUM_POOL_TEMP_CELSIUS, testPool.getTemperatureCelsius());
    }

    @Test
    public void changeTemperatureToBelowMinimumThenTemperatureIsKeptAtMinimum() {
        testPool.setTemperatureCelsius(25.0);
        double delta = -100.0;
        testPool.changeTemperature(delta);
        assertEquals(Pool.MINIMUM_POOL_TEMP_CELSIUS, testPool.getTemperatureCelsius());
    }
}
