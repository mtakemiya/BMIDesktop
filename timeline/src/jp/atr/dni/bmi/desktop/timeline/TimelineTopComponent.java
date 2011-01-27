/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.timeline;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.awt.TextRenderer;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JOptionPane;
import jp.atr.dni.bmi.desktop.model.GeneralFileInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogData;
import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;
import jp.atr.dni.bmi.desktop.neuroshareutils.ElemType;
import jp.atr.dni.bmi.desktop.neuroshareutils.Entity;
import jp.atr.dni.bmi.desktop.neuroshareutils.NSReader;
import jp.atr.dni.bmi.desktop.neuroshareutils.NeuroshareFile;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//jp.atr.dni.bmi.desktop.timeline//timeline//EN",
autostore = false)
public final class TimelineTopComponent extends TopComponent implements GLEventListener {

   /** the amout to translate the canvas */
	private double TRANSLATE_AMOUNT = 10.0;

	/** the amout to scale the canvas */
	private double SCALE_AMOUNT = 1.1;
   
   private double theta = 0;

   private double s = 0;

   /** the x translation of the map */
	private double translationX = 0;

	/** the y translation of the map */
	private double translationY = 0;

	/** the scale of the map */
	private double scale = 1.0;

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


   /** the transform for virtual to screen coordinates */
   private AffineTransform transform = new AffineTransform();

   /** the transform for screen to virtual coordinates */
   private AffineTransform inverseTransform = new AffineTransform();

   private GLCanvas glCanvas;

   private TextRenderer renderer;

   private GeneralFileInfo fileInfo;

   private static TimelineTopComponent instance;
   /** path to the icon used by the component and its open action */
   static final String ICON_PATH = "jp/atr/dni/bmi/desktop/timeline/graphPrev.png";
   private static final String PREFERRED_ID = "timelineTopComponent";

   public TimelineTopComponent() {
//      initComponents();
      initGL();
      setName(NbBundle.getMessage(TimelineTopComponent.class, "CTL_timelineTopComponent"));
      setToolTipText(NbBundle.getMessage(TimelineTopComponent.class, "HINT_timelineTopComponent"));
      setIcon(ImageUtilities.loadImage(ICON_PATH, true));

   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();

      org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TimelineTopComponent.class, "TimelineTopComponent.jLabel1.text")); // NOI18N

      org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TimelineTopComponent.class, "TimelineTopComponent.jLabel3.text")); // NOI18N

      jList1.setModel(new javax.swing.AbstractListModel() {
         String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
         public int getSize() { return strings.length; }
         public Object getElementAt(int i) { return strings[i]; }
      });
      jScrollPane1.setViewportView(jList1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
            .addGap(10, 10, 10))
         .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(jLabel3))
      );
   }// </editor-fold>//GEN-END:initComponents

   /**
    * code to initialize the openGL timeline
    */
   private void initGL() {
      GLProfile.initSingleton();
      GLProfile glp = GLProfile.getDefault();
      GLCapabilities caps = new GLCapabilities(glp);

      renderer = new TextRenderer(new Font("SansSerif", Font.BOLD, 12));
              
      glCanvas = new GLCanvas(caps);

      glCanvas.addGLEventListener(this);

      glCanvas.addMouseListener(new MouseListener() {

         @Override
         public void mouseClicked(MouseEvent me) {
         }

         @Override
         public void mousePressed(MouseEvent me) {
            // update the virtual points
            screenCurrentPoint = me.getPoint();
            screenPickedPoint = screenCurrentPoint;
            currentPoint = getVirtualCoordinates(me.getX(), me.getY());
            pickedPoint = currentPoint;
            if (previousPoint == null) {
               screenPreviousPoint = screenCurrentPoint;
               previousPoint = currentPoint;
            }
         }

         @Override
         public void mouseReleased(MouseEvent me) {
         }

         @Override
         public void mouseEntered(MouseEvent me) { 
         }

         @Override
         public void mouseExited(MouseEvent me) {
         }
      });

      glCanvas.addMouseMotionListener(new MouseMotionListener() {

         @Override
         public void mouseDragged(MouseEvent me) {
            Point2D currentPoint = me.getPoint();// getVirtualCoordinates(arg0.getX(),
                                                   // arg0.getY());

            double dx = currentPoint.getX() - previousPoint.getX();
            double dy = previousPoint.getY() - currentPoint.getY();
//            System.out.println("dx: " + dx + "\tdy: " + dy);
            translationX += dx;
            translationY += dy;

            previousPoint = me.getPoint();// getVirtualCoordinates(arg0.getX(), arg0.getY());

            buildTransforms();
         }

         @Override
         public void mouseMoved(MouseEvent me) {
            previousPoint = me.getPoint();// getVirtualCoordinates(arg0.getX(), arg0.getY());
         }
      });

      glCanvas.addMouseWheelListener(new MouseWheelListener() {

         @Override
         public void mouseWheelMoved(MouseWheelEvent mwe) {
            if (mwe.getWheelRotation() < 0) {
               setScale(getScale()*SCALE_AMOUNT);
            }
            else if (mwe.getWheelRotation() > 0) {
               setScale(getScale()/SCALE_AMOUNT);
            }
         }
      });


      Animator animator = new Animator(glCanvas);
      animator.add(glCanvas);
      animator.start();

      jLabel3 = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      jList1 = new javax.swing.JList();

      org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TimelineTopComponent.class, "TimelineTopComponent.jLabel3.text")); // NOI18N

      jList1.setModel(new javax.swing.AbstractListModel() {
         String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
         public int getSize() { return strings.length; }
         public Object getElementAt(int i) { return strings[i]; }
      });
      jScrollPane1.setViewportView(jList1);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGap(1, 1, 1)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(glCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, 1304, Short.MAX_VALUE)
            .addGap(10, 10, 10))
         .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1368, Short.MAX_VALUE)
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(glCanvas, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(jLabel3))
      );

      //TODO:add a resize listener
//      this.addComponentListener(new ComponentAdapter() {
//			public void componentResized(ComponentEvent arg0) {
//
//				// resize about the center of the scene
//				if (size != null) {
//					Dimension newSize = Canvas.this.getSize();
//					translationX += (newSize.width - size.width)/2.0;
//					translationY += (newSize.height - size.height)/2.0;
//				}
//
//				// update the view transforms when the canvas is resized
//				buildTransforms();
//				size = Canvas.this.getSize();
//			}
//		});

      Lookup.Result<GeneralFileInfo> fileInfos = Utilities.actionsGlobalContext().lookupResult(GeneralFileInfo.class);
      fileInfos.allItems();  // THIS IS IMPORTANT
      fileInfos.addLookupListener(new LookupListener(){
         @Override
         public void resultChanged(LookupEvent e){
            System.out.println("change");

            GeneralFileInfo obj = Utilities.actionsGlobalContext().lookup(GeneralFileInfo.class);
           
            if (obj!=null){
               fileInfo = obj;
            }
          }}
      );
   }

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JList jList1;
   private javax.swing.JScrollPane jScrollPane1;
   // End of variables declaration//GEN-END:variables
   /**
    * Gets default instance. Do not use directly: reserved for *.settings files only,
    * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
    * To obtain the singleton instance, use {@link #findInstance}.
    */
   public static synchronized TimelineTopComponent getDefault() {
      if (instance == null) {
         instance = new TimelineTopComponent();
      }
      return instance;
   }

   /**
    * Obtain the TimelineTopComponent instance. Never call {@link #getDefault} directly!
    */
   public static synchronized TimelineTopComponent findInstance() {
      TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
      if (win == null) {
         Logger.getLogger(TimelineTopComponent.class.getName()).warning(
                 "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
         return getDefault();
      }
      if (win instanceof TimelineTopComponent) {
         return (TimelineTopComponent) win;
      }
      Logger.getLogger(TimelineTopComponent.class.getName()).warning(
              "There seem to be multiple components with the '" + PREFERRED_ID
              + "' ID. That is a potential source of errors and unexpected behavior.");
      return getDefault();
   }

   @Override
   public int getPersistenceType() {
      return TopComponent.PERSISTENCE_ALWAYS;
   }

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

   Object readProperties(java.util.Properties p) {
      if (instance == null) {
         instance = this;
      }
      instance.readPropertiesImpl(p);
      return instance;
   }

   private void readPropertiesImpl(java.util.Properties p) {
      String version = p.getProperty("version");
      // TODO read your settings according to their version
   }

   @Override
   protected String preferredID() {
      return PREFERRED_ID;
   }

   @Override
   public void display(GLAutoDrawable drawable) {
      update();
      render(drawable);
   }

   @Override
   public void dispose(GLAutoDrawable arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void init(GLAutoDrawable drawable) {
      drawable.getGL().setSwapInterval(1);
   }

   @Override
   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
      // TODO Auto-generated method stub

   }

   private void update() {
      theta += 0.05;
      s = Math.sin(theta);
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

   /**
	 * Rebuilds the view transform and the inverse view transforms.
	 */
	private void buildTransforms() {

		double width = getWidth();
		double height = getHeight();
		transform = new AffineTransform(1,0,0,1,0, 0);
		transform.translate(0.5*width, 0.5*height);
		transform.scale(scale, scale);
		transform.translate(translationX - width/2.0, translationY - height/2.0);

		try {
			inverseTransform = transform.createInverse();
		}
		catch (Exception e) {}
	}

   private void render(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClear(GL.GL_COLOR_BUFFER_BIT);

      GeneralFileInfo obj = Utilities.actionsGlobalContext().lookup(GeneralFileInfo.class);
      if (obj !=null){
         fileInfo = obj;
          if ((fileInfo.getNsObj()== null ||fileInfo.getNsObj().getFileInfo()==null)&& obj.getFileExtention().equals("nsn")) {
            NSReader reader = new NSReader();
            NeuroshareFile nsn = reader.readNSFileAllData(obj.getFilePath());
            obj.setNsObj(nsn);
         }
      }
      if (fileInfo == null || fileInfo.getNsObj()== null || fileInfo.getNsObj().getFileInfo()==null) {
         return;
      }
      int max = 500;

      
      gl.glColor3d(.6, s*.1, s*.5);

      gl.glLoadIdentity();
      gl.glTranslated(translationX / (glCanvas.getWidth()*.5), translationY / (glCanvas.getHeight()*.5), 0);
      gl.glScaled(scale, scale, 0);
      
      max = fileInfo.getNsObj().getEntities().size();
      for (int i = 0; i < max; i++) {
         //Draw label
//         gl.glTranslated(translationX / (glCanvas.getWidth()*.5), translationY / (glCanvas.getHeight()*.5)+i, 0);
         drawText(gl, fileInfo.getNsObj().getEntities().get(i).getEntityInfo().getEntityLabel(), (int)(-fileInfo.getFileName().length()*1.2),-3*i, 0.0125f, 2.0f);
      }

      gl.glLineWidth(1);
      
      gl.glLoadIdentity();
      gl.glTranslated(translationX / (glCanvas.getWidth()*.5), translationY / (glCanvas.getHeight()*.5), 0);
      gl.glScaled(scale, scale, 0);

      gl.glBegin(GL.GL_LINES);

      double yOffset = 0;
      
      for (Entity e : fileInfo.getNsObj().getEntities()) {
         if (e.getTag().getElemType() == ElemType.ENTITY_ANALOG) {
            AnalogInfo ai = (AnalogInfo)e;
            if (ai == null || ai.getData() == null) {
               continue;
            }

            for (AnalogData ad : ai.getData()) {
               
               ArrayList<Double> vals = ad.getAnalogValues();
               double lastX = 0;
               double lastY = vals.get(0)-yOffset*3;

               for (int i = 0; i < vals.size(); i++) {
                  if (i % 2 == 0) {
                     gl.glVertex2d(lastX, lastY);
                  } else {
                     lastY = vals.get(i)-yOffset*3;
                     gl.glVertex2d(i, lastY);
                  }
                  lastX = i;
               }
               yOffset++;
            }
         }
      }

    /*  for (int i = 0; i < max; i++) {


         double lastX = -Math.sin(theta);
         double lastY = -Math.random()*Math.cos(theta)-3*i;

         for (int x = 0; x < 1000; x++) {
            if (x % 2 == 0) {
               gl.glVertex2d(lastX, lastY);
            } else {
               lastY = -Math.random()*Math.cos(theta)-3*i;
               gl.glVertex2d(Math.sin(theta)+x, lastY);
            }
            lastX = Math.sin(theta)+x;
         }

//         Point2D point = getScreenCoordinates(x1, y1);

//         point = getScreenCoordinates(x2, y2);
//         gl.glVertex2d(x2, y2);
      }*/
      gl.glEnd();
   }

   /**
	 * Utility function for drawing text
	 *
	 * @param gl - the JOGL context
	 * @param text 0 the text to draw
	 * @param x - the x position
	 * @param y - the y position
	 * @param size - the size of the text
	 * @param width - the width of the letters
	 */
	public void drawText(GL2 gl, String text, int x, int y, float size, float width) {
//      gl.glPushMatrix();
//gl.glTranslated(x, y, 0);
//        gl.glScalef(size, size, 0.0f);
//      renderer.beginRendering(glCanvas.getWidth(), glCanvas.getHeight());
//    // optionally set the color
//    renderer.setColor(1.0f, 0.2f, 0.2f, 0.8f);
//    renderer.draw("Text to draw", x, y);
//    // ... more draw commands, color changes, etc.
//    renderer.endRendering();
//    gl.glPopMatrix();

      GLUT glut = new GLUT();
        gl.glPushMatrix();
        gl.glTranslated(x, y, 0);
        gl.glScalef(size, size, 0.0f);
        gl.glLineWidth(width);
        glut.glutStrokeString(GLUT.STROKE_MONO_ROMAN, text);
        gl.glPopMatrix();
	}

   /**
	 * Gets the tranlation in the x direction.
	 */
	public double getTranslationX() {
		return translationX;
	}

	/**
	 * Sets the translation in the x direction and rebuilds the transforms.
	 */
	public void setTranslationX(double translationX) {
		this.translationX = translationX;
		buildTransforms();
	}

	/**
	 * Gets the tranlation in the y direction.
	 */
	public double getTranslationY() {
		return translationY;
	}

	/**
	 * Sets the translation in the y direction and rebuilds the transforms.
	 */
	public void setTranslationY(double translationY) {
		this.translationY = translationY;
		buildTransforms();
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
}
