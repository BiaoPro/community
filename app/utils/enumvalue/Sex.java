package utils.enumvalue;

/**
 * 性别
 * 
 * @author leaf
 * 
 */
public enum Sex {
	FEMALE(2, "女"), MALE(1, "男");

	private int value;
	private String sexName;

	Sex(int value, String sexName) {
		this.value = value;
		this.sexName = sexName;
	}

	/**
	 * 获得性别（男、女）
	 * 
	 * @param value
	 * @return
	 */
	public static String getSexName(int value) {
		Sex[] sexs = values();
		if (value < sexs.length) {
			return sexs[value].sexName;
		}
		return null;
	}

	/**
	 * 获取数值(2、1)
	 * 
	 * @param sexName
	 * @return
	 */
	public static int getSexValue(String sexName) {
		if ("男".equals(sexName))
			return 1;
		return 2;
	}

	public int getValue() {
		return value;
	}

	public String getSexName() {
		return sexName;
	}
}
