package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	public static JMenuBar menubar = new JMenuBar();
	
	public MainFrame(MainDisplay panel) {
		
		JMenuBar menubar = setupMenu();
		
		add(panel, BorderLayout.CENTER);
		setJMenuBar(menubar);
		
		setPreferredSize(new Dimension(800, 160));
		setVisible(true);
		pack();
		setTitle("Payroll - Alpha");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	private JMenuBar setupMenu() {
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		
		fileMenu.add(exit);
		menubar.add(fileMenu);
		
		return menubar;
	}

	public void addMenuHandler(ActionListener listener) {
		for (int i = 0; i < menubar.getMenuCount(); ++i) {
			for (JMenuItem item : getMenuItems(menubar.getMenu(i))) {
				item.addActionListener(listener);
			}
		}
		
		
	}

	private static List<JMenuItem> getMenuItems(JMenuItem item) {
		List<JMenuItem> items = new ArrayList<>();

		if (item instanceof JMenu) {
			JMenu menu = (JMenu) item;
			for (int i = 0; i < menu.getItemCount(); ++i) {
				items.addAll(getMenuItems(menu.getItem(i)));
			}
		} else {
			items.add(item);
		}

		return items;
	}
	
	

}