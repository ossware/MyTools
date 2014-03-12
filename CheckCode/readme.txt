1、AuthImageServlet是生成验证码的servlet，然后把生成的验证码放到session里：session.setAttribute("ValidateCode", sRand);
2、从session里取验证码，然后后台对比