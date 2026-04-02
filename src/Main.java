import java.util.*;

// Service class
class Service {
    private String name;
    private int cost;

    public Service(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (₹" + cost + ")";
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        //  Reservation IDs (simulating UC6 output)
        String reservation1 = "RES-101";
        String reservation2 = "RES-102";

        //  Map: Reservation → List of Services
        Map<String, List<Service>> serviceMap = new HashMap<>();

        // 🔹 Guest selects services for reservation1
        List<Service> servicesForRes1 = new ArrayList<>();
        servicesForRes1.add(new Service("Breakfast", 500));
        servicesForRes1.add(new Service("Airport Pickup", 1200));

        // 🔹 Guest selects services for reservation2
        List<Service> servicesForRes2 = new ArrayList<>();
        servicesForRes2.add(new Service("Extra Bed", 800));

        // Store in map
        serviceMap.put(reservation1, servicesForRes1);
        serviceMap.put(reservation2, servicesForRes2);

        //  Display services + calculate cost
        for (String resId : serviceMap.keySet()) {

            System.out.println("Reservation ID: " + resId);

            List<Service> services = serviceMap.get(resId);

            int totalCost = 0;

            for (Service s : services) {
                System.out.println(" - " + s);
                totalCost += s.getCost();
            }

            System.out.println("Total Add-On Cost: ₹" + totalCost + "\n");
        }
    }
}