package study;

import java.util.EnumMap;
import java.util.Map;

public enum Phase {
    SOLID, LIQUID, GAS;

    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID), BOIL(LIQUID, GAS),
        CONDENSE(GAS, LIQUID), SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase source;
        private final Phase destination;

        Transition(Phase source, Phase destination) {
            this.source = source;
            this.destination = destination;
        }

        private static final Map<Phase, Map<Phase, Transition>> m =
                new EnumMap<Phase, Map<Phase, Transition>>(Phase.class);
        static {
            for (Phase p : Phase.values())
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            for (Transition trans : Transition.values())
                m.get(trans.source).put(trans.destination, trans);
        }

        public static Transition from(Phase source, Phase destination) {
            return m.get(source).get(destination);
        }
    }

    public static void main(String[] args) {
        for (Phase source : Phase.values())
            for (Phase destination : Phase.values())
                if (source != destination)
                    System.out.printf("%s to %s : %s %n", source, destination,
                            Transition.from(source, destination));
    }
}
//http://rangken.github.io/blog/2015/effective-java-5/