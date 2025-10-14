import HotelsPackage.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main {
    
    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    Scanner scanner;
    public void run() {
        try {
            scanner = new Scanner(System.in);
            
            System.out.println("Добавляем отели: ");
            Scanner fileScanner = new Scanner(new File("hotel.txt"));
            Hotels hotels = new Hotels();
            while(fileScanner.hasNextLine()){
                String hotelLine = fileScanner.nextLine().trim();
                String[] hotelStrings = hotelLine.split(", ");
                if (hotelStrings.length >= 3) {
                    Hotels.Hotel hotel = hotels.new Hotel();
                    hotel.name = hotelStrings[0];
                    hotel.stars = Integer.parseInt(hotelStrings[1]);
                    hotel.city = hotelStrings[2];
                    System.out.println(hotel.city + " " + hotel.name + " " + hotel.stars);
                    hotels.addHotel(hotel);
                }
            }
            fileScanner.close();
            
            System.out.print("\nВведите город для поиска отелей: ");
            String searchCity = scanner.nextLine();
            System.out.println("\nОтели в " + searchCity + ":");
            ArrayList<Hotels.Hotel> Hotels = hotels.getHotelsByCity(searchCity);
            if(!Hotels.isEmpty()){
                for (Hotels.Hotel hotel : Hotels) {
                    System.out.println(hotel.city + " " + hotel.name + " " + hotel.stars);
                }
            }
            else{
                System.out.println("Нету таких отелей");
            }
            
            System.out.print("\nВведите название отеля для поиска городов: ");
            String searchHotelName = scanner.nextLine();
            System.out.println("\nГорода с отелем " + searchHotelName + ":");
            ArrayList<String> Cities = hotels.getCitiesByHotelName(searchHotelName);
            if(!Cities.isEmpty()){
                for (String city : Cities) {
                    System.out.println(city);
                }
            }
            else
                System.out.println("Нету таких городов");
                
            System.out.println("\nВсе отели отсортированные по алфавиту:");
            ArrayList<Hotels.Hotel> sortedHotels = hotels.getHotelsSortedByCities();
            for (Hotels.Hotel hotel : sortedHotels) {
                System.out.println(hotel.city + " " + hotel.name + " " + hotel.stars);
            }
            
            scanner.close();
        } 
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}