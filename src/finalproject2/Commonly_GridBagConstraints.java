package finalproject2;

import java.awt.GridBagConstraints;

public interface Commonly_GridBagConstraints {
	/*
	 *填滿fill
	 */
	final int NONE=GridBagConstraints.NONE;//不填滿
	final int HORIZONTAL=GridBagConstraints.HORIZONTAL;//填滿水平
	final int VERTICAL=GridBagConstraints.VERTICAL;//填滿垂直
	final int BOTH=GridBagConstraints.BOTH;//填滿
	
	/*
	 * 對齊方式anchor
	 */
	final int NORTH=GridBagConstraints.NORTH;//靠上
	final int SOUTH=GridBagConstraints.SOUTH;//靠下
	final int WEST=GridBagConstraints.WEST;//靠左
	final int EAST=GridBagConstraints.EAST;//靠右
	
	final int NORTHEAST=GridBagConstraints.NORTHEAST;//右上角
	final int NORTHWEST=GridBagConstraints.NORTHWEST;//左上角
	final int SOUTHEAST=GridBagConstraints.SOUTHEAST;//右下角
	final int SOUTHWEST=GridBagConstraints.SOUTHWEST;//左下角
	final int CENTER=GridBagConstraints.CENTER;//置中對齊
}
