package com.orm.mylibrary.test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class GenerateValueFiles {

    private int baseW;
    private int baseH;

    private String dirStr = "d:/workspace/car/res";

    private final static String height = "<dimen name=\"head{0}\">{1}px</dimen>\n";
    private final static String margin = "<dimen name=\"margin{0}\">{1}px</dimen>\n";
    private final static String size = "<dimen name=\"size{0}\">{1}px</dimen>\n";
    private final static String head = "<dimen name=\"head{0}\">{1}px</dimen>\n";
    
    private final static int htr[]=new int[]{10,15,20,25,30,35,40,45,50,60,65,70,75,80,100};
    private final static int hsstr[]=new int[]{120,80,220,160,60,260,100,190};
    private final static int sstr[]=new int[]{12,14,16,18,20,22,24,26,28,30};
    private final static int mstr[]=new int[]{5,10,15,20,25,2,34,45,46};
    /**
     * {0}-HEIGHT
     */
    private final static String VALUE_TEMPLATE = "values-{0}x{1}";

    private static final String SUPPORT_DIMESION = "320,480;480,800;480,854;540,960;600,1024;720,1184;720,1196;720,1280;768,1024;800,1280;1080,1812;1080,1920;1440,2560;";

    private String supportStr = SUPPORT_DIMESION;

    public GenerateValueFiles(int baseX, int baseY, String supportStr) {
        this.baseW = baseX;
        this.baseH = baseY;

        if (!this.supportStr.contains(baseX + "," + baseY)) {
            this.supportStr += baseX + "," + baseY + ";";
        }

        this.supportStr += validateInput(supportStr);

        System.out.println(supportStr);

        File dir = new File(dirStr);
        if (!dir.exists()) {
            dir.mkdir();

        }
        System.out.println(dir.getAbsoluteFile());

    }

    /**
     * @param supportStr
     *            w,h_...w,h;
     * @return
     */
    private String validateInput(String supportStr) {
        StringBuffer sb = new StringBuffer();
        String[] vals = supportStr.split("_");
        int w = -1;
        int h = -1;
        String[] wh;
        for (String val : vals) {
            try {
                if (val == null || val.trim().length() == 0)
                    continue;

                wh = val.split(",");
                w = Integer.parseInt(wh[0]);
                h = Integer.parseInt(wh[1]);
            } catch (Exception e) {
                System.out.println("skip invalidate params : w,h = " + val);
                continue;
            }
            sb.append(w + "," + h + ";");
        }

        return sb.toString();
    }

    public void generate() {
        String[] vals = supportStr.split(";");
        for (String val : vals) {
            String[] wh = val.split(",");
            generateXmlFile(Integer.parseInt(wh[0]), Integer.parseInt(wh[1]));
        }

    }

    private void generateXmlFile(int w, int h) {

        StringBuffer sbForWidth = new StringBuffer();
        sbForWidth.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sbForWidth.append("<resources>");
        float cellw = w * 1.0f / baseW;

        System.out.println("width : " + w + "," + baseW + "," + cellw);
        
        
       /**head**/
        for (int i = 0; i < hsstr.length; i++) {
            sbForWidth.append(head.replace("{0}", (i +1)+"").replace("{1}",
                    change(cellw * hsstr[i]) + ""));
        }
        
        
  
        
        /**size**/
        
        
        for (int i = 0; i <sstr.length; i++) {
        	sbForWidth.append(size.replace("{0}", (i +1)+"").replace("{1}",
                    change(cellw * sstr[i]) + ""));
        } 
        
        /**margin**/
        
        
        for (int i = 0; i < mstr.length; i++) {
        	sbForWidth.append(margin.replace("{0}", (i +1)+"").replace("{1}",
                    change(cellw * mstr[i]) + ""));
        }
        
        /**height**/
        
        
        for (int i = 0; i < htr.length; i++) {
        	sbForWidth.append(height.replace("{0}", (i +1)+"").replace("{1}",
                    change(cellw * htr[i]) + ""));
        } 
        
        
        sbForWidth.append("</resources>");

        
        
        
        File fileDir = new File(dirStr + File.separator
                + VALUE_TEMPLATE.replace("{0}", h + "")
                        .replace("{1}", w + ""));
        fileDir.mkdir();

        File layxFile = new File(fileDir.getAbsolutePath(), "dimens.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layxFile));
            pw.print(sbForWidth.toString());
            pw.close();
           
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }

    public static void main(String[] args) {
        int baseW = 480;
        int baseH = 800;
        String addition = "";
        try {
            if (args.length >= 3) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
                addition = args[2];
            } else if (args.length >= 2) {
                baseW = Integer.parseInt(args[0]);
                baseH = Integer.parseInt(args[1]);
            } else if (args.length >= 1) {
                addition = args[0];
            }
        } catch (NumberFormatException e) {

            System.err
                    .println("right input params : java -jar xxx.jar width height w,h_w,h_..._w,h;");
            e.printStackTrace();
            System.exit(-1);
        }

        new GenerateValueFiles(baseW, baseH, addition).generate();
    }

}