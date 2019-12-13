/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lukemayes;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDescriptor;
import com.drew.metadata.exif.ExifSubIFDDirectory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.FilenameUtils;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.ComponentOrientation;
import javax.swing.DropMode;
import javax.swing.JButton;

/**
 *
 * @author mayes
 */
public class ImageDescriptor extends javax.swing.JFrame {

    private final JFileChooser openImageFileChooser;
    private final JFileChooser openTagsFileChooser;
    
    private String path;
    private File imgFile;
    
    private String aperture;
    private String focalLength;
    private String shutterSpeed;
    private String iso;
    private String title;
    private String tags = "hello";
    
    /**
     * Creates new form ImageDescriptorNew
     */
    public ImageDescriptor() {
    	setResizable(false);
    	setLocationByPlatform(true);
    	getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	getContentPane().setPreferredSize(new Dimension(505, 355));
    	setAutoRequestFocus(false);
        initComponents();
        
        openImageFileChooser = new JFileChooser();
        openImageFileChooser.setCurrentDirectory(new File("C:\\Users\\\\mayes\\\\OneDrive\\\\Personal"));
        openImageFileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg"));
        
        openTagsFileChooser = new JFileChooser();
        openTagsFileChooser.setCurrentDirectory(new File("C:\\Users\\mayes\\\\Desktop"));
        openTagsFileChooser.setFileFilter(new FileNameExtensionFilter("Text", "txt"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OpenImageFileButton = new javax.swing.JButton();
        OpenImageFileButton.setBounds(10, 40, 111, 23);
        OpenImageFileButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        messageImageLabel = new javax.swing.JLabel();
        messageImageLabel.setBounds(125, 40, 369, 23);
        messageImageLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        OpenTagsFileButton = new javax.swing.JButton();
        OpenTagsFileButton.setBounds(10, 74, 111, 23);
        OpenTagsFileButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        messageTagsLabel = new javax.swing.JLabel();
        messageTagsLabel.setBounds(125, 74, 370, 23);
        messageTagsLabel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setBounds(10, 132, 484, 180);
        jScrollPane1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        output = new javax.swing.JEditorPane();
        output.setToolTipText("Output");
        output.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        generateButton = new javax.swing.JButton();
        generateButton.setBounds(10, 323, 87, 23);
        generateButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setBounds(10, 11, 599, 23);
        jLabel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setBounds(10, 111, 599, 15);
        jLabel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        OpenImageFileButton.setText("Open image...");
        OpenImageFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenImageFileButtonActionPerformed(evt);
            }
        });
        getContentPane().setLayout(null);
        getContentPane().add(OpenImageFileButton);

        messageImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(messageImageLabel);

        OpenTagsFileButton.setText("Open tag file...");
        OpenTagsFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenTagsFileButtonActionPerformed(evt);
            }
        });
        getContentPane().add(OpenTagsFileButton);

        messageTagsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(messageTagsLabel);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setViewportView(output);

        getContentPane().add(jScrollPane1);

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        getContentPane().add(generateButton);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Select a image file and a text file.");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Output:");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Retrieves photo when open image button is clicked
    private void OpenImageFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenImageFileButtonActionPerformed
        int returnValue1 = openImageFileChooser.showOpenDialog(this);
        BufferedImage bi;
         
        if(returnValue1 == JFileChooser.APPROVE_OPTION) {
            try {
                bi = ImageIO.read(openImageFileChooser.getSelectedFile());
                messageImageLabel.setText("Image file successfully loaded!");
                path = openImageFileChooser.getSelectedFile().getAbsolutePath();
                imgFile = new File(path);
            }catch(IOException ioe) {
                messageImageLabel.setText("No image file chosen!");
            }
        }
        else {
            messageImageLabel.setText("No image file chosen!");
        }
    }//GEN-LAST:event_OpenImageFileButtonActionPerformed

    // Retrieves description when open image button is clicked
    private void OpenTagsFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenTagsFileButtonActionPerformed
        int returnValue1 = openTagsFileChooser.showOpenDialog(this);
        
        if(returnValue1 == JFileChooser.APPROVE_OPTION) {
            try {
                File tagsFile = new File(openTagsFileChooser.getSelectedFile().getAbsolutePath());
                Scanner scan = new Scanner(tagsFile); 
                while (scan.hasNextLine()) {
                    tags = (scan.nextLine());
                }
                    
                messageTagsLabel.setText("Tag file successfully loaded!");
            }catch(IOException ioe) {
                 messageTagsLabel.setText("No tag file chosen!");
            }
        }
        else {
            messageImageLabel.setText("No tag file chosen!");
        }
    }//GEN-LAST:event_OpenTagsFileButtonActionPerformed

    // Retrieves metadata from photo
    private void extractMetadata() throws ImageProcessingException
    {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(imgFile);
            ExifSubIFDDirectory directoryExif = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            ExifSubIFDDescriptor descriptorExif = new ExifSubIFDDescriptor(directoryExif); 
            
            aperture = descriptorExif.getApertureValueDescription();
            focalLength = descriptorExif.getFocalLengthDescription();
            shutterSpeed = descriptorExif.getShutterSpeedDescription();
            iso = descriptorExif.getIsoEquivalentDescription();
            title = FilenameUtils.removeExtension(imgFile.getName());
        }catch(IOException ioe) {
            System.out.println("Metadata not found");
        }

    }
    
    // Extracts metadata, constructs output
    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        try {
            extractMetadata();
            output.setText(title + " ~ " + aperture + " " + focalLength + " " + shutterSpeed + " ISO " + iso + " ~ " + tags);
        } catch (ImageProcessingException ie) {
            System.out.println(ie);
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageDescriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageDescriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageDescriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageDescriptor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageDescriptor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OpenImageFileButton;
    private javax.swing.JButton OpenTagsFileButton;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel messageImageLabel;
    private javax.swing.JLabel messageTagsLabel;
    private javax.swing.JEditorPane output;
}
