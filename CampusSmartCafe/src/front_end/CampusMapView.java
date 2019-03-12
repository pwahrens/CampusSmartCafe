package front_end;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CampusMapView extends JPanel {
	private Image campusMap;

	public CampusMapView() {
		try {
			this.campusMap = ImageIO.read(new File("CampusMapImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.campusMap = this.campusMap.getScaledInstance(580, 772, Image.SCALE_SMOOTH);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.campusMap, 0, 0, this);
	}
}
