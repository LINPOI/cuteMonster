package test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageMergeExample {

    public static void main(String[] args) {
        try {
            // 讀取三張圖片（假設圖片名稱分別為 image1.png, image2.png, image3.png）
            BufferedImage image1 = ImageIO.read(new File("src/PICTURE/eyes.png"));
            BufferedImage image2 = ImageIO.read(new File("src/PICTURE/slime.png"));
            BufferedImage image3 = ImageIO.read(new File("src/PICTURE/red.png"));

            // 確認圖片大小相同
            int width = image1.getWidth();
            int height = image1.getHeight();

            // 創建新的 BufferedImage 來容納三張圖片
            BufferedImage combined = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            // 獲取新圖片的 Graphics
            Graphics g = combined.getGraphics();

         // 繪製三張圖片
            g.drawImage(image1, 0, 0, null);
            g.drawImage(image2, image1.getWidth(), 0, null); // 第二張圖片從第一張圖片的寬度位置開始繪製
            g.drawImage(image3, image1.getWidth() + image2.getWidth(), 0, null); // 第三張圖片從前兩張圖片的寬度總和位置開始繪製
            // 儲存合併後的圖片（假設合併後的圖片名稱為 mergedImage.png）
            File output = new File("src/newpicture/mergedImage.png");
            ImageIO.write(combined, "PNG", output);

            // 釋放資源
            g.dispose();

            System.out.println("圖片合併完成！");
        } catch (Exception e) {
            System.out.println("發生錯誤：" + e.getMessage());
        }
    }
}

