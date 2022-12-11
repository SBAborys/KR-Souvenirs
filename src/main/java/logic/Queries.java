package logic;

import java.util.*;
import java.util.stream.Collectors;

public class Queries {

    /*
        Висести сувеніри заданого виробника
     */
    public static List<Data> getAllSouvenirsByManufacture(List<Data> souvenirs, String manufactureName) {

        List<Data> result = souvenirs.stream()
                .filter(x -> manufactureName.equals(Queries.getManufacturerName(x))).toList();

        return result;
    }

    private static String getManufacturerName(Data x) {
        if(Manufactures.class == x.getClass()) return x.getName();
        if(Souvenirs.class == x.getClass()) return ((Souvenirs) x).getManufacturerName();
        return null;
    }

    /*
        Висести сувеніри заданої країни
     */
    public static List<Data> getAllSouvenirsBeCountry(List<Data> manufactures, List<Data> souvenirs, String countryName) {
        List<Data> result = new ArrayList<>();

        for (Data manufacture : manufactures) {
            Manufactures manufactures1 = (Manufactures) manufacture;
            if(manufactures1.getCountry().equals(countryName)){
                for (Data souvenir : souvenirs) {
                    Souvenirs souvenirs1 = (Souvenirs) souvenir;
                    if(souvenirs1.getManufacturerName().equals(manufactures1.getName())){
                        result.add(souvenir);
                    }
                }
            }
        }

        return result;
    }

    /*
        Висести виробників ціни сувенірів чиїх менше заданої
     */
    public static List<Data> getAllManufacturersWithSouvenirsPriseLessThenSpecified(List<Data> manufacturers, List<Data> souvenirs, double prise) {
        HashSet<String> manufacturerWithSouvenirPriseLessThenSpecified = new HashSet<>();

        for (Data souvenir : souvenirs) {
            Souvenirs souvenirs1 = (Souvenirs) souvenir;
            if(souvenirs1.getPrice() < prise) manufacturerWithSouvenirPriseLessThenSpecified.add(souvenirs1.getManufacturerName());
        }

        List<Data> result = manufacturers.stream()
                .filter(x -> manufacturerWithSouvenirPriseLessThenSpecified.contains(getManufacturerName(x)))
                .collect(Collectors.toList());

        return result;
    }

    /*
        Висести виробників та їх сувеніри
     */

    public static Map<Data, List<Data>> getManufacturersAndTheirSouvenirs(List<Data> manufacturers, List<Data> souvenirs) {

        Map<Data, List<Data>> result = souvenirs.stream()
                .collect(Collectors.groupingBy(x -> Queries.getByName(manufacturers, Queries.getManufacturerName(x))));

        return result;
    }

    private static Data getByName(List<Data> list, String name) {
        return list.stream().filter(x -> name.equals(Queries.getName(x))).toList().get(0);
    }

    private static String getName(Data x) {
        if (Manufactures.class == x.getClass() || Souvenirs.class == x.getClass()) return x.getName();
        return null;
    }

    /*
        Вивести інформацію про виробника за вказаним сувеніром і роком
     */
    public static List<Data> getManufacturerBySouvenirAndYear(List<Data> souvenirs, List<Data> manufacturers, String souvenirName, int year) {
        HashSet<String> manufacturerBasedOnSouvenirAdnYear = new HashSet<>();

        for (Data souvenir : souvenirs) {
            Souvenirs souvenirs1 = (Souvenirs) souvenir;
            if(souvenirs1.getName().equals(souvenirName) && souvenirs1.getReleaseDate().getYear() == year){
                manufacturerBasedOnSouvenirAdnYear.add(souvenirs1.getManufacturerName());
            }
        }

        List<Data> result = manufacturers.stream()
                .filter(x -> manufacturerBasedOnSouvenirAdnYear.contains(getManufacturerName(x))).toList();
        return result;
    }

    /**
        Для кожного року вивести список сувенірів
     */
    public static Map<Integer, List<Data>> getAllSouvenirsGroupedByYear(List<Data> souvenirs) {
        Map<Integer, List<Data>> result = souvenirs.stream()
                .sorted(Comparator.comparing(x -> ((Souvenirs) x).getReleaseDate()))
                .collect(Collectors.groupingBy(x -> ((Souvenirs) x).getReleaseDate().getYear()));
        return result;
    }
}
