package entity.ticket;


public class Ticket {
    private Place departureData;

    private Place arrivalData;
    private double cost;

    private int ID;

    public Ticket(Place departureData, Place arrivalData, double cost) {
        this.departureData = departureData;
        this.arrivalData = arrivalData;
        this.cost = cost;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Place getDepartureData() {
        return departureData;
    }

    public Place getArrivalData() {
        return arrivalData;
    }



    public double getCost() {
        return cost;
    }

}