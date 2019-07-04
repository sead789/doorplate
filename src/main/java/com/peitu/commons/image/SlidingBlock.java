package com.peitu.commons.image;

import com.peitu.doorplateqrcode.dto.SlidingBlockData;

import javax.imageio.*;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Rising
 * @date 2019/6/20
 */
public class SlidingBlock {

    private static final int PIC_LENGTH = 500;
    private static final int PIC_WIDTH = 500;
    private static final int BLOCK_HEIGHT = 55;
    private static final int BLOCK_WIDTH = 55;

    public static SlidingBlockData slidingBlock() {
        Random random = new Random();
        int min = 100;
        int max = 400;
        int min2 = 100;
        int max2 = 400;
        Integer rx = random.nextInt(max - min) + min;
        Integer ry = random.nextInt(max2 - min2) + min2;
        try {
            BufferedImage image = ImageUtils.getImage();
            BufferedImage checkImgMin = getMarkImage(image, rx, ry, BLOCK_HEIGHT, BLOCK_WIDTH);
            BufferedImage checkImg = cutByTemplate(image, getCutAreaData(PIC_LENGTH, PIC_WIDTH, rx, ry, BLOCK_HEIGHT, BLOCK_WIDTH));
            BufferedImage frameMin = imagesFrameMin(checkImgMin);
            BufferedImage frame = imagesFrame(checkImg, rx, ry, BLOCK_HEIGHT, BLOCK_WIDTH);
            BufferedImage dest = simpleBlur(frame, null);
            BufferedImage destMin = simpleBlur(frameMin, null);
            byte[] imageWriter = compressPictures(dest);
            byte[] imageWriterMin = compressPictures(destMin);
            ByteArrayInputStream in = new ByteArrayInputStream(imageWriter);
            ByteArrayInputStream inMin = new ByteArrayInputStream(imageWriterMin);
            BufferedImage dd = ImageIO.read(in);
            BufferedImage ddMin = ImageIO.read(inMin);
            String comp = ImageUtils.bufferedImage2base64(dd);
            String compMin = ImageUtils.bufferedImage2base64(ddMin);
//            ImageIO.write(dd, "jpg", new File("F:\\linshi\\test.png"));
//            ImageIO.write(ddMin, "jpg", new File("F:\\linshi\\test1.png"));
            return new SlidingBlockData(comp, compMin, rx);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 抠图方块裁剪，得到一个用来滑动的小方块
     * 对图片裁剪，并把裁剪后的图片返回
     *
     * @param image
     * @param x
     * @param y
     * @param height 块的高度
     * @param width  块的宽度
     * @return
     * @throws IOException
     */
    private static BufferedImage getMarkImage(BufferedImage image, int x, int y,
                                       int height, int width) throws IOException{
        InputStream is = null;
        ImageInputStream iis = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", os);
            is = new ByteArrayInputStream(os.toByteArray());
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = it.next();
            iis = ImageIO.createImageInputStream(is);
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rect = new Rectangle(x, y, height, width);
            param.setSourceRegion(rect);
            BufferedImage bi = reader.read(0, param);
            return bi;
        } finally {
            if (is != null){
                is.close();
            } if (iis != null){
                iis.close();
            }
        }
    }

    /**
     * 对原图的裁剪位置的上色标记处理
     *
     * @param targetLength 原图的长
     * @param targetWidth  原图的宽
     * @param x
     * @param y
     * @param height  块的高度
     * @param width   块的宽度
     * @return
     */
    private static int[][] getCutAreaData(int targetLength, int targetWidth, int x, int y,
                                   int height, int width){
        int[][] data = new int[targetLength][targetWidth];
        for (int i = 0; i < targetLength; i++) {
            for (int j = 0; j < targetWidth; j++) {
                if (i < x + height && i >= x && j < y + width && j > y){
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
            }
        }
        return data;
    }

    /**
     * 标记为1的地方上色处理
     *
     * @param oriImage
     * @param templateImage
     * @return
     * @throws Exception
     */
    private static BufferedImage cutByTemplate(BufferedImage oriImage, int[][] templateImage){
        for (int i = 0; i < oriImage.getWidth(); i++) {
            for (int j = 0; j < oriImage.getHeight(); j++) {
                int rgb = templateImage[i][j];
                int rgb_ori = oriImage.getRGB(i, j);
                if (rgb == 1){
                    int r = (0xff & rgb_ori);
                    int g = (0xff & (rgb_ori >> 8));
                    int b = (0xff & (rgb_ori >> 16));
                    int gray = (r * 2 + g * 5 + b * 1) >> 3;
                    oriImage.setRGB(i, j , gray);
                }
            }
        }
        return oriImage;
    }

    /**
     * 高斯模糊，原图，增加识别难度
     *
     * @param radius
     * @param horizontal
     * @return
     */
    private static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal){
        if (radius < 1){
            throw new IllegalArgumentException("Radius must be >=1");
        }
        int size = radius * 2 +1;
        float[] data = new float[size];
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        for (int i = -radius; i <= radius ; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) (Math.exp(-distance / twoSigmaSquare) / sigmaRoot);
            total += data[index];
        }
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }
        Kernel kernel = null;
        if (horizontal){
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }

    /**
     * 使用高斯模糊
     *
     * @param src
     * @param dest
     * @return
     */
    private static BufferedImage simpleBlur(BufferedImage src, BufferedImage dest){
        BufferedImageOp op = getGaussianBlurFilter(2, false);
        return op.filter(src, dest);
    }

    /**
     * 为图片添加边框
     * @param image
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    private static BufferedImage imagesFrame(BufferedImage image, int x, int y, int height, int width){
        Graphics g = image.getGraphics();
        g.setColor(Color.RED);
        g.drawRect(x, y, width, height);
        return image;
    }

    private static BufferedImage imagesFrameMin(BufferedImage image){
        int height = 54;
        int width = 54;
        Graphics g = image.getGraphics();
        Color c1 = new Color(173, 216, 230);
        g.setColor(c1);
        g.drawRect(0, 0, width, height);
        return image;
    }

    /**
     * 压缩图片
     *
     * @param bufferedImage
     * @return
     * @throws IOException
     */
    private static byte[] compressPictures(BufferedImage bufferedImage) throws IOException{
        Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpg");
        ImageWriter writer = iter.next();

        ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(1f);
        imageWriteParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
        ColorModel colorModel = ColorModel.getRGBdefault();

        imageWriteParam.setDestinationType(new ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
        IIOImage iImage = new IIOImage(bufferedImage, null, null);
        writer.write(null, iImage, imageWriteParam);

        return byteArrayOutputStream.toByteArray();
    }

}
