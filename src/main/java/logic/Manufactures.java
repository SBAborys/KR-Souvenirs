package logic;

public class Manufactures extends Data implements Comparable{
    private String name;
    private String country;

    public Manufactures(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Manufactures{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (this.getClass() != o.getClass()) return getClass().getName().compareTo(o.getClass().getName());
        Manufactures man = (Manufactures) o;
        int v = name.compareTo(man.name);
        if (v != 0) return v;
        v = country.compareTo(man.country);
        return v;
    }
}
