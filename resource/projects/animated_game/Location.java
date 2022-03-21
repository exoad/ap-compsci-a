public class Location {
    private Location() {}

    public enum LOCATION_CORNER {
        CORNER_TL,
        CORNER_TR,
        CORNER_BL,
        CORNER_BR,
        CORNER_NT,
    }

    public enum LOCATION_EDGE {
        EDGE_L,
        EDGE_R,
        EDGE_T,
        EDGE_B,
        EDGE_N,
    }

    public enum LOCATION_CENTER {
        CENTER_CM,
        CENTER_NT;
    }
}