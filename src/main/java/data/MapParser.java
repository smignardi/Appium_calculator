package data;

import model.OperacionModel;

import java.util.HashMap;
import java.util.Map;

public class MapParser {

    final ExcelReader excelReader = new ExcelReader();

    public Map<String, OperacionModel> getOperacionMap() {
        final var map = new HashMap<String, OperacionModel>();
        final var OperacionList = excelReader.getSumaList();
        for (var element : OperacionList) {
            map.put(element.getOperacion(), element);
        }
        return map;
    }
}
