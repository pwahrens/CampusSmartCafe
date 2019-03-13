package front_end;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import back_end.Cafe;
import back_end.Food;
import back_end.FoodProvider;
import back_end.Meal;
import back_end.VendingMachine;

public class CampusMapView extends JPanel implements MouseListener {
	private Image campusMap;
	private FoodProviderView[] foodProviderViews;
	private Ellipse2D[] markers;

	public CampusMapView() {
		try {
			this.campusMap = ImageIO.read(new File("CampusMapImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.addMouseListener(this);

		this.campusMap = this.campusMap.getScaledInstance(580, 772, Image.SCALE_SMOOTH);
		this.loadFoodProviders();
	}

	public void loadFoodProviders() {
		this.foodProviderViews = new FoodProviderView[4];

		ArrayList<Food> menu0 = new ArrayList<Food>();
		menu0.add(new Meal("Tacos", 400, 5));
		menu0.add(new Meal("Burrito", 800, 8));
		menu0.add(new Meal("Taco Salad", 300, 4));
		FoodProvider foodProvider0 = new Cafe("La Parilla", menu0, new Point(50, 222));
		foodProviderViews[0] = new FoodProviderView(foodProvider0);

		ArrayList<Food> menu1 = new ArrayList<Food>();
		menu1.add(new Meal("Pizza", 200, 3));
		menu1.add(new Meal("Burger", 400, 10));
		menu1.add(new Meal("Caesar Salad", 175, 7));
		FoodProvider foodProvider1 = new Cafe("540", menu1, new Point(300, 300));
		foodProviderViews[1] = new FoodProviderView(foodProvider1);

		ArrayList<Food> menu2 = new ArrayList<Food>();
		menu2.add(new Meal("Lays", 200, 2));
		menu2.add(new Meal("Kettle Chips", 150, 3));
		menu2.add(new Meal("Oreos", 160, 2));
		FoodProvider foodProvider2 = new VendingMachine("Snacks", menu2, new Point(60, 400));
		foodProviderViews[2] = new FoodProviderView(foodProvider2);

		ArrayList<Food> menu3 = new ArrayList<Food>();
		menu3.add(new Meal("Coca Cola", 150, 2));
		menu3.add(new Meal("La Croix", 0, 4));
		menu3.add(new Meal("Pepsi Free", 0, 3));
		FoodProvider foodProvider3 = new VendingMachine("Sodas", menu3, new Point(200, 400));
		foodProviderViews[3] = new FoodProviderView(foodProvider3);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.campusMap, 0, 0, this);
		this.drawMarkers((Graphics2D) g);
	}

	private void drawMarkers(Graphics2D g) {
		this.markers = new Ellipse2D[this.foodProviderViews.length];

		for (int i = 0; i < this.foodProviderViews.length; ++i) {
			FoodProvider foodProvider = this.foodProviderViews[i].getFoodProvider();
			Point location = foodProvider.getLocation();

			this.markers[i] = new Ellipse2D.Double(location.getX(), location.getY(), 20, 20);

			if (foodProvider instanceof Cafe) {
				g.setPaint(Color.blue);
			} else {
				g.setPaint(Color.yellow);
			}

			g.fill(this.markers[i]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < this.foodProviderViews.length; ++i) {
			if (this.markers[i].contains(e.getX(), e.getY())) {
				System.out.println(i);
				this.add(this.foodProviderViews[i]);
			} else {
				this.remove(this.foodProviderViews[i]);
			}
		}

		this.validate();
		this.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		return;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;
	}
}
