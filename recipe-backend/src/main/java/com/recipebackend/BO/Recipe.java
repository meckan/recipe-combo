package com.recipebackend.BO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String type;
    @Lob
    private String recipeText;
    private String recipeNutrients;
    private String author;

    private int portions;
    private int min;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId")
    private List<Ingredient> ingredients;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pictureId")
    private Picture picture;

    public Recipe(Integer id, String name, String type, String recipeText, String recipeNutrients,
                  String author, int portions, int min, List<Ingredient> ingredients, Picture picture) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.recipeText = recipeText;
        this.recipeNutrients = recipeNutrients;
        this.author = author;
        this.portions = portions;
        this.min = min;
        this.ingredients = ingredients;
        this.picture = picture;
    }

    public Recipe(String name, String type, String recipeText, String recipeNutrients, String author,
                  int portions, int min, List<Ingredient> ingredients, Picture picture) {
        this.name = name;
        this.type = type;
        this.recipeText = recipeText;
        this.recipeNutrients = recipeNutrients;
        this.author = author;
        this.portions = portions;
        this.min = min;
        this.ingredients = ingredients;
        this.picture = picture;
    }

    public Recipe() {
    }

    public String convertPictureToTextByteArray(byte[] recipeText) throws TesseractException, IOException {
        String contentType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(recipeText));
        File convFile = new File("temp." + contentType.split("/")[1]);
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(recipeText);
        fos.close();

        String returnText = convertPictureToText(convFile);
        //convFile.delete();
        return returnText;
        //return "Not working kolla return";
    }

    public String convertPictureToText(File image) throws TesseractException {
        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("swe");
        //tesseract.setDatapath("src\\main\\resources\\tessdata");
        tesseract.setDatapath("D:/Skola/IdeaProjects/recipeCollection/src/main/resources/tessdata");

        tesseract.setTessVariable("user_defined_dpi", "300");

        //tesseract.setPageSegMode(1);
        //tesseract.setOcrEngineMode(1);
        String result = tesseract.doOCR(image);
        result = result.replaceAll("\\.\n", "\\.\n\n");
        recipeText = result;
        return result;
    }

    public String processRecipeText(File recipeFile, String screenOrientation) throws IOException, TesseractException {

        OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        BufferedImage image = ImageIO.read(recipeFile);

        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);

        Mat mat1 = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);

        if (screenOrientation.equals("portrait")) {
            Core.rotate(mat, mat1, Core.ROTATE_90_CLOCKWISE);
            Imgproc.cvtColor(mat1, mat1, Imgproc.COLOR_RGB2GRAY);

            System.out.println("Detta är från rotate 90");
        } else if (screenOrientation.equals("landScape")) {
            Core.rotate(mat, mat1, Core.ROTATE_180);
            Imgproc.cvtColor(mat1, mat1, Imgproc.COLOR_RGB2GRAY);

            System.out.println("Hejsan detta är inne i rotate 180");
        }

        byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
        mat1.get(0, 0, data1);
        BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
        //BufferedImage image1 = new BufferedImage(mat1.cols(),mat1.rows(), BufferedImage.TYPE_3BYTE_BGR);

        image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);

        //File ouptut = new File("grayscale.jpg");
        File ouptut = new File("rotateTest.jpg");

        ImageIO.write(image1, "jpg", ouptut);

        return convertPictureToText(ouptut);
        //return "Testing";
    }


    //@formatter:off
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRecipeText() {
        return recipeText;
    }
    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }
    public String getRecipeNutrients() {
        return recipeNutrients;
    }
    public void setRecipeNutrients(String recipeNutrients) {
        this.recipeNutrients = recipeNutrients;
    }
    public int getPortions() {
        return portions;
    }
    public void setPortions(int portions) {
        this.portions = portions;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    public Picture getPicture() {
        return picture;
    }
    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    //@formatter:on
    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", recipeText='" + recipeText + '\'' +
                ", recipeNutrients='" + recipeNutrients + '\'' +
                ", author='" + author + '\'' +
                ", portions=" + portions +
                ", min=" + min +
                ", ingredients=" + ingredients +
                ", picture=" + picture +
                '}';
    }
}
