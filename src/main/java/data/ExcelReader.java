package data;

import com.poiji.bind.Poiji;
import model.OperacionModel;

import java.io.File;
import java.util.List;

public class ExcelReader {
    private final String excelPath = "src/test/resources/data/testData.xlsx";

    public List<OperacionModel> getSumaList(){
        return Poiji.fromExcel(new File(excelPath), OperacionModel.class);
    }
}
