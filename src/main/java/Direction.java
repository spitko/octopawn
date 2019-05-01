public enum Direction {
    NORTH('\u2191', 2, true),
    NORTH_WEST('\u2196', 0, true),
    NORTH_EAST('\u2197', 4, true),
    SOUTH('\u2193', 2, false),
    SOUTH_EAST('\u2198', 4, false),
    SOUTH_WEST('\u2199', 0, false);

    private final char arrow;
    private final int offset;
    private final boolean up;

    Direction(char arrow, int offset, boolean up) {
        this.arrow = arrow;
        this.offset = offset;
        this.up = up;
    }

    public static Direction getDirection(Move m) {
        if (m.x1 == m.x2) {
            if (m.y1 > m.y2) {
                return NORTH;
            }
            return SOUTH;
        }
        if (m.x1 > m.x2) {
            if (m.y1 > m.y2) return NORTH_WEST;
            return SOUTH_WEST;
        }
        if (m.y1 < m.y2) return SOUTH_EAST;
        return NORTH_EAST;
    }

    public char getArrow() {
        return arrow;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isUp() {
        return up;
    }
}
