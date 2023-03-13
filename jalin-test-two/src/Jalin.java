import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Jalin {
  private JPanel JalinPanel;
  private JTextField tfMessage;
  private JTextField tfUrl;
  private JTextField tfStatis;
  private JTextField tfUser;
  private JTextField tfPass;
  private JTextField tfInKey;
  private JTextField tfHeader;
  private JTextField tfResultHmac;
  private JButton genHMACButton;
  private JButton loadButton;
  private JButton hapusButton;
  private JComboBox messageEnum;
  private JTextPane tpPath;
  private JTextPane tpParamGenerateHmac;
  private JButton kirimButton;
  private JTextField tfResponse;

  public static void main(String[] args) {
    JFrame frame = new JFrame("Jalin");
    frame.setContentPane(new Jalin().JalinPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public Jalin() {

    kirimButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String user, pass, header, url, path, resultHmac;

        user = tfUser.getText();
        pass = tfPass.getText();
        header = tfHeader.getText();
        url = tfUrl.getText();
        path = tpPath.getText();
        resultHmac = tfResultHmac.getText();

        JSONObject objectUserPass = new JSONObject();
        objectUserPass.put("user", user);
        objectUserPass.put("pass", pass);
        objectUserPass.put("header", header);
        objectUserPass.put("url", url);
        objectUserPass.put("path", path);
        objectUserPass.put("hmac", resultHmac);

        tfResponse.setText(objectUserPass.toString(4));



      }
    });
    genHMACButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String key, paramHmac, hmacMD5Algorithm;

        key = tfInKey.getText();
        paramHmac = tpParamGenerateHmac.getText();
        hmacMD5Algorithm = "HmacMD5";

        String result = hmacWithApacheCommons(hmacMD5Algorithm, paramHmac, key);

        tfResultHmac.setText(result);


      }
    });
    hapusButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        tfMessage.setText("");
        tfUrl.setText("");
        tpPath.setText("");
        tfResponse.setText("");
        tfUser.setText("");
        tfPass.setText("");
        tfInKey.setText("");
        tfHeader.setText("");
        tfResultHmac.setText("");
      }
    });
    messageEnum.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        JSONObject objectPayment = new JSONObject();
        objectPayment.put("msisdn", "082285643282-payment");
        objectPayment.put("amount", "00000000");
        objectPayment.put("trxDate", "2020-06-09T11:16:33+07:00");
        objectPayment.put("merchantTrxID", "0005502227972");


        JSONObject objectCheck = new JSONObject();
        objectCheck.put("msisdn", "082285643282-check");
        objectCheck.put("amount", "00000000");
        objectCheck.put("trxDate", "2020-06-09T11:16:33+07:00");
        objectCheck.put("merchantTrxID", "0005502227972");

        JSONObject objectRefund = new JSONObject();
        objectRefund.put("msisdn", "082285643282-refund");
        objectRefund.put("amount", "00000000");
        objectRefund.put("trxDate", "2020-06-09T11:16:33+07:00");
        objectRefund.put("merchantTrxID", "0005502227972");

        JSONObject objectInquiry = new JSONObject();
        objectInquiry.put("msisdn", "082285643282-inquiry");
        objectInquiry.put("amount", "00000000");
        objectInquiry.put("trxDate", "2020-06-09T11:16:33+07:00");
        objectInquiry.put("merchantTrxID", "0005502227972");

        JSONObject objectPaymentDebit = new JSONObject();
        objectPaymentDebit.put("msisdn", "082285643282-payment debit");
        objectPaymentDebit.put("amount", "00000000");
        objectPaymentDebit.put("trxDate", "2020-06-09T11:16:33+07:00");
        objectPaymentDebit.put("merchantTrxID", "0005502227972");

        JSONObject[] choices = {objectPayment, objectCheck, objectRefund, objectInquiry, objectPaymentDebit};

        JSONObject selectChoice = choices[messageEnum.getSelectedIndex()];

        tfMessage.setText(CodeFormatter.formatToJSON(selectChoice.toString(4)));

      }
    });
  }

  public static String hmacWithApacheCommons(String algorithm, String data, String key) {
    return new HmacUtils(algorithm, key).hmacHex(data);
  }


}
