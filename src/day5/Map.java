package day5;

public class Map {
    private long sourceStart;
    private long sourceEnd;
    private long increment;

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
