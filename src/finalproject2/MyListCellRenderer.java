package finalproject2;

import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

class MyListCellRenderer extends DefaultListCellRenderer {
	/**
	 * 列表字體
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (renderer instanceof JLabel) {
			JLabel label = (JLabel) renderer;
			// Modify font size here (change "14" to your desired size)
			label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 30));
		}
		return renderer;
	}
}