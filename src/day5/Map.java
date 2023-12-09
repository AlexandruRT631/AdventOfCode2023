package day5;

public class Map {
    private final long sourceStart;
    private final long sourceEnd;
    private final long increment;

    public Map(long sourceStart, long sourceEnd, long increment) {
        this.sourceStart = sourceStart;
        this.sourceEnd = sourceEnd;
        this.increment = increment;
    }

    public long getSourceStart() {
        return sourceStart;
    }

    public long getSourceEnd() {
        return sourceEnd;
    }

    public long getIncrement() {
        return increment;
    }
}
