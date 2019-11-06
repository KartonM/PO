package agh.cs.lab2Home;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public String toString() {
        switch (this) {
            case NORTH:
                return "Północ";
            case SOUTH:
                return "Południe";
            case EAST:
                return "Wschód";
            case WEST:
                return "Zachód";
        }
        return null;
    }

    public MapDirection next() {
        int thisIntValue = (this.ordinal() + 1) % MapDirection.values().length;
        return MapDirection.values()[thisIntValue];
    }

    public MapDirection previous() {
        int thisIntValue = ((this.ordinal() - 1) + MapDirection.values().length) % MapDirection.values().length;
        return MapDirection.values()[thisIntValue];
    }

    public Vector2d toUnitVector() {
        switch (this) {
            case NORTH:
                return new Vector2d(0,1);
            case EAST:
                return new Vector2d(1,0);
            case SOUTH:
                return new Vector2d(0,-1);
            case WEST:
                return new Vector2d(-1,0);
        }
        return null;
    }
}
