package acceptance.session;

import net.serenitybdd.core.Serenity;

import java.util.List;

public class TableData {

    public void setColumnsNames(List<String> columnsNames) {
        Serenity.getCurrentSession().put("columns_name", columnsNames);
    }

    public List<String> getColumnsNames() {
        return (List<String>) Serenity.getCurrentSession().get("columns_name");
    }
}
