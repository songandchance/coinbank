/*
 * CoinBankView.java
 */

package coinbank;

import business.Bank;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class CoinBankView extends FrameView {
    Bank b;
    public CoinBankView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CoinBankApp.getApplication().getMainFrame();
            aboutBox = new CoinBankAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CoinBankApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtQtrs = new javax.swing.JTextField();
        jTxtDime = new javax.swing.JTextField();
        jTxtNick = new javax.swing.JTextField();
        jTxtPen = new javax.swing.JTextField();
        jBtnLdBank = new javax.swing.JButton();
        jBtnClear = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTxtTotVal = new javax.swing.JTextField();
        jBtnAddQ = new javax.swing.JButton();
        jBtnAddD = new javax.swing.JButton();
        jBtnAddN = new javax.swing.JButton();
        jBtnAddP = new javax.swing.JButton();
        jBtnDecQ = new javax.swing.JButton();
        jBtnDecD = new javax.swing.JButton();
        jBtnDecN = new javax.swing.JButton();
        jBtnDecP = new javax.swing.JButton();
        jBtnSave = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(coinbank.CoinBankApp.class).getContext().getResourceMap(CoinBankView.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jTxtQtrs.setText(resourceMap.getString("jTxtQtrs.text")); // NOI18N
        jTxtQtrs.setName("jTxtQtrs"); // NOI18N

        jTxtDime.setText(resourceMap.getString("jTxtDime.text")); // NOI18N
        jTxtDime.setName("jTxtDime"); // NOI18N

        jTxtNick.setText(resourceMap.getString("jTxtNick.text")); // NOI18N
        jTxtNick.setName("jTxtNick"); // NOI18N

        jTxtPen.setText(resourceMap.getString("jTxtPen.text")); // NOI18N
        jTxtPen.setName("jTxtPen"); // NOI18N

        jBtnLdBank.setText(resourceMap.getString("jBtnLdBank.text")); // NOI18N
        jBtnLdBank.setName("jBtnLdBank"); // NOI18N
        jBtnLdBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLdBankActionPerformed(evt);
            }
        });

        jBtnClear.setText(resourceMap.getString("jBtnClear.text")); // NOI18N
        jBtnClear.setName("jBtnClear"); // NOI18N
        jBtnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnClearActionPerformed(evt);
            }
        });

        jLabel6.setFont(resourceMap.getFont("jLabel6.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jTxtTotVal.setEditable(false);
        jTxtTotVal.setText(resourceMap.getString("jTxtTotVal.text")); // NOI18N
        jTxtTotVal.setName("jTxtTotVal"); // NOI18N

        jBtnAddQ.setText(resourceMap.getString("jBtnAddQ.text")); // NOI18N
        jBtnAddQ.setEnabled(false);
        jBtnAddQ.setName("jBtnAddQ"); // NOI18N
        jBtnAddQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddQActionPerformed(evt);
            }
        });

        jBtnAddD.setText(resourceMap.getString("jBtnAddD.text")); // NOI18N
        jBtnAddD.setEnabled(false);
        jBtnAddD.setName("jBtnAddD"); // NOI18N
        jBtnAddD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddDActionPerformed(evt);
            }
        });

        jBtnAddN.setText(resourceMap.getString("jBtnAddN.text")); // NOI18N
        jBtnAddN.setEnabled(false);
        jBtnAddN.setName("jBtnAddN"); // NOI18N
        jBtnAddN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddNActionPerformed(evt);
            }
        });

        jBtnAddP.setText(resourceMap.getString("jBtnAddP.text")); // NOI18N
        jBtnAddP.setEnabled(false);
        jBtnAddP.setName("jBtnAddP"); // NOI18N
        jBtnAddP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddPActionPerformed(evt);
            }
        });

        jBtnDecQ.setText(resourceMap.getString("jBtnDecQ.text")); // NOI18N
        jBtnDecQ.setEnabled(false);
        jBtnDecQ.setName("jBtnDecQ"); // NOI18N
        jBtnDecQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDecQActionPerformed(evt);
            }
        });

        jBtnDecD.setText(resourceMap.getString("jBtnDecD.text")); // NOI18N
        jBtnDecD.setEnabled(false);
        jBtnDecD.setName("jBtnDecD"); // NOI18N
        jBtnDecD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDecDActionPerformed(evt);
            }
        });

        jBtnDecN.setText(resourceMap.getString("jBtnDecN.text")); // NOI18N
        jBtnDecN.setEnabled(false);
        jBtnDecN.setName("jBtnDecN"); // NOI18N
        jBtnDecN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDecNActionPerformed(evt);
            }
        });

        jBtnDecP.setText(resourceMap.getString("jBtnDecP.text")); // NOI18N
        jBtnDecP.setEnabled(false);
        jBtnDecP.setName("jBtnDecP"); // NOI18N
        jBtnDecP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDecPActionPerformed(evt);
            }
        });

        jBtnSave.setText(resourceMap.getString("jBtnSave.text")); // NOI18N
        jBtnSave.setName("jBtnSave"); // NOI18N
        jBtnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(jLabel1))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addComponent(jLabel5))
                                .addGap(58, 58, 58)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jBtnLdBank, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(jTxtQtrs)
                                    .addComponent(jTxtDime)
                                    .addComponent(jTxtNick)
                                    .addComponent(jTxtPen))))
                        .addGap(46, 46, 46))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTxtTotVal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jBtnAddQ)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDecQ))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jBtnAddD)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDecD))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jBtnAddN)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDecN))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(jBtnAddP)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnDecP)))
                .addContainerGap(161, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtQtrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnAddQ)
                        .addComponent(jBtnDecQ)))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTxtDime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnAddD)
                        .addComponent(jBtnDecD)))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTxtNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnAddN)
                        .addComponent(jBtnDecN)))
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTxtPen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnAddP)
                        .addComponent(jBtnDecP))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLdBank)
                .addGap(28, 28, 28)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTxtTotVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnClear)
                    .addComponent(jBtnSave))
                .addContainerGap(82, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(coinbank.CoinBankApp.class).getContext().getActionMap(CoinBankView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 427, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnLdBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLdBankActionPerformed
        statusMessageLabel.setText("");
        int q, d, n, p;
        try
        {
            q = Integer.parseInt(jTxtQtrs.getText());
        }
        catch (Exception e)
        {
            statusMessageLabel.setText("Not a valid input (Quarters)");
            return;            
        }
        try
        {
            d = Integer.parseInt(jTxtDime.getText());
        }
        catch (Exception e)
        {
            statusMessageLabel.setText("Not a valid input (Dimes)");
            return;            
        }
        try
        {
            n = Integer.parseInt(jTxtNick.getText());
        }
        catch (Exception e)
        {
            statusMessageLabel.setText("Not a valid input (Nickels)");
            return;            
        } 
        try
        {
            p = Integer.parseInt(jTxtPen.getText());
        }
        catch (Exception e)
        {
            statusMessageLabel.setText("Not a valid input (Pennies)");
            return;            
        }
        if (q < 0)
        {
            statusMessageLabel.setText("Value cannot be negative");
            return;           
        }
        else if (d < 0)
        {
            statusMessageLabel.setText("Value cannot be negative");
            return;           
        }
        else if (n < 0)
        {
            statusMessageLabel.setText("Value cannot be negative");
            return;           
        }
        else if (p < 0)
        {
            statusMessageLabel.setText("Value cannot be negative");
            return;           
        }
        else
        {
        b = new Bank(q, d, n, p);
        if (!b.getErrorMsg().isEmpty())
        {
            statusMessageLabel.setText(b.getErrorMsg());
            jTxtQtrs.setText("");
            jTxtDime.setText("");
            jTxtNick.setText("");
            jTxtPen.setText("");
        }
        else
        {
            NumberFormat curr = NumberFormat.getCurrencyInstance();
            jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
        }
        jBtnLdBank.setEnabled(false);
        jTxtQtrs.setEnabled(false);
        jTxtDime.setEnabled(false);
        jTxtNick.setEnabled(false);
        jTxtPen.setEnabled(false);
        jBtnAddQ.setEnabled(true);
        jBtnAddD.setEnabled(true);
        jBtnAddN.setEnabled(true);
        jBtnAddP.setEnabled(true);
        jBtnDecQ.setEnabled(true);
        jBtnDecD.setEnabled(true);
        jBtnDecN.setEnabled(true);
        jBtnDecP.setEnabled(true);
        }
        
//
        
    }//GEN-LAST:event_jBtnLdBankActionPerformed

    private void jBtnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnClearActionPerformed
        jBtnLdBank.setEnabled(true);
        jTxtQtrs.setEnabled(true);
        jTxtDime.setEnabled(true);
        jTxtNick.setEnabled(true);
        jTxtPen.setEnabled(true);
        jBtnAddQ.setEnabled(false);
        jBtnAddD.setEnabled(false);
        jBtnAddN.setEnabled(false);
        jBtnAddP.setEnabled(false);
        jBtnDecQ.setEnabled(false);
        jBtnDecD.setEnabled(false);
        jBtnDecN.setEnabled(false);
        jBtnDecP.setEnabled(false);
        jTxtQtrs.setText("");
        jTxtDime.setText("");
        jTxtNick.setText("");
        jTxtPen.setText("");
        jTxtTotVal.setText("");   
    }//GEN-LAST:event_jBtnClearActionPerformed

    private void jBtnAddPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddPActionPerformed
        statusMessageLabel.setText("");
        b.addPenny();
        jTxtPen.setText(String.valueOf(b.getPennies()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
    }//GEN-LAST:event_jBtnAddPActionPerformed

    private void jBtnDecPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDecPActionPerformed
        statusMessageLabel.setText("");
        b.decPenny();        
        if (!b.getErrorMsg().isEmpty())
        {
            statusMessageLabel.setText(b.getErrorMsg());
        }
        else
        {
        jTxtPen.setText(String.valueOf(b.getPennies()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
        }
    }//GEN-LAST:event_jBtnDecPActionPerformed

    private void jBtnAddNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddNActionPerformed
        statusMessageLabel.setText("");
        b.addNickel();
        jTxtNick.setText(String.valueOf(b.getNickels()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
    }//GEN-LAST:event_jBtnAddNActionPerformed

    private void jBtnDecNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDecNActionPerformed
        statusMessageLabel.setText("");
        b.decNickel();
        if (!b.getErrorMsg().isEmpty())
        {
            statusMessageLabel.setText(b.getErrorMsg());
        }
        else
        {

        jTxtNick.setText(String.valueOf(b.getNickels()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
        }
    }//GEN-LAST:event_jBtnDecNActionPerformed

    private void jBtnAddDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddDActionPerformed
        statusMessageLabel.setText("");
        b.addDime();
        jTxtDime.setText(String.valueOf(b.getDimes()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
    }//GEN-LAST:event_jBtnAddDActionPerformed

    private void jBtnDecDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDecDActionPerformed
        statusMessageLabel.setText("");
        b.decDime();
        if (!b.getErrorMsg().isEmpty())
        {
            statusMessageLabel.setText(b.getErrorMsg());
        }
        else
        {        
        jTxtDime.setText(String.valueOf(b.getDimes()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
        }
    }//GEN-LAST:event_jBtnDecDActionPerformed

    private void jBtnAddQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddQActionPerformed
        statusMessageLabel.setText("");
        b.addQuarter();
        jTxtQtrs.setText(String.valueOf(b.getQuarters()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
    }//GEN-LAST:event_jBtnAddQActionPerformed

    private void jBtnDecQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDecQActionPerformed
        statusMessageLabel.setText("");
        b.decQuarter();
        if (!b.getErrorMsg().isEmpty())
        {
            statusMessageLabel.setText(b.getErrorMsg());
        }
        else
        {       
        jTxtQtrs.setText(String.valueOf(b.getQuarters()));
        NumberFormat curr = NumberFormat.getCurrencyInstance();
        jTxtTotVal.setText(String.valueOf(curr.format(b.getTotalValue())));
        }
    }//GEN-LAST:event_jBtnDecQActionPerformed

    private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSaveActionPerformed
        b.saveBank();
        statusMessageLabel.setText("Banks saved as bank.txt.");
    }//GEN-LAST:event_jBtnSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnAddD;
    private javax.swing.JButton jBtnAddN;
    private javax.swing.JButton jBtnAddP;
    private javax.swing.JButton jBtnAddQ;
    private javax.swing.JButton jBtnClear;
    private javax.swing.JButton jBtnDecD;
    private javax.swing.JButton jBtnDecN;
    private javax.swing.JButton jBtnDecP;
    private javax.swing.JButton jBtnDecQ;
    private javax.swing.JButton jBtnLdBank;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTxtDime;
    private javax.swing.JTextField jTxtNick;
    private javax.swing.JTextField jTxtPen;
    private javax.swing.JTextField jTxtQtrs;
    private javax.swing.JTextField jTxtTotVal;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}