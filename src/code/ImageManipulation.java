package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
        pixelateImage("/Users/tommyzhou/Desktop/endeavours/apps/img-manipulation/cyberpunk2077.jpg", 20);

    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        Pixel[][] pixelLayout = new Pixel[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixelLayout[i][j] = image.getPixel(i, j);
            }
        }
        for (Pixel[] pixel : pixelLayout) {
            for (Pixel p : pixel) {
                int avg = (p.getBlue() + p.getGreen() + p.getRed()) / 3;
                p.setBlue(avg);
                p.setGreen(avg);
                p.setRed(avg);
            }
        }
        image.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage image = new APImage(pathOfFile);
        Pixel[][] pixelLayout = new Pixel[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixelLayout[i][j] = image.getPixel(i, j);
            }
        }
        for (Pixel[] pixel : pixelLayout) {
            for (Pixel p : pixel) {
                if (getAverageColour(p) >= 128) {
                    p.setBlue(255);
                    p.setGreen(255);
                    p.setRed(255);
                } else {
                    p.setBlue(0);
                    p.setGreen(0);
                    p.setRed(0);
                }
            }
        }
        image.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        // my favourite threshold value:
        // threshold = 15;
        APImage image = new APImage(pathToFile);
        Pixel[][] pixelLayout = new Pixel[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixelLayout[i][j] = image.getPixel(i, j);
            }
        }
        for (int i = 0; i < image.getWidth() - 1; i++) {
            for (int j = 0; j < image.getHeight() - 1; j++) {
                if (Math.abs(getAverageColour(pixelLayout[i][j]) - getAverageColour(pixelLayout[i+1][j+1])) >= threshold) {
                    //if (Math.abs(getAverageColour(pixelLayout[i][j]) - getAverageColour(pixelLayout[i+1][j])) >= threshold) {
                        pixelLayout[i][j].setBlue(0);
                        pixelLayout[i][j].setGreen(0);
                        pixelLayout[i][j].setRed(0);
                    //}
                } else {
                    pixelLayout[i][j].setBlue(255);
                    pixelLayout[i][j].setGreen(255);
                    pixelLayout[i][j].setRed(255);
                }
            }
        }

        image.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        Pixel[][] pixelLayout = new Pixel[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixelLayout[i][j] = image.getPixel(i, j);
            }
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                image.setPixel(i, j, pixelLayout[image.getWidth() - i - 1][j]);
            }
        }

        image.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage image = new APImage(pathToFile);
        APImage newDim = new APImage(image.getHeight(), image.getWidth());
        Pixel[][] pixelLayout = new Pixel[image.getWidth()][image.getHeight()];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                pixelLayout[i][j] = image.getPixel(i, j);
            }
        }
        for (int i = 0; i < image.getWidth() - 1; i++) {
            for (int j = 0; j < image.getHeight() - 1; j++) {
                newDim.setPixel(newDim.getWidth() - j - 1, i, pixelLayout[i][j]);
            }
        }
        newDim.draw();
    }


    //

    /**
     *
     * My own function: Pixelating the image
     *
     * Parameters: String filePath, int pixelSize
     *
     * for now pixelSize will just be 8
     */

    public static void pixelateImage(String pathToFile, int pixelSize) {
        APImage image = new APImage(pathToFile);

        int width = image.getWidth();
        int height = image.getHeight();

        int blockCols = width / pixelSize;
        int blockRows = height / pixelSize;

        for (int blockRow = 0; blockRow < blockRows; blockRow++) {
            for (int blockCol = 0; blockCol < blockCols; blockCol++) {
                int startX = blockCol * pixelSize;
                int startY = blockRow * pixelSize;

                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;
                int pixelCount = 0;

                for (int y = startY; y < startY + pixelSize && y < height; y++) {
                    for (int x = startX; x < startX + pixelSize && x < width; x++) {
                        Pixel p = image.getPixel(x, y);
                        totalRed += p.getRed();
                        totalGreen += p.getGreen();
                        totalBlue += p.getBlue();
                        pixelCount++;
                    }
                }

                int avgRed = totalRed / pixelCount;
                int avgGreen = totalGreen / pixelCount;
                int avgBlue = totalBlue / pixelCount;

                for (int y = startY; y < startY + pixelSize && y < height; y++) {
                    for (int x  = startX; x < startX + pixelSize && x < width; x++) {
                        Pixel p = image.getPixel(x, y);
                        p.setRed(avgRed);
                        p.setGreen(avgGreen);
                        p.setBlue(avgBlue);
                    }
                }
            }
        }

        image.draw();

    }

}
