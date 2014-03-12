package com.xl.tools;

public class RMBUtils {
     
      /**
      * ���ֽ���дת����˼����д��������Ȼ������ʰ�滻���� Ҫ�õ�������ʽ
      */
      public static String digitUppercase( double n) {
          String fraction[] = { "��", "��" };
          String digit[] = { "��", "Ҽ" , "��", "��", "��" , "��" , "½", "��", "��" , "��" };
          String unit[][] = { { "Ԫ", "��" , "��" }, { "", "ʰ" , "��", "Ǫ" } };
          String head = n < 0 ? "��" : "" ;
          n = Math. abs(n);
          String s = "";
           for (int i = 0; i < fraction. length; i++) {
              s += (digit[( int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll( "(��.)+", "" );
          }
           if (s.length() < 1) {
              s = "��";
          }
           int integerPart = (int) Math.floor(n);
           for (int i = 0; i < unit[0]. length && integerPart > 0; i++) {
              String p = "";
               for (int j = 0; j < unit[1]. length && n > 0; j++) {
                   p = digit[integerPart % 10] + unit[1][j] + p;
                   integerPart = integerPart / 10;
              }
              s = p.replaceAll( "(��.)*��$" , "").replaceAll("^$" , "��") + unit[0][i] + s;
          }

           return head + s.replaceAll("(��.)*��Ԫ" , "Ԫ").replaceFirst( "(��.)+", "" ).replaceAll("(��.)+", "��").replaceAll("^��$" , "��Ԫ��");
     }

}
