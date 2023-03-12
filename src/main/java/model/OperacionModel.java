package model;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;

@ExcelSheet("operaciones")
public class OperacionModel {
    @ExcelCellName("operacion")
    private String operacion;
    @ExcelCellName("numero1")
    private String numero1;
    @ExcelCellName("numero2")
    private String numero2;
    @ExcelCellName("resultado")
    private String resultado;

    public String getNumero1() {
        return numero1;
    }

    public String getNumero2() {
        return numero2;
    }

    public String getResultado() {
        return resultado;
    }

    public String getOperacion() {
        return operacion;
    }
}
