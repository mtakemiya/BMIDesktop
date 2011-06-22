/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.jogltest;

import com.jogamp.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//jp.atr.jogltest//JoglTest//EN",
autostore = false)
@TopComponent.Description(preferredID = "JoglTestTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "leftSlidingSide", openAtStartup = true)
@ActionID(category = "Window", id = "jp.atr.jogltest.JoglTestTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_JoglTestAction",
preferredID = "JoglTestTopComponent")
public final class JoglTestTopComponent extends TopComponent implements GLEventListener {

   private double theta = 0;
   /** the scale of the map */
   private double scale = 1.0;
   /** the amount to translate the canvas */
   private double TRANSLATE_AMOUNT = 10.0;
   /** the amount to scale the canvas */
   private double SCALE_AMOUNT = 1.1;
   /** the x translation of the map */
   private double translationX = 0;
   /** the y translation of the map */
   private double translationY = 0;
   /** the transform for virtual to screen coordinates */
   private AffineTransform transform = new AffineTransform();
   /** the transform for screen to virtual coordinates */
   private AffineTransform inverseTransform = new AffineTransform();
   private Point2D previousPoint;
   /** the current size of the panel */
   private Dimension size;
   private GLCanvas canvas;

   public JoglTestTopComponent() {
      initGL();
      setName(NbBundle.getMessage(JoglTestTopComponent.class, "CTL_JoglTestTopComponent"));
      setToolTipText(NbBundle.getMessage(JoglTestTopComponent.class, "HINT_JoglTestTopComponent"));

   }

   private void initGL() {

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
      layout.setVerticalGroup(
              layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

      GLProfile.initSingleton(true);
      GLProfile glp = GLProfile.getDefault();
      GLCapabilities caps = new GLCapabilities(glp);
      canvas = new GLCanvas(caps);

//      frame = new JFrame("Swing Window Test");
//      canvas.setSize(new Dimension(300, 300));
//      frame.setSize(canvas.getWidth(), canvas.getHeight());
      this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      this.add(canvas);

      buildTransforms();

      canvas.addMouseWheelListener(new MouseWheelListener() {

         @Override
         public void mouseWheelMoved(MouseWheelEvent arg0) {
            if (arg0.getWheelRotation() < 0) {
               setScale(getScale() * SCALE_AMOUNT);
            } else if (arg0.getWheelRotation() > 0) {
               setScale(getScale() / SCALE_AMOUNT);
            }
         }
      });

      canvas.addMouseListener(new MouseListener() {

         @Override
         public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub
         }

         @Override
         public void mousePressed(MouseEvent arg0) {
            previousPoint = arg0.getPoint();
         }

         @Override
         public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub
         }

         @Override
         public void mouseEntered(MouseEvent arg0) {
            // TODO Auto-generated method stub
         }

         @Override
         public void mouseClicked(MouseEvent arg0) {
            System.out.println("x: " + arg0.getX());
            System.out.println("y: " + arg0.getY());
            System.out.println("sx: " + arg0.getXOnScreen());
            System.out.println("sy: " + arg0.getYOnScreen());
         }
      });

      canvas.addMouseMotionListener(new MouseMotionListener() {

         @Override
         public void mouseMoved(MouseEvent arg0) {
            previousPoint = arg0.getPoint();// getVirtualCoordinates(arg0.getX(), arg0.getY());
         }

         @Override
         public void mouseDragged(MouseEvent arg0) {
            Point2D currentPoint = arg0.getPoint();// getVirtualCoordinates(arg0.getX(),
            // arg0.getY());

            double dx = currentPoint.getX() - previousPoint.getX();
            double dy = previousPoint.getY() - currentPoint.getY();
            System.out.println("dx: " + dx + "\tdy: " + dy);
            translationX += dx;
            translationY += dy;

            previousPoint = arg0.getPoint();// getVirtualCoordinates(arg0.getX(), arg0.getY());

//            buildTransforms();
         }
      });

      // add a resize listener
      canvas.addComponentListener(new ComponentAdapter() {

         public void componentResized(ComponentEvent arg0) {

            // resize about the center of the scene
            if (size != null) {
               Dimension newSize = canvas.getSize();
               // translationX += (newSize.width - size.width) / 2.0;
               // translationY += (newSize.height - size.height) / 2.0;
            }

            // update the view transforms when the canvas is resized
//            buildTransforms();
            size = canvas.getSize();
         }
      });

      this.setVisible(true);

      // by default, an AWT Frame doesn't do anything when you click
      // the close button; this bit of code will terminate the program when
      // the window is asked to close

      canvas.addGLEventListener(this);

      Animator animator = new Animator(canvas);
      animator.add(canvas);
      animator.start();

   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 400, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 300, Short.MAX_VALUE)
      );
   }// </editor-fold>//GEN-END:initComponents

   // Variables declaration - do not modify//GEN-BEGIN:variables
   // End of variables declaration//GEN-END:variables
   @Override
   public void componentOpened() {
      // TODO add custom code on component opening
   }

   @Override
   public void componentClosed() {
      // TODO add custom code on component closing
   }

   void writeProperties(java.util.Properties p) {
      // better to version settings since initial version as advocated at
      // http://wiki.apidesign.org/wiki/PropertyFiles
      p.setProperty("version", "1.0");
      // TODO store your settings
   }

   void readProperties(java.util.Properties p) {
      String version = p.getProperty("version");
      // TODO read your settings according to their version
   }

   /**
    * Rebuilds the view transform and the inverse view transforms.
    */
   private void buildTransforms() {

      double width = canvas.getWidth();
      double height = canvas.getHeight();
      transform = new AffineTransform(1, 0, 0, 1, 0, 0);
      transform.translate(0.5 * width, 0.5 * height);
      transform.scale(scale, scale);
      transform.translate(translationX - width / 2.0, translationY - height / 2.0);

      try {
         inverseTransform = transform.createInverse();
      } catch (Exception e) {
      }
   }

   @Override
   public void init(GLAutoDrawable drawable) {
      drawable.getGL().setSwapInterval(1);
   }

   @Override
   public void dispose(GLAutoDrawable drawable) {
      throw new UnsupportedOperationException("Not supported yet.");
   }

   @Override
   public void display(GLAutoDrawable drawable) {
      update();
      render(drawable);
   }

   @Override
   public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
   }

   private void render(GLAutoDrawable drawable) {

      GL2 gl = drawable.getGL().getGL2();

      gl.glClear(GL.GL_COLOR_BUFFER_BIT);
      // draw a triangle filling the window
      // gl.glBegin(GL.GL_TRIANGLES);
      // gl.glColor3f(1, 0, 0);
      // gl.glVertex2d(-c, -c);
      // gl.glColor3f(0, 1, 0);
      // gl.glVertex2d(0, c);
      // gl.glColor3f(0, 0, 1);
      // gl.glVertex2d(s, -s);
      // gl.glEnd();
      gl.glColor3f(1, 0, 0);
      // gl.glEnable(GL2.GL_LINE_STIPPLE);
      // gl.glLineStipple(1, (short)0x00FF);

      gl.glLoadIdentity();
      gl.glTranslated(translationX / (canvas.getWidth() * .5), translationY / (canvas.getHeight() * .5), 0);
      gl.glScaled(scale, scale, 0);

      gl.glBegin(GL.GL_LINES);

      int max = 30000;
      for (int i = 0; i < max; i++) {
         float[] coords = new float[4];

         coords[0] = -10;
         coords[1] = (float) Math.random() * 10;
         coords[2] = i + 10 / max;
         coords[3] = (float) -Math.random() * 10;

         float[] coords2 = new float[4];

         // inverseTransform.transform(coords, 0, coords2, 0, coords.length / 2);

         for (int index = 0; index < 4; index += 2) {
            gl.glVertex2f(coords[index], coords[index + 1]);
            gl.glVertex2f(coords[(index + 2) % coords.length], coords[(index + 3) % coords.length]);
         }
      }
      gl.glEnd();
   }

   private void update() {
      theta += 0.01;
   }

   /**
    * Gets the scale scale.
    */
   public double getScale() {
      return scale;
   }

   /**
    * Sets the scale and rebuilds the affine transforms.
    */
   public void setScale(double scale) {
      if (scale < 0.0001 || scale > 1000000) {
         return;
      }

      this.scale = scale;
      buildTransforms();
   }

   /**
    * Converts screen coordinates to virtual coordinates.
    * 
    * @param x - the x component of the screen coordinate
    * @param y - the y component of the screen coordinate
    * @return - the point in virtual coordinates.
    */
   public Point2D getVirtualCoordinates(double x, double y) {
      return inverseTransform.transform(new Point2D.Double(x, y), null);
   }

   /**
    * Converts virtual coordinates to screen coordinates.
    * 
    * @param x - the x component of the virtual coordinate
    * @param y - the y component of the virtual coordinate
    * @return - the point in screen coordinates.
    */
   public Point2D getScreenCoordinates(double x, double y) {
      return transform.transform(new Point2D.Double(x, y), null);
   }
}
