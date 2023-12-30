package test;
import java.util.Arrays;
import java.util.Random;

import java.util.Arrays;
import java.util.Random;

public class LevelGenerator {
    static final int LEVEL_SIZE = 10; // 關卡大小
    static final int MAX_GENERATIONS = 100; // 最大世代數
    static final double MUTATION_RATE = 0.1; // 變異率

    static final int PLAYER_LEVEL = 5; // 角色等級
    static final int IMMUNITY = 8; // 角色免疫力

    // 生成初始關卡
    static int[] generateInitialLevel() {
        int[] level = new int[LEVEL_SIZE];
        Random rand = new Random();
        for (int i = 0; i < LEVEL_SIZE; i++) {
            level[i] = rand.nextInt(IMMUNITY) < PLAYER_LEVEL ? 0 : 1; // 角色等級和免疫力影響障礙物生成
        }
        return level;
    }

    // 計算關卡的適應度
    static int calculateFitness(int[] level) {
        int fitness = 0;
        for (int cell : level) {
            if (cell == 0) {
                fitness++;
            }
        }
        return fitness;
    }

    // 交配生成新的關卡
    static int[] crossover(int[] parent1, int[] parent2) {
        Random rand = new Random();
        int crossoverPoint = rand.nextInt(LEVEL_SIZE);
        int[] child = new int[LEVEL_SIZE];
        System.arraycopy(parent1, 0, child, 0, crossoverPoint);
        System.arraycopy(parent2, crossoverPoint, child, crossoverPoint, LEVEL_SIZE - crossoverPoint);
        return child;
    }

    // 變異
    static void mutate(int[] level) {
        Random rand = new Random();
        for (int i = 0; i < LEVEL_SIZE; i++) {
            if (rand.nextDouble() < MUTATION_RATE) {
                level[i] = (level[i] + 1) % 2; // 變換 0 和 1
            }
        }
    }

    // 主要的基因演算法過程
    static int[] runGeneticAlgorithm() {
        int[] level = generateInitialLevel();
        int generation = 0;

        while (generation < MAX_GENERATIONS) {
            int[] newLevel = crossover(level, generateInitialLevel());
            mutate(newLevel);

            if (calculateFitness(newLevel) > calculateFitness(level)) {
                level = newLevel;
            }

            generation++;
        }

        return level;
    }

    public static void main(String[] args) {
        int[] finalLevel = runGeneticAlgorithm();
        System.out.println("Final Level: " + Arrays.toString(finalLevel));
    }
}
