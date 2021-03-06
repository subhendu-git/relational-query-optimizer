package edu.buffalo.cse562.operator.utils.scan;

import edu.buffalo.cse562.data.Datum;
import edu.buffalo.cse562.schema.ColumnSchema;
import jdbm.PrimaryStoreMap;

import java.util.List;

import static edu.buffalo.cse562.data.DatumUtils.getDatumOfTypeFromValue;

public class ScanUtils {

    public static Datum[] getDataForRelevantColumnIndexes(Long rowId, List<Integer> relevantColumnIndexes, PrimaryStoreMap<Long, String> storeMap, ColumnSchema[] schema) {
        if (relevantColumnIndexes != null) {
            return getDatumsForRelevantColumnPositions(
                    storeMap.get(rowId),
                    relevantColumnIndexes,
                    schema);
        } else {
            return getDatumsForAllColumnPositions(
                    storeMap.get(rowId),
                    schema);
        }
    }

    public static Datum[] getDatumsForRelevantColumnPositions(String line, List<Integer> relevantColumnIndexes, ColumnSchema[] schema) {
        String[] cells = line.split("\\|");
        Datum[] tuple = new Datum[relevantColumnIndexes.size()];

        for (int i = 0; i < relevantColumnIndexes.size(); i++) {
            Integer index = relevantColumnIndexes.get(i);

            tuple[i] = getDatumOfTypeFromValue(schema[i].getType(), cells[index]);
        }
        return tuple;
    }

    public static Datum[] getDatumsForAllColumnPositions(String line, ColumnSchema[] schema) {
        String[] cells = line.split("\\|");
        Datum[] tuple = new Datum[schema.length];

        for (int i = 0; i < schema.length; i++) {
            tuple[i] = getDatumOfTypeFromValue(schema[i].getType(), cells[i]);
        }
        return tuple;
    }

}
