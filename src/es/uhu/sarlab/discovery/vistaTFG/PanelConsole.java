/*     */ package es.uhu.sarlab.discovery.vistaTFG;
/*     */ import es.uhu.sarlab.utils.BoardListener;
/*     */ import java.awt.Dimension;
import java.awt.EventQueue;
/*     */ import java.awt.Image;
import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Timer;
import java.util.TimerTask;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.LayoutStyle;
/*     */ import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*     */ 
/*     */ public class PanelConsole extends JFrame {
/*  21 */   Timer timer = null; PanelClientServerName clientName;
/*  22 */   TimerTask timerTask = null;
/*  23 */   BoardListener boardListener = new BoardListener(); private JButton jButton1; private JButton jButton2; private JButton jButton3;
/*     */   private JCheckBox jCheckBox1;
/*     */   private JScrollPane jScrollPane1;
/*     */   private JTextArea jTextConsole;
/*     */   
/*     */   public PanelConsole(PanelClientServerName clientName) {
/*  29 */     this.timer = new Timer(true);
/*  30 */     this.timerTask = new MyTimerTask();
/*  31 */     this.clientName = clientName;
/*  32 */     initComponents();
/*  33 */     setLocationRelativeTo(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Image getIconImage() {
/*  39 */     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("es/uhu/sarlab/discovery/vista/resources/Remote-Desktop32x32.png"));
/*  40 */     return retValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVisible(boolean b) {
/*  45 */     this.jCheckBox1.setSelected(true);
/*  46 */     this.jCheckBox1.setLabel("Stop");
/*  47 */     super.setVisible(b);
/*     */   }
/*     */   
/*     */   public void startTimer() {
/*  51 */     this.timerTask = new MyTimerTask();
/*     */     
/*  53 */     this.timer.scheduleAtFixedRate(this.timerTask, 0L, 1000L);
/*  54 */     System.out.println("TimerTask start");
/*     */   }
/*     */   
/*     */   public void stopTimer() {
/*  58 */     if (this.jTextConsole != null) {
/*  59 */       this.jTextConsole.setText(this.clientName.baos.toString());
/*     */     }
/*  61 */     this.timerTask.cancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class MyTimerTask
/*     */     extends TimerTask
/*     */   {
/*     */     public void run() {
/*  71 */       if (PanelConsole.this.jTextConsole != null) {
/*  72 */         PanelConsole.this.jTextConsole.setText(PanelConsole.this.clientName.baos.toString());
/*     */       } else {
/*  74 */         PanelConsole.this.stopTimer();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initComponents() {
/*  88 */     this.jScrollPane1 = new JScrollPane();
/*  89 */     this.jTextConsole = new JTextArea();
/*  90 */     this.jButton1 = new JButton();
/*  91 */     this.jCheckBox1 = new JCheckBox();
/*  92 */     this.jButton2 = new JButton();
/*  93 */     this.jButton3 = new JButton();
/*     */     
/*  95 */     setIconImage(getIconImage());
/*  96 */     addWindowListener(new WindowAdapter() {
/*     */           public void windowClosing(WindowEvent evt) {
/*  98 */             PanelConsole.this.formWindowClosing(evt);
/*     */           }
/*     */           public void windowClosed(WindowEvent evt) {
/* 101 */             PanelConsole.this.formWindowClosed(evt);
/*     */           }
/*     */         });
/*     */     
/* 105 */     this.jTextConsole.setColumns(20);
/* 106 */     this.jTextConsole.setRows(5);
/* 107 */     this.jScrollPane1.setViewportView(this.jTextConsole);
/*     */     
/* 109 */     this.jButton1.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/actualiza.png")));
/* 110 */     ResourceBundle bundle = ResourceBundle.getBundle("es/uhu/sarlab/discovery/vista/Bundle_PanelConsole");
/* 111 */     this.jButton1.setText(bundle.getString("PanelConsole.jButton1.text"));
/* 112 */     this.jButton1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 114 */             PanelConsole.this.jButton1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 118 */     this.jCheckBox1.setText(bundle.getString("PanelConsole.jCheckBox1.text"));
/* 119 */     this.jCheckBox1.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 121 */             PanelConsole.this.jCheckBox1ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 125 */     this.jButton2.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/edit_copy.png")));
/* 126 */     this.jButton2.setText(bundle.getString("PanelConsole.jButton2.text"));
/* 127 */     this.jButton2.setMaximumSize(new Dimension(99, 33));
/* 128 */     this.jButton2.setMinimumSize(new Dimension(99, 33));
/* 129 */     this.jButton2.setPreferredSize(new Dimension(99, 33));
/* 130 */     this.jButton2.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 132 */             PanelConsole.this.jButton2ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 136 */     this.jButton3.setIcon(new ImageIcon(getClass().getResource("/es/uhu/sarlab/discovery/vista/resources/borrar.png")));
/* 137 */     this.jButton3.setText(bundle.getString("PanelConsole.jButton3.text"));
/* 138 */     this.jButton3.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent evt) {
/* 140 */             PanelConsole.this.jButton3ActionPerformed(evt);
/*     */           }
/*     */         });
/*     */     
/* 144 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 145 */     getContentPane().setLayout(layout);
/* 146 */     layout.setHorizontalGroup(layout
/* 147 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148 */         .addGroup(layout.createSequentialGroup()
/* 149 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 150 */             .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
/* 151 */               .addContainerGap()
/* 152 */               .addComponent(this.jScrollPane1))
/* 153 */             .addGroup(layout.createSequentialGroup()
/* 154 */               .addGap(67, 67, 67)
/* 155 */               .addComponent(this.jCheckBox1, -2, 81, -2)
/* 156 */               .addGap(9, 9, 9)
/* 157 */               .addComponent(this.jButton1)
/* 158 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 159 */               .addComponent(this.jButton2, -2, -1, -2)
/* 160 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 161 */               .addComponent(this.jButton3)
/* 162 */               .addGap(0, 122, 32767)))
/* 163 */           .addContainerGap()));
/*     */     
/* 165 */     layout.setVerticalGroup(layout
/* 166 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 167 */         .addGroup(layout.createSequentialGroup()
/* 168 */           .addContainerGap()
/* 169 */           .addComponent(this.jScrollPane1, -2, 359, -2)
/* 170 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 171 */           .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 172 */             .addComponent(this.jButton1)
/* 173 */             .addComponent(this.jCheckBox1)
/* 174 */             .addComponent(this.jButton2, -1, -1, 32767)
/* 175 */             .addComponent(this.jButton3))
/* 176 */           .addContainerGap()));
/*     */ 
/*     */     
/* 179 */     pack();
/*     */   }
/*     */ 
/*     */   
/*     */   private void jButton1ActionPerformed(ActionEvent evt) {
/* 184 */     this.jTextConsole.setText(this.clientName.baos.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void formWindowClosed(WindowEvent evt) {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void formWindowClosing(WindowEvent evt) {
/* 195 */     System.out.println("*******Close console *********");
/* 196 */     if (this.timer != null)
/* 197 */       stopTimer(); 
/*     */   }
/*     */   
/*     */   private void jCheckBox1ActionPerformed(ActionEvent evt) {
/* 201 */     if (this.jCheckBox1.isSelected()) {
/* 202 */       this.jCheckBox1.setLabel("Stop");
/* 203 */       startTimer();
/*     */     } else {
/* 205 */       this.jCheckBox1.setLabel("Start");
/* 206 */       System.out.println("TimerTask stop");
/* 207 */       this.jTextConsole.setText(this.clientName.baos.toString());
/* 208 */       stopTimer();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void jButton2ActionPerformed(ActionEvent evt) {
/* 213 */     this.boardListener.setClipboard(this.clientName.baos.toString());
/*     */   }
/*     */   
/*     */   private void jButton3ActionPerformed(ActionEvent evt) {
/* 217 */     this.clientName.baos.reset();
/* 218 */     this.jTextConsole.setText(this.clientName.baos.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws UnsupportedLookAndFeelException {
/*     */     try {
/* 231 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/* 232 */         if ("Nimbus".equals(info.getName())) {
/* 233 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 237 */     } catch (ClassNotFoundException ex) {
/* 238 */       Logger.getLogger(PanelConsole.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 239 */     } catch (InstantiationException ex) {
/* 240 */       Logger.getLogger(PanelConsole.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 241 */     } catch (IllegalAccessException ex) {
/* 242 */       Logger.getLogger(PanelConsole.class.getName()).log(Level.SEVERE, (String)null, ex);
/* 243 */     } catch (UnsupportedLookAndFeelException ex) {
/* 244 */       Logger.getLogger(PanelConsole.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 249 */     EventQueue.invokeLater(new Runnable() {
/*     */           public void run() {
/* 251 */             (new PanelConsole(null)).setVisible(true);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\alumno\Documents\NetBeansProjects\Java2JavaFX\lib\libdiscoverysarlab1.0.1.jar!\e\\uhu\sarlab\discovery\vista\PanelConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */