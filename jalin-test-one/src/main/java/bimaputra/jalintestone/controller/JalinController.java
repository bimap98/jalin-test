package bimaputra.jalintestone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/jalin")
@RequiredArgsConstructor
public class JalinController {

  @GetMapping("/input")
  public void getDataAlert() throws IOException {
    String file = "Data Alert.txt";
    Collection<String> bankNames = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(file));
    List<String> listBankAbbrevation = reader.lines().toList();
    for (var value : listBankAbbrevation) {
      String[] split = value.split(";");
      List<String> stringList = Arrays.asList(split);
      bankNames.add(stringList.get(0));
    }

    List<String> listUniqueBankAbbrevation = bankNames.stream().distinct().toList();

    for (var value : listUniqueBankAbbrevation) {

      var statementParentOne = "Selamat Siang Rekan Bank " + value + ",";
      var statementParentTwo = "Mohon bantuan untuk Sign on pada envi berikut";

      System.out.println(statementParentOne);
      System.out.println(statementParentTwo);

      for (var s : listBankAbbrevation) {
        String[] split = s.split(";");
        List<String> stringList = Arrays.asList(split);
        if (stringList.get(0).equals(value)) {
          var statementChild = "- Envi MP Port " + stringList.get(2) + " terpantau " + stringList.get(4);
          System.out.println(statementChild);
        }
      }

      var closeStatement = "Terima Kasih";
      System.out.println(closeStatement);
      System.out.println();
    }

  }
}
