package com.xl.tools;

public class RMBUtilsTest {

      public static void main(String agrs[]) {

           // ����
          System. out.println(RMBUtils.digitUppercase(0)); // ��Ԫ��

          System. out.println(RMBUtils.digitUppercase(123)); // Ҽ�۷�ʰ��Ԫ��

          System. out.println(RMBUtils.digitUppercase(1000000)); // Ҽ����Ԫ��

          System. out.println(RMBUtils.digitUppercase(100000001)); // Ҽ����ҼԪ��

          System. out.println(RMBUtils.digitUppercase(1000000000)); // Ҽʰ��Ԫ��

          System. out.println(RMBUtils.digitUppercase(1234567890)); // Ҽʰ������Ǫ������ʰ½����Ǫ�ư۾�ʰԪ��

          System. out.println(RMBUtils.digitUppercase(1001100101)); // Ҽʰ����Ҽ��Ҽʰ����Ҽ����ҼԪ��

          System. out.println(RMBUtils.digitUppercase(110101010)); // Ҽ��ҼǪ��Ҽʰ��ҼǪ��ҼʰԪ��

           // С��
          System. out.println(RMBUtils.digitUppercase(0.12)); // Ҽ�Ƿ���

          System. out.println(RMBUtils.digitUppercase(123.34)); // Ҽ�۷�ʰ��Ԫ��������

          System. out.println(RMBUtils.digitUppercase(1000000.56)); // Ҽ����Ԫ���½��

          System. out.println(RMBUtils.digitUppercase(100000001.78)); // Ҽ����ҼԪ��ǰƷ�

          System. out.println(RMBUtils.digitUppercase(1000000000.90)); // Ҽʰ��Ԫ����

          System. out.println(RMBUtils.digitUppercase(1234567890.03)); // Ҽʰ������Ǫ������ʰ½����Ǫ�ư۾�ʰԪ����

          System. out.println(RMBUtils.digitUppercase(1001100101.00)); // Ҽʰ����Ҽ��Ҽʰ����Ҽ����ҼԪ��

          System. out.println(RMBUtils.digitUppercase(110101010.10)); // Ҽ��ҼǪ��Ҽʰ��ҼǪ��ҼʰԪҼ��

           // ����
          System. out.println(RMBUtils.digitUppercase(-0.12)); // ��Ҽ�Ƿ���

          System. out.println(RMBUtils.digitUppercase(-123.34)); // ��Ҽ�۷�ʰ��Ԫ��������

          System. out.println(RMBUtils.digitUppercase(-1000000.56)); // ��Ҽ����Ԫ���½��

          System. out.println(RMBUtils.digitUppercase(-100000001.78)); // ��Ҽ����ҼԪ��ǰƷ�

          System. out.println(RMBUtils.digitUppercase(-1000000000.90)); // ��Ҽʰ��Ԫ����

          System. out.println(RMBUtils.digitUppercase(-1234567890.03)); // ��Ҽʰ������Ǫ������ʰ½����Ǫ�ư۾�ʰԪ����

          System. out.println(RMBUtils.digitUppercase(-1001100101.00)); // ��Ҽʰ����Ҽ��Ҽʰ����Ҽ����ҼԪ��

          System. out.println(RMBUtils.digitUppercase(-110101010.10)); // ��Ҽ��ҼǪ��Ҽʰ��ҼǪ��ҼʰԪҼ��

     }

}
