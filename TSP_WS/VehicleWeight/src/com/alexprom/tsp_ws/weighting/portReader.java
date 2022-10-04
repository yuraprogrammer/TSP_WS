package com.alexprom.tsp_ws.weighting;

import com.fazecast.jSerialComm.SerialPort;
import java.io.UnsupportedEncodingException;
import org.openide.util.Exceptions;


public final class portReader implements Runnable{


private SerialPort workingPort;
public boolean opened;
public int value=0;
public boolean stop;
public String serialMessage;
private int dataLength = 8;
private int BaudRate = 9600;
public int bytesAvailable;

    public int getBaudRate() {
        return BaudRate;
    }

    public void setBaudRate(int BaudRate) {
        this.BaudRate = BaudRate;
    }

    public String getSerialMessage() {
        return serialMessage;
    }

    public void setSerialMessage(String serialMessage) {
        this.serialMessage = serialMessage;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public portReader(int port, int dLength, int baudRate, int dataBits, int parity, int stopBits){
        super();
        workingPort = null;
        int avail = SerialPort.getCommPorts().length;
        if (avail!=0){
            workingPort = SerialPort.getCommPorts()[port];
            workingPort.setBaudRate(baudRate);
            workingPort.setNumDataBits(dataBits);
            workingPort.setNumStopBits(stopBits);
            workingPort.setParity(parity);
            this.dataLength = dLength;   
            //workingPort.closePort();
            //opened = workingPort.openPort();
            //workingPort.flushIOBuffers();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void closePort(){
        workingPort.closePort(); //Close the port
        opened = false;
    }
    
    public boolean openPort(){
        opened = workingPort.openPort();
        return opened;
    }
    
    public void flush(){
        workingPort.flushIOBuffers();
    }
    
    @Override
    public void run() {
        synchronized(this){
            while (opened){
                bytesAvailable = workingPort.bytesAvailable();
                if (bytesAvailable>0){
                    byte[] readBuffer = new byte[dataLength];
                    int numRead = workingPort.readBytes(readBuffer, readBuffer.length);
                    String S = null;
                    
                    try {
                        S = new String(readBuffer, "UTF-8"); //convert bytes to String
                        S = S.trim();
                        setSerialMessage("-->" + S);
                        if (S.startsWith("R01W")){
                            S = S.substring(4);
                            int newSeq = S.indexOf("R");
                            if (newSeq!=-1){
                                S = S.substring(0, newSeq);
                            }
                            setValue(Integer.parseInt(S));
                        }                                                                        
                        
                    } catch (UnsupportedEncodingException ex) {
                        setSerialMessage(ex.getLocalizedMessage());
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }                                         
        }
        
    }       
        
    

    
    
}
