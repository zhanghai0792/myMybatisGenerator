package tableField;

import java.util.HashMap;
import java.util.Map;

public class typeMapper {
public static Map<String,String> typesMap=new HashMap<String,String>(0);
static{
	typesMap.put("VARCHAR", "String");
	typesMap.put("CHAR", "String");
	typesMap.put("BIT", "Boolean");
	typesMap.put("INT", "Integer");
	typesMap.put("DOUBLE", "Double");
	typesMap.put("DOUBLE", "Date");
}
}
