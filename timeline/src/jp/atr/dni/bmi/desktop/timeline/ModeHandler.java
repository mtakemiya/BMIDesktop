/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.timeline;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Point2D;

import jp.atr.dni.bmi.desktop.timeline.model.ViewerChannel;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・國際電氣通信基礎技術研究所]</i>
 *
 * @version 2011/03/03
 */
public class ModeHandler implements KeyListener, MouseListener,
	MouseMotionListener, MouseWheelListener {

	/** a mapping of interactor names to interactors */
//	LinkedHashMap<String, Mode> modes = new LinkedHashMap<String, Interactor>();

	// interactor names
	public static String DRAG_MODE = "Drag Mode";
	public static String VIEW_MODE = "View Mode";

	/** the current interaction */
//	private Mode mode;

	/** the canvas */
	private TimelineTopComponent canvas;

	/** the previously point clicked by the mouse, in screen coordinates */
	private Point screenPickedPoint;

	/** the previously point clicked by the mouse, in virtual coordinates */
	private Point2D pickedPoint;

	/** the current mouse location, in screen coordinates */
	private Point screenCurrentPoint;

	/** the current mouse location, in virtual coordinates */
	private Point2D currentPoint;

	/** the previous mouse location, in screen coordinates */
	private Point screenPreviousPoint;

	/** the previous mouse location, in virtual coordinates */
	private Point2D previousPoint;

	/** the amout to translate the canvas */
	private double TRANSLATE_AMOUNT = 10.0;

	/** the amout to scale the canvas */
	private double SCALE_AMOUNT = 1.1;

   private ViewerChannel selectedChannel;

	/**
	 * Constructs the mode handler.
	 *
	 * @param canvas - the canvas
	 */
	public ModeHandler(TimelineTopComponent canvas) {
		this.canvas = canvas;
	}

	
	/**
	 * Sets the given object as selected and deselects the previously selected
	 * object.
	 *
	 * @param object - the object to select
	 */
	public void setSelectedObject(ViewerChannel object) {

		// deselect previous object
		if (selectedChannel != null) {
			selectedChannel.setSelected(false);
		}

		selectedChannel = object;
		if (selectedChannel != null) {
			selectedChannel.setSelected(true);
//			canvas.getMessageBar().setSelectedObject(object.getAttributeValue("name"));
		}
		else {
			selectedChannel = null;
//			canvas.getMessageBar().setSelectedObject("");
		}
	}

	/**
	 * Gets the last picked point in screen coordinates.
	 */
	public Point getScreenPickedPoint() {
		return screenPickedPoint;
	}

	/**
	 * Gets the last picked point in virtual coordinates.
	 */
	public Point2D getPickedPoint() {
		return pickedPoint;
	}

	/**
	 * Gets the previous mouse location in screen coordinates.
	 */
	public Point getScreenPreviousPoint() {
		return screenPreviousPoint;
	}

	/**
	 * Gets the previous mouse location in virtual coordinates.
	 */
	public Point2D getPreviousPoint() {
		return previousPoint;
	}

	/**
	 * Gets the current mouse location in screen coordinates.
	 */
	public Point getScreenCurrentPoint() {
		return screenCurrentPoint;
	}

	/**
	 * Gets the current mouse location in virtual coordinates.
	 */
	public Point2D getCurrentPoint() {
		return currentPoint;
	}

	/**
	 * Informs the current interactor of a key pressed events. Also checks if
	 * the key pressed is a hotkey that transforms the canvas or selects a
	 * new interaction.
	 */
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {

		// canvas operations
		case KeyEvent.VK_LEFT:
			canvas.setTranslationX(canvas.getTranslationX() + TRANSLATE_AMOUNT/canvas.getScale());
			break;
		case KeyEvent.VK_RIGHT:
			canvas.setTranslationX(canvas.getTranslationX() - TRANSLATE_AMOUNT/canvas.getScale());
			break;
		case KeyEvent.VK_UP:
			canvas.setTranslationY(canvas.getTranslationY() + TRANSLATE_AMOUNT/canvas.getScale());
			break;
		case KeyEvent.VK_DOWN:
			canvas.setTranslationY(canvas.getTranslationY() - TRANSLATE_AMOUNT/canvas.getScale());
			break;
		case KeyEvent.VK_PAGE_UP:
			canvas.setScale(canvas.getScale()*SCALE_AMOUNT);
			break;
		case KeyEvent.VK_PAGE_DOWN:
			canvas.setScale(canvas.getScale()/SCALE_AMOUNT);
			break;
		case KeyEvent.VK_V:
			canvas.zoomAll();
			break;
//		case KeyEvent.VK_G:
//			canvas.getGrid().setVisible(!canvas.getGrid().isVisible());
//			mainWindow.setGridVisible(canvas.getGrid().isVisible());
//			break;
      }
	}

   @Override
	public void mousePressed(MouseEvent me) {

      // update the virtual points
            screenCurrentPoint = me.getPoint();
            screenPickedPoint = screenCurrentPoint;
            currentPoint = canvas.getVirtualCoordinates(me.getX(), me.getY());
            pickedPoint = currentPoint;
            if (previousPoint == null) {
               screenPreviousPoint = screenCurrentPoint;
               previousPoint = currentPoint;
            }

		// update the virtual points
//		screenCurrentPoint = arg0.getPoint();
//		screenPickedPoint = screenCurrentPoint;
//		currentPoint = canvas.getVirtualCoordinates(arg0.getX(), arg0.getY());
//		pickedPoint = currentPoint;
//		if (previousPoint == null) {
//			screenPreviousPoint = screenCurrentPoint;
//			previousPoint = currentPoint;
//		}
   }

	/**
    *
    * @param arg0
    */
	public void mouseReleased(MouseEvent arg0) {
		//TODO:
	}

	/**
	 * Updates the current and previous points
	 */
	public void mouseMoved(MouseEvent me) {
       previousPoint = me.getPoint();

//		screenCurrentPoint = arg0.getPoint();
//		currentPoint = canvas.getVirtualCoordinates(arg0.getX(), arg0.getY());
//		if (previousPoint == null) {
//			screenPreviousPoint = screenCurrentPoint;
//			previousPoint = currentPoint;
//		}
//
//		screenPreviousPoint = arg0.getPoint();
//		previousPoint = canvas.getVirtualCoordinates(arg0.getX(), arg0.getY());
	}

	/**
	 * Updates the current and previous points. If the left button is
	 * performing the drag, then the current interactor is informed of the
	 * event. If the right button is performing the drag, then the canvas is
	 * panned and interactors are not informed of the event.
	 */
	public void mouseDragged(MouseEvent me) {

      Point2D currentPoint = me.getPoint();// getVirtualCoordinates(arg0.getX(),
				// arg0.getY());
            // getVirtualCoordinates(arg0.getX(), arg0.getY());

            // left button performs an action or drags the canvas
      if ((me.getModifiers() & MouseEvent.BUTTON1_MASK) > 0) {
         double dx = currentPoint.getX() - previousPoint.getX();
         double dy = previousPoint.getY() - currentPoint.getY();
					//            System.out.println("dx: " + dx + "\tdy: " + dy);

//         System.out.println(currentPoint.getY());
         canvas.setTranslationX(canvas.getTranslationX()+dx);
         canvas.setTranslationY(canvas.getTranslationY()+dy);

         previousPoint = me.getPoint();
      }

      //maybe the following is better:

//		screenCurrentPoint = arg0.getPoint();
//		currentPoint = canvas.getVirtualCoordinates(arg0.getX(), arg0.getY());
//
//			double dx = currentPoint.getX() - previousPoint.getX();
//			double dy = currentPoint.getY() - previousPoint.getY();
//			canvas.setTranslationX(canvas.getTranslationX() + dx);
//			canvas.setTranslationY(canvas.getTranslationY() + dy);
//
//
//		screenPreviousPoint = arg0.getPoint();
//		previousPoint = canvas.getVirtualCoordinates(arg0.getX(), arg0.getY());
	}

	/**
	 * The mouse wheel controls the current zoom factor. Iteractors do not
	 * receive mouse wheel events.
	 */
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		if (arg0.getWheelRotation() < 0) {
			canvas.setScale(canvas.getScale()*SCALE_AMOUNT);
		}
		else if (arg0.getWheelRotation() > 0) {
			canvas.setScale(canvas.getScale()/SCALE_AMOUNT);
		}
	}

	/**
	 * Not implemented.
	 */
	public void mouseClicked(MouseEvent me) {
//      System.out.println(me.getYOnScreen() + "\t" + me.getY() + "\t"+canvas.getScale()*(-canvas.getScale() / (canvas.getHeight()*.5)));
	}

	/**
	 * Not implemented.
	 */
	public void keyTyped(KeyEvent arg0) {}

	/**
	 * Not implemented.
	 */
	public void mouseEntered(MouseEvent arg0) {}

	/**
	 * Not implemented.
	 */
	public void mouseExited(MouseEvent arg0) {}

   @Override
   public void keyReleased(KeyEvent ke) {
      //throw new UnsupportedOperationException("Not supported yet.");
   }
}