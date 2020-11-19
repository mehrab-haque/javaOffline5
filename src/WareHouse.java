import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WareHouse {

    private static final String INPUT_FILE_NAME = "cars.txt";
    private static final String OUTPUT_FILE_NAME = "cars.txt";

    private static WareHouse instance=null;
    private List<Car> cars;

    public static synchronized WareHouse getInstance(){
        if(instance==null)
            instance=new WareHouse();
        return instance;
    }
    private WareHouse(){
        cars=new ArrayList<>();
        fetch();
    }

    private void fetch(){
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] features=line.split(",");
                List<String> colours=new ArrayList<>();
                for(int i=2;i<5;i++)
                    if (features[i].length() > 0)
                        colours.add(features[i]);
                Car car=new Car(
                        features[0],
                        Integer.parseInt(features[1]),
                        colours,
                        features[5],
                        features[6],
                        Integer.parseInt(features[7])
                );
                cars.add(car);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void publish(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
            for(Car car : cars)
                bw.write(car+"\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasRegistrationNumber(String string){
        for(Car car : cars){
            if(car.getRegistrationNumber().equalsIgnoreCase(string))
                return true;
        }
        return false;
    }

    public void addCar(Car car){
        cars.add(car);
        publish();
    }

    public void deleteCarByRegistrationNumber(String string){
        try{
            for(Car car:cars)
                if (car.getRegistrationNumber().equalsIgnoreCase(string))
                    cars.remove(car);
        }catch(Exception e) {

        }
        publish();
    }

    public Car getCarByRegistrationNumber(String string){
        for(Car car : cars){
            if(car.getRegistrationNumber().equalsIgnoreCase(string))
                return car;
        }
        return null;
    }

    public List<Car> searchByMakeAndModel(String make,String model){
        List<Car> list=new ArrayList<>();
        for(Car car : cars)
            if (car.getCarMake().equalsIgnoreCase(make) && (model.equalsIgnoreCase("any") || car.getCarModel().equalsIgnoreCase(model)))
                list.add(car);
        return list;

    }

}
