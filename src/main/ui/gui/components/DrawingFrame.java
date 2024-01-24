package ui.gui.components;

import ui.gui.components.ObjectRectangle;
import ui.gui.components.SelectedCell;

import java.awt.*;

public class DrawingFrame extends Canvas {
    /* CLASS LEVEL COMMENT
    this class is responsible for all the rendering of the Cells and the selected one at the moment
     */

    private ObjectRectangle[][] rectArray;
    private SelectedCell selCell;

    // constructor
    public DrawingFrame() {
        this.rectArray = new ObjectRectangle[0][0];
        this.selCell = null;
    }

    // EFFECT: an overriden function from canvas that is the main draw function
    //  (all rendering is done with graphics g and is handled here)
    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < rectArray.length; i++) {
            for (int j = 0; j < rectArray[0].length; j++) {
                ObjectRectangle temp = rectArray[i][j];
                g.setColor(temp.getColor());
                g.fillRect(temp.getX(), temp.getY(), temp.getWidth(), temp.getHeight());
            }
        }
        if (selCell != null) {
            java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
            g2.setStroke(new java.awt.BasicStroke(3));
            g2.setColor(selCell.getColor());
            g2.drawRect(selCell.getX(), selCell.getY(), selCell.getWidth(), selCell.getHeight());
        }
    }

    // REQUIRES: a valid ObjectRectangle array
    // EFFECT: sets the rectangles array with a new array (this is done to update the current grid)
    public void setRectArray(ObjectRectangle[][] or) {
        this.rectArray = or;
    }

    // REQUIRES; a valid SelectedCEll or null
    // EFFECT: sets the current selected cell or sets it to null and doesn't display a selected outline
    public void setSelCell(SelectedCell sc) {
        this.selCell = sc;
    }

}
