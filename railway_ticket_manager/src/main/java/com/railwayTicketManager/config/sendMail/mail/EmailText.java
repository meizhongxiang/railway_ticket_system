package com.railwayTicketManager.config.sendMail.mail;

import java.time.LocalDateTime;

/**
 * @Author: Qidian
 * @ClassName: EmailText
 * @Date: 2019-06-30 22:49
 * @E-mail： qidian@gmail.com
 * @Description:发送邮箱内容
 * @Version: V1.0.0
 */
public class EmailText {
    private String txt;
    public String getTxt(String yzm,int param) {
        String txtConmment;
        txt="尊敬的用户:\r" +
                "  感谢您的注册！\r" +
                "  邮箱验证码为：\r" +
                "\r" +
                "\n" +yzm+
                "\n" +
                "\n" +
                "  为了确保您的账号安全，该验证码15分钟内有效。\n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" +
                "MWZ团队\n";
        txtConmment="尊敬的用户:\r" +
                "  感谢您的使用！\r" +
                "  邮箱验证码为：\r" +
                "\r" +
                "\n" +yzm+
                "\n" +
                "\n" +
                "  为了确保您的账号安全，该验证码15分钟内有效。\n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" ;
        return param==701?txt:txtConmment;
    }

    public String getExceptionTxt(LocalDateTime localDateTime, String stackTrace, String message) {
        String txtConmment;
        txt="尊敬的工作人员:\r" +
                "  你好！\r" +
                "  你所管理的系统在：" +localDateTime+"发生故障！"+
                "\r" +
                "\r" +
                "堆栈信息为：\n"+stackTrace+
                "\r" +
                "\n" +
                "系统分析的原因为：\n" +message+
                "所致。(系统分析原因仅供参考！)\n" +
                "\n" +
                "\n" +
                "  为了确保您的心情舒畅，请马上通知相关人员进行处理！\n" +
                " \n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" +
                "MWZ团队\n";
        return txt;
    }


    public String getPayTxt(String text) {
        txt="尊敬的用户您好:\r" +
                "  您已成功购买:" +text+
                "\r" +
                "\n" +
                "\n" +
                "  为了确保您的出行顺利，请携带有效证件提前进行取票。\n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" +
                "MWZ团队\n";
        return txt;
    }

    public String getCancelPayTxt(String text) {
        txt="尊敬的用户您好:\r" +
                "  您已成功退票" +text+
                "\r" +
                "\n" +
                "\n" +
                "  为了确保您的财产安全,请注意您的退款去向。\n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" +
                "MWZ团队\n";
        return txt;
    }

    public String getUpdatePayTxt(String text) {
        txt="尊敬的用户您好:\r" +
                "  您已成功改签" +text+
                "\r" +
                "\n" +
                "\n" +
                "  为了确保您的出行顺利，请携带有效证件提前进行取票。\n" +
                "  ******此邮件为系统发送，回复无效******\n" +
                "\n" +
                "MWZ团队\n";
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
