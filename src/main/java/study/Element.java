package study;

import java.util.HashMap;
import java.util.Map;

/**
 * Attaching Values to Java Enum , Element4.java in GitHub
 */
public enum Element {
    H("Hydrogen", 1, 1.008f),
    HE("Helium", 2, 4.0026f),
    C("Carbon", 6, 12.011f),
    O("Oxygen", 8, 15.999f);

   /* Locating Java Enum Values : iterate until find one
    public static Element valueOfLabel(String label) {
        for (Element e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return null;
    }
    */

    //maps cache labels and associated instances :  (when values all unique)

    private static final Map<String, Element> BY_LABEL = new HashMap<>();
    private static final Map<Integer, Element> BY_ATOMIC_NUMBER = new HashMap<>();
    private static final Map<Float, Element> BY_ATOMIC_WEIGHT = new HashMap<>();
    //populate caches
    static {
        for (Element e : values()) {
            BY_LABEL.put(e.label, e);
            BY_ATOMIC_NUMBER.put(e.atomicNumber, e);
            BY_ATOMIC_WEIGHT.put(e.atomicWeight, e);
        }
    }

    private final String label; //아래 private생성자에서 변수 final 인스턴스 : 위 static도 해결
    private final int atomicNumber;
    private final float atomicWeight;

    private Element(String label, int atomicNumber, float atomicWeight) {
        this.label = label;
        this.atomicNumber = atomicNumber;
        this.atomicWeight = atomicWeight;
    }

    public static Element valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Element valueOfAtomicNumber(int number) {
        return BY_ATOMIC_NUMBER.get(number);
    }

    public static Element valueOfAtomicWeight(float weight) {
        return BY_ATOMIC_WEIGHT.get(weight);
    }

    @Override
    public String toString() {
        return this.label;
    }
}
