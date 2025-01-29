package service;

public class CosineSimilarity {
    public double calculate(WordVector vectorA, WordVector vectorB) {
        int numerator = calculateDotProduct(vectorA.getVector(), vectorB.getVector());
        double denominator = calculateMagnitude(vectorA.getVector()) * calculateMagnitude(vectorB.getVector());
        return numerator / denominator;

    }

    private static int calculateDotProduct(int[] A, int[] B) {
        int dotProduct = 0;
        for (int i = 0; i < A.length; i++) {
            dotProduct += A[i] * B[i];
        }
        return dotProduct;
    }

    private static double calculateMagnitude(int[] vector) {
        double sum = 0;
        for (int value : vector) {
            sum += value * value;
        }
        return Math.sqrt(sum);
    }
}
