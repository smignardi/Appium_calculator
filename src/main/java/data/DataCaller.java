package data;

import model.OperacionModel;

public class DataCaller {

    private final ExcelReader excelReader = new ExcelReader();
    private final MapParser mapParser = new MapParser();

    public OperacionModel getOperacionSuma(){
        return mapParser.getOperacionMap().get("suma");
    }
}