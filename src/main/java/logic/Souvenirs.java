package logic;

import java.time.LocalDate;
import java.util.Date;

public class Souvenirs extends Data {
    private String name;
    private String manufacturerName;
    private LocalDate releaseDate;
    private double price;

    public Souvenirs(String name, String manufacturerName, LocalDate releaseDate, double price) {
        this.name = name;
        this.manufacturerName = manufacturerName;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Souvenirs{" +
                "name='" + name + '\'' +
                ", manufacturerName='" + manufacturerName + '\'' +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                '}';
    }
}
