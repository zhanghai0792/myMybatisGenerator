package mapperFormate;

public class deleteTemplate {
 public static String deleteById_id="deleteById";
 public static String deleteByIds_id="deleteByIds";
 public static String deleteByObjects_id="deleteObjects";
 private static String deleteById_id_content="<delete id=\"%s\" parameterType=\"java.lang.Integer\">\r\ndelete from %s where id = #{id,jdbcType=INTEGER}</delete>";
 private static String deleteByIds_id_content=" <delete id=\"%s\">\r\ndelete from %s\r\nwhere id in <foreach collection=\"list\" item=\"i\" open=\"(\" close=\")\" separator=\",\">#{i}</foreach>\r\n  </delete>";
 private static String deleteByObjects_id_content=" <delete id=\"%s\">\r\ndelete from %s\r\nwhere id in <foreach collection=\"list\" item=\"i\" open=\"(\" close=\")\" separator=\",\">#{i.id,,jdbcType=INTEGER}</foreach>\r\n  </delete>";
 
 public static String getDeleteById(String tableName){
	return String.format(deleteById_id_content, deleteById_id,tableName);
}
 public static String getDeleteByIds(String tableName){
		return String.format(deleteByIds_id_content, deleteByIds_id,tableName);
	}
 public static String getdeleteByObjects(String tableName){
		return String.format(deleteByObjects_id_content, deleteByIds_id,tableName);
	}
}
