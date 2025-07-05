package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    private static final double SHIPPING_RATE_PER_KG = 15.0; // 15 per kg

    public static double calculateShippingFee(List<Shippable> shippableItems) {
        double totalWeight = shippableItems.stream()
                .mapToDouble(item -> item.getWeight())
                .sum();
        return totalWeight * SHIPPING_RATE_PER_KG;
    }

    public static void processShipment(List<Shippable> shippableItems) {
        if (shippableItems.isEmpty()) return;

        System.out.println("** Shipment notice **");
        double totalWeight = 0;

        // Group items by name and sum their weights
        Map<String, Double> itemWeights = new HashMap<>();
        Map<String, Integer> itemCounts = new HashMap<>();

        for (Shippable item : shippableItems) {
            String name = item.getName();
            itemWeights.put(name, itemWeights.getOrDefault(name, 0.0) + item.getWeight());
            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
            String name = entry.getKey();
            int count = entry.getValue();
            double weight = itemWeights.get(name);
            System.out.println(count + "x " + name + " " + (weight * 1000) + "g");
            totalWeight += weight;
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }
}