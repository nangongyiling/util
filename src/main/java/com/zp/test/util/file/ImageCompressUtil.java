package com.zp.test.util.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageCompressUtil {

	public static void compress(File f,int width,int length,OutputStream os){
		try {
			BufferedImage oriImg = ImageIO.read(f);
			int oriWidth = oriImg.getWidth();
			int oriLength = oriImg.getHeight();
			if((oriWidth / oriLength) > (width / length)){
				length = (oriLength * width) / oriLength;
			}
			else{
				width = (oriWidth * length) / oriLength;
			}
			BufferedImage newImg = new BufferedImage(width, length,BufferedImage.TYPE_INT_RGB ); 
			newImg.getGraphics().drawImage(oriImg, 0, 0, width, length, null); // 绘制缩小后的图
//			// 可以正常实现bmp、png、gif转jpg
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(newImg); // JPEG编码
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
