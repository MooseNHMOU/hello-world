import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
/* 2017.11.24  ---�� 2017.11.27��������
* ����pdf�ļ��еı��
* */
public class getInfoFromPDF3 {
   private static String result = null;  // ��������pdf�ļ��е���Ϣ
   private static FileInputStream is = null;  // ������
   private static PDDocument document = null;   
//��ȡpdf�ļ��ļ���ȫ����Ϣ
    public static String getAllInfoFromPDF(String pdfFilePath){  
        String result = null;  
        FileInputStream is = null;  
        PDDocument document = null;  
        try {  
            is = new FileInputStream(pdfFilePath);  
            PDFParser parser = new PDFParser((RandomAccessRead) is);  
            parser.parse();  
            document = parser.getPDDocument();  
            PDFTextStripper stripper = new PDFTextStripper();  
            result = stripper.getText(document);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (is != null) {  
                try {  
                    is.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (document != null) {  
                try {  
                    document.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return result;  
    }  
    public static void main(String[] args) throws IOException {
    /*  
* ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼����PDF�ļ�
*/
// ͨ�������ļ�·����ȡ�ļ�
    String code = null;
File file = new File("F:\\pdf�ļ�����");
// �ѻ�ȡ�����ļ���������������
   File[] files = file.listFiles();
   for(File f:files){
    //--------------------------1.��ȡ�ļ�·��
    f.getAbsolutePath();
    System.out.println("�ļ�·��:"+f.getAbsolutePath());
    //��ȡ�ļ����ͣ����ļ���׺��
    int start = f.getAbsolutePath().length()-3;
    int end  = f.getAbsolutePath().length();
    //�õ��ļ��ĺ�׺��
    String pdf = f.getAbsolutePath().substring(start, end);
    //�ж��Ƿ���pdf��ʽ���ļ�
    if(pdf.equals("pdf") || pdf.equals("PDF")){
    // ��pdf��ʽ���ļ�
    //�õ�ȫ��pdf�ļ��е���Ϣ
    String str = getInfoFromPDF3.getAllInfoFromPDF(f.getAbsolutePath());
    //----------------------2.��ȡpdf�ļ��еı��
//      String code = str.substring(str.indexOf("��")+1,str.indexOf("��")+27);
    //��ȡpdf�ļ��еı��
    code = str.substring(str.indexOf("��ţ�")+3,str.indexOf("��")+27);
    /*
    * 2017.11.27��������   
    * ȡ���ı��е����ɸ���ţ���ȥ���ظ���
    * 
    * */
///////////////////2017.11.27��������/////////////////////////////////////////
    //��ȡ��ȫ���ı��
    Set set = new HashSet();
    Iterator<String> it = null;
    String regex = "\\d{26}";  
    Pattern pattern = Pattern.compile(regex);  
    Matcher matcher = pattern.matcher(str);  
    while (matcher.find()) { 
    set.add(matcher.group());
    it = set.iterator();
               //System.out.println(matcher.group());   
    }  
    while (it.hasNext()) {
    String getCode = it.next();
    System.out.println("���Ϊ��"+getCode);
    }
/////////////////////////////////////////////////////////////
    //----------------------3.��ȡpdf �ļ��е�����(һ���������һ����Ȩ��Ϊһ��)
    int count=0,StringStart=0;
    while(str.indexOf("����������Ϣ�������ݿ����������Ϣ�ɼ���Ȩ��", StringStart)>=0 && StringStart<str.length()){
    //���ַ��������Ӵ�ʱ���������ַ�������
    count++;
    StringStart=str.indexOf("����������Ϣ�������ݿ����������Ϣ�ɼ���Ȩ��", StringStart)+"����������Ϣ�������ݿ����������Ϣ�ɼ���Ȩ��".length();//�õ��µ�startֵ��
    }
    System.out.println("����--"+count+"--��");   
    System.out.println("---------------------------------------------------------------------");
    }
   }
    }
}