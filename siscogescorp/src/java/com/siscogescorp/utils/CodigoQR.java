/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siscogescorp.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author CIMA2015
 */
public class CodigoQR {
    
    
    public void generaImgQr(){
    
        String details ="Stalyn Granda";
        try {
            ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();        
            File f = new File("D:\\imagnesqr\\ok.jpg");       
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(out.toByteArray());
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodigoQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodigoQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     public void generaImgQrID(int idTiket, int idDeudor){
    
        String details ="QR"+idTiket+"-"+idDeudor;
        try {
            ByteArrayOutputStream out = QRCode.from(details).to(ImageType.JPG).stream();        
            File f = new File("D:\\imagnesqr\\"+details+".jpg");       
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(out.toByteArray());
            fos.flush();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CodigoQR.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CodigoQR.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}
