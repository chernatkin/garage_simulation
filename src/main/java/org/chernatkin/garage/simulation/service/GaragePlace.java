package org.chernatkin.garage.simulation.service;

public class GaragePlace {
    
    private int placeId;
    
    private int floor;
    
    private int placeNumber;
    
    private Long carId;

    public GaragePlace() {
    }

    public GaragePlace(int placeId, int floor, int placeNumber, Long carId) {
        this.placeId = placeId;
        this.floor = floor;
        this.placeNumber = placeNumber;
        this.carId = carId;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((carId == null) ? 0 : carId.hashCode());
        result = prime * result + floor;
        result = prime * result + placeId;
        result = prime * result + placeNumber;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GaragePlace other = (GaragePlace) obj;
        if (carId == null) {
            if (other.carId != null)
                return false;
        } else if (!carId.equals(other.carId))
            return false;
        if (floor != other.floor)
            return false;
        if (placeId != other.placeId)
            return false;
        if (placeNumber != other.placeNumber)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GaragePlace [placeId=" + placeId + ", floor=" + floor
                + ", placeNumber=" + placeNumber + ", carId=" + carId + "]";
    }
}
