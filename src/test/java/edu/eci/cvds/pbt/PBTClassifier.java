package edu.eci.cvds.pbt;

import java.util.*;

public class PBTClassifier {
TreeMap<String, Integer> casesAccumulator = new TreeMap<>();
int count = 0;
String testName;

public PBTClassifier(String testName) {
    this.testName = testName;
}

public PBTClassifier declare(String accum) {
    casesAccumulator.putIfAbsent(accum, 0);
    return this;
}

public PBTClassifier collect(String accum) {
    declare(accum);
    casesAccumulator.put(accum, casesAccumulator.get(accum) + 1);
    count++;
    return this;
}

public void results() {
    double n = count;
    System.out.println();
    System.out.printf("Summary for [%s]%n", testName);
    System.out.println("Test count - " + n);
    System.out.println("-------");
    for (Map.Entry<String, Integer> elem : casesAccumulator.entrySet()) {
        System.out.printf("%s: %d - %.2f%%%n", elem.getKey(), elem.getValue(), 100 * elem.getValue() / n);
    }
}
}