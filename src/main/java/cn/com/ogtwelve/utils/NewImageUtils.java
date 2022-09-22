package cn.com.ogtwelve.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewImageUtils {
    //底图 叠加的logo 保存路径
    public static void addImg( String sourceFilePath, String waterFilePath, String saveFilePath) throws IOException {
        // 构建叠加层
        BufferedImage buffImg = watermark(new File(sourceFilePath), new File(waterFilePath), 100, 100, 1.0f);
        // 输出水印图片
        int temp = saveFilePath.lastIndexOf(".") + 1;
        try {
            ImageIO.write(buffImg, saveFilePath.substring(temp), new File(saveFilePath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        int buffImgWidth = buffImg.getWidth()/20;
        int buffImgHeight = buffImg.getHeight()/50;
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        int waterImgWidth =((buffImg.getHeight()*waterImg.getWidth()/10)/waterImg.getHeight());// 获取层图的宽度
        int waterImgHeight =buffImg.getHeight()/10;// 获取层图的高度
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, buffImgWidth, buffImgHeight, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }
}
