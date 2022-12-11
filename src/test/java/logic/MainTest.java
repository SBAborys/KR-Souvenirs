package logic;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MainTest {
    List<Data> manufacturers;
    List<Data> souvenirs;

    @BeforeMethod
    public void setUp() {
        manufacturers = ParseJsonFile.readDataFromFile(Manufactures.class);
        souvenirs = ParseJsonFile.readDataFromFile(Souvenirs.class);
    }

    @Test
    public void testPrintManufactures(){
        ListOfData manufacturesList = new ListOfData(manufacturers);
        System.out.println(manufacturesList);
    }

    @Test
    public void testPrintSouvenirs(){
        ListOfData souvenirsList = new ListOfData(souvenirs);
        System.out.println(souvenirsList);
    }

    @Test
    public void testGetAllSouvenirsByManufacture(){
        String manufactureName = "A";
        List<Data> newData = Queries.getAllSouvenirsByManufacture(souvenirs, manufactureName);
        System.out.println(new ListOfData(newData));
        assertThat(newData).isEqualTo(List.of(souvenirs.get(0), souvenirs.get(6), souvenirs.get(12)));
    }

    @Test
    public void testGetAllSouvenirsBeCountry(){
        String countryName = "UKRAINE";

        List<Data> newData = Queries.getAllSouvenirsBeCountry(manufacturers, souvenirs, countryName);
        System.out.println(new ListOfData(newData));

        assertThat(newData).isEqualTo(List.of(souvenirs.get(4), souvenirs.get(10)));
    }

    @Test
    public void testGetAllManufacturersWithSouvenirsPriseLessThenSpecified(){
        double prise = 3;

        List<Data> newData = Queries.getAllManufacturersWithSouvenirsPriseLessThenSpecified(manufacturers, souvenirs, prise);
        System.out.println(new ListOfData(newData));

        assertThat(newData).isEqualTo(List.of(manufacturers.get(0), manufacturers.get(1), manufacturers.get(3)));
    }

    @Test
    public void testGetManufacturersAndTheirSouvenirs(){
        Map<Data, List<Data>> result = Queries.getManufacturersAndTheirSouvenirs(manufacturers, souvenirs);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Data, List<Data>> dataListEntry : result.entrySet()) {
            stringBuilder.append(dataListEntry.getKey()).append("\n");

            List<Data> souvenir = dataListEntry.getValue();
            for (Data s : souvenir) {
                stringBuilder.append("  -->").append(s).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }

    @Test
    public void testGetManufacturerBySouvenirAndYear(){
        String souvenirName = "Pen";
        int year = 2022;

        List<Data> result = Queries.getManufacturerBySouvenirAndYear(souvenirs, manufacturers, souvenirName, year);

        System.out.println(new ListOfData(result));

        assertThat(result).isEqualTo(List.of(manufacturers.get(1)));
    }

    @Test
    public void testGetAllSouvenirsGroupedByYear(){
        Map<Integer, List<Data>> result = Queries.getAllSouvenirsGroupedByYear(souvenirs);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<Data>> dataListEntry : result.entrySet()) {
            stringBuilder.append(dataListEntry.getKey()).append("\n");

            for (List<Data> souvenir : result.values()) {
                stringBuilder.append("   -->").append(souvenir).append("\n");
            }
        }
        System.out.println(stringBuilder);
    }
}