package dao;

import java.util.List;


/**
 * @author 闆烽渼楠?
 * 瀵筄bject鐨凞AO鎿嶄綔
 * 鍖呭惈浜嗛€氱敤鐨勪竴浜涙柟娉?
 */

public interface BaseDao {
	/**
	 * 淇濆瓨涓€涓璞?
	 * @param object 涓€涓璞?
	 */
	public void save(Object object);

	 /**
	  * 鏇存柊涓€涓璞?
	  * @param object 涓€涓璞?
	  */
	public void update(Object object);
	
	 /**
	  * 鍒犻櫎涓€涓璞?
	  * @param object 涓€涓璞?
	  */
	public void delete(Object object);
	
	 /**
	  * 鏍规嵁鈥滃睘鎬?鍊尖€濊幏鍙栦竴涓寚瀹氱被鍨嬬殑瀵硅薄
	  * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	  * @param propertyName 瀵硅薄涓睘鎬х殑鍚嶇О
	  * @param propertyValue 瀵硅薄涓睘鎬х殑鍊?
	  * @return 涓€涓璞?
	  */
	public Object ReadSingle(String targetName,String propertyName,Object propertyValue);
	 /**
	  * 鏍规嵁鈥滃睘鎬?鍊尖€濊幏鍙栧涓寚瀹氱被鍨嬬殑瀵硅薄
	  * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	  * @param propertyName 瀵硅薄涓睘鎬х殑鍚嶇О
	  * @param propertyValue 瀵硅薄涓睘鎬х殑鍊?
	  * @return 瀵硅薄鐨勫垪琛?
	  */
	public List<Object> ReadByProperty(String targetName,String propertyName,Object propertyValue);
	 /**
	  * 鑾峰彇鎸囧畾绫诲瀷鐨勬墍鏈夊璞?
	  * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	  * @return 瀵硅薄鐨勫垪琛?
	  */
	public List<Object> ReadAll(String targetName);
	
	
	public List<Object> ReadByPropertyList(String targetName,String propertyName,Object propertyValue);

	/**
	 * 鑾峰彇鎸囧畾绫诲瀷鐨勫璞＄殑鏁伴噺銆?
	 * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	 * @return 鏁伴噺
	 */
	public Integer ReadCount(String targetName);
	 /**
	  * 鏍规嵁鈥滃睘鎬?鍊尖€濅负鏉′欢锛岃幏鍙栨寚瀹氱被鍨嬬殑瀵硅薄鐨勬暟閲忋€?
	  * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	  * @param propertyName 瀵硅薄涓睘鎬х殑鍚嶇О
	  * @param propertyValue 瀵硅薄涓睘鎬х殑鍊?
	  * @return 鏁伴噺
	  */
	public Integer ReadCountByProperty(final String targetName,String propertyName, Object value);
	/**
	 * 鑾峰彇澶氫釜鎸囧畾绫诲瀷鐨勫璞★紝鍙互闄愬畾鑾峰彇瀵硅薄鏁扮洰鐨勫灏戯紝骞朵笖鏍规嵁鐗瑰畾鐨勫睘鎬ц繘琛屾帓搴忋€?
	 * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	 * @param propertyName 瀵硅薄涓睘鎬х殑鍚嶇О锛岀敤浜庢帓搴?
	 * @param num 缁撴灉瀵硅薄鍒楄〃鐨勬渶澶ф暟鐩?
	 * @param order 鎺掑簭鏂瑰紡锛屽彲浠ラ€夋嫨鈥渁sc鈥濇垨鑰呪€渄esc鈥?
	 * @return 瀵硅薄鐨勫垪琛?
	 */
	public List<Object> ReadLimitedByOrder(String targetName, String propertyName,int num, String order);
	/**
	 * 涓や釜鍔熻兘锛?
	 * 1.鏍规嵁鈥滃睘鎬?鍊尖€濊幏鍙栧涓寚瀹氱被鍨嬬殑瀵硅薄
	 * 2.闄愬畾鑾峰彇瀵硅薄鏁扮洰鐨勫灏戯紝骞朵笖鏍规嵁鐗瑰畾鐨勫睘鎬ц繘琛屾帓搴忋€?
	 * @param targetName 瀵硅薄绫诲瀷鍚嶇О
	 * @param readpropertyName 瀵硅薄涓睘鎬х殑鍚嶇О锛岀敤浜庤幏鍙栧璞?
	 * @param readvalue 瀵硅薄涓睘鎬х殑鍊?
	 * @param orderpropertyName 瀵硅薄涓睘鎬х殑鍚嶇О锛岀敤浜庢帓搴?
	 * @param num 缁撴灉瀵硅薄鍒楄〃鐨勬渶澶ф暟鐩?
	 * @param order 鎺掑簭鏂瑰紡锛屽彲浠ラ€夋嫨鈥渁sc鈥濇垨鑰呪€渄esc鈥?
	 * @return
	 */
	public List<Object> ReadByPropertyAndLimitedByOrder(final String targetName, final String readpropertyName,
			final Object readvalue,final String orderpropertyName, final int num, final String order);
}
