package tableField;

public class fieldDefine {
 private String fieldName;
 private String jdbcType;
 private String javaType;
 private int length;
 private int dotNumber;
 private boolean isNull;
 private boolean isPrimaryKey;//是否为主键

 
 
public String getFieldName() {
	return fieldName;
}
public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
}
public String getJdbcType() {
	return jdbcType;
}
public void setJdbcType(String jdbcType) {
	this.jdbcType = jdbcType;
}
/*public String getJavaType() {
	return javaType;
}
public void setJavaType(String javaType) {
	this.javaType = javaType;
}*/
public int getLength() {
	return length;
}
public void setLength(int length) {
	this.length = length;
}
public int getDotNumber() {
	return dotNumber;
}
public void setDotNumber(int dotNumber) {
	this.dotNumber = dotNumber;
}
public boolean isNull() {
	return isNull;
}
public void setNull(boolean isNull) {
	this.isNull = isNull;
}
public fieldDefine() {
	super();
	// TODO Auto-generated constructor stub
}
public fieldDefine(String fieldName, String jdbcType, int length, int dotNumber, boolean isNull) {
	super();
	this.fieldName = fieldName;
	this.jdbcType = jdbcType;
	this.length = length;
	this.dotNumber = dotNumber;
	this.isNull = isNull;
}
public fieldDefine(String fieldName, String jdbcType,String javaType, int length, int dotNumber, int isNull) {
	super();
	this.fieldName = fieldName;
	this.jdbcType = jdbcType.toUpperCase();
	this.javaType=javaType;
	this.length = length;
	this.dotNumber = dotNumber;
	this.isNull =(isNull==1);
}
public boolean isPrimaryKey() {
	return isPrimaryKey;
}
public void setPrimaryKey(boolean isPrimaryKey) {
	this.isPrimaryKey = isPrimaryKey;
}
public fieldDefine(String fieldName, String jdbcType,String javaType, int length, int dotNumber, int isNull, boolean isPrimaryKey) {
	super();
	this.fieldName = fieldName;
	this.jdbcType = jdbcType;
	this.length = length;
	this.dotNumber = dotNumber;
	this.isNull =(isNull==1);
	this.isPrimaryKey = isPrimaryKey;
}
 
}
