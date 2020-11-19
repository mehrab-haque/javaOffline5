import java.util.List;

public class Car {
    private static int MAX_COLOURS=3;
    private String registrationNumber;
    private int yearMade;
    private List<String> colours;
    private String carMake;
    private String carModel;
    private int price;

    public Car(String registrationNumber, int yearMade, List<String> colours, String carMake, String carModel, int price) {
        this.registrationNumber = registrationNumber;
        this.yearMade = yearMade;
        this.colours = colours;
        this.carMake = carMake;
        this.carModel = carModel;
        this.price = price;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getYearMade() {
        return yearMade;
    }

    public void setYearMade(int yearMade) {
        this.yearMade = yearMade;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String output=registrationNumber+","+yearMade+",";
        for(int i=0;i<colours.size();i++)
            output+=colours.get(i)+",";
        for(int i=colours.size();i<MAX_COLOURS;i++)
            output+=",";
        output+=carMake+","+carModel+","+price;
        return output;
    }
}
