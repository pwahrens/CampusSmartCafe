package front_end;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JPanel;

public class BarGraphView extends JPanel{

	private Map<Color, Integer> bars = new LinkedHashMap<Color, Integer>();
	private Color barColors[] = {Color.LIGHT_GRAY,Color.RED, Color.BLUE, Color.GREEN}; ;
	
    public BarGraphView(int[] calData) {
    		for (int i=0; i<barColors.length; ++i) {
    			if(calData[i]<0)
    				calData[i]=0;
                bars.put(barColors[i],calData[i]);
            }
    }

    protected void paintComponent(Graphics gp) {
            super.paintComponent(gp);
            // Cast the graphics objects to Graphics2D
            Graphics2D g = (Graphics2D) gp;
            // determine longest bar
            int max = Integer.MIN_VALUE;
            for (Integer value : bars.values()) {
                    max = Math.max(max, value);
            }
            // paint bars

            int width = (getWidth() / bars.size()) - 2;
            int x = 1;
            for (Color color : bars.keySet()) {
                    int value = bars.get(color);
                    int height = (int) ((getHeight() - 5) * ((double) value / max));
                    g.setColor(color);
                    g.fillRect(x, getHeight() - height, width, height);
                    g.setColor(Color.black);
                    g.drawRect(x, getHeight() - height, width, height);
                    x += (width + 2);
            }
    }

    public Dimension getPreferredSize() {
            return new Dimension(bars.size() * 10 + 2, 50);
    }
}
