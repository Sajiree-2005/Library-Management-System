package model;
import java.sql.Timestamp;

public class Audit {
    public int auditId, recordId;
    public String tableName, operationType, oldData, newData;
    public Timestamp operationTime;
}