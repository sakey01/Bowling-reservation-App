package pitch.example;

public class LaneItem {
    private String lanes, location, openings;
    private int image;
    private boolean isReserved;

    public LaneItem(String lanes, String location, String openings, int image) {
        this.lanes = lanes;
        this.location = location;
        this.openings = openings;
        this.image = image;
        this.isReserved = false;
    }

    public String getLanes() {
        return lanes;
    }

    public String getLocation() {
        return location;
    }

    public String getOpenings() {
        return openings;
    }

    public int getImage() {
        return image;
    }

    public boolean isReserved() {
        return isReserved;
    }
    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
