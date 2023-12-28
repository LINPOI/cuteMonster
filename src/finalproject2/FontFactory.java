package finalproject2;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class FontFactory {
	
	public static Font getFont(String style, int size) {
		switch (style) {
		case "Arial":
			return new Font("Arial", Font.PLAIN, size);
		case "Times New Roman":
			return new Font("Times New Roman", Font.BOLD, size);
		// 可以添加更多字体类型...
		default:
			return new Font(Font.SANS_SERIF, Font.PLAIN, size);
		}
	}

	public static Font commonFont(int i) {
		switch (i) {
		case 1:
			return new Font("標楷體", Font.PLAIN, 20);
		case 2:
			return new Font("Arial", Font.PLAIN, 20);
		case 3:
			return new Font("新細明體", Font.PLAIN, 20);
		default:
			return new Font("微軟正黑體", Font.PLAIN, 20);
		}
	}
	public static Font commonFont(int i,int size) {
		switch (i) {
		case 1:
			return new Font("標楷體", Font.PLAIN, size);
		case 2:
			return new Font("Arial", Font.PLAIN, size);
		case 3:
			return new Font("新細明體", Font.PLAIN, size);
		default:
			return new Font("微軟正黑體", Font.PLAIN, size);
		}
	}
	public static void main(String[] args) {
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		for (String fontName : fontNames) {
			System.out.println(fontName);
		}
	}
}
