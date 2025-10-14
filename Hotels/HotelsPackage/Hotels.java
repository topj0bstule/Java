package HotelsPackage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Hotels {
    public ArrayList<Hotel> hotels;
    public Hotels(){
        hotels = new ArrayList<>();
    }
    public void addHotel(Hotel hotel){
        hotels.add(hotel);
    }
    
    public ArrayList<Hotel> getHotelsByCity(String city){
        ArrayList<Hotel> oneCityHotels = new ArrayList<>();
        for (Hotel hotel: hotels) {
            if(city.equals(hotel.city)) oneCityHotels.add(hotel);
        }
        return oneCityHotels;
    }

    public ArrayList<String> getCitiesByHotelName(String name){
        ArrayList<String> oneNameHotels = new ArrayList<>();
        for (Hotel hotel: hotels) {
            if(name.equals(hotel.name)) oneNameHotels.add(hotel.city);
        }
        return oneNameHotels;
    }
    public ArrayList<Hotel> getHotelsSortedByCities(){
        ArrayList<Hotel> sortedHotels = new ArrayList<>(hotels);
        Collections.sort(sortedHotels, new Comparator<Hotel>() {
            @Override
            public int compare(Hotel h1, Hotel h2) {
                int nameComparison = h1.city.compareToIgnoreCase(h2.city);
                if (nameComparison == 0) {
                    return h2.stars.compareTo(h1.stars);
                }
                return nameComparison;
            }
        });
        return sortedHotels;
    }

    public class Hotel{
        public String city;
        public String name;
        public Integer stars;
        public Hotel(){
            this.name = "";
            this.stars = 0;
            this.city = "";
        }
        public Hotel(String city_, String name_, Integer stars_){
            this.city = city_;
            this.name = name_;
            this.stars = stars_;
        }
    }
}