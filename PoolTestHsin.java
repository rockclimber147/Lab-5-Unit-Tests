import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Demonstrates Pool class.
 *
 * @author Hsin Pang
 * @version 2024
 */
public class Pool {
    /**
     * Defines default pool name.
     */
    public static final String DEFAULT_POOL_NAME = "Unnamed";
    /**
     * Defines default pool temperature in Celsius.
     */
    public static final double DEFAULT_POOL_TEMP_CELSIUS = 40.0;
    /**
     * Defines minimum pool temperature in Celsius.
     */
    public static final double MINIMUM_POOL_TEMP_CELSIUS = 0.0;
    /**
     * Defines maximum pool temperature in Celsius.
     */
    public static final double MAXIMUM_POOL_TEMP_CELSIUS = 100.0;
    /**
     * Defines neutral PH.
     */
    public static final double NEUTRAL_PH = 7.0;
    /**
     * Defines default nutrient coefficient.
     */
    public static final double DEFAULT_NUTRIENT_COEFFICIENT = 0.50;
    /**
     * Defines minimum nutrient coefficient.
     */
    public static final double MINIMUM_NUTRIENT_COEFFICIENT = 0.0;
    /**
     * Defines maximum nutrient coefficient.
     */
    public static final double MAXIMUM_NUTRIENT_COEFFICIENT = 1.0;
    /**
     * Defines constant to convert milliliter to liter.
     */
    public static final double KILO = 1000.0;
    /**
     * Defines maximum ph value.
     */
    public static final double MAXIMUM_PH = 14.0;
    /**
     * Defines magic number 31 for hashcode.
     */
    public static final int MULTIPLIER_31 = 31;
    /**
     * Defines magic number 32 for hashcode.
     */
    public static final int MULTIPLIER_32 = 32;
    private static int numberOfPools = 0;
    private final String name;
    private double volumeLitres;
    private double temperatureCelsius;
    private double pH;
    private double nutrientCoefficient;
    private final int identificationNumber;
    private final ArrayList<Fish> fishInPool;
    private final Random randomNumberGenerator;

    /**
     * Constructs a pool.
     */
    public Pool() {
        Pool.numberOfPools++;
        this.volumeLitres = 0.0;
        this.name = DEFAULT_POOL_NAME;
        this.temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        this.pH = NEUTRAL_PH;
        this.nutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;
        this.fishInPool = new ArrayList<>();
        this.randomNumberGenerator = new Random();
        this.identificationNumber = Pool.numberOfPools;
    }

    /**
     * Constructs a pool with arguments.
     *
     * @param name a string
     * @param volumeLitres a double
     * @param temperatureCelsius a double
     * @param pH a double
     * @param nutrientCoefficient a double
     * @throws IllegalArgumentException if any Genus or species arguments are illogical
     */
    public Pool(final String name, final double volumeLitres, final double temperatureCelsius, final double pH,
                final double nutrientCoefficient) {
        Pool.numberOfPools++;

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.name = name.trim().substring(0, 1).toUpperCase() + name.trim().substring(1).toLowerCase();
        }

        this.volumeLitres = Math.max(volumeLitres, 0);

        if (temperatureCelsius >= MINIMUM_POOL_TEMP_CELSIUS && temperatureCelsius <= MAXIMUM_POOL_TEMP_CELSIUS) {
            this.temperatureCelsius = temperatureCelsius;
        } else {
            this.temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        }

        if (pH >= 0 && pH <= MAXIMUM_PH) {
            this.pH = pH;
        } else {
            this.pH = NEUTRAL_PH;
        }

        if (nutrientCoefficient >= MINIMUM_NUTRIENT_COEFFICIENT && nutrientCoefficient
                <= MAXIMUM_NUTRIENT_COEFFICIENT) {
            this.nutrientCoefficient = nutrientCoefficient;
        } else {
            this.nutrientCoefficient = DEFAULT_NUTRIENT_COEFFICIENT;
        }

        this.fishInPool = new ArrayList<>();
        this.randomNumberGenerator = new Random();
        this.identificationNumber = Pool.numberOfPools;
    }

    /** Returns the name of pool.
     *
     * @return the name as a String
     */
    public String getName() {
        return name;
    }

    /** Returns the volume of pool in litres.
     *
     * @return the volume as a double
     */
    public double getVolumeLitres() {
        return volumeLitres;
    }

    /** Returns the temperature of pool in Celsius.
     *
     * @return the temperature as a double
     */
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    /** Returns the PH of pool.
     *
     * @return the PH as a double
     */
    public double getPH() {
        return pH;
    }

    /** Returns the nutrient coefficient of pool.
     *
     * @return the nutrient coefficient as a double
     */
    public double getNutrientCoefficient() {
        return nutrientCoefficient;
    }

    /** Returns the identification number of pool.
     *
     * @return the identification number as a double
     */
    public int getIdentificationNumber() {
        return identificationNumber;
    }

    /**
     * Sets the volume of pool in litres.
     *
     * @param volumeLitres a double
     */
    public void setVolumeLitres(final double volumeLitres) {
        if (volumeLitres >= 0) {
            this.volumeLitres = volumeLitres;
        }
    }

    /**
     * Sets the temperature of pool in Celsius.
     *
     * @param temperatureCelsius a double
     */
    public void setTemperatureCelsius(final double temperatureCelsius) {
        if (temperatureCelsius >= MINIMUM_POOL_TEMP_CELSIUS && temperatureCelsius <= MAXIMUM_POOL_TEMP_CELSIUS) {
            this.temperatureCelsius = temperatureCelsius;
        }
    }

    /**
     * Sets the PH of pool.
     *
     * @param ph a double
     */
    public void setPH(final double ph) {
        if (ph >= 0 && ph <= MAXIMUM_PH) {
            this.pH = ph;
        }
    }

    /**
     * Sets the nutrient coefficient of pool.
     *
     * @param nutrientCoefficient a double
     */
    public void setNutrientCoefficient(final double nutrientCoefficient) {
        if (nutrientCoefficient >= MINIMUM_NUTRIENT_COEFFICIENT && nutrientCoefficient
                <= MAXIMUM_NUTRIENT_COEFFICIENT) {
            this.nutrientCoefficient = nutrientCoefficient;
        }
    }

    /**
     * Sets the change in nutrient coefficient of pool.
     *
     * @param delta a double
     */
    public void changeNutrientCoefficient(final double delta) {
        double newNutrientCoefficient = Math.min(Math.max(getNutrientCoefficient() + delta,
                MINIMUM_NUTRIENT_COEFFICIENT), MAXIMUM_NUTRIENT_COEFFICIENT);
        setNutrientCoefficient(newNutrientCoefficient);
    }

    /**
     * Sets the change in temperature of pool.
     *
     * @param delta a double
     */
    public void changeTemperature(final double delta) {
        double newTemperatureCelsius = Math.min(Math.max(getTemperatureCelsius() + delta,
                MINIMUM_POOL_TEMP_CELSIUS), MAXIMUM_POOL_TEMP_CELSIUS);
        setTemperatureCelsius(newTemperatureCelsius);
    }

    /**
     * Returns the number of pool.
     *
     * @return the number of pool as an integer
     */
    public static int getNumberCreated() {
        return Pool.numberOfPools;
    }

    /**
     * Adds fish into the pool.
     *
     * @param fish a Fish type
     * @return true if fish is added to the pool else false
     */
    public boolean addFish(final Fish fish) {
        if (fish == null) {
            return false;
        } else {
            fishInPool.add(fish);
            return true;
        }
    }

    /**
     * Returns the population in the pool.
     *
     * @return fish count in  the pool as integer
     */
    public int getPopulation() {
        int aliveCount = 0;
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                aliveCount++;
            }
        }
        return aliveCount;
    }

    /**
     * Kills fish by comparing its nutrient coefficient.
     *
     * @return number of died fish as integer
     */
    public int applyNutrientCoefficient() {
        int numberOfDeath = 0;
        for (Fish fish : fishInPool) {
            if (randomNumberGenerator.nextDouble() > nutrientCoefficient) {
                fish.setIsAlive(false);
                numberOfDeath++;
            }
        }
        return numberOfDeath;
    }

    /**
     * Removes dead fish from the pool.
     *
     * @return number of fish removed as integer
     */
    public int removeDeadFish() {
        Iterator<Fish> fishIterator = fishInPool.iterator();
        int removedFish = 0;
        while (fishIterator.hasNext()) {
            Fish fish = fishIterator.next();
            if (!fish.getIsAlive()) {
                fishIterator.remove();
                removedFish++;
            }
        }
        return removedFish;
    }

    /**
     * Returns volume requirement in liters for fish.
     *
     * @return total liters of volume required as double
     */
    public double getFishVolumeRequirementInLitres() {
        double totalMl = 0;
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                totalMl += fish.getVolumeNeeded();
            }
        }
        return totalMl / KILO;
    }

    /**
     * Returns fish's average age in weeks.
     *
     * @return average  age in weeks as double
     */
    public double getAverageAgeInWeeks() {
        int fishAgeSum = 0;
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                fishAgeSum += fish.getAgeInWeeks();
            }
        }
        return (double) fishAgeSum / getPopulation();
    }

    /**
     * Returns average health coefficient.
     *
     * @return average health coefficient as double
     */
    public double getAverageHealthCoefficient() {
        double fishHealthCoefficientSum = 0;
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                fishHealthCoefficientSum += fish.getHealthCoefficient();
            }
        }
        return fishHealthCoefficientSum / getPopulation();
    }

    /**
     * Returns female percentage in the pool.
     *
     * @return the percentage of female as double
     */
    public double getFemalePercentage() {
        int fishFemale = 0;
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                if (fish.getIsFemale()) {
                    fishFemale++;
                }
            }
        }
        return (double) fishFemale / getPopulation();
    }

    /**
     * Returns median age of fish.
     *
     * @return median age of fish as double
     */
    public double getMedianAge() {
        ArrayList<Integer> ages = new ArrayList<>();
        for (Fish fish : fishInPool) {
            if (fish.getIsAlive()) {
                ages.add(fish.ageInWeeks);
            }
        }

        Collections.sort(ages);
        int fishCount = ages.size();
        int index = fishCount / 2;
        if (fishCount % 2 == 0) {
            return (double) (ages.get(index - 1) + ages.get(index)) / 2;
        } else {
            return ages.get(index);
        }
    }

    /**
     * Adds baby fish to the pool.
     *
     * @return number of total new fries as integer
     */
    public int spawn() {
        int totalNewFry = 0;
        ArrayList<Fish> allNewBabies = new ArrayList<>();
        for (Fish fish : fishInPool) {
            ArrayList<Fish> newBabies = fish.spawn();
            if (newBabies != null) {
                allNewBabies.addAll(newBabies);
                totalNewFry += newBabies.size();
            }
        }
        fishInPool.addAll(allNewBabies);
        return totalNewFry;
    }

    /**
     * Increments age for fish.
     *
     * @return number of dead fish as integer
     */
    public int incrementAges() {
        int died = 0;
        for (Fish fish : fishInPool) {
            fish.incrementAge();
            if (!fish.getIsAlive()) {
                died++;
            }
        }
        return died;
    }

    /**
     * Kills fish when it's too crowded.
     *
     * @return number of dead fish as integer
     */
    public int adjustForCrowding() {
        int died = 0;
        double totalLitresNeeded = getFishVolumeRequirementInLitres();
        while (volumeLitres < totalLitresNeeded) {
            Fish weakestFish = null;
            double lowestHealthCoefficient = Fish.MAXIMUM_HEALTH_COEFFICIENT;
            for (Fish fish : fishInPool) {
                if (fish.getIsAlive() && fish.getHealthCoefficient() < lowestHealthCoefficient) {
                    weakestFish = fish;
                    lowestHealthCoefficient = fish.getHealthCoefficient();
                }
            }
            if (weakestFish != null) {
                weakestFish.setIsAlive(false);
                died++;
                totalLitresNeeded -= weakestFish.getVolumeNeeded() / KILO;
            } else {
                break;
            }
        }
        return died;
    }

    /** Checks if the object passed to this method is equal to current fish.
     *
     * @param o an object to compare with current fish
     * @return boolean to show if the object is equal to current fish
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pool pool = (Pool) o;

        if (Double.compare(getVolumeLitres(), pool.getVolumeLitres()) != 0 || Double.compare(getTemperatureCelsius(),
                pool.getTemperatureCelsius()) != 0 || Double.compare(getPH(), pool.getPH()) != 0
                || Double.compare(getNutrientCoefficient(), pool.getNutrientCoefficient()) != 0
                || getIdentificationNumber() != pool.getIdentificationNumber() || !getName().equals(pool.getName())
                || !fishInPool.equals(pool.fishInPool)) {
            return false;
        }
        return randomNumberGenerator.equals(pool.randomNumberGenerator);
    }

    /** Calculates the hash code for this object using a combination of its properties.
     *
     * @return the computed hash code as integer
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getName().hashCode();
        temp = Double.doubleToLongBits(getVolumeLitres());
        result = MULTIPLIER_31 * result + (int) (temp ^ (temp >>> MULTIPLIER_32));
        temp = Double.doubleToLongBits(getTemperatureCelsius());
        result = MULTIPLIER_31 * result + (int) (temp ^ (temp >>> MULTIPLIER_32));
        temp = Double.doubleToLongBits(getPH());
        result = MULTIPLIER_31 * result + (int) (temp ^ (temp >>> MULTIPLIER_32));
        temp = Double.doubleToLongBits(getNutrientCoefficient());
        result = MULTIPLIER_31 * result + (int) (temp ^ (temp >>> MULTIPLIER_32));
        result = MULTIPLIER_31 * result + getIdentificationNumber();
        result = MULTIPLIER_31 * result + fishInPool.hashCode();
        result = MULTIPLIER_31 * result + randomNumberGenerator.hashCode();
        return result;
    }

    /** Returns a String representation of the pool.
     *
     * @return the string representing the pool
     */
    @Override
    public String toString() {
        return "Pool{name='" + name + '\'' + ", volumeLitres=" + volumeLitres + ", temperatureCelsius="
                + temperatureCelsius + ", pH=" + pH + ", nutrientCoefficient=" + nutrientCoefficient
                + ", identificationNumber=" + identificationNumber + ", fishInPool=" + fishInPool
                + ", randomNumberGenerator=" + randomNumberGenerator + '}';
    }
}
