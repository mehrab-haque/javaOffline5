import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static WareHouse wareHouse;
    private static int MAX_COLOURS=3;

    private static void mainMenu(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Main Menu");
        System.out.println("(1) Search Cars");
        System.out.println("(2) Add Cars");
        System.out.println("(3) Delete Cars");
        System.out.println("(4) Exit System");
        try{
            switch (scanner.nextInt()){
                case 1:
                    carSearchingMenu();
                    break;
                case 2:
                    addCarMenu();
                    break;
                case 3:
                    deleteCarMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default :
                    System.out.println("Invalid input, please enter the correct number");
                    mainMenu();
            }
        }catch (Exception e){
            System.out.println("Invalid input, please enter the correct number");
            scanner.nextLine();
            mainMenu();
        }
    }

    private static void carSearchingMenu(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Car Searching Options");
        System.out.println("(1) By Registration Number");
        System.out.println("(2) By Car Make and Car Model");
        System.out.println("(3) Back to Main Menu");
        try{
            switch (scanner.nextInt()){
                case 1:
                    searchCarByRegistrationNumber();
                    break;
                case 2:
                    searchCarByMakeandModel();
                    break;
                case 3:
                    mainMenu();
                    break;
                default :
                    System.out.println("Invalid input, please enter the correct number");
                    carSearchingMenu();
            }
        }catch (Exception e){
            System.out.println("Invalid input, please enter the correct number");
            scanner.nextLine();
            carSearchingMenu();
        }
    }

    private static void addCarMenu(){
        String registrationNumber=askRegistrationNumber();
        int yearMade=askYearMade();
        List<String> colours=askColours();
        String carMake=askCarMake();
        String carModel=askCarModel();
        int price =askPrice();

        Car car=new Car(
                registrationNumber,
                yearMade,
                colours,
                carMake,
                carModel,
                price
        );
        wareHouse.addCar(car);

        System.out.println("Car added successfully in the warehouse and database updated");
        mainMenu();
    }

    public static void deleteCarMenu(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the registration number of the car you want to delete (Or just press enter to return to the main menu):");
        try{
            String value=scanner.nextLine();
            if(value.trim().length()>0){
                if(wareHouse.hasRegistrationNumber(value)) {
                    wareHouse.deleteCarByRegistrationNumber(value);
                    System.out.println("Car removed from the warehouse successfully and database updated.");
                    mainMenu();
                }
                else {
                    System.out.println("There is no car in the warehouse with this registration number.");
                    deleteCarMenu();
                }
            }else
                mainMenu();
        }catch (Exception e){
            System.out.println("Invalid input, please try again.");
            scanner.nextLine();
            deleteCarMenu();
        }
    }

    public static String askRegistrationNumber(){
        System.out.println("Enter a unique registration number of your car (e.g. 8RT2WT):");
        Scanner scanner=new Scanner(System.in);
        String value;
        try{
            value=scanner.nextLine();
            if(!wareHouse.hasRegistrationNumber(value) && value.trim().length()>0)
                return value;
            else{
                System.out.println("Error : registration number must be a non-empty string and unique.");
                return askRegistrationNumber();
            }
        }catch(Exception e){
            System.out.println("Please try again.");
            scanner.nextLine();
            return askRegistrationNumber();
        }
    }

    public static int askYearMade(){
        System.out.println("Enter the year your car was built (e.g. 2013):");
        Scanner scanner=new Scanner(System.in);
        int value;
        try{
            value=scanner.nextInt();
            if(value>0)
                return value;
            else{
                System.out.println("Error : invalid year.");
                return askYearMade();
            }
        }catch(Exception e){
            System.out.println("Invalid Input");
            scanner.nextLine();
            return askYearMade();
        }
    }

    public static List<String> askColours(){
        List<String> colours=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        for(int i=0;i<MAX_COLOURS;i++){
            System.out.println(i>0?"Enter another colour (if any, press enter if you don't have any other color):":"Enter a colour of the car (e.g. white) :");
            try{
                String colour=scanner.nextLine();
                if(colour.trim().length()==0){
                    if(i>0)
                        return colours;
                    else{
                        System.out.println("You have to enter at least one colour.");
                        return askColours();
                    }
                }else
                    colours.add(colour);
            }catch (Exception e){
                System.out.println("Please try again");
                scanner.nextLine();
                return askColours();
            }
        }
        return colours;
    }

    public static String askCarMake(){
        System.out.println("Enter the car make (e.g. Toyota):");
        Scanner scanner=new Scanner(System.in);
        String value;
        try{
            value=scanner.nextLine();
            if(value.trim().length()>0)
                return value;
            else{
                System.out.println("Error : car make must be a non-empty string.");
                return askCarMake();
            }
        }catch(Exception e){
            System.out.println("Please try again.");
            scanner.nextLine();
            return askCarMake();
        }
    }

    public static String askCarModel(){
        System.out.println("Enter the car model (e.g. Corolla):");
        Scanner scanner=new Scanner(System.in);
        String value;
        try{
            value=scanner.nextLine();
            if(value.trim().length()>0)
                return value;
            else{
                System.out.println("Error : car model must be a non-empty string.");
                return askCarModel();
            }
        }catch(Exception e){
            System.out.println("Please try again.");
            scanner.nextLine();
            return askCarModel();
        }
    }

    public static int askPrice(){
        System.out.println("Enter the price of the car in dollars (e.g. 15000 for $15000):");
        Scanner scanner=new Scanner(System.in);
        int value;
        try{
            value=scanner.nextInt();
            if(value>0)
                return value;
            else{
                System.out.println("Error : invalid price.");
                return askPrice();
            }
        }catch(Exception e){
            System.out.println("Invalid Input");
            scanner.nextLine();
            return askPrice();
        }
    }

    private static void searchCarByRegistrationNumber(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the registration number of the car (or press enter to return to car searching menu) :");
        try{
            String value=scanner.nextLine();
            if(value.trim().length()>0){
                if(wareHouse.hasRegistrationNumber(value)) {
                    System.out.println("Car found, details is below:");
                    System.out.println(wareHouse.getCarByRegistrationNumber(value));
                    System.out.println("Press enter to return back to car searching menu.");
                    scanner.nextLine();
                    carSearchingMenu();
                }
                else {
                    System.out.println("No such car with this Registration Number");
                    searchCarByRegistrationNumber();
                }
            }else
                carSearchingMenu();
        }catch (Exception e){
            System.out.println("Invalid input, please try again.");
            scanner.nextLine();
            searchCarByRegistrationNumber();
        }
    }

    private static void searchCarByMakeandModel(){
        Scanner scanner=new Scanner(System.in);
        String make=askCarMake();
        String model=askCarModel();
        List<Car> queryResult=wareHouse.searchByMakeAndModel(make,model);
        if(queryResult.size()>0){
            System.out.println(queryResult.size()+" result(s) found:");
            for(Car car : queryResult)
                System.out.println(car);
            System.out.println("Press enter to return back to car searching menu.");
            scanner.nextLine();
            carSearchingMenu();
        }else{
            System.out.println("No such car with this Car Make and Car Model");
            System.out.println("Press enter to return back to car searching menu.");
            scanner.nextLine();
            carSearchingMenu();
        }
    }

    public static void main(String[] args) {
        wareHouse=WareHouse.getInstance();
        mainMenu();
    }
}
